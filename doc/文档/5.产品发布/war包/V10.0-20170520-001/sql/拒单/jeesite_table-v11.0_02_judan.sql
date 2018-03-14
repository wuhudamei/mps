/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/10 18:36:00                           */
/*==============================================================*/


/*==============================================================*/
/* Table: biz_employee（modify4）                                 */
/*==============================================================*/
alter table biz_employee
add   exchange_order_times           int             comment '被换单次数 -- \'';

/*==============================================================*/
/* Table: biz_order_distribute_log（modify）                      */
/*==============================================================*/
alter table biz_order_distribute_log
add   old_employee_id                int(11)         comment '原员工id -- \'',
add   reason_type                    varchar(30)     comment '原因类型 -- \'',
add   reason_remarks                 varchar(255)    comment '原因说明 -- \'';

