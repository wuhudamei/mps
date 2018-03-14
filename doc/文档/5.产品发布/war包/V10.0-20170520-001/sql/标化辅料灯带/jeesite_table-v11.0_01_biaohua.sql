/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/10 14:00:25                           */
/*==============================================================*/


drop table if exists biz_order_materials_standard;


/*==============================================================*/
/* Table: biz_materials_standard_receive_bill��modify2��          */
/*==============================================================*/
alter table biz_materials_standard_receive_bill
add   is_viewed                      char(1)         comment '�Ƿ�鿴������ӣ� -- \'',
add   view_datetime                  datetime        comment '�鿴����ʱ�䣨��ӣ� -- \'',
add   abandon_reason                 varchar(255)    comment '�������� -- \'';

/*==============================================================*/
/* Table: biz_materials_standard_receive_detail��modify��         */
/*==============================================================*/
alter table biz_materials_standard_receive_detail
add   max_receive_number_snap        decimal(10,2)   comment '��ȡ�������޿��գ���ӣ� -- \'',
add   apply_number_total_snap        decimal(10,2)   comment '�������������գ���ӣ� -- \'',
add   receive_number_total_snap      decimal(10,2)   comment '����ȡ�������գ���ӣ� -- \'';

/*==============================================================*/
/* Table: biz_materials_standard��modify��                        */
/*==============================================================*/
alter table biz_materials_standard
add   max_receive_number             decimal(10,2)   comment '��ȡ�������ޣ���ӣ� -- \'';

/*==============================================================*/
/* Table: biz_order_materials_standard                          */
/*==============================================================*/
create table biz_order_materials_standard
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_id                       int(11)         comment '����id -- \'',
   materials_standard_id          int(11)         comment '�껯����id -- \'',
   materials_type                 varchar(100)    comment '������� -- \'',
   materials_name                 varchar(100)    comment '�������� -- \'',
   materials_unit                 varchar(10)     comment '���ϵ�λ -- \'',
   materials_price                decimal(10,2)   comment '���ϵ��� -- \'',
   materials_amount               decimal(10,2)   comment '���Ͻ�� -- \'',
   apply_number_total             decimal(10,2)   comment '���������� -- \'',
   receive_number_total           decimal(10,2)   comment '����ȡ���� -- \'',
   max_receive_number             decimal(10,2)   comment '��ȡ�������� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_order��modify6��                                    */
/*==============================================================*/
alter table biz_order
add   is_basicwork_completed         char(1)         comment '�Ƿ��װ������ɣ���ӣ� -- \'0.��1.��';

