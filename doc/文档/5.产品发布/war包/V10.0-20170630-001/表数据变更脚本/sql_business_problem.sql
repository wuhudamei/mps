INSERT INTO biz_cus_service_important_degree (
	important_degree_code,
	important_degree_name ,father_important_degree_code,
	important_degree_level
)
VALUES
	('89', '��ѯ', '0', '1');

INSERT INTO biz_cus_service_important_degree (
		important_degree_code,
	important_degree_name ,father_important_degree_code,
	important_degree_level
)
VALUES
	('90', 'Ͷ��', '0', '1');

INSERT INTO biz_cus_service_important_degree (
			important_degree_code,
	important_degree_name ,father_important_degree_code,
	important_degree_level
)
VALUES
	('91', '�ط�', '0', '1'),

	('92', '�ط���ѯ', '91','2'),
	(
		'93',
		'�ط�Ͷ��',
		'91',
		'2'
	),
	(
		'94',
		'������ѯ',
		'89',
		'2'
	),
	(
		'95',
		'������ѯ',
		'89',
		'2'
	),
	(
		'96',
		'����Ͷ��',
		'90',
		'2'
	),
	(
		'97',
		'�������',
		'90',
		'2'
	),
	(
		'98',
		'����Ͷ��',
		'90',
		'2'
	),
	(
		'99',
		'����Ͷ��',
		'90',
		'2'
	),
	(
		'100',
		'ý���ع�',
		'90',
		'2'
	),
	(
		'101',
		'���ڻط�δִ��1',
		'90',
		'2'
	),
	(
		'102',
		'���ڻط�δִ��2',
		'90',
		'2'
	),
	('103', '����', '90', '2');

