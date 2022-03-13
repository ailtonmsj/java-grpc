package br.com.amsj.grpc.greeting.client;

import br.com.amsj.proto.greet.*;
import io.grpc.*;

import java.io.File;
import java.io.IOException;

public class GreetingManyTimesClient {

    public static void main(String[] args) throws IOException {

        System.out.println("Hello I'm a gRPC client");
        new GreetingManyTimesClient().run();

    }

    private void run() throws IOException {

        // create the channel credentials ( pointing to the ca.crt )
        ChannelCredentials channelCredentials = TlsChannelCredentials.newBuilder().trustManager(new File("ssl/ca.crt")).build();

        // config the channel
        ManagedChannel secureChannel = Grpc.newChannelBuilderForAddress("localhost", 50051, channelCredentials)
                .build();

        clientStreaming(secureChannel);

        // Shutdown the request client
        System.out.println("Shutting down channel");
        secureChannel.shutdown();
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
