package com.ha.ltschat.config;

import com.ha.ltschat.model.User;
import com.ha.ltschat.model.Watch;
import com.ha.ltschat.service.MsgService;
import com.ha.ltschat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.json.JSONObject;

import java.util.*;

@Component
public class MyHandler implements WebSocketHandler {
    @Autowired
    private UserService userService;
    @Autowired
    private MsgService msgService;
    private Map<String, WebSocketSession> sessionMap = new HashMap<>();
    private List<Watch> watchMap = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = (String) session.getAttributes().get("userId");
        sessionMap.put(userId, session);
        for(Watch watch :watchMap){
            if(userId.equals(watch.getWatchedId())){
                WebSocketSession out=getSession(watch.getUserId());
                JSONObject json = new JSONObject();
                json.put("type", "online");
                json.put("content", watch.getWatchedId());
                TextMessage message = new TextMessage(json.toString());
                out.sendMessage(message);
            }
        }
        JSONObject json = new JSONObject();
        json.put("type", "ready");
        json.put("content", "ready");
        TextMessage res = new TextMessage(json.toString());
        session.sendMessage(res);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String userId = (String) session.getAttributes().get("userId");
        sessionMap.remove(userId);
        Iterator<Watch> iterator = watchMap.iterator();
        while (iterator.hasNext()) {
            Watch watch = iterator.next();
            if (userId.equals(watch.getUserId())) {
                iterator.remove();
            }
        }
        for(Watch watch :watchMap){
            if(userId.equals(watch.getWatchedId())){
                WebSocketSession out=getSession(watch.getUserId());
                JSONObject json = new JSONObject();
                json.put("type", "offline");
                json.put("content", watch.getWatchedId());
                TextMessage message = new TextMessage(json.toString());
                out.sendMessage(message);
            }
        }

    }
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String userId = (String) session.getAttributes().get("userId");
        String messagePayload = message.getPayload().toString();
        JSONObject messageJson = new JSONObject(messagePayload);
        String type = messageJson.getString("type");
        String data = messageJson.getString("data");
        if ("watch".equals(type)) {
            Iterator<Watch> iterator = watchMap.iterator();
            while (iterator.hasNext()) {
                Watch watch = iterator.next();
                if (userId.equals(watch.getUserId())) {
                    iterator.remove();
                }
            }
            List<User> students=userService.getStudentsByCourseUuid(data);
            List<User> teachers=userService.getTeachersByCourseUuid(data);
            List<String> online=new ArrayList<>();
            for (User user : students) {
                Watch info =new Watch();
                info.setUserId(userId);
                info.setWatchedId(user.getUuid());
                watchMap.add(info);
                if(getSession(user.getUuid())!=null){
                    online.add(user.getUuid());
                }
            }
            for (User user : teachers) {
                Watch info =new Watch();
                info.setUserId(userId);
                info.setWatchedId(user.getUuid());
                watchMap.add(info);
                if(getSession(user.getUuid())!=null){
                    online.add(user.getUuid());
                }
            }
            JSONObject json = new JSONObject();
            json.put("type", "newInfo");
            json.put("content", online);
            TextMessage res = new TextMessage(json.toString());
            session.sendMessage(res);
        }
        if ("msg".equals(type)){
            WebSocketSession out=getSession(data);
            if(out!=null){
                JSONObject json = new JSONObject();
                json.put("type", "newMsg");
                json.put("content", userId);
                TextMessage res = new TextMessage(json.toString());
                out.sendMessage(res);
                json = new JSONObject();
                int num= msgService.checkUnreadNum(data);
                json.put("type", "unreadNum");
                json.put("content", num);
                res = new TextMessage(json.toString());
                session.sendMessage(res);
            }
        }
        if ("check".equals(type)){
            int num= msgService.checkUnreadNum(data);
            JSONObject json = new JSONObject();
            json.put("type", "unreadNum");
            json.put("content", num);
            TextMessage res = new TextMessage(json.toString());
            session.sendMessage(res);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
    private WebSocketSession getSession(String userId) {
        return sessionMap.get(userId);
    }


}