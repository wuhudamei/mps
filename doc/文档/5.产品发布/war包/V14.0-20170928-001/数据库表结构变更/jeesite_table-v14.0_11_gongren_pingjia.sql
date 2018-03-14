/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/9/4 18:20:26                            */
/*==============================================================*/


drop table if exists biz_employee_star_cnfg;

drop table if exists biz_employee_star_log;


/*==============================================================*/
/* Table: biz_employee_star_cnfg                                */
/*==============================================================*/
create table biz_employee_star_cnfg
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   store_id                       int(11)         comment '�ŵ�id -- \'',
   peoject_mode                   varchar(10)     comment '����ģʽ -- \'',
   employee_type                  varchar(10)     comment 'Ա������ -- \'',
   star                           int             comment '�Ǽ� -- \'',
   max_score                      int             comment '��߷��� -- \'',
   min_socre                      int             comment '��ͷ��� -- \'',
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
/* Table: biz_employee_star_log                                 */
/*==============================================================*/
create table biz_employee_star_log
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   employee_id                    int(11)         comment 'Ա��id -- \'',
   star_change_source             varchar(10)     comment '�Ǽ������Դ -- \'10.��������۵÷֣�20.������Ա�޸�',
   related_business_id            int(11)         comment '����ҵ��id -- \'',
   star_score_before              decimal(8,2)    comment '���ǰ�Ǽ��÷� -- \'',
   star_score_after               decimal(8,2)    comment '������Ǽ��÷� -- \'',
   star_before                    int             comment '���ǰ�Ǽ� -- \'',
   star_after                     int             comment '������Ǽ� -- \'',
   star_change_reason_type        varchar(10)     comment '���ԭ����� -- \'',
   star_change_describe           varchar(1000)   comment '���˵�� -- \'',
   star_change_operator_employee_id int(11)         comment '��������� -- \'',
   star_change_datetime           datetime        comment '�������ʱ�� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_employee��modify5��                                 */
/*==============================================================*/
alter table biz_employee
add   star_score                     decimal(8,2)    comment '�Ǽ��÷֣���ӣ� -- \'';

