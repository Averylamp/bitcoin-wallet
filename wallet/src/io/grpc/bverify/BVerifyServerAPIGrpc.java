package io.grpc.bverify;

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
 **
 *	API Interface Exported by the Server
 * </pre>
 */

public final class BVerifyServerAPIGrpc {

  private BVerifyServerAPIGrpc() {}

  public static final String SERVICE_NAME = "api.BVerifyServerAPI";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @Deprecated // Use {@link #getForwardMethod()} instead.
  public static final io.grpc.MethodDescriptor<ForwardRequest,
      ForwardResponse> METHOD_FORWARD = getForwardMethodHelper();

  private static volatile io.grpc.MethodDescriptor<ForwardRequest,
      ForwardResponse> getForwardMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<ForwardRequest,
      ForwardResponse> getForwardMethod() {
    return getForwardMethodHelper();
  }

  private static io.grpc.MethodDescriptor<ForwardRequest,
      ForwardResponse> getForwardMethodHelper() {
    io.grpc.MethodDescriptor<ForwardRequest, ForwardResponse> getForwardMethod;
    if ((getForwardMethod = BVerifyServerAPIGrpc.getForwardMethod) == null) {
      synchronized (BVerifyServerAPIGrpc.class) {
        if ((getForwardMethod = BVerifyServerAPIGrpc.getForwardMethod) == null) {
          BVerifyServerAPIGrpc.getForwardMethod = getForwardMethod = 
              io.grpc.MethodDescriptor.<ForwardRequest, ForwardResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "api.BVerifyServerAPI", "Forward"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ForwardRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ForwardResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new BVerifyServerAPIMethodDescriptorSupplier("Forward"))
                  .build();
          }
        }
     }
     return getForwardMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @Deprecated // Use {@link #getGetForwardedMethod()} instead.
  public static final io.grpc.MethodDescriptor<GetForwardedRequest,
      io.grpc.bverify.GetForwardedResponse> METHOD_GET_FORWARDED = getGetForwardedMethodHelper();

  private static volatile io.grpc.MethodDescriptor<GetForwardedRequest,
      io.grpc.bverify.GetForwardedResponse> getGetForwardedMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<GetForwardedRequest,
      io.grpc.bverify.GetForwardedResponse> getGetForwardedMethod() {
    return getGetForwardedMethodHelper();
  }

  private static io.grpc.MethodDescriptor<GetForwardedRequest,
      io.grpc.bverify.GetForwardedResponse> getGetForwardedMethodHelper() {
    io.grpc.MethodDescriptor<GetForwardedRequest, io.grpc.bverify.GetForwardedResponse> getGetForwardedMethod;
    if ((getGetForwardedMethod = BVerifyServerAPIGrpc.getGetForwardedMethod) == null) {
      synchronized (BVerifyServerAPIGrpc.class) {
        if ((getGetForwardedMethod = BVerifyServerAPIGrpc.getGetForwardedMethod) == null) {
          BVerifyServerAPIGrpc.getGetForwardedMethod = getGetForwardedMethod = 
              io.grpc.MethodDescriptor.<GetForwardedRequest, io.grpc.bverify.GetForwardedResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "api.BVerifyServerAPI", "GetForwarded"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GetForwardedRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.bverify.GetForwardedResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new BVerifyServerAPIMethodDescriptorSupplier("GetForwarded"))
                  .build();
          }
        }
     }
     return getGetForwardedMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @Deprecated // Use {@link #getSubmitMethod()} instead.
  public static final io.grpc.MethodDescriptor<SubmitRequest,
      SubmitResponse> METHOD_SUBMIT = getSubmitMethodHelper();

  private static volatile io.grpc.MethodDescriptor<SubmitRequest,
      SubmitResponse> getSubmitMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<SubmitRequest,
      SubmitResponse> getSubmitMethod() {
    return getSubmitMethodHelper();
  }

  private static io.grpc.MethodDescriptor<SubmitRequest,
      SubmitResponse> getSubmitMethodHelper() {
    io.grpc.MethodDescriptor<SubmitRequest, SubmitResponse> getSubmitMethod;
    if ((getSubmitMethod = BVerifyServerAPIGrpc.getSubmitMethod) == null) {
      synchronized (BVerifyServerAPIGrpc.class) {
        if ((getSubmitMethod = BVerifyServerAPIGrpc.getSubmitMethod) == null) {
          BVerifyServerAPIGrpc.getSubmitMethod = getSubmitMethod = 
              io.grpc.MethodDescriptor.<SubmitRequest, SubmitResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "api.BVerifyServerAPI", "Submit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SubmitRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SubmitResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new BVerifyServerAPIMethodDescriptorSupplier("Submit"))
                  .build();
          }
        }
     }
     return getSubmitMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @Deprecated // Use {@link #getGetDataRequestMethod()} instead.
  public static final io.grpc.MethodDescriptor<DataRequest,
      DataResponse> METHOD_GET_DATA_REQUEST = getGetDataRequestMethodHelper();

  private static volatile io.grpc.MethodDescriptor<DataRequest,
      DataResponse> getGetDataRequestMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<DataRequest,
      DataResponse> getGetDataRequestMethod() {
    return getGetDataRequestMethodHelper();
  }

  private static io.grpc.MethodDescriptor<DataRequest,
      DataResponse> getGetDataRequestMethodHelper() {
    io.grpc.MethodDescriptor<DataRequest, DataResponse> getGetDataRequestMethod;
    if ((getGetDataRequestMethod = BVerifyServerAPIGrpc.getGetDataRequestMethod) == null) {
      synchronized (BVerifyServerAPIGrpc.class) {
        if ((getGetDataRequestMethod = BVerifyServerAPIGrpc.getGetDataRequestMethod) == null) {
          BVerifyServerAPIGrpc.getGetDataRequestMethod = getGetDataRequestMethod = 
              io.grpc.MethodDescriptor.<DataRequest, DataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "api.BVerifyServerAPI", "GetDataRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  DataRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  DataResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new BVerifyServerAPIMethodDescriptorSupplier("GetDataRequest"))
                  .build();
          }
        }
     }
     return getGetDataRequestMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @Deprecated // Use {@link #getGetAuthPathMethod()} instead.
  public static final io.grpc.MethodDescriptor<PathRequest,
      PathResponse> METHOD_GET_AUTH_PATH = getGetAuthPathMethodHelper();

  private static volatile io.grpc.MethodDescriptor<PathRequest,
      PathResponse> getGetAuthPathMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<PathRequest,
      PathResponse> getGetAuthPathMethod() {
    return getGetAuthPathMethodHelper();
  }

  private static io.grpc.MethodDescriptor<PathRequest,
      PathResponse> getGetAuthPathMethodHelper() {
    io.grpc.MethodDescriptor<PathRequest, PathResponse> getGetAuthPathMethod;
    if ((getGetAuthPathMethod = BVerifyServerAPIGrpc.getGetAuthPathMethod) == null) {
      synchronized (BVerifyServerAPIGrpc.class) {
        if ((getGetAuthPathMethod = BVerifyServerAPIGrpc.getGetAuthPathMethod) == null) {
          BVerifyServerAPIGrpc.getGetAuthPathMethod = getGetAuthPathMethod = 
              io.grpc.MethodDescriptor.<PathRequest, PathResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "api.BVerifyServerAPI", "GetAuthPath"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  PathRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  PathResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new BVerifyServerAPIMethodDescriptorSupplier("GetAuthPath"))
                  .build();
          }
        }
     }
     return getGetAuthPathMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @Deprecated // Use {@link #getGetCommitmentsMethod()} instead.
  public static final io.grpc.MethodDescriptor<CommitmentsRequest,
      CommitmentsResponse> METHOD_GET_COMMITMENTS = getGetCommitmentsMethodHelper();

  private static volatile io.grpc.MethodDescriptor<CommitmentsRequest,
      CommitmentsResponse> getGetCommitmentsMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<CommitmentsRequest,
      CommitmentsResponse> getGetCommitmentsMethod() {
    return getGetCommitmentsMethodHelper();
  }

  private static io.grpc.MethodDescriptor<CommitmentsRequest,
      CommitmentsResponse> getGetCommitmentsMethodHelper() {
    io.grpc.MethodDescriptor<CommitmentsRequest, CommitmentsResponse> getGetCommitmentsMethod;
    if ((getGetCommitmentsMethod = BVerifyServerAPIGrpc.getGetCommitmentsMethod) == null) {
      synchronized (BVerifyServerAPIGrpc.class) {
        if ((getGetCommitmentsMethod = BVerifyServerAPIGrpc.getGetCommitmentsMethod) == null) {
          BVerifyServerAPIGrpc.getGetCommitmentsMethod = getGetCommitmentsMethod = 
              io.grpc.MethodDescriptor.<CommitmentsRequest, CommitmentsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "api.BVerifyServerAPI", "GetCommitments"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CommitmentsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CommitmentsResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new BVerifyServerAPIMethodDescriptorSupplier("GetCommitments"))
                  .build();
          }
        }
     }
     return getGetCommitmentsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BVerifyServerAPIStub newStub(io.grpc.Channel channel) {
    return new BVerifyServerAPIStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BVerifyServerAPIBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new BVerifyServerAPIBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BVerifyServerAPIFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new BVerifyServerAPIFutureStub(channel);
  }

  /**
   * <pre>
   **
   *	API Interface Exported by the Server
   * </pre>
   */
  public static abstract class BVerifyServerAPIImplBase implements io.grpc.BindableService {

    /**
     */
    public void forward(ForwardRequest request,
        io.grpc.stub.StreamObserver<ForwardResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getForwardMethodHelper(), responseObserver);
    }

    /**
     */
    public void getForwarded(GetForwardedRequest request,
        io.grpc.stub.StreamObserver<io.grpc.bverify.GetForwardedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetForwardedMethodHelper(), responseObserver);
    }

    /**
     */
    public void submit(SubmitRequest request,
        io.grpc.stub.StreamObserver<SubmitResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSubmitMethodHelper(), responseObserver);
    }

    /**
     */
    public void getDataRequest(DataRequest request,
        io.grpc.stub.StreamObserver<DataResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDataRequestMethodHelper(), responseObserver);
    }

    /**
     */
    public void getAuthPath(PathRequest request,
        io.grpc.stub.StreamObserver<PathResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAuthPathMethodHelper(), responseObserver);
    }

    /**
     * <pre>
     * only really should be used if no Catena witnessing 
     * </pre>
     */
    public void getCommitments(CommitmentsRequest request,
        io.grpc.stub.StreamObserver<CommitmentsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetCommitmentsMethodHelper(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getForwardMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                ForwardRequest,
                ForwardResponse>(
                  this, METHODID_FORWARD)))
          .addMethod(
            getGetForwardedMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                GetForwardedRequest,
                io.grpc.bverify.GetForwardedResponse>(
                  this, METHODID_GET_FORWARDED)))
          .addMethod(
            getSubmitMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                SubmitRequest,
                SubmitResponse>(
                  this, METHODID_SUBMIT)))
          .addMethod(
            getGetDataRequestMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                DataRequest,
                DataResponse>(
                  this, METHODID_GET_DATA_REQUEST)))
          .addMethod(
            getGetAuthPathMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                PathRequest,
                PathResponse>(
                  this, METHODID_GET_AUTH_PATH)))
          .addMethod(
            getGetCommitmentsMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                CommitmentsRequest,
                CommitmentsResponse>(
                  this, METHODID_GET_COMMITMENTS)))
          .build();
    }
  }

  /**
   * <pre>
   **
   *	API Interface Exported by the Server
   * </pre>
   */
  public static final class BVerifyServerAPIStub extends io.grpc.stub.AbstractStub<BVerifyServerAPIStub> {
    private BVerifyServerAPIStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BVerifyServerAPIStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected BVerifyServerAPIStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BVerifyServerAPIStub(channel, callOptions);
    }

    /**
     */
    public void forward(ForwardRequest request,
        io.grpc.stub.StreamObserver<ForwardResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getForwardMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getForwarded(GetForwardedRequest request,
        io.grpc.stub.StreamObserver<io.grpc.bverify.GetForwardedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetForwardedMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void submit(SubmitRequest request,
        io.grpc.stub.StreamObserver<SubmitResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSubmitMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDataRequest(DataRequest request,
        io.grpc.stub.StreamObserver<DataResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDataRequestMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAuthPath(PathRequest request,
        io.grpc.stub.StreamObserver<PathResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAuthPathMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * only really should be used if no Catena witnessing 
     * </pre>
     */
    public void getCommitments(CommitmentsRequest request,
        io.grpc.stub.StreamObserver<CommitmentsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetCommitmentsMethodHelper(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   **
   *	API Interface Exported by the Server
   * </pre>
   */
  public static final class BVerifyServerAPIBlockingStub extends io.grpc.stub.AbstractStub<BVerifyServerAPIBlockingStub> {
    private BVerifyServerAPIBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BVerifyServerAPIBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected BVerifyServerAPIBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BVerifyServerAPIBlockingStub(channel, callOptions);
    }

    /**
     */
    public ForwardResponse forward(ForwardRequest request) {
      return blockingUnaryCall(
          getChannel(), getForwardMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public io.grpc.bverify.GetForwardedResponse getForwarded(GetForwardedRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetForwardedMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public SubmitResponse submit(SubmitRequest request) {
      return blockingUnaryCall(
          getChannel(), getSubmitMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public DataResponse getDataRequest(DataRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetDataRequestMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public PathResponse getAuthPath(PathRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAuthPathMethodHelper(), getCallOptions(), request);
    }

    /**
     * <pre>
     * only really should be used if no Catena witnessing 
     * </pre>
     */
    public CommitmentsResponse getCommitments(CommitmentsRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetCommitmentsMethodHelper(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   **
   *	API Interface Exported by the Server
   * </pre>
   */
  public static final class BVerifyServerAPIFutureStub extends io.grpc.stub.AbstractStub<BVerifyServerAPIFutureStub> {
    private BVerifyServerAPIFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BVerifyServerAPIFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected BVerifyServerAPIFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BVerifyServerAPIFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ForwardResponse> forward(
        ForwardRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getForwardMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.bverify.GetForwardedResponse> getForwarded(
        GetForwardedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetForwardedMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<SubmitResponse> submit(
        SubmitRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSubmitMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<DataResponse> getDataRequest(
        DataRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDataRequestMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<PathResponse> getAuthPath(
        PathRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAuthPathMethodHelper(), getCallOptions()), request);
    }

    /**
     * <pre>
     * only really should be used if no Catena witnessing 
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<CommitmentsResponse> getCommitments(
        CommitmentsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetCommitmentsMethodHelper(), getCallOptions()), request);
    }
  }

  private static final int METHODID_FORWARD = 0;
  private static final int METHODID_GET_FORWARDED = 1;
  private static final int METHODID_SUBMIT = 2;
  private static final int METHODID_GET_DATA_REQUEST = 3;
  private static final int METHODID_GET_AUTH_PATH = 4;
  private static final int METHODID_GET_COMMITMENTS = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final BVerifyServerAPIImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(BVerifyServerAPIImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FORWARD:
          serviceImpl.forward((ForwardRequest) request,
              (io.grpc.stub.StreamObserver<ForwardResponse>) responseObserver);
          break;
        case METHODID_GET_FORWARDED:
          serviceImpl.getForwarded((GetForwardedRequest) request,
              (io.grpc.stub.StreamObserver<io.grpc.bverify.GetForwardedResponse>) responseObserver);
          break;
        case METHODID_SUBMIT:
          serviceImpl.submit((SubmitRequest) request,
              (io.grpc.stub.StreamObserver<SubmitResponse>) responseObserver);
          break;
        case METHODID_GET_DATA_REQUEST:
          serviceImpl.getDataRequest((DataRequest) request,
              (io.grpc.stub.StreamObserver<DataResponse>) responseObserver);
          break;
        case METHODID_GET_AUTH_PATH:
          serviceImpl.getAuthPath((PathRequest) request,
              (io.grpc.stub.StreamObserver<PathResponse>) responseObserver);
          break;
        case METHODID_GET_COMMITMENTS:
          serviceImpl.getCommitments((CommitmentsRequest) request,
              (io.grpc.stub.StreamObserver<CommitmentsResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class BVerifyServerAPIBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BVerifyServerAPIBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return Api.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("BVerifyServerAPI");
    }
  }

  private static final class BVerifyServerAPIFileDescriptorSupplier
      extends BVerifyServerAPIBaseDescriptorSupplier {
    BVerifyServerAPIFileDescriptorSupplier() {}
  }

  private static final class BVerifyServerAPIMethodDescriptorSupplier
      extends BVerifyServerAPIBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    BVerifyServerAPIMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (BVerifyServerAPIGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BVerifyServerAPIFileDescriptorSupplier())
              .addMethod(getForwardMethodHelper())
              .addMethod(getGetForwardedMethodHelper())
              .addMethod(getSubmitMethodHelper())
              .addMethod(getGetDataRequestMethodHelper())
              .addMethod(getGetAuthPathMethodHelper())
              .addMethod(getGetCommitmentsMethodHelper())
              .build();
        }
      }
    }
    return result;
  }
}
