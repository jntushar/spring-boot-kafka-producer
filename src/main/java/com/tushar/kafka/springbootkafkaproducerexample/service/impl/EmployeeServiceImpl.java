package com.tushar.kafka.springbootkafkaproducerexample.service.impl;

import com.tushar.kafka.springbootkafkaproducerexample.model.Employee;
import com.tushar.kafka.springbootkafkaproducerexample.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private KafkaTemplate<String, Employee> kafkaTemplate;

    private static final String EMPLOYEE_TOPIC = "second-topic";


    @Override
    public String addEmployee(Employee employee) {
        log.info("Sending employee to kafka");
        ListenableFuture<SendResult<String, Employee>> future = kafkaTemplate.send(EMPLOYEE_TOPIC, employee.getDepartment(), employee);
        handleFuture(future);
        return "Employee sent successfully";
    }

    @Override
    public String addMultipleEmployees(List<Employee> employeeList) {
        log.info("Sending {} employees to kafka", employeeList.size());
        for (Employee employee : employeeList) {
            ListenableFuture<SendResult<String, Employee>> future = kafkaTemplate.send(EMPLOYEE_TOPIC, employee.getDepartment(), employee);
            handleFuture(future);
        }
        return "Employees added successfully";
    }

    private void handleFuture(ListenableFuture<SendResult<String, Employee>> future){
        future.addCallback(new ListenableFutureCallback<SendResult<String, Employee>>() {
            @Override
            public void onSuccess(SendResult<String, Employee> result) {
                log.info("Employee sent successfully: Topic={} | Partition={} | Offset={}",
                        result.getRecordMetadata().topic(),
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Failed to send message");
            }
        });
    }
}
