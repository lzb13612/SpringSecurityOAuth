package com.sso.springsecurityoauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @author lzb
 * create time: 2022/7/6
 * jwt转换器
 */
@Configuration
public class JwtTokenConfiguration {
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        return new JwtAccessTokenConverter();
    }
}
