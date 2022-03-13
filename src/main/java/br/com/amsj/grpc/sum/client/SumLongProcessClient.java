package br.com.amsj.grpc.sum.client;

import br.com.amsj.proto.sum.*;
import io.grpc.Deadline;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class SumLongProcessClient {

    public static void main(String[] args) {

        // creating channel
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 50061)
                .usePlaintext()
                .build();

        // creating stub
        SumServiceGrpc.SumServiceBlockingStub sumServiceGrpc = SumServiceGrpc.newBlockingStub(managedChannel);

                // creating request message
        SumLongProcessRequest sumLongProcessRequest = SumLongProcessRequest.newBuilder()
                .setFirstNumber(10)
                .setSecondNumber(15)
                .build();

        // Set the DeadLine smaller than the response time, the time should vary according by environment
        // making gRPC unary call and get the response
        SumLongProcessResponse sumLongProcessResponse = sumServiceGrpc.withDeadline(Deadline.after(800, TimeUnit.MILLISECONDS))
                .sumLongProcess(sumLongProcessRequest);

        int result = sumLongProcessResponse.getResult();

        System.out.println("Sum result is " + result);

        // Shutdown the request client
        System.out.println("Shutting down channel");
        managedChannel.shutdown();
    }
}
