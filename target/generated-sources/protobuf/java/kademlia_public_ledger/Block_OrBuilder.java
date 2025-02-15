// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: blockchain.proto

package kademlia_public_ledger;

public interface Block_OrBuilder extends
    // @@protoc_insertion_point(interface_extends:Block_)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string hash = 1;</code>
   * @return The hash.
   */
  java.lang.String getHash();
  /**
   * <code>string hash = 1;</code>
   * @return The bytes for hash.
   */
  com.google.protobuf.ByteString
      getHashBytes();

  /**
   * <code>string previousHash = 2;</code>
   * @return The previousHash.
   */
  java.lang.String getPreviousHash();
  /**
   * <code>string previousHash = 2;</code>
   * @return The bytes for previousHash.
   */
  com.google.protobuf.ByteString
      getPreviousHashBytes();

  /**
   * <code>string merkleRoot = 3;</code>
   * @return The merkleRoot.
   */
  java.lang.String getMerkleRoot();
  /**
   * <code>string merkleRoot = 3;</code>
   * @return The bytes for merkleRoot.
   */
  com.google.protobuf.ByteString
      getMerkleRootBytes();

  /**
   * <code>string data = 4;</code>
   * @return The data.
   */
  java.lang.String getData();
  /**
   * <code>string data = 4;</code>
   * @return The bytes for data.
   */
  com.google.protobuf.ByteString
      getDataBytes();

  /**
   * <code>repeated .Transaction_ transactions = 5;</code>
   */
  java.util.List<kademlia_public_ledger.Transaction_> 
      getTransactionsList();
  /**
   * <code>repeated .Transaction_ transactions = 5;</code>
   */
  kademlia_public_ledger.Transaction_ getTransactions(int index);
  /**
   * <code>repeated .Transaction_ transactions = 5;</code>
   */
  int getTransactionsCount();
  /**
   * <code>repeated .Transaction_ transactions = 5;</code>
   */
  java.util.List<? extends kademlia_public_ledger.Transaction_OrBuilder> 
      getTransactionsOrBuilderList();
  /**
   * <code>repeated .Transaction_ transactions = 5;</code>
   */
  kademlia_public_ledger.Transaction_OrBuilder getTransactionsOrBuilder(
      int index);

  /**
   * <code>int64 timestamp = 6;</code>
   * @return The timestamp.
   */
  long getTimestamp();

  /**
   * <code>int32 nonce = 7;</code>
   * @return The nonce.
   */
  int getNonce();

  /**
   * <code>.BasicNode senderNode = 8;</code>
   * @return Whether the senderNode field is set.
   */
  boolean hasSenderNode();
  /**
   * <code>.BasicNode senderNode = 8;</code>
   * @return The senderNode.
   */
  kademlia_public_ledger.BasicNode getSenderNode();
  /**
   * <code>.BasicNode senderNode = 8;</code>
   */
  kademlia_public_ledger.BasicNodeOrBuilder getSenderNodeOrBuilder();
}
