#任务包模板
UPDATE biz_task_package_templat
SET settle_style = 1;

#任务包工序
UPDATE biz_order_taskpackage_procedure
SET labor_budget_amount = labor_price * budget_number,
 auxiliary_materials_budget_amount = accessories_price * budget_number,
 labor_settle_amount = labor_price * settlement_number,
 auxiliary_materials_settle_amount = accessories_price * settlement_number,
 labor_auxiliary_materials_settle_amount = synthesize_price * settlement_number
WHERE
	budget_number > 0;

#任务包
UPDATE biz_order_taskpackage
SET settle_style = 1;

UPDATE biz_order_taskpackage a,
 (
	SELECT
		SUM(p.labor_budget_amount) AS "labor_budget_amount",
		SUM(
			p.auxiliary_materials_budget_amount
		) AS "auxiliary_materials_budget_amount",
		p.taskpackage_id
	FROM
		biz_order_taskpackage_procedure p
	GROUP BY
		taskpackage_id
) b
SET a.labor_budget_amount = b.labor_budget_amount,
 a.auxiliary_materials_budget_amount = b.auxiliary_materials_budget_amount
WHERE
	a.id = b.taskpackage_id;


#任务包结算单

update biz_order_taskpackage_settlement set settle_style = 1,worker_group_settle_amount=settlement_amount,pm_materials_settle_amount=0;

UPDATE biz_order_taskpackage_settlement a,
 (
	SELECT
		SUM(p.labor_settle_amount) AS "labor_settle_amount",
		SUM(
			p.auxiliary_materials_settle_amount
		) AS "auxiliary_materials_settle_amount",
		SUM(
			p.labor_auxiliary_materials_settle_amount
		) AS "labor_auxiliary_materials_settle_amount",
		p.taskpackage_id
	FROM
		biz_order_taskpackage_procedure p
	GROUP BY
		taskpackage_id
) b
SET a.auxiliary_materials_settle_amount = b.auxiliary_materials_settle_amount,
 a.labor_settle_amount = b.labor_settle_amount,
 a.labor_auxiliary_materials_settle_amount = b.labor_auxiliary_materials_settle_amount
WHERE
	a.order_taskpackage_id = b.taskpackage_id;


