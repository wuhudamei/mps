ALTER TABLE biz_project_intem ADD(
using_platform VARCHAR(50) DEFAULT NULL COMMENT '使用平台',
valuation_method VARCHAR(50) DEFAULT NULL COMMENT '计价方式',
subordinate_category VARCHAR(50) DEFAULT NULL COMMENT '所属类别',
is_default CHAR(1) DEFAULT NULL COMMENT '是否默认项');


ALTER TABLE biz_project_intem_price 
ADD project_intem_cost_price  DECIMAL(10,2) DEFAULT NULL COMMENT '成本单价/成本占比';