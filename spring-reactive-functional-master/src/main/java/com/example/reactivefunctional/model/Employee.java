package com.example.reactivefunctional.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Employee
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@Document
public class Employee {

    @Id
    private int id;
    private String name;
    private float salary;
    private String department;
    
}