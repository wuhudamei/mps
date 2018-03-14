drop table if exists biz_customer_return_visit;

drop table if exists biz_customer_return_visit_content;

drop table if exists biz_customer_return_visit_record;

drop table if exists biz_customer_return_visit_record_answer;

/*==============================================================*/
/* Table: biz_customer_return_visit                             */
/*==============================================================*/
create table biz_customer_return_visit
(
   id                   int(11) not null auto_increment,
   store_id             int(11) not null comment '门店',
   return_visit_node    varchar(100) not null comment '回访节点',
   project_node         int(11) not null comment '工程节点',
   create_by            varchar(64) comment '创建人',
   create_date          datetime comment '创建时间',
   update_by            varchar(64) comment '更新人',
   update_date          datetime comment '更新时间',
   del_flag             char(1) comment '删除标记',
   primary key (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

alter table biz_customer_return_visit comment '回访节点信息表';

/*==============================================================*/
/* Table: biz_customer_return_visit_content                     */
/*==============================================================*/
create table biz_customer_return_visit_content
(
   id                   int(11) not null auto_increment,
   return_visit_id      int(11) not null comment '回访节点ID',
   question_content     varchar(100) not null comment '问题描述',
   reply_mode           int(11) not null comment '答复方式(dict:0：填空；1：一级选项；2：二级选项)',
   item_content         varchar(1000) comment '选项内容',
   create_by            varchar(64) comment '创建人',
   create_date          datetime comment '创建日期',
   update_by            varchar(64) comment '更新人',
   update_date          datetime comment '更新时间',
   del_flag             char(1) comment '删除标记',
   primary key (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

alter table biz_customer_return_visit_content comment '回访节点内容表';

/*==============================================================*/
/* Table: biz_customer_return_visit_record                      */
/*==============================================================*/
create table biz_customer_return_visit_record
(
   id                   int(11) not null auto_increment,
   order_id             int(11) not null comment '订单ID',
   custom_service_id    varchar(50) comment '回访人员ID',
   custom_service_name  varchar(50) not null comment '回访人员姓名',
   return_visit_time    datetime not null comment '回访时间',
   return_visit_node    int(11) not null comment '回访节点id',
   return_visit_node_name varchar(50) not null comment '回访节点名称',
   create_by            varchar(64) comment '创建人',
   create_date          datetime comment '创建时间',
   update_by            varchar(64) comment '更新人',
   update_date          datetime comment '更新时间',
   del_flag             char(1) comment '删除标记',
   primary key (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

alter table biz_customer_return_visit_record comment '客服回访记录表';

/*==============================================================*/
/* Table: biz_customer_return_visit_record_answer               */
/*==============================================================*/
create table biz_customer_return_visit_record_answer
(
   id                   int(11) not null auto_increment,
   return_visit_record_id int(11) not null comment '回访记录ID',
   return_visit_question_id int(11) not null comment '回访问题ID',
   question_answer      varchar(100) not null comment '问题答案',
   create_by            varchar(64) comment '创建人',
   create_date          datetime comment '创建时间',
   update_by            varchar(64) comment '更新人',
   update_date          datetime comment '更新时间',
   del_flag             char(1) comment '删除标记',
   primary key (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

alter table biz_customer_return_visit_record_answer comment '客户回访问题答案';
