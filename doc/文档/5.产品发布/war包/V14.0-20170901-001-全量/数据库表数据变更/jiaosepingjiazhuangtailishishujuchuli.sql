update biz_eval_score_role set eval_status = '1' where id in (
select a.id from (
 (select b.id from biz_eval_score a
 left join  biz_eval_score_role b on a.id = b.eval_score_id
 where a.eval_status in (0,1) and b.eval_status is null and b.got_score > '0' and b.eval_role_type in (101,201,301))
 ) a
);



update biz_eval_score_role set eval_status = '0' where id in (
select a.id from (
 (select b.id from biz_eval_score a
 left join  biz_eval_score_role b on a.id = b.eval_score_id
 where a.eval_status in (0,1) and b.eval_status is null and b.got_score = '0' and b.eval_role_type in (101,201,301))
 ) a
);