package com.dou.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dou.DouApplication;
import com.dou.api.baidu.manager.BaiDuManager;
import com.dou.api.baidu.request.AccessTokenRequest;
import com.dou.common.Constant;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: dou
 * @ClassName: DouTest.java
 * @Package: com.dou.test
 * @TODO: TODO
 * @Date: 2019-07-11 10:57:05.960
 */
@SpringBootTest(classes = DouApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class DouTest {
	
	/**
	 * 获取access_token
	 */
	@Test
	public void getAccessToken () {
		AccessTokenRequest accessTokenRequest = 
				new AccessTokenRequest(Constant.BAIDU_ACCECC_TOKEN_URL, 
						Constant.BAIDU_GRANT_TYPE, Constant.BAIDU_API_KEY, 
						Constant.BAIDU_SECRET_KEY);
		String result = BaiDuManager.getAccessToken(accessTokenRequest);
		log.info("result: {}", result);
	}

}


