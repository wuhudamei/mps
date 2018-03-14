INSERT INTO biz_guarantee_money_balance (
	employee_id,
	guarantee_money_balance,
	guarantee_money_amount_paid_settle,
	guarantee_money_amount_paid_offline,
	guarantee_money_amount_paid_used
) SELECT
	pm_employee_id,
	MAX(takeoff_amount_total),
  MAX(takeoff_amount_total),
  0,
  0
FROM
	biz_pm_guarantee_money_log
GROUP BY
	pm_employee_id;

INSERT INTO biz_guarantee_money_balance (
	employee_id,
	guarantee_money_balance,
	guarantee_money_amount_paid_settle,
	guarantee_money_amount_paid_offline,
	guarantee_money_amount_paid_used
) SELECT
	employee_id,
	MAX(guarantee_money_amount_total),
  MAX(guarantee_money_amount_total),
  0,
  0
FROM
	biz_guarantee_money
GROUP BY
  employee_id;

UPDATE biz_guarantee_money a,
 (
	SELECT
		employee_id,
		guarantee_money_balance
	FROM
		biz_guarantee_money_balance
) AS b
SET a.guarantee_money_balance = b.guarantee_money_balance
WHERE
	a.employee_id = b.employee_id;


UPDATE biz_pm_guarantee_money_log a,
 (
	SELECT
		employee_id,
		guarantee_money_balance
	FROM
		biz_guarantee_money_balance
) AS b
SET a.guarantee_money_balance = b.guarantee_money_balance
WHERE
	a.pm_employee_id = b.employee_id;