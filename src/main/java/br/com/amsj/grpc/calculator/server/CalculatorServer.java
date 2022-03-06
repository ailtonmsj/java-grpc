package br.com.amsj.grpc.calculator.server;

import br.com.amsj.grpc.sum.server.SumServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class CalculatorServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("CalculatorServer initializing");

        Server server = ServerBuilder.forPort(50081)
                .addService(new CalculatorServiceImpl())
                .build();

        server.start();

        System.out.println("Calculator Server started");

        Runtime.getRuntime().addShutdownHook(new Thread( () -> {
            System.out.println("Received Shutdown Request");
            server.shutdown();
            System.out.println("Successfully stopped the Calculator Server");
        } ));

        server.awaitTermination();
    }
}
