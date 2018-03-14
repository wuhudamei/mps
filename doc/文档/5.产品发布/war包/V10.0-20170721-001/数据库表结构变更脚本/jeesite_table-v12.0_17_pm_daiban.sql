/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/7/13 18:11:15                           */
/*==============================================================*/


drop table if exists biz_todo_item;

drop table if exists biz_todo_item_type;

drop table if exists biz_business_urge_pay;


/*==============================================================*/
/* Table: biz_qc_check_node（modify2）                            */
/*==============================================================*/
alter table biz_qc_check_node
add   days_to_apply                  int             comment '开工后第几天申请 -- \'';

/*==============================================================*/
/* Table: biz_todo_item                                         */
/*==============================================================*/
create table biz_todo_item
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   todo_item_type_id              int(11)         comment '待办事项分类id -- \'',
   related_business_type          varchar(20)     comment '关联业务类型 -- \'',
   related_business_id            int(11)         comment '关联业务id整型 -- \'',
   order_id                       int(11)         comment '订单id -- \'',
   todo_item_employee_id          int(11)         comment '待办人员工id -- \'',
   todo_item_remind_title         varchar(100)    comment '待办事项提醒标题 -- \'',
   todo_item_deal_url             varchar(255)    comment '待办事项处理url -- \'',
   todo_item_generated_datetime   datetime        comment '待办事项生成日期时间 -- \'',
   todo_item_remind_datetime      datetime        comment '待办事项提醒日期时间 -- \'',
   todo_item_view_datetime        datetime        comment '待办事项查看日期时间 -- \'',
   todo_item_deal_datetime        datetime        comment '待办事项处理日期时间 -- \'',
   is_viewed                      char(1)         comment '是否已查看 -- \'0.否；1.是',
   is_solved                      char(1)         comment '是否已处理 -- \'0.否；1.是',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_todo_item_type                                    */
/*==============================================================*/
create table biz_todo_item_type
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   store_id                       int(11)         comment '门店id -- \'',
   project_mode                   varchar(10)     comment '工程模式 -- \'',
   todo_item_code                 varchar(100)    comment '待办事项code -- \'',
   todo_item_name                 varchar(100)    comment '待办事项名称 -- \'',
   days_to_remind                 int             comment '提醒提前天数 -- \'',
   business_step                  varchar(100)    comment '业务阶段 -- \'',
   is_to_remind                   char(1)         comment '是否提醒 -- \'0.否；1.是',
   todo_item_generate_style       varchar(10)     comment '待办事项生成方式 -- \'1.业务触发；2.时间触发',
   todo_item_remarks              varchar(255)    comment '待办事项备注 -- \'',
   sort_index                     int             comment '排序序号 -- \'',
   related_business_type          varchar(20)     comment '关联业务类型 -- \'',
   related_business_id            int(11)         comment '关联业务id整型 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_business_urge_pay                                 */
/*==============================================================*/
create table biz_business_urge_pay
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   related_business_type          varchar(10)     comment '关联业务类型 -- \'1001.订单',
   related_business_id_int        int(11)         comment '关联业务id整型 -- \'',
   urge_pay_type                  varchar(10)     comment '催缴类型 -- \'10.二期款催缴',
   urge_pay_content               varchar(1000)   comment '催缴内容 -- \'',
   urge_pay_channel               varchar(10)     comment '催缴渠道 -- \'10.手机短信',
   status                         varchar(10)     comment '状态 -- \'10.待催缴；20.已催缴；30.不需催缴',
   status_datetime                datetime        comment '状态日期时间 -- \'',
   status_operator_employee_id    int(11)         comment '状态操作人员工id -- \'',
   urge_target_name               varchar(20)     comment '催缴对象姓名 -- \'',
   urge_target_phone              varchar(11)     comment '催缴对象手机号 -- \'',
   urge_target_name2              varchar(20)     comment '催缴对象姓名2 -- \'',
   urge_target_phone2             varchar(11)     comment '催缴对象手机号2 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);
