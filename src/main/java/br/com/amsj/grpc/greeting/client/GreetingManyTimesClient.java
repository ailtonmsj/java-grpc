package br.com.amsj.grpc.greeting.client;

import br.com.amsj.proto.greet.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingManyTimesClient {

    public static void main(String[] args) {

        System.out.println("Hello I'm a gRPC client");
        new GreetingManyTimesClient().run();

    }

    private void run(){
        // config the channel
        ManagedChannel channel = ManagedChannelBuilder.forAddress( "localhost", 50051)
                .usePlaintext()
                .build();

        clientStreaming(channel);

        // Shutdown the request client
        System.out.println("Shutting down channel");
        channel.shutdown();

    }

    private void clientStreaming(ManagedChannel channel) {

        System.out.println("Creating Stub");
        // create client service ( synchronous )
        GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);

        // create protocol buffer to send the message
        Greeting greeting = Greeting.newBuilder()
                .setFirstName("Test")
                .setLastName("da Silva")
                .build();

        GreetManyTimesRequest greetManyTimesRequest = GreetManyTimesRequest.newBuilder()
                .setGreeting(greeting)
                .build();

        // send the message and get the response (stream)
        greetClient.greetManyTimes(greetManyTimesRequest)
                .forEachRemaining(greetManyTimesResponse -> {
                    System.out.println(greetManyTimesResponse.getResult());
                });
    }
}
