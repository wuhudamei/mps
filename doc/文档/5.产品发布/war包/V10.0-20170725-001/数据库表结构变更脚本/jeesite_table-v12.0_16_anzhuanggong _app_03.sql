/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/7/12 10:13:55                           */
/*==============================================================*/

drop table if exists biz_project_install_item_supplier;

drop table if exists biz_supplier_employee_group;

drop table if exists biz_supplier_install_bill;

drop table if exists biz_supplier_install_construct_bill;

/*==============================================================*/
/* Table: biz_project_install_item_supplier                     */
/*==============================================================*/
create table biz_project_install_item_supplier
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   project_install_item_id        int(11)         comment '���̰�װ��id -- \'',
   supplier_id                    int(11)         comment '��Ӧ��id -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_supplier_employee_group                           */
/*==============================================================*/
create table biz_supplier_employee_group
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   supplier_id                    int(11)         comment '��Ӧ��id -- \'',
   employee_group_id              int(11)         comment '������id -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_supplier_install_bill                             */
/*==============================================================*/
create table biz_supplier_install_bill
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   install_bill_code              varchar(100)    comment '��װ���� -- \'',
   order_install_plan_id          int(11)         comment '������װ�ƻ�id -- \'',
   supplier_id                    int(11)         comment '��Ӧ��id -- \'',
   supplier_confirm_into_date     datetime        comment '��Ӧ��ȷ�Ͻ�������ʱ�� -- \'',
   supplier_confirm_complete_date datetime        comment '��Ӧ��ȷ���깤����ʱ�� -- \'',
   status                         varchar(10)     comment '״̬ -- \'',
   status_datetime                datetime        comment '״̬����ʱ�� -- \'',
   real_into_date                 datetime        comment 'ʵ�ʽ�������ʱ�� -- \'',
   real_complete_date             datetime        comment 'ʵ���깤����ʱ�� -- \'',
   real_accept_date               datetime        comment 'ȷ����������ʱ�� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_supplier_install_construct_bill                   */
/*==============================================================*/
create table biz_supplier_install_construct_bill
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   construct_bill_code            varchar(100)    comment 'ʩ������ -- \'',
   supplier_install_bill_id       int(11)         comment '��Ӧ�̰�װ��id -- \'',
   employee_group_id              int(11)         comment '������id -- \'',
   status                         varchar(10)     comment '״̬ -- \'',
   status_datetime                datetime        comment '״̬����ʱ�� -- \'',
   real_into_date                 datetime        comment 'ʵ�ʽ�������ʱ�� -- \'',
   real_complete_date             datetime        comment 'ʵ���깤����ʱ�� -- \'',
   real_accept_date               datetime        comment 'ȷ����������ʱ�� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_supplier��modify��                                  */
/*==============================================================*/
alter table biz_supplier
add   install_item_type              varchar(10)     comment '��װ����ࣨ��ӣ� -- \'';
