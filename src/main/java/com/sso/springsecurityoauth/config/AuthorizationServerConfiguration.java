package com.sso.springsecurityoauth.config;

import com.sso.springsecurityoauth.provider.EmailPasswdGranter;
import com.sso.springsecurityoauth.provider.SsoTokenEnhancer;
import com.sso.springsecurityoauth.service.GranterLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lzb
 * create time: 2022/7/6
 * ?????????????????????
 */

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private ClientDetailsService jdbcClientDetailsService;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    private ApprovalStore approvalStore;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private SsoTokenEnhancer ssoTokenEnhancer;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // Password??????????????????
        endpoints.authenticationManager(authenticationManager);
        // ???Token?????????JWT
//        endpoints.accessTokenConverter(jwtAccessTokenConverter);
        // ???????????????????????????
        endpoints.authorizationCodeServices(authorizationCodeServices);
        // ??????????????????????????????
        endpoints.approvalStore(approvalStore);
        // token??????????????????
        endpoints.tokenStore(tokenStore);
        /*
        * ??????????????????
        * ?????????????????????ssoTokenEnhancer???jwtAccessTokenConverter??????
        * ????????????jwt??????????????????
        * */
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<TokenEnhancer>();
        delegates.add(ssoTokenEnhancer);
        delegates.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(delegates);
        endpoints.tokenEnhancer(enhancerChain);

        // ?????????????????????
        List<TokenGranter> tokenGranters = getTokenGranters(endpoints.getAuthorizationCodeServices(),endpoints.getTokenStore(), endpoints.getTokenServices(), endpoints.getClientDetailsService(),endpoints.getOAuth2RequestFactory());
        endpoints.tokenGranter(new CompositeTokenGranter(tokenGranters));
    }

    @Autowired
    private GranterLoginService granterLoginService;

    private List<TokenGranter> getTokenGranters(AuthorizationCodeServices authorizationCodeServices, TokenStore tokenStore, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory){
        return new ArrayList<>(Arrays.asList(
                // ????????????????????????
                new AuthorizationCodeTokenGranter(tokenServices, authorizationCodeServices, clientDetailsService, requestFactory),
                // ??????password??????
                new ResourceOwnerPasswordTokenGranter(authenticationManager, tokenServices, clientDetailsService, requestFactory),
                // ??????client_credentials????????????
                new ClientCredentialsTokenGranter(tokenServices, clientDetailsService, requestFactory),
                // ???????????????/????????????
                new EmailPasswdGranter(granterLoginService, tokenServices, clientDetailsService, requestFactory)
        ));
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients
//                .inMemory()
//                .withClient("client")
//                .secret(passwordEncoder.encode("123456"))
//                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "refresh_token")
//                .scopes("app")
//                .redirectUris("https://www.baidu.com");
        clients.withClientDetails(jdbcClientDetailsService);
    }
}
