package com.training.reactor.operators;

import reactor.core.publisher.Flux;

/**
 * Reduce
 */
public class ReduceExample {

    public static void main(String[] args) {
        Flux.range(1, 5)
            .scan(0, (acc, elem) ->{
                     System.out.println("acc"+acc);
                     System.out.println("elem"+elem);   
                    return acc + elem;
                } )
            .subscribe(result -> System.out.println("Result: {}"+ result));

       /* 
    Flux.range(1, 5)
    .scan(0, (acc, elem) -> acc + elem)
    .subscribe(result -> System.out.println("Result: {}"+ result));
        */
    }
}