package com.ohraymaster.message.service;


import com.ohraymaster.message.dto.SmsSendRequest;
import com.ohraymaster.message.dto.SmsSendResponse;

public interface SmsService {
	public SmsSendResponse send(SmsSendRequest request);
}
