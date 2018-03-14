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
   eval_activity_id               int(11)         comment '评价活动id -- \'',
   related_stage                  varchar(10)     comment '关联阶段类型 -- \'基装验收完成；竣工验收完成',
   related_qc_check_node_id       int(11)         comment '关联约检节点id -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_eval_taskpack_role_score（modify）                  */
/*==============================================================*/
alter table biz_eval_taskpack_role_score
add   eval_status                    varchar(10)     comment '评价状态（新增） -- \'0.未评价；1.评价完成';

