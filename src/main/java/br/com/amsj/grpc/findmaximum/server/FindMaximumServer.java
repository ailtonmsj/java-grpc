package br.com.amsj.grpc.findmaximum.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class FindMaximumServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("FindMaximumServer initializing");

        Server server = ServerBuilder.forPort(50091)
                .addService(new FindMaximumServiceImpl())
                .build();

        server.start();

        System.out.println("FindMaximumServer started");

        Runtime.getRuntime().addShutdownHook(new Thread( () -> {
            System.out.println("Received Shutdown Request");
            server.shutdown();
            System.out.println("Successfully stopped the FindMaximumServer");
        } ));

        server.awaitTermination();
    }
}
