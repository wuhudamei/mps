
ALTER TABLE biz_assess_reward_punish ADD COLUMN general_remarks varchar(500) DEFAULT NULL COMMENT '总记录备注';
ALTER TABLE biz_assess_reward_punish ADD COLUMN detail_remarks varchar(500) DEFAULT NULL COMMENT '明细备注';
