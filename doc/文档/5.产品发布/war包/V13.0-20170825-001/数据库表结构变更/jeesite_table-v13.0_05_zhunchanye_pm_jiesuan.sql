/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/8/8 8:38:34                             */
/*==============================================================*/

drop table if exists biz_order_change;

drop table if exists biz_order_contract_settle;

drop table if exists biz_order_material_carry_cost;

drop table if exists biz_pm_pre_industry_settle_bill;

drop table if exists biz_pm_pre_industry_settle_summary_bill;

drop table if exists biz_pm_subsidy_cnfg;

/*==============================================================*/
/* Table: biz_normal_pm_settle_node（modify）                     */
/*==============================================================*/
alter table biz_normal_pm_settle_node
add   project_mode                   varchar(10)     comment '工程模式（添加） -- \'',
add   settle_stage                   varchar(20)     comment '结算阶段（添加） -- \'',
add   settle_price                   decimal(8,2)    comment '结算单价（添加） -- \'';

/*==============================================================*/
/* Table: biz_order_change                                      */
/*==============================================================*/
create table biz_order_change
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   change_type                    varchar(10)     comment '变更类型 -- \'10.基装完工增项；20.中期增项；30.中期减项；40.竣工增项；50.竣工减项',
   order_id                       int(11)         comment '订单id -- \'',
   change_amount                  decimal(8,2)    comment '变更金额 -- \'',
   change_account_rate            decimal(8,2)    comment '变更核算比例 -- \'',
   change_account_amount          decimal(8,2)    comment '变更核算金额 -- \'',
   change_remarks                 varchar(1000)   comment '变更说明 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_contract_settle                             */
/*==============================================================*/
create table biz_order_contract_settle
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_id                       int(11)         comment '订单id -- \'',
   settle_stage                   varchar(10)     comment '承包结算阶段 -- \'10.中期结算；20.竣工结算',
   packaged_square                decimal(10,2)   comment '套餐计价面积 -- \'',
   packaged_price                 decimal(10,2)   comment '套餐单价 -- \'',
   packaged_amount                decimal(10,2)   comment '套餐总金额 -- \'',
   contract_subsidy_square        decimal(10,2)   comment '承包补助面积 -- \'',
   contract_subsidy_price         decimal(10,2)   comment '承包补助单价 -- \'',
   contract_subsidy_amount        decimal(10,2)   comment '承包补助总金额 -- \'',
   contract_amount                decimal(10,2)   comment '承包总金额 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_material_carry_cost                         */
/*==============================================================*/
create table biz_order_material_carry_cost
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_id                       int(11)         comment '订单id -- \'',
   carry_cost_amount              decimal(8,2)    comment '搬运运输费 -- \'',
   account_rate                   decimal(8,2)    comment '核算比例 -- \'',
   account_amount                 decimal(8,2)    comment '核算金额 -- \'',
   carry_cost_remarks             varchar(100)    comment '搬运运输费说明 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_pm_pre_industry_settle_bill                       */
/*==============================================================*/
create table biz_pm_pre_industry_settle_bill
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   pm_pre_industry_settle_bill_code varchar(100)    comment '项目经理结算单编号 -- \'',
   pm_pre_industry_settle_summary_bill_id int(11)         comment '项目经理准产业结算汇总单id -- \'',
   settle_bill_type               varchar(10)     comment '结算单类型 -- \'1.中期结算单；2.竣工结算单',
   order_id                       int(11)         comment '订单id -- \'',
   pm_employee_id                 int(11)         comment '项目经理员工id -- \'',
   settle_month                   varchar(10)     comment '结算月份 -- \'',
   settle_datetime                datetime        comment '结算日期时间 -- \'',
   contract_amount                decimal(10,2)   comment '承包总金额 -- \'',
   midway_qc_check_punish_amount  decimal(10,2)   comment '质检罚款金额 -- \'',
   reward_amount                  decimal(10,2)   comment '奖励金额 -- \'',
   punish_amount                  decimal(10,2)   comment '扣款金额 -- \'',
   order_change_add_amount        decimal(10,2)   comment '变更增项金额 -- \'',
   order_change_reduce_amount     decimal(10,2)   comment '变更减项金额 -- \'',
   midway_basicwork_add_amount    decimal(10,2)   comment '中期基装增项金额 -- \'',
   midway_materials_standard_amount decimal(10,2)   comment '中期标化材料扣款金额 -- \'',
   midway_materials_auxiliary_amount decimal(10,2)   comment '中期辅料用量扣款金额 -- \'',
   midway_sand_cement_amount      decimal(10,2)   comment '中期沙子水泥扣款金额 -- \'',
   midway_switch_panel_amount     decimal(10,2)   comment '中期开关面板扣款金额 -- \'',
   midway_worker_salary_amount    decimal(10,2)   comment '中期工人人工费扣款金额 -- \'',
   midway_material_carry_cost_amount decimal(10,2)   comment '中期材料搬运及运输费金额 -- \'',
   midway_contract_settle_rate    decimal(4,2)    comment '中期承包价结算比例 -- \'',
   complete_guarantee_money_amount decimal(10,2)   comment '竣工质保金金额 -- \'',
   complete_longway_commission_amount decimal(10,2)   comment '竣工远程费金额 -- \'',
   contract_settle_amount         decimal(10,2)   comment '承包价结算金额 -- \'',
   real_settle_amount             decimal(10,2)   comment '实际结算金额 -- \'',
   status                         varchar(10)     comment '状态 -- \'',
   status_operator_employee_id    int(11)         comment '状态操作人员工id -- \'',
   status_datetime                datetime        comment '状态日期时间 -- \'',
   status_describe                varchar(1000)   comment '状态描述 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_pm_pre_industry_settle_summary_bill               */
/*==============================================================*/
create table biz_pm_pre_industry_settle_summary_bill
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   pm_pre_industry_settle_summary_bill varchar(100)    comment '项目经理产业结算汇总单编号 -- \'',
   store_id                       int(11)         comment '门店id -- \'',
   engin_depart_id                int(11)         comment '工程部id -- \'',
   settle_month                   varchar(10)     comment '结算月份 -- \'',
   pm_employee_id                 int(11)         comment '项目经理员工id -- \'',
   contract_amount                decimal(10,2)   comment '承包总金额 -- \'',
   midway_qc_check_punish_amount  decimal(10,2)   comment '质检罚款金额 -- \'',
   reward_amount                  decimal(10,2)   comment '奖励金额 -- \'',
   punish_amount                  decimal(10,2)   comment '扣款金额 -- \'',
   order_change_add_amount        decimal(10,2)   comment '变更增项金额 -- \'',
   order_change_reduce_amount     decimal(10,2)   comment '变更减项金额 -- \'',
   midway_basicwork_add_amount    decimal(10,2)   comment '中期基装增项金额 -- \'',
   midway_materials_standard_amount decimal(10,2)   comment '中期标化材料扣款金额 -- \'',
   midway_materials_auxiliary_amount decimal(10,2)   comment '中期辅料用量扣款金额 -- \'',
   midway_sand_cement_amount      decimal(10,2)   comment '中期沙子水泥扣款金额 -- \'',
   midway_switch_panel_amount     decimal(10,2)   comment '中期开关面板扣款金额 -- \'',
   midway_worker_salary_amount    decimal(10,2)   comment '中期工人人工费扣款金额 -- \'',
   midway_material_carry_cost_amount decimal(10,2)   comment '中期材料搬运及运输费金额 -- \'',
   complete_guarantee_money_amount decimal(10,2)   comment '竣工质保金金额 -- \'',
   complete_longway_commission_amount decimal(10,2)   comment '竣工远程费金额 -- \'',
   contract_settle_amount         decimal(10,2)   comment '承包价结算金额 -- \'',
   real_settle_amount             decimal(10,2)   comment '实际结算金额 -- \'',
   status                         varchar(10)     comment '状态 -- \'',
   status_operator_employee_id    int(11)         comment '状态操作人员工id -- \'',
   status_datetime                datetime        comment '状态日期时间 -- \'',
   status_describe                varchar(1000)   comment '状态描述 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_pm_subsidy_cnfg                                   */
/*==============================================================*/
create table biz_pm_subsidy_cnfg
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   store_id                       int(11)         comment '门店id -- \'',
   project_mode                   varchar(10)     comment '工程模式 -- \'',
   square_min                     decimal(8,2)    comment '面积开始值 -- \'',
   square_max                     decimal(8,2)    comment '面积结束值 -- \'',
   subsidy_price                  decimal(8,2)    comment '补助单价 -- \'',
   is_enabled                     char(1)         comment '是否启用 -- \'0.否；1.是',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_qc_check_node（modify3）                            */
/*==============================================================*/
alter table biz_qc_check_node
add   is_for_basicwork               char(1)         comment '是否基装约检节点（添加） -- \'0.否；1.是';

/*==============================================================*/
/* Table: biz_qc_longway_commission_log（modify）                 */
/*==============================================================*/
alter table biz_qc_longway_commission_log
add   longway_commission_type        varchar(10)     comment '远程费类型（添加） -- \'10.项目经理竣工远程费；20.质检员远程费',
change  qc_employee_id   longway_commission_employee_id int(11)         comment '远程费员工id（修改） -- \'修改质检员员工id';

