


drop table if exists biz_project_progress_summary_data;
create table biz_project_progress_summary_data
(
   id                   int(11) not null auto_increment,
   order_id             int(11) not null comment '����id',
   customer_accept_delay_days varchar(10) comment '�ͻ��Ͽ���������',
   is_actual_startwork_customer_signature varchar(10) comment 'ʵ�ʿ����ͻ��Ƿ�ǩ��',
   is_customer_signature varchar(10) comment '�ͻ��Ƿ�ǩ��',
   other_factor_delay_days varchar(10) comment '���/ͣ��/ͣˮ/�ͻ���װ��Ŀ��������',
   
   
   security_door_measure_plan_submit_date varchar(20) comment '�����ź˳�_�ƻ��ᱨ����',
   security_door_measure_actual_submit_time varchar(20) comment '�����ź˳�_ʵ���ᱨʱ��',
   security_door_measure_submit_dealy_days varchar(20) comment '�����ź˳�_�ᱨ��������',
   security_door_measure_expect_arrival_date varchar(20) comment '�����ź˳�_������������',
   security_door_measure_actual_arrival_time varchar(20) comment '�����ź˳�_ʵ�ʽ���ʱ��',
   security_door_measure_arrival_delay_days varchar(20) comment '�����ź˳�_������������',
   security_door_measure_plan_complete_date varchar(20) comment '�����ź˳�_�ƻ��������',
   security_door_measure_actual_complete_time varchar(20) comment '�����ź˳�_ʵ�����ʱ��',
   security_door_measure_complete_delay_days varchar(20) comment '�����ź˳�_����/����/��ǰ����',
   
   
   auxiliary_materials_arrival_plan_submit_date varchar(20) comment '���Ľ���_�ƻ��ᱨ����',
   auxiliary_materials_arrival_actual_submit_time varchar(20) comment '���Ľ���_ʵ���ᱨʱ��',
   auxiliary_materials_arrival_submit_delay_days varchar(20) comment '���Ľ���_�ᱨ��������',
   auxiliary_materials_arrival_expect_arrival_date varchar(20) comment '���Ľ���_������������',
   auxiliary_materials_arrival_actual_arrival_time varchar(20) comment '���Ľ���_ʵ�ʽ���ʱ��',
   auxiliary_materials_arrival_arrival_delay_days varchar(20) comment '���Ľ���_������������',
   auxiliary_materials_arrival_plan_complete_date varchar(20) comment '���Ľ���_�ƻ��������',
   auxiliary_materials_arrival_actual_receive_time varchar(20) comment '���Ľ���_ʵ���ջ�ʱ��',
   auxiliary_materials_arrival_receive_delay_days varchar(20) comment '���Ľ���_����/����/��ǰ����',
   
   
   waterway_circuit_concealment_plan_submit_date varchar(20) comment 'ˮ������_�ƻ��ᱨ����',
   waterway_circuit_concealment_actual_submit_time varchar(20) comment 'ˮ������_ʵ���ᱨʱ��',
   waterway_circuit_concealment_submit_delay_days varchar(20) comment 'ˮ������_�ᱨ��������',
   waterway_circuit_concealment_expect_arrival_date varchar(20) comment 'ˮ������_������������',
   waterway_circuit_concealment_actual_arrival_time varchar(20) comment 'ˮ������_�ʼ���������',
   waterway_circuit_concealment_arrival_delay_days varchar(20) comment 'ˮ������_������������',
   waterway_circuit_concealment_plan_complete_date varchar(20) comment 'ˮ������_�ƻ���������',
   waterway_circuit_concealment_actual_receive_time varchar(20) comment 'ˮ������_ʵ�����պϸ�����',
   waterway_circuit_concealment_receive_delay_days varchar(20) comment 'ˮ������_����/����/��ǰ����',
   
   
   ceramic_tile_plan_submit_date varchar(20) comment '��ש_�ƻ��ᱨ����',
   ceramic_tile_actual_submit_time varchar(20) comment '��ש_ʵ���ᱨʱ��',
   ceramic_tile_submit_delay_days varchar(20) comment '��ש_�ᱨ��������',
   ceramic_tile_expect_arrival_date varchar(20) comment '��ש_������������',
   ceramic_tile_actual_arrival_time varchar(20) comment '��ש_ʵ�ʽ���ʱ��',
   ceramic_tile_arrival_delay_days varchar(20) comment '��ש_������������',
   ceramic_tile_plan_complete_date varchar(20) comment '��ש_�ƻ��������',
   ceramic_tile_actual_receive_time varchar(20) comment '��ש_ʵ���ջ�ʱ��',
   ceramic_tile_receive_delay_days varchar(20) comment '��ש_����/����/��ǰ����',
   
   
   waterproof_accept_plan_submit_date varchar(20) comment '��ˮ����_�ƻ��ᱨ����',
   waterproof_accept_actual_submit_time varchar(20) comment '��ˮ����_ʵ���ᱨʱ��',
   waterproof_accept_submit_delay_days varchar(20) comment '��ˮ����_�ᱨ��������',
   waterproof_accept_expect_arrival_date varchar(20) comment '��ˮ����_������������',
   waterproof_accept_actual_arrival_time varchar(20) comment '��ˮ����_ʵ�ʽ���ʱ��',
   waterproof_accept_arrival_delay_days varchar(20) comment '��ˮ����_������������',
   waterproof_accept_plan_complete_date varchar(20) comment '��ˮ����_�ƻ��������',
   waterproof_accept_actual_receive_time varchar(20) comment '��ˮ����_ʵ���ջ�ʱ��',
   waterproof_accept_receive_delay_days varchar(20) comment '��ˮ����_����/����/��ǰ����',
   
   
   bricklayer_accept_plan_submit_date varchar(20) comment '�߹�����_�ƻ��ᱨ����',
   bricklayer_accept_actual_submit_time varchar(20) comment '�߹�����_ʵ���ᱨʱ��',
   bricklayer_accept_submit_delay_days varchar(20) comment '�߹�����_�ᱨ��������',
   bricklayer_accept_expect_arrival_date varchar(20) comment '�߹�����_������������',
   bricklayer_accept_actual_arrival_time varchar(20) comment '�߹�����_�ʼ���������',
   bricklayer_accept_arrival_delay_days varchar(20) comment '�߹�����_������������',
   bricklayer_accept_plan_complete_date varchar(20) comment '�߹�����_�ƻ���������',
   bricklayer_accept_actual_receive_time varchar(20) comment '�߹�����_ʵ�����պϸ�ʱ��',
   bricklayer_accept_receive_delay_days varchar(20) comment '�߹�����_����/����/��ǰ����',
   
   
   two_period_plan_submit_date varchar(20) comment '���ڿ�_�ƻ��ɿ�����',
   two_period_urge_date varchar(20) comment '���ڿ�_�߿�����',
   two_period_actual_submit_time varchar(20) comment '���ڿ�_ʵ�ʽɿ�����',
   two_period_delay_days varchar(20) comment '���ڿ�_����/����/��ǰ����',
   
   
   
   base_construct_accept_plan_submit_date varchar(20) comment '����ʩ������_�ƻ��ᱨ����',
   base_construct_accept_actual_submit_time varchar(20) comment '����ʩ������_ʵ���ᱨʱ��',
   base_construct_accept_submit_delay_days varchar(20) comment '����ʩ������_�ᱨ��������',
   base_construct_accept_expect_arrival_date varchar(20) comment '����ʩ������_������������',
   base_construct_accept_actual_arrival_time varchar(20) comment '����ʩ������_�ʼ���������',
   base_construct_accept_arrival_delay_days varchar(20) comment '����ʩ������_������������',
   base_construct_accept_plan_complete_date varchar(20) comment '����ʩ������_�ƻ���������',
   base_construct_accept_actual_receive_time varchar(20) comment '����ʩ������_ʵ�����պϸ�����',
   base_construct_accept_receive_delay_days varchar(20) comment '����ʩ������_����/����/��ǰ����',
   
   
   kitchen_hang_ceiling_plan_submit_date varchar(20) comment '��������_�ƻ��ᱨ����',
   kitchen_hang_ceiling_actual_submit_time varchar(20) comment '��������_ʵ���ᱨʱ��',
   kitchen_hang_ceiling_submit_delay_days varchar(20) comment '��������_�ᱨ��������',
   kitchen_hang_ceiling_expect_arrival_date varchar(20) comment '��������_������������',
   kitchen_hang_ceiling_actual_arrival_time varchar(20) comment '��������_ʵ�ʽ���ʱ��',
   kitchen_hang_ceiling_arrival_delay_days varchar(20) comment '��������_������������',
   kitchen_hang_ceiling_plan_complete_date varchar(20) comment '��������_�ƻ��������',
   kitchen_hang_ceiling_unqualified_receive_time varchar(20) comment '��������_������ղ��ϸ�ʱ��',
   kitchen_hang_ceiling_actual_receive_time varchar(20) comment '��������_ʵ���������',
   kitchen_hang_ceiling_qualified_receive_time varchar(20) comment '��������_ʵ�����պϸ�����',
   kitchen_hang_ceiling_receive_delay_days varchar(20) comment '��������_����/����/��ǰ����',
   
   
   
   sanitary_appliance_plan_submit_date varchar(20) comment '���_�ƻ��ᱨ����',
   sanitary_appliance_actual_submit_time varchar(20) comment '���_ʵ���ᱨʱ��',
   sanitary_appliance_submit_delay_days varchar(20) comment '���_�ᱨ��������',
   sanitary_appliance_expect_arrival_date varchar(20) comment '���_������������',
   sanitary_appliance_actual_arrival_time varchar(20) comment '���_ʵ�ʽ���ʱ��',
   sanitary_appliance_arrival_delay_days varchar(20) comment '���_������������',
   sanitary_appliance_plan_complete_date varchar(20) comment '���_�ƻ��������',
   sanitary_appliance_unqualified_receive_time varchar(20) comment '���_������ղ��ϸ�ʱ��',  
   sanitary_appliance_actual_complete_time varchar(20) comment '���_ʵ���������',
   sanitary_appliance_qualified_receive_time varchar(20) comment '���_ʵ�����պϸ�����', 
   sanitary_appliance_complete_delay_days varchar(20) comment '���_����/����/��ǰ����',
   
   
   
   
   wjdjkgmb_plan_submit_date varchar(20) comment '���ƾ߿������_�ƻ��ᱨ����',
   wjdjkgmb_actual_submit_time varchar(20) comment '���ƾ߿������_ʵ���ᱨʱ��',
   wjdjkgmb_submit_delay_days varchar(20) comment '���ƾ߿������_�ᱨ��������',
   wjdjkgmb_expect_arrival_date varchar(20) comment '���ƾ߿������_������������',
   wjdjkgmb_actual_arrival_time varchar(20) comment '���ƾ߿������_ʵ�ʽ���ʱ��',
   wjdjkgmb_arrival_delay_days varchar(20) comment '���ƾ߿������_������������',
   wjdjkgmb_plan_complete_date varchar(20) comment '���ƾ߿������_�ƻ��������',
   wjdjkgmb_unqualified_receive_time varchar(20) comment '���ƾ߿������_������ղ��ϸ�ʱ��', 
   wjdjkgmb_actual_complete_time varchar(20) comment '���ƾ߿������_ʵ���������',
   wjdjkgmb_qualified_receive_time varchar(20) comment '���ƾ߿������_ʵ�����պϸ�����', 
   wjdjkgmb_complete_delay_days varchar(20) comment '���ƾ߿������_����/����/��ǰ����',
  
   
   
   
   cupboard_plan_submit_date varchar(20) comment '����_�ƻ��ᱨ����',
   cupboard_actual_submit_time varchar(20) comment '����_ʵ���ᱨʱ��',
   cupboard_submit_delay_days varchar(20) comment '����_�ᱨ��������',
   cupboard_plan_apply_measure_date varchar(20) comment '����_�ƻ����븴������',
   cupboard_actual_apply_measure_time varchar(20) comment '����_ʵ�����븴������',
   cupboard_apply_measure_delay_days varchar(20) comment '����_���븴����������',
   cupboard_expect_arrival_measure_date varchar(20) comment '����_����������������',
   cupboard_actual_arrival_measure_time varchar(20) comment '����_ʵ�ʽ�����������',
   cupboard_arrival_measure_delay_days varchar(20) comment '����_����������������',
   cupboard_expect_arrival_date varchar(20) comment '����_������������',
   cupboard_actual_arrival_time varchar(20) comment '����_ʵ�ʽ���ʱ��',
   cupboard_arrival_delay_days varchar(20) comment '����_������������',
   cupboard_plan_complete_date varchar(20) comment '����_�ƻ��������',
   cupboard_unqualified_receive_time varchar(20) comment '����_������ղ��ϸ�ʱ��',
   cupboard_actual_complete_date varchar(20) comment '����_ʵ���������',
   cupboard_qualified_receive_time varchar(20) comment '����_ʵ�����պϸ�����',
   cupboard_complete_delay_days varchar(20) comment '����_����/����/��ǰ����',

   
   custom_wardrobe_plan_submit_date varchar(20) comment '�����¹�_�ƻ��ᱨ����',
   custom_wardrobe_actual_submit_time varchar(20) comment '�����¹�_ʵ���ᱨʱ��',
   custom_wardrobe_submit_delay_days varchar(20) comment '�����¹�_�ᱨ��������',
   custom_wardrobe_plan_apply_measure_date varchar(20) comment '�����¹�_�ƻ����븴������',
   custom_wardrobe_actual_apply_measure_time varchar(20) comment '�����¹�_ʵ�����븴��ʱ��',
   custom_wardrobe_apply_measure_delay_days varchar(20) comment '�����¹�_���븴����������',
   custom_wardrobe_expect_arrival_measure_date varchar(20) comment '�����¹�_����������������',
   custom_wardrobe_actual_arrival_measure_time varchar(20) comment '�����¹�_ʵ�ʽ�����������',
   custom_wardrobe_arrival_measure_delay_days varchar(20) comment '�����¹�_����������������',
   custom_wardrobe_expect_arrival_date varchar(20) comment '�����¹�_������������',
   custom_wardrobe_actual_arrival_time varchar(20) comment '�����¹�_ʵ�ʽ���ʱ��',
   custom_wardrobe_arrival_delay_days varchar(20) comment '�����¹�_������������',
   custom_wardrobe_plan_complete_date varchar(20) comment '�����¹�_�ƻ��������',
   custom_wardrobe_unqualified_receive_time varchar(20) comment '�����¹�_������ղ��ϸ�ʱ��',
   custom_wardrobe_actual_complete_date varchar(20) comment '�����¹�_ʵ���������',
   custom_wardrobe_qualified_receive_time varchar(20) comment '�����¹�_ʵ�����պϸ�����',
   custom_wardrobe_complete_delay_days varchar(20) comment '�����¹�_����/����/��ǰ����',
	
   
   wallpaper_plan_submit_date varchar(20) comment '��ֽ_�ƻ��ᱨ����',
   wallpaper_actual_submit_time varchar(20) comment '��ֽ_ʵ���ᱨʱ��',
   wallpaper_submit_delay_days varchar(20) comment '��ֽ_�ᱨ��������',
   wallpaper_expect_arrival_date varchar(20) comment '��ֽ_������������',
   wallpaper_actual_arrival_time varchar(20) comment '��ֽ_ʵ�ʽ���ʱ��',
   wallpaper_arrival_delay_days varchar(20) comment '��ֽ_������������',
   wallpaper_plan_complete_date varchar(20) comment '��ֽ_�ƻ��������',
   wallpaper_unqualified_receive_time varchar(20) comment '��ֽ_������ղ��ϸ�ʱ��',
   wallpaper_actual_complete_date varchar(20) comment '��ֽ_ʵ���������',
   wallpaper_qualified_receive_time varchar(20) comment '��ֽ_ʵ�����պϸ�����',
   wallpaper_complete_delay_days varchar(20) comment '��ֽ_����/����/��ǰ����',
   
   
   
   
   door_plan_submit_date varchar(20) comment '��_�ƻ��ᱨ����',
   door_actual_submit_time varchar(20) comment '��_ʵ���ᱨʱ��',
   door_submit_delay_days varchar(20) comment '��_�ᱨ��������',
   door_plan_apply_measure_date varchar(20) comment '��_�ƻ����븴������',
   door_actual_apply_measure_time varchar(20) comment '��_ʵ�����븴��ʱ��',
   door_apply_measure_delay_days varchar(20) comment '��_���븴����������',
   door_expect_arrival_measure_date varchar(20) comment '��_����������������',
   door_actual_arrival_measure_time varchar(20) comment '��_ʵ�ʽ�����������',
   door_arrival_measure_delay_days varchar(20) comment '��_����������������',
   door_expect_arrival_date varchar(20) comment '��_������������',
   door_actual_arrival_time varchar(20) comment '��_ʵ�ʽ���ʱ��',
   door_arrival_delay_days varchar(20) comment '��_������������',
   door_plan_complete_date varchar(20) comment '��_�ƻ��������',
   door_unqualified_receive_time varchar(20) comment '��_������ղ��ϸ�ʱ��',
   door_actual_complete_date varchar(20) comment '��_ʵ���������',
   door_qualified_receive_time varchar(20) comment '��_ʵ�����պϸ�����',
   door_complete_delay_days varchar(20) comment '��_����/����/��ǰ����',
   
   
   
   
   wood_floor_plan_submit_date varchar(20) comment 'ľ�ذ�_�ƻ��ᱨ����',
   wood_floor_actual_submit_time varchar(20) comment 'ľ�ذ�_ʵ���ᱨʱ��',
   wood_floor_submit_delay_days varchar(20) comment 'ľ�ذ�_�ᱨ��������',
   wood_floor_expect_arrival_date varchar(20) comment 'ľ�ذ�_������������',
   wood_floor_actual_arrival_time varchar(20) comment 'ľ�ذ�_ʵ�ʽ���ʱ��',
   wood_floor_arrival_delay_days varchar(20) comment 'ľ�ذ�_������������',
   wood_floor_plan_complete_date varchar(20) comment 'ľ�ذ�_�ƻ��������',
   wood_floor_unqualified_receive_time varchar(20) comment 'ľ�ذ�_������ղ��ϸ�ʱ��',
   wood_floor_actual_complete_date varchar(20) comment 'ľ�ذ�_ʵ���������',
   wood_floor_qualified_receive_time varchar(20) comment 'ľ�ذ�_ʵ�����պϸ�����',
   wood_floor_complete_delay_days varchar(20) comment 'ľ�ذ�_����/����/��ǰ����',
   
   
   
   
   window_curtains_plan_submit_date varchar(20) comment '����_�ƻ��ᱨ����',
   window_curtains_actual_submit_time varchar(20) comment '����_ʵ���ᱨʱ��',
   window_curtains_submit_delay_days varchar(20) comment '����_�ᱨ��������',
   window_curtains_plan_apply_measure_date varchar(20) comment '����_�ƻ����븴������',
   window_curtains_actual_apply_measure_time varchar(20) comment '����_ʵ�����븴������',
   window_curtains_apply_measure_delay_days varchar(20) comment '����_���븴����������',
   window_curtains_expect_arrival_measure_date varchar(20) comment '����_����������������',
   window_curtains_actual_arrival_measure_time varchar(20) comment '����_ʵ�ʽ�������ʱ��',
   window_curtains_arrival_measure_delay_days varchar(20) comment '����_����������������',
   window_curtains_expect_arrival_date varchar(20) comment '����_������������',
   window_curtains_actual_arrival_time varchar(20) comment '����_ʵ�ʽ���ʱ��',
   window_curtains_arrival_delay_days varchar(20) comment '����_������������',
   window_curtains_plan_complete_date varchar(20) comment '����_�ƻ��������',
   window_curtains_unqualified_receive_time varchar(20) comment '����_������ղ��ϸ�ʱ��',
   window_curtains_actual_complete_date varchar(20) comment '����_ʵ���������',
   window_curtains_qualified_receive_time varchar(20) comment '����_ʵ�����պϸ�����',
   window_curtains_complete_delay_days varchar(20) comment '����_����/����/��ǰ����',
   
   
   
   
   completed_accept_plan_submit_date varchar(20) comment '��������_�ƻ��ᱨ����',
   completed_accept_actual_submit_time varchar(20) comment '��������_ʵ���ᱨʱ��',
   completed_accept_submit_delay_days varchar(20) comment '��������_�ᱨ��������',
   completed_accept_expect_accept_date varchar(20) comment '��������_������������',
   completed_accept_quality_arrival_time varchar(20) comment '��������_�ʼ���������',
   completed_accept_arrival_delay_days varchar(20) comment '��������_������������',
   completed_accept_plan_accept_date varchar(20) comment '��������_�ƻ���������',
   completed_accept_qualified_receive_time varchar(20) comment '��������_ʵ�����պϸ�����',
   completed_accept_delay_days varchar(20) comment '��������_����/����/��ǰ����',
   
   
   final_period_plan_submit_date varchar(20) comment 'β��_�ƻ��ɿ�����',
   final_period_actual_submit_time varchar(20) comment 'β��_ʵ�ʽɿ�����',
   final_period_delay_days varchar(20) comment 'β��_����/����/��ǰ����',
   
   
   domestic_appliance_plan_submit_date varchar(20) comment '�ҵ�_�ƻ��ᱨ����',
   domestic_appliance_actual_submit_time varchar(20) comment '�ҵ�_ʵ���ᱨʱ��',
   domestic_appliance_submit_delay_days varchar(20) comment '�ҵ�_�ᱨ��������',
   domestic_appliance_expect_arrival_date varchar(20) comment '�ҵ�_������������',
   domestic_appliance_actual_arrival_time varchar(20) comment '�ҵ�_ʵ�ʽ���ʱ��',
   domestic_appliance_arrival_delay_days varchar(20) comment '�ҵ�_������������',
   domestic_appliance_plan_complete_date varchar(20) comment '�ҵ�_�ƻ��������',
   domestic_appliance_actual_complete_date varchar(20) comment '�ҵ�_ʵ���������',
   domestic_appliance_qualified_receive_time varchar(20) comment '�ҵ�_ʵ�����պϸ�����',
   domestic_appliance_complete_delay_days varchar(20) comment '�ҵ�_����/����/��ǰ����',
   
   
   furniture_plan_submit_date varchar(20) comment '�Ҿ�_�ƻ��ᱨ����',
   furniture_actual_submit_time varchar(20) comment '�Ҿ�_ʵ���ᱨʱ��',
   furniture_submit_delay_days varchar(20) comment '�Ҿ�_�ᱨ��������',
   furniture_expect_arrival_date varchar(20) comment '�Ҿ�_������������',
   furniture_actual_arrival_time varchar(20) comment '�Ҿ�_ʵ�ʽ���ʱ��',
   furniture_arrival_delay_days varchar(20) comment '�Ҿ�_������������',
   furniture_plan_complete_date varchar(20) comment '�Ҿ�_�ƻ��������',
   furniture_actual_complete_date varchar(20) comment '�Ҿ�_ʵ���������',
   furniture_qualified_receive_time varchar(20) comment '�Ҿ�_ʵ�����պϸ�����',
   furniture_complete_delay_days varchar(20) comment '�Ҿ�_����/����/��ǰ����',
   
   
   create_by            varchar(64) comment '������',
   create_time          datetime comment '����ʱ��',
   update_by            varchar(64) comment '�޸���',
   update_time          datetime comment '�޸�ʱ��',
   del_flag             char(1) comment 'ɾ�����',
   remarks              varchar(20) comment '��ע',
   update_status        char(1) comment '����״̬(1�����³ɹ���2������ʧ��)',
   primary key (id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='�󿴰嶩�����Ȼ�������';

