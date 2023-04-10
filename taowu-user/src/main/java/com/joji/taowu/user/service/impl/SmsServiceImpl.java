package com.joji.taowu.user.service.impl;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.joji.taowu.user.service.SmsService;
import org.springframework.stereotype.Service;


/**
 * 阿里短信服务
 * */
@Service
public class SmsServiceImpl implements SmsService {

    /**
     * 阿里用户accessKeyId
     * */
    private static final String accessKeyId = "LTAI5tR6QcChVM9B4g7A9Z84";

    /**
     * 阿里用户accessKeySecret
     * */
    private static final String accessKeySecret = "Udm8lRbwVAugkXTJlgdOAkh66LFlk1";


    /**
     * 发送Sms短信服务
     * */
    @Override
    public SendSmsResponse sendSms(String phoneNumber, String signName, String templateCode, String templateParam) throws ClientException {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(phoneNumber);
        request.setSignName(signName);
        request.setTemplateCode(templateCode);
        request.setTemplateParam(templateParam);
        SendSmsResponse response = client.getAcsResponse(request);
        return response;
    }
}
