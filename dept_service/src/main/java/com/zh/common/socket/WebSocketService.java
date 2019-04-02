package com.zh.common.socket;

import com.alibaba.fastjson.JSON;
import com.zh.entity.Message;
import com.zh.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * ClassName: WebSocketServer
 * Function: <p>webSocket服务端点</p>
 * date: 2018年10月10日 10:30:31
 *
 * @author hzhang
 * @version 1.0.0
 */
@ServerEndpoint(value ="/websocket.do", configurator = HttpSessionConfig.class)
public class WebSocketService {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketService.class);
    private Session session;
    private HttpSession httpSession;

    @OnOpen
    public void onOpen(Session session, EndpointConfig config){
        this.session = session;
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        // 获取session对象 SObject(这个就是java web登入后的保存的session对象，此处为用户信息，包含了userId)
        SysUser user = (SysUser) this.httpSession.getAttribute("user");
        WebSocketUtils.add(user.getUserid().toString(), this.session);
        logger.info("open connect",user.getUserid());
    }

    @OnClose
    public void onClose(){
        try {
            SysUser user = (SysUser) this.httpSession.getAttribute("user");
            WebSocketUtils.remove(user.getUserid().toString());
            logger.info("close connect",user.getUserid());
        }catch (Exception e){
            logger.info("close connect error");
        }
    }

    @OnError
    public void onError(Throwable t) {

    }

    @OnMessage
    public void onMessage(String message){
        WebSocketUtils.send(JSON.parseObject(message, Message.class));
    }

}
