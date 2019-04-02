package com.zh.common.socket;

import com.alibaba.fastjson.JSONObject;
import com.zh.entity.Message;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName: WebSocketUtils
 * Function: <p>  webSocket工具类</p>
 * date: 2018年10月10日 10:30:31
 *
 * @author hzhang
 * @version 1.0.0
 */
public class WebSocketUtils {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketUtils.class);
    private static Map<String, Session> clients = new ConcurrentHashMap<>();

    public static void add(String userId, Session session) {
        // 如果是新 Session，记录进 Map
        boolean isNewUser = true;
        for (Object o : clients.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            String key = (String) entry.getKey();
            if (key.equals(userId)) {
                isNewUser = false;
                break;
            }
        }
        if (isNewUser) {
            clients.put(userId, session);
        }
        logger.info("Current user number: " + clients.size());
    }

    public static void remove(String userId) {
        clients.remove(userId);
    }

    public static void send(Message message) {
        String sendId = message.getSenderid();
        String receiverId = message.getReceiverid();
        String context = JSONObject.toJSONString(message);
        sendToUserMessage(receiverId,sendId,context);
    }

    private static void sendToUserMessage(String receiverId, String sendId,
                                            String message) {
        try{
            if(StringUtils.isBlank(receiverId)){
                if( clients.get(sendId) == null){
                    logger.error("sendId:{} not found",sendId);
                    return;
                }
                clients.get(sendId).getBasicRemote().sendText(message);
            }else{
                if( clients.get(receiverId) == null){
                    logger.error("receiverId:{} not found",receiverId);
                    return;
                }
                clients.get(receiverId).getBasicRemote().sendText(message);
            }
            logger.info("send message:"+ message);
        }catch (Exception e){
            logger.error("send message fail");
            return;
        }
    }
}