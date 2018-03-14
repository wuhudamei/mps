
alter table biz_order_install_plan add column unqualified_accept_time datetime comment '不合格验收时间';
alter table biz_order_install_plan add column unqualified_reason varchar(50) comment '不合格原因';
alter table biz_order_install_plan add column unqualified_remarks varchar(100) comment '不合格备注';
alter table biz_order_install_plan add column first_pass_rate double(5,2) comment '一次合格率';
alter table biz_order_install_plan add column unqualified_times int comment '不合格次数';


drop table if exists biz_order_install_plan_accept_log;
create table biz_order_install_plan_accept_log
(
   id                   int(11) not null auto_increment comment 'id',
   order_id             int(11) comment '订单id',
   order_install_plan_id int(11) comment '订单安装计划id',
   accept_type          varchar(20) comment '验收类型（合格、不合格）',
   order_install_iteam_id int(11) comment '安装项名称id',
   order_install_iteam  varchar(100) comment '安装项名称',
   unqualified_reason   varchar(50) comment '验收不合格原因',
   accept_remarks       varchar(100) comment '验收备注',
   create_by            varchar(64) comment '创建人',
   create_date          datetime comment '创建时间',
   update_by            varchar(64) comment '更新人',
   update_date          datetime comment '更新时间',
   del_flag             char(1) comment '删除标识',
   remarks              varchar(255) comment '备注',
   operater_id          int(11) comment '操作人id',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主材安装验收日志';