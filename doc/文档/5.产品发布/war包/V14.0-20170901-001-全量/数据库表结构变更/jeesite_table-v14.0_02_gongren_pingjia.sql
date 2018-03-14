/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/8/23 10:22:53                           */
/*==============================================================*/


drop table if exists biz_eval_activity_role_cycle;

/*==============================================================*/
/* Table: biz_eval_activity_role_cycle                          */
/*==============================================================*/
create table biz_eval_activity_role_cycle
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   eval_activity_id               int(11)         comment '�id -- \'',
   eval_target_type               varchar(10)     comment '���۶������� -- \'1.���ˣ�',
   eval_role_type                 varchar(10)     comment '���۽�ɫ���� -- \'1.��Ŀ����2.�ʼ죻3.�ͻ�',
   eval_cycle_hours               decimal(6,2)    comment '��������Сʱ -- \'',
   is_enabled                     char(1)         comment '�Ƿ����� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_eval_score_role��modify��                           */
/*==============================================================*/
alter table biz_eval_score_role
add   eval_cycle_hours               decimal(6,2)    comment '��������Сʱ -- \'';

