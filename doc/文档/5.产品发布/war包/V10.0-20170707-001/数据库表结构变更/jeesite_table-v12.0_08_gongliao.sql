/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/6/23 18:27:00                           */
/*==============================================================*/


/*==============================================================*/
/* Table: biz_order_taskpackage_procedure��modify2��              */
/*==============================================================*/
alter table biz_order_taskpackage_procedure
add   labor_budget_amount            decimal(10,2)   comment '�˹���Ԥ�����ӣ� -- \'',
add   labor_settle_amount            decimal(10,2)   comment '�˹��ѽ������ӣ� -- \'',
add   auxiliary_materials_budget_amount decimal(10,2)   comment '���Ϸ�Ԥ�����ӣ� -- \'',
add   auxiliary_materials_settle_amount decimal(10,2)   comment '���Ϸѽ������ӣ� -- \'',
change total   labor_auxiliary_materials_budget_amount decimal(10,2)   comment '���Ϸ�Ԥ����޸�total�� -- \'',
add   labor_auxiliary_materials_settle_amount decimal(10,2)   comment '���Ϸѽ������ӣ� -- \'';

/*==============================================================*/
/* Table: biz_order_taskpackage_settlement��modify8��             */
/*==============================================================*/
alter  table biz_order_taskpackage_settlement
add   settle_style                   varchar(10)     comment '���㷽ʽ����ӣ� -- \'1.�������ϣ�2.����',
add   worker_group_settle_amount     decimal(10,2)   comment '������������ӣ� -- \'',
add   pm_materials_settle_amount     decimal(10,2)   comment '��Ŀ������Ͻ������ӣ� -- \'',
add   labor_auxiliary_materials_settle_amount decimal(10,2)   comment '���Ϸѽ����ܽ���ӣ� -- \'',
add   labor_settle_amount            decimal(10,2)   comment '�˹��ѽ����ܽ���ӣ� -- \'',
add   auxiliary_materials_settle_amount decimal(10,2)   comment '���Ϸѽ����ܽ���ӣ� -- \'',
change auxiliary_materials_amount   auxiliary_materials_deduct_amount decimal(10,2)   comment '���Ͽ۳����޸�auxiliary_materials_amount�� -- \'',
change sand_cement_amount   sand_cement_deduct_amount      decimal(10,2)   comment 'ɳ��ˮ��۳����޸�sand_cement_amount�� -- \'';

/*==============================================================*/
/* Table: biz_order_taskpackage��modify��                         */
/*==============================================================*/
alter table biz_order_taskpackage
add   labor_budget_amount            decimal(10,2)   comment '�˹���Ԥ���ܽ���ӣ� -- \'',
add   auxiliary_materials_budget_amount decimal(10,2)   comment '���Ϸ�Ԥ���ܽ���ӣ� -- \'',
change  total  labor_auxiliary_materials_budget_amount decimal(10,2)   comment '���Ϸ�Ԥ���ܽ��޸�total�� -- \'',
add   settle_style                   varchar(10)     comment '���㷽ʽ����ӣ� -- \'1.�������ϣ�2.����';

/*==============================================================*/
/* Table: biz_task_package_templat��modify��                      */
/*==============================================================*/
alter table biz_task_package_templat
add   settle_style                   varchar(10)     comment '���㷽ʽ����ӣ� -- \'1.�������ϣ�2.����';

/*==============================================================*/
/* Table: biz_pm_settle_bill��modify4��                           */
/*==============================================================*/
alter table biz_pm_settle_bill
add   midway_auxiliary_materials_settle_amount decimal(10,2)   comment '���ڸ��Ͻ������ӣ� -- \'',
add   complete_auxiliary_materials_settle_amount decimal(10,2)   comment '�������Ͻ������ӣ� -- \'';

/*==============================================================*/
/* Table: biz_pm_settle_summary_bill��modify4��                   */
/*==============================================================*/
alter table biz_pm_settle_summary_bill
add   midway_auxiliary_materials_settle_amount decimal(10,2)   comment '���ڸ��Ͻ������ӣ� -- \'',
add   complete_auxiliary_materials_settle_amount decimal(10,2)   comment '�������Ͻ������ӣ� -- \'';


