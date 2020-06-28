package com.packtpub.jdk.core.eleven.common.module.domain;

import com.packtpub.jdk.core.eleven.common.module.model.TempInfo;

import static java.util.concurrent.Flow.Subscriber;
import static java.util.concurrent.Flow.Subscription;

public class TempSubscriber implements Subscriber<TempInfo> {

    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(TempInfo item) {
        System.out.println(item);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.err.println(throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("Done!");
    }
}
