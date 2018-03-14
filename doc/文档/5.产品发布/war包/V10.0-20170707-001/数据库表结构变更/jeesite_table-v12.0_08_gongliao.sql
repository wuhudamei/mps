/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/6/23 18:27:00                           */
/*==============================================================*/


/*==============================================================*/
/* Table: biz_order_taskpackage_procedure（modify2）              */
/*==============================================================*/
alter table biz_order_taskpackage_procedure
add   labor_budget_amount            decimal(10,2)   comment '人工费预算金额（添加） -- \'',
add   labor_settle_amount            decimal(10,2)   comment '人工费结算金额（添加） -- \'',
add   auxiliary_materials_budget_amount decimal(10,2)   comment '辅料费预算金额（添加） -- \'',
add   auxiliary_materials_settle_amount decimal(10,2)   comment '辅料费结算金额（添加） -- \'',
change total   labor_auxiliary_materials_budget_amount decimal(10,2)   comment '工料费预算金额（修改total） -- \'',
add   labor_auxiliary_materials_settle_amount decimal(10,2)   comment '工料费结算金额（添加） -- \'';

/*==============================================================*/
/* Table: biz_order_taskpackage_settlement（modify8）             */
/*==============================================================*/
alter  table biz_order_taskpackage_settlement
add   settle_style                   varchar(10)     comment '结算方式（添加） -- \'1.包工包料；2.包工',
add   worker_group_settle_amount     decimal(10,2)   comment '工人组结算金额（添加） -- \'',
add   pm_materials_settle_amount     decimal(10,2)   comment '项目经理材料结算金额（添加） -- \'',
add   labor_auxiliary_materials_settle_amount decimal(10,2)   comment '工料费结算总金额（添加） -- \'',
add   labor_settle_amount            decimal(10,2)   comment '人工费结算总金额（添加） -- \'',
add   auxiliary_materials_settle_amount decimal(10,2)   comment '辅料费结算总金额（添加） -- \'',
change auxiliary_materials_amount   auxiliary_materials_deduct_amount decimal(10,2)   comment '辅料扣除金额（修改auxiliary_materials_amount） -- \'',
change sand_cement_amount   sand_cement_deduct_amount      decimal(10,2)   comment '沙子水泥扣除金额（修改sand_cement_amount） -- \'';

/*==============================================================*/
/* Table: biz_order_taskpackage（modify）                         */
/*==============================================================*/
alter table biz_order_taskpackage
add   labor_budget_amount            decimal(10,2)   comment '人工费预算总金额（添加） -- \'',
add   auxiliary_materials_budget_amount decimal(10,2)   comment '辅料费预算总金额（添加） -- \'',
change  total  labor_auxiliary_materials_budget_amount decimal(10,2)   comment '工料费预算总金额（修改total） -- \'',
add   settle_style                   varchar(10)     comment '结算方式（添加） -- \'1.包工包料；2.包工';

/*==============================================================*/
/* Table: biz_task_package_templat（modify）                      */
/*==============================================================*/
alter table biz_task_package_templat
add   settle_style                   varchar(10)     comment '结算方式（添加） -- \'1.包工包料；2.包工';

/*==============================================================*/
/* Table: biz_pm_settle_bill（modify4）                           */
/*==============================================================*/
alter table biz_pm_settle_bill
add   midway_auxiliary_materials_settle_amount decimal(10,2)   comment '中期辅料结算金额（添加） -- \'',
add   complete_auxiliary_materials_settle_amount decimal(10,2)   comment '竣工辅料结算金额（添加） -- \'';

/*==============================================================*/
/* Table: biz_pm_settle_summary_bill（modify4）                   */
/*==============================================================*/
alter table biz_pm_settle_summary_bill
add   midway_auxiliary_materials_settle_amount decimal(10,2)   comment '中期辅料结算金额（添加） -- \'',
add   complete_auxiliary_materials_settle_amount decimal(10,2)   comment '竣工辅料结算金额（添加） -- \'';


