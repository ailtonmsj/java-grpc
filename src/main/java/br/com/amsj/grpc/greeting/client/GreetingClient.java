package br.com.amsj.grpc.greeting.client;

import br.com.amsj.proto.greet.GreetRequest;
import br.com.amsj.proto.greet.GreetResponse;
import br.com.amsj.proto.greet.GreetServiceGrpc;
import br.com.amsj.proto.greet.Greeting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {

    public static void main(String[] args) {

        System.out.println("Hello I'm a gRPC client");
        new GreetingClient().run();
    }

    private void run(){

        // config the channel
        ManagedChannel channel = ManagedChannelBuilder.forAddress( "localhost", 50051)
                .usePlaintext()
                .build();

        unaryGreet(channel);

        // Shutdown the request client
        System.out.println("Shutting down channel");
        channel.shutdown();

    }

    private void unaryGreet(ManagedChannel channel){

        System.out.println("Creating Stub");
        // create client service ( synchronous )
        GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);

        // create protocol buffer to send the message
        Greeting greeting = Greeting.newBuilder()
                .setFirstName("Test")
                .setLastName("da Silva")
                .build();

        GreetRequest greetRequest = GreetRequest.newBuilder()
                .setGreeting(greeting)
                .build();

        // send the message and get the response
        GreetResponse greetResponse = greetClient.greet(greetRequest);
        // print the response
        System.out.println(greetResponse.getResult());
    }
}
