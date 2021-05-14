package com.xzx.commonsb.util;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

import com.tencentcloudapi.nlp.v20190408.NlpClient;
import com.tencentcloudapi.nlp.v20190408.models.*;;

public class SentimentAnalysisUtil {

    public static String getAnalysisRes(String text, String mode) {
        String res = null;

        try {

            Credential cred = new Credential("AKIDKn0vHQZn4oHQItMExPaVhbDZcQwB38nC", "MVT8VbRxDDvqVFTQaU7WhdHej7F5h2sS");

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
            System.out.println(e.toString());
        }
        return res;
    }


}