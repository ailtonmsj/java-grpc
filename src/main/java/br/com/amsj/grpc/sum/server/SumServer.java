package br.com.amsj.grpc.sum.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;

import java.io.IOException;

public class SumServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Sum Server initializing...");

        Server server = ServerBuilder.forPort(50061)
                .addService(new SumServiceImpl())
                .addService(ProtoReflectionService.newInstance()) // for server reflection - Client Discovery
                .build();

        server.start();

        System.out.println("Sum Server started");

        Runtime.getRuntime().addShutdownHook(new Thread( () -> {
            System.out.println("Received Shutdown Request");
            server.shutdown();
            System.out.println("Successfully stopped the server");
        } ));

        server.awaitTermination();
    }
}
