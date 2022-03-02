package br.com.amsj.grpc.greeting.sum.client;

import br.com.amsj.proto.sum.SumRequest;
import br.com.amsj.proto.sum.SumResponse;
import br.com.amsj.proto.sum.SumServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class SumClient {

    public static void main(String[] args) {

        // creating channel
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 50061)
                .usePlaintext()
                .build();

        // creating stub
        SumServiceGrpc.SumServiceBlockingStub sumServiceGrpc = SumServiceGrpc.newBlockingStub(managedChannel);

        // creating request message
        SumRequest sumRequest = SumRequest.newBuilder()
                        .setFirstNumber(10)
                                .setSecondNumber(15)
                                        .build();
        // making gRPC unary call and get the response
        SumResponse sumResponse = sumServiceGrpc.sum(sumRequest);
        int result = sumResponse.getResult();

        System.out.println("Sum result is " + result );

        // Shutdown the request client
        System.out.println("Shutting down channel");
        managedChannel.shutdown();
    }
}
