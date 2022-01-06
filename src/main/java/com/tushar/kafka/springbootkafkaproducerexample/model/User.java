package com.tushar.kafka.springbootkafkaproducerexample.model;

public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private String contactNo;
    private String department;
    private Double salary;

    public User(String userId, String firstName, String lastName, String contactNo, String department, Double salary) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNo = contactNo;
        this.department = department;
        this.salary = salary;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
