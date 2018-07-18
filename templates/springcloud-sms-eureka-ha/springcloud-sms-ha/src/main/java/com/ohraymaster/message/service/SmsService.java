package com.ohraymaster.message.service;

import com.ohraymaster.message.dto.SmsSendRequest;
import com.ohraymaster.message.dto.SmsSendResponse;

/**
 * Created by ray on 18-7-18.
 */
public interface SmsService {
    public SmsSendResponse send(SmsSendRequest request);
}
