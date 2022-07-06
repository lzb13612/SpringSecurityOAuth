CREATE TABLE `oauth_client_details` (
    `client_id` varchar(128) NOT NULL,
    `resource_ids`varchar(256)DEFAULT NULL,
    `client_secret` varchar(256) DEFAULT NULL,
    `scope`varchar(256) DEFAULT NULL,
    `authorized_grant_types` varchar(256) DEFAULT NULL,
    `web_server_redirect_uri` varchar(256) DEFAULT NULL,
    `authorities` varchar(256) DEFAULT NULL,
    `access_token_validity` int(11) DEFAULT NULL,
    `refresh_token_validity` int(11) DEFAULT NULL,
    `additional_information` varchar(4096) DEFAULT NULL,
    `autoapprove` varchar(256) DEFAULT NULL,
    PRIMARY KEY(`client_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sso_user_auths` (
    `user_id`char(32) NOT NULL COMMENT '用户ID',
    `identity_type` char(2) DEFAULT NULL COMMENT '登录类型(01:手机号;02:邮箱;03:用户名;04:微信;05:QQ)',
    `identifier` varchar(50)DEFAULT NULL COMMENT '标识(手机号/邮箱/用户名或第三方应用的标识)',
    `credential` varchar(200) DEFAULT NULL COMMENT '密码凭证(站内的保存密码，站外的不保存或保存token)',
    PRIMARY KEY (`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='注册用户表';

CREATE TABLE `oauth_code` (
    `code` varchar(256) DEFAULT NULL ,
    `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `oauth_approvals` (
    `userId` varchar(256) DEFAULT NULL ,
    `clientId` varchar(256) DEFAULT NULL ,
    `scope` varchar(256) DEFAULT NULL ,
    `status` varchar(10) DEFAULT NULL ,
    `expiresAt` timestamp NULL DEFAULT NULL ,
    `lastModifiedAt` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `oauth_access_token` (
    `token_id` varchar(256) DEFAULT NULL ,
    `token` blob ,
    `authentication_id` varchar(128) NOT NULL ,
    `user_name` varchar(256) DEFAULT NULL ,
    `client_id` varchar(256) DEFAULT NULL ,
    `authentication` blob ,
    `refresh_token` varchar(256) DEFAULT NULL ,
    PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `oauth_refresh_token` (
    `token_id` varchar(256) DEFAULT NULL ,
    `token` blob,
    `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sso_user_info` (
    `user_id`char(32) NOT NULL COMMENT '用户ID',
    `nickname` VARCHAR(20) DEFAULT NULL ,
    `mobile` VARCHAR(11) DEFAULT NULL ,
    `Avatar` VARCHAR(256) DEFAULT NULL ,
    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;