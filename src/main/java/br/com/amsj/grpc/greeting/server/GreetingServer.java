package br.com.amsj.grpc.greeting.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;

import java.io.File;
import java.io.IOException;

public class GreetingServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Sum Server initializing...");

        // For secure Connections
        Server secureServer = ServerBuilder.forPort(50051)
                .addService(new GreetServiceImpl())
                .addService(ProtoReflectionService.newInstance()) // for server reflection - Client Discovery
                .useTransportSecurity(
                        // certificate for localhost (is possible generate for other URL using ssl/instructions.sh (Changing SERVER_CN)
                        new File("ssl/server.crt"),
                        new File("ssl/server.pem")
                )
                .build();

        secureServer.start();

        System.out.println("Sum Server started");

        Runtime.getRuntime().addShutdownHook(new Thread( () -> {
            System.out.println("Received Shutdown Request");
            secureServer.shutdown();
            System.out.println("Successfully stopped the server");
                } ));

        secureServer.awaitTermination();
    }
}
