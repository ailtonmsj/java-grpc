package br.com.amsj.grpc.greeting.server;

import br.com.amsj.proto.greet.GreetRequest;
import br.com.amsj.proto.greet.GreetResponse;
import br.com.amsj.proto.greet.GreetServiceGrpc;
import br.com.amsj.proto.greet.Greeting;
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
}
