package kademlia_public_ledger;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * Interface exported by the server.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.28.1)",
    comments = "Source: blockchain.proto")
public final class Public_ledgerGrpc {

  private Public_ledgerGrpc() {}

  public static final String SERVICE_NAME = "public_ledger.Public_ledger";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<kademlia_public_ledger.NodeID,
      kademlia_public_ledger.BooleanSuccessResponse> getPINGMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PING",
      requestType = kademlia_public_ledger.NodeID.class,
      responseType = kademlia_public_ledger.BooleanSuccessResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kademlia_public_ledger.NodeID,
      kademlia_public_ledger.BooleanSuccessResponse> getPINGMethod() {
    io.grpc.MethodDescriptor<kademlia_public_ledger.NodeID, kademlia_public_ledger.BooleanSuccessResponse> getPINGMethod;
    if ((getPINGMethod = Public_ledgerGrpc.getPINGMethod) == null) {
      synchronized (Public_ledgerGrpc.class) {
        if ((getPINGMethod = Public_ledgerGrpc.getPINGMethod) == null) {
          Public_ledgerGrpc.getPINGMethod = getPINGMethod =
              io.grpc.MethodDescriptor.<kademlia_public_ledger.NodeID, kademlia_public_ledger.BooleanSuccessResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PING"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kademlia_public_ledger.NodeID.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kademlia_public_ledger.BooleanSuccessResponse.getDefaultInstance()))
              .setSchemaDescriptor(new Public_ledgerMethodDescriptorSupplier("PING"))
              .build();
        }
      }
    }
    return getPINGMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kademlia_public_ledger.Key_Value,
      kademlia_public_ledger.BooleanSuccessResponse> getSTOREMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "STORE",
      requestType = kademlia_public_ledger.Key_Value.class,
      responseType = kademlia_public_ledger.BooleanSuccessResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kademlia_public_ledger.Key_Value,
      kademlia_public_ledger.BooleanSuccessResponse> getSTOREMethod() {
    io.grpc.MethodDescriptor<kademlia_public_ledger.Key_Value, kademlia_public_ledger.BooleanSuccessResponse> getSTOREMethod;
    if ((getSTOREMethod = Public_ledgerGrpc.getSTOREMethod) == null) {
      synchronized (Public_ledgerGrpc.class) {
        if ((getSTOREMethod = Public_ledgerGrpc.getSTOREMethod) == null) {
          Public_ledgerGrpc.getSTOREMethod = getSTOREMethod =
              io.grpc.MethodDescriptor.<kademlia_public_ledger.Key_Value, kademlia_public_ledger.BooleanSuccessResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "STORE"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kademlia_public_ledger.Key_Value.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kademlia_public_ledger.BooleanSuccessResponse.getDefaultInstance()))
              .setSchemaDescriptor(new Public_ledgerMethodDescriptorSupplier("STORE"))
              .build();
        }
      }
    }
    return getSTOREMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kademlia_public_ledger.NodeID,
      kademlia_public_ledger.NodeInfo> getFINDNODEMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FIND_NODE",
      requestType = kademlia_public_ledger.NodeID.class,
      responseType = kademlia_public_ledger.NodeInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kademlia_public_ledger.NodeID,
      kademlia_public_ledger.NodeInfo> getFINDNODEMethod() {
    io.grpc.MethodDescriptor<kademlia_public_ledger.NodeID, kademlia_public_ledger.NodeInfo> getFINDNODEMethod;
    if ((getFINDNODEMethod = Public_ledgerGrpc.getFINDNODEMethod) == null) {
      synchronized (Public_ledgerGrpc.class) {
        if ((getFINDNODEMethod = Public_ledgerGrpc.getFINDNODEMethod) == null) {
          Public_ledgerGrpc.getFINDNODEMethod = getFINDNODEMethod =
              io.grpc.MethodDescriptor.<kademlia_public_ledger.NodeID, kademlia_public_ledger.NodeInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FIND_NODE"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kademlia_public_ledger.NodeID.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kademlia_public_ledger.NodeInfo.getDefaultInstance()))
              .setSchemaDescriptor(new Public_ledgerMethodDescriptorSupplier("FIND_NODE"))
              .build();
        }
      }
    }
    return getFINDNODEMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kademlia_public_ledger.Key,
      kademlia_public_ledger.NodeInfoORValue> getFINDVALUEMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FIND_VALUE",
      requestType = kademlia_public_ledger.Key.class,
      responseType = kademlia_public_ledger.NodeInfoORValue.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kademlia_public_ledger.Key,
      kademlia_public_ledger.NodeInfoORValue> getFINDVALUEMethod() {
    io.grpc.MethodDescriptor<kademlia_public_ledger.Key, kademlia_public_ledger.NodeInfoORValue> getFINDVALUEMethod;
    if ((getFINDVALUEMethod = Public_ledgerGrpc.getFINDVALUEMethod) == null) {
      synchronized (Public_ledgerGrpc.class) {
        if ((getFINDVALUEMethod = Public_ledgerGrpc.getFINDVALUEMethod) == null) {
          Public_ledgerGrpc.getFINDVALUEMethod = getFINDVALUEMethod =
              io.grpc.MethodDescriptor.<kademlia_public_ledger.Key, kademlia_public_ledger.NodeInfoORValue>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FIND_VALUE"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kademlia_public_ledger.Key.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kademlia_public_ledger.NodeInfoORValue.getDefaultInstance()))
              .setSchemaDescriptor(new Public_ledgerMethodDescriptorSupplier("FIND_VALUE"))
              .build();
        }
      }
    }
    return getFINDVALUEMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static Public_ledgerStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<Public_ledgerStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<Public_ledgerStub>() {
        @java.lang.Override
        public Public_ledgerStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new Public_ledgerStub(channel, callOptions);
        }
      };
    return Public_ledgerStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static Public_ledgerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<Public_ledgerBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<Public_ledgerBlockingStub>() {
        @java.lang.Override
        public Public_ledgerBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new Public_ledgerBlockingStub(channel, callOptions);
        }
      };
    return Public_ledgerBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static Public_ledgerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<Public_ledgerFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<Public_ledgerFutureStub>() {
        @java.lang.Override
        public Public_ledgerFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new Public_ledgerFutureStub(channel, callOptions);
        }
      };
    return Public_ledgerFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static abstract class Public_ledgerImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * A simple gRPC.
     * The PING RPC probes a node to see if it is online
     * </pre>
     */
    public void pING(kademlia_public_ledger.NodeID request,
        io.grpc.stub.StreamObserver<kademlia_public_ledger.BooleanSuccessResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPINGMethod(), responseObserver);
    }

    /**
     * <pre>
     * A simple gRPC
     * The STORE RPC instructs a node to store pair for later retrieval
     * </pre>
     */
    public void sTORE(kademlia_public_ledger.Key_Value request,
        io.grpc.stub.StreamObserver<kademlia_public_ledger.BooleanSuccessResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSTOREMethod(), responseObserver);
    }

    /**
     * <pre>
     * A simple gRPC
     * The FIND_NODE RPC takes a 160-bit ID as an argument.
     * The recipient of the RPC returns {IP address, UDP port, NodeID}
     * triples for k nodes it knows about closest to the target ID
     * </pre>
     */
    public void fINDNODE(kademlia_public_ledger.NodeID request,
        io.grpc.stub.StreamObserver<kademlia_public_ledger.NodeInfo> responseObserver) {
      asyncUnimplementedUnaryCall(getFINDNODEMethod(), responseObserver);
    }

    /**
     * <pre>
     * A simple gRPC
     * The FIND_VALUE behaves like FIND_NODE - returning {IP address, UDP port, NodeID}
     * triples with one exception. If the rpc recipient has received
     * a store rpc for the key, it just returns the stored value
     * </pre>
     */
    public void fINDVALUE(kademlia_public_ledger.Key request,
        io.grpc.stub.StreamObserver<kademlia_public_ledger.NodeInfoORValue> responseObserver) {
      asyncUnimplementedUnaryCall(getFINDVALUEMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPINGMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kademlia_public_ledger.NodeID,
                kademlia_public_ledger.BooleanSuccessResponse>(
                  this, METHODID_PING)))
          .addMethod(
            getSTOREMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kademlia_public_ledger.Key_Value,
                kademlia_public_ledger.BooleanSuccessResponse>(
                  this, METHODID_STORE)))
          .addMethod(
            getFINDNODEMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kademlia_public_ledger.NodeID,
                kademlia_public_ledger.NodeInfo>(
                  this, METHODID_FIND_NODE)))
          .addMethod(
            getFINDVALUEMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kademlia_public_ledger.Key,
                kademlia_public_ledger.NodeInfoORValue>(
                  this, METHODID_FIND_VALUE)))
          .build();
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class Public_ledgerStub extends io.grpc.stub.AbstractAsyncStub<Public_ledgerStub> {
    private Public_ledgerStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected Public_ledgerStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new Public_ledgerStub(channel, callOptions);
    }

    /**
     * <pre>
     * A simple gRPC.
     * The PING RPC probes a node to see if it is online
     * </pre>
     */
    public void pING(kademlia_public_ledger.NodeID request,
        io.grpc.stub.StreamObserver<kademlia_public_ledger.BooleanSuccessResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPINGMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * A simple gRPC
     * The STORE RPC instructs a node to store pair for later retrieval
     * </pre>
     */
    public void sTORE(kademlia_public_ledger.Key_Value request,
        io.grpc.stub.StreamObserver<kademlia_public_ledger.BooleanSuccessResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSTOREMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * A simple gRPC
     * The FIND_NODE RPC takes a 160-bit ID as an argument.
     * The recipient of the RPC returns {IP address, UDP port, NodeID}
     * triples for k nodes it knows about closest to the target ID
     * </pre>
     */
    public void fINDNODE(kademlia_public_ledger.NodeID request,
        io.grpc.stub.StreamObserver<kademlia_public_ledger.NodeInfo> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFINDNODEMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * A simple gRPC
     * The FIND_VALUE behaves like FIND_NODE - returning {IP address, UDP port, NodeID}
     * triples with one exception. If the rpc recipient has received
     * a store rpc for the key, it just returns the stored value
     * </pre>
     */
    public void fINDVALUE(kademlia_public_ledger.Key request,
        io.grpc.stub.StreamObserver<kademlia_public_ledger.NodeInfoORValue> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFINDVALUEMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class Public_ledgerBlockingStub extends io.grpc.stub.AbstractBlockingStub<Public_ledgerBlockingStub> {
    private Public_ledgerBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected Public_ledgerBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new Public_ledgerBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * A simple gRPC.
     * The PING RPC probes a node to see if it is online
     * </pre>
     */
    public kademlia_public_ledger.BooleanSuccessResponse pING(kademlia_public_ledger.NodeID request) {
      return blockingUnaryCall(
          getChannel(), getPINGMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * A simple gRPC
     * The STORE RPC instructs a node to store pair for later retrieval
     * </pre>
     */
    public kademlia_public_ledger.BooleanSuccessResponse sTORE(kademlia_public_ledger.Key_Value request) {
      return blockingUnaryCall(
          getChannel(), getSTOREMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * A simple gRPC
     * The FIND_NODE RPC takes a 160-bit ID as an argument.
     * The recipient of the RPC returns {IP address, UDP port, NodeID}
     * triples for k nodes it knows about closest to the target ID
     * </pre>
     */
    public kademlia_public_ledger.NodeInfo fINDNODE(kademlia_public_ledger.NodeID request) {
      return blockingUnaryCall(
          getChannel(), getFINDNODEMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * A simple gRPC
     * The FIND_VALUE behaves like FIND_NODE - returning {IP address, UDP port, NodeID}
     * triples with one exception. If the rpc recipient has received
     * a store rpc for the key, it just returns the stored value
     * </pre>
     */
    public kademlia_public_ledger.NodeInfoORValue fINDVALUE(kademlia_public_ledger.Key request) {
      return blockingUnaryCall(
          getChannel(), getFINDVALUEMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class Public_ledgerFutureStub extends io.grpc.stub.AbstractFutureStub<Public_ledgerFutureStub> {
    private Public_ledgerFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected Public_ledgerFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new Public_ledgerFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * A simple gRPC.
     * The PING RPC probes a node to see if it is online
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kademlia_public_ledger.BooleanSuccessResponse> pING(
        kademlia_public_ledger.NodeID request) {
      return futureUnaryCall(
          getChannel().newCall(getPINGMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * A simple gRPC
     * The STORE RPC instructs a node to store pair for later retrieval
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kademlia_public_ledger.BooleanSuccessResponse> sTORE(
        kademlia_public_ledger.Key_Value request) {
      return futureUnaryCall(
          getChannel().newCall(getSTOREMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * A simple gRPC
     * The FIND_NODE RPC takes a 160-bit ID as an argument.
     * The recipient of the RPC returns {IP address, UDP port, NodeID}
     * triples for k nodes it knows about closest to the target ID
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kademlia_public_ledger.NodeInfo> fINDNODE(
        kademlia_public_ledger.NodeID request) {
      return futureUnaryCall(
          getChannel().newCall(getFINDNODEMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * A simple gRPC
     * The FIND_VALUE behaves like FIND_NODE - returning {IP address, UDP port, NodeID}
     * triples with one exception. If the rpc recipient has received
     * a store rpc for the key, it just returns the stored value
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kademlia_public_ledger.NodeInfoORValue> fINDVALUE(
        kademlia_public_ledger.Key request) {
      return futureUnaryCall(
          getChannel().newCall(getFINDVALUEMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PING = 0;
  private static final int METHODID_STORE = 1;
  private static final int METHODID_FIND_NODE = 2;
  private static final int METHODID_FIND_VALUE = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final Public_ledgerImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(Public_ledgerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PING:
          serviceImpl.pING((kademlia_public_ledger.NodeID) request,
              (io.grpc.stub.StreamObserver<kademlia_public_ledger.BooleanSuccessResponse>) responseObserver);
          break;
        case METHODID_STORE:
          serviceImpl.sTORE((kademlia_public_ledger.Key_Value) request,
              (io.grpc.stub.StreamObserver<kademlia_public_ledger.BooleanSuccessResponse>) responseObserver);
          break;
        case METHODID_FIND_NODE:
          serviceImpl.fINDNODE((kademlia_public_ledger.NodeID) request,
              (io.grpc.stub.StreamObserver<kademlia_public_ledger.NodeInfo>) responseObserver);
          break;
        case METHODID_FIND_VALUE:
          serviceImpl.fINDVALUE((kademlia_public_ledger.Key) request,
              (io.grpc.stub.StreamObserver<kademlia_public_ledger.NodeInfoORValue>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class Public_ledgerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    Public_ledgerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return kademlia_public_ledger.PL.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Public_ledger");
    }
  }

  private static final class Public_ledgerFileDescriptorSupplier
      extends Public_ledgerBaseDescriptorSupplier {
    Public_ledgerFileDescriptorSupplier() {}
  }

  private static final class Public_ledgerMethodDescriptorSupplier
      extends Public_ledgerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    Public_ledgerMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (Public_ledgerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new Public_ledgerFileDescriptorSupplier())
              .addMethod(getPINGMethod())
              .addMethod(getSTOREMethod())
              .addMethod(getFINDNODEMethod())
              .addMethod(getFINDVALUEMethod())
              .build();
        }
      }
    }
    return result;
  }
}
