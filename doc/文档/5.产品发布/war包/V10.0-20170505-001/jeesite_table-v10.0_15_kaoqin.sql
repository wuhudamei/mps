/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/3 15:20:06                            */
/*==============================================================*/


/*==============================================================*/
/* Table: biz_attend_bill��modify2��                              */
/*==============================================================*/
alter table biz_attend_bill
change    attend_days_must  attend_days_must             decimal(5,2)    comment 'Ӧ�����������޸ģ� -- \'',
change    leave_days_thing  leave_days_thing             decimal(5,2)    comment '�¼��������޸ģ� -- \'',
change    leave_days_sick   leave_days_sick              decimal(5,2)    comment '�����������޸ģ� -- \'',
change    leave_days_annual leave_days_annual            decimal(5,2)    comment '����������޸ģ� -- \'',
change    rest_days         rest_days                    decimal(5,2)    comment '�����������޸ģ� -- \'';

