


drop table if exists biz_project_progress_summary_data;
create table biz_project_progress_summary_data
(
   id                   int(11) not null auto_increment,
   order_id             int(11) not null comment '订单id',
   customer_accept_delay_days varchar(10) comment '客户认可延期天数',
   is_actual_startwork_customer_signature varchar(10) comment '实际开工客户是否签字',
   is_customer_signature varchar(10) comment '客户是否签字',
   other_factor_delay_days varchar(10) comment '变更/停电/停水/客户自装项目延期天数',
   
   
   security_door_measure_plan_submit_date varchar(20) comment '防盗门核尺_计划提报日期',
   security_door_measure_actual_submit_time varchar(20) comment '防盗门核尺_实际提报时间',
   security_door_measure_submit_dealy_days varchar(20) comment '防盗门核尺_提报延期天数',
   security_door_measure_expect_arrival_date varchar(20) comment '防盗门核尺_期望进场日期',
   security_door_measure_actual_arrival_time varchar(20) comment '防盗门核尺_实际进场时间',
   security_door_measure_arrival_delay_days varchar(20) comment '防盗门核尺_进场延期天数',
   security_door_measure_plan_complete_date varchar(20) comment '防盗门核尺_计划完成日期',
   security_door_measure_actual_complete_time varchar(20) comment '防盗门核尺_实际完成时间',
   security_door_measure_complete_delay_days varchar(20) comment '防盗门核尺_正常/延期/提前天数',
   
   
   auxiliary_materials_arrival_plan_submit_date varchar(20) comment '辅材进场_计划提报日期',
   auxiliary_materials_arrival_actual_submit_time varchar(20) comment '辅材进场_实际提报时间',
   auxiliary_materials_arrival_submit_delay_days varchar(20) comment '辅材进场_提报延期天数',
   auxiliary_materials_arrival_expect_arrival_date varchar(20) comment '辅材进场_期望进场日期',
   auxiliary_materials_arrival_actual_arrival_time varchar(20) comment '辅材进场_实际进场时间',
   auxiliary_materials_arrival_arrival_delay_days varchar(20) comment '辅材进场_进场延期天数',
   auxiliary_materials_arrival_plan_complete_date varchar(20) comment '辅材进场_计划完成日期',
   auxiliary_materials_arrival_actual_receive_time varchar(20) comment '辅材进场_实际收货时间',
   auxiliary_materials_arrival_receive_delay_days varchar(20) comment '辅材进场_正常/延期/提前天数',
   
   
   waterway_circuit_concealment_plan_submit_date varchar(20) comment '水电隐蔽_计划提报日期',
   waterway_circuit_concealment_actual_submit_time varchar(20) comment '水电隐蔽_实际提报时间',
   waterway_circuit_concealment_submit_delay_days varchar(20) comment '水电隐蔽_提报延期天数',
   waterway_circuit_concealment_expect_arrival_date varchar(20) comment '水电隐蔽_期望验收日期',
   waterway_circuit_concealment_actual_arrival_time varchar(20) comment '水电隐蔽_质检上门日期',
   waterway_circuit_concealment_arrival_delay_days varchar(20) comment '水电隐蔽_上门延期天数',
   waterway_circuit_concealment_plan_complete_date varchar(20) comment '水电隐蔽_计划验收日期',
   waterway_circuit_concealment_actual_receive_time varchar(20) comment '水电隐蔽_实际验收合格日期',
   waterway_circuit_concealment_receive_delay_days varchar(20) comment '水电隐蔽_正常/延期/提前天数',
   
   
   ceramic_tile_plan_submit_date varchar(20) comment '瓷砖_计划提报日期',
   ceramic_tile_actual_submit_time varchar(20) comment '瓷砖_实际提报时间',
   ceramic_tile_submit_delay_days varchar(20) comment '瓷砖_提报延期天数',
   ceramic_tile_expect_arrival_date varchar(20) comment '瓷砖_期望进场日期',
   ceramic_tile_actual_arrival_time varchar(20) comment '瓷砖_实际进场时间',
   ceramic_tile_arrival_delay_days varchar(20) comment '瓷砖_进场延期天数',
   ceramic_tile_plan_complete_date varchar(20) comment '瓷砖_计划完成日期',
   ceramic_tile_actual_receive_time varchar(20) comment '瓷砖_实际收货时间',
   ceramic_tile_receive_delay_days varchar(20) comment '瓷砖_正常/延期/提前天数',
   
   
   waterproof_accept_plan_submit_date varchar(20) comment '防水验收_计划提报日期',
   waterproof_accept_actual_submit_time varchar(20) comment '防水验收_实际提报时间',
   waterproof_accept_submit_delay_days varchar(20) comment '防水验收_提报延期天数',
   waterproof_accept_expect_arrival_date varchar(20) comment '防水验收_期望进场日期',
   waterproof_accept_actual_arrival_time varchar(20) comment '防水验收_实际进场时间',
   waterproof_accept_arrival_delay_days varchar(20) comment '防水验收_进场延期天数',
   waterproof_accept_plan_complete_date varchar(20) comment '防水验收_计划完成日期',
   waterproof_accept_actual_receive_time varchar(20) comment '防水验收_实际收货时间',
   waterproof_accept_receive_delay_days varchar(20) comment '防水验收_正常/延期/提前天数',
   
   
   bricklayer_accept_plan_submit_date varchar(20) comment '瓦工验收_计划提报日期',
   bricklayer_accept_actual_submit_time varchar(20) comment '瓦工验收_实际提报时间',
   bricklayer_accept_submit_delay_days varchar(20) comment '瓦工验收_提报延期天数',
   bricklayer_accept_expect_arrival_date varchar(20) comment '瓦工验收_期望进场日期',
   bricklayer_accept_actual_arrival_time varchar(20) comment '瓦工验收_质检上门日期',
   bricklayer_accept_arrival_delay_days varchar(20) comment '瓦工验收_上门延期天数',
   bricklayer_accept_plan_complete_date varchar(20) comment '瓦工验收_计划验收日期',
   bricklayer_accept_actual_receive_time varchar(20) comment '瓦工验收_实际验收合格时间',
   bricklayer_accept_receive_delay_days varchar(20) comment '瓦工验收_正常/延期/提前天数',
   
   
   two_period_plan_submit_date varchar(20) comment '二期款_计划缴款日期',
   two_period_urge_date varchar(20) comment '二期款_催款日期',
   two_period_actual_submit_time varchar(20) comment '二期款_实际缴款日期',
   two_period_delay_days varchar(20) comment '二期款_正常/延期/提前天数',
   
   
   
   base_construct_accept_plan_submit_date varchar(20) comment '基础施工验收_计划提报日期',
   base_construct_accept_actual_submit_time varchar(20) comment '基础施工验收_实际提报时间',
   base_construct_accept_submit_delay_days varchar(20) comment '基础施工验收_提报延期天数',
   base_construct_accept_expect_arrival_date varchar(20) comment '基础施工验收_期望进场日期',
   base_construct_accept_actual_arrival_time varchar(20) comment '基础施工验收_质检上门日期',
   base_construct_accept_arrival_delay_days varchar(20) comment '基础施工验收_上门延期天数',
   base_construct_accept_plan_complete_date varchar(20) comment '基础施工验收_计划验收日期',
   base_construct_accept_actual_receive_time varchar(20) comment '基础施工验收_实际验收合格日期',
   base_construct_accept_receive_delay_days varchar(20) comment '基础施工验收_正常/延期/提前天数',
   
   
   kitchen_hang_ceiling_plan_submit_date varchar(20) comment '厨卫吊顶_计划提报日期',
   kitchen_hang_ceiling_actual_submit_time varchar(20) comment '厨卫吊顶_实际提报时间',
   kitchen_hang_ceiling_submit_delay_days varchar(20) comment '厨卫吊顶_提报延期天数',
   kitchen_hang_ceiling_expect_arrival_date varchar(20) comment '厨卫吊顶_期望进场日期',
   kitchen_hang_ceiling_actual_arrival_time varchar(20) comment '厨卫吊顶_实际进场时间',
   kitchen_hang_ceiling_arrival_delay_days varchar(20) comment '厨卫吊顶_进场延期天数',
   kitchen_hang_ceiling_plan_complete_date varchar(20) comment '厨卫吊顶_计划完成日期',
   kitchen_hang_ceiling_unqualified_receive_time varchar(20) comment '厨卫吊顶_最近验收不合格时间',
   kitchen_hang_ceiling_actual_receive_time varchar(20) comment '厨卫吊顶_实际完成日期',
   kitchen_hang_ceiling_qualified_receive_time varchar(20) comment '厨卫吊顶_实际验收合格日期',
   kitchen_hang_ceiling_receive_delay_days varchar(20) comment '厨卫吊顶_正常/延期/提前天数',
   
   
   
   sanitary_appliance_plan_submit_date varchar(20) comment '洁具_计划提报日期',
   sanitary_appliance_actual_submit_time varchar(20) comment '洁具_实际提报时间',
   sanitary_appliance_submit_delay_days varchar(20) comment '洁具_提报延期天数',
   sanitary_appliance_expect_arrival_date varchar(20) comment '洁具_期望进场日期',
   sanitary_appliance_actual_arrival_time varchar(20) comment '洁具_实际进场时间',
   sanitary_appliance_arrival_delay_days varchar(20) comment '洁具_进场延期天数',
   sanitary_appliance_plan_complete_date varchar(20) comment '洁具_计划完成日期',
   sanitary_appliance_unqualified_receive_time varchar(20) comment '洁具_最近验收不合格时间',  
   sanitary_appliance_actual_complete_time varchar(20) comment '洁具_实际完成日期',
   sanitary_appliance_qualified_receive_time varchar(20) comment '洁具_实际验收合格日期', 
   sanitary_appliance_complete_delay_days varchar(20) comment '洁具_正常/延期/提前天数',
   
   
   
   
   wjdjkgmb_plan_submit_date varchar(20) comment '五金灯具开关面板_计划提报日期',
   wjdjkgmb_actual_submit_time varchar(20) comment '五金灯具开关面板_实际提报时间',
   wjdjkgmb_submit_delay_days varchar(20) comment '五金灯具开关面板_提报延期天数',
   wjdjkgmb_expect_arrival_date varchar(20) comment '五金灯具开关面板_期望进场日期',
   wjdjkgmb_actual_arrival_time varchar(20) comment '五金灯具开关面板_实际进场时间',
   wjdjkgmb_arrival_delay_days varchar(20) comment '五金灯具开关面板_进场延期天数',
   wjdjkgmb_plan_complete_date varchar(20) comment '五金灯具开关面板_计划完成日期',
   wjdjkgmb_unqualified_receive_time varchar(20) comment '五金灯具开关面板_最近验收不合格时间', 
   wjdjkgmb_actual_complete_time varchar(20) comment '五金灯具开关面板_实际完成日期',
   wjdjkgmb_qualified_receive_time varchar(20) comment '五金灯具开关面板_实际验收合格日期', 
   wjdjkgmb_complete_delay_days varchar(20) comment '五金灯具开关面板_正常/延期/提前天数',
  
   
   
   
   cupboard_plan_submit_date varchar(20) comment '橱柜_计划提报日期',
   cupboard_actual_submit_time varchar(20) comment '橱柜_实际提报时间',
   cupboard_submit_delay_days varchar(20) comment '橱柜_提报延期天数',
   cupboard_plan_apply_measure_date varchar(20) comment '橱柜_计划申请复尺日期',
   cupboard_actual_apply_measure_time varchar(20) comment '橱柜_实际申请复尺日期',
   cupboard_apply_measure_delay_days varchar(20) comment '橱柜_申请复尺延期天数',
   cupboard_expect_arrival_measure_date varchar(20) comment '橱柜_期望进场复尺日期',
   cupboard_actual_arrival_measure_time varchar(20) comment '橱柜_实际进场复尺日期',
   cupboard_arrival_measure_delay_days varchar(20) comment '橱柜_进场复尺延期天数',
   cupboard_expect_arrival_date varchar(20) comment '橱柜_期望进场日期',
   cupboard_actual_arrival_time varchar(20) comment '橱柜_实际进场时间',
   cupboard_arrival_delay_days varchar(20) comment '橱柜_进场延期天数',
   cupboard_plan_complete_date varchar(20) comment '橱柜_计划完成日期',
   cupboard_unqualified_receive_time varchar(20) comment '橱柜_最近验收不合格时间',
   cupboard_actual_complete_date varchar(20) comment '橱柜_实际完成日期',
   cupboard_qualified_receive_time varchar(20) comment '橱柜_实际验收合格日期',
   cupboard_complete_delay_days varchar(20) comment '橱柜_正常/延期/提前天数',

   
   custom_wardrobe_plan_submit_date varchar(20) comment '定制衣柜_计划提报日期',
   custom_wardrobe_actual_submit_time varchar(20) comment '定制衣柜_实际提报时间',
   custom_wardrobe_submit_delay_days varchar(20) comment '定制衣柜_提报延期天数',
   custom_wardrobe_plan_apply_measure_date varchar(20) comment '定制衣柜_计划申请复尺日期',
   custom_wardrobe_actual_apply_measure_time varchar(20) comment '定制衣柜_实际申请复尺时间',
   custom_wardrobe_apply_measure_delay_days varchar(20) comment '定制衣柜_申请复尺延期天数',
   custom_wardrobe_expect_arrival_measure_date varchar(20) comment '定制衣柜_期望进场复尺日期',
   custom_wardrobe_actual_arrival_measure_time varchar(20) comment '定制衣柜_实际进场复尺日期',
   custom_wardrobe_arrival_measure_delay_days varchar(20) comment '定制衣柜_进场复尺延期天数',
   custom_wardrobe_expect_arrival_date varchar(20) comment '定制衣柜_期望进场日期',
   custom_wardrobe_actual_arrival_time varchar(20) comment '定制衣柜_实际进场时间',
   custom_wardrobe_arrival_delay_days varchar(20) comment '定制衣柜_进场延期天数',
   custom_wardrobe_plan_complete_date varchar(20) comment '定制衣柜_计划完成日期',
   custom_wardrobe_unqualified_receive_time varchar(20) comment '定制衣柜_最近验收不合格时间',
   custom_wardrobe_actual_complete_date varchar(20) comment '定制衣柜_实际完成日期',
   custom_wardrobe_qualified_receive_time varchar(20) comment '定制衣柜_实际验收合格日期',
   custom_wardrobe_complete_delay_days varchar(20) comment '定制衣柜_正常/延期/提前天数',
	
   
   wallpaper_plan_submit_date varchar(20) comment '壁纸_计划提报日期',
   wallpaper_actual_submit_time varchar(20) comment '壁纸_实际提报时间',
   wallpaper_submit_delay_days varchar(20) comment '壁纸_提报延期天数',
   wallpaper_expect_arrival_date varchar(20) comment '壁纸_期望进场日期',
   wallpaper_actual_arrival_time varchar(20) comment '壁纸_实际进场时间',
   wallpaper_arrival_delay_days varchar(20) comment '壁纸_进场延期天数',
   wallpaper_plan_complete_date varchar(20) comment '壁纸_计划完成日期',
   wallpaper_unqualified_receive_time varchar(20) comment '壁纸_最近验收不合格时间',
   wallpaper_actual_complete_date varchar(20) comment '壁纸_实际完成日期',
   wallpaper_qualified_receive_time varchar(20) comment '壁纸_实际验收合格日期',
   wallpaper_complete_delay_days varchar(20) comment '壁纸_正常/延期/提前天数',
   
   
   
   
   door_plan_submit_date varchar(20) comment '门_计划提报日期',
   door_actual_submit_time varchar(20) comment '门_实际提报时间',
   door_submit_delay_days varchar(20) comment '门_提报延期天数',
   door_plan_apply_measure_date varchar(20) comment '门_计划申请复尺日期',
   door_actual_apply_measure_time varchar(20) comment '门_实际申请复尺时间',
   door_apply_measure_delay_days varchar(20) comment '门_申请复尺延期天数',
   door_expect_arrival_measure_date varchar(20) comment '门_期望进场复尺日期',
   door_actual_arrival_measure_time varchar(20) comment '门_实际进场复尺日期',
   door_arrival_measure_delay_days varchar(20) comment '门_进场复尺延期天数',
   door_expect_arrival_date varchar(20) comment '门_期望进场日期',
   door_actual_arrival_time varchar(20) comment '门_实际进场时间',
   door_arrival_delay_days varchar(20) comment '门_进场延期天数',
   door_plan_complete_date varchar(20) comment '门_计划完成日期',
   door_unqualified_receive_time varchar(20) comment '门_最近验收不合格时间',
   door_actual_complete_date varchar(20) comment '门_实际完成日期',
   door_qualified_receive_time varchar(20) comment '门_实际验收合格日期',
   door_complete_delay_days varchar(20) comment '门_正常/延期/提前天数',
   
   
   
   
   wood_floor_plan_submit_date varchar(20) comment '木地板_计划提报日期',
   wood_floor_actual_submit_time varchar(20) comment '木地板_实际提报时间',
   wood_floor_submit_delay_days varchar(20) comment '木地板_提报延期天数',
   wood_floor_expect_arrival_date varchar(20) comment '木地板_期望进场日期',
   wood_floor_actual_arrival_time varchar(20) comment '木地板_实际进场时间',
   wood_floor_arrival_delay_days varchar(20) comment '木地板_进场延期天数',
   wood_floor_plan_complete_date varchar(20) comment '木地板_计划完成日期',
   wood_floor_unqualified_receive_time varchar(20) comment '木地板_最近验收不合格时间',
   wood_floor_actual_complete_date varchar(20) comment '木地板_实际完成日期',
   wood_floor_qualified_receive_time varchar(20) comment '木地板_实际验收合格日期',
   wood_floor_complete_delay_days varchar(20) comment '木地板_正常/延期/提前天数',
   
   
   
   
   window_curtains_plan_submit_date varchar(20) comment '窗帘_计划提报日期',
   window_curtains_actual_submit_time varchar(20) comment '窗帘_实际提报时间',
   window_curtains_submit_delay_days varchar(20) comment '窗帘_提报延期天数',
   window_curtains_plan_apply_measure_date varchar(20) comment '窗帘_计划申请复尺日期',
   window_curtains_actual_apply_measure_time varchar(20) comment '窗帘_实际申请复尺日期',
   window_curtains_apply_measure_delay_days varchar(20) comment '窗帘_申请复尺延期天数',
   window_curtains_expect_arrival_measure_date varchar(20) comment '窗帘_期望进场复尺日期',
   window_curtains_actual_arrival_measure_time varchar(20) comment '窗帘_实际进场复尺时间',
   window_curtains_arrival_measure_delay_days varchar(20) comment '窗帘_进场复尺延期天数',
   window_curtains_expect_arrival_date varchar(20) comment '窗帘_期望进场日期',
   window_curtains_actual_arrival_time varchar(20) comment '窗帘_实际进场时间',
   window_curtains_arrival_delay_days varchar(20) comment '窗帘_进场延期天数',
   window_curtains_plan_complete_date varchar(20) comment '窗帘_计划完成日期',
   window_curtains_unqualified_receive_time varchar(20) comment '窗帘_最近验收不合格时间',
   window_curtains_actual_complete_date varchar(20) comment '窗帘_实际完成日期',
   window_curtains_qualified_receive_time varchar(20) comment '窗帘_实际验收合格日期',
   window_curtains_complete_delay_days varchar(20) comment '窗帘_正常/延期/提前天数',
   
   
   
   
   completed_accept_plan_submit_date varchar(20) comment '竣工验收_计划提报日期',
   completed_accept_actual_submit_time varchar(20) comment '竣工验收_实际提报时间',
   completed_accept_submit_delay_days varchar(20) comment '竣工验收_提报延期天数',
   completed_accept_expect_accept_date varchar(20) comment '竣工验收_期望验收日期',
   completed_accept_quality_arrival_time varchar(20) comment '竣工验收_质检上门日期',
   completed_accept_arrival_delay_days varchar(20) comment '竣工验收_上门延期天数',
   completed_accept_plan_accept_date varchar(20) comment '竣工验收_计划验收日期',
   completed_accept_qualified_receive_time varchar(20) comment '竣工验收_实际验收合格日期',
   completed_accept_delay_days varchar(20) comment '竣工验收_正常/延期/提前天数',
   
   
   final_period_plan_submit_date varchar(20) comment '尾款_计划缴款日期',
   final_period_actual_submit_time varchar(20) comment '尾款_实际缴款日期',
   final_period_delay_days varchar(20) comment '尾款_正常/延期/提前天数',
   
   
   domestic_appliance_plan_submit_date varchar(20) comment '家电_计划提报日期',
   domestic_appliance_actual_submit_time varchar(20) comment '家电_实际提报时间',
   domestic_appliance_submit_delay_days varchar(20) comment '家电_提报延期天数',
   domestic_appliance_expect_arrival_date varchar(20) comment '家电_期望进场日期',
   domestic_appliance_actual_arrival_time varchar(20) comment '家电_实际进场时间',
   domestic_appliance_arrival_delay_days varchar(20) comment '家电_进场延期天数',
   domestic_appliance_plan_complete_date varchar(20) comment '家电_计划完成日期',
   domestic_appliance_actual_complete_date varchar(20) comment '家电_实际完成日期',
   domestic_appliance_qualified_receive_time varchar(20) comment '家电_实际验收合格日期',
   domestic_appliance_complete_delay_days varchar(20) comment '家电_正常/延期/提前天数',
   
   
   furniture_plan_submit_date varchar(20) comment '家具_计划提报日期',
   furniture_actual_submit_time varchar(20) comment '家具_实际提报时间',
   furniture_submit_delay_days varchar(20) comment '家具_提报延期天数',
   furniture_expect_arrival_date varchar(20) comment '家具_期望进场日期',
   furniture_actual_arrival_time varchar(20) comment '家具_实际进场时间',
   furniture_arrival_delay_days varchar(20) comment '家具_进场延期天数',
   furniture_plan_complete_date varchar(20) comment '家具_计划完成日期',
   furniture_actual_complete_date varchar(20) comment '家具_实际完成日期',
   furniture_qualified_receive_time varchar(20) comment '家具_实际验收合格日期',
   furniture_complete_delay_days varchar(20) comment '家具_正常/延期/提前天数',
   
   
   create_by            varchar(64) comment '创建人',
   create_time          datetime comment '创建时间',
   update_by            varchar(64) comment '修改人',
   update_time          datetime comment '修改时间',
   del_flag             char(1) comment '删除标记',
   remarks              varchar(20) comment '备注',
   update_status        char(1) comment '更新状态(1、更新成功；2、更新失败)',
   primary key (id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='大看板订单进度汇总数据';

