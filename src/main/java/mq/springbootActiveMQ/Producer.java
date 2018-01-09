package mq.springbootActiveMQ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service("producer")
public class Producer {
    @Autowired
    private JmsMessagingTemplate jmsTemplates;

    public void sendMessage(Destination destination, final String message) {
        jmsTemplates.convertAndSend(destination, message);
    }

    @JmsListener(destination = "out.queue")
    public void consumerMsg(String text) {
        System.out.println("extract data from out.queue: " + text);
    }

}
