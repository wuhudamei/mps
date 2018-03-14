/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/5 14:02:31                            */
/*==============================================================*/


drop table if exists biz_order_report_instore_log;

drop table if exists biz_order_report_send_log;

drop table if exists biz_order_report_send_rule;

drop table if exists biz_order_report_send_rule_service;

drop table if exists biz_order_report_sign_log;

drop table if exists biz_order_report_sign_log_related_order;

/*==============================================================*/
/* Table: biz_order_report_instore_log                          */
/*==============================================================*/
create table biz_order_report_instore_log
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   operate_source                 varchar(10)     comment '操作来源 -- \'1.接口；2.人工',
   order_report_id                int(11)         comment '返单id -- \'',
   instore_type                   varchar(10)     comment '进店类型 -- \'1.进店未签单；2.进店已签单',
   instore_datetime               datetime        comment '进店日期时间 -- \'',
   instore_remarks                varchar(255)    comment '进店备注 -- \'',
   sign_datetime                  datetime        comment '签单日期时间 -- \'',
   sign_remarks                   varchar(255)    comment '签单备注 -- \'',
   operator_name                  varchar(20)     comment '操作人姓名 -- \'',
   operator_phone                 varchar(11)     comment '操作人手机号 -- \'',
   operate_datetime               datetime        comment '操作日期时间 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_report_send_log                             */
/*==============================================================*/
create table biz_order_report_send_log
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   send_type                      varchar(10)     comment '派单类型 -- \'1.系统自动分派客服；2.人工分派客服；3.人工转派客服',
   order_report_id                int(11)         comment '返单id -- \'',
   service_employee_id            int(11)         comment '客服员工id -- \'',
   service_name                   varchar(20)     comment '客服姓名 -- \'',
   service_phone                  varchar(11)     comment '客服手机号 -- \'',
   operator_employee_id           int(11)         comment '操作人员工id -- \'',
   operate_datetime               datetime        comment '操作日期时间 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_report_send_rule                            */
/*==============================================================*/
create table biz_order_report_send_rule
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   start_datetime                 datetime        comment '起始日期时间 -- \'',
   end_datetime                   datetime        comment '截止日期时间 -- \'',
   status                         varchar(10)     comment '状态 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_report_send_rule_service                    */
/*==============================================================*/
create table biz_order_report_send_rule_service
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   send_rule_id                   int(11)         comment '返单派单规则id -- \'',
   service_index                  int             comment '客服序号 -- \'',
   service_employee_id            int(11)         comment '客服员工id -- \'',
   send_count                     int             comment '分派次数 -- \'',
   is_send_end                    char(1)         comment '是否循环分派末尾 -- \'0.否；1.是',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_report_sign_log                             */
/*==============================================================*/
create table biz_order_report_sign_log
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   sign_type                      varchar(10)     comment '签订类型 -- \'1.签订合同；2.补签合同；3.签订订单',
   operate_source                 varchar(10)     comment '操作来源 -- \'1.接口；2.人工',
   operator_employee_id           int(11)         comment '操作人员工id -- \'',
   operator_name                  varchar(20)     comment '操作人姓名 -- \'',
   operator_phone                 varchar(11)     comment '操作人手机号 -- \'',
   operate_datetime               datetime        comment '操作日期时间 -- \'',
   sign_datetime                  datetime        comment '签订日期时间 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_report_sign_log_related_order               */
/*==============================================================*/
create table biz_order_report_sign_log_related_order
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   sign_log_id                    int(11)         comment '返单签订记录id -- \'',
   order_id                       int(11)         comment '关联订单id -- \'',
   order_number                   varchar(100)    comment '关联订单编码 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_report（modify）                              */
/*==============================================================*/
alter table biz_order_report 
add   is_asked                       char(1)         comment '是否咨询过（添加） -- \'1.是；0.否',
add   service_employee_id            int(11)         comment '客服员工id（添加） -- \'',
add   service_name                   varchar(20)     comment '客服姓名（添加） -- \'',
add   service_phone                  varchar(11)      comment '客服手机号（添加） -- \'';


/*==============================================================*/
/* Table: biz_employee（modify3）                                 */
/*==============================================================*/
alter table biz_employee
add   employee_id_mapping_order_change_sys varchar(60)     comment '员工id_订单流转系统映射 -- \'';


