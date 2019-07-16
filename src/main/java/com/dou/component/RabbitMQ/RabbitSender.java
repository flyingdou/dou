package com.dou.component.RabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: dou
 * @ClassName: RabbitSender.java
 * @Package: com.dou.component.RabbitMQ
 * @TODO: rabbitMQ消息发送器
 * @Date: 2019-07-16 16:44:29.480
 */
@Component
@Slf4j
public class RabbitSender {
	
	/**
	 * 注入amqpTemplate
	 */
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	/**
	 * 基础版发送消息
	 */
	public void send (String message) {
		log.info("发送的内容: {}", "发送的消息是: " + message);
		this.amqpTemplate.convertAndSend("hello", message);
	}

}


