UPDATE biz_materials_standard_receive_bill
SET receive_bill_type = 1
WHERE
	receive_bill_type IS NULL