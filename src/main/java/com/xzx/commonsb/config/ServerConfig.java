package com.xzx.commonsb.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Classname ServerConfig
 * @Description
 * @Date 2021/7/4 21:52
 * @Author XZX
 * @Version 1.0
 */
@Component
@Slf4j
public class ServerConfig implements ApplicationListener<WebServerInitializedEvent> {

    private int serverPort;

    public int getPort() {
        return this.serverPort;
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        try {
            InetAddress inetAddress = Inet4Address.getLocalHost();
            this.serverPort = event.getWebServer().getPort();
            log.info("项目启动启动成功！接口文档地址: http://"+inetAddress.getHostAddress()+":"+serverPort+"/swagger-ui.html");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
