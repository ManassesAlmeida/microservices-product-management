package com.manassesalmeida.payment.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.manassesalmeida.payment.data.vo.ProductVO;
import com.manassesalmeida.payment.entity.Product;
import com.manassesalmeida.payment.repository.ProductRepository;

@Component
public class ProductReceiveMessage {

	@Autowired
	private ProductRepository productRepository;

	@RabbitListener(queues = {"${crud.rabbitmq.queue}"})
	public void receive(@Payload ProductVO productVO) {
		productRepository.save(Product.create(productVO));
	}
}
