package com.middleware.webapi.publisher.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.middleware.webapi.fileUpload.model.FileInfo;

@Service
public class RabbitMQSender {
	
	@Autowired
	private final AmqpTemplate rabbitTemplate;
	
	RabbitMQSender(AmqpTemplate amqp){
		this.rabbitTemplate= amqp;
	}
	
	@Value("${javainuse.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${javainuse.rabbitmq.routingkey}")
	private String routingkey;	
	
	
	public void send(FileInfo file) {
		rabbitTemplate.convertAndSend(exchange, routingkey, file);
		System.out.println("Send msg = " + file);
	}
}

