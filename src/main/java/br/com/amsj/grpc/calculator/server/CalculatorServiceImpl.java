package br.com.amsj.grpc.calculator.server;

import br.com.amsj.proto.calculator.CalculatorAverageRequest;
import br.com.amsj.proto.calculator.CalculatorAverageResponse;
import br.com.amsj.proto.calculator.CalculatorServiceGrpc;
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
}
