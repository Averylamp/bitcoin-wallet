// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protos/api.proto

package io.grpc.bverify;

/**
 * Protobuf type {@code api.GetForwardedResponse}
 */
public  final class GetForwardedResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:api.GetForwardedResponse)
    GetForwardedResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GetForwardedResponse.newBuilder() to construct.
  private GetForwardedResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetForwardedResponse() {
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetForwardedResponse(
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
            IssueReceiptRequest.Builder subBuilder = null;
            if (requestCase_ == 1) {
              subBuilder = ((IssueReceiptRequest) request_).toBuilder();
            }
            request_ =
                input.readMessage(IssueReceiptRequest.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom((IssueReceiptRequest) request_);
              request_ = subBuilder.buildPartial();
            }
            requestCase_ = 1;
            break;
          }
          case 18: {
            TransferReceiptRequest.Builder subBuilder = null;
            if (requestCase_ == 2) {
              subBuilder = ((TransferReceiptRequest) request_).toBuilder();
            }
            request_ =
                input.readMessage(TransferReceiptRequest.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom((TransferReceiptRequest) request_);
              request_ = subBuilder.buildPartial();
            }
            requestCase_ = 2;
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
    return Api.internal_static_api_GetForwardedResponse_descriptor;
  }

  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return Api.internal_static_api_GetForwardedResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            GetForwardedResponse.class, Builder.class);
  }

  private int requestCase_ = 0;
  private Object request_;
  public enum RequestCase
      implements com.google.protobuf.Internal.EnumLite {
    ISSUE_RECEIPT(1),
    TRANSFER_RECEIPT(2),
    REQUEST_NOT_SET(0);
    private final int value;
    private RequestCase(int value) {
      this.value = value;
    }
    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @Deprecated
    public static RequestCase valueOf(int value) {
      return forNumber(value);
    }

    public static RequestCase forNumber(int value) {
      switch (value) {
        case 1: return ISSUE_RECEIPT;
        case 2: return TRANSFER_RECEIPT;
        case 0: return REQUEST_NOT_SET;
        default: return null;
      }
    }
    public int getNumber() {
      return this.value;
    }
  };

  public RequestCase
  getRequestCase() {
    return RequestCase.forNumber(
        requestCase_);
  }

  public static final int ISSUE_RECEIPT_FIELD_NUMBER = 1;
  /**
   * <code>.api.IssueReceiptRequest issue_receipt = 1;</code>
   */
  public boolean hasIssueReceipt() {
    return requestCase_ == 1;
  }
  /**
   * <code>.api.IssueReceiptRequest issue_receipt = 1;</code>
   */
  public IssueReceiptRequest getIssueReceipt() {
    if (requestCase_ == 1) {
       return (IssueReceiptRequest) request_;
    }
    return IssueReceiptRequest.getDefaultInstance();
  }
  /**
   * <code>.api.IssueReceiptRequest issue_receipt = 1;</code>
   */
  public IssueReceiptRequestOrBuilder getIssueReceiptOrBuilder() {
    if (requestCase_ == 1) {
       return (IssueReceiptRequest) request_;
    }
    return IssueReceiptRequest.getDefaultInstance();
  }

  public static final int TRANSFER_RECEIPT_FIELD_NUMBER = 2;
  /**
   * <code>.api.TransferReceiptRequest transfer_receipt = 2;</code>
   */
  public boolean hasTransferReceipt() {
    return requestCase_ == 2;
  }
  /**
   * <code>.api.TransferReceiptRequest transfer_receipt = 2;</code>
   */
  public TransferReceiptRequest getTransferReceipt() {
    if (requestCase_ == 2) {
       return (TransferReceiptRequest) request_;
    }
    return TransferReceiptRequest.getDefaultInstance();
  }
  /**
   * <code>.api.TransferReceiptRequest transfer_receipt = 2;</code>
   */
  public TransferReceiptRequestOrBuilder getTransferReceiptOrBuilder() {
    if (requestCase_ == 2) {
       return (TransferReceiptRequest) request_;
    }
    return TransferReceiptRequest.getDefaultInstance();
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
    if (requestCase_ == 1) {
      output.writeMessage(1, (IssueReceiptRequest) request_);
    }
    if (requestCase_ == 2) {
      output.writeMessage(2, (TransferReceiptRequest) request_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (requestCase_ == 1) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, (IssueReceiptRequest) request_);
    }
    if (requestCase_ == 2) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, (TransferReceiptRequest) request_);
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
    if (!(obj instanceof GetForwardedResponse)) {
      return super.equals(obj);
    }
    GetForwardedResponse other = (GetForwardedResponse) obj;

    boolean result = true;
    result = result && getRequestCase().equals(
        other.getRequestCase());
    if (!result) return false;
    switch (requestCase_) {
      case 1:
        result = result && getIssueReceipt()
            .equals(other.getIssueReceipt());
        break;
      case 2:
        result = result && getTransferReceipt()
            .equals(other.getTransferReceipt());
        break;
      case 0:
      default:
    }
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
    switch (requestCase_) {
      case 1:
        hash = (37 * hash) + ISSUE_RECEIPT_FIELD_NUMBER;
        hash = (53 * hash) + getIssueReceipt().hashCode();
        break;
      case 2:
        hash = (37 * hash) + TRANSFER_RECEIPT_FIELD_NUMBER;
        hash = (53 * hash) + getTransferReceipt().hashCode();
        break;
      case 0:
      default:
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static GetForwardedResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static GetForwardedResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static GetForwardedResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static GetForwardedResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static GetForwardedResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static GetForwardedResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static GetForwardedResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static GetForwardedResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static GetForwardedResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static GetForwardedResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static GetForwardedResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static GetForwardedResponse parseFrom(
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
  public static Builder newBuilder(GetForwardedResponse prototype) {
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
   * Protobuf type {@code api.GetForwardedResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:api.GetForwardedResponse)
      io.grpc.bverify.GetForwardedResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return Api.internal_static_api_GetForwardedResponse_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return Api.internal_static_api_GetForwardedResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              GetForwardedResponse.class, Builder.class);
    }

    // Construct using io.grpc.bverify.GetForwardedResponse.newBuilder()
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
      requestCase_ = 0;
      request_ = null;
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return Api.internal_static_api_GetForwardedResponse_descriptor;
    }

    public GetForwardedResponse getDefaultInstanceForType() {
      return GetForwardedResponse.getDefaultInstance();
    }

    public GetForwardedResponse build() {
      GetForwardedResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public GetForwardedResponse buildPartial() {
      GetForwardedResponse result = new GetForwardedResponse(this);
      if (requestCase_ == 1) {
        if (issueReceiptBuilder_ == null) {
          result.request_ = request_;
        } else {
          result.request_ = issueReceiptBuilder_.build();
        }
      }
      if (requestCase_ == 2) {
        if (transferReceiptBuilder_ == null) {
          result.request_ = request_;
        } else {
          result.request_ = transferReceiptBuilder_.build();
        }
      }
      result.requestCase_ = requestCase_;
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
      if (other instanceof GetForwardedResponse) {
        return mergeFrom((GetForwardedResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(GetForwardedResponse other) {
      if (other == GetForwardedResponse.getDefaultInstance()) return this;
      switch (other.getRequestCase()) {
        case ISSUE_RECEIPT: {
          mergeIssueReceipt(other.getIssueReceipt());
          break;
        }
        case TRANSFER_RECEIPT: {
          mergeTransferReceipt(other.getTransferReceipt());
          break;
        }
        case REQUEST_NOT_SET: {
          break;
        }
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
      GetForwardedResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (GetForwardedResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int requestCase_ = 0;
    private Object request_;
    public RequestCase
        getRequestCase() {
      return RequestCase.forNumber(
          requestCase_);
    }

    public Builder clearRequest() {
      requestCase_ = 0;
      request_ = null;
      onChanged();
      return this;
    }


    private com.google.protobuf.SingleFieldBuilderV3<
        IssueReceiptRequest, IssueReceiptRequest.Builder, IssueReceiptRequestOrBuilder> issueReceiptBuilder_;
    /**
     * <code>.api.IssueReceiptRequest issue_receipt = 1;</code>
     */
    public boolean hasIssueReceipt() {
      return requestCase_ == 1;
    }
    /**
     * <code>.api.IssueReceiptRequest issue_receipt = 1;</code>
     */
    public IssueReceiptRequest getIssueReceipt() {
      if (issueReceiptBuilder_ == null) {
        if (requestCase_ == 1) {
          return (IssueReceiptRequest) request_;
        }
        return IssueReceiptRequest.getDefaultInstance();
      } else {
        if (requestCase_ == 1) {
          return issueReceiptBuilder_.getMessage();
        }
        return IssueReceiptRequest.getDefaultInstance();
      }
    }
    /**
     * <code>.api.IssueReceiptRequest issue_receipt = 1;</code>
     */
    public Builder setIssueReceipt(IssueReceiptRequest value) {
      if (issueReceiptBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        request_ = value;
        onChanged();
      } else {
        issueReceiptBuilder_.setMessage(value);
      }
      requestCase_ = 1;
      return this;
    }
    /**
     * <code>.api.IssueReceiptRequest issue_receipt = 1;</code>
     */
    public Builder setIssueReceipt(
        IssueReceiptRequest.Builder builderForValue) {
      if (issueReceiptBuilder_ == null) {
        request_ = builderForValue.build();
        onChanged();
      } else {
        issueReceiptBuilder_.setMessage(builderForValue.build());
      }
      requestCase_ = 1;
      return this;
    }
    /**
     * <code>.api.IssueReceiptRequest issue_receipt = 1;</code>
     */
    public Builder mergeIssueReceipt(IssueReceiptRequest value) {
      if (issueReceiptBuilder_ == null) {
        if (requestCase_ == 1 &&
            request_ != IssueReceiptRequest.getDefaultInstance()) {
          request_ = IssueReceiptRequest.newBuilder((IssueReceiptRequest) request_)
              .mergeFrom(value).buildPartial();
        } else {
          request_ = value;
        }
        onChanged();
      } else {
        if (requestCase_ == 1) {
          issueReceiptBuilder_.mergeFrom(value);
        }
        issueReceiptBuilder_.setMessage(value);
      }
      requestCase_ = 1;
      return this;
    }
    /**
     * <code>.api.IssueReceiptRequest issue_receipt = 1;</code>
     */
    public Builder clearIssueReceipt() {
      if (issueReceiptBuilder_ == null) {
        if (requestCase_ == 1) {
          requestCase_ = 0;
          request_ = null;
          onChanged();
        }
      } else {
        if (requestCase_ == 1) {
          requestCase_ = 0;
          request_ = null;
        }
        issueReceiptBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>.api.IssueReceiptRequest issue_receipt = 1;</code>
     */
    public IssueReceiptRequest.Builder getIssueReceiptBuilder() {
      return getIssueReceiptFieldBuilder().getBuilder();
    }
    /**
     * <code>.api.IssueReceiptRequest issue_receipt = 1;</code>
     */
    public IssueReceiptRequestOrBuilder getIssueReceiptOrBuilder() {
      if ((requestCase_ == 1) && (issueReceiptBuilder_ != null)) {
        return issueReceiptBuilder_.getMessageOrBuilder();
      } else {
        if (requestCase_ == 1) {
          return (IssueReceiptRequest) request_;
        }
        return IssueReceiptRequest.getDefaultInstance();
      }
    }
    /**
     * <code>.api.IssueReceiptRequest issue_receipt = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        IssueReceiptRequest, IssueReceiptRequest.Builder, IssueReceiptRequestOrBuilder>
        getIssueReceiptFieldBuilder() {
      if (issueReceiptBuilder_ == null) {
        if (!(requestCase_ == 1)) {
          request_ = IssueReceiptRequest.getDefaultInstance();
        }
        issueReceiptBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            IssueReceiptRequest, IssueReceiptRequest.Builder, IssueReceiptRequestOrBuilder>(
                (IssueReceiptRequest) request_,
                getParentForChildren(),
                isClean());
        request_ = null;
      }
      requestCase_ = 1;
      onChanged();;
      return issueReceiptBuilder_;
    }

    private com.google.protobuf.SingleFieldBuilderV3<
        TransferReceiptRequest, TransferReceiptRequest.Builder, TransferReceiptRequestOrBuilder> transferReceiptBuilder_;
    /**
     * <code>.api.TransferReceiptRequest transfer_receipt = 2;</code>
     */
    public boolean hasTransferReceipt() {
      return requestCase_ == 2;
    }
    /**
     * <code>.api.TransferReceiptRequest transfer_receipt = 2;</code>
     */
    public TransferReceiptRequest getTransferReceipt() {
      if (transferReceiptBuilder_ == null) {
        if (requestCase_ == 2) {
          return (TransferReceiptRequest) request_;
        }
        return TransferReceiptRequest.getDefaultInstance();
      } else {
        if (requestCase_ == 2) {
          return transferReceiptBuilder_.getMessage();
        }
        return TransferReceiptRequest.getDefaultInstance();
      }
    }
    /**
     * <code>.api.TransferReceiptRequest transfer_receipt = 2;</code>
     */
    public Builder setTransferReceipt(TransferReceiptRequest value) {
      if (transferReceiptBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        request_ = value;
        onChanged();
      } else {
        transferReceiptBuilder_.setMessage(value);
      }
      requestCase_ = 2;
      return this;
    }
    /**
     * <code>.api.TransferReceiptRequest transfer_receipt = 2;</code>
     */
    public Builder setTransferReceipt(
        TransferReceiptRequest.Builder builderForValue) {
      if (transferReceiptBuilder_ == null) {
        request_ = builderForValue.build();
        onChanged();
      } else {
        transferReceiptBuilder_.setMessage(builderForValue.build());
      }
      requestCase_ = 2;
      return this;
    }
    /**
     * <code>.api.TransferReceiptRequest transfer_receipt = 2;</code>
     */
    public Builder mergeTransferReceipt(TransferReceiptRequest value) {
      if (transferReceiptBuilder_ == null) {
        if (requestCase_ == 2 &&
            request_ != TransferReceiptRequest.getDefaultInstance()) {
          request_ = TransferReceiptRequest.newBuilder((TransferReceiptRequest) request_)
              .mergeFrom(value).buildPartial();
        } else {
          request_ = value;
        }
        onChanged();
      } else {
        if (requestCase_ == 2) {
          transferReceiptBuilder_.mergeFrom(value);
        }
        transferReceiptBuilder_.setMessage(value);
      }
      requestCase_ = 2;
      return this;
    }
    /**
     * <code>.api.TransferReceiptRequest transfer_receipt = 2;</code>
     */
    public Builder clearTransferReceipt() {
      if (transferReceiptBuilder_ == null) {
        if (requestCase_ == 2) {
          requestCase_ = 0;
          request_ = null;
          onChanged();
        }
      } else {
        if (requestCase_ == 2) {
          requestCase_ = 0;
          request_ = null;
        }
        transferReceiptBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>.api.TransferReceiptRequest transfer_receipt = 2;</code>
     */
    public TransferReceiptRequest.Builder getTransferReceiptBuilder() {
      return getTransferReceiptFieldBuilder().getBuilder();
    }
    /**
     * <code>.api.TransferReceiptRequest transfer_receipt = 2;</code>
     */
    public TransferReceiptRequestOrBuilder getTransferReceiptOrBuilder() {
      if ((requestCase_ == 2) && (transferReceiptBuilder_ != null)) {
        return transferReceiptBuilder_.getMessageOrBuilder();
      } else {
        if (requestCase_ == 2) {
          return (TransferReceiptRequest) request_;
        }
        return TransferReceiptRequest.getDefaultInstance();
      }
    }
    /**
     * <code>.api.TransferReceiptRequest transfer_receipt = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        TransferReceiptRequest, TransferReceiptRequest.Builder, TransferReceiptRequestOrBuilder>
        getTransferReceiptFieldBuilder() {
      if (transferReceiptBuilder_ == null) {
        if (!(requestCase_ == 2)) {
          request_ = TransferReceiptRequest.getDefaultInstance();
        }
        transferReceiptBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            TransferReceiptRequest, TransferReceiptRequest.Builder, TransferReceiptRequestOrBuilder>(
                (TransferReceiptRequest) request_,
                getParentForChildren(),
                isClean());
        request_ = null;
      }
      requestCase_ = 2;
      onChanged();;
      return transferReceiptBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:api.GetForwardedResponse)
  }

  // @@protoc_insertion_point(class_scope:api.GetForwardedResponse)
  private static final GetForwardedResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new GetForwardedResponse();
  }

  public static GetForwardedResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetForwardedResponse>
      PARSER = new com.google.protobuf.AbstractParser<GetForwardedResponse>() {
    public GetForwardedResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GetForwardedResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetForwardedResponse> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<GetForwardedResponse> getParserForType() {
    return PARSER;
  }

  public GetForwardedResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

