package framework.springboot.consumer;

import com.alibaba.fastjson.JSON;
import framework.springboot.util.QueueList;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MessageHandler {
    @JmsListener(destination = QueueList.DESTINATION1)
    public void receiveQueue(String text) {
        Map<String, Object> map = JSON.parseObject(text);
        System.out.println("testActiveMQ:" + map);
    }
}
