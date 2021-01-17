package com.example.maven.springbootlearn.websocket_demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Service;

@Service
@ServerEndpoint("/ws")
public class WebServer {

    public static List<WebServer> serverSource = Collections.synchronizedList(new ArrayList<>());

    public static void sendMessage1(final String message){
        serverSource.forEach(se->{
            se.sendMessage(message);
        });
    }

    private Session session;

    @OnOpen
    public void open(Session session) {
        serverSource.add(this);
        this.session = session;
    }

    @OnClose
    public void onClose() {
        serverSource.remove(this);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    public void sendMessage( String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
