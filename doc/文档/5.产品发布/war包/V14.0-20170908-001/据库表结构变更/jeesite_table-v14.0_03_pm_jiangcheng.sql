/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/8/23 11:08:52                           */
/*==============================================================*/


drop table if exists biz_assess_reward_punish;

drop table if exists biz_assess_rule;

drop table if exists biz_assess_rule_type;


/*==============================================================*/
/* Table: biz_assess_reward_punish                              */
/*==============================================================*/
create table biz_assess_reward_punish
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   related_business_type          varchar(10)     comment '����ҵ������ -- \'',
   related_business_id_int        int(11)         comment '����ҵ��id���� -- \'',
   related_business_id_varchar    varchar(100)    comment '����ҵ��id�ַ��� -- \'',
   assess_rule_id                 int(11)         comment '��������id -- \'',
   is_reward_or_punish            varchar(10)     comment '�ǽ���� -- \'1.������2.�ͷ�',
   reward_punish_target_type      varchar(10)     comment '���Ͷ������� -- \'10.������20.Ա��',
   reward_punish_target_employee_type varchar(10)     comment '���Ͷ���Ա������ -- \'',
   reward_punish_target_employee_id int(11)         comment '���Ͷ���Ա��id -- \'',
   reward_punish_amount           decimal(8,2)    comment '���ͽ�� -- \'',
   reward_punish_score            decimal(8,2)    comment '���ͷ��� -- \'',
   reward_punish_remarks          varchar(255)    comment '����˵�� -- \'',
   reward_punish_datetime         datetime        comment '��������ʱ�� -- \'',
   reward_punish_status           varchar(10)     comment '����״̬ -- \'1.�½���2.�ѹ�������',
   status_datetime                datetime        comment '״̬����ʱ�� -- \'',
   status_operator                int(11)         comment '״̬������Ա��id -- \'',
   status_describe                varchar(1000)   comment '״̬˵�� -- \'',
   settle_stage                   varchar(10)     comment '��������׶� -- \'1.��װ��ɣ�2.�������',
   settle_type                    varchar(10)     comment '���㵥���� -- \'1.���˽��㣻2.��Ŀ������㣻',
   settle_id                      int(11)         comment '���㵥id -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_assess_rule                                       */
/*==============================================================*/
create table biz_assess_rule
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   store_id                       int(11)         comment '�ŵ�id -- \'',
   project_mode                   varchar(10)     comment '����ģʽ -- \'',
   biz_assess_rule_type_id        int(11)         comment '������������id -- \'',
   biz_assess_rule_describe       varchar(2000)   comment '��������˵�� -- \'',
   reward_punish_amount           decimal(10,2)   comment '���ͽ�� -- \'',
   reward_punish_score            int             comment '���ͷ��� -- \'',
   reward_punish_target_employee_type varchar(10)     comment '���Ͷ���Ա������ -- \'',
   is_enabled                     char(1)         comment '�Ƿ����� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_assess_rule_type                                  */
/*==============================================================*/
create table biz_assess_rule_type
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   store_id                       int(11)         comment '�ŵ�id -- \'',
   project_mode                   varchar(10)     comment '����ģʽ -- \'',
   type_name                      varchar(100)    comment '�������� -- \'',
   is_reward_or_punish            varchar(10)     comment '�ǽ���� -- \'',
   reward_punish_target_type      varchar(10)     comment '���Ͷ������� -- \'10.������20.Ա��',
   is_enabled                     char(1)         comment '�Ƿ����� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_pm_settle_bill��modify5��                           */
/*==============================================================*/
alter table biz_pm_settle_bill
add   midway_reward_amount           decimal(8,2)    comment '���ڽ�������ӣ� -- \'',
add   midway_punish_amount           decimal(8,2)    comment '���ڿۿ����ӣ� -- \'',
add   complete_reward_amount         decimal(8,2)    comment '������������ӣ� -- \'',
add   complete_punish_amount         decimal(8,2)    comment '�����ۿ����ӣ� -- \'';

/*==============================================================*/
/* Table: biz_pm_settle_summary_bill��modify5��                   */
/*==============================================================*/
alter table biz_pm_settle_summary_bill
add   midway_reward_amount           decimal(8,2)    comment '���ڽ�������ӣ� -- \'',
add   midway_punish_amount           decimal(8,2)    comment '���ڿۿ����ӣ� -- \'',
add   complete_reward_amount         decimal(8,2)    comment '������������ӣ� -- \'',
add   complete_punish_amount         decimal(8,2)    comment '�����ۿ����ӣ� -- \'';

