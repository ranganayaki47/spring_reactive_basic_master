package com.training.reactor.operators;

import java.time.Duration;

import reactor.core.publisher.Flux;

public class FlatMapExample {


    public static Flux fetchEmployees(String department) {
        if(department.equals("java"))
                return Flux.just("ramesh","suresh");
           else     
                return Flux.just("dhinesh","ganesh");
    }
    
   
    public static void main(String[] args) throws Exception{
            Flux.just("java","dotnet")
                .flatMap((department)->fetchEmployees(department))
                //      .switchMap(FlatMapExample::fetchEmployees)
                .subscribe(value->System.out.println(value));
        
        
        /*
        for(int i=1;i<=5;i++){

            for(int j=1;j<=5;j++){
                System.out.println(i*j);
            }
            System.out.println();

        }



        //Flux.range(1, 5)
           // .map(i->Flux.)
          //  .subscribe(System.out::println);




     /*   Flux.just("user-1", "user-2", "user-3")
        .flatMap(u -> requestBooks(u)
            .map(b -> u + "/" + b))
        .subscribe(result -> System.out.println("Result: {}"+ result));
        */
    }
}