package com.airline.admin.service;

import java.util.Stack;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerListener {

    private static final String TOPIC = "kafka-topic";
    
    private Stack<String> changeList = new Stack<>();

    @KafkaListener(topics = TOPIC, groupId="group_id", containerFactory = "userKafkaListenerFactory")
    public void consumeJson(String str) {
    	changeList.push(str);
    }
    
    public String changes() {
    	String str = "";
    	while(!changeList.isEmpty()) {
    		str=str + changeList.pop()+"\n";
    	}
    	return str;
    }
    
}