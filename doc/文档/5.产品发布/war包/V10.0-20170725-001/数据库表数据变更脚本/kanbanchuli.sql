UPDATE biz_project_progress_boning
SET node4_actual_date = node4_plan_date
WHERE order_id IN (305, 428, 563)
AND node4_actual_date IS NULL;

UPDATE biz_project_progress_boning
SET node5_actual_date = node5_plan_date
WHERE order_id IN (305, 428, 563)
AND node5_actual_date IS NULL;

UPDATE biz_project_progress_boning
SET node7_actual_date = node7_plan_date
WHERE order_id IN (305, 428, 563)
AND node7_actual_date IS NULL;

UPDATE biz_project_progress_boning
SET node9_actual_date = node9_plan_date
WHERE order_id IN (305, 428, 563)
AND node9_actual_date IS NULL;

UPDATE biz_project_progress_boning
SET node19_actual_date = node19_plan_date
WHERE order_id IN (305, 428, 563)
AND node19_actual_date IS NULL;

UPDATE biz_order
SET is_to_refresh_process_data = 1
WHERE id IN (305, 428, 563)