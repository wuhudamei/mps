/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/2 18:21:11                            */
/*==============================================================*/


drop table if exists biz_business_urge;

/*==============================================================*/
/* Table: biz_business_urge                                     */
/*==============================================================*/
create table biz_business_urge
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   business_only_mark_int         int(11)         comment '业务唯一标识整型 -- \'',
   business_only_mark_varchar     varchar(100)    comment '业务唯一标识字符型 -- \'',
   busines_type                   varchar(10)     comment '业务类型 -- \'1.主材安装申请；2.墙地砖采购',
   operate_type                   varchar(10)     comment '操作类型 -- \'1.催促；2.回复',
   operate_content                varchar(255)    comment '操作内容 -- \'',
   operator_employee_id           int(11)         comment '操作人员工id -- \'',
   operator_type                  varchar(10)     comment '操作人类型 -- \'1.项目经理；2.材料部',
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
/* Table: biz_order_install_plan                                */
/*==============================================================*/
alter table biz_order_install_plan
add  supplier_confirm_remarks       varchar(255)    comment '供应商确认说明（添加） -- \'';

