//TODO
    //FEITO - Com o lookup aos user da rede, ver se o recipient da transacao existe, se nao, mostrar a lista dos que existem e dizer para escolher dai.
    //FEITO - Criar bloco com esta transaçao (podemos comecar por criar um bloco com esta informacao e nao ser logo uma transacao mesmo)
    //FEITO - Criar wallet para cada user que se junte.
    //FEITO - Lookup dos miners (têm na construcao da tree que o atributo isMiner é true)
    //FEITO - Mandar a esses nós o bloco
    //Feito - Fazer com que, quando eles recebam minem o bloco
    //Feito - Ver qual é o primeiro a minar (recebe dinheiro) e esse bloco é mandado a todos os users (incluindo ao master) para atualizarmos a blockchain
    // Ver melhor a cena dos transation outputs e inputs, em ultimo caso pomos dinheiro nas carteiras manualmente.
    // Resolver 
    // Sybils atacks
    // Proof of sake


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
import java.util.Scanner;  // Import the Scanner class

public class Kademlia {
    private static final Logger logger = Logger.getLogger(Kademlia.class.getName());
    Scanner scan = new Scanner(System.in);  // Create a Scanner object


    private Binary_tree tree = null;

    public void launch() {
        logger.info("launch init");
        // cmdlistener
        Thread cmdlistener = new Thread(new Runnable() { 
            @Override
            public void run()
            { 
                Menu_Inicial();
                try { 
                    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                    String line = "";

                    while (line.equalsIgnoreCase("quit") == false) {
                        line = in.readLine();

                        String[] commands = line.split(" ");

                        if (commands[0].equals("CreateHardCodedNode")) {
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
                                
                        } else if (commands[0].equals("CreateMiner")) {
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
                            System.out.println("Waiting to mine blocks....");
                            
                            //tree.chain.printChain();
                            tree.updateBlockchain(master);

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
                        }  else if (commands[0].equals("CreateUser")) {
                            tree = new Binary_tree(commands[1]);
                            System.out.println();
                            System.out.println("Welcome to the network "+commands[1]);
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
                            //logger.info("Created node");
                            // este nó conhece o hardcoded 127.0.0.1:50051
                            Binary_tree.Master_node master = tree.new Master_node(50051);
                            tree.inserts(master);
                            
                            tree.lookup(tree.current.nodeID);
                            //logger.info("Created master node");
                            //System.out.println("Tamanho closest: " + tree.lookup(tree.current.nodeID).size());

                           // tree.chain.printChain(); // ainda esta vazia pq nao pediu nada ao master
                            tree.updateBlockchain(master);
                            tree.getMinersFromMaster(master);

                            System.out.println("Waiting for transactions...");

                            Menu();
                            

                            
                        } else if (commands[0].equals("PrintAllNodes")) {
                            
                            tree.testUnit.printallnodes();
                        } else if (commands[0].equals("PrintAllChain")) {
                            tree.chain.printChain();
                        } else if (commands[0].equals("SendTransaction")){
                            System.out.println();
                            System.out.println("Please indicate the recipient of the transation");

                            
                            String recipient =scan.nextLine();
                            ArrayList<String> users=new ArrayList<String>();
                            users=tree.testUnit.NodeExists();
                            //System.out.println(Arrays.toString(users.toArray()));
                            while(!users.contains(recipient)){
                                System.out.println("Sorry :(");
                                System.out.println("You don't have a connection with that user in the network;");
                                users=tree.testUnit.NodeExists();
                                System.out.println();
                                System.out.println("Here you can see the list of available users:");
                                users.removeAll(Arrays.asList("", null));
                                System.out.println();
                                System.out.println(Arrays.toString(users.toArray()));
                                System.out.println();
                                System.out.println("Please indicate the recipient of the transation");
                                recipient =scan.nextLine();
                            }

                            System.out.println();
                            System.out.println("Please indicate the value of the transation");
                            int value =Integer.parseInt(scan.nextLine());
                            while(value > 100){
                                System.out.println();
                                System.out.println("Sorry :(");
                                System.out.println("The blockchain has less that amount... You cannot do a transaction of that amount");
                                System.out.println();
                                System.out.println("Please indicate the value of the transation");
                                value =Integer.parseInt(scan.nextLine());
                            }

                            logger.info("Sending to miners transaction of "+ value +" to "+recipient);
                           
                            tree.sendTransaction(recipient, value);

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


    public static void Menu_Inicial(){
        System.out.println("************************************");
        System.out.println("* Welcome to our Public Ledger! :) *");
        System.out.println("************************************");
        System.out.println();
        System.out.println();
        System.out.println("-------------------");
        System.out.println("Available commands:");
        System.out.println("-------------------");
        System.out.println();
        System.out.println("-CreateHardCodedNode");
        System.out.println("-CreateMiner");
        System.out.println("-CreateUser <username>");
        System.out.println("-SendTransaction");
        System.out.println("-PrintAllNodes");
        System.out.println("-PrintAllChain");
        System.out.println("-quit");
        System.out.println();
        System.out.println();
        System.out.print("Option: ");

 }

 public static void Menu(){
    System.out.println("************************************");
    System.out.println("*              USER MENU           *");
    System.out.println("************************************");
    System.out.println();
    System.out.println();
    System.out.println("-------------------");
    System.out.println("Available commands:");
    System.out.println("-------------------");
    System.out.println();
    System.out.println("-SendTransaction");
    System.out.println("-PrintAllNodes");
    System.out.println("-PrintAllChain");
    System.out.println("-quit");
    System.out.println();
    System.out.println();
    System.out.print("Option: ");

}


    public static void main(String[] args) {

        Kademlia pl = new Kademlia();
        pl.launch();

    }

}