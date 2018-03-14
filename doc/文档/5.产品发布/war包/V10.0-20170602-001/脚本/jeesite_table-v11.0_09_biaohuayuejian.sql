/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/24 18:14:44                           */
/*==============================================================*/


drop table if exists biz_materials_standard_qc_check_node;

/*==============================================================*/
/* Table: biz_materials_standard_qc_check_node                  */
/*==============================================================*/
create table biz_materials_standard_qc_check_node
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   store_id                       int(11)         comment '门店id -- \'',
   project_mode                   varchar(10)     comment '工程模式 -- \'',
   material_type                  varchar(10)     comment '材料类型 -- \'',
   qc_check_node_id               int(11)         comment '约检节点id -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

