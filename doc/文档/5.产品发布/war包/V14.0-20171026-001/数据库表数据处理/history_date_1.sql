UPDATE biz_order_install_item a,biz_project_install_item b 
SET
	a.is_show_install_description = b.is_show_install_description,
	a.install_description = b.install_description
WHERE
	a.project_install_item_id = b.id
