package com.tushar.kafka.springbootkafkaproducerexample.resource;

import com.tushar.kafka.springbootkafkaproducerexample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    private static final String TOPIC = "Kafka_Demo";

    @RequestMapping(method = RequestMethod.POST, value = "/users/add")
    public String addUser(@RequestBody User user){
        kafkaTemplate.send(TOPIC, user.getDepartment(), user);
        return "User added successfully";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/addmultiple")
    public String addUsers(@RequestBody List<User> users){
        for(User user: users){
            kafkaTemplate.send(TOPIC, user.getDepartment(), user);
        }
        return "Users added successfully";
    }
}