package br.com.amsj.grpc.numberdecomposition.server;

import br.com.amsj.proto.numberdecomposition.NumberDecompositionRequest;
import br.com.amsj.proto.numberdecomposition.NumberDecompositionResponse;
import br.com.amsj.proto.numberdecomposition.NumberDecompositionServiceGrpc;
import io.grpc.stub.StreamObserver;

public class NumberDecompositionServiceImpl extends NumberDecompositionServiceGrpc.NumberDecompositionServiceImplBase {

    @Override
    public void numberDecomposition(NumberDecompositionRequest request, StreamObserver<NumberDecompositionResponse> responseObserver) {

        int number = request.getNumber();

        int divisor = 2;

        try {
            while (number > 1) {
                if (number % divisor == 0) {
                    number = number / divisor;

                    NumberDecompositionResponse numberDecompositionResponse = NumberDecompositionResponse
                            .newBuilder()
                            .setResult(divisor)
                            .build();

                    responseObserver.onNext(numberDecompositionResponse);
                    Thread.sleep(1000L);
                } else {
                    divisor++;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            responseObserver.onCompleted();
        }
    }
}
