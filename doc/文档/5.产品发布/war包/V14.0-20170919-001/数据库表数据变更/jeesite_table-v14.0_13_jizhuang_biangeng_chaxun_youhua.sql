/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/9/18 15:28:50                           */
/*==============================================================*/


/*==============================================================*/
/* Table: biz_project_change_bill��modify2��                      */
/*==============================================================*/
alter table biz_project_change_bill
add   is_dealed_by_engineering_department char(1)         comment '�Ƿ񹤳̲��Ѵ��� -- \'0.��1.��',
add   engineering_department_deal_emplyee_id int(11)         comment '���̲�������Ա��id -- \'',
add   engineering_department_deal_datetime datetime        comment '���̲���������ʱ�� -- \'',
add   engineering_department_deal_describe varchar(1000)   comment '���̲�����˵�� -- \'';

