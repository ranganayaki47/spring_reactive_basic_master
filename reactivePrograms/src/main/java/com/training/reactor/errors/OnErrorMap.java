package com.training.reactor.errors;

import reactor.core.publisher.Flux;

/**
 * OnErrorReturnExample
 */

public class OnErrorMap {

    public static void main(String[] args) {
        errorGenerator()
            .subscribe(output->System.out.println(output),
                    error->System.out.println("inside the error"+error),
                    ()->System.out.println("inside the error"));
    }


    public static Flux<Integer> errorGenerator(){
        /*
        try{
            for()
            return 100/x
        }catch(AriExc){
              throw new BussinessException();  
        }

        	•	Catch, wrap to a BusinessException, and re-throw. 

            // onErrorResume
            // onErrorMap
            // onErrorReturn
            // doonerror
            // checked exception

        */

        return Flux.just(2,6,3,60,0,4)
                   .map(x->100/x)
                   .doOnError((error)->System.out.println(error))
                   .onErrorMap((e)->new Exception("number derror"));
                    
    }
    
}