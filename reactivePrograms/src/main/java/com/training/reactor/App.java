package com.training.reactor;

import java.time.Duration;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import javax.management.Notification;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Hello world!
 */
public final class App {
    
    public static Flux<String> method(){
        String [] arr={"ramesh","suresh","dhinesh"};
        List<String> names=Arrays.asList(arr);
        return Flux.fromIterable(names);
    }


    public static void main(String[] args) throws Exception{
        method().subscribe((value)->System.out.println("1"+value));
        method().subscribe((value)->System.out.println("2"+value));
        method().subscribe((value)->System.out.println("3"+value));


        /* 
        java 8

        for(String name:names){
        }
            //push

     
        Flux.interval(Duration.ofMillis(500)).log()
            .subscribe(
                (value)-> System.out.println(Thread.currentThread()+"hai"),
                (error)->System.out.println(error),
                ()->System.out.println("completed"),
                (disposable)->disposable.cancel());
            System.out.println(Thread.currentThread());
             Thread.sleep(4000);
*/
        // just (value)
        // fromIterable(collection)
        // interval
        // error

        // Flux
        // Subscriber


     /*   Flux.just("hello","world")
            .subscribe((value)->System.out.println(value));

        Mono.just("hello")
            .subscribe();

        Mono.empty().subscribe((value)->System.out.println(value),
                        (error)->System.out.println(error),
                         ()->System.out.println("value") );*/

        //Push
        
        Mono.error(new Exception("e"))
            .subscribe((value)->System.out.println(value),
            (error)->System.out.println(error),
             ()->System.out.println("completed"));

/*

String[] arr={"ramesh","suresh","dhiesh"};
Flux<String> namesFlux=Flux.fromIterable(Arrays.asList(arr));
    namesFlux.subscribe(
            (value)->{System.out.println(Thread.currentThread());
                        System.out.println(value);},
            (error)->System.out.println("exception"+error),
            ()->System.out.println("completed")
            );



  Flux emitter=Flux.interval(Duration.ofMillis(200));
            emitter.subscribe(value->System.out.println(Thread.currentThread()+":"+value));
            emitter.subscribe(value->System.out.println(Thread.currentThread()+":"+value));

  System.out.println(Thread.currentThread());    
  Thread.sleep(1000);
       	
	Spring boot —> — > WebFlux —> above excerice
	spring boot —> MongoDB — crud



			 Mono.just("one");
        Flux.just("one","two");
        Mono.error(new Exception("hello")).log().subscribe(value->System.out.println(value));*/
          //localhost:8080/employee/1001
         // --> Mono<Employee>  



            /*
        //Flux and Mono
        // subscribe -> subscribe
                    --> value
                    --> Error
                    ---> Notification
                    ---> Disposable*/

        // publisher  --> producer --> stream -> 
        // subscriber    
            }
                               // (5)
}
