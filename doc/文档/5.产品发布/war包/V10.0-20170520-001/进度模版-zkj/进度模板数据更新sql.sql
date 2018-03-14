UPDATE biz_node_plan s INNER JOIN 
(
SELECT
 b.id,
 a.id AS 'nodePlanId'
FROM
 biz_node_plan a
LEFT JOIN biz_construction_schedule b ON b.sort = a.node_index
LEFT JOIN biz_order o ON a.order_id = o.id AND o.house_is_new = b.is_old_house AND o.store_id = b.store_id AND o.project_mode = b.project_mode
WHERE
a.construction_schedule_id IS NULL
) f ON s.id = f.nodePlanId
SET s.construction_schedule_id = f.id