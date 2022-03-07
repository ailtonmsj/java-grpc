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

        List<Integer> numbers = new ArrayList<>();

        StreamObserver<FindMaximumRequest> streamObserverRequest = new StreamObserver<FindMaximumRequest>() {

            @Override
            public void onNext(FindMaximumRequest value) {
                numbers.add(value.getNumber());
                Integer greater = getGreater(numbers);

                FindMaximumResponse findMaximumResponse = FindMaximumResponse.newBuilder()
                        .setGreater(greater)
                        .build();

                responseObserver.onNext(findMaximumResponse);

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };



        return streamObserverRequest;
    }

    private Integer getGreater(List<Integer> numbers) {

        Integer greater = 0;
        if(numbers != null && numbers.size() > 0) {
            greater = Integer.MIN_VALUE;
            for(Integer number : numbers) {
                if (number > greater) {
                    greater = number;
                }
            }
        }
        return greater;
    }
}
