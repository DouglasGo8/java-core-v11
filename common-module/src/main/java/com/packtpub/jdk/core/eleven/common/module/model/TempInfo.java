package com.packtpub.jdk.core.eleven.common.module.model;


import lombok.Getter;

import java.util.Random;

public class TempInfo {

    private static final Random rdn = new Random();

    @Getter
    private final int temp;
    @Getter
    private final String town;


    public TempInfo(int temp, String town) {
        this.temp = temp;
        this.town = town;
    }

    public static TempInfo fetch(String town) {

        if (rdn.nextInt(10) == 0)
            throw new RuntimeException("Error!");

        return new TempInfo(rdn.nextInt(100), town);

    }

    @Override
    public String toString() {
        return town + " : " + temp;
    }

}
