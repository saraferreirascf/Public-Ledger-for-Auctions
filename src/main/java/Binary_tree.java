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
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Binary_tree {
    private final Logger logger = Logger.getLogger(Binary_tree.class.getName());

    ArrayList<KBucket> kbuckets = new ArrayList<KBucket>();
    Key iaddress;
    Node current = null;
    P2PServer server = null;
    P2PClient client = null;
    BigInteger k = new BigInteger("1");
    int nsize = 20;
    BigInteger maxk = getMaxK();
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
        List<Key> listenKeys = new ArrayList<Key>();
        List<Node> closest = new ArrayList<Node>();
        List<Node> father = new ArrayList<Node>();
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
                client = new P2PClient(ManagedChannelBuilder.forTarget(inode.ip + ":" + inode.port).usePlaintext().build());
                Iterator<NodeInfo> round1 = client.FIND_NODE(inode.toNodeID().build());
                listenKeys.add(inode.nodeID);
                while ( round1 != null && round1.hasNext()) {
                    NodeInfo info = round1.next();
                    Node node = new Node(info.getNode());
                    if (node.nodeID.compareTo(idistance) < 0) {
                        idistance = node.nodeID;
                        closest.add(node);
                    }
                    // round2 send findnode to k triples
                    logger.info("round2(" + i + ") id:" + new BigInteger(1, node.nodeID.key) + " port: " + node.port);
                    client = new P2PClient(ManagedChannelBuilder.forTarget(node.ip + ":" + node.port).usePlaintext().build());
                    Iterator<NodeInfo> round2 = client.FIND_NODE(node.toNodeID().build());
                    listenKeys.add(node.nodeID);
                    int counter = 0;
                    while ( (round2 != null && round2.hasNext()) || counter == alpha){
                        NodeInfo info2 = round2.next();
                        Node node2 = new Node(info2.getNode());
                        if (node2.nodeID.compareTo(idistance) < 0) {
                            idistance = node2.nodeID;
                            closest.add(node2);
                        }
                        for (Key ikey: listenKeys){
                            if (counter == alpha)
                                break;
                            if (node2.nodeID.compareTo(ikey) != 0) {
                                logger.info("round3(" + i + ") id:" + new BigInteger(1, node2.nodeID.key) + " port: " + node2.port);
                                client = new P2PClient(ManagedChannelBuilder.forTarget(node2.ip + ":" + node2.port).usePlaintext().build());
                                Iterator<NodeInfo> round3 = client.FIND_NODE(node2.toNodeID().build());
                                counter++;
                                while ( round3 != null && round3.hasNext()) {
                                    NodeInfo info3 = round3.next();
                                    Node node3 = new Node(info3.getNode());
                                    if (node3.nodeID.compareTo(idistance) < 0) {
                                        idistance = node3.nodeID;
                                        closest.add(node3);
                                    }
                                }
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
            return new BigInteger(1, this.key);
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

        public BooleanSuccessResponse PING(NodeID request) {
            BooleanSuccessResponse response = null;
            try {
                response = blockingStub.pING(request);
                logger.info("PING return statement");
                return response;
            } catch (StatusRuntimeException e) {
                logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            }
            return null;
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

        public void insertNodes() throws InterruptedException, IOException {
            logger.info("Insert nodes test unit " + kbuckets.size());
            
            while (kbuckets.size() != 160){
                logger.info("ikbucket size: " + kbuckets.size() + " k: " + k.intValue());
                
                for (int i=0; i<kbuckets.size(); i++){
                    KBucket ikbucket = kbuckets.get(i);
                    if (ikbucket.inRange(iaddress)) {
                        Key key;
                        key = ikbucket.addRandomSufix();
                        Node inode = new Node(123);
                        inode.nodeID = key;
                        inserts(inode);
                    }
                }
            }
            printallnodes();
            String s = "";
            while (s.equals("")) {
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                s = bufferRead.readLine();
            }
            while (k.intValue() < 3) {
                logger.info("ikbucket size: " + kbuckets.size() + " k: " + k.intValue());
                for (int i=0; i<kbuckets.size(); i++){
                    KBucket ikbucket = kbuckets.get(i);
                    if (ikbucket.nodes.size() < k.intValue()) {
                        Key key;
                        key = ikbucket.addRandomSufix();
                        Node inode = new Node(123);
                        inode.nodeID = key;
                        inserts(inode);
                    }
                }
                
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
                logger.info(request.getSender().getClientName() + " has connected");

                // when a kademlia node receives any message(request or reply) from another node,
                // it updates the appropeiate k-bucket for the sender´s nodeID
                Node snode = new Node(request.getSender());
                inserts(snode);

                if (snode.nodeID.compareTo(current.nodeID) == 0)
                    responseObserver.onNext(BooleanSuccessResponse.newBuilder().setSuccess(true).build());
                else
                    responseObserver.onNext(BooleanSuccessResponse.newBuilder().setSuccess(false).build());
                responseObserver.onCompleted();
                logger.info("ping -> server response completed");
            }

            @Override
            public void fINDNODE(NodeID request, StreamObserver<NodeInfo> responseObserver) {
                // FIND_NODE takes a 160-bit ID as na argument. The recipient of a the RPC returns <IP address, UDP port, Node ID>
                // triples for the k nodes it knows about closest to the target ID. These triples can come from a single k-bucket,
                // or they may come from multiple k-buckets if the closest kbucket is not full.
                logger.info(request.getSender().getClientName() + " has connected");
                logger.info("kbuckets size:" + kbuckets.size());
                
                // when a kademlia node receives any message(request or reply) from another node,
                // it updates the appropeiate k-bucket for the sender´s nodeID
                Node snode = new Node(request.getSender());
                inserts(snode);
                
                // prepare key to calculate closest nodes
                Key key = new Key(request.getNodeID().toByteArray());

                // sort
                Collections.sort(kbuckets, new Comparator<KBucket>() {
                    @Override
                    public int compare(KBucket k1, KBucket k2) {
                        return k1.prefix.xor(key).compareTo(k2.prefix.xor(key));
                    }
                });

                // find closest non-empty k-bucket
                int ik = 0;
                for (int i=0; i<kbuckets.size(); i++) {
                    logger.info("iterate for buckets");
                    KBucket ikbucket = kbuckets.get(i);
                    for (Node inode: ikbucket.nodes) {
                        logger.info("sending nodeID: " + inode.nodeID.kToBigInt());
                        responseObserver.onNext(inode.toNodeInfo().build());
                        if (++ik == k.intValue())
                            break;
                    }
                    if (ik == k.intValue())
                        break;
                }
                responseObserver.onCompleted();
                logger.info("id:" + current.nodeID.kToString() + "has completed find node for " + request.getSender().getClientName() + " has connected");
                logger.info("find node -> server response completed");
            }
        }
    }

    // Nota o bucket tem tem em seu range o own address tem sempre k=1 para forçar o split
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
                    Node jnode = null;
                    if ((jnode = ikbucket.getNodeByID(inode.nodeID)) != null) {
                        ikbucket.nodes.remove(jnode);
                        ikbucket.nodes.add(inode);   
                        inode.kbucket = ikbucket;                     
                    } // verifica se o node não está full 
                    else if (ikbucket.nodes.size() < ikbucket.k.intValue()) {
                        logger.info("Added");
                        inode.kbucket = ikbucket;
                        ikbucket.nodes.add(inode);
                        break;
                    } // se está full
                    else if (ikbucket.nodes.size() == ikbucket.k.intValue() && ikbucket.k.intValue() != 0){
                        // mas está no range do init adreess e temos menos que 160 kbucktes - split
                        if (ikbucket.inRange(this.iaddress) && nbuckets<160) {
                            inode.kbucket = ikbucket;
                            ikbucket.nodes.add(inode);
                            logger.info("Split");
                            split(ikbucket);
                            break;
                        } else if (!ikbucket.inRange(this.iaddress)) {
                            // if the appropriate k-bucket is full, however then the recipient pings de k-buckets least-recently seen node to decide what to do
                            jnode = ikbucket.nodes.get(0);
                            NodeID infoID = jnode.toNodeID().build();
                            this.client = new P2PClient(ManagedChannelBuilder.forTarget(jnode.ip + ":" + jnode.port).usePlaintext().build());
                            BooleanSuccessResponse response = this.client.PING(infoID);
                            if (response.getSuccess()) {
                                logger.info("Ping success, add to tail");
                                ikbucket.nodes.remove(jnode);
                                ikbucket.nodes.add(jnode);
                            } else {
                                logger.info("Ping not success, inode added to tail");
                                ikbucket.nodes.remove(jnode);
                                ikbucket.nodes.add(inode);
                                inode.kbucket = ikbucket; 
                            }
                            break;
                        }
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
        Node (BasicNode node) {
            this.port = node.getPort();
            this.nodeID = new Key(node.getNodeID().toByteArray());
            this.ip = node.getIp();
        }

        // messages parse
        public BasicNode.Builder toBasicNode(){
            return BasicNode.newBuilder().setNodeID(ByteString.copyFrom(this.nodeID.key)).setIp(this.ip).setPort(this.port).setClientName("id: " + this.nodeID.kToString());
        }

        public NodeID.Builder toNodeID(){
            return NodeID.newBuilder().setNodeID(ByteString.copyFrom(this.nodeID.key)).setSender(current.toBasicNode());
        }

        public NodeInfo.Builder toNodeInfo() {
            return NodeInfo.newBuilder().setNode(this.toBasicNode()).setSender(current.toBasicNode());
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
        public BigInteger k = null;

        KBucket(Key prefix, int plength) {
            this.prefix = prefix;
            this.plength = plength;
            byte[] bytes = new byte[nsize];
            int position = this.plength % 8;
            int index = this.plength / 8;

            for (int i=0; i <nsize; i++) {
                if (i<index) {
                    bytes[i] = (byte) 0x00;
                } else if (i == index) {
                    int exp = 8 - position;
                    int aux = (int)Math.pow(2, exp);
                    bytes[i] = (byte) aux;
                }else {
                    bytes[i] = (byte) 0xff;
                }
            }

            // se chegamos ao ponto máximo do split
            if (this.plength == 160) {
                // se somos o initial address ou own address
                if (this.prefix.compareTo(iaddress) == 0) {
                    k = new BigInteger("0");
                } else {
                    k = new BigInteger("1");
                }
            } // se estamos no range do own address vamos ter k=1 para forçar o split
            else if (this.inRange(iaddress)){
                k = new BigInteger("1");
            } else if (!this.inRange(iaddress))
                k = new BigInteger(1, bytes);
        }

        public Node getNodeByID(Key ikey) {
            for (Node inode: this.nodes)
                if(inode.nodeID.compareTo(ikey) == 0){
                    return inode;
                }
            return null;
        }

        public Key addRandomSufix() {
            logger.info("before addRandomSufix: " + this.prefix.kToBigInt());
            Key r = new Key();
            
            int position = (this.plength % 8);
            int index = this.plength / 8;

            for (int i=0; i <nsize; i++) {
                if (i<index) {
                    r.key[i] = this.prefix.key[i];
                } else if (i == index) {
                    int exp = (8-position);
                    int min = 1;
                    int max = (int)Math.pow(2, exp) - 1;
                    int aux = (int)Math.round(((Math.random() * (max - min)) + min));
                    r.key[i] = (byte)((int) this.prefix.key[i] + aux);
                }else {
                    int aux = (int)(Math.random() * 255);
                    r.key[i] = (byte) aux;
                }
            }
            logger.info("after addRandomSufix: " + r.kToBigInt());
            return r;
        }

        public Key addAndGet0ToPrefix() {
            logger.info("Entered add 0 to Prefix. Prefix: " + this.prefix.kToBigInt() + " plength: " + this.plength);
            Key r = new Key();
            
            int position = (this.plength % 8);
            int index = this.plength / 8;

            for (int i=0; i<=index; i++)
                r.key[i] = this.prefix.key[i];

            // last bit of last byte
            r.key[index] = (byte) (r.key[index] & ~(1 << (7-position)));
            logger.info("add 0 to prefix: " + r.kToBigInt());
            return r;
        }

        public Key addAndGet1ToPrefix() {
            logger.info("Entered add 1 to Prefix. Prefix: " + this.prefix.kToBigInt() + " plength: " + this.plength);
            Key r = new Key();
            
            int position = (this.plength % 8);
            int index = this.plength / 8;

            for (int i=0; i<=index; i++)
                r.key[i] = this.prefix.key[i];
            // last bit of last byte
            r.key[index] = (byte) (r.key[index] | (1 << (7-position)));
            logger.info("add 1 to prefix: " + r.kToBigInt());
            return r;
        }

        public boolean inRange(Node inode){
            int i = 0, index, position;

            while (i < this.plength) {
                position = i % 8;
                index = i / 8;

                if (((inode.nodeID.key[index] >> (7 - position)) & 1) == ((this.prefix.key[index] >> (7 - position)) & 1))
                    i++;
                else
                    return false;
            }
            return true;
        }
        public boolean inRange(Key iid){
            int i = 0, index, position;
            logger.info("iaddress: " + iid.kToBigInt() + " prefix: " + this.prefix.kToBigInt() + " prefix length: " + this.plength);
            while (i < this.plength) {
                position = i % 8;
                index = i / 8;

                if (((iid.key[index] >> (7 - position)) & 1) == ((this.prefix.key[index] >> (7 - position)) & 1))
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
        byte[] bytes = new byte[nsize];
        bytes[0] = (byte) 0xf0;
        return new BigInteger(1, bytes).subtract(new BigInteger("1"));

    }

}

