package com.sso.springsecurityoauth.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lzb
 * create time: 2022/7/6
 * 用户信息
 */
@Data
@TableName("sso_user_info")
public class UserInfo {
    private String userId;
    private String nickname;
    private String mobile;
    private String avatar;
}
