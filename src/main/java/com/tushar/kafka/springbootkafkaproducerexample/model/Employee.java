package com.tushar.kafka.springbootkafkaproducerexample.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String contactNo;
    private String department;
    private Double salary;

}
