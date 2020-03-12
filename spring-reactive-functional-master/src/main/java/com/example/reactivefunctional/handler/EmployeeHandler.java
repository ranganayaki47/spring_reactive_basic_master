package com.example.reactivefunctional.handler;

import com.example.reactivefunctional.dao.EmployeeDao;
import com.example.reactivefunctional.model.Employee;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * ProductHandler
 */
@Component
public class EmployeeHandler {

    private EmployeeDao employeeDao;

    public EmployeeHandler(EmployeeDao employeeDao){
        this.employeeDao=employeeDao;
    }

    public Mono<ServerResponse> getAllEmployees(ServerRequest serverRequest){
        Flux <Employee>employees=employeeDao.findAll();
        return ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(employees, Employee.class)
                    .switchIfEmpty(ServerResponse.notFound().build());
     }


     public Mono<ServerResponse> getEmployeeById(ServerRequest serverRequest){
       /* Mono<Employee> employeeMono=employeeDao.findById(Integer.parseInt(serverRequest.pathVariable("id")));
        return employeeMono.flatMap(employee->ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(employee, Employee.class))
                             .switchIfEmpty(ServerResponse.notFound().build());
        */

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                                   .body(employeeDao.findById(Integer.parseInt(serverRequest.pathVariable("id")))
                                                    , Employee.class)
                                   .switchIfEmpty(ServerResponse.notFound().build());



     }


     public Mono<ServerResponse> deleteEmployee(ServerRequest serverRequest){
        return employeeDao.findById(Integer.parseInt(serverRequest.pathVariable("id")))
                   .flatMap(emp->ServerResponse.ok()
                                            .build(employeeDao.deleteById(emp.getId())))
                   .switchIfEmpty(ServerResponse.notFound().build());
     }


     public Mono<ServerResponse> updateEmployee(ServerRequest serverRequest){
        Mono <Employee>employeeMono=serverRequest.bodyToMono(Employee.class);
        Mono <Employee>existingMono= employeeDao.findById(Integer.parseInt(serverRequest.pathVariable("id")));

        Mono <Employee>mergedMono=existingMono.zipWith(employeeMono,
                    (
                        (existingEmployee,employee)->Employee.builder()
                                                            .id(Integer.parseInt(serverRequest.pathVariable("id")))
                                                            .name(employee.getName())
                                                            .salary(employee.getSalary())
                                                            .build()
                    ));
                    
                   return mergedMono.flatMap(emp1->ServerResponse.ok()
                                    .body(employeeDao.save(emp1), Employee.class))
                   .switchIfEmpty(ServerResponse.notFound().build());
                   
     }

     public Mono<ServerResponse> saveEmployee(ServerRequest serverRequest){
            Mono<Employee> employeeMono=serverRequest.bodyToMono(Employee.class);
            return employeeMono.flatMap(employee->
                                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                                .body(employeeDao.save(employee),Employee.class))
                                .switchIfEmpty(ServerResponse.notFound().build());
        }

}