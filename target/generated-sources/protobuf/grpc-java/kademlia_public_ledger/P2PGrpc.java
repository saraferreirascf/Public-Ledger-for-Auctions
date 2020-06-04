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
public final class P2PGrpc {

  private P2PGrpc() {}

  public static final String SERVICE_NAME = "P2P";

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
    if ((getPINGMethod = P2PGrpc.getPINGMethod) == null) {
      synchronized (P2PGrpc.class) {
        if ((getPINGMethod = P2PGrpc.getPINGMethod) == null) {
          P2PGrpc.getPINGMethod = getPINGMethod =
              io.grpc.MethodDescriptor.<kademlia_public_ledger.NodeID, kademlia_public_ledger.BooleanSuccessResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PING"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kademlia_public_ledger.NodeID.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kademlia_public_ledger.BooleanSuccessResponse.getDefaultInstance()))
              .setSchemaDescriptor(new P2PMethodDescriptorSupplier("PING"))
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
    if ((getSTOREMethod = P2PGrpc.getSTOREMethod) == null) {
      synchronized (P2PGrpc.class) {
        if ((getSTOREMethod = P2PGrpc.getSTOREMethod) == null) {
          P2PGrpc.getSTOREMethod = getSTOREMethod =
              io.grpc.MethodDescriptor.<kademlia_public_ledger.Key_Value, kademlia_public_ledger.BooleanSuccessResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "STORE"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kademlia_public_ledger.Key_Value.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kademlia_public_ledger.BooleanSuccessResponse.getDefaultInstance()))
              .setSchemaDescriptor(new P2PMethodDescriptorSupplier("STORE"))
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
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<kademlia_public_ledger.NodeID,
      kademlia_public_ledger.NodeInfo> getFINDNODEMethod() {
    io.grpc.MethodDescriptor<kademlia_public_ledger.NodeID, kademlia_public_ledger.NodeInfo> getFINDNODEMethod;
    if ((getFINDNODEMethod = P2PGrpc.getFINDNODEMethod) == null) {
      synchronized (P2PGrpc.class) {
        if ((getFINDNODEMethod = P2PGrpc.getFINDNODEMethod) == null) {
          P2PGrpc.getFINDNODEMethod = getFINDNODEMethod =
              io.grpc.MethodDescriptor.<kademlia_public_ledger.NodeID, kademlia_public_ledger.NodeInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FIND_NODE"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kademlia_public_ledger.NodeID.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kademlia_public_ledger.NodeInfo.getDefaultInstance()))
              .setSchemaDescriptor(new P2PMethodDescriptorSupplier("FIND_NODE"))
              .build();
        }
      }
    }
    return getFINDNODEMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kademlia_public_ledger.Key_Value,
      kademlia_public_ledger.NodeInfo> getFINDVALUEMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FIND_VALUE",
      requestType = kademlia_public_ledger.Key_Value.class,
      responseType = kademlia_public_ledger.NodeInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<kademlia_public_ledger.Key_Value,
      kademlia_public_ledger.NodeInfo> getFINDVALUEMethod() {
    io.grpc.MethodDescriptor<kademlia_public_ledger.Key_Value, kademlia_public_ledger.NodeInfo> getFINDVALUEMethod;
    if ((getFINDVALUEMethod = P2PGrpc.getFINDVALUEMethod) == null) {
      synchronized (P2PGrpc.class) {
        if ((getFINDVALUEMethod = P2PGrpc.getFINDVALUEMethod) == null) {
          P2PGrpc.getFINDVALUEMethod = getFINDVALUEMethod =
              io.grpc.MethodDescriptor.<kademlia_public_ledger.Key_Value, kademlia_public_ledger.NodeInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FIND_VALUE"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kademlia_public_ledger.Key_Value.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kademlia_public_ledger.NodeInfo.getDefaultInstance()))
              .setSchemaDescriptor(new P2PMethodDescriptorSupplier("FIND_VALUE"))
              .build();
        }
      }
    }
    return getFINDVALUEMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kademlia_public_ledger.NodeID,
      kademlia_public_ledger.Kbucket> getJOINMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "JOIN",
      requestType = kademlia_public_ledger.NodeID.class,
      responseType = kademlia_public_ledger.Kbucket.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kademlia_public_ledger.NodeID,
      kademlia_public_ledger.Kbucket> getJOINMethod() {
    io.grpc.MethodDescriptor<kademlia_public_ledger.NodeID, kademlia_public_ledger.Kbucket> getJOINMethod;
    if ((getJOINMethod = P2PGrpc.getJOINMethod) == null) {
      synchronized (P2PGrpc.class) {
        if ((getJOINMethod = P2PGrpc.getJOINMethod) == null) {
          P2PGrpc.getJOINMethod = getJOINMethod =
              io.grpc.MethodDescriptor.<kademlia_public_ledger.NodeID, kademlia_public_ledger.Kbucket>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "JOIN"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kademlia_public_ledger.NodeID.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kademlia_public_ledger.Kbucket.getDefaultInstance()))
              .setSchemaDescriptor(new P2PMethodDescriptorSupplier("JOIN"))
              .build();
        }
      }
    }
    return getJOINMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kademlia_public_ledger.NodeInfo,
      kademlia_public_ledger.Block_> getGETBlockChainMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GETBlockChain",
      requestType = kademlia_public_ledger.NodeInfo.class,
      responseType = kademlia_public_ledger.Block_.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<kademlia_public_ledger.NodeInfo,
      kademlia_public_ledger.Block_> getGETBlockChainMethod() {
    io.grpc.MethodDescriptor<kademlia_public_ledger.NodeInfo, kademlia_public_ledger.Block_> getGETBlockChainMethod;
    if ((getGETBlockChainMethod = P2PGrpc.getGETBlockChainMethod) == null) {
      synchronized (P2PGrpc.class) {
        if ((getGETBlockChainMethod = P2PGrpc.getGETBlockChainMethod) == null) {
          P2PGrpc.getGETBlockChainMethod = getGETBlockChainMethod =
              io.grpc.MethodDescriptor.<kademlia_public_ledger.NodeInfo, kademlia_public_ledger.Block_>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GETBlockChain"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kademlia_public_ledger.NodeInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kademlia_public_ledger.Block_.getDefaultInstance()))
              .setSchemaDescriptor(new P2PMethodDescriptorSupplier("GETBlockChain"))
              .build();
        }
      }
    }
    return getGETBlockChainMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kademlia_public_ledger.Transaction_,
      kademlia_public_ledger.BooleanSuccessResponse> getSendTransactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendTransaction",
      requestType = kademlia_public_ledger.Transaction_.class,
      responseType = kademlia_public_ledger.BooleanSuccessResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kademlia_public_ledger.Transaction_,
      kademlia_public_ledger.BooleanSuccessResponse> getSendTransactionMethod() {
    io.grpc.MethodDescriptor<kademlia_public_ledger.Transaction_, kademlia_public_ledger.BooleanSuccessResponse> getSendTransactionMethod;
    if ((getSendTransactionMethod = P2PGrpc.getSendTransactionMethod) == null) {
      synchronized (P2PGrpc.class) {
        if ((getSendTransactionMethod = P2PGrpc.getSendTransactionMethod) == null) {
          P2PGrpc.getSendTransactionMethod = getSendTransactionMethod =
              io.grpc.MethodDescriptor.<kademlia_public_ledger.Transaction_, kademlia_public_ledger.BooleanSuccessResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kademlia_public_ledger.Transaction_.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kademlia_public_ledger.BooleanSuccessResponse.getDefaultInstance()))
              .setSchemaDescriptor(new P2PMethodDescriptorSupplier("SendTransaction"))
              .build();
        }
      }
    }
    return getSendTransactionMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static P2PStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<P2PStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<P2PStub>() {
        @java.lang.Override
        public P2PStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new P2PStub(channel, callOptions);
        }
      };
    return P2PStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static P2PBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<P2PBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<P2PBlockingStub>() {
        @java.lang.Override
        public P2PBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new P2PBlockingStub(channel, callOptions);
        }
      };
    return P2PBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static P2PFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<P2PFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<P2PFutureStub>() {
        @java.lang.Override
        public P2PFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new P2PFutureStub(channel, callOptions);
        }
      };
    return P2PFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static abstract class P2PImplBase implements io.grpc.BindableService {

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
    public void fINDVALUE(kademlia_public_ledger.Key_Value request,
        io.grpc.stub.StreamObserver<kademlia_public_ledger.NodeInfo> responseObserver) {
      asyncUnimplementedUnaryCall(getFINDVALUEMethod(), responseObserver);
    }

    /**
     * <pre>
     * A simple gRPC
     * The FIND_VALUE behaves like FIND_NODE - returning {IP address, UDP port, NodeID}
     * triples with one exception. If the rpc recipient has received
     * a store rpc for the key, it just returns the stored value
     * </pre>
     */
    public void jOIN(kademlia_public_ledger.NodeID request,
        io.grpc.stub.StreamObserver<kademlia_public_ledger.Kbucket> responseObserver) {
      asyncUnimplementedUnaryCall(getJOINMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get BlockChain
     * </pre>
     */
    public void gETBlockChain(kademlia_public_ledger.NodeInfo request,
        io.grpc.stub.StreamObserver<kademlia_public_ledger.Block_> responseObserver) {
      asyncUnimplementedUnaryCall(getGETBlockChainMethod(), responseObserver);
    }

    /**
     * <pre>
     * send transaction to miners
     * </pre>
     */
    public void sendTransaction(kademlia_public_ledger.Transaction_ request,
        io.grpc.stub.StreamObserver<kademlia_public_ledger.BooleanSuccessResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSendTransactionMethod(), responseObserver);
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
            asyncServerStreamingCall(
              new MethodHandlers<
                kademlia_public_ledger.NodeID,
                kademlia_public_ledger.NodeInfo>(
                  this, METHODID_FIND_NODE)))
          .addMethod(
            getFINDVALUEMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                kademlia_public_ledger.Key_Value,
                kademlia_public_ledger.NodeInfo>(
                  this, METHODID_FIND_VALUE)))
          .addMethod(
            getJOINMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kademlia_public_ledger.NodeID,
                kademlia_public_ledger.Kbucket>(
                  this, METHODID_JOIN)))
          .addMethod(
            getGETBlockChainMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                kademlia_public_ledger.NodeInfo,
                kademlia_public_ledger.Block_>(
                  this, METHODID_GETBLOCK_CHAIN)))
          .addMethod(
            getSendTransactionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kademlia_public_ledger.Transaction_,
                kademlia_public_ledger.BooleanSuccessResponse>(
                  this, METHODID_SEND_TRANSACTION)))
          .build();
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class P2PStub extends io.grpc.stub.AbstractAsyncStub<P2PStub> {
    private P2PStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected P2PStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new P2PStub(channel, callOptions);
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
      asyncServerStreamingCall(
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
    public void fINDVALUE(kademlia_public_ledger.Key_Value request,
        io.grpc.stub.StreamObserver<kademlia_public_ledger.NodeInfo> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getFINDVALUEMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * A simple gRPC
     * The FIND_VALUE behaves like FIND_NODE - returning {IP address, UDP port, NodeID}
     * triples with one exception. If the rpc recipient has received
     * a store rpc for the key, it just returns the stored value
     * </pre>
     */
    public void jOIN(kademlia_public_ledger.NodeID request,
        io.grpc.stub.StreamObserver<kademlia_public_ledger.Kbucket> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getJOINMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get BlockChain
     * </pre>
     */
    public void gETBlockChain(kademlia_public_ledger.NodeInfo request,
        io.grpc.stub.StreamObserver<kademlia_public_ledger.Block_> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGETBlockChainMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * send transaction to miners
     * </pre>
     */
    public void sendTransaction(kademlia_public_ledger.Transaction_ request,
        io.grpc.stub.StreamObserver<kademlia_public_ledger.BooleanSuccessResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendTransactionMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class P2PBlockingStub extends io.grpc.stub.AbstractBlockingStub<P2PBlockingStub> {
    private P2PBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected P2PBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new P2PBlockingStub(channel, callOptions);
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
    public java.util.Iterator<kademlia_public_ledger.NodeInfo> fINDNODE(
        kademlia_public_ledger.NodeID request) {
      return blockingServerStreamingCall(
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
    public java.util.Iterator<kademlia_public_ledger.NodeInfo> fINDVALUE(
        kademlia_public_ledger.Key_Value request) {
      return blockingServerStreamingCall(
          getChannel(), getFINDVALUEMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * A simple gRPC
     * The FIND_VALUE behaves like FIND_NODE - returning {IP address, UDP port, NodeID}
     * triples with one exception. If the rpc recipient has received
     * a store rpc for the key, it just returns the stored value
     * </pre>
     */
    public kademlia_public_ledger.Kbucket jOIN(kademlia_public_ledger.NodeID request) {
      return blockingUnaryCall(
          getChannel(), getJOINMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get BlockChain
     * </pre>
     */
    public java.util.Iterator<kademlia_public_ledger.Block_> gETBlockChain(
        kademlia_public_ledger.NodeInfo request) {
      return blockingServerStreamingCall(
          getChannel(), getGETBlockChainMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * send transaction to miners
     * </pre>
     */
    public kademlia_public_ledger.BooleanSuccessResponse sendTransaction(kademlia_public_ledger.Transaction_ request) {
      return blockingUnaryCall(
          getChannel(), getSendTransactionMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class P2PFutureStub extends io.grpc.stub.AbstractFutureStub<P2PFutureStub> {
    private P2PFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected P2PFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new P2PFutureStub(channel, callOptions);
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
     * The FIND_VALUE behaves like FIND_NODE - returning {IP address, UDP port, NodeID}
     * triples with one exception. If the rpc recipient has received
     * a store rpc for the key, it just returns the stored value
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kademlia_public_ledger.Kbucket> jOIN(
        kademlia_public_ledger.NodeID request) {
      return futureUnaryCall(
          getChannel().newCall(getJOINMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * send transaction to miners
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kademlia_public_ledger.BooleanSuccessResponse> sendTransaction(
        kademlia_public_ledger.Transaction_ request) {
      return futureUnaryCall(
          getChannel().newCall(getSendTransactionMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PING = 0;
  private static final int METHODID_STORE = 1;
  private static final int METHODID_FIND_NODE = 2;
  private static final int METHODID_FIND_VALUE = 3;
  private static final int METHODID_JOIN = 4;
  private static final int METHODID_GETBLOCK_CHAIN = 5;
  private static final int METHODID_SEND_TRANSACTION = 6;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final P2PImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(P2PImplBase serviceImpl, int methodId) {
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
          serviceImpl.fINDVALUE((kademlia_public_ledger.Key_Value) request,
              (io.grpc.stub.StreamObserver<kademlia_public_ledger.NodeInfo>) responseObserver);
          break;
        case METHODID_JOIN:
          serviceImpl.jOIN((kademlia_public_ledger.NodeID) request,
              (io.grpc.stub.StreamObserver<kademlia_public_ledger.Kbucket>) responseObserver);
          break;
        case METHODID_GETBLOCK_CHAIN:
          serviceImpl.gETBlockChain((kademlia_public_ledger.NodeInfo) request,
              (io.grpc.stub.StreamObserver<kademlia_public_ledger.Block_>) responseObserver);
          break;
        case METHODID_SEND_TRANSACTION:
          serviceImpl.sendTransaction((kademlia_public_ledger.Transaction_) request,
              (io.grpc.stub.StreamObserver<kademlia_public_ledger.BooleanSuccessResponse>) responseObserver);
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

  private static abstract class P2PBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    P2PBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return kademlia_public_ledger.PL.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("P2P");
    }
  }

  private static final class P2PFileDescriptorSupplier
      extends P2PBaseDescriptorSupplier {
    P2PFileDescriptorSupplier() {}
  }

  private static final class P2PMethodDescriptorSupplier
      extends P2PBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    P2PMethodDescriptorSupplier(String methodName) {
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
      synchronized (P2PGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new P2PFileDescriptorSupplier())
              .addMethod(getPINGMethod())
              .addMethod(getSTOREMethod())
              .addMethod(getFINDNODEMethod())
              .addMethod(getFINDVALUEMethod())
              .addMethod(getJOINMethod())
              .addMethod(getGETBlockChainMethod())
              .addMethod(getSendTransactionMethod())
              .build();
        }
      }
    }
    return result;
  }
}
