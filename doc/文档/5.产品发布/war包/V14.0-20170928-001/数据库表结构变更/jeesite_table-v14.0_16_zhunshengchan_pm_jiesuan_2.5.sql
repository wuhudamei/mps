/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/9/15 15:11:56                           */
/*==============================================================*/



/*==============================================================*/
/* Table: biz_order_finance_collection（modify）                  */
/*==============================================================*/
alter table biz_order_finance_collection
add   collection_status              varchar(10)     comment '收款状态（添加） -- \'',
add   collection_operator_employee_id int(11)         comment '收款操作人员工id（添加） -- \'',
add   collection_remarks             varchar(1000)   comment '收款说明（添加） -- \'',
add   collection_operate_datetime    datetime        comment '收款操作日期时间 -- \'';

/*==============================================================*/
/* Table: biz_pre_pm_settle_finance_receive_moeny（modify）       */
/*==============================================================*/
alter table biz_pre_pm_settle_finance_receive_moeny
add   collection_status              varchar(10)     comment '收款状态（添加） -- \'';

