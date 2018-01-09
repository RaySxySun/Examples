package mq.springbootActiveMQ;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @JmsListener(destination = "mytest.queue")
    @SendTo("out.queue")
    public String receiveQueue(String text) {
        System.out.println("Consumer receive the message: " + text);
        return "return message: " + text;
    }
}
