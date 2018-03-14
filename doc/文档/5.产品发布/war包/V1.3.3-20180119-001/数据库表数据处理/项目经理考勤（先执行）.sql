drop table if exists order_manager_snapshot;

/*==============================================================*/
/* Table: order_manager_snapshot                                */
/*==============================================================*/
create table order_manager_snapshot
(
   id                   int(11) not null,
   order_id             int(11),
   manager_id           int(11),
   attend_month         datetime,
   remarks              varchar(255),
   create_by            int(11),
   create_date          datetime,
   update_by            int(11),
   update_date          datetime,
   del_flag             char(1),
   primary key (id)
);

alter table order_manager_snapshot comment '保存每个月,订单项目经理的信息';
