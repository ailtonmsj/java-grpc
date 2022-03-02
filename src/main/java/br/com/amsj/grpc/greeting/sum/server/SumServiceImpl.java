package br.com.amsj.grpc.greeting.sum.server;

import br.com.amsj.proto.sum.SumRequest;
import br.com.amsj.proto.sum.SumResponse;
import br.com.amsj.proto.sum.SumServiceGrpc;
import io.grpc.stub.StreamObserver;

public class SumServiceImpl extends SumServiceGrpc.SumServiceImplBase {
    @Override
    public void sum(SumRequest request, StreamObserver<SumResponse> responseObserver) {

        int firstNumber = request.getFirstNumber();
        int secondNumber = request.getSecondNumber();

        int result = firstNumber + secondNumber;

        SumResponse sumResponse = SumResponse.newBuilder()
                .setResult(result)
                .build();

        responseObserver.onNext(sumResponse);

        responseObserver.onCompleted();

    }
}
