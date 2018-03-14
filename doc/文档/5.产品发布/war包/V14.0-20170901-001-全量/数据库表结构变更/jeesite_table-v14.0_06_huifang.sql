/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/8/25 18:59:10                           */
/*==============================================================*/


drop table if exists biz_customer_return_visit_order_enable;

/*==============================================================*/
/* Table: biz_customer_return_visit_order_enable                */
/*==============================================================*/
create table biz_customer_return_visit_order_enable
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_id                       int(11)         comment '����id -- \'',
   customer_return_visit_id       int(11)         comment '�طýڵ�id -- \'',
   is_enabled                     char(1)         comment '�Ƿ����� -- \'0.��1.��',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

alter table biz_customer_return_visit_order_enable comment '�ͷ��طö���ͣ���ñ�';

