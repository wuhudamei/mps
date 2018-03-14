/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/2 18:21:11                            */
/*==============================================================*/


/*===============================================================*/
/* Table: "biz_order_install_item_problem(modify)"               */
/*===============================================================*/
rename table biz_order_install_item_problem to biz_business_problem;

alter table biz_business_problem
add   business_type           varchar(10)     comment 'ҵ�����ͣ���ӣ� -- \'',
change order_install_item_id  business_only_mark_int int(11)         comment '������װ��id���޸�Ϊ��ҵ��Ψһ��ʶ���ͣ� -- \'';


/*=====================================================================*/
/* Table: biz_order_install_item_problem_log��modify��		       */
/*=====================================================================*/
rename table biz_order_install_item_problem_log to biz_business_problem_log;

alter table biz_business_problem_log
change   order_install_item_problem_id business_problem_id  int(11)        comment '������װ������id -- \'';

/*===========================================================================*/
/* Table: "biz_project_install_item_problem_type(biz_business_problem_type)" */
/*===========================================================================*/
rename table biz_project_install_item_problem_type to biz_business_problem_type;

alter table biz_business_problem_type
add   business_type                  varchar(10)     comment '����ҵ�����ͣ���ӣ� -- \'';

