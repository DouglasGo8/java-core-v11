package com.packtpub.jdk.core.eleven.common.module.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Person {

    @Getter
    private final int age;
    @Getter
    private final String name;

    @Override
    public String toString() {
        return "Person{age=" + age + ", name='" + name + "'}";
    }
}
