
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import com.google.protobuf.Message;
import io.grpc.Channel;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import java.math.BigInteger;

public class Kademlia {
    private static final Logger logger = Logger.getLogger(Kademlia.class.getName());

    private Binary_tree tree = null;

    public void launch() {
        logger.info("launch init");
        // cmdlistener
        Thread cmdlistener = new Thread(new Runnable() { 
            @Override
            public void run()
            { 
                try { 
                    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                    String line = "";

                    while (line.equalsIgnoreCase("quit") == false) {
                        line = in.readLine();

                        String[] commands = line.split(" ");

                        if (commands[0].equals("createhardcodednode")) {
                            logger.info("Selected createhardcodednode");
                            tree = new Binary_tree(50051);
                            Thread tserver = new Thread(new Runnable() { 
                                @Override
                                public void run()
                                { 
                                    try {
                                        tree.server.run();
                                    } catch (Exception e) {
                                        e.printStackTrace(); 
                                    }
                                } 
                            });
                            tserver.start();
                            // join
                            tree.inserts(tree.current);              
                            logger.info("Created master node");
                            tree.chain.printChain();
                                
                        } else if (commands[0].equals("createminer")) {
                            logger.info("Selected creatminer");
                            tree = new Binary_tree();
                            Thread tserver = new Thread(new Runnable() { 
                                @Override
                                public void run()
                                { 
                                    try {
                                        tree.server.run();
                                    } catch (Exception e) {
                                        e.printStackTrace(); 
                                    }
                                } 
                            });
                            tserver.start();
                            // join
                            tree.inserts(tree.current);
                            logger.info("Created node");
                            // este nó conhece o hardcoded 127.0.0.1:50051
                            Binary_tree.Master_node master = tree.new Master_node(50051);
                            tree.inserts(master);
                            logger.info("Created master node");
                            tree.lookup(tree.current.nodeID);
                            
                            //tree.chain.printChain();
                            //tree.getBlockChain(master);

                            /*
                            tree.testUnit.findValue(master);
                            tree.store(master.nodeID, master.nodeID, "Valor Y");
                            */
                            // lookup meu own id
                            // Finnaly, u refreshes all k-buckets further away than its closest neighbor.
                            // During the refreshes, u both populates its own k-buckets and insets itsekf 
                            // into other nodes k-buckets as necessary.
                        }  else if (commands[0].equals("testinsertNodes")) {
                            tree = new Binary_tree(50051);
                            tree.inserts(tree.current);
                            tree.testUnit.insertNodes();
                            tree.testUnit.printallnodes();
                        }  else if (commands[0].equals("createuser")) {
                            tree = new Binary_tree(commands[1]);
                            Thread tserver = new Thread(new Runnable() { 
                                @Override
                                public void run()
                                { 
                                    try {
                                        tree.server.run();
                                    } catch (Exception e) {
                                        e.printStackTrace(); 
                                    }
                                } 
                            });
                            tserver.start();
                            // join
                            tree.inserts(tree.current);
                            logger.info("Created node");
                            // este nó conhece o hardcoded 127.0.0.1:50051
                            Binary_tree.Master_node master = tree.new Master_node(50051);
                            tree.inserts(master);
                            logger.info("Created master node");
                            tree.lookup(tree.current.nodeID);

                            tree.chain.printChain();
                            tree.updateBlockchain(master);
                            //*** falta pedir a transação e enviar para os miners
                        } 
                    }

                    in.close();
                } catch (Exception e) { 
                    e.printStackTrace(); 
                }
            } 
        });
        cmdlistener.start();
    }

    public static void main(String[] args) {

        Kademlia pl = new Kademlia();
        pl.launch();

    }

}