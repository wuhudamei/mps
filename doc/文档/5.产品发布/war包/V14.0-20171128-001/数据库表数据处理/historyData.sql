UPDATE biz_employee
SET create_date = '2017-01-01 00:00:00'
WHERE
	create_date IS NULL;