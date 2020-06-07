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
    int nsize = 20;
    BigInteger maxk = getMaxK();
    int alpha = 3;
    TestUnit_ testUnit = new TestUnit_();
    BlockChain chain = null;
    Boolean isMiner =false;

    Binary_tree() throws java.io.IOException {
        this.chain = new BlockChain();
        this.current = new Node();
        this.server = new P2PServer(this.current.port);
        iaddress = new Key();
        generateRandom160bits(iaddress);
        this.isMiner=true;
    }

    Binary_tree(String nome) throws java.io.IOException {
        this.chain = new BlockChain();
        this.current = new Node(nome);
        this.server = new P2PServer(this.current.port);
        iaddress = new Key();
        generateRandom160bits(iaddress);
    }

    Binary_tree(int p) throws java.io.IOException {
        this.chain = new BlockChain();
        this.current = new Master_node(p);
        this.server = new P2PServer(p);
        iaddress = new Key();
        generateRandom160bits(iaddress);
        this.chain.addBlock(new Block("Genesis Block", "100 coins"));
    }

    public List<Node> lookup(Key key) throws InterruptedException {
        //////logger.info("started lookup");
        List<Key> listenKeys = new ArrayList<Key>();
        List<Node> closest = new ArrayList<Node>();
        List<Node> father = new ArrayList<Node>();

        Key idistance = new Key();
        idistance.MAX_KEY();
        KBucket ikbucket = null;
        
        // find closest non-empty k-bucket
       // System.out.println("Size: " + kbuckets.size());
        for (int i=0; i<kbuckets.size(); i++) {
            KBucket tmpkbucket = kbuckets.get(i);
            Key tmpdistance = tmpkbucket.prefix.xor(key);
          //  System.out.println("idistance: " + idistance.kToBigInt());
            //System.out.println("bucket prefix: " + tmpkbucket.prefix.kToBigInt());
            //System.out.println("key: " + key.kToBigInt());
            //System.out.println("tmpdistance: " + tmpdistance.kToBigInt());
            //System.out.println("tmpdistance.compareTo(idistance) " + tmpdistance.compareTo(idistance));
            //System.out.println("Número de nós: " + tmpkbucket.nodes.size());

            if (tmpdistance.compareTo(idistance) <= 0 && tmpkbucket.nodes.size() != 0) {
                idistance = tmpdistance;
                ikbucket = tmpkbucket;
            }
        }
        


        // Send Find Node to alpha(=3) closest nodes
        // sorte list
        if (ikbucket != null) {
            //System.out.println("lookup kbucket size: " + ikbucket.nodes.size());
            Collections.sort(ikbucket.nodes, new Comparator<Node>() {
                @Override
                public int compare(Node n1, Node n2) {
                    return n1.nodeID.xor(key).compareTo(n2.nodeID.xor(key));
                }
            });

            int sublsize = (ikbucket.nodes.size() < alpha) ? ikbucket.nodes.size(): alpha;
            father.addAll(ikbucket.nodes.subList(0, sublsize));
            closest.addAll(ikbucket.nodes.subList(0, sublsize));
            //System.out.println("send alpha FIND NODES!!! " + alpha );
            for (int i=0; i<sublsize; i++) {
                // send alpha FIND NODES
                Node inode = father.get(i);
                System.out.println("round1(" + i + ") id:" + inode.nodeID.kToBigInt() + " port: " + inode.port);
                client = new P2PClient(ManagedChannelBuilder.forTarget(inode.ip + ":" + inode.port).usePlaintext().build());
                Iterator<NodeInfo> round1 = client.FIND_NODE(inode.toNodeID(key).build());
                listenKeys.add(inode.nodeID);
                while ( round1 != null && round1.hasNext()) {
                    NodeInfo info = round1.next();
                    Node node = new Node(info.getNode());
                    inserts(node);
                    if (node.nodeID.compareTo(idistance) < 0) {
                        idistance = node.nodeID;
                        closest.add(node);
                        if ((!ikbucket.inRange(iaddress) && BigInteger.valueOf(Integer.valueOf(closest.size())).compareTo(ikbucket.k) == 0) || (ikbucket.inRange(iaddress) && closest.size() == 10))
                            return closest;
                    }
                    // round2 send findnode to k triples
                    System.out.println("round2(" + i + ") id:" + new BigInteger(1, node.nodeID.key) + " port: " + node.port);
                    client = new P2PClient(ManagedChannelBuilder.forTarget(node.ip + ":" + node.port).usePlaintext().build());
                    Iterator<NodeInfo> round2 = client.FIND_NODE(node.toNodeID(key).build());
                    listenKeys.add(node.nodeID);
                    int counter = 0;
                    while ( (round2 != null && round2.hasNext() && counter <= alpha)){
                        NodeInfo info2 = round2.next();
                        Node node2 = new Node(info2.getNode());
                        inserts(node2);
                        if (node2.nodeID.compareTo(idistance) < 0) {
                            idistance = node2.nodeID;
                            closest.add(node2);
                            if ((!ikbucket.inRange(iaddress) && BigInteger.valueOf(Integer.valueOf(closest.size())).compareTo(ikbucket.k) == 0) || (ikbucket.inRange(iaddress) && closest.size() == 10))
                                return closest;
                        }
                        for (int u=0; u<listenKeys.size(); u++){
                            Key ikey = listenKeys.get(u);
                            if (counter == alpha)
                                break;
                            if (node2.nodeID.compareTo(ikey) != 0) {
                                System.out.println("round3(" + i + ") id:" + new BigInteger(1, node2.nodeID.key) + " port: " + node2.port);
                                client = new P2PClient(ManagedChannelBuilder.forTarget(node2.ip + ":" + node2.port).usePlaintext().build());
                                Iterator<NodeInfo> round3 = client.FIND_NODE(node2.toNodeID(key).build());
                                counter++;
                                while ( round3 != null && round3.hasNext()) {
                                    NodeInfo info3 = round3.next();
                                    Node node3 = new Node(info3.getNode());
                                    inserts(node3);
                                    if (node3.nodeID.compareTo(idistance) < 0) {
                                        idistance = node3.nodeID;
                                        closest.add(node3);
                                        if ((!ikbucket.inRange(iaddress) && BigInteger.valueOf(Integer.valueOf(closest.size())).compareTo(ikbucket.k) == 0) || (ikbucket.inRange(iaddress) && closest.size() == 10))
                                            return closest;
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("null");
        }
        ////logger.info("ended lookup");
        return closest;
    }

    public List<Node> findvalue_lookup(Key key, Key v) throws InterruptedException {
        ////logger.info("started find value lookup");
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
        

        //////logger.info("lookup kbucket size: " + ikbucket.nodes.size());
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
            father.addAll(ikbucket.nodes.subList(0, sublsize));
            closest.addAll(ikbucket.nodes.subList(0, sublsize));
            //////logger.info("send alpha FIND NODES!!!");
            for (int i=0; i<sublsize; i++) {
                // send alpha FIND NODES
                Node inode = father.get(i);
                System.out.println("round1(" + i + ") id:" + inode.nodeID.kToBigInt() + " port: " + inode.port);
                client = new P2PClient(ManagedChannelBuilder.forTarget(inode.ip + ":" + inode.port).usePlaintext().build());
                Iterator<NodeInfo> round1 = client.FIND_VALUE(inode.toKey_Value(key, v, "").build());
                listenKeys.add(inode.nodeID);
                while ( round1 != null && round1.hasNext()) {
                    NodeInfo info = round1.next();
                    if (info.getValue() != null) {
                        storeIn(closest, key, v, info.getValue());
                    }
                    Node node = new Node(info.getNode());
                    inserts(node);
                    if (node.nodeID.compareTo(idistance) < 0) {
                        idistance = node.nodeID;
                        closest.add(node);
                        if ((!ikbucket.inRange(iaddress) && BigInteger.valueOf(Integer.valueOf(closest.size())).compareTo(ikbucket.k) == 0) || (ikbucket.inRange(iaddress) && closest.size() == 10))
                            return closest;
                    }
                    // round2 send findnode to k triples
                    System.out.println("round2(" + i + ") id:" + new BigInteger(1, node.nodeID.key) + " port: " + node.port);
                    client = new P2PClient(ManagedChannelBuilder.forTarget(node.ip + ":" + node.port).usePlaintext().build());
                    Iterator<NodeInfo> round2 = client.FIND_VALUE(node.toKey_Value(key, v, "").build());
                    listenKeys.add(node.nodeID);
                    int counter = 0;
                    while ( (round2 != null && round2.hasNext() && counter <= alpha)){
                        NodeInfo info2 = round2.next();
                        if (info2.getValue() != null) {
                            storeIn(closest, key, v, info2.getValue());
                        }
                        Node node2 = new Node(info2.getNode());
                        inserts(node2);
                        if (node2.nodeID.compareTo(idistance) < 0) {
                            idistance = node2.nodeID;
                            closest.add(node2);
                            if ((!ikbucket.inRange(iaddress) && BigInteger.valueOf(Integer.valueOf(closest.size())).compareTo(ikbucket.k) == 0) || (ikbucket.inRange(iaddress) && closest.size() == 10))
                                return closest;
                        }
                        for (int u=0; u<listenKeys.size(); u++){
                            Key ikey = listenKeys.get(u);
                            if (counter == alpha)
                                break;
                            if (node2.nodeID.compareTo(ikey) != 0) {
                                System.out.println("round3(" + i + ") id:" + new BigInteger(1, node2.nodeID.key) + " port: " + node2.port);
                                client = new P2PClient(ManagedChannelBuilder.forTarget(node2.ip + ":" + node2.port).usePlaintext().build());
                                Iterator<NodeInfo> round3 = client.FIND_VALUE(node2.toKey_Value(key, v, "").build());
                                counter++;
                                while ( round3 != null && round3.hasNext()) {
                                    NodeInfo info3 = round3.next();
                                    if (info3.getValue() != null) {
                                        storeIn(closest, key, v, info3.getValue());
                                    }
                                    Node node3 = new Node(info3.getNode());
                                    inserts(node3);
                                    if (node3.nodeID.compareTo(idistance) < 0) {
                                        idistance = node3.nodeID;
                                        closest.add(node3);
                                        if ((!ikbucket.inRange(iaddress) && BigInteger.valueOf(Integer.valueOf(closest.size())).compareTo(ikbucket.k) == 0) || (ikbucket.inRange(iaddress) && closest.size() == 10))
                                            return closest;
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
        ////logger.info("ended find value lookup");
        return closest;
    }

    public void storeIn(List<Node> inodes, Key k, Key kv, String value) throws InterruptedException{
        int i = 0;
        for (int j=0; j<inodes.size(); j++) {
            Node inode = inodes.get(j);
            BooleanSuccessResponse response = client.STORE(inode.toKey_Value(k, kv, value).build());
            //////logger.info("store in closest: " + i++ + " response: " + response.getSuccess());
        }
        //////logger.info("Store in closest completed");
        return ;
    }

    public void store(Key key, Key kv, String value) throws InterruptedException {
        List<Node> inodes = lookup(key);

        storeIn(inodes, key, kv, value);
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

        Key (BigInteger bi) {
            this.key = bi.toByteArray();
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

            return new BigInteger(1, this.key).compareTo(new BigInteger(1, k.key));
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

        public Iterator<NodeInfo> FIND_NODE(NodeID request) throws java.lang.InterruptedException{
            logger.info("FINDNODE " + new BigInteger(request.getNodeID().toByteArray()).toString() + " from sender " + new BigInteger(request.getSender().getNodeID().toByteArray()).toString());
            Iterator<NodeInfo> response;
            try {
                response = blockingStub.fINDNODE(request);
                //////logger.info("FINDNODE return statement");
                return response;
            } catch (StatusRuntimeException e) {
                logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            }/* finally {
                ((ManagedChannel)channel).shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
            }*/
            return Collections.emptyIterator();
        }

        public BooleanSuccessResponse STORE(Key_Value request) throws java.lang.InterruptedException{
            ////logger.info("STORE " + new BigInteger(request.getKey().toByteArray()).toString() + " from sender " + new BigInteger(request.getSender().getNodeID().toByteArray()).toString());
            BooleanSuccessResponse response =  null;
            try {
                response = blockingStub.sTORE(request);
                ////logger.info("STORE return statement");
                ////logger.info("Value: " + response.getSuccess());
                return response;
            } catch (StatusRuntimeException e) {
                logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            }/* finally {
                ((ManagedChannel)channel).shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
            }*/
            return response;
        }

        public Iterator<NodeInfo> FIND_VALUE(Key_Value request) throws java.lang.InterruptedException{
            ////logger.info("FINDVALUE " + new BigInteger(request.getKey().toByteArray()).toString() + " from sender " + new BigInteger(request.getSender().getNodeID().toByteArray()).toString());
            Iterator<NodeInfo> response;
            try {
                response = blockingStub.fINDVALUE(request);
                ////logger.info("FINDVALUE return statement");
                ////logger.info("Value: " + response.next().getValue());
                return response;
            } catch (StatusRuntimeException e) {
                logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            }/* finally {
                ((ManagedChannel)channel).shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
            }*/
            return Collections.emptyIterator();
        }

        public Iterator<Block_> GETBlockChain(NodeInfo request) throws java.lang.InterruptedException {
            //logger.info("GETBlockChain " + new BigInteger(request.getKey().toByteArray()).toString() + " from sender " + new BigInteger(request.getSender().getNodeID().toByteArray()).toString());
            Iterator<Block_> response;
            try {
                response = blockingStub.gETBlockChain(request);
                return response;
            } catch (StatusRuntimeException e) {
                logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            }
            return Collections.emptyIterator();
        }

        public BooleanSuccessResponse  SendTransaction(Transaction_ request) throws java.lang.InterruptedException {
            //////logger.info("FINDVALUE " + new BigInteger(request.getKey().toByteArray()).toString() + " from sender " + new BigInteger(request.getSender().getNodeID().toByteArray()).toString());
            BooleanSuccessResponse response = null;
            try {
                response = blockingStub.sendTransaction(request);
                
            } catch (StatusRuntimeException e) {
                logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            }
            return response;
        }
        public BooleanSuccessResponse SendBlock(Block_ request){
            BooleanSuccessResponse response = null;
            try {
                response = blockingStub.sendBlock(request);
                
            } catch (StatusRuntimeException e) {
                logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            }
            return response;
        }

        public BasicNode GetNodeFromName(NodeName request){
            BasicNode response = null;
            try {
                response = blockingStub.getNodeFromName(request);                
            } catch (StatusRuntimeException e) {
                logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            }
            return response;
        }
        
        public Iterator<BasicNode> GetMinersFromMaster(BasicNode request){
            Iterator<BasicNode> response = null;
            try {
                response = blockingStub.getMinersFromMaster(request);                
            } catch (StatusRuntimeException e) {
                logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            }
            return response;
        }

    }

    class TestUnit_ {
        public void printallnodes() {
            for (int i=0; i<kbuckets.size(); i++){
                KBucket kb = kbuckets.get(i);
                for( int j=0; j<kb.nodes.size(); j++){
                    Node node = kb.nodes.get(j);
                    logger.info(node.name + " : " + iaddress.kToBigInt() + " : " + kb.plength + " : " +kb.prefix.kToBigInt() + " : " + node.nodeID.kToBigInt());
                }
            }
        }

        public ArrayList<String> NodeExists(){
            ArrayList<String> nodeList= new ArrayList<String>();
            for (int i=0; i<kbuckets.size(); i++){
                KBucket kb = kbuckets.get(i);
                for( int j=0; j<kb.nodes.size(); j++){
                    Node node = kb.nodes.get(j);
                    nodeList.add(node.name);
                }
            }


            return nodeList;
        }

        public void insertNodes() throws InterruptedException, IOException {
            ////logger.info("Insert nodes test unit " + kbuckets.size());
            
            while (kbuckets.size() != 161){
                
            ////logger.info("Insert nodes test unit " + kbuckets.size());
            printallnodes();
                String s = "";
                while (s.equals("") && (kbuckets.size() == 160 || kbuckets.size() == 60 || kbuckets.size() == 120)) {
                    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                    s = bufferRead.readLine();
                }
                
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
            String s = "";
                while (s.equals("")) {
                    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                    s = bufferRead.readLine();
                }

            for ( int j=0; j<5; j++) {
                for (int i=0; i<kbuckets.size(); i++){
                    KBucket ikbucket = kbuckets.get(i);
                    Key key = ikbucket.addRandomSufix();
                    Node inode = new Node(123);
                    inode.nodeID = key;
                    inserts(inode);
                }
                printallnodes();
            }            
        }

        public void findValue(Node inode) throws InterruptedException {
            Key_Value kv = inode.toKey_Value(inode.nodeID, inode.nodeID, "123").build();
            client = new P2PClient(ManagedChannelBuilder.forTarget(inode.ip + ":" + inode.port).usePlaintext().build());
            client.FIND_VALUE(kv);

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
            public void sTORE(Key_Value request, StreamObserver<BooleanSuccessResponse> responseObserver) {
                // FIND_NODE takes a 160-bit ID as na argument. The recipient of a the RPC returns <IP address, UDP port, Node ID>
                // triples for the k nodes it knows about closest to the target ID. These triples can come from a single k-bucket,
                // or they may come from multiple k-buckets if the closest kbucket is not full.
                ////logger.info(new BigInteger(request.getSender().getNodeID().toByteArray()).toString() + " has connected");
                //////logger.info("kbuckets size:" + kbuckets.size());
                
                // when a kademlia node receives any message(request or reply) from another node,
                // it updates the appropeiate k-bucket for the sender´s nodeID
                Node snode = new Node(request.getSender());
                inserts(snode);

                String value = request.getValue();

                // write file
                //////logger.info("I receive this value to store: " + value);

                responseObserver.onNext(BooleanSuccessResponse.newBuilder().setSuccess(true).build());
                responseObserver.onCompleted();
                //////logger.info("id:" + current.nodeID.kToBigInt() + "has completed store for " + new BigInteger(request.getSender().getNodeID().toByteArray()).toString() + " has connected");
                ////logger.info("store -> server response completed");
            }


            @Override
            public void fINDVALUE(Key_Value request, StreamObserver<NodeInfo> responseObserver) {
                // FIND_NODE takes a 160-bit ID as na argument. The recipient of a the RPC returns <IP address, UDP port, Node ID>
                // triples for the k nodes it knows about closest to the target ID. These triples can come from a single k-bucket,
                // or they may come from multiple k-buckets if the closest kbucket is not full.
                ////logger.info(new BigInteger(request.getSender().getNodeID().toByteArray()).toString() + " has connected");
                //////logger.info("kbuckets size:" + kbuckets.size());
                
                // when a kademlia node receives any message(request or reply) from another node,
                // it updates the appropeiate k-bucket for the sender´s nodeID
                Node snode = new Node(request.getSender());
                inserts(snode);
                
                // prepare key to calculate closest nodes
                Key key = new Key(request.getKey().toByteArray());

                // the value is here?
                if (key.compareTo(current.nodeID) == 0){
                    // RETORNAR VALOR AQUI
                    responseObserver.onNext(NodeInfo.newBuilder().setValue("Valor X").build());
                    responseObserver.onCompleted();
                    ////logger.info("id:" + current.nodeID.kToBigInt() + "has completed find value for " + new BigInteger(request.getSender().getNodeID().toByteArray()).toString() + " has connected");
                    logger.info("find value -> server response completed");
                    return ;
                }

                // sort
                Collections.sort(kbuckets, new Comparator<KBucket>() {
                    @Override
                    public int compare(KBucket k1, KBucket k2) {
                        if (k1.inRange(iaddress))
                            return 1;
                        if (k2.inRange(iaddress))
                            return -1;
                        return k1.prefix.xor(key).compareTo(k2.prefix.xor(key));
                    }
                });

                // find closest non-empty k-bucket
                int ik = 0;
                // a documentação é muito subjetiva, fala em k closest nodes por isso vamos usar o k do kbuckt mais próximo, para definir o número de nós closest que devemos retornar
                BigInteger nkclosest = new BigInteger("0");
                for (int i=0; i<kbuckets.size(); i++) {
                    //System.out.println("iterate for buckets");
                    KBucket ikbucket = kbuckets.get(i);
                    if (i == 0) {
                        nkclosest = ikbucket.k;
                    }
                    for (int j=0; j<ikbucket.nodes.size(); j++){
                        Node inode = ikbucket.nodes.get(j);
                        System.out.println("sending nodeID: " + inode.nodeID.kToBigInt());
                        responseObserver.onNext(inode.toNodeInfo().build());
                        if (++ik == nkclosest.intValue())
                            break;
                    }
                    if (ik == nkclosest.intValue())
                        break;
                }
                responseObserver.onCompleted();
                //System.out.println("id:" + current.nodeID.kToBigInt() + "has completed find value for " + new BigInteger(request.getSender().getNodeID().toByteArray()).toString() + " has connected");
                logger.info("find value -> server response completed");
            }

            @Override
            public void fINDNODE(NodeID request, StreamObserver<NodeInfo> responseObserver) {
                // FIND_NODE takes a 160-bit ID as na argument. The recipient of a the RPC returns <IP address, UDP port, Node ID>
                // triples for the k nodes it knows about closest to the target ID. These triples can come from a single k-bucket,
                // or they may come from multiple k-buckets if the closest kbucket is not full.
                ////logger.info(new BigInteger(request.getSender().getNodeID().toByteArray()).toString() + " has connected");
                //////logger.info("kbuckets size:" + kbuckets.size());
                
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
                        if (k1.inRange(iaddress))
                            return 1;
                        if (k2.inRange(iaddress))
                            return -1;
                        return k1.prefix.xor(key).compareTo(k2.prefix.xor(key));
                    }
                });

                // find closest non-empty k-bucket
                int ik = 0;
                // a documentação é muito subjetiva, fala em k closest nodes por isso vamos usar o k do kbuckt mais próximo, para definir o número de nós closest que devemos retornar
                BigInteger nkclosest = new BigInteger("0");
                for (int i=0; i<kbuckets.size(); i++) {
                    //System.out.println("iterate for buckets");
                    KBucket ikbucket = kbuckets.get(i);
                    if (i == 0)
                        nkclosest = ikbucket.k;
                    
                    System.out.println("****** " + nkclosest.toString() );

                    for (int j=0; j<ikbucket.nodes.size(); j++){
                        Node inode = ikbucket.nodes.get(j);
                        System.out.println("sending nodeID: " + inode.nodeID.kToBigInt());
                        responseObserver.onNext(inode.toNodeInfo().build());
                        if (BigInteger.valueOf(Integer.valueOf(++ik)).compareTo(nkclosest) == 0)
                            break;

                    }

                    if (BigInteger.valueOf(Integer.valueOf(ik)).compareTo(nkclosest) == 0)
                        break;
                }
                responseObserver.onCompleted();
               // System.out.println("id:" + current.nodeID.kToBigInt() + "has completed find node for " + new BigInteger(request.getSender().getNodeID().toByteArray()).toString() + " has connected");
                logger.info("find node -> server response completed");
            }

            @Override
            public void gETBlockChain(NodeInfo request, StreamObserver<Block_> responseObserver) {
                //logger.info(new BigInteger("GetBlockChain: " + request.getSender().getNodeID().toByteArray()).toString() + " has connected");
                
                // when a kademlia node receives any message(request or reply) from another node,
                // it updates the appropeiate k-bucket for the sender´s nodeID
                Node snode = new Node(request.getSender());
                inserts(snode);

                for (int i=0; i<chain.blockchain.size(); i++){
                    Block iblock = chain.blockchain.get(i);
                     responseObserver.onNext(iblock.toBlock_(current).build());
                }

                responseObserver.onCompleted();
                System.out.println("id:" + current.nodeID.kToBigInt() + "has completed getblockChain for " + new BigInteger(request.getSender().getNodeID().toByteArray()).toString() + " has connected");
                logger.info("getblockChain -> server response completed");

            }

            @Override
            public void sendTransaction(Transaction_ request, StreamObserver<BooleanSuccessResponse> responseObserver){
                //logger.info(new BigInteger("GetBlockChain: " + request.getSender().getNodeID().toByteArray()).toString() + " has connected");
                
                // when a kademlia node receives any message(request or reply) from another node,
                // it updates the appropeiate k-bucket for the sender´s nodeID
                if (request.getSenderNode() != null) {
                    Node snode = new Node(request.getSenderNode());
                    inserts(snode);
                }
                testUnit.printallnodes();

                Transaction transaction = Transaction.copyFrom(request);
                if(!transaction.processTransaction()){
                    responseObserver.onNext(BooleanSuccessResponse.newBuilder().setSuccess(false).build());
                    responseObserver.onCompleted();
                    return;
                } 

                String block_data= "Transaction "+transaction.transactionId;
                Block lblock = chain.blockchain.get(chain.blockchain.size()-1);


                //Block target = new Block(lblock.hash, "Bloco " + chain.blockchain.size());
                Block target = new Block(lblock.hash, block_data);
                
                target.addTransaction(transaction);
                target.mineBlock(chain.difficulty);

                // send block for all users
                for (int i=0; i<kbuckets.size(); i++){
                    KBucket ikbucket = kbuckets.get(i);

                    for (int j=0; j<ikbucket.nodes.size(); j++){
                        Node inode = ikbucket.nodes.get(j);
                        client = new P2PClient(ManagedChannelBuilder.forTarget(inode.ip + ":" + inode.port).usePlaintext().build());
                        System.out.println("Send block to " + inode.name);
                        client.SendBlock(target.toBlock_(current).build());
                    }
                }
                responseObserver.onNext(BooleanSuccessResponse.newBuilder().setSuccess(true).build());
                responseObserver.onCompleted();
                ////logger.info("sendTransation -> server response completed");

            }

            @Override
            public void sendBlock(Block_ request, StreamObserver<BooleanSuccessResponse> responseObserver){
                //////logger.info(new BigInteger("GetBlockChain: " + request.getSender().getNodeID().toByteArray()).toString() + " has connected");
                
                // when a kademlia node receives any message(request or reply) from another node,
                // it updates the appropeiate k-bucket for the sender´s nodeID
                /*if (request.getSenderNode() != null) {
                    Node snode = new Node(request.getSenderNode());
                    inserts(snode);
                }*/

                Block newblock = Block.copyFrom(request);
                Block lblock = chain.blockchain.get(chain.blockchain.size()-1);
                
                if (!lblock.hash.equals(newblock.previousHash)){
                    responseObserver.onNext(BooleanSuccessResponse.newBuilder().setSuccess(false).build());
                    responseObserver.onCompleted();
                    return;
                }
                
                chain.blockchain.add(newblock);
                responseObserver.onNext(BooleanSuccessResponse.newBuilder().setSuccess(true).build());
                responseObserver.onCompleted();
                logger.info("sendBlock -> server response completed");
                chain.printChain();

                return;

            }

            @Override
            public void getNodeFromName(NodeName request, StreamObserver<BasicNode> responseObserver){
                //////logger.info(new BigInteger("GetBlockChain: " + request.getSender().getNodeID().toByteArray()).toString() + " has connected");
                
                // when a kademlia node receives any message(request or reply) from another node,
                // it updates the appropeiate k-bucket for the sender´s nodeID
                if (request.getSender() != null) {
                    Node snode = new Node(request.getSender());
                    inserts(snode);
                }

                String recipient = request.getName();

                for (int i=0; i<kbuckets.size(); i++) {
                    KBucket ikbucket = kbuckets.get(i);

                    for (int j=0; j<ikbucket.nodes.size(); j++) {
                        Node inode = ikbucket.nodes.get(j);
                        if (inode.name.equals(recipient)) {
                            //System.out.println("Finded node name");
                            responseObserver.onNext(inode.toBasicNode().build());
                            responseObserver.onCompleted();
                            return;
                        }
                    }
                }

                responseObserver.onCompleted();
                ////logger.info("getNodeFromName -> server response completed");
                return;

            }

            @Override
            public void getMinersFromMaster(BasicNode request, StreamObserver<BasicNode> responseObserver){
                //////logger.info(new BigInteger("GetBlockChain: " + request.getSender().getNodeID().toByteArray()).toString() + " has connected");
                
                for (int i=0; i<kbuckets.size(); i++) {
                    KBucket ikbucket = kbuckets.get(i);

                    for (int j=0; j<ikbucket.nodes.size(); j++) {
                        Node inode = ikbucket.nodes.get(j);
                        if (inode.isMiner) {
                            System.out.println("Finded node miner");
                            responseObserver.onNext(inode.toBasicNode().build());
                        }
                    }
                }

                responseObserver.onCompleted();
                logger.info("GetMinersFromMaster -> server response completed");
                return;

            }
            

        }
    }

    // Nota o bucket tem tem em seu range o own address tem sempre k=1 para forçar o split
    public boolean inserts(Node inode) {
        ////logger.info("JOIN init, inser node, name: " + inode.name);
        int nbuckets = kbuckets.size();
        if (nbuckets == 0) {
            //System.out.println("Created first kbucket");
            inode.kbucket = new KBucket(new Key(), 0);
            kbuckets.add(inode.kbucket);
            inode.kbucket.nodes.add(inode);
        } else {
            for (int j=0; j<kbuckets.size(); j++) {
                KBucket ikbucket = kbuckets.get(j);
                //System.out.println("kbuctet prefix: " + ikbucket.prefix.kToBigInt());
                //System.out.println("node id: " + inode.nodeID.kToBigInt());
                //System.out.println("kbucket size: " + ikbucket.nodes.size() + " k: " + ikbucket.k.toString());
                if (ikbucket.inRange(inode)) {
                    //System.out.println("Same range.");
                    // verifica se o node já existe no bucket
                    Node jnode = null;
                    if ((jnode = ikbucket.getNodeByID(inode.nodeID)) != null) {
                        //System.out.println("NodeID already exists in kbucket");
                        ikbucket.nodes.remove(jnode);
                        ikbucket.nodes.add(inode);   
                        inode.kbucket = ikbucket;                     
                    } // verifica se o node não está full 
                    else if (BigInteger.valueOf(Integer.valueOf(ikbucket.nodes.size())).compareTo(ikbucket.k) == -1) {
                        //System.out.println("Added");
                        inode.kbucket = ikbucket;
                        ikbucket.nodes.add(inode);
                        break;
                    } // se está full
                    else if (BigInteger.valueOf(Integer.valueOf(ikbucket.nodes.size())).compareTo(ikbucket.k) == 0 && ikbucket.k.compareTo(new BigInteger("0")) != 0){
                        // mas está no range do init adreess e temos menos que 160 kbucktes - split
                        if (ikbucket.inRange(this.iaddress)) {
                            inode.kbucket = ikbucket;
                            ikbucket.nodes.add(inode);
                            //System.out.println("Split");
                            split(ikbucket);
                            break;
                        } else if (!ikbucket.inRange(this.iaddress)) {
                            // if the appropriate k-bucket is full, however then the recipient pings de k-buckets least-recently seen node to decide what to do
                            jnode = ikbucket.nodes.get(0);
                            NodeID infoID = jnode.toNodeID().build();
                            this.client = new P2PClient(ManagedChannelBuilder.forTarget(jnode.ip + ":" + jnode.port).usePlaintext().build());
                            BooleanSuccessResponse response = this.client.PING(infoID);
                            if (response.getSuccess()) {
                                //System.out.println("Ping success, add to tail");
                                ikbucket.nodes.remove(jnode);
                                ikbucket.nodes.add(jnode);
                            } else {
                                //System.out.println("Ping not success, inode added to tail");
                                ikbucket.nodes.remove(jnode);
                                ikbucket.nodes.add(inode);
                                inode.kbucket = ikbucket; 
                            }
                            break;
                        }
                    }
                } else {
                    ////logger.info("Node out of range");
                }
            }
        }
        return true;
    }

    public void split(KBucket i) {
        kbuckets.add(new KBucket(i.addAndGet0ToPrefix(), i.plength+1));
        kbuckets.add(new KBucket(i.addAndGet1ToPrefix(), i.plength+1));
        kbuckets.remove(i);
        for (int j=0; j<i.nodes.size(); j++){
            Node inode = i.nodes.get(j);
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
        public String name;
        public String publicKey;
        public boolean isMiner;


        Node() {
            this.port = get_Port();
            writeFile(String.valueOf(this.port));
            generateRandom160bits(this.nodeID);
            this.isMiner = true;
            this.name = "";
        }
        Node(String name) {
            this.port = get_Port();
            this.name = name;
            writeFile(String.valueOf(this.port));
            generateRandom160bits(this.nodeID);
            this.publicKey = StringUtil.getStringFromKey(chain.wallet.publicKey);
            this.isMiner = false;
        }
        // construtor para o hardcoded node
        Node(int p) {
            this.port = p;
            this.nodeID = new Key(new BigInteger("579182793079556569232906595954800423677054759475"));
            this.isMiner = false;
            this.name = "";
        }
        Node (BasicNode node) {
            this.port = node.getPort();
            this.nodeID = new Key(node.getNodeID().toByteArray());
            this.ip = node.getIp();
            this.isMiner = node.getIsMiner();
            this.name = node.getName();
            
            this.publicKey = node.getPublickey();
        }

        // messages parse
        public BasicNode.Builder toBasicNode(){
            return BasicNode.newBuilder().setName((this.name == null) ? "": this.name).setIsMiner(this.isMiner).setPublickey((this.publicKey == null) ? "": this.publicKey).setNodeID(ByteString.copyFrom(this.nodeID.key)).setIp(this.ip).setPort(this.port).setClientName("id: " + this.nodeID.kToString());
        }

        public NodeID.Builder toNodeID(){
            return NodeID.newBuilder().setNodeID(ByteString.copyFrom(this.nodeID.key)).setSender(current.toBasicNode());
        }

        public NodeID.Builder toNodeID(Key key){
            return NodeID.newBuilder().setNodeID(ByteString.copyFrom(key.key)).setSender(current.toBasicNode());
        }

        // esta função apenas ser para a unidade de testes, não está completamente funcional, foi adaptada
        public Key_Value.Builder toKey_Value(Key id, Key kv, String v) {
            return Key_Value.newBuilder().setKey(ByteString.copyFrom(id.key)).setKv(ByteString.copyFrom(kv.key)).setValue(v).setSender(current.toBasicNode());
        }

        public NodeInfo.Builder toNodeInfo() {
            return NodeInfo.newBuilder().setNode(this.toBasicNode()).setSender(current.toBasicNode());
        }

        public NodeName.Builder toNodeName(String name){
            return NodeName.newBuilder().setName(name).setSender(current.toBasicNode());
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
            //////logger.info("kbucket constructor: ");
            this.prefix = prefix;
            this.plength = plength;
            byte[] bytes = new byte[nsize];
            int position = this.plength % 8;
            int index = this.plength / 8;

            // se chegamos ao ponto máximo do split
            if (this.plength == 160) {
                //////logger.info("não é possível entrar aqui neste momento");
                // se somos o initial address ou own address
                if (this.prefix.compareTo(iaddress) == 0) {
                    this.k = new BigInteger("0");
                } else {
                    this.k = new BigInteger("1");
                }
            } // se estamos no range do own address vamos ter k=1 para forçar o split
            else if (this.inRange(iaddress)){
                //////logger.info("inside range");
                this.k = new BigInteger("1");
                
            //////logger.info("Constructed k = " + this.k.intValue());
            } else if (!this.inRange(iaddress)){
                //////logger.info("outside range");
                for (int i=0; i <nsize; i++) {
                    if (i<index) {
                        //////logger.info("menor");
                        bytes[i] = (byte) 0x00;
                    } else if (i == index) {
                        //////logger.info("igual");
                        int exp = 8 - position;
                        int aux = (int)Math.pow(2, exp) - 1;
                        bytes[i] = (byte) aux;
                    }else {
                        //////logger.info("maior");
                        bytes[i] = (byte) 0xff;
                    }
                }
                this.k = new BigInteger(1, bytes).add(new BigInteger("1"));
            }
            //////logger.info("Constructed k = " + this.k.toString());
        }

        public Node getNodeByID(Key ikey) {
            for (int i=0; i<this.nodes.size(); i++) {
                Node inode = this.nodes.get(i);
                if(inode.nodeID.compareTo(ikey) == 0){
                    return inode;
                }
            }
            return null;
        }

        public Key addRandomSufix() {
            //////logger.info("before addRandomSufix: " + this.prefix.kToBigInt());
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
            //////logger.info("after addRandomSufix: " + r.kToBigInt());
            return r;
        }

        public Key addAndGet0ToPrefix() {
            //////logger.info("Entered add 0 to Prefix. Prefix: " + this.prefix.kToBigInt() + " plength: " + this.plength);
            Key r = new Key();
            
            int position = (this.plength % 8);
            int index = this.plength / 8;

            for (int i=0; i<=index; i++)
                r.key[i] = this.prefix.key[i];

            // last bit of last byte
            r.key[index] = (byte) (r.key[index] & ~(1 << (7-position)));
            //////logger.info("add 0 to prefix: " + r.kToBigInt());
            return r;
        }

        public Key addAndGet1ToPrefix() {
            //////logger.info("Entered add 1 to Prefix. Prefix: " + this.prefix.kToBigInt() + " plength: " + this.plength);
            Key r = new Key();
            
            int position = (this.plength % 8);
            int index = this.plength / 8;

            for (int i=0; i<=index; i++)
                r.key[i] = this.prefix.key[i];
            // last bit of last byte
            r.key[index] = (byte) (r.key[index] | (1 << (7-position)));
            //////logger.info("add 1 to prefix: " + r.kToBigInt());
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
            //////logger.info("iaddress: " + iid.kToBigInt() + " prefix: " + this.prefix.kToBigInt() + " prefix length: " + this.plength);
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

    /*
    BLOCKCHAIN
    */
    public List<Node> getMiners(){
        ArrayList<Node> miners = new ArrayList<Node>();
        for (int i=0; i<kbuckets.size(); i++) {
            KBucket ikbucket = kbuckets.get(i);

            for (int j=0; j<ikbucket.nodes.size(); j++) {
                Node inode = ikbucket.nodes.get(j);
                if (inode.isMiner)
                    miners.add(inode);
            }
        }
        return miners;
    }

    public void getMinersFromMaster(Node master){
        client = new P2PClient(ManagedChannelBuilder.forTarget("localhost:50051").usePlaintext().build());
        Iterator<BasicNode> miners = client.GetMinersFromMaster(master.toBasicNode().build());

     while( miners.hasNext()){
            inserts(new Node(miners.next()));
        }
        return;
    }

    public Node getNodeFromName(String name){
        for (int i=0; i<kbuckets.size(); i++) {
            KBucket ikbucket = kbuckets.get(i);

            for (int j=0; j<ikbucket.nodes.size(); j++) {
                Node inode = ikbucket.nodes.get(j);
                //System.out.println(inode.name + " é igual ao " + name + "?");
                if (inode.name.equals(name))
                    return inode;
            }
        }
        return null;
    }

    public void printAllUsers(){
        for (int i=0; i<kbuckets.size(); i++) {
            KBucket ikbucket = kbuckets.get(i);
            System.out.print("[");
            for (int j=0; j<ikbucket.nodes.size(); j++) {
                if (j != 0)
                    System.out.print(", ");
                Node inode = ikbucket.nodes.get(j);
                System.out.print(inode.name);
            }
            System.out.println("]");
        }
        return;
    }

    public void updateBlockchain(Node inode) throws InterruptedException {
        client = new P2PClient(ManagedChannelBuilder.forTarget(inode.ip + ":" + inode.port).usePlaintext().build());
        chain.blockchain = new ArrayList<Block>();
        System.out.println(client.GETBlockChain(inode.toNodeInfo().build()));
        chain.blockchain.addAll(Block.copyFrom(client.GETBlockChain(inode.toNodeInfo().build())));
        //chain.printChain();
    }

    public void sendTransaction(String recipient, int amount) throws java.security.NoSuchAlgorithmException, java.security.spec.InvalidKeySpecException, InterruptedException, java.security.NoSuchProviderException {
        Node inode = getNodeFromName(recipient);
        List<Node> closest;
        boolean exist = false;

        if (inode == null){
            // find node from name
            client = new P2PClient(ManagedChannelBuilder.forTarget("localhost:50051").usePlaintext().build());
            inode =  new Node(client.GetNodeFromName(current.toNodeName(recipient).build()));
            System.out.println("node name: " + inode.name);
        }


        Transaction transaction = new Transaction(chain.wallet.publicKey, StringUtil.getKeyFromString(inode.publicKey), amount, null);
        transaction.generateSignature(chain.wallet.privateKey);


        if (!transaction.processTransaction()) {
            System.out.println();
            System.out.println("Falha ao processar transaction");
            System.out.println();
            return;
        }

        Transaction_ transaction2 = Transaction.toTransaction_(transaction, null);
        Transaction transaction3 = Transaction.copyFrom(transaction2);

        if (!transaction3.processTransaction()) {
            System.out.println();
            System.out.println("Falha ao processar transaction 3");
            System.out.println();
            return;
        }

        List<Node> miners = getMiners();
        for (int j=0; j<miners.size(); j++){
            Node iminer = miners.get(j);
            client = new P2PClient(ManagedChannelBuilder.forTarget(iminer.ip + ":" + iminer.port).usePlaintext().build());
            // Send transaction to miners
            client.SendTransaction(Transaction.toTransaction_(transaction, current));
        }

        /*if (!exist) {
            System.out.println("we did not find the recipient " + recipient);
            System.out.println("lista de utilizadores conhecidos:");
            printAllUsers();
            return;
        }*/
        return;
    }
}
