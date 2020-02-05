package com.packtpub.jdk.core.eleven.common.module.model;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Engine {

    @Setter
    private int horsePower;

    public double getSpeedMph(double timeSec, int weightPounds) {
        var v = (2.0 * this.horsePower * 746 * timeSec * 32.17) / weightPounds;
        return Math.round(Math.sqrt(v) * 0.68);
    }

}
