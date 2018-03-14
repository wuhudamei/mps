

drop table if exists biz_customer_return_visit_record_answer;
create table biz_customer_return_visit_record_answer
(
   id                   int(11) not null auto_increment,
   return_visit_record_id int(11) not null comment '回访记录ID',
   return_visit_question varchar(100) not null comment '回访问题',
   question_answer      varchar(1000) not null comment '问题答案',
   create_by            varchar(64) comment '创建人',
   create_date          datetime comment '创建时间',
   update_by            varchar(64) comment '更新人',
   update_date          datetime comment '更新时间',
   del_flag             char(1) comment '删除标记',
   primary key (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

alter table biz_customer_return_visit_record_answer comment '客户回访问题答案';
