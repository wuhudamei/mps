update biz_eval_score_role a left join biz_eval_score b on a.eval_score_id = b.id left join biz_order_taskpackage c on b.related_business_id = c.id left join biz_order d on d.id = c.order_id set a.eval_by_employee_id = d.order_inspector_id where a.eval_role_type in (201);

update biz_eval_score_role a set a.eval_cycle_hours = '12';