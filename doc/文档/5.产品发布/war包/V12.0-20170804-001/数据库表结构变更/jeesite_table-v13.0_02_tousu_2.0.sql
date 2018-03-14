/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/7/24 14:52:01                           */
/*==============================================================*/


/*==============================================================*/
/* Table: biz_order_complaint（modify）                           */
/*==============================================================*/
alter table biz_order_complaint
add   complaint_person_employee_id   int(11)         comment '投诉人员工id -- \'',
add   related_business_type          varchar(10)     comment '关联业务类型（添加） -- \'',
add   related_business_id_int        int(11)         comment '关联业务id整型（修改cus_service_problem_id） -- \'';

