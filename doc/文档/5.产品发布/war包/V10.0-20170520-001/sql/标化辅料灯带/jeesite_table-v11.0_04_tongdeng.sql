/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/11 18:05:21                           */
/*==============================================================*/


drop table if exists biz_materials_standard_number_square;


/*==============================================================*/
/* Table: biz_materials_standard_number_square                  */
/*==============================================================*/
create table biz_materials_standard_number_square
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   materials_standard_id          int(11)         comment '�껯����id -- \'',
   square_min                     decimal(8,2)    comment '������ޣ��������� -- \'',
   square_max                     decimal(8,2)    comment '������� -- \'',
   number_rule_code               varchar(10)     comment '����������� -- \'',
   number_rule_describe           varchar(255)    comment '������������ -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_materials_standard_receive_bill��modify3��          */
/*==============================================================*/
alter table biz_materials_standard_receive_bill
add   receive_bill_type              varchar(10)     comment '��ȡ������ -- \'1.�껯���ϣ�2.Ͳ�Ƶƴ�';

/*==============================================================*/
/* Table: biz_materials_standard��modify2��                       */
/*==============================================================*/
alter table biz_materials_standard
add   materials_large_type           varchar(10)     comment '���ϴ��ࣨ��ӣ� -- \'1.�껯���ϣ�2.Ͳ�Ƶƴ�',
add   is_limit_max_number            char(1)         comment '�Ƿ�����������������ӣ� -- \'0.��1.��';

/*==============================================================*/
/* Table: biz_order_materials_standard��modify��                  */
/*==============================================================*/
alter table biz_order_materials_standard
add   apply_number_suggest           decimal(10,2)   comment '����������������ӣ� -- \'';



