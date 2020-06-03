import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import kademlia_public_ledger.*;
import com.google.common.collect.Iterators;

public class Block {

    public String hash;
    public String previousHash;
    public String merkleRoot;
    private String data; //our data will be a simple message. in the future it will be transactions
    public ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private long timeStamp; //as number of milliseconds since 1/1/1970.
    private int nonce;

    //Block Constructor.
    public Block(String previousHash, String data) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();

        this.hash = calculateHash(); //Making sure we do this after we set the other values.
    }

    //faltam as transacos aqui
    public Block(String hash, String previousHash, String merkleRoot, String data, long timestamp, int nonce) {
        this.hash = hash;
        this.data = data;
        this.previousHash = previousHash;
        this.merkleRoot = merkleRoot;
        //this.transactions = transactions;
        this.timeStamp = timestamp;
        this.nonce = nonce;
    }

    public static ArrayList<Block> copyFrom(Iterator<Block_> blocks_) {
        ArrayList<Block> ichain = new ArrayList<Block>();
        while(blocks_.hasNext()){
            ichain.add(copyFrom(blocks_.next()));
        }
        return ichain;
    }

    public static  Block copyFrom(Block_ block_) {
        return new Block(block_.getHash(),
                         block_.getPreviousHash(),
                         block_.getMerkleRoot(),
                         block_.getData(),
                         //Transaction.copyFrom(block_.getTransactionsList()),
                         block_.getTimestamp(),
                         block_.getNonce());
    }

    //Calculate new hash based on blocks contents
    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        merkleRoot
        );
        return calculatedhash;
    }

    public Block_.Builder toBlock_(){
        return Block_.newBuilder().setHash(this.hash)
                                    .setPreviousHash(this.previousHash)
                                    .setMerkleRoot(this.merkleRoot)
                                    .setData(this.data)
                                    .addAllTransactions(Transaction.toTransactions_(this.transactions))
                                    .setTimestamp(this.timeStamp)
                                    .setNonce(this.nonce);
    }

    //Increases nonce value until hash target is reached.
    public void mineBlock(int difficulty) {
        merkleRoot = StringUtil.getMerkleRoot(transactions);
        String target = StringUtil.getDificultyString(difficulty); //Create a string with difficulty * "0"
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    //Add transactions to this block
    public boolean addTransaction(Transaction transaction) {
        //process transaction and check if valid, unless block is genesis block then ignore.
        if(transaction == null) return false;
        if((!"0".equals(previousHash))) {
            if((transaction.processTransaction() != true)) {
                System.out.println("Transaction failed to process. Discarded.");
                return false;
            }
        }

        transactions.add(transaction);
        System.out.println("Transaction Successfully added to Block");
        return true;
    }

}