package com.packtpub.jdk.core.eleven.chapter08;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

public class App {


    @Test
    @SneakyThrows
    public void howToExtendsThread() {

        @Setter
        @AllArgsConstructor
        final class MyThread extends Thread {
            private String parameter;


            @Override
            @SneakyThrows
            public void run() {
                while (!"exit".equalsIgnoreCase(this.parameter)) {
                    out.println((super.isDaemon() ? "daemon" : " user") +
                            " thread " + this.getName() + "(id=" + this.getId() + ") parameter: " +
                            this.parameter);
                    TimeUnit.SECONDS.sleep(1);
                }
                out.println((super.isDaemon() ? "daemon" : " user") +
                        " thread " + this.getName() + "(id=" + this.getId() + ") parameter: " +
                        this.parameter);
            }
        }

        var thread1 = new MyThread("One");
        var thread2 = new MyThread("Two");

        thread2.setDaemon(true);
        thread1.start();
        thread2.start();

        TimeUnit.SECONDS.sleep(2);
        thread1.setParameter("exit");

        TimeUnit.SECONDS.sleep(2);

        out.println("Exit of Main Thread");

    }

    @Test
    @SneakyThrows
    public void howToExtendsRunnable() {

        @Setter
        @AllArgsConstructor
        final class MyRunnable implements Runnable {
            private String parameter, name;

            @Override
            @SneakyThrows
            public void run() {
                while (!"exit".equalsIgnoreCase(this.parameter)) {
                    out.println(" thread " + this.name + ", parameter: " + this.parameter);
                    TimeUnit.SECONDS.sleep(1);
                }
                out.println(" thread " + this.name + ", parameter: " + this.parameter);
            }
        }


        var runnable1 = new MyRunnable("keep", "One");
        var runnable2 = new MyRunnable("keep", "Two");

        var thread1 = new Thread(runnable1);
        var thread2 = new Thread(runnable2);

        thread2.setDaemon(true);

        thread1.start();
        thread2.start();

        TimeUnit.SECONDS.sleep(2);
        runnable1.setParameter("exit");

        TimeUnit.SECONDS.sleep(2);

        out.println("Exit of Main Thread");

    }


}
