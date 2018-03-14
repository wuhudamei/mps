UPDATE biz_order_checksize a,
(SELECT a.id,a.order_id,b.value,b.label,c.store_id,c.project_mode,d.id AS "aabb",d.install_item_name
FROM biz_order_checksize a 
LEFT JOIN sys_dict b ON a.checksize_type = b.value
LEFT JOIN biz_order c ON c.id = a.order_id
INNER JOIN biz_project_install_item d ON d.store_id = c.store_id AND c.project_mode = d.project_mode AND d.install_item_name LIKE CONCAT('%',b.label,'%')
WHERE b.type = 'check_scale')b
SET a.order_install_item_id = b.aabb WHERE b.id = a.id;

UPDATE biz_order_checksize a,
(SELECT a.id AS 'aid',b.id AS 'bid' FROM biz_order_checksize a 
LEFT JOIN biz_order_install_item b ON a.order_id = b.order_id AND a.order_install_item_id = b.project_install_item_id) b 
SET a.order_install_item_id = b.bid
WHERE a.id = b.aid;
