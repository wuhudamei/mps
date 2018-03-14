  drop table if exists biz_home_login_logout_log;

/*==============================================================*/
/* Table: biz_home_login_logout_log                             */
/*==============================================================*/
create table biz_home_login_logout_log
(
   id                   int not null auto_increment,
   deal_mode            varchar(20) comment '登录方式（WeChat、app）',
   deal_type            varchar(10) not null comment '登录类型(in、out)',
   deal_phone           varchar(20) not null comment '登录手机号',
   deal_time            datetime not null comment '操作时间',
   remarks              varchar(200) not null comment '登录备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;