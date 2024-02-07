package org.reb;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class Main {
    private static final int PORT = 50051;

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(PORT)
                .addService(new ProductInfoImpl())
                .build()
                .start();

        System.out.println("Server started, listening on " + PORT);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down gRPC server since JVM is shutting down");
            if (server != null) {
                server.shutdown();
            }
            System.out.println("Server shut down");
        }));
        server.awaitTermination();
    }
}
