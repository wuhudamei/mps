alter table biz_pm_attend_month add column actual_value_total int(11) DEFAULT null comment 'ʵ��ȡֵ�ܺ�';

alter table biz_pm_attend_month_order add column actual_value int(11) DEFAULT null comment 'ʵ��ȡֵ';
update  biz_pm_attend_cnfg set project_mode=1;