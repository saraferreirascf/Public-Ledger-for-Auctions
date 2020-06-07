// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: blockchain.proto

package kademlia_public_ledger;

public interface Transaction_OrBuilder extends
    // @@protoc_insertion_point(interface_extends:Transaction_)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string transactionId = 1;</code>
   * @return The transactionId.
   */
  java.lang.String getTransactionId();
  /**
   * <code>string transactionId = 1;</code>
   * @return The bytes for transactionId.
   */
  com.google.protobuf.ByteString
      getTransactionIdBytes();

  /**
   * <code>string sender = 2;</code>
   * @return The sender.
   */
  java.lang.String getSender();
  /**
   * <code>string sender = 2;</code>
   * @return The bytes for sender.
   */
  com.google.protobuf.ByteString
      getSenderBytes();

  /**
   * <code>string reciepient = 3;</code>
   * @return The reciepient.
   */
  java.lang.String getReciepient();
  /**
   * <code>string reciepient = 3;</code>
   * @return The bytes for reciepient.
   */
  com.google.protobuf.ByteString
      getReciepientBytes();

  /**
   * <code>float value = 4;</code>
   * @return The value.
   */
  float getValue();

  /**
   * <code>bytes signature = 5;</code>
   * @return The signature.
   */
  com.google.protobuf.ByteString getSignature();

  /**
   * <code>repeated .TransactionInput_ inputs = 6;</code>
   */
  java.util.List<kademlia_public_ledger.TransactionInput_> 
      getInputsList();
  /**
   * <code>repeated .TransactionInput_ inputs = 6;</code>
   */
  kademlia_public_ledger.TransactionInput_ getInputs(int index);
  /**
   * <code>repeated .TransactionInput_ inputs = 6;</code>
   */
  int getInputsCount();
  /**
   * <code>repeated .TransactionInput_ inputs = 6;</code>
   */
  java.util.List<? extends kademlia_public_ledger.TransactionInput_OrBuilder> 
      getInputsOrBuilderList();
  /**
   * <code>repeated .TransactionInput_ inputs = 6;</code>
   */
  kademlia_public_ledger.TransactionInput_OrBuilder getInputsOrBuilder(
      int index);

  /**
   * <code>repeated .TransactionOutput_ outputs = 7;</code>
   */
  java.util.List<kademlia_public_ledger.TransactionOutput_> 
      getOutputsList();
  /**
   * <code>repeated .TransactionOutput_ outputs = 7;</code>
   */
  kademlia_public_ledger.TransactionOutput_ getOutputs(int index);
  /**
   * <code>repeated .TransactionOutput_ outputs = 7;</code>
   */
  int getOutputsCount();
  /**
   * <code>repeated .TransactionOutput_ outputs = 7;</code>
   */
  java.util.List<? extends kademlia_public_ledger.TransactionOutput_OrBuilder> 
      getOutputsOrBuilderList();
  /**
   * <code>repeated .TransactionOutput_ outputs = 7;</code>
   */
  kademlia_public_ledger.TransactionOutput_OrBuilder getOutputsOrBuilder(
      int index);

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
