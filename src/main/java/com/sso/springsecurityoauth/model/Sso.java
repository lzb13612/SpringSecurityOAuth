package com.sso.springsecurityoauth.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sso.springsecurityoauth.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lzb
 * create time: 2022/7/6
 */
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Sso {
    private int code;
    private String message;
    private Object data;

    public static Sso build(){
        return build(null);
    }

    public static Sso build(Object data) {
        return new Sso(200, "成功", data);
    }

    public static Sso buildFail(HttpStatus status){
        return buildFail(status.getCode(), status.getMessage());
    }

    public static Sso buildFail(String msg) {
        return buildFail(400, msg);
    }

    public static Sso buildFail(Integer code, String msg){
        return new Sso(code, msg, null);
    }
}
