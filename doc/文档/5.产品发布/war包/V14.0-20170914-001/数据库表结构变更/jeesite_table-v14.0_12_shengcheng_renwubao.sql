/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/9/8 11:11:02                            */
/*==============================================================*/


drop table if exists biz_task_package_templat_budget_amount_limit;

/*==============================================================*/
/* Table: biz_task_package_templat_budget_amount_limit          */
/*==============================================================*/
create table biz_task_package_templat_budget_amount_limit
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   taskpackage_templat_id         int(11)         comment '任务包模板id -- \'',
   budget_square_max              decimal(8,2)    comment '最大预算面积 -- \'',
   budget_square_min              decimal(8,2)    comment '最小预算面积 -- \'',
   labor_auxiliary_materials_budget_amount_max decimal(10,2)   comment '工料费预算金额上限 -- \'',
   is_enabled                     char(1)         comment '是否启用 -- \'0.否；1.是',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

