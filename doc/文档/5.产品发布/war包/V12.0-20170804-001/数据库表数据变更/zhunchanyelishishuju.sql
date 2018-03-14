UPDATE biz_procedure_price set project_mode = 1 where project_mode is NULL;
UPDATE biz_task_package_templat set project_mode = 1 where project_mode is null;
UPDATE biz_task_package_auxiliary_materials set project_mode =1 where project_mode is NULL;
UPDATE biz_order_taskpackage_procedure set project_mode = 1 where project_mode is null;
UPDATE biz_order_taskpackage set project_mode = 1 where project_mode is null;
UPDATE biz_order_taskpackage_payment_summary set project_mode = 1 where project_mode is null;
update biz_task_package_work_plan_templat set project_mode = 1 where project_mode is null;