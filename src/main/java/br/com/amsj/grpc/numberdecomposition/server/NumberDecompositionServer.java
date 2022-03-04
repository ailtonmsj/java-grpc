package br.com.amsj.grpc.numberdecomposition.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class NumberDecompositionServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Number Decomposition Server initializing...");

        Server server = ServerBuilder.forPort(50071)
                .addService(new NumberDecompositionServiceImpl())
                .build();

        server.start();

        System.out.println("Number Decomposition Server started");

        Runtime.getRuntime().addShutdownHook(new Thread( () -> {
            System.out.println("Received Shutdown Request");
            server.shutdown();
            System.out.println("Successfully stopped the server");
        } ));

        server.awaitTermination();
    }
}
