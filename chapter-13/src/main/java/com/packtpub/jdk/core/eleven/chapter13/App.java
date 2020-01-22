package com.packtpub.jdk.core.eleven.chapter13;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.junit.Ignore;
import org.junit.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static java.lang.System.out;

public class App {


    @Test
    @Ignore
    public void howToLocalDate() {

        var date = LocalDate.now();
        var dayOfWeek = date.getDayOfWeek();
        var dayOfMonth = date.getDayOfMonth();
        var month = date.getMonth();
        var year = date.getYear();

        var date1 = LocalDate.of(2020, 1, 16);
        var date2 = LocalDate.ofYearDay(2020, 23);

        //
        var time = LocalTime.ofSecondOfDay(3600); // One hour

        out.println(time);

        out.println(date1);

    }

    @Test
    public void howToZonedDate() {

        var indianTz = ZoneId.of("Asia/Kolkata");
        var istDatetime = ZonedDateTime.now(indianTz);
        var dateTime = ZonedDateTime.now();

        var dateTimeOf = ZonedDateTime.of(2020, 1,
                22, 14,30, 11, 33, indianTz);

        var hourAheadClock = Clock.offset(Clock.system(ZoneId.of("Asia/Kolkata")), Duration.ofHours(1));

        out.println(istDatetime);
        out.println(dateTime);
        out.println(dateTimeOf);
        out.println(hourAheadClock);
    }

    @Test
    public void howToOffSetTime() {

        var offSet = OffsetTime.of(LocalTime.of(14, 12, 32),
                ZoneOffset.ofHoursMinutes(5, 30)); // 14:12:32+05:30

        var period3 = Period.ofDays(10);
        var duration1 = Duration.of(56, ChronoUnit.MINUTES); // PT56M
        var duration2 = Duration.of(56, ChronoUnit.DAYS); // PT144H
        var duration3 = Duration.ofSeconds(87); // PT1M27S
        var duration4 = Duration.ofHours(7); // PT7H

        out.println(offSet);
        out.println(period3);
    }

    @Test
    public void howToLocalDateTime() {

        var loc1 = LocalDateTime.now();
        var loc2 = LocalDateTime.of(2018, 6, 22, 11, 0);
        out.println(loc1);
    }

    @Test
    public void howToInstant() {
        var inst1 = Instant.now();
        var inst2 = Instant.now().plusNanos(1991999);

        out.println(inst1);
        out.println(inst2);
    }

    @Test
    public void howToHandleDate() {

        out.println(LocalDate.now().plusDays(2));
        out.println(LocalDate.now().minusYears(2));
        out.println(LocalDate.now().minusMonths(2));

        out.println(LocalDateTime.now().plusDays(2));
        out.println(LocalDateTime.now().plusMinutes(2));
    }

    @Test
    public void howToDateTimeFormatter() {
        var ld = LocalDate.now();
        var ldt = LocalDateTime.now();

        out.println(ld.format(DateTimeFormatter.ISO_DATE));
        out.println(ldt.format(DateTimeFormatter.ISO_DATE_TIME));
        out.println(ldt.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm:ss a"))); // 17 Jan 2020 08:30:20 PM
    }

   @FunctionalInterface
    interface MyInterface { String sayHi(String name); }

    @Test
    public void howToFunctionalInterface() {
        MyInterface r = (p) -> String.format("Hi %s", p);
        BiFunction<Double, Integer, Double> b1 = (@NotNull var p1, @NotNull var p2) -> p1 / p2;

        this.printWithPrefixAnsPostfix("P1", "P2").accept("ok");

        out.println(r.sayHi("Douglas"));
        out.println(b1.apply(10d, 5));
    }


    private Consumer<String> printWithPrefixAnsPostfix(String p1, String p2) {
        return s -> out.println(p1 + s + p2);
    }
}
