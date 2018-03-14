/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/18 16:37:05                           */
/*==============================================================*/


drop table if exists biz_eval_activity_stage;

/*==============================================================*/
/* Table: biz_eval_activity_stage                               */
/*==============================================================*/
create table biz_eval_activity_stage
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   eval_activity_id               int(11)         comment '���ۻid -- \'',
   related_stage                  varchar(10)     comment '�����׶����� -- \'��װ������ɣ������������',
   related_qc_check_node_id       int(11)         comment '����Լ��ڵ�id -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_eval_taskpack_role_score��modify��                  */
/*==============================================================*/
alter table biz_eval_taskpack_role_score
add   eval_status                    varchar(10)     comment '����״̬�������� -- \'0.δ���ۣ�1.�������';

