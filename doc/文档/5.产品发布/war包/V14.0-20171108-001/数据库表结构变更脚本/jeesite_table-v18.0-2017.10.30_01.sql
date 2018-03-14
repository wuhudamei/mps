ALTER TABLE biz_task_package_templat DROP apply_budget_ratio;
alter TABLE biz_task_package_templat ADD apply_budget_ratio double(8,2) comment '申请预算比例';