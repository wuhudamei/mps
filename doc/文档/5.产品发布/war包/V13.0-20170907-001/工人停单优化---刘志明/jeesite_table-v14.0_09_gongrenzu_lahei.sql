/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/9/4 18:18:49                            */
/*==============================================================*/


/*==============================================================*/
/* Table: biz_employeegroup（modify2）                            */
/*==============================================================*/
alter table biz_employeegroup
add   order_stop_reason_type         varchar(10)     comment '停单原因类型 -- \'',
add   order_stop_reason              varchar(1000)   comment '停单原因 -- \'',
add   order_stop_operator_employee_id int(11)         comment '停单操作人 -- \'',
add   order_stop_operate_datetime    datetime        comment '停单操作时间 -- \'';

