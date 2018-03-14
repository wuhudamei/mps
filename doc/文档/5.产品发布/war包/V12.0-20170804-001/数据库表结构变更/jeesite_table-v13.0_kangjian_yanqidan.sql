/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/8/1 9:58:43                             */
/*==============================================================*/


drop table if exists biz_order_delay_bill;

drop table if exists biz_order_delay_category;

/*==============================================================*/
/* Table: biz_order_delay_bill                                  */
/*==============================================================*/
create table biz_order_delay_bill
(
   id                             int(11)         not null auto_increment comment '主键ID -- \'',
   order_id                       int(11)         comment '订单ID -- \'',
   delay_bill_stage_status        varchar(100)    comment '延期阶段 -- \'',
   delay_bill_category_id         int(11)         comment '延期类别 -- \'',
   delay_begin_datetime           datetime        comment '延期开始日期时间 -- \'',
   delay_end_datetime             datetime        comment '延期结束日期时间 -- \'',
   delay_days                     int             comment '延期天数 -- \'',
   deferred_instruction           varchar(1000)   comment '延期说明 -- \'',
   deferred_application_datetime  datetime        comment '延期申请日期时间 -- \'',
   status                         varchar(10)      comment '状态 -- \'',
   status_datetime                datetime        comment '状态日期时间 -- \'',
   create_date                    datetime        comment '创建时间 -- \'',
   create_by                      varchar(64)     comment '创建人 -- \'',
   update_date                    datetime        comment '更新时间 -- \'',
   update_by                      varchar(64)     comment '更新人 -- \'',
   del_flag                       char            comment '删除标识 -- \'',
   remarks                        varchar(1000)   comment '备注 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_delay_category                              */
/*==============================================================*/
create table biz_order_delay_category
(
   id                             int(11)         not null auto_increment comment '主键ID -- \'',
   store_id                       int(11)         comment '门店 -- \'',
   parent_id                      int(11)         comment '父ID -- \'',
   delay_type_level               int             comment '延期类型等级 -- \'',
   delay_type_name                varchar(100)    comment '延期类型名称 -- \'',
   create_date                    datetime        comment '创建时间 -- \'',
   create_by                      varchar(64)     comment '创建人 -- \'',
   update_date                    datetime        comment '更新时间 -- \'',
   update_by                      varchar(64)     comment '更新人 -- \'',
   del_flag                       char            comment '删除标识 -- \'',
   remarks                        varchar(1000)   comment '备注 -- \'',
   primary key (id)
);

alter table biz_order_delay_category comment '根据类别加载原因';

