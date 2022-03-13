package br.com.amsj.grpc.sum.server;

import br.com.amsj.proto.sum.*;
import io.grpc.Context;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.TimeUnit;

public class SumServiceImpl extends SumServiceGrpc.SumServiceImplBase {
    @Override
    public void sum(SumRequest request, StreamObserver<SumResponse> responseObserver) {

        int firstNumber = request.getFirstNumber();
        int secondNumber = request.getSecondNumber();

        int result = firstNumber + secondNumber;

        SumResponse sumResponse = SumResponse.newBuilder()
                .setResult(result)
                .build();

        responseObserver.onNext(sumResponse);

        responseObserver.onCompleted();

    }

    /**
     * Long process calculator
     * @param request
     * @param responseObserver
     */
    @Override
    public void sumLongProcess(SumLongProcessRequest request, StreamObserver<SumLongProcessResponse> responseObserver) {

        Context context = Context.current();

        int firstNumber = request.getFirstNumber();
        int secondNumber = request.getSecondNumber();

        System.out.println("Request received");

        for(int i=0; i < 3; i++){

            System.out.println("Time left on deadline: " + context.getDeadline().timeRemaining(TimeUnit.MILLISECONDS) + " ms");
            // Only continues the processing if the client is listening.
            if(!context.isCancelled()) {
                try {
                    Thread.sleep(200L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                System.out.println("Client not want the response anymore...");
                return;
            }
        }

        int result  = firstNumber + secondNumber;

        System.out.println("Sending response");

        responseObserver.onNext(SumLongProcessResponse.newBuilder().setResult(result).build());
        responseObserver.onCompleted();



    }

}
