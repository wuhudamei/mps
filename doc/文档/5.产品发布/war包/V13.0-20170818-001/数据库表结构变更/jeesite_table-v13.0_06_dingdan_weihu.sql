/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/8/8 17:47:40                            */
/*==============================================================*/


/*==============================================================*/
/* Table: biz_order（modify11）                                   */
/*==============================================================*/
alter table biz_order
add   is_scrap                       char(1)         comment '是否作废（添加） -- \'0.否；1.是',
add   scrap_operator_employee_id     int(11)         comment '作废操作人员工id（添加） -- \'',
add   scrap_datetime                 datetime        comment '作废日期时间（添加） -- \'',
add   scrap_describe                 varchar(1000)   comment '作废说明（添加） -- \'',
add   qc_complete_accept_check_datetime datetime        comment '质检竣工验收日期时间（添加） -- \'',
add   scrap_reason_type              varchar(10)     comment '作废原因类型 -- \'';

