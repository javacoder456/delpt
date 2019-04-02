package com.zh.common.socket;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

/**
 * ClassName: HttpSessionConfig
 * Function: <p>  将httpRequest的session存入webSocket的session内 </p>
 * date: 2018年10月10日 10:30:31
 *
 * @author hzhang
 * @version 1.0.0
 */
public class HttpSessionConfig extends Configurator implements HttpSessionListener {

    @Override
    public void modifyHandshake(ServerEndpointConfig sec,  HandshakeRequest request,
                                HandshakeResponse response) {
        // 获取当前Http连接的session
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        // 将http session信息注入websocketSession
        sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
    }


    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
