package com.dou.util;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
	/**
	 * HTTP请求工具类
	 * 
	 * @author 华文
	 * @date 2019年7月5日
	 * @version 1.0
	 */
	public class HttpClientUtil {

	    /**
	     * Get请求
	     * 
	     * @param url
	     * @return
	     * @throws Exception
	     */
	    public static String httpGet(String url) throws Exception {
	        HttpClient client = new HttpClient();
	        GetMethod method = new GetMethod(url);
	        // 设置http请求字符集
	        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
	        client.executeMethod(method);
	        return method.getResponseBodyAsString();
	    }

	    /**
	     * Post请求
	     * 
	     * @param url
	     * @param requestParam
	     * @return
	     * @throws Exception
	     */
	    public static String httpPost(String url, Map<String, String> requestParam) throws Exception {
	        HttpClient client = new HttpClient();
	        PostMethod method = new PostMethod(url);
	        // 设置http请求字符集
	        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
	        // 创建post请求参数数组
	        NameValuePair[] requestParams = new NameValuePair[requestParam.size()];
	        // 遍历param添加参数
	        Iterator<Map.Entry<String, String>> it = requestParam.entrySet().iterator();
	        for (int i = 0; it.hasNext(); i++) {
	            Map.Entry<String, String> entry = it.next();
	            requestParams[i] = new NameValuePair(entry.getKey(), entry.getValue());
	        }
	        // 设置请求内容
	        method.setRequestBody(requestParams);
	        client.executeMethod(method);
	        return method.getResponseBodyAsString();
	    }
	    
	    
	    /**
	     * Post请求
	     * 
	     * @param url
	     * @param requestParam
	     * @return
	     * @throws Exception
	     */
	    public static String httpPostDou(String url, Map<String, String> requestParam) throws Exception {
	        HttpClient client = new HttpClient();
	        PostMethod method = new PostMethod(url);
	        method.setRequestHeader("Content-type", "application/json");
	        // 设置http请求字符集
	        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
	        // 创建post请求参数数组
	        NameValuePair[] requestParams = new NameValuePair[requestParam.size()];
	        // 遍历param添加参数
	        Iterator<Map.Entry<String, String>> it = requestParam.entrySet().iterator();
	        for (int i = 0; it.hasNext(); i++) {
	            Map.Entry<String, String> entry = it.next();
	            requestParams[i] = new NameValuePair(entry.getKey(), entry.getValue());
	        }
	        // 设置请求内容
	        method.setRequestBody(requestParams);
	        client.executeMethod(method);
	        return method.getResponseBodyAsString();
	    }
	    
	    
	    
	    
	    

	}

