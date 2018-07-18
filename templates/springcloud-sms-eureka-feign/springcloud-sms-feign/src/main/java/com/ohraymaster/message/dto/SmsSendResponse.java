package com.ohraymaster.message.dto;

import lombok.Data;

/**
 * Created by ray on 18-7-18.
 */
@Data
public class SmsSendResponse {
   private String message;
   private String code;
}
