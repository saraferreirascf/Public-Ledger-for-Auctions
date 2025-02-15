// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: blockchain.proto

package kademlia_public_ledger;

/**
 * Protobuf type {@code NodeName}
 */
public  final class NodeName extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:NodeName)
    NodeNameOrBuilder {
private static final long serialVersionUID = 0L;
  // Use NodeName.newBuilder() to construct.
  private NodeName(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private NodeName() {
    name_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new NodeName();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private NodeName(
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
            kademlia_public_ledger.BasicNode.Builder subBuilder = null;
            if (sender_ != null) {
              subBuilder = sender_.toBuilder();
            }
            sender_ = input.readMessage(kademlia_public_ledger.BasicNode.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(sender_);
              sender_ = subBuilder.buildPartial();
            }

            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            name_ = s;
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
    return kademlia_public_ledger.PL.internal_static_NodeName_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return kademlia_public_ledger.PL.internal_static_NodeName_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            kademlia_public_ledger.NodeName.class, kademlia_public_ledger.NodeName.Builder.class);
  }

  public static final int SENDER_FIELD_NUMBER = 1;
  private kademlia_public_ledger.BasicNode sender_;
  /**
   * <code>.BasicNode sender = 1;</code>
   * @return Whether the sender field is set.
   */
  public boolean hasSender() {
    return sender_ != null;
  }
  /**
   * <code>.BasicNode sender = 1;</code>
   * @return The sender.
   */
  public kademlia_public_ledger.BasicNode getSender() {
    return sender_ == null ? kademlia_public_ledger.BasicNode.getDefaultInstance() : sender_;
  }
  /**
   * <code>.BasicNode sender = 1;</code>
   */
  public kademlia_public_ledger.BasicNodeOrBuilder getSenderOrBuilder() {
    return getSender();
  }

  public static final int NAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object name_;
  /**
   * <code>string name = 2;</code>
   * @return The name.
   */
  public java.lang.String getName() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      name_ = s;
      return s;
    }
  }
  /**
   * <code>string name = 2;</code>
   * @return The bytes for name.
   */
  public com.google.protobuf.ByteString
      getNameBytes() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      name_ = b;
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
    if (sender_ != null) {
      output.writeMessage(1, getSender());
    }
    if (!getNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, name_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (sender_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getSender());
    }
    if (!getNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, name_);
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
    if (!(obj instanceof kademlia_public_ledger.NodeName)) {
      return super.equals(obj);
    }
    kademlia_public_ledger.NodeName other = (kademlia_public_ledger.NodeName) obj;

    if (hasSender() != other.hasSender()) return false;
    if (hasSender()) {
      if (!getSender()
          .equals(other.getSender())) return false;
    }
    if (!getName()
        .equals(other.getName())) return false;
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
    if (hasSender()) {
      hash = (37 * hash) + SENDER_FIELD_NUMBER;
      hash = (53 * hash) + getSender().hashCode();
    }
    hash = (37 * hash) + NAME_FIELD_NUMBER;
    hash = (53 * hash) + getName().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static kademlia_public_ledger.NodeName parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static kademlia_public_ledger.NodeName parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static kademlia_public_ledger.NodeName parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static kademlia_public_ledger.NodeName parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static kademlia_public_ledger.NodeName parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static kademlia_public_ledger.NodeName parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static kademlia_public_ledger.NodeName parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static kademlia_public_ledger.NodeName parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static kademlia_public_ledger.NodeName parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static kademlia_public_ledger.NodeName parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static kademlia_public_ledger.NodeName parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static kademlia_public_ledger.NodeName parseFrom(
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
  public static Builder newBuilder(kademlia_public_ledger.NodeName prototype) {
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
   * Protobuf type {@code NodeName}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:NodeName)
      kademlia_public_ledger.NodeNameOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return kademlia_public_ledger.PL.internal_static_NodeName_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return kademlia_public_ledger.PL.internal_static_NodeName_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              kademlia_public_ledger.NodeName.class, kademlia_public_ledger.NodeName.Builder.class);
    }

    // Construct using kademlia_public_ledger.NodeName.newBuilder()
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
      if (senderBuilder_ == null) {
        sender_ = null;
      } else {
        sender_ = null;
        senderBuilder_ = null;
      }
      name_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return kademlia_public_ledger.PL.internal_static_NodeName_descriptor;
    }

    @java.lang.Override
    public kademlia_public_ledger.NodeName getDefaultInstanceForType() {
      return kademlia_public_ledger.NodeName.getDefaultInstance();
    }

    @java.lang.Override
    public kademlia_public_ledger.NodeName build() {
      kademlia_public_ledger.NodeName result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public kademlia_public_ledger.NodeName buildPartial() {
      kademlia_public_ledger.NodeName result = new kademlia_public_ledger.NodeName(this);
      if (senderBuilder_ == null) {
        result.sender_ = sender_;
      } else {
        result.sender_ = senderBuilder_.build();
      }
      result.name_ = name_;
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
      if (other instanceof kademlia_public_ledger.NodeName) {
        return mergeFrom((kademlia_public_ledger.NodeName)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(kademlia_public_ledger.NodeName other) {
      if (other == kademlia_public_ledger.NodeName.getDefaultInstance()) return this;
      if (other.hasSender()) {
        mergeSender(other.getSender());
      }
      if (!other.getName().isEmpty()) {
        name_ = other.name_;
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
      kademlia_public_ledger.NodeName parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (kademlia_public_ledger.NodeName) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private kademlia_public_ledger.BasicNode sender_;
    private com.google.protobuf.SingleFieldBuilderV3<
        kademlia_public_ledger.BasicNode, kademlia_public_ledger.BasicNode.Builder, kademlia_public_ledger.BasicNodeOrBuilder> senderBuilder_;
    /**
     * <code>.BasicNode sender = 1;</code>
     * @return Whether the sender field is set.
     */
    public boolean hasSender() {
      return senderBuilder_ != null || sender_ != null;
    }
    /**
     * <code>.BasicNode sender = 1;</code>
     * @return The sender.
     */
    public kademlia_public_ledger.BasicNode getSender() {
      if (senderBuilder_ == null) {
        return sender_ == null ? kademlia_public_ledger.BasicNode.getDefaultInstance() : sender_;
      } else {
        return senderBuilder_.getMessage();
      }
    }
    /**
     * <code>.BasicNode sender = 1;</code>
     */
    public Builder setSender(kademlia_public_ledger.BasicNode value) {
      if (senderBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        sender_ = value;
        onChanged();
      } else {
        senderBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.BasicNode sender = 1;</code>
     */
    public Builder setSender(
        kademlia_public_ledger.BasicNode.Builder builderForValue) {
      if (senderBuilder_ == null) {
        sender_ = builderForValue.build();
        onChanged();
      } else {
        senderBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.BasicNode sender = 1;</code>
     */
    public Builder mergeSender(kademlia_public_ledger.BasicNode value) {
      if (senderBuilder_ == null) {
        if (sender_ != null) {
          sender_ =
            kademlia_public_ledger.BasicNode.newBuilder(sender_).mergeFrom(value).buildPartial();
        } else {
          sender_ = value;
        }
        onChanged();
      } else {
        senderBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.BasicNode sender = 1;</code>
     */
    public Builder clearSender() {
      if (senderBuilder_ == null) {
        sender_ = null;
        onChanged();
      } else {
        sender_ = null;
        senderBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.BasicNode sender = 1;</code>
     */
    public kademlia_public_ledger.BasicNode.Builder getSenderBuilder() {
      
      onChanged();
      return getSenderFieldBuilder().getBuilder();
    }
    /**
     * <code>.BasicNode sender = 1;</code>
     */
    public kademlia_public_ledger.BasicNodeOrBuilder getSenderOrBuilder() {
      if (senderBuilder_ != null) {
        return senderBuilder_.getMessageOrBuilder();
      } else {
        return sender_ == null ?
            kademlia_public_ledger.BasicNode.getDefaultInstance() : sender_;
      }
    }
    /**
     * <code>.BasicNode sender = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        kademlia_public_ledger.BasicNode, kademlia_public_ledger.BasicNode.Builder, kademlia_public_ledger.BasicNodeOrBuilder> 
        getSenderFieldBuilder() {
      if (senderBuilder_ == null) {
        senderBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            kademlia_public_ledger.BasicNode, kademlia_public_ledger.BasicNode.Builder, kademlia_public_ledger.BasicNodeOrBuilder>(
                getSender(),
                getParentForChildren(),
                isClean());
        sender_ = null;
      }
      return senderBuilder_;
    }

    private java.lang.Object name_ = "";
    /**
     * <code>string name = 2;</code>
     * @return The name.
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string name = 2;</code>
     * @return The bytes for name.
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string name = 2;</code>
     * @param value The name to set.
     * @return This builder for chaining.
     */
    public Builder setName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      name_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string name = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearName() {
      
      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }
    /**
     * <code>string name = 2;</code>
     * @param value The bytes for name to set.
     * @return This builder for chaining.
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      name_ = value;
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


    // @@protoc_insertion_point(builder_scope:NodeName)
  }

  // @@protoc_insertion_point(class_scope:NodeName)
  private static final kademlia_public_ledger.NodeName DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new kademlia_public_ledger.NodeName();
  }

  public static kademlia_public_ledger.NodeName getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<NodeName>
      PARSER = new com.google.protobuf.AbstractParser<NodeName>() {
    @java.lang.Override
    public NodeName parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new NodeName(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<NodeName> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<NodeName> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public kademlia_public_ledger.NodeName getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

