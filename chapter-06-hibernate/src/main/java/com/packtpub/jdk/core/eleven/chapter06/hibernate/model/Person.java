package com.packtpub.jdk.core.eleven.chapter06.hibernate.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private LocalDate dob;
    private int ordered;

    public Person(String name, String address, LocalDate dob, int ordered) {
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.ordered = ordered;
    }

    @Override
    public String toString() {
        return "Person {name:" + name + ", address: " + address + ", dob: " + dob + ", ordered: " + ordered + "}";
    }
}
