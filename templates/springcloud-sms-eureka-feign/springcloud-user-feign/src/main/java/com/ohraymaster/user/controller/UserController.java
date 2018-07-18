package com.ohraymaster.user.controller;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.ohraymaster.user.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MessageService messageService;

    @RequestMapping("test")
    @ResponseBody
    public String test() {
        return "success";
    }

    @RequestMapping(value= {"register/sms"})
    public ResponseEntity<SmsSendResponse> sms(String mobile){
        Random random = new Random();
        int next = random.nextInt(10000000);
        String code = ""+(10000000-next);
        ResponseEntity<SmsSendResponse> response = doSend(mobile, code);
        return response;
    }

    public ResponseEntity<SmsSendResponse> doSend(String  mobile,String code) {
        final String sendUrl = "http://message-service/message/sms/send";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("mobile", mobile);
        map.add("templateId", "CHECK_CODE");
        map.add("params['code']", code);
        log.info("发送参数：{}",map);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<SmsSendResponse> response = restTemplate.postForEntity(sendUrl, request, SmsSendResponse.class);
        return response;
    }

    @RequestMapping(value= {"register/sms2"})
    public ResponseEntity<SmsSendResponse> sms2(String mobile) {
        Random random = new Random();
        int next = random.nextInt(10000000);
        String code = "" + (10000000 - next);
        ResponseEntity<SmsSendResponse> response = doSendFeign(mobile, code);
        return response;
    }

    private ResponseEntity<SmsSendResponse> doSendFeign(String mobile, String code) {
        SmsSendRequest request = new SmsSendRequest();
        request.setTemplateId("CHECK_CODE");
        request.setMobile("13828284756");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("code", code);
        request.setParams(params);
        return messageService.send(request);
    }

    @Data
    public static class SmsSendResponse {
        private String message;
        private String code;
    }

    @Data
    public static class SmsSendRequest{
        private String templateId;
        private String mobile;
        private Map<String, Object> params;
    }

}
