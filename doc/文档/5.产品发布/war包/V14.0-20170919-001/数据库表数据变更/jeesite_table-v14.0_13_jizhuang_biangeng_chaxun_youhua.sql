/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/9/18 15:28:50                           */
/*==============================================================*/


/*==============================================================*/
/* Table: biz_project_change_bill（modify2）                      */
/*==============================================================*/
alter table biz_project_change_bill
add   is_dealed_by_engineering_department char(1)         comment '是否工程部已处理 -- \'0.否；1.是',
add   engineering_department_deal_emplyee_id int(11)         comment '工程部处理人员工id -- \'',
add   engineering_department_deal_datetime datetime        comment '工程部处理日期时间 -- \'',
add   engineering_department_deal_describe varchar(1000)   comment '工程部处理说明 -- \'';

