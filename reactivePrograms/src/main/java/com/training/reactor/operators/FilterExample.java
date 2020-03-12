package com.training.reactor.operators;

import org.omg.CORBA.SystemException;

import reactor.core.publisher.Flux;

/**
 * FilterExample
 */




public class FilterExample {

    public static void main(String[] args) {
            Flux.range(1, 12)
        //         .repeat(2)
        //         .distinct()
        //        .filter((x)->x%2==0)
                .takeWhile((x)->x==10)
                .subscribe(System.out::println);

               }

}