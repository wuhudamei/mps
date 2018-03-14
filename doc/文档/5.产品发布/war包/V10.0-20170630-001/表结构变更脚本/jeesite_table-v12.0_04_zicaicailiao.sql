/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/6/9 17:58:13                            */
/*==============================================================*/


drop table if exists biz_material_selfbuy;

drop table if exists biz_material_selfbuy_reimburse;

/*==============================================================*/
/* Table: biz_material_selfbuy                                  */
/*==============================================================*/
create table biz_material_selfbuy
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   store_id                       int(11)         comment '�ŵ�id -- \'',
   project_mode                   varchar(10)     comment '����ģʽ -- \'',
   material_name                  varchar(100)    comment '�������� -- \'',
   material_code                  varchar(100)    comment '���ϱ��� -- \'',
   settle_rate                    decimal(5,2)    comment '��Ŀ���������� -- \'',
   settle_stage                   varchar(10)     comment '��������׶� -- \'1.���ڽ��㣻2.��������',
   is_enabled                     char(1)         comment '�Ƿ����� -- \'0.��1.�ǣ�',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_material_selfbuy_reimburse                        */
/*==============================================================*/
create table biz_material_selfbuy_reimburse
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   reimburse_type                 varchar(10)     comment '�������� -- \'1.�����걨��2.�����걨',
   related_reimburse_id           int(11)         comment '��������id -- \'',
   order_id                       int(11)         comment '����id -- \'',
   material_selfbuy_id            int(11)         comment '�����Բ�id -- \'',
   customer_pay_amount            decimal(8,2)    comment '�ͻ����ѽ�� -- \'',
   settle_rate                    decimal(5,2)    comment '������� -- \'',
   settle_stage                   varchar(10)     comment '��������׶� -- \'1.���ڽ��㣻2.��������',
   settle_amount                  decimal(8,2)    comment 'ʵ�ʽ����� -- \'',
   reimburse_remarks              varchar(255)    comment '����˵�� -- \'',
   reimburse_status               varchar(10)     comment '����״̬ -- \'',
   reimburse_status_datetime      datetime        comment '����״̬����ʱ�� -- \'',
   reimburse_status_remarks       varchar(255)    comment '����״̬˵�� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_pm_settle_bill��modify3��                           */
/*==============================================================*/
alter table biz_pm_settle_bill
add   material_selfbuy_reimburse_amount decimal(10,2)   comment '�Բɲ��ϱ�������ӣ� -- \'';


/*==============================================================*/
/* Table: biz_pm_settle_summary_bill��modify3��                   */
/*==============================================================*/
alter table biz_pm_settle_summary_bill
add   material_selfbuy_reimburse_amount decimal(10,2)   comment '�Բɲ��ϱ�������ӣ� -- \'';

