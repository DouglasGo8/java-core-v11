package com.packtpub.jdk.core.eleven.chapter14;

import com.packtpub.jdk.core.eleven.common.module.model.Person;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.out;


public class App {

    @Test
    public void howToInitWithStreams() {

        String[] strings = {"1", "3", "2"};

        Stream.empty().forEach(out::println); // prints nothing
        Stream.of(1, 3, 5, 2, 22, 34, 13).sorted().forEach(out::println);
        Stream.of(strings).sorted().forEach(out::println);
    }

    @Test
    public void howToFlatMapWithinStream() {

        Stream.of(Stream.of(1, 3, 4), Stream.of(4, 5))
                .flatMap(e -> e).forEach(out::println);
    }

    @Test
    public void howToStreamIterate() {

        Stream.iterate(1, i -> ++i)
                .limit(10)
                .forEach(out::println);

        Stream.iterate(1, i -> i < 10, i -> ++i)
                .limit(10)
                .forEach(out::println);

        Stream.generate(() -> new Random().nextInt(60)).limit(6).forEach(out::println);
    }

    @Test
    public void howToFilteringStreams() {

        Stream.of("4", "3", "3", "2").distinct().forEach(out::println);
        Stream.of("1", "2", "3", "4", "5").skip(3).forEach(out::println);
        Stream.of("1", "2", "3", "4", "5").filter(s -> Objects.equals(s, "2")).forEach(out::println);
        Stream.of("1", "2", "3", "4", "5").dropWhile(s -> Integer.parseInt(s) < 3).forEach(out::println);
        Stream.of("1", "2", "3", "4", "5").takeWhile(s -> Integer.parseInt(s) < 3).forEach(out::println);
    }

    @Test
    public void howToMappingStreams() {

        Stream.of("1", "2", "3", "4", "5").mapToInt(Integer::valueOf).forEach(out::println);
        Stream.of("1", "2", "3", "4", "5").mapToLong(Integer::valueOf).forEach(out::println);
        Stream.of("1", "2", "3", "4", "5").mapToDouble(Integer::valueOf).forEach(out::println);
        Stream.of("1", "2", "3", "4", "5")
                .mapToInt(Integer::valueOf)
                .flatMap(n -> IntStream.iterate(1, i -> i < n, i -> ++i))
                .forEach(out::println);
    }

    @Test
    public void howToSortedStream() {

        Stream.of("2", "4", "12", "1")
                .map(Integer::valueOf)
                .sorted(Comparator.reverseOrder()).forEach(out::println);
    }


    @Test
    public void howToTerminate() {

        var path = Paths.get("src/main/resources/person.csv");

        try (Stream<String> lines = Files.newBufferedReader(path).lines()) {
            lines.filter(s -> s.contains("J")).forEach(out::println);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Test
    public void howToClassOptional() {

        var result = Stream.of("1", "2", "3", "4", "5");

        result.filter("42"::equals).findAny().or(() -> Optional.of("Not found")).get();
        result.filter("42"::equals).findAny().orElse("Not found");
        result.filter("53"::equals).findAny().orElseGet(() -> "Not found");


    }

    @Test
    public void howToReduce() {
        var persons = Stream.of(new Person(23, "Bob"), new Person(33, "Jim"),
                new Person(40, "Douglas"), new Person(27, "Bill"));

        out.println(persons.reduce((p1, p2) -> p1.getAge() > p2.getAge() ? p1 : p2).orElse(null));
        //persons.map(Person::getName).reduce((p1, p2)-> p1 + ", " + p2).orElse(null);
    }

    @Test
    public void howToCollectors() {

        out.println(Stream.of("a", "b", "c").collect(Collectors.toList()));
        out.println(Stream.of("a", "a", "c").collect(Collectors.toSet()));

        var list = List.of(new Person(23, "Bob"), new Person(33, "Jim"),
                new Person(40, "Douglas"), new Person(27, "Bill"));

        list.stream().collect(Collectors.toMap(p -> p.getName() + "-" + p.getAge(), p -> p))
                .forEach((k, v) -> out.println(k + v));

        list.stream().collect(Collectors.toCollection(HashSet::new)).forEach(out::println);


        var list1 = List.of("a", "b", "c", "d");

        out.println(list1.stream().collect(Collectors.joining()));
        out.println(list1.stream().collect(Collectors.joining(", ")));
    }

    @Test
    public void howToSummarizing() {

        var person = List.of(new Person(23, "Bob"), new Person(33, "Jim"),
                new Person(40, "Douglas"), new Person(27, "Bill"));

        out.println(person.stream().collect(Collectors.summingInt(Person::getAge)));
        IntSummaryStatistics p = person.stream().collect(Collectors.summarizingInt(Person::getAge));
        out.println(p);
        out.println(p.getMax());

        var partitioned = person.stream().collect(Collectors.partitioningBy(p1 -> p1.getAge() > 27));
        out.println(partitioned);

    }

    @Test
    public void howToGroupingBy() {

        var person = List.of(new Person(23, "Bob"), new Person(33, "Jim"),
                new Person(40, "Douglas"), new Person(27, "Bill"));

        out.println(person.stream().collect(Collectors.groupingBy(Person::getAge)));


    }


}