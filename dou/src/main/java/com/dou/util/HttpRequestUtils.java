package com.dou.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

@SuppressWarnings("deprecation")
public class HttpRequestUtils {
	private static Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class); // 日志记录

	/**
	 * httpPost
	 * 
	 * @param url
	 *            路径
	 * @param jsonParam
	 *            参数
	 * @return
	 */
	public static JSONObject httpPost(String url, JSONObject jsonParam) {
		return httpPost(url, jsonParam, false);
	}

	/**
	 * post请求
	 * 
	 * @param url
	 *            url地址
	 * @param jsonParam
	 *            参数
	 * @param noNeedResponse
	 *            不需要返回结果
	 * @return
	 */
	@SuppressWarnings("resource")
	public static JSONObject httpPost(String url, JSONObject jsonParam, boolean noNeedResponse) {
		// post请求返回结果
		DefaultHttpClient httpClient = new DefaultHttpClient();
		JSONObject jsonResult = null;
		HttpPost method = new HttpPost(url);
		try {
			if (null != jsonParam) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				method.setEntity(entity);
			}
			HttpResponse result = httpClient.execute(method);
			url = URLDecoder.decode(url, "UTF-8");
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				String str = "";
				try {
					/** 读取服务器返回过来的json字符串数据 **/
					str = EntityUtils.toString(result.getEntity());
					if (noNeedResponse) {
						return null;
					}
					/** 把json字符串转换成json对象 **/
					jsonResult = JSONObject.parseObject(str);
				} catch (Exception e) {
					logger.error("post请求提交失败:" + url, e);
				}
			}
		} catch (IOException e) {
			logger.error("post请求提交失败:" + url, e);
		}
		return jsonResult;
	}

	/**
	 * 发送get请求
	 * 
	 * @param url
	 *            路径
	 * @return
	 */
	@SuppressWarnings("resource")
	public static JSONObject httpGet(String url) {
		// get请求返回结果
		JSONObject jsonResult = null;
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			// 发送get请求
			HttpGet request = new HttpGet(url);
			HttpResponse response = client.execute(request);

			/** 请求发送成功，并得到响应 **/
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				/** 读取服务器返回过来的json字符串数据 **/
				String strResult = EntityUtils.toString(response.getEntity());
				/** 把json字符串转换成json对象 **/
				jsonResult = JSONObject.parseObject(strResult);
				url = URLDecoder.decode(url, "UTF-8");
			} else {
				logger.error("get请求提交失败:" + url);
			}
		} catch (IOException e) {
			logger.error("get请求提交失败:" + url, e);
		}
		return jsonResult;
	}

	/**
	 * 发送get请求
	 * 
	 * @param url
	 *            路径
	 * @return
	 */
	@SuppressWarnings("resource")
	public static JSONObject httpGetx(String url) {
		// get请求返回结果
		JSONObject jsonResult = null;
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			// 发送get请求
			HttpGet request = new HttpGet(url);
			HttpResponse response = client.execute(request);

			/** 请求发送成功，并得到响应 **/
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				/** 读取服务器返回过来的json字符串数据 **/
				String strResult = EntityUtils.toString(response.getEntity());
				int end = strResult.indexOf("<html>");
				strResult = strResult.substring(0, end);
				/** 把json字符串转换成json对象 **/
				System.err.println(strResult);
				jsonResult = JSONObject.parseObject(strResult);
				url = URLDecoder.decode(url, "UTF-8");
			} else {
				logger.error("get请求提交失败:" + url);
			}

		} catch (IOException e) {
			logger.error("get请求提交失败:" + url, e);
		}
		return jsonResult;
	}

	/**
	 * http请求
	 * 
	 * @param url
	 * @param param
	 * @return
	 */
	@SuppressWarnings("resource")
	public static String httpReques(String url, String param) {
		// post请求返回结果
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost method = new HttpPost(url);
		try {
			HttpResponse result = httpClient.execute(method);
			url = URLDecoder.decode(url, "UTF-8");
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				String str = "";
				try {
					/** 读取服务器返回过来的json字符串数据 **/
					str = EntityUtils.toString(result.getEntity());
					/** 把json字符串转换成json对象 **/
					return str;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * httpclient传输文件流
	 * 
	 * @return
	 */
	public static String httpRequestUploadFile(String url, File file) {
		String result = "";
		try {
			// 创建HttpClient对象
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			MultipartEntityBuilder mEntityBuilder = MultipartEntityBuilder.create();
			mEntityBuilder.addBinaryBody("file", file);
			httpPost.setEntity(mEntityBuilder.build());
			CloseableHttpResponse response = httpclient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity resEntity = response.getEntity();
				result = EntityUtils.toString(resEntity);
				// 消耗掉response
				EntityUtils.consume(resEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * http请求
	 * 
	 * @param url
	 * @param param
	 * @return
	 */
	public static String httpRequestToString(String url, String param) {
		// 创建httpclient工具对象
		HttpClient client = new HttpClient();
		// 创建post请求方法
		PostMethod myPost = new PostMethod(url);
		try {
			// 设置post请求参数
			// 设置请求头部类型
			myPost.setRequestHeader("Content-Type", "text/xml");
			myPost.setRequestHeader("charset", "utf-8");
			// 设置请求体，即xml文本内容，一种是直接获取xml内容字符串，一种是读取xml文件以流的形式
			myPost.setRequestBody(param);
			int statusCode = client.executeMethod(myPost);
			// url = URLDecoder.decode(url, "UTF-8");
			// 请求发送成功，并得到响应
			if (statusCode == 200) {
				String str = "";
				try {
					// 读取服务器返回过来的json字符串数据
					// str = EntityUtils.toString(result.getEntity());
					InputStream inputStream = myPost.getResponseBodyAsStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
					StringBuffer stringBuffer = new StringBuffer();
					while ((str = br.readLine()) != null) {
						stringBuffer.append(str);
					}
					return stringBuffer.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 发送http请求
	 * 
	 * @param url
	 * @param param
	 * @return
	 */
	public static String httpRequest(String url, String param) {
		// 创建httpclient工具对象
		HttpClient client = new HttpClient();
		// 创建post请求方法
		PostMethod myPost = new PostMethod(url);
		try {
			// 设置post请求参数
			// 设置请求头部类型
			myPost.setRequestHeader("Content-Type", "text/xml");
			myPost.setRequestHeader("charset", "utf-8");
			// 设置请求体，即xml文本内容，一种是直接获取xml内容字符串，一种是读取xml文件以流的形式
			myPost.setRequestBody(param);
			int statusCode = client.executeMethod(myPost);
			// 请求发送成功，并得到响应
			if (statusCode == 200) {
				try {
					// 读取服务器返回过来的json字符串数据
					String str = "";
					InputStream inputStream = myPost.getResponseBodyAsStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
					StringBuffer stringBuffer = new StringBuffer();
					while ((str = br.readLine()) != null) {
						stringBuffer.append(str);
					}
					return stringBuffer.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
