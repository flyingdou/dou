package com.dou.dao;

import java.util.List;
import java.util.Map;

import com.dou.pojo.ErrorRecord;

public interface ErrorRecordMapper {
	
    int insertSelective(ErrorRecord record);
    
    List<Map<String, Object>> getAll();
}