/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/7/26 14:35:21                           */
/*==============================================================*/


/*==============================================================*/
/* Table: biz_cus_service_problem（modify）                       */
/*==============================================================*/
alter table biz_cus_service_problem
add   question_type1                 varchar(1000)   comment '问题类型1 -- \'',
add   question_type2                 varchar(1000)   comment '问题类型2 -- \'',
add   work_source                    varchar(1000)   comment '工单来源 -- \'',
add   complaintType                  varchar(1000)   comment '投诉类型 -- \'';
