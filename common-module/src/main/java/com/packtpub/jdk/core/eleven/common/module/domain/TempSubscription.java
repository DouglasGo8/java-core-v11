package com.packtpub.jdk.core.eleven.common.module.domain;

import com.packtpub.jdk.core.eleven.common.module.model.TempInfo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.Flow.Subscriber;
import static java.util.concurrent.Flow.Subscription;

/**
 *
 */
public class TempSubscription implements Subscription {

    private final String town;
    private final Subscriber<? super TempInfo> subscriber;
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public TempSubscription(Subscriber<? super TempInfo> subscriber, String town) {
        this.town = town;
        this.subscriber = subscriber;
    }

    @Override
    public void request(long n) {
        // System.out.println(n);
        executor.submit(() -> {
            for (long i = 0L; i < n; i++) {
                try {
                    subscriber.onNext(TempInfo.fetch(town));
                } catch (Exception e) {
                    subscriber.onError(e);
                    break;
                }
            }
        });
    }

    @Override
    public void cancel() {
        this.subscriber.onComplete();
    }
}
