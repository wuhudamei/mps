/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/5 14:02:31                            */
/*==============================================================*/


drop table if exists biz_order_report_instore_log;

drop table if exists biz_order_report_send_log;

drop table if exists biz_order_report_send_rule;

drop table if exists biz_order_report_send_rule_service;

drop table if exists biz_order_report_sign_log;

drop table if exists biz_order_report_sign_log_related_order;

/*==============================================================*/
/* Table: biz_order_report_instore_log                          */
/*==============================================================*/
create table biz_order_report_instore_log
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   operate_source                 varchar(10)     comment '������Դ -- \'1.�ӿڣ�2.�˹�',
   order_report_id                int(11)         comment '����id -- \'',
   instore_type                   varchar(10)     comment '�������� -- \'1.����δǩ����2.������ǩ��',
   instore_datetime               datetime        comment '��������ʱ�� -- \'',
   instore_remarks                varchar(255)    comment '���걸ע -- \'',
   sign_datetime                  datetime        comment 'ǩ������ʱ�� -- \'',
   sign_remarks                   varchar(255)    comment 'ǩ����ע -- \'',
   operator_name                  varchar(20)     comment '���������� -- \'',
   operator_phone                 varchar(11)     comment '�������ֻ��� -- \'',
   operate_datetime               datetime        comment '��������ʱ�� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_report_send_log                             */
/*==============================================================*/
create table biz_order_report_send_log
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   send_type                      varchar(10)     comment '�ɵ����� -- \'1.ϵͳ�Զ����ɿͷ���2.�˹����ɿͷ���3.�˹�ת�ɿͷ�',
   order_report_id                int(11)         comment '����id -- \'',
   service_employee_id            int(11)         comment '�ͷ�Ա��id -- \'',
   service_name                   varchar(20)     comment '�ͷ����� -- \'',
   service_phone                  varchar(11)     comment '�ͷ��ֻ��� -- \'',
   operator_employee_id           int(11)         comment '������Ա��id -- \'',
   operate_datetime               datetime        comment '��������ʱ�� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_report_send_rule                            */
/*==============================================================*/
create table biz_order_report_send_rule
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   start_datetime                 datetime        comment '��ʼ����ʱ�� -- \'',
   end_datetime                   datetime        comment '��ֹ����ʱ�� -- \'',
   status                         varchar(10)     comment '״̬ -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_report_send_rule_service                    */
/*==============================================================*/
create table biz_order_report_send_rule_service
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   send_rule_id                   int(11)         comment '�����ɵ�����id -- \'',
   service_index                  int             comment '�ͷ���� -- \'',
   service_employee_id            int(11)         comment '�ͷ�Ա��id -- \'',
   send_count                     int             comment '���ɴ��� -- \'',
   is_send_end                    char(1)         comment '�Ƿ�ѭ������ĩβ -- \'0.��1.��',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_report_sign_log                             */
/*==============================================================*/
create table biz_order_report_sign_log
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   sign_type                      varchar(10)     comment 'ǩ������ -- \'1.ǩ����ͬ��2.��ǩ��ͬ��3.ǩ������',
   operate_source                 varchar(10)     comment '������Դ -- \'1.�ӿڣ�2.�˹�',
   operator_employee_id           int(11)         comment '������Ա��id -- \'',
   operator_name                  varchar(20)     comment '���������� -- \'',
   operator_phone                 varchar(11)     comment '�������ֻ��� -- \'',
   operate_datetime               datetime        comment '��������ʱ�� -- \'',
   sign_datetime                  datetime        comment 'ǩ������ʱ�� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_report_sign_log_related_order               */
/*==============================================================*/
create table biz_order_report_sign_log_related_order
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   sign_log_id                    int(11)         comment '����ǩ����¼id -- \'',
   order_id                       int(11)         comment '��������id -- \'',
   order_number                   varchar(100)    comment '������������ -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_report��modify��                              */
/*==============================================================*/
alter table biz_order_report 
add   is_asked                       char(1)         comment '�Ƿ���ѯ������ӣ� -- \'1.�ǣ�0.��',
add   service_employee_id            int(11)         comment '�ͷ�Ա��id����ӣ� -- \'',
add   service_name                   varchar(20)     comment '�ͷ���������ӣ� -- \'',
add   service_phone                  varchar(11)      comment '�ͷ��ֻ��ţ���ӣ� -- \'';


/*==============================================================*/
/* Table: biz_employee��modify3��                                 */
/*==============================================================*/
alter table biz_employee
add   employee_id_mapping_order_change_sys varchar(60)     comment 'Ա��id_������תϵͳӳ�� -- \'';


