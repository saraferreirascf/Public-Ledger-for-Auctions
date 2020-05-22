import com.google.protobuf.Message;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
//import io.grpc.examples.routeguide.RouteGuideGrpc.RouteGuideBlockingStub;
//import io.grpc.examples.routeguide.RouteGuideGrpc.RouteGuideStub;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kademlia_public_ledger.*;
import java.util.concurrent.TimeUnit;

public class Public_ledger_Client {
    private static final Logger logger = Logger.getLogger(Public_ledger_Client.class.getName());

    private final Public_ledgerGrpc.Public_ledgerBlockingStub blockingStub;
    public String name; 
    //private final Public_ledgerGrpc.Public_ledgerStub asyncStub;

    //private Channel channel;

    //private Random random = new Random();
    //private TestHelper testHelper;

     /** Construct client for accessing Public_ledger server using the existing channel. */
    public Public_ledger_Client(Channel channelBuilder) {
        blockingStub = Public_ledgerGrpc.newBlockingStub(channelBuilder);
        name="client";
       // asyncStub = Public_ledgerGrpc.newStub(channelBuilder);
    }

    public void PING() {
       // NodeID request = NodeID.newBuilder().setNodeID("1").build();
       NodeID request = NodeID.newBuilder().setNodeID("1").setClientName(name).build();
        BooleanSuccessResponse response;
        try {
            response = blockingStub.pING(request);
            System.out.println("Response: " + response.getSuccess());
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
    }

    /** Issues several different requests and then exits. */
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:" + args[0]).usePlaintext().build();
        System.out.println("status: " + channel.getState(true).toString());
        try {
            Public_ledger_Client client = new Public_ledger_Client(channel);
            client.PING();
        } finally {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }

}