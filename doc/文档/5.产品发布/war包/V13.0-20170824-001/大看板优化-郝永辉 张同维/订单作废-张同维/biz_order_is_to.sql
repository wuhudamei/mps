/*
更改大看板字段
*/

update biz_order a  set a.is_to_refresh_process_data=null  where  a.is_scrap='1' 