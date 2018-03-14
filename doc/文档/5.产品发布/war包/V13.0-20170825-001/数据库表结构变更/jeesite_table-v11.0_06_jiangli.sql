/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/18 16:36:11                           */
/*==============================================================*/


drop table if exists biz_business_reward_punish;

/*==============================================================*/
/* Table: biz_business_reward_punish                            */
/*==============================================================*/
create table biz_business_reward_punish
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   reward_punish_type             varchar(10)     comment '�������� -- \'1.������2.�ͷ�',
   employee_type                  varchar(10)     comment 'Ա������ -- \'',
   employee_Id                    int(11)         comment 'Ա��id -- \'',
   related_business_type          varchar(10)     comment '����ҵ������ -- \'',
   related_business_id_int        int(11)         comment '����ҵ��id���� -- \'',
   related_business_id_varchar    varchar(100)    comment '����ҵ��id�ַ��� -- \'',
   reward_punish_amount           decimal(8,2)    comment '���ͽ�� -- \'',
   reward_punish_remarks          varchar(255)    comment '����˵�� -- \'',
   reward_punish_datetime         datetime        comment '�������� -- \'',
   reward_punish_status           varchar(10)     comment '����״̬ -- \'1.�½���2.�ѹ�������',
   status_datetime                datetime        comment '״̬����ʱ�� -- \'',
   settle_stage                   varchar(10)     comment '��������׶� -- \'1.��װ��ɣ�2.�������',
   settle_type                    varchar(10)     comment '���㵥���� -- \'1.���˽��㣻2.��Ŀ�������',
   settle_id                      int(11)         comment '���㵥id -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

