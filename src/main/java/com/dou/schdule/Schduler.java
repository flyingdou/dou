package com.dou.schdule;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.dou.util.EasyUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: dou
 * @ClassName: Schduler.java
 * @Package: com.dou.schdule
 * @TODO: 当时任务
 * @Date: 2019-07-17 17:15:22.703
 */
@Component
@Slf4j
public class Schduler {
	
	
//	@Scheduled(cron = "0/5 * * * * ?")
	public void log4dou () {
		log.info("定时任务启动了，现在时间: {}", EasyUtils.dateFormat(new Date(), "HH:mm:ss"));
	}

}


