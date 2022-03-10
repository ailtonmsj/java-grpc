package br.com.amsj.grpc.findmaximum.server;

import br.com.amsj.grpc.findmaximum.FindMaximumRequest;
import br.com.amsj.grpc.findmaximum.FindMaximumResponse;
import br.com.amsj.grpc.findmaximum.FindMaximumServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class FindMaximumServiceImpl extends FindMaximumServiceGrpc.FindMaximumServiceImplBase {

    @Override
    public StreamObserver<FindMaximumRequest> findMaximum(StreamObserver<FindMaximumResponse> responseObserver) {

        StreamObserver<FindMaximumRequest> streamObserverRequest = new StreamObserver<FindMaximumRequest>() {

            int currentMaximum = 0;

            @Override
            public void onNext(FindMaximumRequest value) {
                currentMaximum = getGreater(currentMaximum, value.getNumber());

                FindMaximumResponse findMaximumResponse = FindMaximumResponse.newBuilder()
                        .setGreater(currentMaximum)
                        .build();

                responseObserver.onNext(findMaximumResponse);

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                FindMaximumResponse findMaximumResponse = FindMaximumResponse.newBuilder()
                        .setGreater(currentMaximum)
                        .build();

                responseObserver.onCompleted();
            }
        };



        return streamObserverRequest;
    }

    private Integer getGreater(Integer currentMaximum, Integer number) {

        if(number != null && number > currentMaximum) {
            return number;
        }
        return currentMaximum;
    }
}
