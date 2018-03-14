create table biz_customer_return_visit_invalid_record
(
   id                   int(11) not null auto_increment,
   order_id             int(11) not null comment '订单id',
   invalid_reason       varchar(200) comment '无效原因',
   custom_service_id    varchar(50) comment '回访人员ID',
   custom_service_name  varchar(50) not null comment '回访人员姓名',
   return_visit_time    datetime not null comment '回访时间',
   return_visit_node    int(11) not null comment '回访节点',
   return_visit_node_name varchar(50) not null comment '回访节点名称',
   create_by            varchar(64) comment '创建人',
   create_date          datetime comment '创建时间',
   update_by            varchar(64) comment '更新人',
   update_date          datetime comment '更新时间',
   del_flag             char(1) comment '删除标记',
   primary key (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;