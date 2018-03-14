/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/6/26 18:06:00                           */
/*==============================================================*/


/*==============================================================*/
/* Table: biz_project_install_item（modify3）                     */
/*==============================================================*/
alter table biz_project_install_item
add   is_show_install_description    char(1)         comment '是否展示安装说明（添加） -- \'',
add   install_description            varchar(255)    comment '安装说明描述（添加） -- \'';

/*==============================================================*/
/* Table: biz_order_install_plan（modify4）                       */
/*==============================================================*/
alter table biz_order_install_plan
add   install_require                varchar(255)    comment '安装要求（添加） -- \'';

