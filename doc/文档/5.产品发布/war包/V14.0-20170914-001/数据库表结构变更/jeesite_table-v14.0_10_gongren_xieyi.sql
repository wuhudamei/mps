/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/9/4 18:19:46                            */
/*==============================================================*/


drop table if exists biz_employee_agreement;

/*==============================================================*/
/* Table: biz_employee_agreement                                */
/*==============================================================*/
create table biz_employee_agreement
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   employee_type                  varchar(10)     comment '员工类型 -- \'',
   employee_id                    int(11)         comment '员工id -- \'',
   is_sign_employee_agreement     char(1)         comment '是否签定协议 -- \'0.否；1.是；',
   employee_agreement_sign_datetime datetime        comment '签定协议日期时间 -- \'',
   employee_agreement_read_datetime datetime        comment '最后阅读协议日期时间 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

