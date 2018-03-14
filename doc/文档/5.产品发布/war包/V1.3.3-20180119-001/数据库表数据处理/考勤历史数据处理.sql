
 ALTER TABLE order_manager_snapshot MODIFY id INT AUTO_INCREMENT;
INSERT INTO order_manager_snapshot
            (order_id,
             manager_id,
             attend_month,
             create_by,
             create_date,
             update_by,
             update_date,
             del_flag)
            SELECT
              a.id,
              a.item_manager_id,
              DATE_FORMAT(DATE_ADD(NOW(),INTERVAL -1 MONTH),'%Y-%m'),
              1,
              NOW(),
              1,
              NOW(),
              0
            FROM biz_order a;
INSERT INTO order_manager_snapshot
            (order_id,
             manager_id,
             attend_month,
             create_by,
             create_date,
             update_by,
             update_date,
             del_flag)
            SELECT
              a.id,
              a.item_manager_id,
              DATE_FORMAT(DATE_ADD(NOW(),INTERVAL -2 MONTH),'%Y-%m'),
              1,
              NOW(),
              1,
              NOW(),
              0
            FROM biz_order a;
            INSERT INTO order_manager_snapshot
            (order_id,
             manager_id,
             attend_month,
             create_by,
             create_date,
             update_by,
             update_date,
             del_flag)
            SELECT
              a.id,
              a.item_manager_id,
              DATE_FORMAT(DATE_ADD(NOW(),INTERVAL -3 MONTH),'%Y-%m'),
              1,
              NOW(),
              1,
              NOW(),
              0
            FROM biz_order a;
            INSERT INTO order_manager_snapshot
            (order_id,
             manager_id,
             attend_month,
             create_by,
             create_date,
             update_by,
             update_date,
             del_flag)
            SELECT
              a.id,
              a.item_manager_id,
              DATE_FORMAT(DATE_ADD(NOW(),INTERVAL -4 MONTH),'%Y-%m'),
              1,
              NOW(),
              1,
              NOW(),
              0
            FROM biz_order a;
             INSERT INTO order_manager_snapshot
            (order_id,
             manager_id,
             attend_month,
             create_by,
             create_date,
             update_by,
             update_date,
             del_flag)
            SELECT
              a.id,
              a.item_manager_id,
              DATE_FORMAT(DATE_ADD(NOW(),INTERVAL -5 MONTH),'%Y-%m'),
              1,
              NOW(),
              1,
              NOW(),
              0
            FROM biz_order a;
			
			UPDATE order_manager_snapshot a LEFT JOIN 
(SELECT * FROM biz_order_distribute_log a WHERE a.id IN (SELECT MAX(a.id) FROM biz_order_distribute_log a WHERE a.distribute_type = '102' GROUP BY a.order_id,DATE_FORMAT(a.create_date,'%Y-%m')))
 b ON b.order_id = a.order_id AND a.attend_month = DATE_FORMAT(b.create_date,'%Y-%m') SET a.manager_id = b.distributed_employee_id;






