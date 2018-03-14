UPDATE biz_order_taskpackage_settlement_detail set ensure_amount_datetime = create_date;

UPDATE biz_order_taskpackage_settlement a,
 (
 SELECT
  settlement_id,
  MAX(ensure_amount_datetime) as ensure_amount_datetime
 FROM
  biz_order_taskpackage_settlement_detail
 GROUP BY
  settlement_id
) b
SET a.ensure_amount_datetime = b.ensure_amount_datetime where a.id=b.settlement_id;
