package com.packtpub.jdk.core.eleven.chapter04;

import org.junit.Test;

import java.util.function.*;

import static java.lang.System.out;

public class App {


    @Test
    public void howToDoFunctional() {

        Function<Integer, Double> fun = (p1) -> p1 * 10.0;
        out.println(fun.apply(2));
        Consumer<String> consumer = out::println;
        consumer.accept("Java Rocks!");
        Supplier<String> supplier = () -> "Java Rocks!";
        out.println(supplier.get());
        Predicate<Double> predicate = (p) -> p > 2.0d;
        out.println(predicate.test(2d));

        Consumer<String> consumer1 = s -> out.println(String.format("Hi, %s", s));

        consumer1.accept("Douglas");

    }

    @Test
    public void howToDoMethodRef() {

        Supplier<String> sayHi = this::sayHello;

        out.println(sayHi.get());

        var a = 3;
        var b = 3;

        atariConsumer(a, b, (v, u) -> {
            out.println(v);
            out.println(u);
        });
    }

    private String sayHello() {
        return String.format("Hi, %s", "dbatista");
    }

    private void atariConsumer(int ff, int x, BiConsumer<String, Integer> output) {

        output.accept(String.format("There here come %d-%d", ff, x), ff + x);
    }
}
