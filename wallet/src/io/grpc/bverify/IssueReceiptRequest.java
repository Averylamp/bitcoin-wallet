// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protos/api.proto

package io.grpc.bverify;

/**
 * <pre>
 **
 * 	Request to Issue A Receipt along with 
 *	the required signatures
 * </pre>
 *
 * Protobuf type {@code api.IssueReceiptRequest}
 */
public  final class IssueReceiptRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:api.IssueReceiptRequest)
    IssueReceiptRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use IssueReceiptRequest.newBuilder() to construct.
  private IssueReceiptRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private IssueReceiptRequest() {
    signatureWarehouse_ = com.google.protobuf.ByteString.EMPTY;
    signatureDepositor_ = com.google.protobuf.ByteString.EMPTY;
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private IssueReceiptRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new NullPointerException();
    }
    int mutable_bitField0_ = 0;
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
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            Receipt.Builder subBuilder = null;
            if (receipt_ != null) {
              subBuilder = receipt_.toBuilder();
            }
            receipt_ = input.readMessage(Receipt.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(receipt_);
              receipt_ = subBuilder.buildPartial();
            }

            break;
          }
          case 18: {

            signatureWarehouse_ = input.readBytes();
            break;
          }
          case 26: {

            signatureDepositor_ = input.readBytes();
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
    return Api.internal_static_api_IssueReceiptRequest_descriptor;
  }

  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return Api.internal_static_api_IssueReceiptRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            IssueReceiptRequest.class, Builder.class);
  }

  public static final int RECEIPT_FIELD_NUMBER = 1;
  private Receipt receipt_;
  /**
   * <code>.api.Receipt receipt = 1;</code>
   */
  public boolean hasReceipt() {
    return receipt_ != null;
  }
  /**
   * <code>.api.Receipt receipt = 1;</code>
   */
  public Receipt getReceipt() {
    return receipt_ == null ? Receipt.getDefaultInstance() : receipt_;
  }
  /**
   * <code>.api.Receipt receipt = 1;</code>
   */
  public ReceiptOrBuilder getReceiptOrBuilder() {
    return getReceipt();
  }

  public static final int SIGNATURE_WAREHOUSE_FIELD_NUMBER = 2;
  private com.google.protobuf.ByteString signatureWarehouse_;
  /**
   * <code>bytes signature_warehouse = 2;</code>
   */
  public com.google.protobuf.ByteString getSignatureWarehouse() {
    return signatureWarehouse_;
  }

  public static final int SIGNATURE_DEPOSITOR_FIELD_NUMBER = 3;
  private com.google.protobuf.ByteString signatureDepositor_;
  /**
   * <code>bytes signature_depositor = 3;</code>
   */
  public com.google.protobuf.ByteString getSignatureDepositor() {
    return signatureDepositor_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (receipt_ != null) {
      output.writeMessage(1, getReceipt());
    }
    if (!signatureWarehouse_.isEmpty()) {
      output.writeBytes(2, signatureWarehouse_);
    }
    if (!signatureDepositor_.isEmpty()) {
      output.writeBytes(3, signatureDepositor_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (receipt_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getReceipt());
    }
    if (!signatureWarehouse_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, signatureWarehouse_);
    }
    if (!signatureDepositor_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(3, signatureDepositor_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof IssueReceiptRequest)) {
      return super.equals(obj);
    }
    IssueReceiptRequest other = (IssueReceiptRequest) obj;

    boolean result = true;
    result = result && (hasReceipt() == other.hasReceipt());
    if (hasReceipt()) {
      result = result && getReceipt()
          .equals(other.getReceipt());
    }
    result = result && getSignatureWarehouse()
        .equals(other.getSignatureWarehouse());
    result = result && getSignatureDepositor()
        .equals(other.getSignatureDepositor());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasReceipt()) {
      hash = (37 * hash) + RECEIPT_FIELD_NUMBER;
      hash = (53 * hash) + getReceipt().hashCode();
    }
    hash = (37 * hash) + SIGNATURE_WAREHOUSE_FIELD_NUMBER;
    hash = (53 * hash) + getSignatureWarehouse().hashCode();
    hash = (37 * hash) + SIGNATURE_DEPOSITOR_FIELD_NUMBER;
    hash = (53 * hash) + getSignatureDepositor().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static IssueReceiptRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static IssueReceiptRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static IssueReceiptRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static IssueReceiptRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static IssueReceiptRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static IssueReceiptRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static IssueReceiptRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static IssueReceiptRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static IssueReceiptRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static IssueReceiptRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static IssueReceiptRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static IssueReceiptRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(IssueReceiptRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(
      BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   **
   * 	Request to Issue A Receipt along with 
   *	the required signatures
   * </pre>
   *
   * Protobuf type {@code api.IssueReceiptRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:api.IssueReceiptRequest)
      io.grpc.bverify.IssueReceiptRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return Api.internal_static_api_IssueReceiptRequest_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return Api.internal_static_api_IssueReceiptRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              IssueReceiptRequest.class, Builder.class);
    }

    // Construct using io.grpc.bverify.IssueReceiptRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      if (receiptBuilder_ == null) {
        receipt_ = null;
      } else {
        receipt_ = null;
        receiptBuilder_ = null;
      }
      signatureWarehouse_ = com.google.protobuf.ByteString.EMPTY;

      signatureDepositor_ = com.google.protobuf.ByteString.EMPTY;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return Api.internal_static_api_IssueReceiptRequest_descriptor;
    }

    public IssueReceiptRequest getDefaultInstanceForType() {
      return IssueReceiptRequest.getDefaultInstance();
    }

    public IssueReceiptRequest build() {
      IssueReceiptRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public IssueReceiptRequest buildPartial() {
      IssueReceiptRequest result = new IssueReceiptRequest(this);
      if (receiptBuilder_ == null) {
        result.receipt_ = receipt_;
      } else {
        result.receipt_ = receiptBuilder_.build();
      }
      result.signatureWarehouse_ = signatureWarehouse_;
      result.signatureDepositor_ = signatureDepositor_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof IssueReceiptRequest) {
        return mergeFrom((IssueReceiptRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(IssueReceiptRequest other) {
      if (other == IssueReceiptRequest.getDefaultInstance()) return this;
      if (other.hasReceipt()) {
        mergeReceipt(other.getReceipt());
      }
      if (other.getSignatureWarehouse() != com.google.protobuf.ByteString.EMPTY) {
        setSignatureWarehouse(other.getSignatureWarehouse());
      }
      if (other.getSignatureDepositor() != com.google.protobuf.ByteString.EMPTY) {
        setSignatureDepositor(other.getSignatureDepositor());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      IssueReceiptRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (IssueReceiptRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private Receipt receipt_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        Receipt, Receipt.Builder, ReceiptOrBuilder> receiptBuilder_;
    /**
     * <code>.api.Receipt receipt = 1;</code>
     */
    public boolean hasReceipt() {
      return receiptBuilder_ != null || receipt_ != null;
    }
    /**
     * <code>.api.Receipt receipt = 1;</code>
     */
    public Receipt getReceipt() {
      if (receiptBuilder_ == null) {
        return receipt_ == null ? Receipt.getDefaultInstance() : receipt_;
      } else {
        return receiptBuilder_.getMessage();
      }
    }
    /**
     * <code>.api.Receipt receipt = 1;</code>
     */
    public Builder setReceipt(Receipt value) {
      if (receiptBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        receipt_ = value;
        onChanged();
      } else {
        receiptBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.api.Receipt receipt = 1;</code>
     */
    public Builder setReceipt(
        Receipt.Builder builderForValue) {
      if (receiptBuilder_ == null) {
        receipt_ = builderForValue.build();
        onChanged();
      } else {
        receiptBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.api.Receipt receipt = 1;</code>
     */
    public Builder mergeReceipt(Receipt value) {
      if (receiptBuilder_ == null) {
        if (receipt_ != null) {
          receipt_ =
            Receipt.newBuilder(receipt_).mergeFrom(value).buildPartial();
        } else {
          receipt_ = value;
        }
        onChanged();
      } else {
        receiptBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.api.Receipt receipt = 1;</code>
     */
    public Builder clearReceipt() {
      if (receiptBuilder_ == null) {
        receipt_ = null;
        onChanged();
      } else {
        receipt_ = null;
        receiptBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.api.Receipt receipt = 1;</code>
     */
    public Receipt.Builder getReceiptBuilder() {
      
      onChanged();
      return getReceiptFieldBuilder().getBuilder();
    }
    /**
     * <code>.api.Receipt receipt = 1;</code>
     */
    public ReceiptOrBuilder getReceiptOrBuilder() {
      if (receiptBuilder_ != null) {
        return receiptBuilder_.getMessageOrBuilder();
      } else {
        return receipt_ == null ?
            Receipt.getDefaultInstance() : receipt_;
      }
    }
    /**
     * <code>.api.Receipt receipt = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        Receipt, Receipt.Builder, ReceiptOrBuilder>
        getReceiptFieldBuilder() {
      if (receiptBuilder_ == null) {
        receiptBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            Receipt, Receipt.Builder, ReceiptOrBuilder>(
                getReceipt(),
                getParentForChildren(),
                isClean());
        receipt_ = null;
      }
      return receiptBuilder_;
    }

    private com.google.protobuf.ByteString signatureWarehouse_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes signature_warehouse = 2;</code>
     */
    public com.google.protobuf.ByteString getSignatureWarehouse() {
      return signatureWarehouse_;
    }
    /**
     * <code>bytes signature_warehouse = 2;</code>
     */
    public Builder setSignatureWarehouse(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      signatureWarehouse_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes signature_warehouse = 2;</code>
     */
    public Builder clearSignatureWarehouse() {
      
      signatureWarehouse_ = getDefaultInstance().getSignatureWarehouse();
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString signatureDepositor_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes signature_depositor = 3;</code>
     */
    public com.google.protobuf.ByteString getSignatureDepositor() {
      return signatureDepositor_;
    }
    /**
     * <code>bytes signature_depositor = 3;</code>
     */
    public Builder setSignatureDepositor(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      signatureDepositor_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes signature_depositor = 3;</code>
     */
    public Builder clearSignatureDepositor() {
      
      signatureDepositor_ = getDefaultInstance().getSignatureDepositor();
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:api.IssueReceiptRequest)
  }

  // @@protoc_insertion_point(class_scope:api.IssueReceiptRequest)
  private static final IssueReceiptRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new IssueReceiptRequest();
  }

  public static IssueReceiptRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<IssueReceiptRequest>
      PARSER = new com.google.protobuf.AbstractParser<IssueReceiptRequest>() {
    public IssueReceiptRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new IssueReceiptRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<IssueReceiptRequest> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<IssueReceiptRequest> getParserForType() {
    return PARSER;
  }

  public IssueReceiptRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

