package com.packtpub.jdk.core.eleven.chapter16;

import lombok.SneakyThrows;
import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

public class App {


    @SneakyThrows
    public static void main(String... args) {
        Main.main(args);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
    public void theTestMethod() {
        int res = 0;
        for (int i = 0; i < 1000; i++) {
            int n = i * i;
            if (n != 0 && n % 250_000 == 0) {
                res += n;
            }
        }
    }
}
