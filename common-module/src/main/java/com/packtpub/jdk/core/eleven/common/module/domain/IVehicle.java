package com.packtpub.jdk.core.eleven.common.module.domain;

/**
 * @author dbatista
 */
public interface IVehicle {

    void setSpeedModel(ISpeedModel speedModel);
    default double getSpeedMph(double timeSec) {return -1;}
    default int getWeightPounds() {return -1;}
    default int getWeightKg() {
        return convertPoundsToKg(this.getWeightPounds());
    }
    private int convertPoundsToKg(int pounds) {
        return (int)Math.round(0.454 * pounds);
    }
    static int convertKgToPounds(int kilograms) {
        return (int) Math.round(2.205 * kilograms);
    }
}
