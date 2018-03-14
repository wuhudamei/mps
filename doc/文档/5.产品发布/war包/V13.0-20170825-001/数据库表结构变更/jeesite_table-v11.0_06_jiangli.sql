/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/18 16:36:11                           */
/*==============================================================*/


drop table if exists biz_business_reward_punish;

/*==============================================================*/
/* Table: biz_business_reward_punish                            */
/*==============================================================*/
create table biz_business_reward_punish
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   reward_punish_type             varchar(10)     comment '奖惩类型 -- \'1.奖励；2.惩罚',
   employee_type                  varchar(10)     comment '员工类型 -- \'',
   employee_Id                    int(11)         comment '员工id -- \'',
   related_business_type          varchar(10)     comment '关联业务类型 -- \'',
   related_business_id_int        int(11)         comment '关联业务id整型 -- \'',
   related_business_id_varchar    varchar(100)    comment '关联业务id字符型 -- \'',
   reward_punish_amount           decimal(8,2)    comment '奖惩金额 -- \'',
   reward_punish_remarks          varchar(255)    comment '奖惩说明 -- \'',
   reward_punish_datetime         datetime        comment '奖惩日期 -- \'',
   reward_punish_status           varchar(10)     comment '奖惩状态 -- \'1.新建；2.已关联结算',
   status_datetime                datetime        comment '状态日期时间 -- \'',
   settle_stage                   varchar(10)     comment '所属结算阶段 -- \'1.基装完成；2.竣工完成',
   settle_type                    varchar(10)     comment '结算单类型 -- \'1.工人结算；2.项目经理结算',
   settle_id                      int(11)         comment '结算单id -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

