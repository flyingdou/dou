package com.dou.api.baidu.request;

import com.dou.util.EasyUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: dou
 * @ClassName: AccessTokenRequest.java
 * @Package: com.dou.api.baidu.request
 * @TODO: 请求实体类
 * @Date: 2019-07-11 09:19:15.893
 */
@Data
@Slf4j
@AllArgsConstructor
public class AccessTokenRequest {
	
	/**
	 * 请求url
	 */
	private String requst_url;
	
	/**
	 * 授权类型
	 */
	private String grant_type;
	
	/**
	 * api_key
	 */
	private String api_key;
	
	/**
	 * secret_key
	 */
	private String secret_key;
	
	/**
	 * 获取请求链接
	 * @return
	 */
	public String getAccessTokenUrl () {
		String access_url_str = this.getRequst_url();
		String access_url = EasyUtils.stringFormat(access_url_str, 
				this.getGrant_type(), this.getApi_key(), this.getSecret_key());
		log.info("access_url: {}", access_url);
		return access_url;
	}




	

}
