package com.training.reactor;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.core.publisher.Flux;

/**
 * Subscribers
 */

public class SubscribersExample {

    public static void main(String[] args) {
        Subscriber<String> subscriber = new Subscriber<String>() {
            volatile Subscription subscription;                             // (1)
         
            public void onSubscribe(Subscription s) {                       // (2)
               subscription = s;                                            // (2.1)
               System.out.println("initial request for 1 element");                   //
               subscription.request(1);                                     // (2.2)
            }
         
            public void onNext(String s) {                                  // (3)
               System.out.println("onNext: {}"+ s);                                   //
               System.out.println("requesting 1 more element");                       //
               subscription.request(1);                                     // (3.1)
            }
         
            public void onComplete() {
               System.out.println("onComplete");
            }
         
            public void onError(Throwable t) {
                System.out.println("onError: {}"+ t.getMessage());
            }
         };
         
         Flux<String> stream = Flux.just("Hello", "world", "!");            // (4)
         stream.subscribe(subscriber);      
    }

}