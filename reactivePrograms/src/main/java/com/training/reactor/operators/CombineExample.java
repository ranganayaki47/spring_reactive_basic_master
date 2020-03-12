package com.training.reactor.operators;

import java.time.Duration;

import reactor.core.publisher.Flux;

/**
 * CombineExample
 */
public class CombineExample {

    public static void main(String[] args) throws Exception{
        System.out.println("concat");
                        Flux.zip(
                            Flux.interval(Duration.ofSeconds(1)).map(id -> "A" + id).take(5),
                            Flux.interval(Duration.ofSeconds(1)).map(id -> "B" + id)
                            ).subscribe(e -> System.out.println("onNext: {}"+ e));                        
                    Thread.sleep(10000);
        }

}