package com.dou.component.RabbitMQ;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;

/**
 * @Author: dou
 * @ClassName: TopicRabbitConfig.java
 * @Package: com.dou.component
 * @TODO: rabbitMQ配置类
 * @Date: 2019-07-16 16:10:05.093
 */
@Configuration
public class TopicRabbitConfig {
	
	/**
	 * 队列名称
	 */
	final static String message = "topic.message";
	
	/**
	 * 队列名称
	 */
	final static String messages = "topic.messages";
	
	
	/**
	 * 队列名称
	 */
	final static String messageAll = "topic.#";
	
	/**
	 * 交换机名称
	 */
	final static String exchange = "exchange";
	
	/**
	 * 实例化queue绑定message
	 * @return
	 */
	@Bean
	public Queue queueMessage () {
		return new Queue(TopicRabbitConfig.message);
	}
	
	/**
	 * 实例化queue绑定messages
	 * @return
	 */
	@Bean
	public Queue queueMessages () {
		return new Queue(TopicRabbitConfig.messages);
	}
	
	/**
	 * 实例化交换机
	 * @return
	 */
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(TopicRabbitConfig.exchange);
	}
	
	/**
	 * 绑定队列、交换机、routeKey
	 * @param queueMessage
	 * @param exchange
	 * @return
	 */
	@Bean
	public Binding bindingExchangeMessage (Queue queueMessage, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessage).to(exchange).with(TopicRabbitConfig.message);
	}
	
	
	/**
	 * 绑定队列、交换机、routeKey
	 * @param queueMessages
	 * @param exchange
	 * @return
	 */
	@Bean
	public Binding bindingExchangeMessages (Queue queueMessages, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessages).to(exchange).with(TopicRabbitConfig.messageAll);
	}
	
	
}


