alter table biz_customer_return_visit add column project_mode varchar(10) DEFAULT '1' comment '工程模式';


drop table if exists biz_customer_return_visit_tradition_order_data;
create table biz_customer_return_visit_tradition_order_data
(
   id                   int(11) not null auto_increment comment 'id',
   order_id             int(11) not null comment '订单id',
   return_visit_node    varchar(10) not null comment '回访节点id',
   return_visit_status  TINYINT not null comment '回访状态（0过期、1待回访、2已回访）',
   return_visit_time    datetime comment '回访时间',
   return_visit_times   int comment '回访次数',
   create_by            varchar(64) comment '创建人',
   create_date          datetime comment '创建时间',
   update_by            varchar(64) comment '更新人',
   update_date          datetime comment '更新时间',
   del_flag             char(1) comment '删除标识',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='传统订单待回访节点数据';