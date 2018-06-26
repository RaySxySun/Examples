package controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class SampleController {
    public static Logger logger = LoggerFactory.getLogger(SampleController.class);

    @Autowired
    private KafkaTemplate<String, String> template;

    @RequestMapping("/send")
    @ResponseBody
    String send(String topic, String key, String data) {
        template.send(topic, key, data);
        return "success";
    }

    @KafkaListener(id = "t1", topics = "t1")
    public void listenT1(ConsumerRecord<?, ?> consumerRecord) throws Exception {
        logger.info("{}-{}-{}", consumerRecord.topic(), consumerRecord.key(), consumerRecord.value());
    }

    @KafkaListener(id = "t2", topics = "t2")
    public void listenT2(ConsumerRecord<?, ?> consumerRecord) throws Exception {
        logger.info("{}-{}-{}", consumerRecord.topic(), consumerRecord.key(), consumerRecord.value());
    }

}
