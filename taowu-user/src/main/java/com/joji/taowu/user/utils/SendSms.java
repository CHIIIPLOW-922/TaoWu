package com.joji.taowu.user.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;

@Slf4j
@Component
public class SendSms {
    @Resource
    RedisSmsCache redisSmsCache;

    /**
     * 阿里用户accessKeyId
     */
    private static final String accessKeyId = "LTAI5tR6QcChVM9B4g7A9Z84";

    /**
     * 阿里用户accessKeySecret
     */
    private static final String accessKeySecret = "Udm8lRbwVAugkXTJlgdOAkh66LFlk1";

    /**
     * 短信模版编码
     */
    private static final String templateCode = "SMS_275790104";

    /**
     * 签名名称
     */
    private static final String signName = "淘物商城";

    /**
     * 6位随机数字验证码生成
     */
    public String verifyNumberGenerator() {
        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000;
        String result = String.format("%06d", randomNumber);
        return result;
    }


    /**
     * 发送Sms短信服务
     */
    public SendSmsResponse sendSms(String phoneNumber) throws ClientException {
        String verifyNumber = verifyNumberGenerator();

        try {
            redisSmsCache.put(phoneNumber,verifyNumber);
        }catch (Exception e){
            log.info("短信缓存失败");
            e.printStackTrace();
        }
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(phoneNumber);
        request.setSignName(signName);
        request.setTemplateCode(templateCode);
        String templateParam = "{\"code\":\"" + verifyNumber + "\"}";
        request.setTemplateParam(templateParam);
        SendSmsResponse response = client.getAcsResponse(request);
        return response;
    }
}
