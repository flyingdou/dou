package com.dou.component.webSocket;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: dou
 * @ClassName: webSocketConfig.java
 * @Package: com.dou.component.webSocket
 * @TODO: webSocketConfig
 * @Date: 2019-07-19 11:29:00.953
 */
@Slf4j
@Component
@ServerEndpoint("/ws/webSocket")
public class webSocketConfig {
	
	
	 //每个客户端都会有相应的session,服务端可以发送相关消息
	 private Session session;
	  
	 //J.U.C包下线程安全的类，主要用来存放每个客户端对应的webSocket连接
	 private static CopyOnWriteArraySet<webSocketConfig> copyOnWriteArraySet = new CopyOnWriteArraySet<webSocketConfig>();

	 
	 
	 /**
	  * 页面发起webSocket连接时，进入这里
	  * @param session
	  */
	 @OnOpen
	 public void onOpen (Session session) {
		 this.session = session;
		 copyOnWriteArraySet.add(this);
		 log.info("webSocket建立了一个新的连接, 总连接数: {}", copyOnWriteArraySet.size());
	 }
	 
	 
	 /**
	  * 页面断开了webSocket连接
	  */
	 @OnClose
	 public void onClose () {
		 copyOnWriteArraySet.remove(this);
		 log.info("webSocket断开了一个连接，总连接数: {}", copyOnWriteArraySet.size());
	 }
	 
	 
	 /**
	  * 客户端发送消息过来，进入这个方法处理
	  * @param message
	 * @throws IOException 
	  */
	 @OnMessage
	 public void onMessage (String message) throws IOException {
		 log.info("客户端发过来的新消息: {}, 我是客户端: {}", message, this.session.getId());
		 // 给客户端回复消息
		 sendMessages(null, "这是服务器的回复消息123");
	 }
	 
	 
	 @OnError
	 public void onError (Session session, Throwable error) {
		 log.info("发生错误: {}, 发生错误的sessionId: {}", error.getMessage(), session.getId());
	 }
	 
	 
	 
	 /**
	  * 向客户端发送消息
	  * @param sessionIds
	  * @param message
	  * @throws IOException
	  */
	 public void sendMessages (List<String> sessionIds, String message) throws IOException {
		 // 给定sessionId，然后根据sessionId推送消息，若未传sessionId，则为广播
		 String sendScope = "";
		 if (sessionIds == null || sessionIds.size() <1 ) {
			 sendScope = "向全部在线客户端推送";
			 log.info("推送范围: {}", sendScope);
			 for (webSocketConfig webSocket : copyOnWriteArraySet) {
				  webSocket.session.getBasicRemote().sendText(message);
			 }
		 } else {
			 sendScope = sessionIds.toString();
			 log.info("推送范围: {}", sendScope);
			 for (String string : sessionIds) {
				 for (webSocketConfig webSocket : copyOnWriteArraySet) {
					  if (StringUtils.equalsIgnoreCase(string, webSocket.session.getId())) {
						  webSocket.session.getBasicRemote().sendText(message);
					  }
				 }
			 }
			
		 }
		 
	 }
	 
	 /**
	  * 如果使用springboot内置tomcat，需要配置，否则不需要
	  * @return
	  */
	 @Bean
	 public ServerEndpointExporter  serverEndpointExporter () {
		 return new ServerEndpointExporter();
	 }

}


