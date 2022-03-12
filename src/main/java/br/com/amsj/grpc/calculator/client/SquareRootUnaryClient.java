package br.com.amsj.grpc.calculator.client;

import br.com.amsj.proto.calculator.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class SquareRootUnaryClient {

    public static void main(String[] args) {

        System.out.println("SquareRootUnaryClient initializing...");
        new SquareRootUnaryClient().run();
    }

    private void run() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50081)
                .usePlaintext()
                .build();

        // To pass on the execution
        //Integer number = 9;

        // To get an error on the execution
        Integer number = -9;

        try {
            double squareRoot = syncSquareRoot(channel, number);
            System.out.println("Square Root: " + squareRoot);
        } catch (RuntimeException re) {
            re.printStackTrace();
        }finally {
            // Shutdown the request client
            System.out.println("Shutting down channel");
            channel.shutdown();
        }
    }

    private double syncSquareRoot(ManagedChannel channel, Integer number) {

        CalculatorServiceGrpc.CalculatorServiceBlockingStub calculatorServiceBlockingStub = CalculatorServiceGrpc.newBlockingStub(channel);

        SquareRootResponse squareRootResponse = calculatorServiceBlockingStub.squareRoot(SquareRootRequest.newBuilder()
                .setNumber(number)
                .build());

        return squareRootResponse.getSquareRoot();
    }
}


