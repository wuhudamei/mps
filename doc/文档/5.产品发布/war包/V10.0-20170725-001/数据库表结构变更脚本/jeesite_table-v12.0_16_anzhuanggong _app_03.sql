/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/7/12 10:13:55                           */
/*==============================================================*/

drop table if exists biz_project_install_item_supplier;

drop table if exists biz_supplier_employee_group;

drop table if exists biz_supplier_install_bill;

drop table if exists biz_supplier_install_construct_bill;

/*==============================================================*/
/* Table: biz_project_install_item_supplier                     */
/*==============================================================*/
create table biz_project_install_item_supplier
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   project_install_item_id        int(11)         comment '工程安装项id -- \'',
   supplier_id                    int(11)         comment '供应商id -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_supplier_employee_group                           */
/*==============================================================*/
create table biz_supplier_employee_group
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   supplier_id                    int(11)         comment '供应商id -- \'',
   employee_group_id              int(11)         comment '工人组id -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_supplier_install_bill                             */
/*==============================================================*/
create table biz_supplier_install_bill
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   install_bill_code              varchar(100)    comment '安装单号 -- \'',
   order_install_plan_id          int(11)         comment '订单安装计划id -- \'',
   supplier_id                    int(11)         comment '供应商id -- \'',
   supplier_confirm_into_date     datetime        comment '供应商确认进场日期时间 -- \'',
   supplier_confirm_complete_date datetime        comment '供应商确认完工日期时间 -- \'',
   status                         varchar(10)     comment '状态 -- \'',
   status_datetime                datetime        comment '状态日期时间 -- \'',
   real_into_date                 datetime        comment '实际进场日期时间 -- \'',
   real_complete_date             datetime        comment '实际完工日期时间 -- \'',
   real_accept_date               datetime        comment '确认验收日期时间 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_supplier_install_construct_bill                   */
/*==============================================================*/
create table biz_supplier_install_construct_bill
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   construct_bill_code            varchar(100)    comment '施工单号 -- \'',
   supplier_install_bill_id       int(11)         comment '供应商安装单id -- \'',
   employee_group_id              int(11)         comment '工人组id -- \'',
   status                         varchar(10)     comment '状态 -- \'',
   status_datetime                datetime        comment '状态日期时间 -- \'',
   real_into_date                 datetime        comment '实际进场日期时间 -- \'',
   real_complete_date             datetime        comment '实际完工日期时间 -- \'',
   real_accept_date               datetime        comment '确认验收日期时间 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_supplier（modify）                                  */
/*==============================================================*/
alter table biz_supplier
add   install_item_type              varchar(10)     comment '安装项分类（添加） -- \'';
