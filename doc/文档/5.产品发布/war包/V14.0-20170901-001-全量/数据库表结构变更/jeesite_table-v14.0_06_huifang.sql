/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/8/25 18:59:10                           */
/*==============================================================*/


drop table if exists biz_customer_return_visit_order_enable;

/*==============================================================*/
/* Table: biz_customer_return_visit_order_enable                */
/*==============================================================*/
create table biz_customer_return_visit_order_enable
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_id                       int(11)         comment '订单id -- \'',
   customer_return_visit_id       int(11)         comment '回访节点id -- \'',
   is_enabled                     char(1)         comment '是否启用 -- \'0.否；1.是',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

alter table biz_customer_return_visit_order_enable comment '客服回访订单停启用表';

