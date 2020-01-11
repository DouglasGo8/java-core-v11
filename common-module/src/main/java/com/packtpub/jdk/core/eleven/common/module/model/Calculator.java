package com.packtpub.jdk.core.eleven.common.module.model;

public class Calculator {

    private double prop;

    public double calculate(int i) {
        //DoubleStream.generate(new Random()::nextDouble).limit(50);
        synchronized (this) {
            this.prop = 2.0d * i;
            //DoubleStream.generate(new Random()::nextDouble).limit(50);
            return Math.sqrt(this.prop);
        }
    }
}
