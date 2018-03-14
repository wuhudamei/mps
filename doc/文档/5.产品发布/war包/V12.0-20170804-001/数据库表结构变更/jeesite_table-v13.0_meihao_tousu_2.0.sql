/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/7/25 13:46:38                           */
/*==============================================================*/


drop table if exists biz_order_complaint_pre;

/*==============================================================*/
/* Table: biz_order_complaint_pre                               */
/*==============================================================*/
create table biz_order_complaint_pre
(
   id                             int(11)         not null auto_increment comment '主键 -- \'主键',
   order_id                       int(11)         default NULL comment '订单id -- \'',
   complaint_source               varchar(20)     default NULL comment '投诉来源 -- \'',
   complaint_employee_id          int(11)         default NULL comment '投诉人id -- \'',
   complaint_describe             varchar(1000)   comment '投诉描述 -- \'',
   complaint_status               varchar(10)     default NULL comment '投诉状态 -- \'',
   status_deal_employee_id        int(11)         comment '状态处理人id -- \'',
   status_datetime                datetime        default NULL comment '状态生成日期时间 -- \'',
   status_describe                varchar(255)    default NULL comment '状态描述 -- \'',
   remarks                        varchar(255)    default NULL comment '备注 -- \'',
   create_date                    datetime        default NULL comment '创建时间 -- \'',
   create_by                      varchar(64)     default NULL comment '创建人 -- \'',
   update_date                    datetime        default NULL comment '更新时间 -- \'',
   update_by                      varchar(64)     default NULL comment '更新人 -- \'',
   del_flag                       char(1)         default NULL comment '删除标记 -- \'',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

