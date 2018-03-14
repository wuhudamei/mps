/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/7/3 10:36:49                            */
/*==============================================================*/


drop table if exists biz_complaint_problem_item;

drop table if exists biz_complaint_problem_type;

drop table if exists biz_order_complaint;

drop table if exists biz_order_complaint_problem;

drop table if exists biz_order_complaint_problem_deal;

drop table if exists biz_order_complaint_problem_deal_log;

drop table if exists biz_order_complaint_problem_item;

/*==============================================================*/
/* Table: biz_complaint_problem_item                            */
/*==============================================================*/
create table biz_complaint_problem_item
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   store_id                       int(11)         comment '门店id -- \'',
   complaint_problem_type_id      int(11)         comment '投诉问题分类id -- \'',
   item_name                      varchar(100)    comment '事项名称 -- \'',
   response_time                  decimal(10,2)   comment '响应时间 -- \'',
   execute_time_limit             decimal(10,2)   comment '执行时限 -- \'',
   special_time_limit             decimal(10,2)   comment '特殊时间 -- \'',
   deduct_score                   decimal(10,2)   comment '扣分 -- \'',
   punish_money                   decimal(10,2)   comment '罚款 -- \'',
   item_remarks                   varchar(255)    comment '事项备注 -- \'',
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
/* Table: biz_complaint_problem_type                            */
/*==============================================================*/
create table biz_complaint_problem_type
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   store_id                       int(11)         comment '门店id -- \'',
   type_name                      varchar(100)    comment '分类名称 -- \'',
   task_package_templat_id        int(11)         comment '任务包模板id -- \'',
   deal_person_type               varchar(10)     comment '处理人员类型 -- \'1.项目经理；2.项目经理+工人；3.质检员',
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
/* Table: biz_order_complaint                                   */
/*==============================================================*/
create table biz_order_complaint
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_id                       int(11)         comment '订单id -- \'',
   complaint_source               varchar(10)     comment '投诉来源 -- \'1.客户；2.设计部；3.材料部；4.客管部；',
   data_input_channel             varchar(10)     comment '数据录入渠道 -- \'1.部门上报；2.售后系统推送',
   cus_service_problem_id         int(11)         comment '售后问题id -- \'',
   complaint_person_name          varchar(20)     comment '投诉人姓名 -- \'',
   complaint_person_phone         varchar(11)     comment '投诉人手机号 -- \'',
   status                         varchar(10)     comment '状态 -- \'10.新建；20.处理中；30.已处理',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_complaint_problem                           */
/*==============================================================*/
create table biz_order_complaint_problem
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_complaint_id             int(11)         comment '订单投诉id -- \'',
   complaint_problem_type_id      int(11)         comment '问题分类id -- \'',
   task_package_templat_id        int(11)         comment '任务包模板id -- \'',
   order_taskpackage_id           int(11)         comment '订单任务包id -- \'',
   complaint_role_type            varchar(10)     comment '被投诉角色类型 -- \'1.项目经理；2.项目经理+工人；3.质检员',
   complaint_problem_describe     varchar(255)    comment '投诉问题描述 -- \'',
   status                         varchar(10)     comment '状态 -- \'10.新建；20.处理中；30.已处理',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_complaint_problem_deal                      */
/*==============================================================*/
create table biz_order_complaint_problem_deal
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_complaint_problem_id     int(11)         comment '订单投诉问题id -- \'',
   deal_person_type               varchar(10)     comment '处理人员类型 -- \'1.项目经理；2.工人组长；3.工人；4.质检员',
   deal_employee_id               int(11)         comment '处理人员员工id -- \'',
   deal_status                    varchar(10)     comment '处理状态 -- \'10.确认接收；20.已答复',
   deal_status_datetime           datetime        comment '处理状态日期时间 -- \'',
   deal_describe                  varchar(255)    comment '处理描述 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_complaint_problem_deal_log                  */
/*==============================================================*/
create table biz_order_complaint_problem_deal_log
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_complaint_problem_deal_id int(11)         comment '订单投诉问题id -- \'',
   deal_person_type               varchar(10)     comment '处理人员类型 -- \'1.项目经理；2.工人组长；3.工人；4.质检员',
   deal_employee_id               int(11)         comment '处理人员员工id -- \'',
   deal_status                    int(11)         comment '处理状态 -- \'10.确认接收；20.已答复',
   deal_status_datetime           datetime        comment '处理状态日期时间 -- \'',
   deal_describe                  varchar(255)    comment '处理描述 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_complaint_problem_item                      */
/*==============================================================*/
create table biz_order_complaint_problem_item
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_complaint_problem_id     int(11)         comment '订单投诉问题id -- \'',
   complaint_problem_item_id      int(11)         comment '投诉问题事项id -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);


/*==============================================================*/
/* Table: biz_cus_service_problem（modify）                       */
/*==============================================================*/
alter table biz_cus_service_problem
add   status                         varchar(10)     comment '状态（添加） -- \'10.已接收；20.已处理；30.已驳回',
add   status_datetime                datetime        comment '状态日期时间（添加） -- \'',
add   status_describe                varchar(255)    comment '状态描述（添加） -- \'',
add   status_operator_employee_id    int(11)         comment '状态操作人员工id（添加） -- \'';


