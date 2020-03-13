package com.example.webclient;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class WebclientApplication {


	private WebClient webClient;


	WebclientApplication(){
		this.webClient=WebClient.builder()
									.baseUrl("http://localhost:8080/flux")
									.build();	
	}


	public Mono<String> postNewEmployee(Employee employee){
		 return webClient.post()
				.body(Mono.just(employee),Employee.class)
				.exchange()
				//.bodyToMono(ResponseEntity.class)
				// doOnError doOnSucces, doOnNext
				.flatMap(response->response.bodyToMono(String.class))
				.doOnSuccess(output->{
					System.out.println("@@@@@@@@@@@@@@@@@@@@@@");}
					);
				}		

	public static void main(String[] args) {
		Employee ramesh=Employee.builder()
		.id(1008)
		 .name("Mahesh kumar") 
		 .department("department") 
		 .build();
		
		 ramesh.setSalary(1000000);
		 WebclientApplication webclientApplication=new WebclientApplication();
		//webclientApplication.getAllEmployees()//.subscribe(output->System.out.println(output));
		//	webclientApplication.postNewEmployee(ramesh)
		//	webclientApplication.updateProduct(ramesh.getId(), ramesh)
		//	webclientApplication.deleteProduct(ramesh.getId())


		//webclientApplication.getAllEmployees()
		webclientApplication.getEmployeeById(1001)
	 	.subscribe(output->{
			System.out.println("*******s******************************");
			System.out.println(output);
			System.out.println("*************************************");

			},
			error->{
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println(error);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

			},
			()->System.out.println("Completedddddddddddddddddddd")
		);

	}

	private Flux<Employee> getAllEmployees() {
        return webClient
				.get()
				.retrieve()
                .bodyToFlux(Employee.class);
               // .doOnNext(o -> System.out.println("**********GET: " + o));
    }




	private Mono<Employee> getEmployeeById(int id) {
        return webClient
                .get()
                .uri("/{id}", id)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError,
						clientErrorResponse->{
						Mono<String> errorMsg= clientErrorResponse.bodyToMono(String.class);
							return errorMsg.flatMap(error-> {
								System.out.println("%%%%%%%%%%error"+error);
								throw new RuntimeException(error);
							});
						}
					)
					.bodyToMono(Employee.class)
					.doOnSuccess(o -> System.out.println("**********UPDATE " + o));
    }


    private Mono<String> updateProduct(int id, Employee employee) {
        return webClient
                .patch()
                .uri("/{id}", id)
                .body(Mono.just(employee), Employee.class)
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(o -> System.out.println("**********UPDATE " + o));
    }

    private Mono<String> deleteProduct(int id) {
        return webClient
                .delete()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(o -> System.out.println("**********DELETE " + o));
    }


}
