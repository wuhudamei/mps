update biz_pm_settle_category_detail a ,(
SELECT a.id,b.pm_punish_employee_id from biz_pm_settle_category_detail a LEFT JOIN biz_qc_bill_check_item b on a.related_business_id = b.id where a.pm_employee_id is NULL and a.settle_category = '4' and a.settle_role = '1'
) b set a.remarks="addPmId",a.pm_employee_id = b.pm_punish_employee_id where a.id = b.id;

UPDATE biz_pm_settle_category_summary a,(
SELECT order_id,pm_employee_id from biz_pm_settle_category_detail where remarks='addPmId' and settle_status = 30 and settle_amount < 0  GROUP BY order_id,pm_employee_id ORDER BY order_id
) b set a.pm_employee_id = b.pm_employee_id,a.remarks = "addPmId" where a.order_id = b.order_id and a.pm_employee_id is null and a.settle_amount <0;


UPDATE biz_pm_settle_bill a,(
SELECT order_id,pm_employee_id from biz_pm_settle_category_detail where remarks="addPmId" AND settle_amount <0 AND  settle_status=30 GROUP BY order_id,pm_employee_id ORDER BY order_id
) b set a.pm_employee_id  = b.pm_employee_id,a.remarks="addPmId"  where a.pm_employee_id IS NULL and (a.midway_qc_check_punish_amount <0 or a.complet_qc_check_punish_amount <0) and a.settle_role=1 AND a.order_id = b.order_id;



DELETE FROM biz_pm_settle_bill where pm_employee_id IS NULL and (midway_qc_check_punish_amount = 0 or complet_qc_check_punish_amount = 0 ) and total_amount = 0 ;

DELETE FROM biz_pm_settle_bill where id=4132;

DELETE FROM biz_pm_settle_category_summary where id =7283;

DELETE FROM biz_pm_settle_category_detail where id=2543;

#修改 订单201610174538 项目经理乔海雷的质检罚款
UPDATE biz_pm_settle_bill SET midway_qc_check_punish_amount = -150,total_amount=2129 where id = 3589;

#修改 订单201703121149 项目经理张明振的质检罚款
UPDATE biz_pm_settle_bill SET midway_qc_check_punish_amount = -250,total_amount=1264.5 where id = 3476;

#修改  订单201610232783 项目经理包诗旭的质检罚款
UPDATE biz_pm_settle_bill SET midway_qc_check_punish_amount = -390,total_amount=2176.9 where id = 3258;