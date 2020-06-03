import java.security.PublicKey;
import java.util.Iterator;
import kademlia_public_ledger.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionOutput {
    public String id;
    public PublicKey reciepient; //also known as the new owner of these coins.
    public float value; //the amount of coins they own
    public String parentTransactionId; //the id of the transaction this output was created in

    //Constructor
    public TransactionOutput(PublicKey reciepient, float value, String parentTransactionId) {
        this.reciepient = reciepient;
        this.value = value;
        this.parentTransactionId = parentTransactionId;
        this.id = StringUtil.applySha256(StringUtil.getStringFromKey(reciepient)+Float.toString(value)+parentTransactionId);
    }

    // usado para parses
    public TransactionOutput(String id, String reciepient, float value, String parentTransactionId) {
        this.id = id;
        //this.reciepient = Base64.getDecoder().decode(reciepient);
        this.value = value;
        this.parentTransactionId = parentTransactionId;
    }

    //Check if coin belongs to you
    public boolean isMine(PublicKey publicKey) {
        return (publicKey == reciepient);
    }

    public static ArrayList<TransactionOutput> copyFrom(List<TransactionOutput_> outputs_){
        ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

        for (int i=0; i<outputs_.size(); i++){
            TransactionOutput_ tout_ = outputs_.get(i);
            outputs.add(TransactionOutput.copyFrom(tout_));
        }
        return outputs;
    }

    public static TransactionOutput copyFrom(TransactionOutput_ output_) {
        return new TransactionOutput(output_.getId(), output_.getReciepient(), output_.getValue(), output_.getParentTransactionId());
    }
}
