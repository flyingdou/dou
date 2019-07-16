package com.dou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dou.component.RabbitMQ.RabbitSender;

/**
 * @Author: dou
 * @ClassName: TestController.java
 * @Package: com.dou.controller
 * @TODO: 测试controller
 * @Date: 2019-07-16 17:11:09.755
 */
@RestController
public class TestController {
	
	@Autowired
	private RabbitSender rabbitSender;
	
	
	@RequestMapping("/test")
	public void test () {
		for (int i = 0; i < 10000; i++) {
			rabbitSender.send(i + "");
		}
	}
	

}


