import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileWriter;   


public class Nodes {
  private static final Logger logger = Logger.getLogger(Nodes.class.getName());

  private Server server;
  private final static Nodes node1 = new Nodes();

  private void start(int port_number) throws IOException {
    /* The port on which the server should run */
    int port = port_number;
    // int port = 50051;
    String text = String.valueOf(port_number);
    writeFile(text);
    server = ServerBuilder.forPort(port).addService(new GreeterImpl()).build().start();
    logger.info("Server started, listening on " + port);
    

    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        // Use stderr here since the logger may have been reset by its JVM shutdown
        // hook.
        System.err.println("*** shutting down gRPC server since JVM is shutting down");
        try {
          Nodes.this.stop();
        } catch (InterruptedException e) {

          e.printStackTrace(System.err);
        }
        System.err.println("*** server shut down");
      }
    });
  }

  public static ArrayList<String> readFile() {
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

  private void stop() throws InterruptedException {
    if (server != null) {
      server.shutdown().awaitTermination(30, TimeUnit.SECONDS);

    }
  }

  /**
   * Await termination on the main thread since the grpc library uses daemon
   * threads.
   */
  private void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }


  /**
   * Main launches the server from the command line.
   */
  public static void main(String[] args) throws IOException, InterruptedException {

    ArrayList<String> file_content = new ArrayList<String>();
    file_content = readFile();
    int port;
    if(file_content.size()==0){
      port=50052;
    }
    else{
      port = Integer.parseInt(file_content.get(file_content.size() - 1)) + 1;
    }
    // System.out.println(Arrays.toString(file_content.toArray()));
    
    final Nodes new_node = new Nodes();

    System.out.println("New node in port"+port);
    new_node.start(port);
    
    Node n1=new Node(port,8008);
    Master_node.nodes.add(n1);
    
    Node master = new Node(50051,8008);

    KBucket k_new_node = new KBucket();
    k_new_node.add_to_KBucket(master); //master node
    Master_node.k_bucket.add_to_KBucket(n1);

    Master_node.k_bucket.to_String();

    /*ManagedChannel channel = ManagedChannelBuilder.forTarget(String.valueOf(nodes.get(0).get_Port())).usePlaintext().build();
   final GreeterGrpc.GreeterBlockingStub blockingStub;
    blockingStub=GreeterGrpc.newBlockingStub(channel);  

    logger.info("Will try to greet " + "master node" + " ...");
    HelloRequest request = HelloRequest.newBuilder().setName("master node").build();
    HelloReply response;
    try {
      response = blockingStub.sayHello(request);
    } catch (StatusRuntimeException e) {
      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      return;
    }
    logger.info("Greeting: " + response.getMessage());*/
    
  

    node1.blockUntilShutdown();
    new_node.blockUntilShutdown();

  }


  static class GreeterImpl extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
      HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
      responseObserver.onNext(reply);
      responseObserver.onCompleted();
    }
  }
}
