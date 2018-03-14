UPDATE biz_employee a,sys_user b SET a.sysUserId = b.id WHERE a.id = b.emp_id;
UPDATE biz_employee a,sys_user b SET b.phone = a.phone WHERE a.id = b.emp_id;