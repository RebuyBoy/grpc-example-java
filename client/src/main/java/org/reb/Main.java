package org.reb;

import ecommerce.ProductInfoGrpc;
import ecommerce.ProductInfoOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Main {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        ProductInfoGrpc.ProductInfoBlockingStub stub = ProductInfoGrpc.newBlockingStub(channel);

        ProductInfoOuterClass.ProductID productID = stub.addProduct(ProductInfoOuterClass.Product.newBuilder()
                .setName("Apple iPhone 11")
                .setDescription("Meet Apple iPhone 11. All-new dual-camera system with Ultra Wide and Night mode.")
                .build());

        System.out.println(productID.getValue());
    }
}
