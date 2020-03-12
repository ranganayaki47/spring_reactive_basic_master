package com.training.reactor.errors;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * OnErrorReturnExample
 */

public class OnErrorResume {

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
              return someotherfunction;
        }

        

        */

        return Flux.just(2,5,20,50,0,4)
                   .map(x->100/x)
                   .onErrorResume(ex->Flux.range(1,23));
    }
    
}