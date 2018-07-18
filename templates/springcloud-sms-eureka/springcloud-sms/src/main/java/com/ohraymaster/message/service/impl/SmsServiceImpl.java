package com.ohraymaster.message.service.impl;

import com.ohraymaster.message.dao.MessageTemplateDao;
import com.ohraymaster.message.dto.SmsSendRequest;
import com.ohraymaster.message.dto.SmsSendResponse;
import com.ohraymaster.message.entity.MessageTemplate;
import com.ohraymaster.message.service.SmsService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by ray on 18-7-18.
 */
@Slf4j
@Service
public class SmsServiceImpl implements SmsService {
    @Autowired
    private MessageTemplateDao messageTemplateDao;

    @Autowired
    private Configuration configuration;

    @Override
    @SneakyThrows
    public SmsSendResponse send(SmsSendRequest request) {
        MessageTemplate messageTemplate = messageTemplateDao.get(request.getTemplateId());
        String templateContent = messageTemplate.getContent();
        Template template = new Template(request.getTemplateId(), new StringReader(templateContent), configuration);
        StringWriter out = new StringWriter();
        template.process(request.getParams(), out);
        String content = out.toString();
        return doSend(request.getMobile(), content);
    }

    private SmsSendResponse doSend(String mobile, String content) {
        SmsSendResponse response= new SmsSendResponse();
        response.setCode("200");
        response.setMessage("sent successfully");
        log.info("sent sms, mobile number: {}, status: {}", mobile, content, response.getCode());
        return response;
    }
}
