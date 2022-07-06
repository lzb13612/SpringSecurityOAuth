package com.sso.springsecurityoauth.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sso.springsecurityoauth.mapper.UserAuthMapper;
import com.sso.springsecurityoauth.model.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzb
 * create time: 2022/7/6
 * 用户服务
 */

@Service
public class UserAuthService implements UserDetailsService {
    @Autowired
    private UserAuthMapper userAuthMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<UserAuth> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserAuth::getIdentifier, username);
        UserAuth userAuth = userAuthMapper.selectOne(queryWrapper);
        if(userAuth == null){
            throw new UsernameNotFoundException("帐号不存在");
        }
        List<GrantedAuthority> list= AuthorityUtils.commaSeparatedStringToAuthorityList("ROOT_USER");
        return new User(userAuth.getIdentifier(), userAuth.getCredential(), list);
    }
}
