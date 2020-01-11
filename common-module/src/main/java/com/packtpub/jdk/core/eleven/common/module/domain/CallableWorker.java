package com.packtpub.jdk.core.eleven.common.module.domain;

import java.util.concurrent.Callable;

public interface CallableWorker<Result> extends Callable<Result> {

    /**
     *
     * @return default Name
     */
    default String getName() {
        return "Anonymous";
    }

    /**
     *
     * @return default Sleep
     */
    default int getSleepSec() {
        return 1;
    }
}
