/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/6/1 18:49:16                            */
/*==============================================================*/


/*==============================================================*/
/* Table: biz_business_problem_type（modify）                     */
/*==============================================================*/
alter table biz_business_problem_type
add   punish_score                   decimal(8,2)    comment '扣除分数（添加） -- \'',
add   punish_money                   decimal(8,2)    comment '罚款金额（添加） -- \'',
add   punish_remarks                 varchar(255)    comment '处罚说明（添加） -- \'';

/*==============================================================*/
/* Table: biz_business_problem（modify）                          */
/*==============================================================*/
alter table biz_business_problem
add   expect_solve_datetime          datetime        comment '期望完成日期（添加） -- \'',
add   incharge_name                  varchar(20)     comment '责任人（添加） -- \'',
add   punish_score                   decimal(8,2)    comment '扣除分数（添加） -- \'',
add   punish_money                   decimal(8,2)    comment '罚款金额（添加） -- \'',
add   punish_remarks                 varchar(255)    comment '处罚说明（添加） -- \'';

