package com.packtpub.jdk.core.eleven.chapter01;

import com.packtpub.jdk.core.eleven.common.module.domain.ISpeedModel;
import com.packtpub.jdk.core.eleven.common.module.domain.TempSubscriber;
import com.packtpub.jdk.core.eleven.common.module.domain.TempSubscription;
import com.packtpub.jdk.core.eleven.common.module.model.*;
import lombok.SneakyThrows;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.System.out;

/**
 * @author dbatista
 */
public class App {

    @Test
    @Ignore
    public void testJob01() {

        var a = 2;
        var b = 2;
        var c = 3;

        ISpeedModel s = (x, y, z) -> (x * 2) + y + z;

        out.println(s.getSpeedMph(a, b, c));

        new Vehicle(a, b, c).method(s);

    }

    @Test
    @Ignore
    public void testJob02() {

        var numbers = new Numbers();
        var list = Arrays.asList(12, 5, 45, 18, 33, 24, 40);

        // 1st ref
        // TODO Auto-generated method stub
        numbers.findSuchNumbers(list, numbers::isMoreThanFifty).forEach(out::println);

        // numbers.findSuchNumbers(list, (p1, p2) -> numbers.isMoreThanFifty(p1, p2));
        numbers.findSuchNumbers(list, numbers::isMoreThanFifty);

        numbers.supply(out::println, "1");

    }

    @Test
    @Ignore
    public void doWork() {

        final Runnable task1 = CommonTasks::myHeaders;
        final Runnable task2 = CommonTasks::myHeaders;

        task1.run();
        task2.run();
    }

    @Test
    @Ignore
    public void optionalThings() {

        final Optional<Integer> prize1 = Optional.empty();
        final Optional<Integer> prize2 = Optional.of(100);
        final Optional<Integer> prize3 = Optional.ofNullable(null);
        final Optional<Integer> prize4 = Optional.of(3341);

        int prized = prize1.or(() -> Optional.of(0)).get(); // zero if prize1 is null

        out.println(prize1.isPresent());
        out.println(prize1);


        prize2.ifPresent(CommonTasks::checkResultInt);

        prize4.ifPresentOrElse((n) -> out.println("Ok..." + n), () -> out.println("NOk"));

        out.println(prize2.orElseThrow(RuntimeException::new));

        out.println(prize2.isPresent());
        out.println(prize3.isPresent());
        out.println(prized);

    }

    @Test
    @Ignore
    public void compareThings() {
        int result = Objects.compare("a", "c", Comparator.naturalOrder()); // return -2
        out.println(result);
    }

    @Test
    public void howToCompletableWithTimeOut() throws Exception {


        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Hit");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).get(3, TimeUnit.SECONDS);

    }

    @Test
    public void howToLines() {
        var s = "1\n2\n3\n4";
        s.lines().forEach(out::println);
    }

    @Test
    @SneakyThrows
    public void howToCombineCompletableFuture() {

        final String brand = "No need exchange the Exchange ;)";

        CompletableFuture.supplyAsync(this::enrichCCFromOrig)
                .thenAcceptAsync((result) -> this.enrichCCOrigOrElseCategory(result, brand))
                .thenApplyAsync((Void) -> this.enrichCCFromDest())
                .thenAcceptAsync((result) -> this.enrichCCDestElseCategory(result, brand))
                .get(2099, TimeUnit.MILLISECONDS);
    }


    @Test
    public void testRangePrecision() {

        IntStream.rangeClosed(1, 500)
                .map(i -> ThreadLocalRandom.current().nextInt(0, 21))
                .forEach(out::println);

    }

    @Test
    @SneakyThrows
    public void testFuture() {

        /*var shop = new Shop("");
        var future = Executors.newCachedThreadPool().submit(() -> {
            //Thread.sleep(4000);
            return shop.getPrice("");
        });

        out.println(future.get(3, TimeUnit.SECONDS));
        System.out.println(shop.getAsyncPrice("").get());
        */
        var listOfShots = List.of(
                new Shop("product01"),
                new Shop("product02"),
                new Shop("product03"));

        long start = System.nanoTime();
        listOfShots
                //.stream() // 3020 msecs
                .parallelStream() // 1018 msecs
                //.map(s -> String.format("%s price is %.2f", s.getProductName(), s.getPrice())) // 1018 msecs
                .map(s -> CompletableFuture.supplyAsync(() -> String.format("%s is %.2f",
                        s.getProductName(), s.getPrice())))
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        long duration = (System.nanoTime() - start) / 1_000_000;

        out.println(duration + " in msecs");


    }

    @Test
    public void testOrTimeout() throws ExecutionException, InterruptedException {
        var other = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
               throw new RuntimeException(e);
            }
            return 2;

        });

        var v = CompletableFuture.supplyAsync(() -> {
            var v1 = new Random().nextInt(100);
            System.out.println(v1);
            return v1;
        }).thenCombine(other, Integer::sum).orTimeout(1, TimeUnit.SECONDS);

        out.println(v.get());
    }


    @Test
    public void getReactiveTemp() {
        Flow.Publisher<TempInfo> p = r -> r.onSubscribe(new TempSubscription(r, "New York"));

        p.subscribe(new TempSubscriber());
    }



    private int enrichCCFromOrig() {
        return new Random().nextInt(100);
    }

    private int enrichCCFromDest() {
        return new Random().nextInt(100);
    }

    private void enrichCCOrigOrElseCategory(int result, String brand) {
        //TimeUnit.SECONDS.sleep(1);
        System.out.println(result);
        if (result % 2 == 0) {
            // RestTemplate
            System.out.println("Ok will enrich with CC Category in Orig " + brand);
        } else {
            // Enrich Normal
            System.out.println("Ok will enrich CC in Orig");
        }
    }

    private void enrichCCDestElseCategory(int result, String brand) {
        System.out.println(result);
        if (result % 2 == 0) {
            // RestTemplate
            System.out.println("Ok will enrich with CC Category in Dest " + brand);
        } else {
            // Enrich Normal
            System.out.println("Ok will enrich CC in Dest ");
        }


    }


}