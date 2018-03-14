/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/9/4 18:20:26                            */
/*==============================================================*/


drop table if exists biz_employee_star_cnfg;

drop table if exists biz_employee_star_log;


/*==============================================================*/
/* Table: biz_employee_star_cnfg                                */
/*==============================================================*/
create table biz_employee_star_cnfg
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   store_id                       int(11)         comment '门店id -- \'',
   peoject_mode                   varchar(10)     comment '工程模式 -- \'',
   employee_type                  varchar(10)     comment '员工类型 -- \'',
   star                           int             comment '星级 -- \'',
   max_score                      int             comment '最高分数 -- \'',
   min_socre                      int             comment '最低分数 -- \'',
   is_enabled                     char(1)         comment '是否启用 -- \'0.否；1.是',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_employee_star_log                                 */
/*==============================================================*/
create table biz_employee_star_log
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   employee_id                    int(11)         comment '员工id -- \'',
   star_change_source             varchar(10)     comment '星级变更来源 -- \'10.任务包评价得分；20.管理人员修改',
   related_business_id            int(11)         comment '关联业务id -- \'',
   star_score_before              decimal(8,2)    comment '变更前星级得分 -- \'',
   star_score_after               decimal(8,2)    comment '变更后星级得分 -- \'',
   star_before                    int             comment '变更前星级 -- \'',
   star_after                     int             comment '变更后星级 -- \'',
   star_change_reason_type        varchar(10)     comment '变更原因分类 -- \'',
   star_change_describe           varchar(1000)   comment '变更说明 -- \'',
   star_change_operator_employee_id int(11)         comment '变更操作人 -- \'',
   star_change_datetime           datetime        comment '变更日期时间 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_employee（modify5）                                 */
/*==============================================================*/
alter table biz_employee
add   star_score                     decimal(8,2)    comment '星级得分（添加） -- \'';

