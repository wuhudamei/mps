/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/8/8 17:47:40                            */
/*==============================================================*/


/*==============================================================*/
/* Table: biz_order��modify11��                                   */
/*==============================================================*/
alter table biz_order
add   is_scrap                       char(1)         comment '�Ƿ����ϣ���ӣ� -- \'0.��1.��',
add   scrap_operator_employee_id     int(11)         comment '���ϲ�����Ա��id����ӣ� -- \'',
add   scrap_datetime                 datetime        comment '��������ʱ�䣨��ӣ� -- \'',
add   scrap_describe                 varchar(1000)   comment '����˵������ӣ� -- \'',
add   qc_complete_accept_check_datetime datetime        comment '�ʼ쿢����������ʱ�䣨��ӣ� -- \'',
add   scrap_reason_type              varchar(10)     comment '����ԭ������ -- \'';

