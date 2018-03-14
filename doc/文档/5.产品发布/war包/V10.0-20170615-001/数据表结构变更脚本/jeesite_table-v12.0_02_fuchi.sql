/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/6/5 15:54:46                            */
/*==============================================================*/


/*==============================================================*/
/* Table: biz_order_checksize（modify2）                          */
/*==============================================================*/
alter table biz_order_checksize
add order_install_item_id          int(11)         comment '订单安装项id（添加） -- \'';

/*==============================================================*/
/* Table: biz_order_install_item（modify）                        */
/*==============================================================*/
alter table biz_order_install_item
add   days_plan_checksize            int             comment '开工第几天复尺（添加） -- \'',
add   is_to_checksize                char(1)         comment '是否复尺（添加） -- \'0.否；1.是；';

/*==============================================================*/
/* Table: biz_project_install_item（modify2）                     */
/*==============================================================*/
alter table biz_project_install_item
add   days_plan_checksize            int             comment '开工第几天复尺（添加） -- \'',
add   is_to_checksize                char(1)         comment '是否复尺（添加） -- \'0.否；1.是；';

