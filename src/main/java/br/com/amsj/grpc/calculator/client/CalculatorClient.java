package br.com.amsj.grpc.calculator.client;

import br.com.amsj.proto.calculator.CalculatorAverageRequest;
import br.com.amsj.proto.calculator.CalculatorAverageResponse;
import br.com.amsj.proto.calculator.CalculatorServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CalculatorClient {

    public static void main(String[] args) {

        System.out.println("CalculatorClient initializing...");
        new CalculatorClient().run();

    }

    private void run() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50081)
                .usePlaintext()
                .build();

        CountDownLatch countDownLatch = new CountDownLatch(1);

        asyncAverage(channel, countDownLatch);

        try {
            countDownLatch.await(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Shutdown the request client
        System.out.println("Shutting down channel");
        channel.shutdown();

    }

    private void asyncAverage(ManagedChannel channel, CountDownLatch countDownLatch) {
        // Async Client
        CalculatorServiceGrpc.CalculatorServiceStub calculatorServiceAsyncStub = CalculatorServiceGrpc.newStub(channel);

        // Create the stream observer request
        StreamObserver<CalculatorAverageRequest> requestObserver = calculatorServiceAsyncStub.calculatorAverage(new StreamObserver<CalculatorAverageResponse>() {
            @Override
            public void onNext(CalculatorAverageResponse value) {
                // get the response from server
                System.out.println("Received response from server");
                System.out.println("Average: " + value.getAverage());
                // will be called only one
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("We have a problem...");
            }

            @Override
            public void onCompleted() {
                // the server is done - has send the data
                System.out.println("Server has complete the response");
                // called after onNext()
                countDownLatch.countDown();
            }
        });

        requestObserver.onNext(CalculatorAverageRequest.newBuilder()
                .setNumber(1)
                .build());

        System.out.println("Send first: " + 1);


        requestObserver.onNext(CalculatorAverageRequest.newBuilder()
                .setNumber(2)
                .build());

        System.out.println("Send second: " + 2);

        requestObserver.onNext(CalculatorAverageRequest.newBuilder()
                .setNumber(3)
                .build());

        System.out.println("Send third: " + 3);

        requestObserver.onNext(CalculatorAverageRequest.newBuilder()
                .setNumber(4)
                .build());

        System.out.println("Send fourth: " + 4);

        requestObserver.onCompleted();
    }
}
