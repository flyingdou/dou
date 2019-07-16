package com.dou.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dou.component.RabbitMQ.RabbitSender;

/**
 * @Author: dou
 * @ClassName: douTest.java
 * @Package: com.dou.test
 * @TODO: 测试类
 * @Date: 2019-07-16 16:58:36.023
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DouTest {
	
	@Autowired
	private RabbitSender rabbitSender;
	
	
	@Test
	public void send () {
		for (int i = 0; i < 10000; i++) {
			rabbitSender.send(i + "");
		}
	}

}


