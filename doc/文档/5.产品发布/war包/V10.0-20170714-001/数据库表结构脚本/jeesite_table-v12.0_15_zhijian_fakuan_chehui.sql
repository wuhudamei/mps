/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/7/6 17:19:53                            */
/*==============================================================*/


drop table if exists biz_qc_bill_check_item_log;

/*==============================================================*/
/* Table: biz_qc_bill_check_item_log	                       */
/*==============================================================*/
create table biz_qc_bill_check_item_log
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   qc_bill_check_item_id          int(11)         comment '�ʼ�����id -- \'',
   worker_punish_amount_old       decimal(10,2)   comment '���˴������_ԭ�� -- \'',
   worker_punish_score_old        int             comment '���˴�������_ԭ�� -- \'',
   pm_punish_score_old            int             comment '��Ŀ����������_ԭ�� -- \'',
   pm_punish_amount_old           decimal(10,2)   comment '��Ŀ���������_ԭ�� -- \'',
   qc_punish_score_old            int             comment '�ʼ�Ա��������_ԭ�� -- \'',
   qc_punish_amount_old           decimal(10,2)   comment '�ʼ�Ա�������_ԭ�� -- \'',
   modify_datetime                datetime        comment '�޸�����ʱ�� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

