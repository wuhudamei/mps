/*==============================================================*/
/* Table: biz_order_install_item（modify2）                       */
/*==============================================================*/
alter table biz_order_install_item
add   install_mode                   varchar(10)     comment '安装模式（添加） -- \'1.产业；2.传统',
add   status                         varchar(10)     comment '状态（添加） -- \'',
add   status_datetime                datetime        comment '状态日期（添加） -- \'';

/*==============================================================*/
/* Table: biz_order_install_plan（modify）                        */
/*==============================================================*/
alter table biz_order_install_plan
add   install_mode                   varchar(10)     comment '安装模式（添加） -- \'1.产业；2.传统',
add   send_supplier_id               int(11)         comment '转给供应商id（添加） -- \'',
add   plan_complete_date             datetime        comment '计划完工日期日期（添加） -- \'',
add   supplier_confirm_complete_date datetime        comment '供应商确认完成日期（添加） -- \'';

/*==============================================================*/
/* Table: biz_project_install_item（modify4）                     */
/*==============================================================*/
alter table biz_project_install_item
add   install_mode                   varchar(10)     comment '安装模式（添加） -- \'1.产业；2.传统';

