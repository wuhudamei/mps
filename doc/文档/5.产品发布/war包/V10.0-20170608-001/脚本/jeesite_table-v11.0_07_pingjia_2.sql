/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/6/2 16:39:04                            */
/*==============================================================*/


rename table biz_eval_taskpack_role_index_score to biz_eval_score_role_index;

rename table biz_eval_taskpack_role_score to biz_eval_score_role;

rename table biz_eval_taskpack_score to biz_eval_score;


/*========================================================================*/
/* Table: "biz_eval_taskpack_role_index_score(biz_eval_score_role_index)" */
/*========================================================================*/
alter table biz_eval_score_role_index
change eval_taskpack_role_score_id  eval_score_role_id             int(11)         comment '���۽�ɫ�÷�id -- \'change   eval_taskpack_role_score_id';


/*==============================================================*/
/* Table: biz_eval_taskpack_role_score����Ϊbiz_eval_score_role��   */
/*==============================================================*/
alter table biz_eval_score_role
change eval_taskpack_score_id  eval_score_id                  int(11)         comment '���۵÷�id -- \'change  eval_taskpack_score_id';


/*==============================================================*/
/* Table: biz_eval_taskpack_score����Ϊbiz_eval_score��             */
/*==============================================================*/
alter table biz_eval_score
add      eval_type                      varchar(10)     comment '�������ͣ���ӣ� -- \'1.���۹��ˣ�2.������Ŀ����',
change order_taskpackage_id  related_business_id            int(11)         comment '����ҵ��id -- \'change order_taskpackage_id';

