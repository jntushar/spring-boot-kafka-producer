package com.tushar.kafka.springbootkafkaproducerexample.service;

import com.tushar.kafka.springbootkafkaproducerexample.model.Employee;

import java.util.List;

public interface EmployeeService {

    String addEmployee(Employee employee);

    String addMultipleEmployees(List<Employee> employeeList);
}
