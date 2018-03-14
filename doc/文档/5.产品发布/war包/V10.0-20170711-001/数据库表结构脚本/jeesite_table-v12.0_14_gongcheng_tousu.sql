/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/7/3 10:36:49                            */
/*==============================================================*/


drop table if exists biz_complaint_problem_item;

drop table if exists biz_complaint_problem_type;

drop table if exists biz_order_complaint;

drop table if exists biz_order_complaint_problem;

drop table if exists biz_order_complaint_problem_deal;

drop table if exists biz_order_complaint_problem_deal_log;

drop table if exists biz_order_complaint_problem_item;

/*==============================================================*/
/* Table: biz_complaint_problem_item                            */
/*==============================================================*/
create table biz_complaint_problem_item
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   store_id                       int(11)         comment '�ŵ�id -- \'',
   complaint_problem_type_id      int(11)         comment 'Ͷ���������id -- \'',
   item_name                      varchar(100)    comment '�������� -- \'',
   response_time                  decimal(10,2)   comment '��Ӧʱ�� -- \'',
   execute_time_limit             decimal(10,2)   comment 'ִ��ʱ�� -- \'',
   special_time_limit             decimal(10,2)   comment '����ʱ�� -- \'',
   deduct_score                   decimal(10,2)   comment '�۷� -- \'',
   punish_money                   decimal(10,2)   comment '���� -- \'',
   item_remarks                   varchar(255)    comment '���ע -- \'',
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
/* Table: biz_complaint_problem_type                            */
/*==============================================================*/
create table biz_complaint_problem_type
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   store_id                       int(11)         comment '�ŵ�id -- \'',
   type_name                      varchar(100)    comment '�������� -- \'',
   task_package_templat_id        int(11)         comment '�����ģ��id -- \'',
   deal_person_type               varchar(10)     comment '������Ա���� -- \'1.��Ŀ����2.��Ŀ����+���ˣ�3.�ʼ�Ա',
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
/* Table: biz_order_complaint                                   */
/*==============================================================*/
create table biz_order_complaint
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_id                       int(11)         comment '����id -- \'',
   complaint_source               varchar(10)     comment 'Ͷ����Դ -- \'1.�ͻ���2.��Ʋ���3.���ϲ���4.�͹ܲ���',
   data_input_channel             varchar(10)     comment '����¼������ -- \'1.�����ϱ���2.�ۺ�ϵͳ����',
   cus_service_problem_id         int(11)         comment '�ۺ�����id -- \'',
   complaint_person_name          varchar(20)     comment 'Ͷ�������� -- \'',
   complaint_person_phone         varchar(11)     comment 'Ͷ�����ֻ��� -- \'',
   status                         varchar(10)     comment '״̬ -- \'10.�½���20.�����У�30.�Ѵ���',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_complaint_problem                           */
/*==============================================================*/
create table biz_order_complaint_problem
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_complaint_id             int(11)         comment '����Ͷ��id -- \'',
   complaint_problem_type_id      int(11)         comment '�������id -- \'',
   task_package_templat_id        int(11)         comment '�����ģ��id -- \'',
   order_taskpackage_id           int(11)         comment '���������id -- \'',
   complaint_role_type            varchar(10)     comment '��Ͷ�߽�ɫ���� -- \'1.��Ŀ����2.��Ŀ����+���ˣ�3.�ʼ�Ա',
   complaint_problem_describe     varchar(255)    comment 'Ͷ���������� -- \'',
   status                         varchar(10)     comment '״̬ -- \'10.�½���20.�����У�30.�Ѵ���',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_complaint_problem_deal                      */
/*==============================================================*/
create table biz_order_complaint_problem_deal
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_complaint_problem_id     int(11)         comment '����Ͷ������id -- \'',
   deal_person_type               varchar(10)     comment '������Ա���� -- \'1.��Ŀ����2.�����鳤��3.���ˣ�4.�ʼ�Ա',
   deal_employee_id               int(11)         comment '������ԱԱ��id -- \'',
   deal_status                    varchar(10)     comment '����״̬ -- \'10.ȷ�Ͻ��գ�20.�Ѵ�',
   deal_status_datetime           datetime        comment '����״̬����ʱ�� -- \'',
   deal_describe                  varchar(255)    comment '�������� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_complaint_problem_deal_log                  */
/*==============================================================*/
create table biz_order_complaint_problem_deal_log
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_complaint_problem_deal_id int(11)         comment '����Ͷ������id -- \'',
   deal_person_type               varchar(10)     comment '������Ա���� -- \'1.��Ŀ����2.�����鳤��3.���ˣ�4.�ʼ�Ա',
   deal_employee_id               int(11)         comment '������ԱԱ��id -- \'',
   deal_status                    int(11)         comment '����״̬ -- \'10.ȷ�Ͻ��գ�20.�Ѵ�',
   deal_status_datetime           datetime        comment '����״̬����ʱ�� -- \'',
   deal_describe                  varchar(255)    comment '�������� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_complaint_problem_item                      */
/*==============================================================*/
create table biz_order_complaint_problem_item
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_complaint_problem_id     int(11)         comment '����Ͷ������id -- \'',
   complaint_problem_item_id      int(11)         comment 'Ͷ����������id -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);


/*==============================================================*/
/* Table: biz_cus_service_problem��modify��                       */
/*==============================================================*/
alter table biz_cus_service_problem
add   status                         varchar(10)     comment '״̬����ӣ� -- \'10.�ѽ��գ�20.�Ѵ���30.�Ѳ���',
add   status_datetime                datetime        comment '״̬����ʱ�䣨��ӣ� -- \'',
add   status_describe                varchar(255)    comment '״̬��������ӣ� -- \'',
add   status_operator_employee_id    int(11)         comment '״̬������Ա��id����ӣ� -- \'';


