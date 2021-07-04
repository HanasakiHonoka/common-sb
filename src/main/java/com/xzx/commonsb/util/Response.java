package com.xzx.commonsb.util;

import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

/**
 * @Classname Response
 * @Description
 * @Date 2021/7/4 22:02
 * @Author XZX
 * @Version 1.0
 */
@Data
public class Response implements Serializable {

    private Boolean success;

    private String msg;

    private Integer code;

    private Object data;

    public Response() {
    }

    public Response(Boolean success, String msg, Integer code, Object data) {
        this.success = success;
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public static Response success(Object data) {
        Response response = new Response();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    public static Response error(String msg) {
        Response response = new Response();
        response.setSuccess(false);
        response.setMsg(msg);
        return response;
    }

    //public static class Builder {
    //
    //}

}
