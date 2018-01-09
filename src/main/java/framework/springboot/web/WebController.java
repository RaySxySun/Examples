package framework.springboot.web;

import com.alibaba.fastjson.JSON;
import framework.springboot.util.QueueList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WebController {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("/")
    public String sayHello() throws IOException{
        Map<String, String> msg = new HashMap<>();
        msg.put("key", "hi, activeMQ");
        jmsMessagingTemplate.convertAndSend(QueueList.DESTINATION1, JSON.toJSONString(msg));
        return "Hello, active mq";
    }
}
