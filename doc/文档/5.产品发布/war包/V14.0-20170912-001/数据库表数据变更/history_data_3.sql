UPDATE biz_order_wall_floor_tile n
SET n.purchase_count = (
	SELECT
		IFNULL(SUM(b.apply_counta), 0)
	FROM
		biz_purchase a
	INNER JOIN biz_purchase_wall_floor_tile b ON b.purchase_id = a.id
	WHERE
		a.order_id = n.order_id
	AND a.purchase_type = 5
	AND a.`status` != 21
	AND n.main_mate_type = b.main_mate_type
	AND n.position = b.position
	AND n.brand_combo = b.brand_combo
	AND n.model = b.model
	AND n.specification = b.specification
	AND n.unit = b.unit
)
WHERE
	n.id > 10000
AND n.id < 15001