// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: blockchain.proto

package kademlia_public_ledger;

public interface Key_ValueOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Key_Value)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>bytes key = 1;</code>
   * @return The key.
   */
  com.google.protobuf.ByteString getKey();

  /**
   * <code>bytes kv = 2;</code>
   * @return The kv.
   */
  com.google.protobuf.ByteString getKv();

  /**
   * <code>string value = 4;</code>
   * @return The value.
   */
  java.lang.String getValue();
  /**
   * <code>string value = 4;</code>
   * @return The bytes for value.
   */
  com.google.protobuf.ByteString
      getValueBytes();

  /**
   * <code>.BasicNode sender = 3;</code>
   * @return Whether the sender field is set.
   */
  boolean hasSender();
  /**
   * <code>.BasicNode sender = 3;</code>
   * @return The sender.
   */
  kademlia_public_ledger.BasicNode getSender();
  /**
   * <code>.BasicNode sender = 3;</code>
   */
  kademlia_public_ledger.BasicNodeOrBuilder getSenderOrBuilder();
}
