package com.ohraymaster.user.service;

import com.ohraymaster.user.controller.UserController;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ray on 18-7-18.
 */
@FeignClient("message-service")
public interface MessageService {
    @RequestMapping(method= RequestMethod.POST, value="/message/sms/send2")
    public ResponseEntity<UserController.SmsSendResponse> send(UserController.SmsSendRequest request);
}
