/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/8/1 9:58:43                             */
/*==============================================================*/


drop table if exists biz_order_delay_bill;

drop table if exists biz_order_delay_category;

/*==============================================================*/
/* Table: biz_order_delay_bill                                  */
/*==============================================================*/
create table biz_order_delay_bill
(
   id                             int(11)         not null auto_increment comment '����ID -- \'',
   order_id                       int(11)         comment '����ID -- \'',
   delay_bill_stage_status        varchar(100)    comment '���ڽ׶� -- \'',
   delay_bill_category_id         int(11)         comment '������� -- \'',
   delay_begin_datetime           datetime        comment '���ڿ�ʼ����ʱ�� -- \'',
   delay_end_datetime             datetime        comment '���ڽ�������ʱ�� -- \'',
   delay_days                     int             comment '�������� -- \'',
   deferred_instruction           varchar(1000)   comment '����˵�� -- \'',
   deferred_application_datetime  datetime        comment '������������ʱ�� -- \'',
   status                         varchar(10)      comment '״̬ -- \'',
   status_datetime                datetime        comment '״̬����ʱ�� -- \'',
   create_date                    datetime        comment '����ʱ�� -- \'',
   create_by                      varchar(64)     comment '������ -- \'',
   update_date                    datetime        comment '����ʱ�� -- \'',
   update_by                      varchar(64)     comment '������ -- \'',
   del_flag                       char            comment 'ɾ����ʶ -- \'',
   remarks                        varchar(1000)   comment '��ע -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order_delay_category                              */
/*==============================================================*/
create table biz_order_delay_category
(
   id                             int(11)         not null auto_increment comment '����ID -- \'',
   store_id                       int(11)         comment '�ŵ� -- \'',
   parent_id                      int(11)         comment '��ID -- \'',
   delay_type_level               int             comment '�������͵ȼ� -- \'',
   delay_type_name                varchar(100)    comment '������������ -- \'',
   create_date                    datetime        comment '����ʱ�� -- \'',
   create_by                      varchar(64)     comment '������ -- \'',
   update_date                    datetime        comment '����ʱ�� -- \'',
   update_by                      varchar(64)     comment '������ -- \'',
   del_flag                       char            comment 'ɾ����ʶ -- \'',
   remarks                        varchar(1000)   comment '��ע -- \'',
   primary key (id)
);

alter table biz_order_delay_category comment '����������ԭ��';

