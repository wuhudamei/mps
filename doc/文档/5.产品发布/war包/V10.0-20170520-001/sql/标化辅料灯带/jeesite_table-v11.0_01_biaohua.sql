/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/10 14:00:25                           */
/*==============================================================*/


drop table if exists biz_order_materials_standard;


/*==============================================================*/
/* Table: biz_materials_standard_receive_bill（modify2）          */
/*==============================================================*/
alter table biz_materials_standard_receive_bill
add   is_viewed                      char(1)         comment '是否查看过（添加） -- \'',
add   view_datetime                  datetime        comment '查看日期时间（添加） -- \'',
add   abandon_reason                 varchar(255)    comment '废弃理由 -- \'';

/*==============================================================*/
/* Table: biz_materials_standard_receive_detail（modify）         */
/*==============================================================*/
alter table biz_materials_standard_receive_detail
add   max_receive_number_snap        decimal(10,2)   comment '领取数量上限快照（添加） -- \'',
add   apply_number_total_snap        decimal(10,2)   comment '已申请总数快照（添加） -- \'',
add   receive_number_total_snap      decimal(10,2)   comment '已领取总数快照（添加） -- \'';

/*==============================================================*/
/* Table: biz_materials_standard（modify）                        */
/*==============================================================*/
alter table biz_materials_standard
add   max_receive_number             decimal(10,2)   comment '领取数量上限（添加） -- \'';

/*==============================================================*/
/* Table: biz_order_materials_standard                          */
/*==============================================================*/
create table biz_order_materials_standard
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_id                       int(11)         comment '订单id -- \'',
   materials_standard_id          int(11)         comment '标化辅材id -- \'',
   materials_type                 varchar(100)    comment '物料类别 -- \'',
   materials_name                 varchar(100)    comment '物料名称 -- \'',
   materials_unit                 varchar(10)     comment '物料单位 -- \'',
   materials_price                decimal(10,2)   comment '物料单价 -- \'',
   materials_amount               decimal(10,2)   comment '物料金额 -- \'',
   apply_number_total             decimal(10,2)   comment '已申请总数 -- \'',
   receive_number_total           decimal(10,2)   comment '已领取总数 -- \'',
   max_receive_number             decimal(10,2)   comment '领取数量上限 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order（modify6）                                    */
/*==============================================================*/
alter table biz_order
add   is_basicwork_completed         char(1)         comment '是否基装验收完成（添加） -- \'0.否；1.是';

