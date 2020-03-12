package com.training.reactor.operators;

import java.util.Optional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Then
 */
public class Then {

    public static void main(String[] args) {
    //    The Mono and Flux streams have the then, thenMany, and thenEmpty operators, which complete when the upper stream completes. The operators ignore incoming elements and only replay completion or error signals. These operators can be useful for triggering new streams as soon as the upper stream finishes processing: 
    // operation --> 
    //    map( delete employee)
    //    .then(Mono.just("Employee hass been deleted suc"))

    
    Flux.just(1, 2, 3)
            .thenMany(Flux.just(4, 5))
            .subscribe(e -> System.out.println("onNext: {}"+ e));
    

            // list of employee from database
            //
            // "no employeeoptional

            Optional optionalValue=Optional.empty();
            Mono.justOrEmpty(optionalValue)
                .defaultIfEmpty("novalues")
                .subscribe((value)->System.out.println("value"+ value));
    
        
            Mono.justOrEmpty(optionalValue)
                .doOnSuccess((value)->{
                            System.out.println("it doest not streams"+value);
                        })
                .subscribe((value)->System.out.println("value"+ value));

    
        }
}