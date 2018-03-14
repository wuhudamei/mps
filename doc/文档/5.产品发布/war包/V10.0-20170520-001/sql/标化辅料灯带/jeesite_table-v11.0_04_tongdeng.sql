/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/11 18:05:21                           */
/*==============================================================*/


drop table if exists biz_materials_standard_number_square;


/*==============================================================*/
/* Table: biz_materials_standard_number_square                  */
/*==============================================================*/
create table biz_materials_standard_number_square
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   materials_standard_id          int(11)         comment '标化辅料id -- \'',
   square_min                     decimal(8,2)    comment '面积下限（不包含） -- \'',
   square_max                     decimal(8,2)    comment '面积上限 -- \'',
   number_rule_code               varchar(10)     comment '数量规则编码 -- \'',
   number_rule_describe           varchar(255)    comment '数量规则描述 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_materials_standard_receive_bill（modify3）          */
/*==============================================================*/
alter table biz_materials_standard_receive_bill
add   receive_bill_type              varchar(10)     comment '领取单类型 -- \'1.标化辅料；2.筒灯灯带';

/*==============================================================*/
/* Table: biz_materials_standard（modify2）                       */
/*==============================================================*/
alter table biz_materials_standard
add   materials_large_type           varchar(10)     comment '材料大类（添加） -- \'1.标化辅料；2.筒灯灯带',
add   is_limit_max_number            char(1)         comment '是否限制申请数量（添加） -- \'0.否；1.是';

/*==============================================================*/
/* Table: biz_order_materials_standard（modify）                  */
/*==============================================================*/
alter table biz_order_materials_standard
add   apply_number_suggest           decimal(10,2)   comment '建议申请数量（添加） -- \'';



