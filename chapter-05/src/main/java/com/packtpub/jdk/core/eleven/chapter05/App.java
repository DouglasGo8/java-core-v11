package com.packtpub.jdk.core.eleven.chapter05;

import com.packtpub.jdk.core.eleven.common.module.model.Car;
import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.out;
import static java.util.Map.entry;

public class App {


    interface IFoo {
        static String method() {
            return "Hi";
        }
    }

    @Test
    @Ignore
    public void howToImmutableCollections() {

        List.of("This", "is", "a", "Array").forEach(out::println);
        Set.of("This", "is", "a", "Array").forEach(out::println);
        Map.of(1, "Hi", 2, "There").forEach((k, v) -> out.println(String.format("Key: %d - Value: %s", k, v)));
        Map.ofEntries(entry(1, "Java"), entry(2, "Rocks!!!"))
                .forEach((k, v) -> out.println(String.format("Key: %d - Value: %s", k, v)));

    }

    @Test
    @Ignore
    public void howToStream() {

        final String[] array = new String[]{"One", "Two", "Three"};

        Stream.of(array).sorted().forEach(out::println);

        /*
         * IntStream.range(1, 100) .skip(5) .takeWhile(i -> i < 100)
         * .forEach(out::println);
         */

        final String file = "F:/.camel/data/inbox/addresses.csv";

        try (Stream<String> stream = Files.lines(Paths.get(file)).limit(3)) {
            stream.parallel().filter(l -> l.length() > 0).forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stream.of("That", "is", "a", "Stream.of(literals)").filter(s -> s.contains("Th"))
                .flatMap(s -> Pattern.compile("(?!^)").splitAsStream(s)).forEach(out::println);

        out.println(Stream.of(1, 2, 3).map(i -> i.toString() + ",").reduce("", (a, b) -> a + b));

        // Stream.of(1, 2, 3)
        // .map(Object::toString)
        // .toArray(String[]::new);

        IntStream.rangeClosed(1, 3).forEach(out::println);
    }

    @Test
    @Ignore
    public void howToStreamTwo() {
        Arrays.stream(new int[]{1, 2, 2}, 1, 2).forEach(out::println);
    }


    @Test
    public void howToHashMap() {

        Map<String, Car> map = Stream.of(new Car(2016, "Jetta", "TSI", 150.30d),
                new Car(2019, "Tiguan", "R-LINE", 165.32d))
                // Behavior changes with key static String value
                .collect(HashMap::new, (k, v) -> k.put(v.getModel(), v), Map::putAll);

        out.println(map);

        Map<String, Integer> map2 = Stream.of(new Car(2016, "Jetta", "TSI", 150.30d),
                new Car(2019, "Tiguan", "R-LINE", 165.32d)).collect(HashMap::new,
                (k, v) -> k.put(v.getModel(), v.getYear()), Map::putAll);

        out.println(map2);

        out.println(IFoo.method());

    }

    @Test
    public void howToGroupingBy() {


        var car = new Car();

        out.println(car.ofCars().collect(Collectors.groupingBy(Car::getBrand)));
        out.println(car.ofCars().collect(Collectors.groupingBy(Car::getYear)));
        out.println(car.ofCars().collect(Collectors.groupingBy(Car::getYear, Collectors.toSet())));
        out.println(car.ofCars().collect(Collectors.groupingBy(Car::getBrand, Collectors.counting())));
        out.println(car.ofCars().collect(Collectors.groupingBy(Car::getBrand,
                Collectors.mapping(Car::getYear, Collectors.toList()))));
        out.println(car.ofCars().collect(Collectors.partitioningBy(c -> c.getBrand().contains("B"))));

    }

    @Test
    public void howToWithStrings() {

        out.println("".isEmpty()); // returns true
        out.println("ab1".matches("[a-z+]")); // return false

        var s1 = "aBc";
        var s2 = "abc";

        out.println(s1.equals(s2)); // return false
        out.println(s2.equalsIgnoreCase(s1)); // returns true
    }

    @Test
    public void hwoToWithStringsTwo() {

        out.println("123".repeat(2));
        out.println("".isBlank()); // returns true
        out.println(" ".isBlank()); // retuns true
        out.println("  a ".isBlank()); // returns false

        var sp = "   abc  ";

        out.println("'" + sp + "'"); // returns ' abc '
        out.println("'" + sp.stripLeading() + "'"); // return 'abc '
        out.println("'" + sp.stripTrailing() + "'"); // return ' abc'
        out.println("'" + sp.strip() + "'"); // return 'abc'
    }

    @Test
    public void howToCommonLang() {

        out.println(StringUtils.trim("' x'"));
        out.println(StringUtils.trimToNull(null)); // prints null
        out.println(StringUtils.trimToEmpty(null)); // prints ''

    }

    @Test
    public void howToInputStream() {

        // - InputStream
        //  + ByteArrayInputStream
        //  + FileInputStream
        //  + ObjectInputStream
        //  + PipedInputStream
        //  + SequenceInputStream
        //  + FilterInputStream

        var b1 = new byte[]{2, 3, 4};

        try (ByteArrayInputStream bais = new ByteArrayInputStream(b1)) {

            int data = bais.read();
            // int data
            // while ((data = bais.read()) != -1)
            while (data != -1) {
                out.println(data + " ");
                data = bais.read();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void howToFileInputStream() {
        var file = "D:/.camel/data/inbox/hello.txt";
        // Maven resources dir access
        // /src/main/resources
        // try (InputStream is = InputOutStream.class.getResourceAsStream("/hello.txt"))
        try (FileInputStream fis = new FileInputStream(file)) {
            int data;
            while ((data = fis.read()) != -1) {
                out.print(((char) data) + " ");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void howToPipeInputStream() {

        try {

            PipedInputStream pis = new PipedInputStream();
            PipedOutputStream pos = new PipedOutputStream();

            pos.connect(pis);

            Runnable r1 = () -> {
                try {
                    pos.write(123);
                    pos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };

            Runnable r2 = () ->
            {
                try {
                    out.println(pis.read());
                    pis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };

            r1.run();
            r2.run();

            TimeUnit.SECONDS.sleep(2);


        } catch (InterruptedException | IOException ex) {
            ex.printStackTrace();
        }

    }


    @Test
    public void howToOutputStream() {
        String file = "output.txt";
        try (PrintStream ps = new PrintStream(file)) {
            ps.println("Hi There"); // generates a file with Hi there content
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void howToCreateAndDeleteFiles() {

        var sep = File.separator;
        out.println(sep);

        String path = "demo1" + File.separator + "demo2" + File.separator;
        String fileName = "fileName.txt";
        try {
            File f = new File(path + fileName);
            new File(path).mkdirs();
            f.createNewFile();
            f.delete();
            path = StringUtils.substringBeforeLast(path, File.separator);
            while (new File(path).delete()) {
                path = StringUtils.substringBeforeLast(path, File.separator);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
