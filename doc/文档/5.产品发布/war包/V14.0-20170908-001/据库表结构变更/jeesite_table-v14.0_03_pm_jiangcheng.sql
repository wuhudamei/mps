/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/8/23 11:08:52                           */
/*==============================================================*/


drop table if exists biz_assess_reward_punish;

drop table if exists biz_assess_rule;

drop table if exists biz_assess_rule_type;


/*==============================================================*/
/* Table: biz_assess_reward_punish                              */
/*==============================================================*/
create table biz_assess_reward_punish
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   related_business_type          varchar(10)     comment '关联业务类型 -- \'',
   related_business_id_int        int(11)         comment '关联业务id整型 -- \'',
   related_business_id_varchar    varchar(100)    comment '关联业务id字符型 -- \'',
   assess_rule_id                 int(11)         comment '考核条例id -- \'',
   is_reward_or_punish            varchar(10)     comment '是奖或惩 -- \'1.奖励；2.惩罚',
   reward_punish_target_type      varchar(10)     comment '奖惩对象类型 -- \'10.订单；20.员工',
   reward_punish_target_employee_type varchar(10)     comment '奖惩对象员工类型 -- \'',
   reward_punish_target_employee_id int(11)         comment '奖惩对象员工id -- \'',
   reward_punish_amount           decimal(8,2)    comment '奖惩金额 -- \'',
   reward_punish_score            decimal(8,2)    comment '奖惩分数 -- \'',
   reward_punish_remarks          varchar(255)    comment '奖惩说明 -- \'',
   reward_punish_datetime         datetime        comment '奖惩日期时间 -- \'',
   reward_punish_status           varchar(10)     comment '奖惩状态 -- \'1.新建；2.已关联结算',
   status_datetime                datetime        comment '状态日期时间 -- \'',
   status_operator                int(11)         comment '状态操作人员工id -- \'',
   status_describe                varchar(1000)   comment '状态说明 -- \'',
   settle_stage                   varchar(10)     comment '所属结算阶段 -- \'1.基装完成；2.竣工完成',
   settle_type                    varchar(10)     comment '结算单类型 -- \'1.工人结算；2.项目经理结算；',
   settle_id                      int(11)         comment '结算单id -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_assess_rule                                       */
/*==============================================================*/
create table biz_assess_rule
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   store_id                       int(11)         comment '门店id -- \'',
   project_mode                   varchar(10)     comment '工程模式 -- \'',
   biz_assess_rule_type_id        int(11)         comment '考核条例分类id -- \'',
   biz_assess_rule_describe       varchar(2000)   comment '考核条例说明 -- \'',
   reward_punish_amount           decimal(10,2)   comment '奖惩金额 -- \'',
   reward_punish_score            int             comment '奖惩分数 -- \'',
   reward_punish_target_employee_type varchar(10)     comment '奖惩对象员工类型 -- \'',
   is_enabled                     char(1)         comment '是否启用 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_assess_rule_type                                  */
/*==============================================================*/
create table biz_assess_rule_type
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   store_id                       int(11)         comment '门店id -- \'',
   project_mode                   varchar(10)     comment '工程模式 -- \'',
   type_name                      varchar(100)    comment '分类名称 -- \'',
   is_reward_or_punish            varchar(10)     comment '是奖或惩 -- \'',
   reward_punish_target_type      varchar(10)     comment '奖惩对象类型 -- \'10.订单；20.员工',
   is_enabled                     char(1)         comment '是否启用 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_pm_settle_bill（modify5）                           */
/*==============================================================*/
alter table biz_pm_settle_bill
add   midway_reward_amount           decimal(8,2)    comment '中期奖励金额（添加） -- \'',
add   midway_punish_amount           decimal(8,2)    comment '中期扣款金额（添加） -- \'',
add   complete_reward_amount         decimal(8,2)    comment '竣工奖励金额（添加） -- \'',
add   complete_punish_amount         decimal(8,2)    comment '竣工扣款金额（添加） -- \'';

/*==============================================================*/
/* Table: biz_pm_settle_summary_bill（modify5）                   */
/*==============================================================*/
alter table biz_pm_settle_summary_bill
add   midway_reward_amount           decimal(8,2)    comment '中期奖励金额（添加） -- \'',
add   midway_punish_amount           decimal(8,2)    comment '中期扣款金额（添加） -- \'',
add   complete_reward_amount         decimal(8,2)    comment '竣工奖励金额（添加） -- \'',
add   complete_punish_amount         decimal(8,2)    comment '竣工扣款金额（添加） -- \'';

