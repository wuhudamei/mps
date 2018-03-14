/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/3 15:20:06                            */
/*==============================================================*/


/*==============================================================*/
/* Table: biz_attend_bill（modify2）                              */
/*==============================================================*/
alter table biz_attend_bill
change    attend_days_must  attend_days_must             decimal(5,2)    comment '应出勤天数（修改） -- \'',
change    leave_days_thing  leave_days_thing             decimal(5,2)    comment '事假天数（修改） -- \'',
change    leave_days_sick   leave_days_sick              decimal(5,2)    comment '病假天数（修改） -- \'',
change    leave_days_annual leave_days_annual            decimal(5,2)    comment '年假天数（修改） -- \'',
change    rest_days         rest_days                    decimal(5,2)    comment '补休天数（修改） -- \'';

