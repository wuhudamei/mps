

ALTER TABLE biz_pm_settle_bill ADD(
midway_inspection_reward_amount decimal(10,2) DEFAULT NULL COMMENT '中期巡检奖励',
midway_inspection_punish_amount decimal(10,2) DEFAULT NULL COMMENT '中期巡检奖励',
complete_inspection_reward_amount decimal(10,2) DEFAULT NULL COMMENT '竣工巡检奖励',
complete_inspection_punish_amount decimal(10,2) DEFAULT NULL COMMENT '竣工巡检罚款'
);

ALTER TABLE biz_pm_settle_summary_bill ADD(
midway_inspection_reward_amount decimal(10,2) DEFAULT NULL COMMENT '中期巡检奖励',
midway_inspection_punish_amount decimal(10,2) DEFAULT NULL COMMENT '中期巡检奖励',
complete_inspection_reward_amount decimal(10,2) DEFAULT NULL COMMENT '竣工巡检奖励',
complete_inspection_punish_amount decimal(10,2) DEFAULT NULL COMMENT '竣工巡检罚款'
);

ALTER TABLE biz_assess_rule_type ADD is_month_inspection  char(1) DEFAULT '0' COMMENT '是否月度巡检字段，1是、0否';
ALTER TABLE biz_assess_reward_punish ADD is_month_inspection  char(1) DEFAULT '0' COMMENT '是否月度巡检字段，1是、0否';