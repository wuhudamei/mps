/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/10 18:36:00                           */
/*==============================================================*/


/*==============================================================*/
/* Table: biz_employee��modify4��                                 */
/*==============================================================*/
alter table biz_employee
add   exchange_order_times           int             comment '���������� -- \'';

/*==============================================================*/
/* Table: biz_order_distribute_log��modify��                      */
/*==============================================================*/
alter table biz_order_distribute_log
add   old_employee_id                int(11)         comment 'ԭԱ��id -- \'',
add   reason_type                    varchar(30)     comment 'ԭ������ -- \'',
add   reason_remarks                 varchar(255)    comment 'ԭ��˵�� -- \'';

