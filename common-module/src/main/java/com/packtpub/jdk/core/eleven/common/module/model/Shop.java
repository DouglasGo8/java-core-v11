package com.packtpub.jdk.core.eleven.common.module.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Getter
@AllArgsConstructor
public class Shop {

    private final String productName;

    @SneakyThrows
    public double getPrice() {
        TimeUnit.SECONDS.sleep(1);
        return new Random().nextDouble();
    }


    @SneakyThrows
    public double getPrice(String whichProduct) {
        TimeUnit.SECONDS.sleep(2);
        return new Random().nextDouble();
    }

    public Future<Double> getAsyncPrice(String whichProduct) {

        var futurePrice = new CompletableFuture<Double>();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                futurePrice.complete(new Random().nextDouble());
            } catch (Exception e) {
                futurePrice.completeExceptionally(e);
            }
        }).start();

        return futurePrice;
    }

    public CompletionStage<Double> getCompletableWithSuccess(String whichProduct) {
        return CompletableFuture.supplyAsync(() -> {
            //TimeUnit.SECONDS.sleep(2);
            return new Random().nextDouble();

        });
    }


}
