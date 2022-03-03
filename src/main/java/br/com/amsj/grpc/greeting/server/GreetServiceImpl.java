package br.com.amsj.grpc.greeting.server;

import br.com.amsj.proto.greet.*;
import io.grpc.stub.StreamObserver;

import java.util.Date;

public class GreetServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {

    @Override
    public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {

        // Print something for each request
        System.out.println("Request received" + new Date());

        // extract request
        Greeting greeting = request.getGreeting();
        String firstName = greeting.getFirstName();

        // set response
        String result = "Hello " + firstName;
        GreetResponse response = GreetResponse.newBuilder()
                .setResult(result)
                .build();

        // send response
        responseObserver.onNext(response);

        // complete gRPC call
        responseObserver.onCompleted();
    }

    @Override
    public void greetManyTimes(GreetManyTimesRequest request, StreamObserver<GreetManyTimesResponse> responseObserver) {

        // Print something for each request
        System.out.println("Request received" + new Date());

        // extract request
        Greeting greeting = request.getGreeting();
        String firstName = greeting.getFirstName();

        // set response
        try {
            for(int i = 0; i < 10; i++) {
                String result = "Hello " + firstName + " response number: " + i;
                GreetManyTimesResponse response = GreetManyTimesResponse.newBuilder()
                        .setResult(result)
                        .build();
                // send response
                responseObserver.onNext(response);


                Thread.sleep(1000L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // complete gRPC call
            responseObserver.onCompleted();
        }
    }
}
