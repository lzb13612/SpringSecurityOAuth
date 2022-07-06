package com.sso.springsecurityoauth.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author lzb
 * create time: 2022/7/6
 * 手机登录模型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GranterUser implements AuthenticatedPrincipal, Serializable {
    private String nickname;
    private String mobile;
    private String avatar;

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public String getName() {
        return nickname;
    }
}
