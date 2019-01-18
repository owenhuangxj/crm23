package com.ss.crm.util;

import com.ss.crm.entity.Message;
import com.ss.crm.entity.User;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/websocket", configurator = GetHttpSessionConfigurator.class)
//@ServerEndpoint(value = "/websocket")
@Component
public class Websocket {
	private static int onlineCount = 0;
	private static Map<User, Websocket> clients = new ConcurrentHashMap<>();
//	private static Set<String> accounts = new HashSet<String>();
	private Session session;
	private User user;
	private static int id = 0;

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) throws IOException {
		HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		user = (User) httpSession.getAttribute("user");
		List<Message> list = new ArrayList<Message>();
//		admin.setMsgs(list);

		this.session = session;

		addOnlineCount();
		System.out.println("clients : "+clients);
		clients.put(user, this);
//		accounts.add(user.getAdminAccount());
//		httpSession.setAttribute("accounts", accounts);
		System.out.println(clients);
		System.out.println("已连接");
	}

	@OnClose
	public void onClose() throws IOException {
		clients.remove(user);
		subOnlineCount();
	}

	@OnMessage
	public void onMessage(String message) throws IOException {
		// 接收的message参数是json对象。
		JSONObject jsonTo = JSONObject.fromObject(message);

			Message msg = new Message();
			msg.setMsgFrom(user.getUserId());
			msg.setText(jsonTo.get("text").toString());
			msg.setMsgTo(Integer.parseInt(jsonTo.get("To").toString()));
//			admin.getMsgs().add(mes);
			sendMessageTo(jsonTo.get("text").toString(), Integer.parseInt(jsonTo.get("To").toString()));
			System.out.println(jsonTo.toString());
	}

	@OnError
	public void onError(Session session, Throwable error) {
		error.printStackTrace();
	}

	public void sendMessageTo(String message, int To) throws IOException {
		for (Websocket item : clients.values()) {
			if (item.user.getUserId() == To)
				item.session.getAsyncRemote().sendText(message + "," + user.getUserId());
		}
	}

	public void sendMessageAll(String message) throws IOException {
		for (Websocket item : clients.values()) {
			item.session.getAsyncRemote().sendText(message);
		}
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		Websocket.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		Websocket.onlineCount--;
	}

	public static synchronized Map<User, Websocket> getClients() {
		return clients;
	}
}
