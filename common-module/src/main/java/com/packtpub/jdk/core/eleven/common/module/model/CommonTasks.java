
package com.packtpub.jdk.core.eleven.common.module.model;

import java.util.Optional;
import static java.lang.System.out;

/**
 * @author dbatista
 */
public class CommonTasks {

    public static void myHeaders() {
        out.println("hi");
    }

    public static void checkResultInt(Optional<Integer> lotteryPrize) {

        if (lotteryPrize.isEmpty() || lotteryPrize.get() <= 0) {
            out.println("We've lost again...");
        } else {
            out.println("We've won! Your half is " + Math.round(((double) lotteryPrize.get()) / 2) + "!");
        }
    }

    public static void checkResultInt(int lotteryPrize) {

        if (lotteryPrize <= 0) {
            out.println("We've lost again...");
        } else {
            out.println("We've won! Your half is " + Math.round(((double) lotteryPrize) / 2) + "!");
        }
    }

}