/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/8/1 19:41:13                            */
/*==============================================================*/


drop table if exists biz_pm_attend_cnfg;

drop table if exists biz_pm_attend_cnfg_star;

drop table if exists biz_pm_attend_day_order;

drop table if exists biz_pm_attend_month;

drop table if exists biz_pm_attend_month_order;

drop table if exists biz_pm_attend_salary_bill;

drop table if exists biz_pm_attend_salary_summary_bill;

drop table if exists biz_pm_attend_salary_summary_bill_rel;

/*==============================================================*/
/* Table: biz_business_sign（modify）                             */
/*==============================================================*/
alter table biz_business_sign
add   sign_step                      varchar(10)     comment '签到阶段（添加） -- \'10.基装；20.竣工',
add   is_valid                       char(1)         comment '是否合格（添加） -- \'0.否；1.是';


/*==============================================================*/
/* Table: biz_sign（modify）                                      */
/*==============================================================*/
alter table biz_sign
add   sign_step                      varchar(10)     comment '签到阶段（添加） -- \'10.基装；20.竣工',
add   is_valid                       char(1)         comment '是否合格（添加） -- \'0.否；1.是';


/*==============================================================*/
/* Table: biz_pm_attend_cnfg                                    */
/*==============================================================*/
create table biz_pm_attend_cnfg
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   store_id                       int(11)         comment '门店id -- \'',
   project_mode                   varchar(10)     comment '工程模式 -- \'',
   absence_punish_amount          decimal(8,2)    comment '缺勤扣款金额 -- \'',
   sign_cycle_days_basicwork      int             comment '基装签到周期天数 -- \'',
   sign_cycle_days_complete       int             comment '竣工签到周期天数 -- \'',
   effect_month                   varchar(20)     comment '生效月份 -- \'',
   is_enabled                     char(1)         comment '是否启用 -- \'0.停用；1.启用',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_pm_attend_cnfg_star                               */
/*==============================================================*/
create table biz_pm_attend_cnfg_star
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   pm_attend_cnfg_id              int(11)         comment 'pm考勤配置id -- \'',
   star                           int             comment '星级 -- \'',
   star_salary_all_attend         decimal(8,2)    comment '星级全勤底薪 -- \'',
   star_salary_min                decimal(8,2)    comment '星级最低薪水 -- \'',
   is_enabled                     char(1)         comment '是否启用 -- \'0.停用；1.启用',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_pm_attend_day_order                               */
/*==============================================================*/
create table biz_pm_attend_day_order
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_id                       int(11)         comment '订单id -- \'',
   pm_employee_id                 int(11)         comment 'pm员工id -- \'',
   sign_datetime                  datetime        comment '签到日期时间 -- \'',
   sign_error_distance            decimal(8,2)    comment '签到误差 -- \'',
   is_valid                       char(1)         comment '是否合格 -- \'0.否；1.是',
   sign_step                      varchar(10)     comment '签到阶段 -- \'10.基装；20.竣工',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_pm_attend_month                                   */
/*==============================================================*/
create table biz_pm_attend_month
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   pm_attend_month_code           varchar(100)    comment 'pm考勤月度编码 -- \'',
   attend_month                   varchar(20)     comment '考勤月份 -- \'',
   pm_employee_id                 int(11)         comment 'pm员工id -- \'',
   attend_start_date              datetime        comment '考勤开始日期 -- \'',
   attend_end_date                datetime        comment '考勤结束日期 -- \'',
   must_sign_times                int             comment '应签到次数 -- \'',
   real_sign_times                int             comment '实际签到次数 -- \'',
   owed_sign_times                int             comment '欠缺签到次数 -- \'',
   attend_rate                    decimal(5,2)    comment '考勤率 -- \'',
   status                         varchar(10)     comment '状态 -- \'',
   status_datetime                datetime        comment '状态日期时间 -- \'',
   status_employee_id             int(11)         comment '状态操作人员工id -- \'',
   status_describe                varchar(1000)   comment '状态描述 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_pm_attend_month_order                             */
/*==============================================================*/
create table biz_pm_attend_month_order
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   pm_attend_month_id             int(11)         comment 'pm考勤月度id -- \'',
   order_id                       int(11)         comment '订单id -- \'',
   pm_employee_id                 int(11)         comment 'pm员工id -- \'',
   must_sign_times                int             comment '应签到次数 -- \'',
   real_sign_times                int             comment '实际签到次数 -- \'',
   owed_sign_times                int             comment '欠缺签到次数 -- \'',
   attend_rate                    decimal(5,2)    comment '考勤率 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_pm_attend_salary_bill                             */
/*==============================================================*/
create table biz_pm_attend_salary_bill
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   pm_attend_salary_bill_code     varchar(100)    comment '工资单编号 -- \'',
   pm_attend_salary_summary_bill_id int(11)         comment 'pm工资汇总单id -- \'',
   pm_attend_month_id             int(11)         comment 'pm考勤月度id -- \'',
   pm_employee_id                 int(11)         comment 'pm员工id -- \'',
   pm_star                        int             comment 'pm星级 -- \'',
   pm_star_salary                 decimal(8,2)    comment 'pm星级全勤底薪 -- \'',
   pm_star_salary_min             decimal(8,2)    comment 'pm星级最低薪水 -- \'',
   pm_star_salary_attend_full_default decimal(8,2)    comment '考勤周期全勤薪水（系统默认） -- \'',
   pm_star_salary_attend_default  decimal(8,2)    comment '考勤薪水（系统默认） -- \'',
   pm_star_salary_real_default    decimal(8,2)    comment '实际薪水（系统默认） -- \'',
   pm_star_salary_attend_full     decimal(8,2)    comment '考勤周期全勤薪水 -- \'',
   pm_star_salary_attend          decimal(8,2)    comment '考勤薪水 -- \'',
   pm_star_salary_punish          decimal(8,2)    comment '薪水扣款 -- \'',
   pm_star_salary_real            decimal(8,2)    comment '实际薪水 -- \'',
   status                         varchar(10)     comment '状态 -- \'',
   status_datetime                datetime        comment '状态日期时间 -- \'',
   status_employee_id             int(11)         comment '状态操作人员工id -- \'',
   status_describe                varchar(1000)   comment '状态描述 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_pm_attend_salary_summary_bill                     */
/*==============================================================*/
create table biz_pm_attend_salary_summary_bill
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   store_id                       int(11)         comment '门店id -- \'',
   project_mode                   varchar(10)     comment '工程模式 -- \'',
   pm_attend_salary_summary_bill_code varchar(100)    comment 'pm工资单汇总编号 -- \'',
   salary_month                   varchar(20)     comment '工资月份 -- \'',
   generated_datetime             datetime        comment '汇总生成日期时间 -- \'',
   salray_bill_count              int             comment '工资单数量 -- \'',
   salary_total_amount            decimal(8,2)    comment '工资总额 -- \'',
   status                         varchar(10)     comment '状态 -- \'',
   status_datetime                datetime        comment '状态日期时间 -- \'',
   status_employee_id             int(11)         comment '状态操作人员工id -- \'',
   status_describe                varchar(1000)   comment '状态描述 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_pm_attend_salary_summary_bill_rel                 */
/*==============================================================*/
create table biz_pm_attend_salary_summary_bill_rel
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   pm_attend_salary_summary_bill_id int(11)         comment '工资汇总单id -- \'',
   pm_attend_salary_bill_id       int(11)         comment '工资单id -- \'',
   primary key (id)
);

