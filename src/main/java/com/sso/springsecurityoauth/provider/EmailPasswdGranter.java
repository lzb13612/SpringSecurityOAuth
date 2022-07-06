package com.sso.springsecurityoauth.provider;

import com.sso.springsecurityoauth.model.GranterUser;
import com.sso.springsecurityoauth.service.GranterLoginService;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Map;

/**
 * @author lzb
 * create time: 2022/7/6
 * 邮箱/密码登录实现
 */
public class EmailPasswdGranter extends AbstractSsoTokenGranter{
    private static final String GRANT_TYPE = "email_password";

    private final GranterLoginService granterLoginService;

    public EmailPasswdGranter(GranterLoginService granterLoginService, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory){
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.granterLoginService = granterLoginService;
    }

    @Override
    protected GranterUser getGranterUser(Map<String, String> parameters) {
        String identifier = parameters.get("identifier");
        String credential = parameters.get("credential");
        return granterLoginService.loadByEmailPassword(identifier, credential);
    }
}
