alter table biz_order_install_item
add 	is_show_install_description char(1)    comment '是否展示安装说明 -- \'',
add 	install_description         varchar(255)     comment '安装说明描述 -- \'';




alter table biz_order_install_plan
add 	allow_apply_checksize_date  datetime  comment '可申请复尺日期 -- \'';



create table biz_order_install_plan_advance_apply
(
   id                   int(11) not null auto_increment,
   order_id             int(11) not null comment '订单id',
   apply_type           varchar(10) not null comment '申请类型（安装/复尺）',
   order_install_plan_id int(11) not null comment '安装项计划id',
   install_item_name    varchar(100) not null comment '安装项计划名称',
   old_plan_apply_date  datetime not null comment '原计划可申请日期',
   deal_status          varchar(10) not null comment '处理状态',
   deal_employee_id     int(11) comment '处理人id',
   dela_time            datetime comment '处理时间',
   remarks              varchar(255) comment '备注',
   create_by            varchar(64) comment '创建人',
   create_date          datetime comment '创建时间',
   update_by            varchar(64) comment '更新人',
   update_date          datetime comment '更新时间',
   del_flag             char(1) comment '删除标志',
   primary key (id)
);
