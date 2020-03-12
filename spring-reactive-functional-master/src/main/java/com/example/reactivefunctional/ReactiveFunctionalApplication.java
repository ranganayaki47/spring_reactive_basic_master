package com.example.reactivefunctional;

import com.example.reactivefunctional.dao.EmployeeDao;
import com.example.reactivefunctional.model.Employee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.example.reactivefunctional.handler.EmployeeHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@SpringBootApplication
public class ReactiveFunctionalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveFunctionalApplication.class, args);
	}

	private Mono<ServerResponse> hiHandler(ServerRequest serverRequest){
		return ServerResponse.ok().body(Mono.just("Hi World!"), String.class);
	}





	
	private Mono<ServerResponse> pathVariableHandler(ServerRequest serverRequest){
		String name=serverRequest.pathVariable("name");
		return ServerResponse.ok()
				.body(Mono.just("Hi World!"+name), String.class);
	}



	private Mono<ServerResponse> payloadHandler(ServerRequest serverRequest){
		Employee ramesh=Employee.builder()
			.id(1001)
			 .name("ramesh kumar") 
			 .department("department") 
			 .build();
			
			 Employee suresh=Employee.builder()
			 .id(1002)
			  .name("suresh kumar") 
			  .department("department") 
			  .build();

			Flux<Employee> employeeFlux=Flux.just(ramesh,suresh);
		return ServerResponse.ok()
				.body(employeeFlux, Employee.class);
	}

	private Mono<ServerResponse> postHandler(ServerRequest serverRequest){
		Flux<Employee> employeeMono=serverRequest.bodyToFlux(Employee.class);
		return ServerResponse.ok()
				.body(employeeMono.map(employee->{
								employee.setName(employee.getName().toUpperCase());
								return employee;}
						), Employee.class);
	}

	private Mono<ServerResponse> putHandler(ServerRequest serverRequest){
		Mono<Employee> employeeMono=serverRequest.bodyToMono(Employee.class);
		String id=serverRequest.pathVariable("id");

		return ServerResponse.ok()
				.body(employeeMono.map(employee->{
								employee.setName(employee.getName().toUpperCase());
								return employee;}
						), Employee.class);
	}


	//@Bean
    RouterFunction<ServerResponse> helloRouterFunction() {
        RouterFunction<ServerResponse> routerFunction =
        RouterFunctions.route(
			RequestPredicates.path("/hello"),
                        serverRequest ->
							ServerResponse.ok().body(Flux.just("Hello World!"), String.class))
			.andRoute(GET("/hi"), this::hiHandler)
			.andRoute(GET("/print/{name}"), this::pathVariableHandler)
			.andRoute(GET("/payload"), this::payloadHandler)
			.andRoute(PUT("/postpayload/{id}"), this::postHandler);
        return routerFunction;
    }

  




	@Bean
	CommandLineRunner init(EmployeeDao employeeDao){
		return (args)->{
			Employee ramesh=Employee.builder()
			.id(1001)
			 .name("ramesh kumar") 
			 .department("department") 
			 .build();
			
			 Employee suresh=Employee.builder()
			 .id(1002)
			  .name("suresh kumar") 
			  .department("department") 
			  .build();

			Flux<Employee> employeeFlux=Flux.just(ramesh,suresh);
				employeeFlux.flatMap(employee->employeeDao.save(employee))
		 		 .subscribe(employee1->System.out.println("employee name"+employee1.getName()));
		};

	}


    @Bean
    public RouterFunction<ServerResponse> routes(EmployeeHandler employeeHandler){
        return route(
				GET("/employees"), employeeHandler::getAllEmployees)
                .andRoute(GET("/employees/{id}"), employeeHandler::getEmployeeById)
                .andRoute(POST("/employees"), employeeHandler::saveEmployee)
                .andRoute(PUT("/employees/{id}"), employeeHandler::updateEmployee)
                .andRoute(DELETE("/employees/{id}"), employeeHandler::deleteEmployee);
    }

}
