package com.packtpub.jdk.core.eleven.chapter01;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiPredicate;


import com.packtpub.jdk.core.eleven.common.module.domain.ISpeedModel;
import com.packtpub.jdk.core.eleven.common.module.model.CommonTasks;
import com.packtpub.jdk.core.eleven.common.module.model.Numbers;
import com.packtpub.jdk.core.eleven.common.module.model.Vehicle;
import org.junit.Ignore;
import org.junit.Test;

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
        numbers.findSuchNumbers(list, new BiPredicate<Integer, Integer>() {

            @Override
            public boolean test(Integer arg0, Integer arg1) {
                // TODO Auto-generated method stub
                return numbers.isMoreThanFifty(arg0, arg1);
            }
        }).forEach(out::println);

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

}