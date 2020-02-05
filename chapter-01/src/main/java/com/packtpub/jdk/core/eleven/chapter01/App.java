package com.packtpub.jdk.core.eleven.chapter01;

import com.packtpub.jdk.core.eleven.common.module.domain.ISpeedModel;
import com.packtpub.jdk.core.eleven.common.module.model.CommonTasks;
import com.packtpub.jdk.core.eleven.common.module.model.Numbers;
import com.packtpub.jdk.core.eleven.common.module.model.Vehicle;
import lombok.SneakyThrows;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiPredicate;

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

        var task1 = CompletableFuture.supplyAsync(this::enrichCCFromOrig)
                .thenAcceptAsync(this::enrichCCOrigOrElseCategory);


        var task2 = CompletableFuture.supplyAsync(this::enrichCCFromDest)
                .thenAcceptAsync(this::enrichCCDestElseCategory);

        task1.get(1, TimeUnit.SECONDS);
        task2.get(1, TimeUnit.SECONDS);

    }


    private int enrichCCFromOrig() {
        return new Random().nextInt(100);
    }

    private int enrichCCFromDest() {
        return new Random().nextInt(100);
    }

    private void enrichCCOrigOrElseCategory(int result) {
        //System.out.println(result);
        if (result % 2 == 0) {
            // RestTemplate
            System.out.println("Ok will enrich with CC Category in Orig");
        } else {
            // Enrich Normal
            System.out.println("Ok will enrich CC in Orig");
        }
    }

    private void enrichCCDestElseCategory(int result) {
        //System.out.println(result);
        if (result % 2 == 0) {
            // RestTemplate
            System.out.println("Ok will enrich with CC Category in Dest");
        } else {
            // Enrich Normal
            System.out.println("Ok will enrich CC in Dest");
        }
    }


}