package com.github.ashutosh.ubpa.service;

import com.github.ashutosh.ubpa.configuration.KafkaParameters;
import com.github.ashutosh.ubpa.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessageWhenAProductIsRequested(Product product){

        // User id needs to be replaced with current authenticated user id
        String message = "User with user id 1 viewed product with product "+product.getProductName()+" with id "+product.getId()
                +" at time "+ LocalDateTime.now();
       // String topic="test-topic";
        String topic = KafkaParameters.TEST_TOPIC;
        kafkaTemplate.send(topic,message);

    }
    
    @KafkaListener(topics = KafkaParameters.TEST_TOPIC,groupId = KafkaParameters.TEST_GROUP_NAME)
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group group1 " + message);
    }
}
