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
   eval_activity_id               int(11)         comment '活动id -- \'',
   eval_target_type               varchar(10)     comment '评价对象类型 -- \'1.工人；',
   eval_role_type                 varchar(10)     comment '评价角色类型 -- \'1.项目经理；2.质检；3.客户',
   eval_cycle_hours               decimal(6,2)    comment '评价周期小时 -- \'',
   is_enabled                     char(1)         comment '是否启用 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_eval_score_role（modify）                           */
/*==============================================================*/
alter table biz_eval_score_role
add   eval_cycle_hours               decimal(6,2)    comment '评价周期小时 -- \'';

