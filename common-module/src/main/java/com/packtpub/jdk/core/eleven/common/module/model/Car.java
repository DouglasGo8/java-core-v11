package com.packtpub.jdk.core.eleven.common.module.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private int year;
    private String brand;
    private String model;
    private double price;

    @Override
    public String toString() {
        return "{model:" + this.model + ", year: " + this.year + ", price:" + this.price + "}";
    }

    public Stream<Car> ofCars() {
        return Stream.of(new Car(2016, "Maserati", "Levanti", 495.000d),
                new Car(2018, "BMW", "X5", 550.001d),
                new Car(2009, "Mercedez", "A 45 AMG", 321.02d),
                new Car(2016, "Alfa Romeo", "Alfa Romeo 2300 B", 33.22d),
                new Car(2019, "BMW", "X3", 168.45d));
    }
}