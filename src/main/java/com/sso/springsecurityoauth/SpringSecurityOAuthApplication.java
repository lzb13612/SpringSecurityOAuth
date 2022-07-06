package com.sso.springsecurityoauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sso.springsecurityoauth.mapper")
public class SpringSecurityOAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityOAuthApplication.class, args);
    }

}
