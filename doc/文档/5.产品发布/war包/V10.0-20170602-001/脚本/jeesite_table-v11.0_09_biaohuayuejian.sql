/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/24 18:14:44                           */
/*==============================================================*/


drop table if exists biz_materials_standard_qc_check_node;

/*==============================================================*/
/* Table: biz_materials_standard_qc_check_node                  */
/*==============================================================*/
create table biz_materials_standard_qc_check_node
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   store_id                       int(11)         comment '�ŵ�id -- \'',
   project_mode                   varchar(10)     comment '����ģʽ -- \'',
   material_type                  varchar(10)     comment '�������� -- \'',
   qc_check_node_id               int(11)         comment 'Լ��ڵ�id -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

