/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/6/1 18:49:16                            */
/*==============================================================*/


/*==============================================================*/
/* Table: biz_business_problem_type��modify��                     */
/*==============================================================*/
alter table biz_business_problem_type
add   punish_score                   decimal(8,2)    comment '�۳���������ӣ� -- \'',
add   punish_money                   decimal(8,2)    comment '�������ӣ� -- \'',
add   punish_remarks                 varchar(255)    comment '����˵������ӣ� -- \'';

/*==============================================================*/
/* Table: biz_business_problem��modify��                          */
/*==============================================================*/
alter table biz_business_problem
add   expect_solve_datetime          datetime        comment '����������ڣ���ӣ� -- \'',
add   incharge_name                  varchar(20)     comment '�����ˣ���ӣ� -- \'',
add   punish_score                   decimal(8,2)    comment '�۳���������ӣ� -- \'',
add   punish_money                   decimal(8,2)    comment '�������ӣ� -- \'',
add   punish_remarks                 varchar(255)    comment '����˵������ӣ� -- \'';

