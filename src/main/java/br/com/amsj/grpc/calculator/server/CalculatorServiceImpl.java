package br.com.amsj.grpc.calculator.server;

import br.com.amsj.proto.calculator.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class CalculatorServiceImpl extends CalculatorServiceGrpc.CalculatorServiceImplBase {
    @Override
    public StreamObserver<CalculatorAverageRequest> calculatorAverage(StreamObserver<CalculatorAverageResponse> responseObserver) {

        StreamObserver<CalculatorAverageRequest> averageRequestStreamObserver = new StreamObserver<CalculatorAverageRequest>() {

            int total = 0;
            double count = 0;

            @Override
            public void onNext(CalculatorAverageRequest value) {
                // when client sends a message
                total += value.getNumber();
                count++;
            }

            @Override
            public void onError(Throwable t) {
                // when client send an error
            }

            // when we want tyo send the response ( responseObserver )
            @Override
            public void onCompleted() {
                // client is done
                double average = total / count;

                responseObserver.onNext(
                        CalculatorAverageResponse.newBuilder()
                                .setAverage(average)
                                .build()
                );
                responseObserver.onCompleted();


            }
        };

        return averageRequestStreamObserver;
    }

    @Override
    public void squareRoot(SquareRootRequest request, StreamObserver<SquareRootResponse> responseObserver) {

        Integer number = request.getNumber();

        if(number >= 0){
            double squareRoot = Math.sqrt(number);
            responseObserver.onNext(SquareRootResponse.newBuilder().
                    setSquareRoot(squareRoot)
                    .build());
            responseObserver.onCompleted();
        }else{
            responseObserver.onError(
                    Status.INVALID_ARGUMENT
                            .withDescription("The number must be positive ( >= 0 )")
                            .augmentDescription("Number sent: " + number)
                            .asRuntimeException());
        }

    }

}
