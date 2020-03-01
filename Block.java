import java.util.Date;


public class Block {

    public String hash;
    public String previousHash;
    //our data will be a simple message.
    private String data;
    //as number of milliseconds since 1/1/1970.
    private long timeStamp;

    /**
     *  <p>Block Constructor.</p>
     * @param {string} data
     * @param {string} previousHash
     */
    public block(String data,String previousHash ) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        //Making sure we do this after we set the other values.
        this.hash = calculateHash();
    }

    /**
     *
     * @return {string}
     */
    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        data
        );
        return calculatedhash;
    }
}




