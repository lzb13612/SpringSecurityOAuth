package com.sso.springsecurityoauth.http;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lzb
 * create time: 2022/7/6
 * 网页状态枚举
 */
public enum HttpStatus {
    UNKNOWN(-1, "未知错误"),
    SUCCESS(200, "成功"),
    FAILURE(9999, "失败");
    private int code;
    private String message;

    private HttpStatus(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }

    public Map<String, Object> map(){
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("code", this.code);
        map.put("message", this.message);
        return map;
    }

    @Override
    public String toString(){
        return this.name();
    }
}
