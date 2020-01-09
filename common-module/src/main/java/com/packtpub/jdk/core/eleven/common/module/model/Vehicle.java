
package com.packtpub.jdk.core.eleven.common.module.model;

import com.packtpub.jdk.core.eleven.common.module.domain.ISpeedModel;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Vehicle {

    final int x, y, z;

    public void method(ISpeedModel speed) {
        System.out.println(speed.getSpeedMph(this.x, this.y, this.z));
    }

}