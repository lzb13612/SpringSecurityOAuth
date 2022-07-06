package com.sso.springsecurityoauth.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sso.springsecurityoauth.mapper.UserAuthMapper;
import com.sso.springsecurityoauth.model.GranterUser;
import com.sso.springsecurityoauth.model.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Permission;
import java.util.List;

/**
 * @author lzb
 * create time: 2022/7/6
 */
@Service
public class GranterLoginServiceImpl implements GranterLoginService {
    private final String EMAIL_CODE_TYPE = "02";

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @Override
    public GranterUser loadByEmailPassword(String identifier, String credential) {
        QueryWrapper<UserAuth> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserAuth::getIdentityType, EMAIL_CODE_TYPE)
                .eq(UserAuth::getIdentifier, identifier);
        UserAuth userAuth = userAuthMapper.selectOne(queryWrapper);
        if(userAuth == null){
            throw new UsernameNotFoundException("帐号不存在");
        }
        boolean flag = passwordEncoder.matches(credential, userAuth.getCredential());
        if(flag){
            /*
            * TODO:对接数据库
            * QueryWrapper<UserInfo> userInfoQueryWrapper = new QueryWrapper<>();
            * userInfoQueryWrapper.lambda().eq(UserInfo::getUserId, userAuth.getUserId());
            * UserInfo userInfo = userInfoService.getOne(userInfoQueryWrapper);
            * List<Permission> listPermission = userAuthService.listAuthority(userAuth.getUserId());
            * List<GrantedAuthority> listAuthority = createAuthorityList(listPermission);
            * */

//            QueryWrapper<UserInfo> userInfoQueryWrapper = new QueryWrapper<>();
//            userInfoQueryWrapper.lambda().eq(UserInfo::getUserId, userAuth.getUserId());
//            UserInfo userInfo = userInfoService.getOne(userInfoQueryWrapper);
//            List<Permission> listPermission = userAuthService.listAuthority(userAuth.getUserId());
//            List<GrantedAuthority> listAuthority = createAuthorityList(listPermission);
            GranterUser granterUser = new GranterUser();
            granterUser.setNickname("lzb");
            granterUser.setMobile("13612868340");
            granterUser.setAvatar("https://lumiere-a.akamaihd.net/v1/images/p_avatar_thewayofwater_97_v3_4cb5b11f.png");
            return granterUser;
        }else {
            return new GranterUser();
        }
    }
}
