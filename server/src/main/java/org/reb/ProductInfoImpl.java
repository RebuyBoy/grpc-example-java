package org.reb;

import ecommerce.ProductInfoGrpc;
import ecommerce.ProductInfoOuterClass;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProductInfoImpl extends ProductInfoGrpc.ProductInfoImplBase {
    private static final Map<String, ProductInfoOuterClass.Product> productMap = new HashMap<>();

    @Override
    public void getProduct(ProductInfoOuterClass.ProductID request, StreamObserver<ProductInfoOuterClass.Product> responseObserver) {
        String productId = request.getValue();
        if (productMap.containsKey(productId)) {
            responseObserver.onNext(productMap.get(productId));
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new StatusException(Status.NOT_FOUND));
        }
    }

    @Override
    public void addProduct(ProductInfoOuterClass.Product request,
                           io.grpc.stub.StreamObserver<ProductInfoOuterClass.ProductID> responseObserver) {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        System.out.println("Adding product with ID: " + randomUUIDString);
        productMap.put(randomUUIDString, request);
        ProductInfoOuterClass.ProductID productID = ProductInfoOuterClass.ProductID.newBuilder().setValue(randomUUIDString).build();
        responseObserver.onNext(productID);
        responseObserver.onCompleted();
    }

}
