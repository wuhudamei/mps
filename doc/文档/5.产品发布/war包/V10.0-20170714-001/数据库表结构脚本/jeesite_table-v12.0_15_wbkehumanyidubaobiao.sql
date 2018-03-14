ALTER TABLE biz_customer_return_visit_content ADD statistics_department INT (2);

ALTER TABLE biz_customer_return_visit_record_answer ADD statistics_department INT (2),
 ADD statistics_result VARCHAR (50);

UPDATE biz_customer_return_visit_content
SET statistics_department = 0;