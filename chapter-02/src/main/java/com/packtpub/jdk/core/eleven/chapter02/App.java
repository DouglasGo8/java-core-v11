package com.packtpub.jdk.core.eleven.chapter02;

import com.packtpub.jdk.core.eleven.common.module.model.Engine;
import org.junit.Test;
import static java.lang.System.out;

public class App {

    @Test
    public void howCalculateHorsePower() {

        double timeSec = 7.0;
        int horsePower = 240;
        int vehicleWeight = 1500;

        var engine = new Engine();

        engine.setHorsePower(horsePower);
        out.println(engine.getSpeedMph(timeSec, vehicleWeight));
    }
}
