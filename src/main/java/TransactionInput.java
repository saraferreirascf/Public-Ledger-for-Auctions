import java.util.Iterator;
import kademlia_public_ledger.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionInput {
    public String transactionOutputId; //Reference to TransactionOutputs -> transactionId
    public TransactionOutput UTXO; //Contains the Unspent transaction output

    public TransactionInput(String transactionOutputId) {
        this.transactionOutputId = transactionOutputId;
    }

    public TransactionInput(String transactionOutputId, TransactionOutput out) {
        this.transactionOutputId = transactionOutputId;
        this.UTXO = out;
    }

    public static ArrayList<TransactionInput> copyFrom(List<TransactionInput_> inputs_){
        ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
        for (int i=0; i<inputs_.size(); i++)
            inputs.add(TransactionInput.copyFrom(inputs_.get(i)));
        return inputs;
    }

    public static TransactionInput copyFrom(TransactionInput_ input_) {
        return new TransactionInput(input_.getTransactionOutputId(), TransactionOutput.copyFrom(input_.getUTXO()));
    }
}
