INSERT INTO biz_cus_service_important_degree (
	important_degree_code,
	important_degree_name ,father_important_degree_code,
	important_degree_level
)
VALUES
	('89', '咨询', '0', '1');

INSERT INTO biz_cus_service_important_degree (
		important_degree_code,
	important_degree_name ,father_important_degree_code,
	important_degree_level
)
VALUES
	('90', '投诉', '0', '1');

INSERT INTO biz_cus_service_important_degree (
			important_degree_code,
	important_degree_name ,father_important_degree_code,
	important_degree_level
)
VALUES
	('91', '回访', '0', '1'),

	('92', '回访咨询', '91','2'),
	(
		'93',
		'回访投诉',
		'91',
		'2'
	),
	(
		'94',
		'来电咨询',
		'89',
		'2'
	),
	(
		'95',
		'上门咨询',
		'89',
		'2'
	),
	(
		'96',
		'来电投诉',
		'90',
		'2'
	),
	(
		'97',
		'多次来电',
		'90',
		'2'
	),
	(
		'98',
		'上门投诉',
		'90',
		'2'
	),
	(
		'99',
		'涉外投诉',
		'90',
		'2'
	),
	(
		'100',
		'媒体曝光',
		'90',
		'2'
	),
	(
		'101',
		'到期回访未执行1',
		'90',
		'2'
	),
	(
		'102',
		'到期回访未执行2',
		'90',
		'2'
	),
	('103', '其他', '90', '2');

