package com.sso.springsecurityoauth.provider;

import com.sso.springsecurityoauth.model.GranterUser;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Map;

/**
 * @author lzb
 * create time: 2022/7/6
 */
public abstract class AbstractSsoTokenGranter extends AbstractTokenGranter {
    private final OAuth2RequestFactory requestFactory;

    protected AbstractSsoTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
        this.requestFactory = requestFactory;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = tokenRequest.getRequestParameters();
        GranterUser granterUser = getGranterUser(parameters);
        OAuth2Request storedOAuth2Request = this.requestFactory.createOAuth2Request(client, tokenRequest);
        PreAuthenticatedAuthenticationToken authenticationToken = new PreAuthenticatedAuthenticationToken(granterUser, null, granterUser.getAuthorities());
        authenticationToken.setDetails(granterUser);
        return new OAuth2Authentication(storedOAuth2Request, authenticationToken);
    }

    protected abstract GranterUser getGranterUser(Map<String, String> parameters);
}
