/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/10 18:33:41                           */
/*==============================================================*/


drop table if exists biz_order_taskpackage_payment_frozen_log;

drop table if exists biz_order_taskpackage_settlement_cancle_log;

drop table if exists biz_order_taskpackage_settlement_update_log;


/*==============================================================*/
/* Table: biz_order_taskpackage_payment_frozen_log              */
/*==============================================================*/
create table biz_order_taskpackage_payment_frozen_log
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   biz_order_taskpackage_payment_id int(11)         comment '订单任务包结算单id -- \'',
   frozen_type                    varchar(255)    comment '冻结解冻类型 -- \'1.冻结；2.解冻',
   frozen_remarks                 varchar(255)    comment '冻结解冻说明 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_taskpackage_settlement_cancle_log           */
/*==============================================================*/
create table biz_order_taskpackage_settlement_cancle_log
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   operate_type                   varchar(10)     comment '操作类型 -- \'1.撤回',
   biz_order_taskpackage_settlemen_id int(11)         comment '订单任务包结算单id -- \'',
   operate_remarks                varchar(255)    comment '操作说明 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);


/*==============================================================*/
/* Table: biz_order_taskpackage_settlement_update_log           */
/*==============================================================*/
create table biz_order_taskpackage_settlement_update_log
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_taskpackage_settlement_id int(11)         comment '订单任务包结算单id -- \'',
   amount_old                     decimal(8,2)    comment '结算单原金额 -- \'',
   amount_new                     decimal(8,2)    comment '结算单修改后金额 -- \'',
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
/* Table: biz_order_taskpackage_procedure（modify）               */
/*==============================================================*/
alter table biz_order_taskpackage_procedure
add   modify_number_reason           varchar(255)    comment '后台更改工程量原因（添加） -- \'';

/*==============================================================*/
/* Table: biz_order_taskpackage_settlement（modify6）             */
/*==============================================================*/
alter table biz_order_taskpackage_settlement
add   company_deduct_amount          decimal(8,2)    comment '公司扣款金额（添加） -- \'',
add   company_deduct_reason          varchar(255)    comment '公司扣款原因（添加） -- \'';

