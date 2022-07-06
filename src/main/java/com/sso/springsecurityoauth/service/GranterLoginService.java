package com.sso.springsecurityoauth.service;

import com.sso.springsecurityoauth.model.GranterUser;

/**
 * @author lzb
 * create time: 2022/7/6
 */

public interface GranterLoginService {
    GranterUser loadByEmailPassword(String identifier, String credential);
}
