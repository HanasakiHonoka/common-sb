package com.xzx.commonsb.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alinlp.model.v20200629.GetSaChGeneralRequest;
import com.aliyuncs.alinlp.model.v20200629.GetSaChGeneralResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

import com.tencentcloudapi.nlp.v20190408.NlpClient;
import com.tencentcloudapi.nlp.v20190408.models.*;
import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;;
@Slf4j
public class SentimentAnalysisUtil {

    public static String getTxAnalysisRes(String text, String mode, String secretId, String secretKey) {
        String res = null;

        try {

            Credential cred = new Credential(secretId, secretKey);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("nlp.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            NlpClient client = new NlpClient(cred, "ap-guangzhou", clientProfile);

            SentimentAnalysisRequest req = new SentimentAnalysisRequest();
            req.setText(text);
            req.setMode(mode);

            SentimentAnalysisResponse resp = client.SentimentAnalysis(req);

            res = SentimentAnalysisResponse.toJsonString(resp);
        } catch (TencentCloudSDKException e) {
            log.error(e.toString());
        }
        return res;
    }

    public static String getAliAnalysisRes(String text, String accessKeyId, String secret) {
        String res = null;
        DefaultProfile defaultProfile = DefaultProfile.getProfile(
                "cn-hangzhou",
                accessKeyId,
                secret);


        IAcsClient client = new DefaultAcsClient(defaultProfile);
        GetSaChGeneralRequest request = new GetSaChGeneralRequest();
        request.setSysEndpoint("alinlp.cn-hangzhou.aliyuncs.com");
        request.setServiceCode("alinlp");
        request.setText(text);
        try {
            GetSaChGeneralResponse response = client.getAcsResponse(request);
            res = response.getData();
        } catch (ClientException e) {
            log.error(e.toString());
        }
        return res;
    }


}