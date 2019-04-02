package com.zh.common.taskmanger.active;

import com.alibaba.fastjson.JSON;
import com.zh.entity.MQParamDTO;
import com.zh.entity.Message;
import com.zh.common.CommonConstant;
import com.zh.common.socket.WebSocketUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * ClassName: AQMDealMsgService
 * Function: <p> activemq处理消息 </p>
 * date: 2018年9月7日 10:30:31
 *
 * @author hzhang
 * @version 1.0.0
 */
@Component
public class AQMDealMsgService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理业务消息
     * @param dto 消息对象
     */
    public void dealMsg(MQParamDTO dto){
        logger.info("start deal msg===========");
        if(CommonConstant.MSG_TIP.equals(dto.getType())){
            pushMsg(dto);
        }
    }

    private void pushMsg(MQParamDTO dto){
        String message = dto.getData().toString();
        WebSocketUtils.send(JSON.parseObject(message, Message.class));
        logger.info("deal msg end==========");
    }

}
