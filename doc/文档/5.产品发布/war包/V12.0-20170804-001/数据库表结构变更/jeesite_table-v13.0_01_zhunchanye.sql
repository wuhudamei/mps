/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/7/24 17:48:18                           */
/*==============================================================*/


/*==============================================================*/
/* Table: biz_employeegroup（modify）                             */
/*==============================================================*/
alter table biz_employeegroup
add   project_mode                   varchar(10)     comment '工程模式（添加） -- \'';

/*==============================================================*/
/* Table: biz_order_taskpackage_procedure（modify3）              */
/*==============================================================*/
alter table biz_order_taskpackage_procedure
add   project_mode                   varchar(10)     comment '工程模式（添加） -- \'';

/*==============================================================*/
/* Table: biz_order_taskpackage（modify2）                        */
/*==============================================================*/
alter table biz_order_taskpackage
add   project_mode                   varchar(10)     comment '工程模式（添加） -- \'';

/*==============================================================*/
/* Table: biz_procedure_price（modify）                           */
/*==============================================================*/
alter table biz_procedure_price
add   project_mode                   varchar(10)     comment '工程模式（添加） -- \'';

/*==============================================================*/
/* Table: biz_task_package_auxiliary_materials（modify）          */
/*==============================================================*/
alter table biz_task_package_auxiliary_materials
add   project_mode                   varchar(10)     comment '工程模式（添加） -- \'';

/*==============================================================*/
/* Table: biz_task_package_templat（modify3）                     */
/*==============================================================*/
alter table biz_task_package_templat
add   project_mode                   varchar(10)     comment '工程模式（添加） -- \'';

/*==============================================================*/
/* Table: biz_order_taskpackage_payment_summary（modify3）        */
/*==============================================================*/
alter table biz_order_taskpackage_payment_summary
add   project_mode                   varchar(10)     comment '工程模式（添加） -- \'';

/*==============================================================*/
/* Table: biz_task_package_work_plan_templat（modify）            */
/*==============================================================*/
alter table biz_task_package_work_plan_templat
add   project_mode                   varchar(10)     comment '工程模式（添加） -- \'';

