import java.util.Date;

public class block {

        public String hash;
        public String previousHash;
        private String data; //our data will be a simple message.
        private long timeStamp; //as number of milliseconds since 1/1/1970.

        //Block Constructor.
        public block(String data,String previousHash ) {
            this.data = data;
            this.previousHash = previousHash;
            this.timeStamp = new Date().getTime();
        }
}




