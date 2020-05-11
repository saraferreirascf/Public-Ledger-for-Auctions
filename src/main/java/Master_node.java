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

public class Master_node {
    public static KBucket k_bucket = new KBucket();
    public static ArrayList<Node> nodes = new ArrayList<Node>();
    private static final Logger logger = Logger.getLogger(Master_node.class.getName());
    private static Server server;

    private static void start(int port_number) throws IOException {
        /* The port on which the server should run */
        int port = port_number;
        // int port = 50051;
        // String text = String.valueOf(port_number);
        server = ServerBuilder.forPort(port).addService(new GreeterImpl()).build().start();
        logger.info("Master node started, listening on " + port);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown
                // hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                /*try {
                    //Master_node.this.stop();
                } catch (InterruptedException e) {

                    e.printStackTrace(System.err);
                }*/
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);

        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        start(50051);
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