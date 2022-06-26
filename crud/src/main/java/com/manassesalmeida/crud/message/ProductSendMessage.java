package com.manassesalmeida.crud.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.manassesalmeida.crud.data.vo.ProductVO;

@Component
public class ProductSendMessage {

	@Value("${crud.rabbitmq.exchange}")
	String exchange;
	
	@Value("${crud.rabbitmq.routingKey}")
	String routingKey;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendMessage(ProductVO productVO) {
		rabbitTemplate.convertAndSend(exchange, routingKey, productVO);
	}
}
