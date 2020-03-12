package com.training.reactor.operators;

import java.time.Duration;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;

/**
 * Disposable
 */
public class DisposableExample {

    public static void main(String[] args) throws Exception {
        Flux.range(1, 100)                                                 // (1)
        .subscribe(                                                    // (2)
            data -> System.out.println("onNext: {}"+ data),
            err -> { /* ignore */ },
            () -> System.out.println("onComplete"),
            subscription -> {                                          // (3)
               subscription.request(4);                                // (3.1)
               subscription.cancel();                                  // (3.2)
            }
        );

        Disposable disposable = Flux.interval(Duration.ofMillis(50))         // (1)
                .subscribe(data -> System.out.println("onNext: {}"+ data));
    
        Thread.sleep(200);                                                   // (3)
         disposable.dispose(); 
    
        }
    
}