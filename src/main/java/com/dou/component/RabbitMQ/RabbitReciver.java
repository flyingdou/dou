package com.dou.component.RabbitMQ;

import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.dou.util.EasyUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: dou
 * @ClassName: RabbitReciver.java
 * @Package: com.dou.component.RabbitMQ
 * @TODO: 接收队列的消息
 * @Date: 2019-07-16 16:52:52.665
 */
@Component
@Slf4j
public class RabbitReciver {
	
	/**
	 * 接收者
	 * @throws InterruptedException 
	 */
	@RabbitListener(queues = "hello")
	@RabbitHandler
	public void process (String message) throws InterruptedException {
		log.info("接收的消息: {}, 时间: {}", message, EasyUtils.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
		// 接收线程，睡眠500ms
		Thread.sleep(20);
	}

}


