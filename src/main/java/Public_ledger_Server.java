import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import kademlia_public_ledger.*;
import java.util.concurrent.TimeUnit;

public class Public_ledger_Server {
  private static final Logger logger = Logger.getLogger(Public_ledger_Server.class.getName());

  private final int port;
  private final Server server;

  public Public_ledger_Server(int port) throws IOException {
    this.port = port;
    server = ServerBuilder.forPort(port).addService(new Public_ledger_Service())
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
          Public_ledger_Server.this.stop();
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

  /**
   * Main method.  This comment makes the linter happy.
   */
  public static void main(String[] args) throws Exception {
    Public_ledger_Server server = new Public_ledger_Server(Integer.parseInt(args[0]));
    server.start();
    server.blockUntilShutdown();
  }
  
  
  private static class Public_ledger_Service extends Public_ledgerGrpc.Public_ledgerImplBase {

    @Override
    public void pING(NodeID request, StreamObserver<BooleanSuccessResponse> responseObserver) {
      responseObserver.onNext(BooleanSuccessResponse.newBuilder().setSuccess(true).build());
      System.out.println(request.getClientName() + " has connected");
      responseObserver.onCompleted();
    }

  }

}