
package com.packtpub.jdk.core.eleven.common.module.model;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Numbers {

    public boolean isMoreThanFifty(int p1, int p2) {
        return (p1 + p2) > 50;
    }

    public Stream<Integer> findSuchNumbers(List<Integer> l, BiPredicate<Integer, Integer> p) {

        return l.stream().filter(i -> p.test(i, i + 10));
    }

    public String myHeaders(String token, String apiKey) {
        return String.format("...token[%s]...apiKey[%s]...", token, apiKey);

    }

    public void supply(Consumer<String> p, String p1) {
        p.accept(p1);
    }

}