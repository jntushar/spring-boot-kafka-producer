package com.tushar.kafka.springbootkafkaproducerexample.controller;

import com.tushar.kafka.springbootkafkaproducerexample.model.Employee;
import com.tushar.kafka.springbootkafkaproducerexample.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tushar.kafka.springbootkafkaproducerexample.common.Constants.API_VERSION;

@RestController
@RequestMapping(path = API_VERSION + "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.OK);
    }

    @PostMapping("/addMultiple")
    public ResponseEntity<String> addEmployees(@RequestBody List<Employee> employees) {
        return new ResponseEntity<>(employeeService.addMultipleEmployees(employees), HttpStatus.OK);
    }
}