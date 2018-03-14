/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/7/13 18:11:15                           */
/*==============================================================*/


drop table if exists biz_todo_item;

drop table if exists biz_todo_item_type;

drop table if exists biz_business_urge_pay;


/*==============================================================*/
/* Table: biz_qc_check_node��modify2��                            */
/*==============================================================*/
alter table biz_qc_check_node
add   days_to_apply                  int             comment '������ڼ������� -- \'';

/*==============================================================*/
/* Table: biz_todo_item                                         */
/*==============================================================*/
create table biz_todo_item
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   todo_item_type_id              int(11)         comment '�����������id -- \'',
   related_business_type          varchar(20)     comment '����ҵ������ -- \'',
   related_business_id            int(11)         comment '����ҵ��id���� -- \'',
   order_id                       int(11)         comment '����id -- \'',
   todo_item_employee_id          int(11)         comment '������Ա��id -- \'',
   todo_item_remind_title         varchar(100)    comment '�����������ѱ��� -- \'',
   todo_item_deal_url             varchar(255)    comment '���������url -- \'',
   todo_item_generated_datetime   datetime        comment '����������������ʱ�� -- \'',
   todo_item_remind_datetime      datetime        comment '����������������ʱ�� -- \'',
   todo_item_view_datetime        datetime        comment '��������鿴����ʱ�� -- \'',
   todo_item_deal_datetime        datetime        comment '�������������ʱ�� -- \'',
   is_viewed                      char(1)         comment '�Ƿ��Ѳ鿴 -- \'0.��1.��',
   is_solved                      char(1)         comment '�Ƿ��Ѵ��� -- \'0.��1.��',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_todo_item_type                                    */
/*==============================================================*/
create table biz_todo_item_type
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   store_id                       int(11)         comment '�ŵ�id -- \'',
   project_mode                   varchar(10)     comment '����ģʽ -- \'',
   todo_item_code                 varchar(100)    comment '��������code -- \'',
   todo_item_name                 varchar(100)    comment '������������ -- \'',
   days_to_remind                 int             comment '������ǰ���� -- \'',
   business_step                  varchar(100)    comment 'ҵ��׶� -- \'',
   is_to_remind                   char(1)         comment '�Ƿ����� -- \'0.��1.��',
   todo_item_generate_style       varchar(10)     comment '�����������ɷ�ʽ -- \'1.ҵ�񴥷���2.ʱ�䴥��',
   todo_item_remarks              varchar(255)    comment '�������ע -- \'',
   sort_index                     int             comment '������� -- \'',
   related_business_type          varchar(20)     comment '����ҵ������ -- \'',
   related_business_id            int(11)         comment '����ҵ��id���� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_business_urge_pay                                 */
/*==============================================================*/
create table biz_business_urge_pay
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   related_business_type          varchar(10)     comment '����ҵ������ -- \'1001.����',
   related_business_id_int        int(11)         comment '����ҵ��id���� -- \'',
   urge_pay_type                  varchar(10)     comment '�߽����� -- \'10.���ڿ�߽�',
   urge_pay_content               varchar(1000)   comment '�߽����� -- \'',
   urge_pay_channel               varchar(10)     comment '�߽����� -- \'10.�ֻ�����',
   status                         varchar(10)     comment '״̬ -- \'10.���߽ɣ�20.�Ѵ߽ɣ�30.����߽�',
   status_datetime                datetime        comment '״̬����ʱ�� -- \'',
   status_operator_employee_id    int(11)         comment '״̬������Ա��id -- \'',
   urge_target_name               varchar(20)     comment '�߽ɶ������� -- \'',
   urge_target_phone              varchar(11)     comment '�߽ɶ����ֻ��� -- \'',
   urge_target_name2              varchar(20)     comment '�߽ɶ�������2 -- \'',
   urge_target_phone2             varchar(11)     comment '�߽ɶ����ֻ���2 -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);
