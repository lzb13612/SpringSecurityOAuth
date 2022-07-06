package com.sso.springsecurityoauth.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lzb
 * create time: 2022/7/6
 * 用户授权信息
 */
@Data
@TableName("sso_user_auths")
public class UserAuth {
    private String userId;
    private String identityType;
    private String identifier;
    private String credential;
}
