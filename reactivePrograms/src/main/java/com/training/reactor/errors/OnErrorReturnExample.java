package com.training.reactor.errors;

import reactor.core.publisher.Flux;

/**
 * OnErrorReturnExample
 */

public class OnErrorReturnExample {

    public static void main(String[] args) {
        Flux<Integer> sequence=errorGenerator();
        sequence.subscribe(output->System.out.println(output),
        error->System.out.println("inside the error"+error),
        ()->System.out.println("inside the error"));
    }

   
        // {}
        // {}

    public static Flux<Integer> errorGenerator(){
        /*
        try{
            for()
            return 100/x
        }catch(AriExc){
              return 0;  
        }

        	•	Catch and return a static default value. 


        */

        return Flux.just(2,6,3,60,0,4)
                   .map(x->100/x)
                  .onErrorReturn(100);
                    
    }
    
}