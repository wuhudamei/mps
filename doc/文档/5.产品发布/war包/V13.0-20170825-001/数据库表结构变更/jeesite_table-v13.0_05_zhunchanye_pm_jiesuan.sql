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
/* Table: biz_normal_pm_settle_node��modify��                     */
/*==============================================================*/
alter table biz_normal_pm_settle_node
add   project_mode                   varchar(10)     comment '����ģʽ����ӣ� -- \'',
add   settle_stage                   varchar(20)     comment '����׶Σ���ӣ� -- \'',
add   settle_price                   decimal(8,2)    comment '���㵥�ۣ���ӣ� -- \'';

/*==============================================================*/
/* Table: biz_order_change                                      */
/*==============================================================*/
create table biz_order_change
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   change_type                    varchar(10)     comment '������� -- \'10.��װ�깤���20.�������30.���ڼ��40.�������50.��������',
   order_id                       int(11)         comment '����id -- \'',
   change_amount                  decimal(8,2)    comment '������ -- \'',
   change_account_rate            decimal(8,2)    comment '���������� -- \'',
   change_account_amount          decimal(8,2)    comment '��������� -- \'',
   change_remarks                 varchar(1000)   comment '���˵�� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_contract_settle                             */
/*==============================================================*/
create table biz_order_contract_settle
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_id                       int(11)         comment '����id -- \'',
   settle_stage                   varchar(10)     comment '�а�����׶� -- \'10.���ڽ��㣻20.��������',
   packaged_square                decimal(10,2)   comment '�ײͼƼ���� -- \'',
   packaged_price                 decimal(10,2)   comment '�ײ͵��� -- \'',
   packaged_amount                decimal(10,2)   comment '�ײ��ܽ�� -- \'',
   contract_subsidy_square        decimal(10,2)   comment '�а�������� -- \'',
   contract_subsidy_price         decimal(10,2)   comment '�а��������� -- \'',
   contract_subsidy_amount        decimal(10,2)   comment '�а������ܽ�� -- \'',
   contract_amount                decimal(10,2)   comment '�а��ܽ�� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_material_carry_cost                         */
/*==============================================================*/
create table biz_order_material_carry_cost
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_id                       int(11)         comment '����id -- \'',
   carry_cost_amount              decimal(8,2)    comment '��������� -- \'',
   account_rate                   decimal(8,2)    comment '������� -- \'',
   account_amount                 decimal(8,2)    comment '������ -- \'',
   carry_cost_remarks             varchar(100)    comment '���������˵�� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_pm_pre_industry_settle_bill                       */
/*==============================================================*/
create table biz_pm_pre_industry_settle_bill
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   pm_pre_industry_settle_bill_code varchar(100)    comment '��Ŀ������㵥��� -- \'',
   pm_pre_industry_settle_summary_bill_id int(11)         comment '��Ŀ����׼��ҵ������ܵ�id -- \'',
   settle_bill_type               varchar(10)     comment '���㵥���� -- \'1.���ڽ��㵥��2.�������㵥',
   order_id                       int(11)         comment '����id -- \'',
   pm_employee_id                 int(11)         comment '��Ŀ����Ա��id -- \'',
   settle_month                   varchar(10)     comment '�����·� -- \'',
   settle_datetime                datetime        comment '��������ʱ�� -- \'',
   contract_amount                decimal(10,2)   comment '�а��ܽ�� -- \'',
   midway_qc_check_punish_amount  decimal(10,2)   comment '�ʼ췣���� -- \'',
   reward_amount                  decimal(10,2)   comment '������� -- \'',
   punish_amount                  decimal(10,2)   comment '�ۿ��� -- \'',
   order_change_add_amount        decimal(10,2)   comment '��������� -- \'',
   order_change_reduce_amount     decimal(10,2)   comment '��������� -- \'',
   midway_basicwork_add_amount    decimal(10,2)   comment '���ڻ�װ������ -- \'',
   midway_materials_standard_amount decimal(10,2)   comment '���ڱ껯���Ͽۿ��� -- \'',
   midway_materials_auxiliary_amount decimal(10,2)   comment '���ڸ��������ۿ��� -- \'',
   midway_sand_cement_amount      decimal(10,2)   comment '����ɳ��ˮ��ۿ��� -- \'',
   midway_switch_panel_amount     decimal(10,2)   comment '���ڿ������ۿ��� -- \'',
   midway_worker_salary_amount    decimal(10,2)   comment '���ڹ����˹��ѿۿ��� -- \'',
   midway_material_carry_cost_amount decimal(10,2)   comment '���ڲ��ϰ��˼�����ѽ�� -- \'',
   midway_contract_settle_rate    decimal(4,2)    comment '���ڳа��۽������ -- \'',
   complete_guarantee_money_amount decimal(10,2)   comment '�����ʱ����� -- \'',
   complete_longway_commission_amount decimal(10,2)   comment '����Զ�̷ѽ�� -- \'',
   contract_settle_amount         decimal(10,2)   comment '�а��۽����� -- \'',
   real_settle_amount             decimal(10,2)   comment 'ʵ�ʽ����� -- \'',
   status                         varchar(10)     comment '״̬ -- \'',
   status_operator_employee_id    int(11)         comment '״̬������Ա��id -- \'',
   status_datetime                datetime        comment '״̬����ʱ�� -- \'',
   status_describe                varchar(1000)   comment '״̬���� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_pm_pre_industry_settle_summary_bill               */
/*==============================================================*/
create table biz_pm_pre_industry_settle_summary_bill
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   pm_pre_industry_settle_summary_bill varchar(100)    comment '��Ŀ�����ҵ������ܵ���� -- \'',
   store_id                       int(11)         comment '�ŵ�id -- \'',
   engin_depart_id                int(11)         comment '���̲�id -- \'',
   settle_month                   varchar(10)     comment '�����·� -- \'',
   pm_employee_id                 int(11)         comment '��Ŀ����Ա��id -- \'',
   contract_amount                decimal(10,2)   comment '�а��ܽ�� -- \'',
   midway_qc_check_punish_amount  decimal(10,2)   comment '�ʼ췣���� -- \'',
   reward_amount                  decimal(10,2)   comment '������� -- \'',
   punish_amount                  decimal(10,2)   comment '�ۿ��� -- \'',
   order_change_add_amount        decimal(10,2)   comment '��������� -- \'',
   order_change_reduce_amount     decimal(10,2)   comment '��������� -- \'',
   midway_basicwork_add_amount    decimal(10,2)   comment '���ڻ�װ������ -- \'',
   midway_materials_standard_amount decimal(10,2)   comment '���ڱ껯���Ͽۿ��� -- \'',
   midway_materials_auxiliary_amount decimal(10,2)   comment '���ڸ��������ۿ��� -- \'',
   midway_sand_cement_amount      decimal(10,2)   comment '����ɳ��ˮ��ۿ��� -- \'',
   midway_switch_panel_amount     decimal(10,2)   comment '���ڿ������ۿ��� -- \'',
   midway_worker_salary_amount    decimal(10,2)   comment '���ڹ����˹��ѿۿ��� -- \'',
   midway_material_carry_cost_amount decimal(10,2)   comment '���ڲ��ϰ��˼�����ѽ�� -- \'',
   complete_guarantee_money_amount decimal(10,2)   comment '�����ʱ����� -- \'',
   complete_longway_commission_amount decimal(10,2)   comment '����Զ�̷ѽ�� -- \'',
   contract_settle_amount         decimal(10,2)   comment '�а��۽����� -- \'',
   real_settle_amount             decimal(10,2)   comment 'ʵ�ʽ����� -- \'',
   status                         varchar(10)     comment '״̬ -- \'',
   status_operator_employee_id    int(11)         comment '״̬������Ա��id -- \'',
   status_datetime                datetime        comment '״̬����ʱ�� -- \'',
   status_describe                varchar(1000)   comment '״̬���� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_pm_subsidy_cnfg                                   */
/*==============================================================*/
create table biz_pm_subsidy_cnfg
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   store_id                       int(11)         comment '�ŵ�id -- \'',
   project_mode                   varchar(10)     comment '����ģʽ -- \'',
   square_min                     decimal(8,2)    comment '�����ʼֵ -- \'',
   square_max                     decimal(8,2)    comment '�������ֵ -- \'',
   subsidy_price                  decimal(8,2)    comment '�������� -- \'',
   is_enabled                     char(1)         comment '�Ƿ����� -- \'0.��1.��',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_qc_check_node��modify3��                            */
/*==============================================================*/
alter table biz_qc_check_node
add   is_for_basicwork               char(1)         comment '�Ƿ��װԼ��ڵ㣨��ӣ� -- \'0.��1.��';

/*==============================================================*/
/* Table: biz_qc_longway_commission_log��modify��                 */
/*==============================================================*/
alter table biz_qc_longway_commission_log
add   longway_commission_type        varchar(10)     comment 'Զ�̷����ͣ���ӣ� -- \'10.��Ŀ������Զ�̷ѣ�20.�ʼ�ԱԶ�̷�',
change  qc_employee_id   longway_commission_employee_id int(11)         comment 'Զ�̷�Ա��id���޸ģ� -- \'�޸��ʼ�ԱԱ��id';

