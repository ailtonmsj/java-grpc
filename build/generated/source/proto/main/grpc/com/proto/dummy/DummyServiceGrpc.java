package com.proto.dummy;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.44.1)",
    comments = "Source: dummy/dummy.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class DummyServiceGrpc {

  private DummyServiceGrpc() {}

  public static final String SERVICE_NAME = "dummy.DummyService";

  // Static method descriptors that strictly reflect the proto.
  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DummyServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DummyServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DummyServiceStub>() {
        @java.lang.Override
        public DummyServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DummyServiceStub(channel, callOptions);
        }
      };
    return DummyServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DummyServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DummyServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DummyServiceBlockingStub>() {
        @java.lang.Override
        public DummyServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DummyServiceBlockingStub(channel, callOptions);
        }
      };
    return DummyServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DummyServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DummyServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DummyServiceFutureStub>() {
        @java.lang.Override
        public DummyServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DummyServiceFutureStub(channel, callOptions);
        }
      };
    return DummyServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class DummyServiceImplBase implements io.grpc.BindableService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .build();
    }
  }

  /**
   */
  public static final class DummyServiceStub extends io.grpc.stub.AbstractAsyncStub<DummyServiceStub> {
    private DummyServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DummyServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DummyServiceStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class DummyServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<DummyServiceBlockingStub> {
    private DummyServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DummyServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DummyServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class DummyServiceFutureStub extends io.grpc.stub.AbstractFutureStub<DummyServiceFutureStub> {
    private DummyServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DummyServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DummyServiceFutureStub(channel, callOptions);
    }
  }


  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DummyServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DummyServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class DummyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DummyServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.proto.dummy.Dummy.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DummyService");
    }
  }

  private static final class DummyServiceFileDescriptorSupplier
      extends DummyServiceBaseDescriptorSupplier {
    DummyServiceFileDescriptorSupplier() {}
  }

  private static final class DummyServiceMethodDescriptorSupplier
      extends DummyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DummyServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (DummyServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DummyServiceFileDescriptorSupplier())
              .build();
        }
      }
    }
    return result;
  }
}
