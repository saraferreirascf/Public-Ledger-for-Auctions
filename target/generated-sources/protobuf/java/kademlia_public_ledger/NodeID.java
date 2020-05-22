// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: blockchain.proto

package kademlia_public_ledger;

/**
 * Protobuf type {@code public_ledger.NodeID}
 */
public  final class NodeID extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:public_ledger.NodeID)
    NodeIDOrBuilder {
private static final long serialVersionUID = 0L;
  // Use NodeID.newBuilder() to construct.
  private NodeID(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private NodeID() {
    nodeID_ = "";
    clientName_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new NodeID();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private NodeID(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            nodeID_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            clientName_ = s;
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return kademlia_public_ledger.PL.internal_static_public_ledger_NodeID_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return kademlia_public_ledger.PL.internal_static_public_ledger_NodeID_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            kademlia_public_ledger.NodeID.class, kademlia_public_ledger.NodeID.Builder.class);
  }

  public static final int NODEID_FIELD_NUMBER = 1;
  private volatile java.lang.Object nodeID_;
  /**
   * <code>string nodeID = 1;</code>
   * @return The nodeID.
   */
  public java.lang.String getNodeID() {
    java.lang.Object ref = nodeID_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      nodeID_ = s;
      return s;
    }
  }
  /**
   * <code>string nodeID = 1;</code>
   * @return The bytes for nodeID.
   */
  public com.google.protobuf.ByteString
      getNodeIDBytes() {
    java.lang.Object ref = nodeID_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      nodeID_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CLIENT_NAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object clientName_;
  /**
   * <code>string client_name = 2;</code>
   * @return The clientName.
   */
  public java.lang.String getClientName() {
    java.lang.Object ref = clientName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      clientName_ = s;
      return s;
    }
  }
  /**
   * <code>string client_name = 2;</code>
   * @return The bytes for clientName.
   */
  public com.google.protobuf.ByteString
      getClientNameBytes() {
    java.lang.Object ref = clientName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      clientName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getNodeIDBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, nodeID_);
    }
    if (!getClientNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, clientName_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getNodeIDBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, nodeID_);
    }
    if (!getClientNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, clientName_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof kademlia_public_ledger.NodeID)) {
      return super.equals(obj);
    }
    kademlia_public_ledger.NodeID other = (kademlia_public_ledger.NodeID) obj;

    if (!getNodeID()
        .equals(other.getNodeID())) return false;
    if (!getClientName()
        .equals(other.getClientName())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + NODEID_FIELD_NUMBER;
    hash = (53 * hash) + getNodeID().hashCode();
    hash = (37 * hash) + CLIENT_NAME_FIELD_NUMBER;
    hash = (53 * hash) + getClientName().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static kademlia_public_ledger.NodeID parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static kademlia_public_ledger.NodeID parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static kademlia_public_ledger.NodeID parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static kademlia_public_ledger.NodeID parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static kademlia_public_ledger.NodeID parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static kademlia_public_ledger.NodeID parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static kademlia_public_ledger.NodeID parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static kademlia_public_ledger.NodeID parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static kademlia_public_ledger.NodeID parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static kademlia_public_ledger.NodeID parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static kademlia_public_ledger.NodeID parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static kademlia_public_ledger.NodeID parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(kademlia_public_ledger.NodeID prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code public_ledger.NodeID}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:public_ledger.NodeID)
      kademlia_public_ledger.NodeIDOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return kademlia_public_ledger.PL.internal_static_public_ledger_NodeID_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return kademlia_public_ledger.PL.internal_static_public_ledger_NodeID_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              kademlia_public_ledger.NodeID.class, kademlia_public_ledger.NodeID.Builder.class);
    }

    // Construct using kademlia_public_ledger.NodeID.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      nodeID_ = "";

      clientName_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return kademlia_public_ledger.PL.internal_static_public_ledger_NodeID_descriptor;
    }

    @java.lang.Override
    public kademlia_public_ledger.NodeID getDefaultInstanceForType() {
      return kademlia_public_ledger.NodeID.getDefaultInstance();
    }

    @java.lang.Override
    public kademlia_public_ledger.NodeID build() {
      kademlia_public_ledger.NodeID result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public kademlia_public_ledger.NodeID buildPartial() {
      kademlia_public_ledger.NodeID result = new kademlia_public_ledger.NodeID(this);
      result.nodeID_ = nodeID_;
      result.clientName_ = clientName_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof kademlia_public_ledger.NodeID) {
        return mergeFrom((kademlia_public_ledger.NodeID)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(kademlia_public_ledger.NodeID other) {
      if (other == kademlia_public_ledger.NodeID.getDefaultInstance()) return this;
      if (!other.getNodeID().isEmpty()) {
        nodeID_ = other.nodeID_;
        onChanged();
      }
      if (!other.getClientName().isEmpty()) {
        clientName_ = other.clientName_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      kademlia_public_ledger.NodeID parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (kademlia_public_ledger.NodeID) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object nodeID_ = "";
    /**
     * <code>string nodeID = 1;</code>
     * @return The nodeID.
     */
    public java.lang.String getNodeID() {
      java.lang.Object ref = nodeID_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        nodeID_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string nodeID = 1;</code>
     * @return The bytes for nodeID.
     */
    public com.google.protobuf.ByteString
        getNodeIDBytes() {
      java.lang.Object ref = nodeID_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        nodeID_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string nodeID = 1;</code>
     * @param value The nodeID to set.
     * @return This builder for chaining.
     */
    public Builder setNodeID(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      nodeID_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string nodeID = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearNodeID() {
      
      nodeID_ = getDefaultInstance().getNodeID();
      onChanged();
      return this;
    }
    /**
     * <code>string nodeID = 1;</code>
     * @param value The bytes for nodeID to set.
     * @return This builder for chaining.
     */
    public Builder setNodeIDBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      nodeID_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object clientName_ = "";
    /**
     * <code>string client_name = 2;</code>
     * @return The clientName.
     */
    public java.lang.String getClientName() {
      java.lang.Object ref = clientName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        clientName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string client_name = 2;</code>
     * @return The bytes for clientName.
     */
    public com.google.protobuf.ByteString
        getClientNameBytes() {
      java.lang.Object ref = clientName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        clientName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string client_name = 2;</code>
     * @param value The clientName to set.
     * @return This builder for chaining.
     */
    public Builder setClientName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      clientName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string client_name = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearClientName() {
      
      clientName_ = getDefaultInstance().getClientName();
      onChanged();
      return this;
    }
    /**
     * <code>string client_name = 2;</code>
     * @param value The bytes for clientName to set.
     * @return This builder for chaining.
     */
    public Builder setClientNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      clientName_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:public_ledger.NodeID)
  }

  // @@protoc_insertion_point(class_scope:public_ledger.NodeID)
  private static final kademlia_public_ledger.NodeID DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new kademlia_public_ledger.NodeID();
  }

  public static kademlia_public_ledger.NodeID getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<NodeID>
      PARSER = new com.google.protobuf.AbstractParser<NodeID>() {
    @java.lang.Override
    public NodeID parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new NodeID(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<NodeID> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<NodeID> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public kademlia_public_ledger.NodeID getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

