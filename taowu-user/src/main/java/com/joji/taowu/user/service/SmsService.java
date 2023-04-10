package com.joji.taowu.user.service;


import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;

public interface SmsService {


    SendSmsResponse sendSms(String phoneNumbers, String signName, String templateCode, String templateParam) throws ClientException;
}
