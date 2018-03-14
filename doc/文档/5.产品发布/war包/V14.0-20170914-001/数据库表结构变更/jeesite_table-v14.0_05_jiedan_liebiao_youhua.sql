/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/8/25 11:03:40                           */
/*==============================================================*/


/*==============================================================*/
/* Table: biz_materials_choice_bill（modify）                     */
/*==============================================================*/
alter table biz_materials_choice_bill
add   is_dealed_main_materials       char(1)         comment '是否已处理主材（添加） -- \'0.否；1.是',
add   is_dealed_wall_floor_tile      char(1)         comment '是否已处理墙地砖（添加） -- \'0.否；1.是';

/*==============================================================*/
/* Table: biz_order_wall_floor_tile（modify2）                    */
/*==============================================================*/
alter table biz_order_wall_floor_tile
add   attribute                      varchar(100)    comment '属性（添加） -- \'',
add   supplier_name                  varchar(100)    comment '供应商名称（添加） -- \'',
add   loss_ratio                     decimal(10,2)   comment '损耗系数（添加） -- \'',
add   purchase_count                 decimal(10,2)   comment '已申请数量（添加） -- \'';

/*==============================================================*/
/* Table: biz_prepare_order（modify6）                            */
/*==============================================================*/
alter table biz_prepare_order
add   refuse_reason_type             varchar(10)     comment '拒绝理由分类（添加） -- \'',
add   refuse_reason                  varchar(1000)   comment '拒绝理由（添加） -- \'';

