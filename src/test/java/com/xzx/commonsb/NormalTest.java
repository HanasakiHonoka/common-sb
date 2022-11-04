package com.xzx.commonsb;

import com.aliyuncs.AcsResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alinlp.model.v20200629.GetPosChEcomRequest;
import com.aliyuncs.alinlp.model.v20200629.GetPosChEcomResponse;
import com.aliyuncs.alinlp.model.v20200629.GetSaChGeneralRequest;
import com.aliyuncs.alinlp.model.v20200629.GetSaChGeneralResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class NormalTest {
    @Test
    public void TestCloud() throws ClientException {
        DefaultProfile defaultProfile = DefaultProfile.getProfile(
                "cn-hangzhou",
                "LTAI5tBtq3HHxR9nhGfopdEY",
                "uAPXNvRCie3tRGfsIgstJF4fSNn5gf");


        IAcsClient client = new DefaultAcsClient(defaultProfile);
        GetSaChGeneralRequest request = new GetSaChGeneralRequest();
        request.setSysEndpoint("alinlp.cn-hangzhou.aliyuncs.com");
        request.setServiceCode("alinlp");
        request.setText("这电影差强人意");
        long start = System.currentTimeMillis();
        GetSaChGeneralResponse response = client.getAcsResponse(request);
        System.out.println(response.hashCode());
        System.out.println(response.getRequestId() + "\n" + response.getData() + "\n" + "cost:" + (System.currentTimeMillis()- start));
    }

    @Test
    public void testMod() {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {
            Random random = new Random();
            long l = random.nextLong();
            if (l < 0) l = -l;
            long m = l % 1;
            //System.out.println();
        }

        long end = System.currentTimeMillis();
        System.out.println("time cost: " + (end - start));
    }

}
