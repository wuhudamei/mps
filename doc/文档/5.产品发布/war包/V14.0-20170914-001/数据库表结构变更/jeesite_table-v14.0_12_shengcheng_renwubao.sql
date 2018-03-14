/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/9/8 11:11:02                            */
/*==============================================================*/


drop table if exists biz_task_package_templat_budget_amount_limit;

/*==============================================================*/
/* Table: biz_task_package_templat_budget_amount_limit          */
/*==============================================================*/
create table biz_task_package_templat_budget_amount_limit
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   taskpackage_templat_id         int(11)         comment '�����ģ��id -- \'',
   budget_square_max              decimal(8,2)    comment '���Ԥ����� -- \'',
   budget_square_min              decimal(8,2)    comment '��СԤ����� -- \'',
   labor_auxiliary_materials_budget_amount_max decimal(10,2)   comment '���Ϸ�Ԥ�������� -- \'',
   is_enabled                     char(1)         comment '�Ƿ����� -- \'0.��1.��',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

