package com.zh.common.taskmanger.active;

import com.zh.entity.MQParamDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * ClassName: QueueMessageListener
 * Function: <p> 监听器 消费该条消息</p>
 * date: 2018年9月7日 10:31:57
 *
 * @author hzhang
 * @version 1.0.0
 */

@Component("queueMessageListener")
public class QueueMessageListener implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(QueueMessageListener.class);

    @Resource
    private AQMDealMsgService aqmDealMsgService;

    @Override
    public void onMessage(Message message) {

        try {
            //接受Object类型的消息
            ObjectMessage objMsg = (ObjectMessage) message;
            MQParamDTO dto = (MQParamDTO)objMsg.getObject();
            if(dto != null){
                //do something
                aqmDealMsgService.dealMsg(dto);
            }

        } catch (JMSException e) {
            logger.error(e.getErrorCode(),e.getMessage());
            return;
        }
    }
}