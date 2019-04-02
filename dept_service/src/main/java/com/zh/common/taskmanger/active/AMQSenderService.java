package com.zh.common.taskmanger.active;

import com.zh.entity.MQParamDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * ClassName: AMQSenderService
 * Function: <p> activemq生产者 </p>
 * date: 2018年9月7日 10:30:31
 *
 * @author hzhang
 * @version 1.0.0
 */

@Component("aMQSenderService")
public class AMQSenderService {

    private static final Logger logger = LoggerFactory.getLogger(AMQSenderService.class);

    @Resource(name="jmsTemplate")
    private JmsTemplate jmsTemplate;
    @Resource(name="destination")
    private Destination destination;

    //向特定的队列发送消息
    public void sendMsg(final MQParamDTO paramDTO) {

        try {
            logger.info("send queue: {} to msg:{}", destination, paramDTO.toString());
            jmsTemplate.send(destination, new MessageCreator() {

                @Override
                public Message createMessage(Session session)
                        throws JMSException {
                    ObjectMessage message = session.createObjectMessage();
                    message.setObject(paramDTO);
                    return message;
                }
            });

        } catch (Exception ex) {
            logger.error("error message：{}", destination, paramDTO.toString());
        }

    }
}