package com.packtpub.jdk.core.eleven.chapter15;

import io.reactivex.Observable;
import lombok.SneakyThrows;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.out;


public class App {


    @Test
    public void howToGetAverage() {


        var numbers = IntStream.range(1, 11).boxed().collect(Collectors.toList());


        this.getAverage(numbers.stream()); // 1s
        this.getAverage(numbers.parallelStream()); // 200ms


    }

    @Test
    public void howToGetWithCompletableFuture() {

        var pool = Executors.newFixedThreadPool(3);
        var numbers = IntStream.range(1, 11).boxed().collect(Collectors.toList());

        var list = numbers // List<CompletableFuture<Double>>
                .stream()
                .map(id -> CompletableFuture.supplyAsync(() -> this.get(id), pool))
                .collect(Collectors.toList());

        var start = LocalTime.now();

        double a = list.stream().mapToDouble(CompletableFuture::join).average().orElse(0);

        out.println((Math.round(a * 100.) / 100.) + " in " +
                Duration.between(start, LocalTime.now()).toMillis() + " ms ");
    }

    @Test
    public void howToGetWithRxJava() {

        double a = IntStream.rangeClosed(1, 5)
                .filter(i -> i % 2 == 0)
                .mapToDouble(Double::valueOf)
                .map(Math::sqrt)
                .sum();

        out.println(a);

        var observable = Observable.<Double>range(1, 5)
                .filter(i -> i % 2 == 0)
                //.doOnNext(out::println)
                .map(Math::sqrt)
                .cache();

        observable.reduce(Double::sum).subscribe(out::println);
        observable.reduce(Double::sum).map(r -> r /2).subscribe(out::println);


    }

    private void getAverage(Stream<Integer> ids) {

        var start = LocalTime.now();
        var a = ids.mapToDouble(this::get).average().orElse(0);

        out.println((Math.round(a * 100.) / 100.) + " in " +
                Duration.between(start, LocalTime.now()).toMillis() + " ms ");
    }

    @SneakyThrows
    private double get(int id) {
        TimeUnit.MILLISECONDS.sleep(100);
        return id * Math.random();
    }


}
