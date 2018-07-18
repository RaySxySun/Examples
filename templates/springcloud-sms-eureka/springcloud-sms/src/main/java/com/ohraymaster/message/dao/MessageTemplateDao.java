package com.ohraymaster.message.dao;

import com.ohraymaster.message.entity.MessageTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by ray on 18-7-18.
 */
@Repository
public class MessageTemplateDao {
    public MessageTemplate get(String id) {
        MessageTemplate messageTemplate = new MessageTemplate();
        messageTemplate.setId(id);
        messageTemplate.setName("sms validation!");
        messageTemplate.setContent("pin code is ${code}, please input the code to get the access.");
        return messageTemplate;
    }
}
