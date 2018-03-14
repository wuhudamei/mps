UPDATE biz_project_progress_boning a
LEFT JOIN biz_order b ON a.order_id = b.id
SET a.actual_end_date = b.actual_end_date