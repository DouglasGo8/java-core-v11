package com.packtpub.jdk.core.eleven.chapter07;

import com.packtpub.jdk.core.eleven.common.module.domain.CallableWorker;
import com.packtpub.jdk.core.eleven.common.module.model.Calculator;
import com.packtpub.jdk.core.eleven.common.module.model.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static java.lang.System.out;

public class App {


    @Test
    @Ignore
    public void howToThread() throws InterruptedException {

        Runnable thread1 = () -> IntStream.rangeClosed(1, 10).forEach(out::println);
        thread1.run();
        ;

        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    @Ignore
    public void howToSynchronization() throws InterruptedException {

        var calc = new Calculator();
        Runnable runnable = () -> out.println(IntStream.range(1, 40).mapToDouble(calc::calculate).sum());


        new Thread(runnable).start();
        new Thread(runnable).start();

        TimeUnit.SECONDS.sleep(2);

    }

    @Test
    @Ignore
    public void howToRemove() {

        // without CopyOnWriter... throws a UnsupportedOperationException
        var list = new CopyOnWriteArrayList<>(Arrays.asList("One", "Two", "Three"));
        list.removeIf("Two"::equals);
        out.println(list);

    }

    @Test
    @Ignore
    public void howToExecutorService() {

        int shutdownDelaySec = 1;
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Runnable runnable = () -> out.println("Worker One did the job");

        executor.execute(runnable);
        runnable = () -> out.println("Worker Two did the job");
        Future future = executor.submit(runnable);

        try {
            executor.shutdown();
            executor.awaitTermination(shutdownDelaySec, TimeUnit.SECONDS);
        } catch (Exception ex) {
            out.println("Caught around");
        } finally {
            if (!executor.isTerminated()) {
                if (!future.isDone() && !future.isCancelled()) {
                    out.println("Cancelling the task...");
                    future.cancel(true);
                }
            }
            out.println(executor.shutdownNow().size());
        }
    }

    @Test
    @Ignore
    public void howToCallableWorker() {

        @Getter
        @AllArgsConstructor
        final class CallableWorkerImpl implements CallableWorker<Result> {
            private int sleepSec;
            private String name;

            //
            @Override
            public Result call() throws Exception {
                Thread.sleep(this.sleepSec * 1000);
                return new Result(this.name, this.sleepSec, 42);
            }
        }
        var shutDownDelaySec = 1;
        List<Future<Result>> futures = null;
        ExecutorService executors = Executors.newSingleThreadExecutor();

        try {
            //
            var workers = List.of(new CallableWorkerImpl(2, "One"),
                    new CallableWorkerImpl(4, "Two"),
                    new CallableWorkerImpl(3, "Three"));
            //
            futures = executors.invokeAll(workers, shutDownDelaySec, TimeUnit.SECONDS);
            //
            executors.shutdown();
            out.println("Waiting for " + shutDownDelaySec);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (!executors.isTerminated()) {
                out.println("Terminating remaining tasks...");
                assert futures != null;
                futures.forEach(future -> {
                    try {
                        if (!future.isDone() && !future.isCancelled()) {
                            out.println("Cancelling tasks " + future.get(shutDownDelaySec, TimeUnit.SECONDS).getWorkerName());
                            future.cancel(true);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }
            executors.shutdownNow();
        }

        assert futures != null;
        futures.forEach(future -> {
            if (future.isCancelled()) {
                out.println("Worker is Down");
            } else {
                try {
                    out.println(future.get(shutDownDelaySec, TimeUnit.SECONDS));
                } catch (InterruptedException | ExecutionException | TimeoutException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    @Test
    public void howToForkJoin() {

        final class RecursiveTaskAverage extends RecursiveTask<Double> {
            @Override
            protected Double compute() {
                return 2.0d;
            }
        }

        var recursive = new RecursiveTaskAverage();

        recursive.fork();
        double result = recursive.join();
        out.println(result);
    }


}
