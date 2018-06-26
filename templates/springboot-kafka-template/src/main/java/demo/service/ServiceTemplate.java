package demo.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import demo.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class ServiceTemplate {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    public void send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        log.info("----------------------- send message --------------------");
        log.info(gson.toJson(message));
        kafkaTemplate.send("springboot-kafka-template", gson.toJson(message));
    }
}
