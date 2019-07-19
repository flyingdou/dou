package com.dou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dou.dao.ErrorRecordMapper;
import com.dou.service.ErrorRecordService;

/**
 * @Author: dou
 * @ClassName: ErrorRecordServiceImpl.java
 * @Package: com.dou.service.impl
 * @TODO: serviceimpl
 * @Date: 2019-07-18 10:32:03.321
 */
@Service
public class ErrorRecordServiceImpl implements ErrorRecordService {

	@Autowired
	private ErrorRecordMapper errorRecordMapper;
	
	@Override
	public List<Map<String, Object>> getAll() {
		return errorRecordMapper.getAll();
	}

}


