package com.ohraymaster.message.dto;

import lombok.Data;

import java.util.Map;

/**
 * Created by ray on 18-7-18.
 */
@Data
public class SmsSendRequest {
    private String templateId;
    private String mobile;
    private Map<String, Object> params;
}
