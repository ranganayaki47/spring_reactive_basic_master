package com.example.reactivefunctional.dao;

import com.example.reactivefunctional.model.Employee;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


/**
 * EmployeeDao
 */
public interface EmployeeDao extends ReactiveMongoRepository<Employee, Integer>{

}