UPDATE biz_node_plan a INNER JOIN biz_node_plan b SET a.plan_check_time = b.plan_done_date WHERE a.id = b.id;