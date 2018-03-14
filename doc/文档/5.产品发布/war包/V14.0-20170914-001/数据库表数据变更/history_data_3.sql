UPDATE biz_order_wall_floor_tile n
SET n.purchase_count = (
	SELECT
		IFNULL(SUM(b.apply_counta),0)
	FROM
		biz_purchase a
	INNER JOIN biz_purchase_wall_floor_tile b ON b.purchase_id = a.id
	WHERE
		a.order_id = n.order_id
	AND a.purchase_type = 5
	AND a.`status` != 21
	AND n.main_mate_type = b.main_mate_type
	AND (CASE WHEN (n.position IS NOT NULL OR b.position IS NOT NULL) THEN  n.position = b.position ELSE 1=1 END)
	AND (CASE WHEN (n.brand_combo IS NOT NULL OR b.brand_combo IS NOT NULL) THEN  n.brand_combo = b.brand_combo ELSE 1=1 END)
	AND (CASE WHEN (n.model IS NOT NULL OR b.model IS NOT NULL) THEN  n.model = b.model ELSE 1=1 END)
	AND (CASE WHEN (n.specification IS NOT NULL OR b.specification IS NOT NULL) THEN  n.specification = b.specification ELSE 1=1 END)
	AND (CASE WHEN (n.unit IS NOT NULL OR b.unit IS NOT NULL) THEN  n.unit = b.unit ELSE 1=1 END)
)
WHERE
	n.id > 10000
AND n.id < 15001