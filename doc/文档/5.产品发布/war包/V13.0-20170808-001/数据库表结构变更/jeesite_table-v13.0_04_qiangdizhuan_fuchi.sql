/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/8/1 19:42:41                            */
/*==============================================================*/


drop table if exists biz_wall_floor_tile_order_count;

drop table if exists biz_wall_floor_tile_recheck;

drop table if exists biz_wall_floor_tile_recheck_cnfg;

drop table if exists biz_wall_floor_tile_return;

/*==============================================================*/
/* Table: biz_order_wall_floor_tile（modify）                     */
/*==============================================================*/
alter table biz_order_wall_floor_tile
add   is_count_square                char(1)         comment '是否计算面积（添加） -- \'0.否；1.是',
add   unit_square                    decimal(8,2)    comment '单位面积（添加） -- \'';

/*==============================================================*/
/* Table: biz_purchase_wall_floor_tile（modify2）                 */
/*==============================================================*/
alter table biz_purchase_wall_floor_tile
add   is_count_square                char(1)         comment '是否计算面积（添加） -- \'0.否；1.是',
add   unit_square                    decimal(8,2)    comment '单位面积（添加） -- \'',
add   apply_square                   decimal(8,2)    comment '申请面积（添加） -- \'';

/*==============================================================*/
/* Table: biz_wall_floor_tile_order_count                       */
/*==============================================================*/
create table biz_wall_floor_tile_order_count
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_id                       int(11)         comment '订单id -- \'',
   square_budget                  decimal(8,2)    comment '预算面积 -- \'',
   square_purchase_total          decimal(8,2)    comment '采购合计面积 -- \'',
   square_return                  decimal(8,2)    comment '退货面积 -- \'',
   square_purchase_real           decimal(8,2)    comment '采购实际面积 -- \'',
   square_settle                  decimal(8,2)    comment '结算面积 -- \'',
   square_measure                 decimal(8,2)    comment '实测面积 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_wall_floor_tile_recheck                           */
/*==============================================================*/
create table biz_wall_floor_tile_recheck
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_id                       int(11)         comment '订单id -- \'',
   square_budget                  decimal(8,2)    comment '预算面积 -- \'',
   square_quota                   decimal(8,2)    comment '定额面积 -- \'',
   square_purchase                decimal(8,2)    comment '实际下单面积 -- \'',
   square_measure                 decimal(8,2)    comment '实测面积 -- \'',
   plan_measure_date              date            comment '计划测量日期 -- \'',
   real_measure_date              date            comment '实际测量日期 -- \'',
   measure_remarks                varchar(1000)   comment '实测说明 -- \'',
   price                          decimal(8,2)    comment '墙地砖单价 -- \'',
   assess_square_error_1          decimal(8,2)    comment '考核面积误差1 -- \'',
   assess_square_error_2          decimal(8,2)    comment '考核面积误差2 -- \'',
   assess_amount_1                decimal(8,2)    comment '考核金额1 -- \'',
   assess_amount_2                decimal(8,2)    comment '考核金额2 -- \'',
   assess_person_name_1           varchar(20)     comment '被考核人姓名1 -- \'',
   assess_person_name_2           varchar(20)     comment '被考核人姓名2 -- \'',
   status                         varchar(20)     comment '状态 -- \'',
   status_describe                varchar(1000)   comment '状态描述 -- \'',
   status_datetime                datetime        comment '状态日期时间 -- \'',
   status_operate_employee_id     int(11)         comment '状态操作人员工id -- \'',
   recheck_remarks                varchar(1000)   comment '复尺备注 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_wall_floor_tile_recheck_cnfg                      */
/*==============================================================*/
create table biz_wall_floor_tile_recheck_cnfg
(
   price                          decimal(8,2)    comment '材料单价 -- \'',
   square_over_max_rate           decimal(6,4)    comment '面积超出上限比例 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \''
);

/*==============================================================*/
/* Table: biz_wall_floor_tile_return                            */
/*==============================================================*/
create table biz_wall_floor_tile_return
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_id                       int(11)         comment '订单id -- \'',
   square_return                  decimal(8,2)    comment '退货面积 -- \'',
   return_datetime                datetime        comment '退货日期时间 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

