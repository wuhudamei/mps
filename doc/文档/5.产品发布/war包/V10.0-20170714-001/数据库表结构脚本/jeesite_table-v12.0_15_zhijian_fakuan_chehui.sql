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
   qc_bill_check_item_id          int(11)         comment '质检检查项id -- \'',
   worker_punish_amount_old       decimal(10,2)   comment '工人处罚金额_原来 -- \'',
   worker_punish_score_old        int             comment '工人处罚分数_原来 -- \'',
   pm_punish_score_old            int             comment '项目经理处罚分数_原来 -- \'',
   pm_punish_amount_old           decimal(10,2)   comment '项目经理处罚金额_原来 -- \'',
   qc_punish_score_old            int             comment '质检员处罚分数_原来 -- \'',
   qc_punish_amount_old           decimal(10,2)   comment '质检员处罚金额_原来 -- \'',
   modify_datetime                datetime        comment '修改日期时间 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

