UPDATE sys_dict a SET a.label ='水电隐蔽验收' WHERE a.type = 'delayed_stage' AND a.value IN (10,20,30,40);
UPDATE sys_dict a SET a.label ='防水验收' WHERE a.type = 'delayed_stage' AND a.value IN (50);
UPDATE sys_dict a SET a.label ='瓦工验收' WHERE a.type = 'delayed_stage' AND a.value IN (60);
UPDATE sys_dict a SET a.label ='基础施工验收' WHERE a.type = 'delayed_stage' AND a.value IN (70);
UPDATE sys_dict a SET a.label ='竣工验收' WHERE a.type = 'delayed_stage' AND a.value IN (80);

UPDATE biz_order_delay_bill a 
LEFT JOIN sys_dict c ON a.delay_bill_stage_status = c.value AND c.type = 'delayed_stage'
INNER JOIN biz_node_plan b ON a.order_id = b.order_id AND  b.node_name = c.label
SET a.delay_bill_stage_status = b.id;