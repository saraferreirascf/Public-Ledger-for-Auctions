// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: blockchain.proto

package kademlia_public_ledger;

/**
 * Protobuf type {@code Key_Value}
 */
public  final class Key_Value extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:Key_Value)
    Key_ValueOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Key_Value.newBuilder() to construct.
  private Key_Value(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Key_Value() {
    key_ = com.google.protobuf.ByteString.EMPTY;
    kv_ = com.google.protobuf.ByteString.EMPTY;
    value_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Key_Value();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Key_Value(
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

            key_ = input.readBytes();
            break;
          }
          case 18: {

            kv_ = input.readBytes();
            break;
          }
          case 26: {
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
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            value_ = s;
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
    return kademlia_public_ledger.PL.internal_static_Key_Value_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return kademlia_public_ledger.PL.internal_static_Key_Value_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            kademlia_public_ledger.Key_Value.class, kademlia_public_ledger.Key_Value.Builder.class);
  }

  public static final int KEY_FIELD_NUMBER = 1;
  private com.google.protobuf.ByteString key_;
  /**
   * <code>bytes key = 1;</code>
   * @return The key.
   */
  public com.google.protobuf.ByteString getKey() {
    return key_;
  }

  public static final int KV_FIELD_NUMBER = 2;
  private com.google.protobuf.ByteString kv_;
  /**
   * <code>bytes kv = 2;</code>
   * @return The kv.
   */
  public com.google.protobuf.ByteString getKv() {
    return kv_;
  }

  public static final int VALUE_FIELD_NUMBER = 4;
  private volatile java.lang.Object value_;
  /**
   * <code>string value = 4;</code>
   * @return The value.
   */
  public java.lang.String getValue() {
    java.lang.Object ref = value_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      value_ = s;
      return s;
    }
  }
  /**
   * <code>string value = 4;</code>
   * @return The bytes for value.
   */
  public com.google.protobuf.ByteString
      getValueBytes() {
    java.lang.Object ref = value_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      value_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int SENDER_FIELD_NUMBER = 3;
  private kademlia_public_ledger.BasicNode sender_;
  /**
   * <code>.BasicNode sender = 3;</code>
   * @return Whether the sender field is set.
   */
  public boolean hasSender() {
    return sender_ != null;
  }
  /**
   * <code>.BasicNode sender = 3;</code>
   * @return The sender.
   */
  public kademlia_public_ledger.BasicNode getSender() {
    return sender_ == null ? kademlia_public_ledger.BasicNode.getDefaultInstance() : sender_;
  }
  /**
   * <code>.BasicNode sender = 3;</code>
   */
  public kademlia_public_ledger.BasicNodeOrBuilder getSenderOrBuilder() {
    return getSender();
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
    if (!key_.isEmpty()) {
      output.writeBytes(1, key_);
    }
    if (!kv_.isEmpty()) {
      output.writeBytes(2, kv_);
    }
    if (sender_ != null) {
      output.writeMessage(3, getSender());
    }
    if (!getValueBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, value_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!key_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(1, key_);
    }
    if (!kv_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, kv_);
    }
    if (sender_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getSender());
    }
    if (!getValueBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, value_);
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
    if (!(obj instanceof kademlia_public_ledger.Key_Value)) {
      return super.equals(obj);
    }
    kademlia_public_ledger.Key_Value other = (kademlia_public_ledger.Key_Value) obj;

    if (!getKey()
        .equals(other.getKey())) return false;
    if (!getKv()
        .equals(other.getKv())) return false;
    if (!getValue()
        .equals(other.getValue())) return false;
    if (hasSender() != other.hasSender()) return false;
    if (hasSender()) {
      if (!getSender()
          .equals(other.getSender())) return false;
    }
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
    hash = (37 * hash) + KEY_FIELD_NUMBER;
    hash = (53 * hash) + getKey().hashCode();
    hash = (37 * hash) + KV_FIELD_NUMBER;
    hash = (53 * hash) + getKv().hashCode();
    hash = (37 * hash) + VALUE_FIELD_NUMBER;
    hash = (53 * hash) + getValue().hashCode();
    if (hasSender()) {
      hash = (37 * hash) + SENDER_FIELD_NUMBER;
      hash = (53 * hash) + getSender().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static kademlia_public_ledger.Key_Value parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static kademlia_public_ledger.Key_Value parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static kademlia_public_ledger.Key_Value parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static kademlia_public_ledger.Key_Value parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static kademlia_public_ledger.Key_Value parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static kademlia_public_ledger.Key_Value parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static kademlia_public_ledger.Key_Value parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static kademlia_public_ledger.Key_Value parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static kademlia_public_ledger.Key_Value parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static kademlia_public_ledger.Key_Value parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static kademlia_public_ledger.Key_Value parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static kademlia_public_ledger.Key_Value parseFrom(
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
  public static Builder newBuilder(kademlia_public_ledger.Key_Value prototype) {
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
   * Protobuf type {@code Key_Value}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:Key_Value)
      kademlia_public_ledger.Key_ValueOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return kademlia_public_ledger.PL.internal_static_Key_Value_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return kademlia_public_ledger.PL.internal_static_Key_Value_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              kademlia_public_ledger.Key_Value.class, kademlia_public_ledger.Key_Value.Builder.class);
    }

    // Construct using kademlia_public_ledger.Key_Value.newBuilder()
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
      key_ = com.google.protobuf.ByteString.EMPTY;

      kv_ = com.google.protobuf.ByteString.EMPTY;

      value_ = "";

      if (senderBuilder_ == null) {
        sender_ = null;
      } else {
        sender_ = null;
        senderBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return kademlia_public_ledger.PL.internal_static_Key_Value_descriptor;
    }

    @java.lang.Override
    public kademlia_public_ledger.Key_Value getDefaultInstanceForType() {
      return kademlia_public_ledger.Key_Value.getDefaultInstance();
    }

    @java.lang.Override
    public kademlia_public_ledger.Key_Value build() {
      kademlia_public_ledger.Key_Value result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public kademlia_public_ledger.Key_Value buildPartial() {
      kademlia_public_ledger.Key_Value result = new kademlia_public_ledger.Key_Value(this);
      result.key_ = key_;
      result.kv_ = kv_;
      result.value_ = value_;
      if (senderBuilder_ == null) {
        result.sender_ = sender_;
      } else {
        result.sender_ = senderBuilder_.build();
      }
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
      if (other instanceof kademlia_public_ledger.Key_Value) {
        return mergeFrom((kademlia_public_ledger.Key_Value)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(kademlia_public_ledger.Key_Value other) {
      if (other == kademlia_public_ledger.Key_Value.getDefaultInstance()) return this;
      if (other.getKey() != com.google.protobuf.ByteString.EMPTY) {
        setKey(other.getKey());
      }
      if (other.getKv() != com.google.protobuf.ByteString.EMPTY) {
        setKv(other.getKv());
      }
      if (!other.getValue().isEmpty()) {
        value_ = other.value_;
        onChanged();
      }
      if (other.hasSender()) {
        mergeSender(other.getSender());
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
      kademlia_public_ledger.Key_Value parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (kademlia_public_ledger.Key_Value) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private com.google.protobuf.ByteString key_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes key = 1;</code>
     * @return The key.
     */
    public com.google.protobuf.ByteString getKey() {
      return key_;
    }
    /**
     * <code>bytes key = 1;</code>
     * @param value The key to set.
     * @return This builder for chaining.
     */
    public Builder setKey(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      key_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes key = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearKey() {
      
      key_ = getDefaultInstance().getKey();
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString kv_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes kv = 2;</code>
     * @return The kv.
     */
    public com.google.protobuf.ByteString getKv() {
      return kv_;
    }
    /**
     * <code>bytes kv = 2;</code>
     * @param value The kv to set.
     * @return This builder for chaining.
     */
    public Builder setKv(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      kv_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes kv = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearKv() {
      
      kv_ = getDefaultInstance().getKv();
      onChanged();
      return this;
    }

    private java.lang.Object value_ = "";
    /**
     * <code>string value = 4;</code>
     * @return The value.
     */
    public java.lang.String getValue() {
      java.lang.Object ref = value_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        value_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string value = 4;</code>
     * @return The bytes for value.
     */
    public com.google.protobuf.ByteString
        getValueBytes() {
      java.lang.Object ref = value_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        value_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string value = 4;</code>
     * @param value The value to set.
     * @return This builder for chaining.
     */
    public Builder setValue(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      value_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string value = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearValue() {
      
      value_ = getDefaultInstance().getValue();
      onChanged();
      return this;
    }
    /**
     * <code>string value = 4;</code>
     * @param value The bytes for value to set.
     * @return This builder for chaining.
     */
    public Builder setValueBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      value_ = value;
      onChanged();
      return this;
    }

    private kademlia_public_ledger.BasicNode sender_;
    private com.google.protobuf.SingleFieldBuilderV3<
        kademlia_public_ledger.BasicNode, kademlia_public_ledger.BasicNode.Builder, kademlia_public_ledger.BasicNodeOrBuilder> senderBuilder_;
    /**
     * <code>.BasicNode sender = 3;</code>
     * @return Whether the sender field is set.
     */
    public boolean hasSender() {
      return senderBuilder_ != null || sender_ != null;
    }
    /**
     * <code>.BasicNode sender = 3;</code>
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
     * <code>.BasicNode sender = 3;</code>
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
     * <code>.BasicNode sender = 3;</code>
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
     * <code>.BasicNode sender = 3;</code>
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
     * <code>.BasicNode sender = 3;</code>
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
     * <code>.BasicNode sender = 3;</code>
     */
    public kademlia_public_ledger.BasicNode.Builder getSenderBuilder() {
      
      onChanged();
      return getSenderFieldBuilder().getBuilder();
    }
    /**
     * <code>.BasicNode sender = 3;</code>
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
     * <code>.BasicNode sender = 3;</code>
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


    // @@protoc_insertion_point(builder_scope:Key_Value)
  }

  // @@protoc_insertion_point(class_scope:Key_Value)
  private static final kademlia_public_ledger.Key_Value DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new kademlia_public_ledger.Key_Value();
  }

  public static kademlia_public_ledger.Key_Value getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Key_Value>
      PARSER = new com.google.protobuf.AbstractParser<Key_Value>() {
    @java.lang.Override
    public Key_Value parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Key_Value(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Key_Value> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Key_Value> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public kademlia_public_ledger.Key_Value getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

