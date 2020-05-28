import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Iterator;
import java.io.File;
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.net.URL;
import kademlia_public_ledger.*;
import com.google.protobuf.Message;
import io.grpc.Channel;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.TimeUnit;
import io.grpc.StatusRuntimeException;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.math.BigInteger;
import com.google.protobuf.ByteString;

public class Binary_tree {
    private final Logger logger = Logger.getLogger(Binary_tree.class.getName());

    ArrayList<KBucket> kbuckets = new ArrayList<KBucket>();
    Key iaddress;
    Node current = null;
    P2PServer server = null;
    P2PClient client = null;
    BigInteger k = new BigInteger("1");
    BigInteger maxk = getMaxK();
    int nsize = 20;
    int alpha = 3;
    TestUnit_ testUnit = new TestUnit_();

    Binary_tree() throws java.io.IOException {
        this.current = new Node();
        this.server = new P2PServer(this.current.port);
        iaddress = new Key();
        generateRandom160bits(iaddress);
    }

    Binary_tree(int p) throws java.io.IOException {
        this.current = new Master_node(p);
        this.server = new P2PServer(p);
        iaddress = new Key();
        generateRandom160bits(iaddress);
    }

    public void lookup(Key key) throws InterruptedException {
        logger.info("started lookup");
        ArrayList<Key> listenKeys = new ArrayList<Key>();
        ArrayList<Node> closest = new ArrayList<Node>();
        ArrayList<Node> father = new ArrayList<Node>();
        Key idistance = new Key();
        idistance.MAX_KEY();
        KBucket ikbucket = null;

        // find closest non-empty k-bucket
        for (int i=0; i<kbuckets.size(); i++) {
            KBucket tmpkbucket = kbuckets.get(i);
            Key tmpdistance = tmpkbucket.prefix.xor(key);
            if (tmpdistance.compareTo(idistance) < 0) {
                idistance = tmpdistance;
                ikbucket = tmpkbucket;
            }
        }
        
        logger.info("lookup kbucket size: " + ikbucket.nodes.size());
        // Send Find Node to alpha(=3) closest nodes
        // sorte list
        if (ikbucket != null) {
            Collections.sort(ikbucket.nodes, new Comparator<Node>() {
                @Override
                public int compare(Node n1, Node n2) {
                    return n1.nodeID.xor(key).compareTo(n2.nodeID.xor(key));
                }
            });

            int sublsize = (ikbucket.nodes.size() < alpha) ? ikbucket.nodes.size(): alpha;
            father = ikbucket.nodes.subList(0, sublsize);
            closest = ikbucket.nodes.subList(0, sublsize);
            logger.info("send alpha FIND NODES!!!");
            for (int i=0; i<sublsize; i++) {
                // send alpha FIND NODES
                Node inode = father.get(i);
                logger.info("round1(" + i + ") id:" + inode.nodeID.kToBigInt() + " port: " + inode.port);
                client = new P2PClient(ManagedChannelBuilder.forTarget(inode.ip + ":" + inode.port).usePlaintext().build(););
                Iterator<NodeInfo> round1 = client.FIND_NODE(inode.toNodeID().build());
                listenKeys.add(inode.nodeID);
                while ( round1 != null && round1.hasNext()) {
                    NodeInfo info = round1.next();
                    Key infokey = new Key( info.getNid().getNodeID().toByteArray());
                    if (infokey.compareTo(idistance) < 0) {
                        idistance = infokey;
                        closest.add(infokey);
                    }
                    // round2 send findnode to k triples
                    logger.info("round2(" + i + ") id:" + new BigInteger(info.getNid().getNodeID().toByteArray()) + " port: " + info.getPort());
                    client = new P2PClient(ManagedChannelBuilder.forTarget(info.getIp() + ":" + inode.getPort()).usePlaintext().build());
                    Iterator<NodeInfo> round2 = client.FIND_NODE(info.getNid());
                    allKeys.add(info);
                    listenKeys.add(info);
                    int count = 0;
                    while ( (round2 != null && round2.hasNext()) || count == alpha){
                        NodeInfo info = round2.next();
                        Key infoKey = new key(info.getNid().getNodeID().toByteArray());
                        for (Key ikey: listenKeys){
                            if (infoKey.compareTo(ikey) == 0) {
                                logger.info("round3(" + i + ") id:" + new BigInteger(info.getNid().getNodeID().toByteArray()) + " port: " + info.getPort());
                                client = new P2PClient(ManagedChannelBuilder.forTarget(info.getIp() + ":" + inode.getPort()).usePlaintext().build(););
                                Iterator<NodeInfo> round3 = client.FIND_NODE(info.getNid());
                                count++;
                                break;
                            }
                        }
                    }
                }
            }
        }
        
        logger.info("ended lookup");
    }

    class Key {
        byte[] key;

        Key() {
            this.key = new byte[nsize];
            //MAX_KEY();
        }
        Key(byte[] k) {
            this.key = k;
        }
        void MAX_KEY(){
            for (int i=0; i<nsize; i++)
                this.key[i] = (byte) 0xff;
            return;
        }
        Key xor(Key k){
            // assume that the keys has same size
            Key result = new Key();
            for (int i=0; i<nsize; i++)
                result.key[i] = (byte) (this.key[i] ^ k.key[i]);
            return result;    
        }

        int compareTo(Key k) {
            for (int i=0; i<nsize; i++) {
                if (this.key[i] < k.key[i])
                    return -1;
                else if (this.key[i] > k.key[i])
                    return 1;
            }
            return 0;
        }
        String kToString(){
            return new String(this.key);
        }
        BigInteger kToBigInt(){
            return new BigInteger(this.key);
        }
    }

    class P2PClient {
        private final Logger logger = Logger.getLogger(P2PClient.class.getName());

        private final P2PGrpc.P2PBlockingStub blockingStub;
        public String name; 
        //private final Public_ledgerGrpc.Public_ledgerStub asyncStub;

        private Channel channel;

        //private Random random = new Random();
        //private TestHelper testHelper;

        /** Construct client for accessing Public_ledger server using the existing channel. */
        P2PClient(Channel channelBuilder) {
            this.channel = channelBuilder;
            blockingStub = P2PGrpc.newBlockingStub(channelBuilder);
            name="name";
            // asyncStub = Public_ledgerGrpc.newStub(channelBuilder);
        }

        public void PING() {
        }

        public void JOIN(byte[] nodeID) {
        }

        public Iterator<NodeInfo> FIND_NODE(NodeID request) throws java.lang.InterruptedException{
            //logger.info("FINDNODE " + request.getNodeID() + " from client " + request.getClientName());
            Iterator<NodeInfo> response;
            try {
                response = blockingStub.fINDNODE(request);
                logger.info("FINDNODE return statement");
                return response;
            } catch (StatusRuntimeException e) {
                logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            }/* finally {
                ((ManagedChannel)channel).shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
            }*/
            return Collections.emptyIterator();
        }
    }

    class TestUnit_ {
        public void printallnodes() {
            for (KBucket kb: kbuckets) {
                for(Node node: kb.nodes)
                    logger.info(kb.plength + " : " +kb.prefix.kToBigInt() + " : " + node.nodeID.kToBigInt());
            }
        }
    }
    class P2PServer {
        private final Logger logger = Logger.getLogger(P2PServer.class.getName());

        private final int port;
        private final Server server;
        
        public P2PServer(int port) throws IOException {
            this.port = port;
            server = ServerBuilder.forPort(port).addService(new P2PService())
                .build();
        }

        /** Start serving requests. */
        public void start() throws IOException {
            server.start();
            logger.info("Server started, listening on " + port);
            Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                try {
                P2PServer.this.stop();
                } catch (InterruptedException e) {
                e.printStackTrace(System.err);
                }
                System.err.println("*** server shut down");
            }
            });
        }

        /** Stop serving requests and shutdown resources. */
        public void stop() throws InterruptedException {
            if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
            }
        }

        /**
        * Await termination on the main thread since the grpc library uses daemon threads.
        */
        private void blockUntilShutdown() throws InterruptedException {
            if (server != null) {
            server.awaitTermination();
            }
        }

        public void run() throws java.io.IOException, java.lang.InterruptedException {
            start();
            blockUntilShutdown();
        }
        
        
        private class P2PService extends P2PGrpc.P2PImplBase {

            @Override
            public void pING(NodeID request, StreamObserver<BooleanSuccessResponse> responseObserver) {
            
                responseObserver.onNext(BooleanSuccessResponse.newBuilder().setSuccess(true).build());
                System.out.println(request.getClientName() + " has connected");
                responseObserver.onCompleted();
            }

            @Override
            public void fINDNODE(NodeID request, StreamObserver<NodeInfo> responseObserver) {
                // FIND_NODE takes a 160-bit ID as na argument. The recipient of a the RPC returns <IP address, UDP port, Node ID>
                // triples for the k nodes it knows about closest to the target ID. These triples can come from a single k-bucket,
                // or they may come from multiple k-buckets if the closest kbucket is not full.
                logger.info(request.getClientName() + " has connected");

                Key key = new Key(request.getNodeID().toByteArray());
                int ik = 0;

                Collections.sort(kbuckets, new Comparator<KBucket>() {
                    @Override
                    public int compare(KBucket k1, KBucket k2) {
                        return k1.prefix.xor(key).compareTo(k2.prefix.xor(key));
                    }
                });
                logger.info("kbuckets size:" + kbuckets.size());
                // find closest non-empty k-bucket
                for (int i=0; i<kbuckets.size(); i++) {
                    logger.info("iterate for buckets");
                    KBucket ikbucket = kbuckets.get(i);
                    for (Node inode: ikbucket.nodes) {
                        logger.info("sending nodeID: " + inode.nodeID.kToBigInt());
                        responseObserver.onNext(inode.toNodeInfo().build());
                        if (++ik == k)
                            break;
                    }
                    if (ik == k)
                        break;
                }
                responseObserver.onCompleted();
                logger.info("id:" + current.nodeID.kToString() + "has completed find node for " + request.getClientName() + " has connected");
            }
        }
    }

    public boolean inserts(Node inode) {
        logger.info("JOIN init");
        int nbuckets = kbuckets.size();
        if (nbuckets == 0) {
            logger.info("Created first kbucket");
            inode.kbucket = new KBucket(new Key(), 0);
            kbuckets.add(inode.kbucket);
            inode.kbucket.nodes.add(inode);
        } else {
            for (int j=0; j<kbuckets.size(); j++) {
                KBucket ikbucket = kbuckets.get(j);
                logger.info("kbuctet prefix: " + ikbucket.prefix.kToBigInt());
                logger.info("node id: " + inode.nodeID.kToBigInt());
                if (ikbucket.inRange(inode)) {
                    logger.info("Same range.");
                    // verifica se o node já existe no bucket
                    if ((Node jnode = ikbucket.getNodeByID(inode.nodeID)) != null) {
                        ikbucket.nodes.remove(jnode);
                        ikbucket.nodes.add(inode);   
                        inode.kbucket = ikbucket;                     
                    } // verifica se o node não está full 
                    else if (ikbucket.nodes.size() < k.intValue()) {
                        logger.info("Added");
                        inode.kbucket = ikbucket;
                        ikbucket.nodes.add(inode);
                        break;
                    } // se está full
                    else if (ikbucket.nodes.size() == k.intValue()){
                        // mas está no range do init adreess e temos menos que 160 kbucktes - split
                        if (ikbucket.inRange(this.iaddress) && nbuckets<160) {
                            inode.kbucket = ikbucket;
                            ikbucket.nodes.add(inode);
                            logger.info("Split");
                            split(ikbucket);
                            break;
                        } else if (!ikbucket.inRange(this.iaddress)) {
                            logger.info("Node outofrange init address");
                            break;
                        } else if (k.intValue().compareTo(maxk) < 0) {
                            logger.info("k++");
                            k = k.add(new BigInteger("1"));
                            break;
                        } else if (k.intValue().compareTo(maxk) == 0) {
                            // if the appropriate k-bucket is full, however then the recipient pings de k-buckets least-recently seen node to decide what to do
                            Node jnode = ikbucket.get(0);
                            NodeID info = jnode.toNodeInfo();
                            this.client = new P2PClient(ManagedChannelBuilder.forTarget(info.getIp() + ":" + inode.getPort()).usePlaintext().build());
                            BooleanSuccessResponse response = this.client.PING(info.getNid());
                            if (response.getSuccess()) {
                                logger.info("Ping success, add to tail");
                                ikbucket.remove(jnode);
                                ikbucket.add(jnode);
                            } else {
                                logger.info("Ping not success, inode added to tail");
                                ikbucket.remove(jnode);
                                ikbucket.add(inode);
                                inode.kbucket = ikbucket; 
                            }
                            break;
                        } 
                } else {
                    logger.info("Node out od range");
                }
            }
        }
        return true;
    }

    public void split(KBucket i) {
        kbuckets.add(new KBucket(i.addAndGet0ToPrefix(), i.plength+1));
        kbuckets.add(new KBucket(i.addAndGet1ToPrefix(), i.plength+1));
        kbuckets.remove(i);
        for (Node inode : i.nodes) {
            inode.kbucket = null;
            inserts(inode);
        }
    }

    class Master_node extends Node {
        private final Logger logger = Logger.getLogger(Master_node.class.getName());

        Master_node() {}
        Master_node(int p) { super(p); }
    }

    class Node {
        private final Logger logger = Logger.getLogger(Node.class.getName());
        public Key nodeID = new Key();
        public String ip = "127.0.0.1";
        public int port;
        public KBucket kbucket = null;

        Node() {
            this.port = get_Port();
            writeFile(String.valueOf(this.port));
            generateRandom160bits(this.nodeID);
        }
        Node(int p) {
            this.port = p;
            generateRandom160bits(this.nodeID);
        }

        // messages parse
        public NodeID.Builder toNodeID(){
            return NodeID.newBuilder().setNodeID(ByteString.copyFrom(this.nodeID.key)).setClientName("id: " + current.nodeID.kToString());
        }
        public NodeInfo.Builder toNodeInfo() {
            return NodeInfo.newBuilder().setNid(NodeID.newBuilder()
                                            .setNodeID(ByteString.copyFrom(this.nodeID.key))
                                            .setClientName("id: " + current.nodeID.kToString()))
                                            .setIp(this.ip)
                                            .setPort(this.port);
        }

        private ArrayList<String> readFile() {
            ArrayList<String> content = new ArrayList<String>();
            try {
                File myObj = new File("ports_used.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    content.add(data);
                    // System.out.println(data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            }
            return content;
        }

        private void writeFile(String text) {
            try {
            FileWriter myWriter = new FileWriter("ports_used.txt", true);
            myWriter.write(text + "\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            }
        }

        private int get_Port() {
            ArrayList<String> file_content = new ArrayList<String>();
            file_content = readFile();
            int port;
            if(file_content.size()==0){
                port=50052;
            }
            else{
                port = Integer.parseInt(file_content.get(file_content.size() - 1)) + 1;
            }
            return port;
        }

    }

    class KBucket {
        private final Logger logger = Logger.getLogger(KBucket.class.getName());
        public Key prefix;
        public int plength;
        public ArrayList<Node> nodes = new ArrayList<Node>();

        KBucket(Key prefix, int plength) {
            this.prefix = prefix;
            this.plength = plength;
        }

        public Node getNodeByID(Key ikey) {
            this.nodes.forEach((node) -> if(node.nodeID.compareTo(ikey) == 0){return node});
            return null;
        }

        public Key addAndGet0ToPrefix() {
            Key r = new Key();
            if (plength == 0)
                return r;
            
            for (int i=0; i<this.plength; i++)
                r.key[i] = prefix.key[i];
            // last bit of last byte
            int position = this.plength % 8;
            int index = this.plength / 8;
            r.key[index] = (byte) (r.key[index] | ~(1 << (8-position)));
            return r;
        }

        public Key addAndGet1ToPrefix() {
            Key r = new Key();
            logger.info("plength: " + plength);
            if (plength == 0) {
                r.key[0] = (byte) 0x80;
                logger.info("add 1 to prefix: " + r.kToBigInt());
                return r;
            }
            
            for (int i=0; i<this.plength; i++)
                r.key[i] = prefix.key[i];
            // last bit of last byte
            int position = this.plength % 8;
            int index = this.plength / 8;
            r.key[index] = (byte) (r.key[index] | (1 << (8-position)));
            return r;
        }

        public boolean inRange(Node inode){
            int i = 1, index, position;

            while (i <= this.plength) {
                position = i % 8;
                index = i / 8;

                if (((inode.nodeID.key[index] >> (8 - position)) & 1) == ((this.prefix.key[index] >> (8 - position)) & 1))
                    i++;
                else
                    return false;
            }
            return true;
        }
        public boolean inRange(Key iid){
            int i = 1, index, position;
            logger.info("idd size: " + iid.key.length + " prefix size: " + this.prefix.key.length);
            while (i <= this.plength) {
                position = i % 8;
                index = i / 8;

                if (((iid.key[index] >> (8 - position)) & 1) == ((this.prefix.key[index] >> (8 - position)) & 1))
                    i++;
                else
                    return false;
            }
            return true;
        }
    }

    void generateRandom160bits(Key k) {
        // generate 20 bytes ou 160 bits random 
        Random rd = new Random();
        rd.nextBytes(k.key);
    }

    BigInteger getMaxK() {
        byte[] bytes = new byte[msize];
        bytes[0] = (byte) 0xf0;
        return new BigInteger(bytes).subbtract(new BigInteger("1"));

    }

}

