/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/2 18:21:11                            */
/*==============================================================*/


drop table if exists biz_business_urge;

/*==============================================================*/
/* Table: biz_business_urge                                     */
/*==============================================================*/
create table biz_business_urge
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   business_only_mark_int         int(11)         comment 'ҵ��Ψһ��ʶ���� -- \'',
   business_only_mark_varchar     varchar(100)    comment 'ҵ��Ψһ��ʶ�ַ��� -- \'',
   busines_type                   varchar(10)     comment 'ҵ������ -- \'1.���İ�װ���룻2.ǽ��ש�ɹ�',
   operate_type                   varchar(10)     comment '�������� -- \'1.�ߴ٣�2.�ظ�',
   operate_content                varchar(255)    comment '�������� -- \'',
   operator_employee_id           int(11)         comment '������Ա��id -- \'',
   operator_type                  varchar(10)     comment '���������� -- \'1.��Ŀ����2.���ϲ�',
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
/* Table: biz_order_install_plan                                */
/*==============================================================*/
alter table biz_order_install_plan
add  supplier_confirm_remarks       varchar(255)    comment '��Ӧ��ȷ��˵������ӣ� -- \'';

