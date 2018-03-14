/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/9/4 18:19:46                            */
/*==============================================================*/


drop table if exists biz_employee_agreement;

/*==============================================================*/
/* Table: biz_employee_agreement                                */
/*==============================================================*/
create table biz_employee_agreement
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   employee_type                  varchar(10)     comment 'Ա������ -- \'',
   employee_id                    int(11)         comment 'Ա��id -- \'',
   is_sign_employee_agreement     char(1)         comment '�Ƿ�ǩ��Э�� -- \'0.��1.�ǣ�',
   employee_agreement_sign_datetime datetime        comment 'ǩ��Э������ʱ�� -- \'',
   employee_agreement_read_datetime datetime        comment '����Ķ�Э������ʱ�� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

