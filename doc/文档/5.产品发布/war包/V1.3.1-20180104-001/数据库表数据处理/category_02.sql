UPDATE biz_materials_choice_category cc,biz_materials_choice_category bb
SET cc.parent_id = bb.id
WHERE
	cc.del_flag = 5
AND cc.del_flag = bb.del_flag
AND cc.category_level = 2
AND cc.store_id = bb.store_id
AND bb.category_level = 1
AND bb.category_code = CONCAT(cc.parent_id,'-')