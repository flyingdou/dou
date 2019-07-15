package com.dou.api.baidu.manager;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.dou.api.baidu.request.AccessTokenRequest;
import com.dou.util.HttpRequestUtils;

/**
 * @Author: dou
 * @ClassName: BaiDuManager.java
 * @Package: com.dou.api.baidu.manager
 * @TODO: 与百度api服务交互
 * @Date: 2019-07-11 10:02:56.276
 */
@Component
public class BaiDuManager {
	
	 /**
	  * 获取access_token
	  * @param accessTokenRequest
	  * @return
	  */
	 public static String getAccessToken (AccessTokenRequest accessTokenRequest) {
		 // 先从缓存中查找
		 String access_token = "";
		 JSONObject access_token_json = new JSONObject();
		 if (StringUtils.isNotEmpty(access_token_json.getString("access_token"))
		 && access_token_json.getLongValue("expire_time") < new Date().getTime()) {
			 access_token = access_token_json.getString("access_token");
		 } else {
			 // 暂无有效的access_token，需要请求百度服务器
			 JSONObject access_token_resp = 
					 HttpRequestUtils.httpPost(accessTokenRequest.getAccessTokenUrl(), null);
		     if (access_token_resp.containsKey("access_token")) {
		    	 access_token = access_token_resp.getString("access_token");
		    	 JSONObject baiduAccessToken = new JSONObject();
		    	 baiduAccessToken.fluentPut("access_token", access_token)
		    	                 .fluentPut("expire_time", new Date().getTime() + 1000*60*60*24*30)
		    	                 ;
		     }
		 }
		 return access_token;
	 }

}


