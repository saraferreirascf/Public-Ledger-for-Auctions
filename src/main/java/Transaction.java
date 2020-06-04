import java.security.*;
import java.util.ArrayList;
import java.util.Iterator;
import kademlia_public_ledger.*;
import java.util.List;
import com.google.protobuf.ByteString;

public class Transaction {

    public String transactionId; //Contains a hash of transaction*
    public PublicKey sender; //Senders address/public key.
    public static PublicKey reciepient; //Recipients address/public key.
    public float value; //Contains the amount we wish to send to the recipient.
    public byte[] signature; //This is to prevent anybody else from spending funds in our wallet.

    public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

    private static int sequence = 0; //A rough count of how many transactions have been generated

    // Constructor:
    public Transaction(PublicKey from, PublicKey to, float value,  ArrayList<TransactionInput> inputs) {
        this.sender = from;
        this.reciepient = to;
        this.value = value;
        this.inputs = inputs;
    }

    public Transaction(String tid, String from, String to, float value, byte[] s, ArrayList<TransactionInput> inputs, ArrayList<TransactionOutput> outputs) {
        //this.sender = from;
        //this.reciepient = to;
        this.transactionId = tid;
        this.value = value;
        this.signature = s;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public static Iterable<Transaction_> toTransactions_(ArrayList<Transaction> ts){
        ArrayList<Transaction_> result = new ArrayList<Transaction_>();
        for (int i=0; i<ts.size(); i++) {
            Transaction t = ts.get(i);
            result.add(toTransaction_(t, null));
        }
        return  getIterableFromIterator(result.iterator());
    }

    public static Transaction_ toTransaction_ (Transaction transaction, Binary_tree.Node inode){
        if (inode != null)
            return Transaction_.newBuilder().setSenderNode(inode.toBasicNode()).setTransactionId(transaction.transactionId).setSender(StringUtil.getStringFromKey(transaction.sender)).setReciepient(StringUtil.getStringFromKey(transaction.reciepient)).setValue((long)transaction.value).setSignature(ByteString.copyFrom(transaction.signature)).build();
        return Transaction_.newBuilder().setTransactionId(transaction.transactionId).setSender(StringUtil.getStringFromKey(transaction.sender)).setReciepient(StringUtil.getStringFromKey(transaction.reciepient)).setValue((long)transaction.value).setSignature(ByteString.copyFrom(transaction.signature)).build();
    }

    public static <T> Iterable<T> 
    getIterableFromIterator(Iterator<T> iterator) 
    { 
        return new Iterable<T>() { 
            @Override
            public Iterator<T> iterator() 
            { 
                return iterator; 
            } 
        }; 
    } 

    public static ArrayList<Transaction> copyFrom(List<Transaction_> transactions_){
        ArrayList<Transaction> itransactions = new ArrayList<Transaction>();
        for (int i=0; i<transactions_.size(); i++){
            itransactions.add(Transaction.copyFrom(transactions_.get(i)));
        }
        return itransactions;
    }

    public static Transaction copyFrom(Transaction_ transaction_){
        return new Transaction(transaction_.getTransactionId(), transaction_.getSender(), transaction_.getReciepient(), transaction_.getValue(), transaction_.getSignature().toByteArray(), TransactionInput.copyFrom(transaction_.getInputsList()), TransactionOutput.copyFrom(transaction_.getOutputsList()));
    }

    public boolean processTransaction() {

        if(verifySignature() == false) {
            System.out.println("#Transaction Signature failed to verify");
            return false;
        }

        //Gathers transaction inputs (Making sure they are unspent):
        for(TransactionInput i : inputs) {
            i.UTXO = NoobChain.UTXOs.get(i.transactionOutputId);
        }

        //Checks if transaction is valid:
        if(getInputsValue() < NoobChain.minimumTransaction) {
            System.out.println("Transaction Inputs too small: " + getInputsValue());
            System.out.println("Please enter the amount greater than " + NoobChain.minimumTransaction);
            return false;
        }

        //Generate transaction outputs:
        float leftOver = getInputsValue() - value; //get value of inputs then the left over change:
        transactionId = calulateHash();
        outputs.add(new TransactionOutput( this.reciepient, value,transactionId)); //send value to recipient
        outputs.add(new TransactionOutput( this.sender, leftOver,transactionId)); //send the left over 'change' back to sender

        //Add outputs to Unspent list
        for(TransactionOutput o : outputs) {
            NoobChain.UTXOs.put(o.id , o);
        }

        //Remove transaction inputs from UTXO lists as spent:
        for(TransactionInput i : inputs) {
            if(i.UTXO == null) continue; //if Transaction can't be found skip it
            NoobChain.UTXOs.remove(i.UTXO.id);
        }

        return true;
    }

    public float getInputsValue() {
        float total = 0;
        for(TransactionInput i : inputs) {
            if(i.UTXO == null) continue; //if Transaction can't be found skip it, This behavior may not be optimal.
            total += i.UTXO.value;
        }
        return total;
    }

    public void generateSignature(PrivateKey privateKey) {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + Float.toString(value)	;
        signature = StringUtil.applyECDSASig(privateKey,data);
    }

    public boolean verifySignature() {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + Float.toString(value)	;
        return StringUtil.verifyECDSASig(sender, data, signature);
    }

    public float getOutputsValue() {
        float total = 0;
        for(TransactionOutput o : outputs) {
            total += o.value;
        }
        return total;
    }

    private String calulateHash() {
        sequence++; //increase the sequence to avoid 2 identical transactions having the same hash
        return StringUtil.applySha256(
                StringUtil.getStringFromKey(sender) +
                        StringUtil.getStringFromKey(reciepient) +
                        Float.toString(value) + sequence
        );
    }
}