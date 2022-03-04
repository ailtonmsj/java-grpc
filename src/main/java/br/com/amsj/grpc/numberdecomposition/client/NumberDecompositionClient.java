package br.com.amsj.grpc.numberdecomposition.client;

import br.com.amsj.proto.numberdecomposition.NumberDecompositionRequest;
import br.com.amsj.proto.numberdecomposition.NumberDecompositionServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class NumberDecompositionClient {

    public static void main(String[] args) {

        // creating channel
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 50071)
                .usePlaintext()
                .build();

        // creating stub
        NumberDecompositionServiceGrpc.NumberDecompositionServiceBlockingStub numberDecompositionService = NumberDecompositionServiceGrpc.newBlockingStub(managedChannel);

        // creating request message
        NumberDecompositionRequest numberDecompositionRequest = NumberDecompositionRequest.newBuilder()
                .setNumber(120)
                .build();

        // making gRPC unary call and get the response
        numberDecompositionService.numberDecomposition(numberDecompositionRequest)
                .forEachRemaining(numberDecompositionResponse -> {
                            System.out.println(numberDecompositionResponse.getResult());
                        });

        // Shutdown the request client
        System.out.println("Shutting down channel");
        managedChannel.shutdown();
    }
}
