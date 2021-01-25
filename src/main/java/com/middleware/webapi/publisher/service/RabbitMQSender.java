package com.middleware.webapi.publisher.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.middleware.webapi.publisher.model.Employee;

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
	
	public void send(Employee company) {
		rabbitTemplate.convertAndSend(exchange, routingkey, company);
		System.out.println("Send msg = " + company.toString());
	    
	}
}

