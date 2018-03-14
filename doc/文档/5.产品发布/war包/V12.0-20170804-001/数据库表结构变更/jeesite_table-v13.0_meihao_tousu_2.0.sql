/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/7/25 13:46:38                           */
/*==============================================================*/


drop table if exists biz_order_complaint_pre;

/*==============================================================*/
/* Table: biz_order_complaint_pre                               */
/*==============================================================*/
create table biz_order_complaint_pre
(
   id                             int(11)         not null auto_increment comment '���� -- \'����',
   order_id                       int(11)         default NULL comment '����id -- \'',
   complaint_source               varchar(20)     default NULL comment 'Ͷ����Դ -- \'',
   complaint_employee_id          int(11)         default NULL comment 'Ͷ����id -- \'',
   complaint_describe             varchar(1000)   comment 'Ͷ������ -- \'',
   complaint_status               varchar(10)     default NULL comment 'Ͷ��״̬ -- \'',
   status_deal_employee_id        int(11)         comment '״̬������id -- \'',
   status_datetime                datetime        default NULL comment '״̬��������ʱ�� -- \'',
   status_describe                varchar(255)    default NULL comment '״̬���� -- \'',
   remarks                        varchar(255)    default NULL comment '��ע -- \'',
   create_date                    datetime        default NULL comment '����ʱ�� -- \'',
   create_by                      varchar(64)     default NULL comment '������ -- \'',
   update_date                    datetime        default NULL comment '����ʱ�� -- \'',
   update_by                      varchar(64)     default NULL comment '������ -- \'',
   del_flag                       char(1)         default NULL comment 'ɾ����� -- \'',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

