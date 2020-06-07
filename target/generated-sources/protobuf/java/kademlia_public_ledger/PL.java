// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: blockchain.proto

package kademlia_public_ledger;

public final class PL {
  private PL() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Kbucket_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Kbucket_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Key_Value_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Key_Value_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Block__descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Block__fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Transaction__descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Transaction__fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_TransactionInput__descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_TransactionInput__fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_TransactionOutput__descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_TransactionOutput__fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_BooleanSuccessResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_BooleanSuccessResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_NodeInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_NodeInfo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_NodeID_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_NodeID_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_NodeName_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_NodeName_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_BasicNode_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_BasicNode_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_NodeInfoORValue_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_NodeInfoORValue_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020blockchain.proto\"\\\n\007Kbucket\022)\n\010respons" +
      "e\030\002 \001(\0132\027.BooleanSuccessResponse\022\033\n\010brot" +
      "hers\030\n \003(\0132\t.NodeInfo\022\t\n\001i\030\001 \001(\005\"O\n\tKey_" +
      "Value\022\013\n\003key\030\001 \001(\014\022\n\n\002kv\030\002 \001(\014\022\r\n\005value\030" +
      "\004 \001(\t\022\032\n\006sender\030\003 \001(\0132\n.BasicNode\"\265\001\n\006Bl" +
      "ock_\022\014\n\004hash\030\001 \001(\t\022\024\n\014previousHash\030\002 \001(\t" +
      "\022\022\n\nmerkleRoot\030\003 \001(\t\022\014\n\004data\030\004 \001(\t\022#\n\014tr" +
      "ansactions\030\005 \003(\0132\r.Transaction_\022\021\n\ttimes" +
      "tamp\030\006 \001(\003\022\r\n\005nonce\030\007 \001(\005\022\036\n\nsenderNode\030" +
      "\010 \001(\0132\n.BasicNode\"\325\001\n\014Transaction_\022\025\n\rtr" +
      "ansactionId\030\001 \001(\t\022\016\n\006sender\030\002 \001(\t\022\022\n\nrec" +
      "iepient\030\003 \001(\t\022\r\n\005value\030\004 \001(\002\022\021\n\tsignatur" +
      "e\030\005 \001(\014\022\"\n\006inputs\030\006 \003(\0132\022.TransactionInp" +
      "ut_\022$\n\007outputs\030\007 \003(\0132\023.TransactionOutput" +
      "_\022\036\n\nsenderNode\030\010 \001(\0132\n.BasicNode\"S\n\021Tra" +
      "nsactionInput_\022\033\n\023transactionOutputId\030\001 " +
      "\001(\t\022!\n\004UTXO\030\002 \001(\0132\023.TransactionOutput_\"`" +
      "\n\022TransactionOutput_\022\n\n\002id\030\001 \001(\t\022\022\n\nreci" +
      "epient\030\002 \001(\t\022\r\n\005value\030\003 \001(\003\022\033\n\023parentTra" +
      "nsactionId\030\004 \001(\t\")\n\026BooleanSuccessRespon" +
      "se\022\017\n\007success\030\001 \001(\010\"O\n\010NodeInfo\022\030\n\004node\030" +
      "\001 \001(\0132\n.BasicNode\022\032\n\006sender\030\002 \001(\0132\n.Basi" +
      "cNode\022\r\n\005value\030\003 \001(\t\"4\n\006NodeID\022\016\n\006nodeID" +
      "\030\001 \001(\014\022\032\n\006sender\030\002 \001(\0132\n.BasicNode\"4\n\010No" +
      "deName\022\032\n\006sender\030\001 \001(\0132\n.BasicNode\022\014\n\004na" +
      "me\030\002 \001(\t\"|\n\tBasicNode\022\016\n\006nodeID\030\001 \001(\014\022\n\n" +
      "\002ip\030\002 \001(\t\022\014\n\004port\030\003 \001(\005\022\023\n\013client_name\030\004" +
      " \001(\t\022\021\n\tpublickey\030\005 \001(\t\022\017\n\007isMiner\030\006 \001(\010" +
      "\022\014\n\004name\030\007 \001(\t\"\021\n\017NodeInfoORValue2\302\003\n\003P2" +
      "P\022*\n\004PING\022\007.NodeID\032\027.BooleanSuccessRespo" +
      "nse\"\000\022.\n\005STORE\022\n.Key_Value\032\027.BooleanSucc" +
      "essResponse\"\000\022#\n\tFIND_NODE\022\007.NodeID\032\t.No" +
      "deInfo\"\0000\001\022\'\n\nFIND_VALUE\022\n.Key_Value\032\t.N" +
      "odeInfo\"\0000\001\022\033\n\004JOIN\022\007.NodeID\032\010.Kbucket\"\000" +
      "\022\'\n\rGETBlockChain\022\t.NodeInfo\032\007.Block_\"\0000" +
      "\001\022;\n\017SendTransaction\022\r.Transaction_\032\027.Bo" +
      "oleanSuccessResponse\"\000\022/\n\tSendBlock\022\007.Bl" +
      "ock_\032\027.BooleanSuccessResponse\"\000\022*\n\017GetNo" +
      "deFromName\022\t.NodeName\032\n.BasicNode\"\000\0221\n\023G" +
      "etMinersFromMaster\022\n.BasicNode\032\n.BasicNo" +
      "de\"\0000\001B!\n\026kademlia_public_ledgerB\002PLP\001\242\002" +
      "\000b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_Kbucket_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Kbucket_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Kbucket_descriptor,
        new java.lang.String[] { "Response", "Brothers", "I", });
    internal_static_Key_Value_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_Key_Value_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Key_Value_descriptor,
        new java.lang.String[] { "Key", "Kv", "Value", "Sender", });
    internal_static_Block__descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_Block__fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Block__descriptor,
        new java.lang.String[] { "Hash", "PreviousHash", "MerkleRoot", "Data", "Transactions", "Timestamp", "Nonce", "SenderNode", });
    internal_static_Transaction__descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_Transaction__fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Transaction__descriptor,
        new java.lang.String[] { "TransactionId", "Sender", "Reciepient", "Value", "Signature", "Inputs", "Outputs", "SenderNode", });
    internal_static_TransactionInput__descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_TransactionInput__fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_TransactionInput__descriptor,
        new java.lang.String[] { "TransactionOutputId", "UTXO", });
    internal_static_TransactionOutput__descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_TransactionOutput__fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_TransactionOutput__descriptor,
        new java.lang.String[] { "Id", "Reciepient", "Value", "ParentTransactionId", });
    internal_static_BooleanSuccessResponse_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_BooleanSuccessResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_BooleanSuccessResponse_descriptor,
        new java.lang.String[] { "Success", });
    internal_static_NodeInfo_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_NodeInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_NodeInfo_descriptor,
        new java.lang.String[] { "Node", "Sender", "Value", });
    internal_static_NodeID_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_NodeID_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_NodeID_descriptor,
        new java.lang.String[] { "NodeID", "Sender", });
    internal_static_NodeName_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_NodeName_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_NodeName_descriptor,
        new java.lang.String[] { "Sender", "Name", });
    internal_static_BasicNode_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_BasicNode_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_BasicNode_descriptor,
        new java.lang.String[] { "NodeID", "Ip", "Port", "ClientName", "Publickey", "IsMiner", "Name", });
    internal_static_NodeInfoORValue_descriptor =
      getDescriptor().getMessageTypes().get(11);
    internal_static_NodeInfoORValue_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_NodeInfoORValue_descriptor,
        new java.lang.String[] { });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
