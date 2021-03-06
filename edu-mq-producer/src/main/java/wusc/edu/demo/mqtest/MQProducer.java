/**
 * 基于Dubbo的分布式系统架构视频教程，吴水成，wu-sc@foxmail.com，学习交流QQ群：367211134 .
 */
package wusc.edu.demo.mqtest;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import wusc.edu.demo.mqtest.params.MailParam;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @描述: MQ消息生产者 .
 * @作者: WuShuicheng .
 * @创建时间: 2015-3-17,下午10:55:30 .
 * @版本号: V1.0 .
 */
@Service("mqProducer")
public class MQProducer {

    @Autowired
    private JmsTemplate activeMqJmsTemplate;

    /**
     * 发送消息.
     *
     * @param mail
     */
    public void sendMessage(final MailParam mail) {
        activeMqJmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(JSONObject.toJSONString(mail));
            }
        });
    }

}
