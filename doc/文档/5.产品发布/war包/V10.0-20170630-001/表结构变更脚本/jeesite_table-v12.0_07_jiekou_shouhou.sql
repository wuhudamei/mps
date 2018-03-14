/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/6/22 19:24:22                           */
/*==============================================================*/


drop table if exists biz_cus_service_important_degree;

drop table if exists biz_cus_service_liable_type;

drop table if exists biz_cus_service_problem;

/*==============================================================*/
/* Table: biz_cus_service_important_degree                      */
/*==============================================================*/
create table biz_cus_service_important_degree
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   important_degree_code          varchar(20)     comment '重要程度编码 -- \'',
   father_important_degree_code   varchar(20)     comment '父重要程度编码 -- \'',
   important_degree_level         int             comment '重要程度级别 -- \'',
   important_degree_name          varchar(100)    comment '重要程度名称 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_cus_service_liable_type                           */
/*==============================================================*/
create table biz_cus_service_liable_type
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   liable_type_code               varchar(20)     comment '责任类别编码 -- \'',
   father_liable_type_code        varchar(20)     comment '父责任类别编码 -- \'',
   liable_type_level              int             comment '责任类别级别 -- \'',
   liable_type_name               varchar(100)    comment '责任类别名称 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_cus_service_problem                               */
/*==============================================================*/
create table biz_cus_service_problem
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   store_id                       int(11)         comment '门店id -- \'',
   store_code                     varchar(50)     comment '门店编码 -- \'',
   order_id                       int(11)         comment '订单id -- \'',
   order_code                     varchar(100)    comment '订单编码 -- \'',
   work_order_code                varchar(100)    comment '工单编号 -- \'',
   customer_address               varchar(255)    comment '客户地址 -- \'',
   customer_name                  varchar(20)     comment '客户姓名 -- \'',
   customer_mobile                varchar(11)     comment '客户手机号 -- \'',
   contractor_name                varchar(20)     comment '项目经理姓名 -- \'',
   contractor_mobile              varchar(11)     comment '项目经理手机号 -- \'',
   supervisor_name                varchar(20)     comment '质检员姓名 -- \'',
   supervisor_mobile              varchar(11)     comment '质检员手机号 -- \'',
   problem_create_datetime        datetime        comment '问题创建时间 -- \'',
   problem_describe               varchar(1000)   comment '问题描述 -- \'',
   liable_department_code         varchar(20)     comment '责任部门编码 -- \'',
   liable_type_code_1             varchar(20)     comment '责任编码1 -- \'',
   liable_type_code_2             varchar(20)     comment '责任编码2 -- \'',
   important_degree_code_1        varchar(20)     comment '重要程度编码1 -- \'',
   important_degree_code_2        varchar(20)     comment '重要程度编码2 -- \'',
   photo_url                      varchar(1000)   comment '照片url -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

