package com.dou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dou.service.ErrorRecordService;

/**
 * @Author: dou
 * @ClassName: ErrorRecordController.java
 * @Package: com.dou.controller
 * @TODO: TODO
 * @Date: 2019-07-18 10:34:13.132
 */
@RestController
public class ErrorRecordController {
	
	@Autowired
	private ErrorRecordService errorRecordService;
	
	
	/**
	 * 测试sql
	 * @return
	 */
	@RequestMapping("/getAll")
	public String getAll () {
		JSONObject result = new JSONObject();
		result.fluentPut("success", true)
		      .fluentPut("data", errorRecordService.getAll())
		      ;
		return JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm:ss");
	}
	

}


