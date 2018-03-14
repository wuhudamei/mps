INSERT INTO biz_order_install_plan_accept_log (
	order_id,
	order_install_plan_id,
	accept_type,
	order_install_iteam_id,
	order_install_iteam,
	create_by,
	create_date,
	update_by,
	update_date,
	del_flag,
	remarks,
	operater_id
) SELECT
	*
FROM
	(
		SELECT
			b.order_id,
			a.business_only_mark_int,
			'1',
			b.order_install_item_id,
			b.install_item_name,
			a.create_by,
			a.create_date,
			a.update_by,
			a.update_date,
			a.del_flag,
			'验收合格',
			a.business_employee_id
		FROM
			biz_business_status_log a
		LEFT JOIN biz_order_install_plan b ON a.business_only_mark_int = b.id
		WHERE
			a.business_type = '901'
		AND a.business_status = '4'
	) m;
	
	
UPDATE biz_order_install_plan
SET first_pass_rate = 1
WHERE `status` = 4;


UPDATE biz_order_install_plan
SET 
unqualified_times = 0;