package com.tushar.kafka.springbootkafkaproducerexample.service.impl;

import com.tushar.kafka.springbootkafkaproducerexample.model.Employee;
import com.tushar.kafka.springbootkafkaproducerexample.service.EmployeeService;
import com.tushar.kafka.springbootkafkaproducerexample.service.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.tushar.kafka.springbootkafkaproducerexample.common.Constants.EMPLOYEE_TOPIC;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private ProducerService<String, Employee> producerService;


    @Override
    public String addEmployee(Employee employee) {
        log.info("Sending employee to kafka");
        producerService.sendToTopic(EMPLOYEE_TOPIC, employee.getDepartment(), employee);
        return "Employee sent successfully";
    }

    @Override
    public String addMultipleEmployees(List<Employee> employeeList) {
        log.info("Sending {} employees to kafka", employeeList.size());
        for (Employee employee : employeeList) {
            producerService.sendToTopic(EMPLOYEE_TOPIC, employee.getDepartment(), employee);
        }
        return "Employees added successfully";
    }
}
