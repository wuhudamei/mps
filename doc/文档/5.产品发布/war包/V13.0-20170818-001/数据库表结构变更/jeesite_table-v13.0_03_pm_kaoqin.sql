/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/8/1 19:41:13                            */
/*==============================================================*/


drop table if exists biz_pm_attend_cnfg;

drop table if exists biz_pm_attend_cnfg_star;

drop table if exists biz_pm_attend_day_order;

drop table if exists biz_pm_attend_month;

drop table if exists biz_pm_attend_month_order;

drop table if exists biz_pm_attend_salary_bill;

drop table if exists biz_pm_attend_salary_summary_bill;

drop table if exists biz_pm_attend_salary_summary_bill_rel;

/*==============================================================*/
/* Table: biz_business_sign��modify��                             */
/*==============================================================*/
alter table biz_business_sign
add   sign_step                      varchar(10)     comment 'ǩ���׶Σ���ӣ� -- \'10.��װ��20.����',
add   is_valid                       char(1)         comment '�Ƿ�ϸ���ӣ� -- \'0.��1.��';


/*==============================================================*/
/* Table: biz_sign��modify��                                      */
/*==============================================================*/
alter table biz_sign
add   sign_step                      varchar(10)     comment 'ǩ���׶Σ���ӣ� -- \'10.��װ��20.����',
add   is_valid                       char(1)         comment '�Ƿ�ϸ���ӣ� -- \'0.��1.��';


/*==============================================================*/
/* Table: biz_pm_attend_cnfg                                    */
/*==============================================================*/
create table biz_pm_attend_cnfg
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   store_id                       int(11)         comment '�ŵ�id -- \'',
   project_mode                   varchar(10)     comment '����ģʽ -- \'',
   absence_punish_amount          decimal(8,2)    comment 'ȱ�ڿۿ��� -- \'',
   sign_cycle_days_basicwork      int             comment '��װǩ���������� -- \'',
   sign_cycle_days_complete       int             comment '����ǩ���������� -- \'',
   effect_month                   varchar(20)     comment '��Ч�·� -- \'',
   is_enabled                     char(1)         comment '�Ƿ����� -- \'0.ͣ�ã�1.����',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_pm_attend_cnfg_star                               */
/*==============================================================*/
create table biz_pm_attend_cnfg_star
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   pm_attend_cnfg_id              int(11)         comment 'pm��������id -- \'',
   star                           int             comment '�Ǽ� -- \'',
   star_salary_all_attend         decimal(8,2)    comment '�Ǽ�ȫ�ڵ�н -- \'',
   star_salary_min                decimal(8,2)    comment '�Ǽ����нˮ -- \'',
   is_enabled                     char(1)         comment '�Ƿ����� -- \'0.ͣ�ã�1.����',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_pm_attend_day_order                               */
/*==============================================================*/
create table biz_pm_attend_day_order
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_id                       int(11)         comment '����id -- \'',
   pm_employee_id                 int(11)         comment 'pmԱ��id -- \'',
   sign_datetime                  datetime        comment 'ǩ������ʱ�� -- \'',
   sign_error_distance            decimal(8,2)    comment 'ǩ����� -- \'',
   is_valid                       char(1)         comment '�Ƿ�ϸ� -- \'0.��1.��',
   sign_step                      varchar(10)     comment 'ǩ���׶� -- \'10.��װ��20.����',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_pm_attend_month                                   */
/*==============================================================*/
create table biz_pm_attend_month
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   pm_attend_month_code           varchar(100)    comment 'pm�����¶ȱ��� -- \'',
   attend_month                   varchar(20)     comment '�����·� -- \'',
   pm_employee_id                 int(11)         comment 'pmԱ��id -- \'',
   attend_start_date              datetime        comment '���ڿ�ʼ���� -- \'',
   attend_end_date                datetime        comment '���ڽ������� -- \'',
   must_sign_times                int             comment 'Ӧǩ������ -- \'',
   real_sign_times                int             comment 'ʵ��ǩ������ -- \'',
   owed_sign_times                int             comment 'Ƿȱǩ������ -- \'',
   attend_rate                    decimal(5,2)    comment '������ -- \'',
   status                         varchar(10)     comment '״̬ -- \'',
   status_datetime                datetime        comment '״̬����ʱ�� -- \'',
   status_employee_id             int(11)         comment '״̬������Ա��id -- \'',
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
/* Table: biz_pm_attend_month_order                             */
/*==============================================================*/
create table biz_pm_attend_month_order
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   pm_attend_month_id             int(11)         comment 'pm�����¶�id -- \'',
   order_id                       int(11)         comment '����id -- \'',
   pm_employee_id                 int(11)         comment 'pmԱ��id -- \'',
   must_sign_times                int             comment 'Ӧǩ������ -- \'',
   real_sign_times                int             comment 'ʵ��ǩ������ -- \'',
   owed_sign_times                int             comment 'Ƿȱǩ������ -- \'',
   attend_rate                    decimal(5,2)    comment '������ -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_pm_attend_salary_bill                             */
/*==============================================================*/
create table biz_pm_attend_salary_bill
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   pm_attend_salary_bill_code     varchar(100)    comment '���ʵ���� -- \'',
   pm_attend_salary_summary_bill_id int(11)         comment 'pm���ʻ��ܵ�id -- \'',
   pm_attend_month_id             int(11)         comment 'pm�����¶�id -- \'',
   pm_employee_id                 int(11)         comment 'pmԱ��id -- \'',
   pm_star                        int             comment 'pm�Ǽ� -- \'',
   pm_star_salary                 decimal(8,2)    comment 'pm�Ǽ�ȫ�ڵ�н -- \'',
   pm_star_salary_min             decimal(8,2)    comment 'pm�Ǽ����нˮ -- \'',
   pm_star_salary_attend_full_default decimal(8,2)    comment '��������ȫ��нˮ��ϵͳĬ�ϣ� -- \'',
   pm_star_salary_attend_default  decimal(8,2)    comment '����нˮ��ϵͳĬ�ϣ� -- \'',
   pm_star_salary_real_default    decimal(8,2)    comment 'ʵ��нˮ��ϵͳĬ�ϣ� -- \'',
   pm_star_salary_attend_full     decimal(8,2)    comment '��������ȫ��нˮ -- \'',
   pm_star_salary_attend          decimal(8,2)    comment '����нˮ -- \'',
   pm_star_salary_punish          decimal(8,2)    comment 'нˮ�ۿ� -- \'',
   pm_star_salary_real            decimal(8,2)    comment 'ʵ��нˮ -- \'',
   status                         varchar(10)     comment '״̬ -- \'',
   status_datetime                datetime        comment '״̬����ʱ�� -- \'',
   status_employee_id             int(11)         comment '״̬������Ա��id -- \'',
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
/* Table: biz_pm_attend_salary_summary_bill                     */
/*==============================================================*/
create table biz_pm_attend_salary_summary_bill
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   store_id                       int(11)         comment '�ŵ�id -- \'',
   project_mode                   varchar(10)     comment '����ģʽ -- \'',
   pm_attend_salary_summary_bill_code varchar(100)    comment 'pm���ʵ����ܱ�� -- \'',
   salary_month                   varchar(20)     comment '�����·� -- \'',
   generated_datetime             datetime        comment '������������ʱ�� -- \'',
   salray_bill_count              int             comment '���ʵ����� -- \'',
   salary_total_amount            decimal(8,2)    comment '�����ܶ� -- \'',
   status                         varchar(10)     comment '״̬ -- \'',
   status_datetime                datetime        comment '״̬����ʱ�� -- \'',
   status_employee_id             int(11)         comment '״̬������Ա��id -- \'',
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
/* Table: biz_pm_attend_salary_summary_bill_rel                 */
/*==============================================================*/
create table biz_pm_attend_salary_summary_bill_rel
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   pm_attend_salary_summary_bill_id int(11)         comment '���ʻ��ܵ�id -- \'',
   pm_attend_salary_bill_id       int(11)         comment '���ʵ�id -- \'',
   primary key (id)
);

