/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/10 18:33:41                           */
/*==============================================================*/


drop table if exists biz_order_taskpackage_payment_frozen_log;

drop table if exists biz_order_taskpackage_settlement_cancle_log;

drop table if exists biz_order_taskpackage_settlement_update_log;


/*==============================================================*/
/* Table: biz_order_taskpackage_payment_frozen_log              */
/*==============================================================*/
create table biz_order_taskpackage_payment_frozen_log
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   biz_order_taskpackage_payment_id int(11)         comment '������������㵥id -- \'',
   frozen_type                    varchar(255)    comment '����ⶳ���� -- \'1.���᣻2.�ⶳ',
   frozen_remarks                 varchar(255)    comment '����ⶳ˵�� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_taskpackage_settlement_cancle_log           */
/*==============================================================*/
create table biz_order_taskpackage_settlement_cancle_log
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   operate_type                   varchar(10)     comment '�������� -- \'1.����',
   biz_order_taskpackage_settlemen_id int(11)         comment '������������㵥id -- \'',
   operate_remarks                varchar(255)    comment '����˵�� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);


/*==============================================================*/
/* Table: biz_order_taskpackage_settlement_update_log           */
/*==============================================================*/
create table biz_order_taskpackage_settlement_update_log
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_taskpackage_settlement_id int(11)         comment '������������㵥id -- \'',
   amount_old                     decimal(8,2)    comment '���㵥ԭ��� -- \'',
   amount_new                     decimal(8,2)    comment '���㵥�޸ĺ��� -- \'',
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
/* Table: biz_order_taskpackage_procedure��modify��               */
/*==============================================================*/
alter table biz_order_taskpackage_procedure
add   modify_number_reason           varchar(255)    comment '��̨���Ĺ�����ԭ����ӣ� -- \'';

/*==============================================================*/
/* Table: biz_order_taskpackage_settlement��modify6��             */
/*==============================================================*/
alter table biz_order_taskpackage_settlement
add   company_deduct_amount          decimal(8,2)    comment '��˾�ۿ����ӣ� -- \'',
add   company_deduct_reason          varchar(255)    comment '��˾�ۿ�ԭ����ӣ� -- \'';

