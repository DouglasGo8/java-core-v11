package com.packtpub.jdk.core.eleven.chapter07;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.lang.System.out;

public class App {


    @Test
    public void howToThread() throws InterruptedException {

        Runnable thread1 = () -> IntStream.rangeClosed(1, 10).forEach(out::println);
        thread1.run();;

        TimeUnit.SECONDS.sleep(1);
    }

}
