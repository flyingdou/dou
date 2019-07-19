package com.dou.pojo;

import java.util.Date;

import lombok.Data;

/**
 * @Author: dou
 * @ClassName: ErrorRecord.java
 * @Package: com.dou.pojo
 * @TODO: 实体类
 * @Date: 2019-07-19 16:40:14.623
 */
@Data
public class ErrorRecord {
	
    private Integer id;

    private String deviceNo;

    private Integer errorType;

    private Integer status;

    private Date createTime;

    private Integer isDel;

}