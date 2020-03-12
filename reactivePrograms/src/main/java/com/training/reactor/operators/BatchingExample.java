package com.training.reactor.operators;

import reactor.core.publisher.Flux;

/**
 * BatchingExample
 */
public class BatchingExample {

    public static void main(String[] args) {
           Flux.range(1, 13)
            .buffer()
            .subscribe(e -> System.out.println("onNext: {}"+ e));
        }
}