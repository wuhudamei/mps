UPDATE biz_order_install_plan a,biz_order_install_item b,biz_order c
SET 
a.allow_apply_checksize_date = DATE_ADD(c.actual_start_date,INTERVAL b.days_plan_checksize DAY)
WHERE
	a.order_install_item_id = b.id
AND b.order_id = c.id
AND b.is_to_checksize = 1
