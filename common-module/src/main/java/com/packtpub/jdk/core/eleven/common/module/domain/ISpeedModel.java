

package com.packtpub.jdk.core.eleven.common.module.domain;

/**
 * @author dbatista
 */
@FunctionalInterface
public interface ISpeedModel {
    double getSpeedMph(int timeSec, int weightPounds, int horsePower);
}
