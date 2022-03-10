package br.com.amsj.grpc.findmaximum.client;

import br.com.amsj.grpc.calculator.client.CalculatorClient;
import br.com.amsj.grpc.findmaximum.FindMaximumRequest;
import br.com.amsj.grpc.findmaximum.FindMaximumResponse;
import br.com.amsj.grpc.findmaximum.FindMaximumServiceGrpc;
import br.com.amsj.proto.calculator.CalculatorAverageRequest;
import br.com.amsj.proto.calculator.CalculatorAverageResponse;
import br.com.amsj.proto.calculator.CalculatorServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class FindMaximumClient {

    public static void main(String[] args) {

        System.out.println("FindMaximumClient initializing...");
        new FindMaximumClient().run();
    }

    private void run() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50091)
                .usePlaintext()
                .build();

        CountDownLatch countDownLatch = new CountDownLatch(1);

        asyncMaximum(channel, countDownLatch);

        try {
            countDownLatch.await(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Shutdown the request client
        System.out.println("Shutting down channel");
        channel.shutdown();

    }

    private void asyncMaximum(ManagedChannel channel, CountDownLatch countDownLatch) {

        // Async Client
        FindMaximumServiceGrpc.FindMaximumServiceStub findMaximumServiceAsyncStub = FindMaximumServiceGrpc.newStub(channel);

        // Create the stream observer request
        StreamObserver<FindMaximumRequest> requestStreamObserver = findMaximumServiceAsyncStub.findMaximum(new StreamObserver<FindMaximumResponse>() {

            @Override
            public void onNext(FindMaximumResponse value) {
                System.out.println("Response for server :" + value.getGreater());
            }

            @Override
            public void onError(Throwable t) {
                countDownLatch.countDown();
            }

            @Override
            public void onCompleted() {
                System.out.println("Server is done sending data");
                countDownLatch.countDown();
            }
        });

        Arrays.asList(10, 1, 30, 5, 90, 100, 80, 3).forEach(
                number -> {
                    System.out.println("The current number is " + number);
                    requestStreamObserver.onNext(FindMaximumRequest.newBuilder()
                            .setNumber(number)
                            .build());
                    try {
                        Thread.sleep(400L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        requestStreamObserver.onCompleted();

        try {
            countDownLatch.await(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
