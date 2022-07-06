package com.sso.springsecurityoauth.provider;

import com.sso.springsecurityoauth.model.GranterUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lzb
 * create time: 2022/7/6
 * 扩展信息
 */
public class SsoTokenEnhancer  implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//        User user = (User) authentication.getPrincipal();
//        Map<String, Object> map = new LinkedHashMap<String, Object>();
//        map.put("client_id", authentication.getOAuth2Request().getClientId());
//        map.put("username", user.getUsername());
//        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);
//        return accessToken;
        Object user = authentication.getPrincipal();
        if(user instanceof GranterUser){
            GranterUser granterUser = (GranterUser) user;
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("NickName", granterUser.getNickname());
            map.put("mobile", granterUser.getMobile());
            map.put("avatar", granterUser.getAvatar());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);
            return accessToken;
        }
        User defaultUser = (User) user;
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("client_id", authentication.getOAuth2Request().getClientId());
        map.put("username", defaultUser.getUsername());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);
        return accessToken;
    }
}
