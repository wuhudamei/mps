-- //1.主材转给供应商日志缺失【create_date】
UPDATE biz_business_status_log a,
 biz_order_install_plan b
SET a.create_date = DATE_ADD(b.apply_into_create_datetime,INTERVAL 10 MINUTE)
WHERE
	a.business_only_mark_int = b.id
AND a.business_type = 901
AND a.business_status = 3
AND a.create_date IS NULL;

-- //2.主材转给供应商日志缺失【remark】
UPDATE biz_business_status_log a,
 biz_order_install_plan b
SET a.remarks = DATE_FORMAT(
	b.supplier_confirm_into_date,
	'%Y-%m-%d'
)
WHERE
	a.business_only_mark_int = b.id
AND a.business_type = 901
AND a.business_status = 3
AND a.remarks IS NULL
AND b.supplier_confirm_into_date IS NOT NULL;



-- //3.更新主材安装计划表中的17条历史数据
UPDATE biz_order_install_plan a
SET a.`status` = 3,
 a.supplier_confirm_into_date = '2016-04-05 00:00:00'
WHERE
	a.`status` = 2
AND a.apply_into_create_datetime < '2017-3-31 23:59:59';


-- //4.插入响应的转供应商的日志（共17条）
INSERT INTO biz_business_status_log (
	business_type,
	business_only_mark_int,
	business_only_mark_varchar,
	business_status,
	status_datetime,
	business_remarks,
	business_employee_id,
	remarks,
	create_by,
	create_date,
	update_by,
	update_date,
	del_flag
)
VALUES
	(
		"901",
		21,
		NULL,
		3,
		'2016-04-05 00:00:00',
		NULL,
		NULL,
		'2016-04-05',
		NULL,
		'2016-04-05 00:00:00',
		NULL,
		'2016-04-05 00:00:00',
		0
	),
	(
		901,
		47,
		NULL,
		3,
		'2016-04-05 00:00:00',
		NULL,
		NULL,
		'2016-04-05',
		NULL,
		'2016-04-05 00:00:00',
		NULL,
		'2016-04-05 00:00:00',
		0
	),
	(
		901,
		63,
		NULL,
		3,
		'2016-04-05 00:00:00',
		NULL,
		NULL,
		'2016-04-05',
		NULL,
		'2016-04-05 00:00:00',
		NULL,
		'2016-04-05 00:00:00',
		0
	),
	(
		901,
		71,
		NULL,
		3,
		'2016-04-05 00:00:00',
		NULL,
		NULL,
		'2016-04-05',
		NULL,
		'2016-04-05 00:00:00',
		NULL,
		'2016-04-05 00:00:00',
		0
	),
	(
		901,
		84,
		NULL,
		3,
		'2016-04-05 00:00:00',
		NULL,
		NULL,
		'2016-04-05',
		NULL,
		'2016-04-05 00:00:00',
		NULL,
		'2016-04-05 00:00:00',
		0
	),
	(
		901,
		91,
		NULL,
		3,
		'2016-04-05 00:00:00',
		NULL,
		NULL,
		'2016-04-05',
		NULL,
		'2016-04-05 00:00:00',
		NULL,
		'2016-04-05 00:00:00',
		0
	),
	(
		901,
		126,
		NULL,
		3,
		'2016-04-05 00:00:00',
		NULL,
		NULL,
		'2016-04-05',
		NULL,
		'2016-04-05 00:00:00',
		NULL,
		'2016-04-05 00:00:00',
		0
	),
	(
		901,
		147,
		NULL,
		3,
		'2016-04-05 00:00:00',
		NULL,
		NULL,
		'2016-04-05',
		NULL,
		'2016-04-05 00:00:00',
		NULL,
		'2016-04-05 00:00:00',
		0
	),
	(
		901,
		155,
		NULL,
		3,
		'2016-04-05 00:00:00',
		NULL,
		NULL,
		'2016-04-05',
		NULL,
		'2016-04-05 00:00:00',
		NULL,
		'2016-04-05 00:00:00',
		0
	),
	(
		901,
		156,
		NULL,
		3,
		'2016-04-05 00:00:00',
		NULL,
		NULL,
		'2016-04-05',
		NULL,
		'2016-04-05 00:00:00',
		NULL,
		'2016-04-05 00:00:00',
		0
	),
	(
		901,
		188,
		NULL,
		3,
		'2016-04-05 00:00:00',
		NULL,
		NULL,
		'2016-04-05',
		NULL,
		'2016-04-05 00:00:00',
		NULL,
		'2016-04-05 00:00:00',
		0
	),
	(
		901,
		200,
		NULL,
		3,
		'2016-04-05 00:00:00',
		NULL,
		NULL,
		'2016-04-05',
		NULL,
		'2016-04-05 00:00:00',
		NULL,
		'2016-04-05 00:00:00',
		0
	),
	(
		901,
		206,
		NULL,
		3,
		'2016-04-05 00:00:00',
		NULL,
		NULL,
		'2016-04-05',
		NULL,
		'2016-04-05 00:00:00',
		NULL,
		'2016-04-05 00:00:00',
		0
	),
	(
		901,
		335,
		NULL,
		3,
		'2016-04-05 00:00:00',
		NULL,
		NULL,
		'2016-04-05',
		NULL,
		'2016-04-05 00:00:00',
		NULL,
		'2016-04-05 00:00:00',
		0
	),
	(
		901,
		488,
		NULL,
		3,
		'2016-04-05 00:00:00',
		NULL,
		NULL,
		'2016-04-05',
		NULL,
		'2016-04-05 00:00:00',
		NULL,
		'2016-04-05 00:00:00',
		0
	),
	(
		901,
		4210,
		NULL,
		3,
		'2016-04-05 00:00:00',
		NULL,
		NULL,
		'2016-04-05',
		NULL,
		'2016-04-05 00:00:00',
		NULL,
		'2016-04-05 00:00:00',
		0
	),
	(
		901,
		4211,
		NULL,
		3,
		'2016-04-05 00:00:00',
		NULL,
		NULL,
		'2016-04-05',
		NULL,
		'2016-04-05 00:00:00',
		NULL,
		'2016-04-05 00:00:00',
		0
	);

