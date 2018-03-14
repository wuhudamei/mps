package cn.damei.common.utils;

/**
 * 
 */
public class ConstantUtils {

	public static final String ORDERTASKPACKAGE_AUDIT_SUCCESS = "通过";// 任务包审核通过按钮

	public static final String ORDERTASKPACKAGE_ID_CREATE = "10";// 初始订单任务单状态
	public static final String ORDERTASKPACKAGE_ID_CREATE_REMARKS = "创建";// 已生成预算

	public static final String ORDERTASKPACKAGE_AUDIT_SUCCESS_20 = "20";
	public static final String ORDERTASKPACKAGE_AUDIT_SUCCESS_20_VALUES = "预算员审核通过";

	public static final String ORDERSTATUS_200_VALUE = "200";
	public static final String ORDERSTATUS_200_VALUE_REMARK = "施工中";

	public static final String ORDERSTATUS_100_VALUE = "100";
	public static final String ORDERSTATUS_100_VALUE_REMARK = "营销部已转单给工程部";

	public static final String ORDERSTATUS_101_VALUE = "101";
	public static final String ORDERSTATUS_101_VALUE_REMARK = "工程部退回订单";

	public static final String ORDERSTATUS_105_VALUE = "105";
	public static final String ORDERSTATUS_105_VALUE_REMARK = "工程部确认接收订单";

	public static final String ORDERSTATUS_110_VALUE = "110";
	public static final String ORDERSTATUS_110_VALUE_REMARK = "已生成预算";

	public static final String ORDERSTATUS_120_VALUE = "120";
	public static final String ORDERSTATUS_120_VALUE_REMARK = "已派项目经理";

	public static final String ORDERSTATUS_125_VALUE = "125";
	public static final String ORDERSTATUS_125_VALUE_REMARK = "已派质检员";

	public static final String ORDERSTATUS_130_VALUE = "130";
	public static final String ORDERSTATUS_130_VALUE_REMARK = "已现场交底";

	public static final String ORDERSTATUS_300_VALUE = "300";
	public static final String ORDERSTATUS_300_VALUE_REMARK = "项目经理已申请竣工";

	public static final String ORDERSTATUS_330_VALUE = "330";
	public static final String ORDERSTATUS_330_VALUE_REMARK = "结算员竣工审核不通过";

	public static final String ORDERSTATUS_340_VALUE = "340";
	public static final String ORDERSTATUS_340_VALUE_REMARK = "结算员竣工审核通过";

	public static final String ORDERSTATUS_400_VALUE = "400";
	public static final String ORDERSTATUS_400_VALUE_REMARK = "确认已竣工";

	/** 竣工状态维护 */
	public static final String ORDER_FINISH_PROJECT_BILL_STATUS_1 = "1";// 项目经理已申请
	public static final String ORDER_FINISH_PROJECT_BILL_STATUS_2 = "2";// 结算员已驳回
	public static final String ORDER_FINISH_PROJECT_BILL_STATUS_3 = "3";// 结算员已通过
	public static final String ORDER_FINISH_PROJECT_BILL_STATUS_4 = "4";// 已确认竣工

	public static final String ORDER_TASK_STATUS_80_VALUE = "80";
	public static final String ORDER_TASK_STATUS_80_VALUE_REMARK = "工人申请完工";
	public static final String ORDER_TASK_STATUS_90_VALUE = "90";
	public static final String ORDER_TASK_STATUS_90_VALUE_REMARK = "待质检复核结算单";
	public static final String ORDER_TASK_STATUS_95_VALUE = "95";
	public static final String ORDER_TASK_STATUS_95_VALUE_REMARK = "质检已复核";
	public static final String ORDER_TASK_STATUS_100_VALUE = "100";
	public static final String ORDER_TASK_STATUS_100_VALUE_REMARK = "已提交结算单且不超定额";
	public static final String ORDER_TASK_STATUS_105_VALUE = "105";
	public static final String ORDER_TASK_STATUS_105_VALUE_REMARK = "已提交结算单且已复核超定额";
	public static final String ORDER_TASK_STATUS_150_VALUE = "150";
	public static final String ORDER_TASK_STATUS_150_VALUE_REMARK = "已付首款";
	public static final String ORDER_TASK_STATUS_160_VALUE = "160";
	public static final String ORDER_TASK_STATUS_160_VALUE_REMARK = "付款完成";

	public static final String ORDERTASKPACKAGE_STATUS_NO = "0";// 任务包生成状态0:未生成
	public static final String ORDERTASKPACKAGE_STATUS_YES = "1";// 任务包生成状态1:已生成

	public static final String ORDERTASKPACKAGE_STATUS_NUMBER_110 = "110";// 已生成预算
	public static final String ORDERTASKPACKAGE_STATUS_NUMBER_110_REMARKS = "已生成预算";// 已生成预算

	// 任务包编号：RW2061609090001【时间戳八位数字+顺序码四位数字，按顺序生成；默认显示最大编号+1】系统生成不可重复。
	public static final String TASKPACKAGE_NUMBER = "RW";
	// 采购单编号：PO2061609090001【时间戳八位数字+顺序码四位数字，按顺序生成；默认显示最大编号+1】系统生成不可重复。
	public static final String PURCHASE_NUMBER = "PO";
	// 复检单编号：PO2061609090001【时间戳八位数字+顺序码四位数字，按顺序生成；默认显示最大编号+1】系统生成不可重复。
	public static final String RECHECK_NUMBER = "FJ";
	public static final String BROADCAST_NUMBER = "BB";
	// 申请竣工竣工单的编号规则：“JG”+年月日（四位）+四位流水号；比如：JG201611090001。
	public static final String APP_COMPLETED_STRING = "JG";
	// 安装单编号规则：“AZ”+年月日（8位）+四位流水号；例如：AN201707100001
	public static final String SUPPLIER_INSTALL_BILL_CODE = "AZ";
	// 施工单的编号规则：“SG”+年月日（四位）+四位流水号，例如：SG201707100001
	public static final String SUPPLIER_INSTALL_CONSTRUCT_BILL_CODE = "SG";
	public static final String APP_COMPLETED_INT = "9";

	// 复尺单的编号规则：FC+四位年月日+四位流水号（FC201611090001）
	public static final String APP_RECHECK_STRING = "FC";

	public static final String AUXILIARY_NUMBER = "1";// 辅材编号
	public static final String SWITCH_PANEL_NUMBER = "2";// 开关面板编号
	public static final String FLOOR_BRICK_NUMBER = "3";// 地砖 编号
	public static final String FLOOR_BRICK_NUMBER31 = "31";// 地砖踢脚线
	public static final String FLOOR_BRICK_NUMBER32 = "32";// 地砖/波导线
	public static final String FLOOR_BRICK_NUMBER33 = "33";// 其他瓷砖配件
	public static final String WALL_BRICK_NUMBER = "4";// 墙砖编号
	public static final String WALL_BRICK_NUMBER41 = "41";// 上墙砖编号
	public static final String WALL_BRICK_NUMBER42 = "42";// 下墙砖编号
	public static final String WALL_BRICK_NUMBER43 = "43";// 腰线
	public static final String WALL_BRICK_NUMBER44 = "44";// 转角
	public static final String WALL_BRICK_NUMBER45 = "45";// 花片
	public static final String WALL_BRICK_NUMBER46 = "46";// 角花
	public static final String WALL_FLOOR_BRICK_NUMBER = "5";// 墙地砖编号
	public static final String AUXILIARY_NUMBER_STRING = "辅材";// 辅材
	public static final String SWITCH_PANEL_NUMBER_STRING = "开关面板";// 开关面板
	public static final String FLOOR_BRICK_NUMBER_STRING = "地砖";// 地砖
	public static final String FLOOR_BRICK_NUMBER_STRING31 = "地砖踢脚线";// 地砖
	public static final String FLOOR_BRICK_NUMBER_STRING32 = "地线/波导线";// 地砖
	public static final String FLOOR_BRICK_NUMBER_STRING33 = "其它瓷砖配件";// 地砖
	public static final String WALL_BRICK_NUMBER_STRING = "墙砖";// 墙砖
	public static final String WALL_FLOOR_BRICK_NUMBER_STRING = "墙地砖";// 墙地砖
	public static final String WALL_FLOOR_BRICK_NUMBER_STRING41 = "上墙砖";// 上墙砖
	public static final String WALL_FLOOR_BRICK_NUMBER_STRING42 = "下墙砖";// 下墙砖
	public static final String WALL_FLOOR_BRICK_NUMBER_STRING43 = "腰线";//
	public static final String WALL_FLOOR_BRICK_NUMBER_STRING44 = "转角";//
	public static final String WALL_FLOOR_BRICK_NUMBER_STRING45 = "花片";//
	public static final String WALL_FLOOR_BRICK_NUMBER_STRING46 = "角花";//

	public static final String AUXILIARY_MATERIALS_CATEGORY = "1"; // 辅材
	public static final String MAIN_MATERIALS_CATEGORY = "2"; // 辅材

	public static final String SIGN_TYPE_MANAGER_START = "1"; // 开工签到-项目经理
	public static final String SIGN_TYPE_EMPLOYEE_START = "2"; // 开工签到-工人组长
	public static final String SIGN_TYPE_MANAGER_CHECK = "3"; // 验收签到-项目经理
	public static final String SIGN_TYPE_SCENE_SIGN_4 = "4"; // 拆改交底-项目经理签到
	public static final String SIGN_TYPE_INSPECTOR_CHECK_301 = "301"; // 约检签到-质检
	public static final String SIGN_TYPE_INSPECTOR_CHECK_302 = "302"; // 抽检签到-质检
	public static final String SIGN_TYPE_INSPECTOR_CHECK_303 = "303"; // 复检签到-质检

	public static final String EMP_TYPE_GROUPLEAD = "2";// 工人组长
	public static final String EMP_TYPE_MANAGER = "3";// 项目经理

	public static final String MEASUREMENT_UNIT_TYPE = "biz_unit";// 计量单位
	public static final String AUXILIARY_MEASUREMENT_UNIT_TYPE = "biz_material_unit";// 计量单位

	public static final String IS_QUALITY_GUARANTEE_YES = "1";// 是否扣除质保金 1-是
	public static final String IS_QUALITY_GUARANTEE_NO = "0";// 是否扣除质保金 0-否

	public static final String SUBMMIT_STATUS_YES = "YES";// 提交状态 --已付款为:YES
	public static final String SUBMMIT_STATUS_NO = "NO";// 提交状态 -- 未付款为:NO

	public static final String UPLOAD_DISCLOSE = "disclose";// 现场交底图片上传
	public static final String UPLOAD_CONFIRMSTART = "confirmstart";// 确认开工图片上传
	public static final String UPLOAD_PROGRESS = "nodeplan";// 进度通报图片上传
	public static final String UPLOAD_COMPLETED = "completed";// 申请竣工上传图片
	public static final String UPLOAD_ACCEPTANCE = "acceptance";// 安装项验收单上传图片
	public static final String UPLOAD = "upload";//文件根目录
	public static final String UPLOAD_CHECKITEM = "/upload/pic/pqc/check/checkItem/";// 质检
																						// 约检检查项目上传图片
	public static final String ORDER_COMPLINT = "/upload/pic/bizOrder/complaint/";// 工程问题上传图片
	public static final String UPLOAD_RECHECK = "/upload/pic/manager/check/recheck/";// 质检
																						// 复检
																						// 项目经理申请复检
																						// 上传图片
	public static final String UPLOAD_CONFIRM = "/upload/pic/pqc/check/confirm/";// 质检
																					// 约检检查项目确认验收上传图片
	public static final String UPLOAD_SELECTCHECKITEM = "/upload/pic/pqc/selectCheck/selectCheckItem/";// 质检
																										// 约检检查项目确认验收上传图片
	public static final String UPLOAD_BROADCAST_MANAGER = "/upload/pic/manager/broadcast/";// 项目经理确认展示的播报图片路径
	public static final String UPLOAD_PROBLEM_MANAGER = "/upload/pic/manager/install/problem/";// 项目经理问题上报图片
	public static final String UPLOAD_CKEDITOR = "/upload/pic/pc/ueditor/notice/";// pc端公告富文本编缉图片上传路径

	public static final String UPLOAD_CHECKSIZE = "/upload/pic/manager/check/progress/confirm/";// 项目经理端
																								// 进度通报
																								// 上报复尺
																								// 图片上传
	public static final String UPLOAD_WALLAPPLY = "/upload/pic/manager/materialManagement/wallApply/";// 项目经理端
																										// 材料管理
																										// 申请墙地砖
																										// 图片上传
	public static final String UPLOAD_SCRAP = "/upload/pic/modules/orderScrap/orderScrap/";// 订单作废上传图片

	public static final String UPLOAD_DEMAND = "/upload/pic/worker/doneApply/";// 工人端
																				// 申请完工
																				// 上传图片
	// public static final String UPLOAD_PURCHASE_RECEIVE =
	// "/uploadpic/pic/manager/receipt/";//项目经理 收货 上传图片
	public static final String UPLOAD_PURCHASE_RECEIVE = "/upload/pic/manager/receipt/";// 项目经理
																						// 收货
																						// 上传图片
	public static final String UPLOAD_PURCHASE_APPLY_OVER_SWITCHPANEL = "/upload/pic/manager/applySwitchPanel/";// 项目经理
																												// 申请开关面板超定额
																												// 上传图片

	public static final Integer SWITCH_PANEL_CATEGORY_ID = 24; // 开关面板分类id

	// 上传复尺的复尺内容
	public static final String CHECK_SCALE_1_VALUE = "1";
	public static final String CHECK_SCALE_1_VALUE_REMARK = "防盗门";
	public static final String CHECK_SCALE_2_VALUE = "2";
	public static final String CHECK_SCALE_2_VALUE_REMARK = "外窗";
	public static final String CHECK_SCALE_3_VALUE = "3";
	public static final String CHECK_SCALE_3_VALUE_REMARK = "室内门、移门及垭口";
	public static final String CHECK_SCALE_4_VALUE = "4";
	public static final String CHECK_SCALE_4_VALUE_REMARK = "橱柜";
	public static final String CHECK_SCALE_5_VALUE = "5";
	public static final String CHECK_SCALE_5_VALUE_REMARK = "定制柜";
	public static final String CHECK_SCALE_6_VALUE = "6";
	public static final String CHECK_SCALE_6_VALUE_REMARK = "窗帘";
	public static final String CHECK_SCALE_7_VALUE = "7";
	public static final String CHECK_SCALE_7_VALUE_REMARK = "软硬包";
	public static final String CHECK_SCALE_8_VALUE = "8";
	public static final String CHECK_SCALE_8_VALUE_REMARK = "窗台石";

	public static final String SETTLEMENT_DETAIL_STATUS_0 = "0";// 结算工人薪酬状态 --
																// 0:拒绝
	public static final String SETTLEMENT_DETAIL_STATUS_1 = "1";// 结算工人薪酬状态 --
																// 1:接受
	public static final String SETTLEMENT_DETAIL_STATUS_2 = "2";// 结算工人薪酬状态 --
																// 2:未处理

	public static final String PAYMENT_TYPE_0 = "0";// 付款单的类型 -- 0：首款
	public static final String PAYMENT_TYPE_1 = "1";// 付款单的类型 -- 1：尾款

	public static final String PAYMENT_DETAIL_STATUS_0 = "0";// 付款明细状态--0：未付
	public static final String PAYMENT_DETAIL_STATUS_1 = "1";// 付款明细状态--1：已付
	public static final String PAYMENT_DETAIL_STATUS_93 = "93";// 付款明细状态
																// --93：已撤回

	public static final String TASKPACKAGE_STATUS = "taskpackage_status";// 订单任务包类型

	public static final String PAYMENT_STATUS = "payment_status";// 付款单状态
	public static final String PAYMENT_STATUS_10 = "10";// 付款单状态 10-已生成付款单
	public static final String PAYMENT_STATUS_15 = "15";// 付款单状态 15-已审核通过待打款
	public static final String PAYMENT_STATUS_18 = "18";// 付款单状态 18-付款单已冻结
	public static final String PAYMENT_STATUS_20 = "20";// 付款单状态 20-已生成批次
	public static final String PAYMENT_STATUS_30 = "30";// 付款单状态 30-批次审核通过
	public static final String PAYMENT_STATUS_40 = "40";// 付款单状态 40-付款单付款中
	public static final String PAYMENT_STATUS_90 = "90";// 付款单状态 90-批次已作废
	public static final String PAYMENT_STATUS_93 = "93";// 付款单状态 93-付款单已撤回
	public static final String PAYMENT_STATUS_100 = "100";// 付款单状态 100-付款已完成

	public static final String ORDER_TASKPACKAGE_PAYMENT_TYPE_0 = "0";// 付款单类型
																		// 0-首付
	public static final String ORDER_TASKPACKAGE_PAYMENT_TYPE_1 = "1";// 付款单类型
																		// 1-尾付

	public static final String ORDER_STATUS = "order_status";// 订单状态
	public static final String ORDER_STATUS_NUMBER_320 = "320";// 订单状态
																// 320-竣工申请审核通过

	public static final String SUMMARY_STATUS = "summary_status";// 批次状态
	public static final String SUMMARY_STATUS_10 = "10";// 批次状态 10-生成批次
	public static final String SUMMARY_STATUS_20 = "20";// 批次状态 20-批次审核通过
	public static final String SUMMARY_STATUS_50 = "50";// 批次状态 50-批次付款中
	public static final String SUMMARY_STATUS_90 = "90";// 批次状态 90-批次已作废
	public static final String SUMMARY_STATUS_100 = "100";// 批次状态 100-批次付款已完成

	public static final String PAYMENT_DETAIL_MERGE_STATUS_0 = "0";// 付款明细合并状态--0：未付
	public static final String PAYMENT_DETAIL_MERGE_STATUS_1 = "1";// 付款明细合并状态--1：已付

	public static final String PROJECT_INSTALLATION_STATUS_1 = "1";// 工程安装状态1.已生成计划
	public static final String PROJECT_INSTALLATION_STATUS_2 = "2";// 工程安装状态2.已申请计划
	public static final String PROJECT_INSTALLATION_STATUS_4 = "4";// 工程安装状态3.已验收

	public static final double EVERYMONTH_MONEY = 3500;

	// 徽商银行------------------------start-----------------------------
	public static final String COMPANY_BANK_NO = "1101301021000525501"; // 公司银行卡号（固定）
	public static final String COMPANY_NAME = "安徽驰恒建筑劳务有限公司"; // 公司名称（固定）
	public static final String CURRENCY = "01"; // 币种（固定01）
	public static final String SERVICE = "劳务"; // 劳务（固定）
	// 徽商银行------------------------end-------------------------------

	public static final String SETTLEMENT_STATUS = "settlement_status";// 结算单状态
	public static final String SETTLEMENT_STATUS_10 = "10";// 结算单状态 10-待质检员复核
	public static final String SETTLEMENT_STATUS_20 = "20";// 结算单状态 20-质检员已复核
	public static final String SETTLEMENT_STATUS_50 = "50";// 结算单状态 50-项目经理已提交
	public static final String SETTLEMENT_STATUS_60 = "60";// 结算单状态 60-工人不认可分配金额
	public static final String SETTLEMENT_STATUS_65 = "65";// 结算单状态 65-工人已确认分配金额
	public static final String SETTLEMENT_STATUS_70 = "70";// 结算单状态 70-结算员已驳回
	public static final String SETTLEMENT_STATUS_80 = "80";// 结算单状态 80-结算员已通过
	public static final String SETTLEMENT_STATUS_90 = "90";// 结算单状态 90-已付首款
	public static final String SETTLEMENT_STATUS_100 = "100";// 结算单状态 100-已付款完成
	public static final String UPLOAD_SCRAP_209 = "209";// 图片上传类型

	public static final String SETTLEMENT_IS_NEED_RECHECK_0 = "0";// 结算单是否需要复合
																	// 0-否
	public static final String SETTLEMENT_IS_NEED_RECHECK_1 = "1";// 结算单是否需要复合
																	// 1-是

	/** 项目经理端竣工申请图片类型值 */
	public static final String COMPLETED_PIC_TYPE_101 = "101";// 申请竣工（工程竣工验收单）
	public static final String COMPLETED_PIC_TYPE_102 = "102";// 申请竣工（项目总结书）
	public static final String COMPLETED_PIC_TYPE_103 = "103";// 申请竣工（延期确认单）
	public static final String PICTURE_BUSINESS_TYPE_104 = "104";// 开关面板超定额图片类型
	public static final String PICTURE_BUSINESS_TYPE_5 = "5"; // 收货单的图片类型

	/** 上报复尺 数据字典取值 */
	// 复尺内容 recheck_taokou_position
	public static final String COMPLEX_CONTENT_MAP = "complex_content"; // 复尺内容在字典表中对应的字段类型值
	public static final String COMPLEX_CONTENT_MAP_1 = "1"; // 1-套口
	public static final String COMPLEX_CONTENT_MAP_2 = "2"; // 2-窗帘
	public static final String COMPLEX_CONTENT_MAP_3 = "3"; // 3-推拉门
	public static final String COMPLEX_CONTENT_MAP_4 = "4"; // 4-平开门
	public static final String COMPLEX_CONTENT_MAP_5 = "5"; // 5-马桶
	public static final String COMPLEX_CONTENT_MAP_6 = "6"; // 6-浴室柜

	/** 公用上传图片类型值 */
	public static final String TAOKOU_KEY = "201"; // 1-套口
	public static final String CURTAIN_KEY = "202"; // 2-窗帘
	public static final String PUSH_PULL_DOOR_KEY = "203"; // 3-推拉门
	public static final String FLAT_OPEN_DOOR_KEY = "204"; // 4-平开门
	public static final String CLOSE_TOOL_KEY = "205"; // 5-马桶
	public static final String ROOM_CABINET_KEY = "206"; // 6-浴室柜

	/** 任务包状态 */
	public static final String ORDER_TASKPACKAGE_STATUS_10 = "10";
	public static final String ORDER_TASKPACKAGE_STATUS_20 = "20"; //
	public static final String ORDER_TASKPACKAGE_STATUS_50 = "50";
	public static final String ORDER_TASKPACKAGE_STATUS_55 = "55";
	public static final String ORDER_TASKPACKAGE_STATUS_60 = "60";
	public static final String ORDER_TASKPACKAGE_STATUS_70 = "70";
	public static final String ORDER_TASKPACKAGE_STATUS_80 = "80";
	public static final String ORDER_TASKPACKAGE_STATUS_90 = "90";
	public static final String ORDER_TASKPACKAGE_STATUS_95 = "95";
	public static final String ORDER_TASKPACKAGE_STATUS_100 = "100";
	public static final String ORDER_TASKPACKAGE_STATUS_105 = "105";
	public static final String ORDER_TASKPACKAGE_STATUS_110 = "110";
	public static final String ORDER_TASKPACKAGE_STATUS_130 = "130";// 结算员驳回
	public static final String ORDER_TASKPACKAGE_STATUS_140 = "140";// 结算员审核通过
	public static final String ORDER_TASKPACKAGE_STATUSNAME_140 = "结算员审核通过";// 结算员审核通过
	public static final String ORDER_TASKPACKAGE_STATUSNAME_130 = "结算员驳回";// 结算员审核通过

	public static final String MESSAGE_GROUP_TYPE_100 = "100"; // 短信组类型
																// 100-财务付款员

	public static final String SEND_MSG_STATUS_0 = "0"; // 短信发送状态 0-待发送
	public static final String SEND_MSG_STATUS_1 = "1"; // 短信发送状态 1-发送成功
	public static final String SEND_MSG_STATUS_2 = "2"; // 短信发送状态 2-发送失败

	/** 采购单状态 */
	public static final String PURCHASE_STATUS_10 = "10";// 项目经理已申请
	public static final String PURCHASE_STATUS_21 = "21";// 已废弃
	public static final String PURCHASE_STATUS_40 = "40";// 已转给供应商
	public static final String PURCHASE_STATUS_70 = "70";// 部分收货
	public static final String PURCHASE_STATUS_90 = "90";// 已完成

	/** 工程模式 */
	public static final String EMPLOYEE_PROJECT_MODE_3 = "3";// 全部
	public static final String EMPLOYEE_PROJECT_MODE_2 = "2";// 传统
	public static final String EMPLOYEE_PROJECT_MODE_1 = "1";// 产业

	/** 工程模式 */
	public static final String PROJECT_MODE_2 = "2";// 传统
	public static final String PROJECT_MODE_1 = "1";// 产业

	/** 是否启用 */
	public static final String IS_ENABLE_1 = "1";// 启用
	public static final String IS_ENABLE_0 = "0";// 停用

	/** 约检单、抽检单 */
	public static final String QC_BILL_TYPE_1 = "1";// 约检
	public static final String QC_BILL_TYPE_2 = "2";// 抽检

	/** 是否复检 */
	public static final String IS_RECHECK_0 = "0";// 不复检
	public static final String IS_RECHECK_1 = "1";// 复检

	/** 项目结理结算状态 */
	public static final String PM_SETTLE_STATUS_10 = "10"; // 项目经理结算状态 10-创建
	public static final String PM_SETTLE_STATUS_15 = "15"; // 项目经理结算状态 15-已撤回
	public static final String PM_SETTLE_STATUS_20 = "20"; // 项目经理结算状态 20-已验收节点
	public static final String PM_SETTLE_STATUS_30 = "30"; // 项目经理结算状态 30-已收客户款
	public static final String PM_SETTLE_STATUS_50 = "50"; // 项目经理结算状态 50-已生成结算单

	public static final String PM_SETTLE_STATUS_35 = "35"; // 项目经理结算状态
															// 35-已下发给项目经理（准产业）
	public static final String PM_SETTLE_STATUS_38 = "38"; // 项目经理结算状态
															// 38-已重新下发给项目经理（准产业）
	public static final String PM_SETTLE_STATUS_40 = "40"; // 项目经理结算状态
															// 40-项目经理同意（准产业）
	public static final String PM_SETTLE_STATUS_45 = "45"; // 项目经理结算状态
															// 45-项目经理拒绝（准产业）

	/** 项目结理结算单类型 */
	public static final String PM_SETTLE_BILL_TYPE_1 = "1"; // 项目经理结算单类型 1-中期结算单
	public static final String PM_SETTLE_BILL_TYPE_2 = "2"; // 项目经理结算单类型 2-竣工结算单

	/** 结算单类目 */
	public static final String PM_SETTLE_CATEGORY_1 = "1"; // 结算单类目 1-标化辅材
	public static final String PM_SETTLE_CATEGORY_2 = "2"; // 结算单类目 2-自主支配
	public static final String PM_SETTLE_CATEGORY_3 = "3"; // 结算单类目 3-中期提成
	public static final String PM_SETTLE_CATEGORY_4 = "4"; // 结算单类目 4-质检罚款
	public static final String PM_SETTLE_CATEGORY_401 = "401"; // 结算单类目
																// 401-中期质检罚款
	public static final String PM_SETTLE_CATEGORY_402 = "402"; // 结算单类目
																// 402-竣工质检罚款
	public static final String PM_SETTLE_CATEGORY_5 = "5"; // 结算单类目 5-竣工提成
	public static final String PM_SETTLE_CATEGORY_6 = "6"; // 结算单类目 6-质保金

	public static final String PM_SETTLE_CATEGORY_7 = "7"; // 结算单类目 7-质检员中期提成
	public static final String PM_SETTLE_CATEGORY_8 = "8"; // 结算单类目 8-质检员竣工提成
	public static final String PM_SETTLE_CATEGORY_9 = "9"; // 结算单类目 9-质检员中期远程费
	public static final String PM_SETTLE_CATEGORY_10 = "10"; // 结算单类目
																// 10-质检员竣工远程费
	public static final String PM_SETTLE_CATEGORY_11 = "11";// 结算单类目 11-自采材料报销

	public static final String PM_SETTLE_CATEGORY_12 = "12";// 结算单类目
															// 12-项目经理材料结算类目明细
	public static final String PM_SETTLE_CATEGORY_121 = "121";// 结算单类目
																// 121-中期项目经理材料结算类目明细
	public static final String PM_SETTLE_CATEGORY_122 = "122";// 结算单类目
																// 122-竣工项目经理材料结算类目明细

	public static final String QC_SETTLE_CATEGORY_7 = "7"; // 质检员中期提成
	public static final String QC_SETTLE_CATEGORY_8 = "8"; // 质检员竣工提成
	public static final String QC_SETTLE_CATEGORY_9 = "9"; // 质检员中期远程费
	public static final String QC_SETTLE_CATEGORY_10 = "10"; // 质检员竣工远程费
	public static final String QC_SETTLE_CATEGORY_11 = "11"; // 自采材料报销

	public static final String PM_SETTLE_CATEGORY_900 = "900";// 中期承包总价
	public static final String PM_SETTLE_CATEGORY_901 = "901";// 基装增项
	public static final String PM_SETTLE_CATEGORY_902 = "902";// 辅料用量
	public static final String PM_SETTLE_CATEGORY_903 = "903";// 沙子水泥
	public static final String PM_SETTLE_CATEGORY_904 = "904";// 标化材料
	public static final String PM_SETTLE_CATEGORY_905 = "905";// 开关面板
	public static final String PM_SETTLE_CATEGORY_906 = "906";// 工人人工费
	public static final String PM_SETTLE_CATEGORY_907 = "907";// 中期奖励
	public static final String PM_SETTLE_CATEGORY_908 = "908";// 中期扣款
	public static final String PM_SETTLE_CATEGORY_909 = "909";// 材料搬运及运输费
	public static final String PM_SETTLE_CATEGORY_910 = "910";// 中期变更增项
	public static final String PM_SETTLE_CATEGORY_911 = "911";// 中期变更减项
	public static final String PM_SETTLE_CATEGORY_912 = "912";// 中期巡检奖励
	public static final String PM_SETTLE_CATEGORY_913 = "913";// 中期巡检扣款

	public static final String PM_SETTLE_CATEGORY_1000 = "1000";// 竣工承包总价
	public static final String PM_SETTLE_CATEGORY_1001 = "1001";// 远程费
	public static final String PM_SETTLE_CATEGORY_1002 = "1002";// 竣工奖励
	public static final String PM_SETTLE_CATEGORY_1003 = "1003";// 竣工扣款
	public static final String PM_SETTLE_CATEGORY_1004 = "1004";// 竣工变更增项
	public static final String PM_SETTLE_CATEGORY_1005 = "1005";// 竣工变更减项
	public static final String PM_SETTLE_CATEGORY_1012 = "1012";// 竣工巡检奖励
	public static final String PM_SETTLE_CATEGORY_1013 = "1013";// 竣工巡检扣款



	public static final String change_type_10 = "10";// 基装增项
	public static final String change_type_20 = "20";// 中期变更增项
	public static final String change_type_30 = "30";// 中期变更减项
	public static final String change_type_40 = "40";// 竣工变更增项
	public static final String change_type_50 = "50";// 竣工变更减项

	/** app类型(名称) */
	public static final String APP_TYPE = "app_type"; // app类型 app_type

	/** 员工类型 */
	public static final String EMP_TYPE_3 = "3"; // 项目经理
	public static final String EMP_TYPE_2 = "2"; // 工人
	public static final String EMP_TYPE_1 = "1"; // 质检员
	public static final String EMP_TYPE_4 = "4"; // 客户类型

	/** 标化辅材是否结算 */
	public static final String IS_SETTLED_0 = "0";// 未结算
	public static final String IS_SETTLED_1 = "1";// 已结算

	/** 工人收款拆分最大值 */
	public static final Double PAYMENT_DETAIL_SPLIT_3500 = 3500d;// 工人收款拆分最大值
	public static final Double PAYMENT_DETAIL_SPLIT_100 = 100d;// 工人收款拆分最小值

	/** 公告状态 */
	public static final String NOTICE_STATUS_1 = "1";// 暂存
	public static final String NOTICE_STATUS_2 = "2";// 已发布
	public static final String NOTICE_STATUS_3 = "3";// 已撤回

	/** 公告接收类型 */
	public static final String NOTICE_RECEIVER_TYPE_1 = "1";// 角色
	public static final String NOTICE_RECEIVER_TYPE_2 = "2";// 指定员工

	/** 未读消息 */
	public static final String VIEW_lOG_HOME_REPORT = "3";// 项目经理申请墙地砖是否已读
	public static final String VIEW_lOG_MANAGER_WALLANDFLOOR = "201";// 项目经理申请墙地砖是否已读
	public static final String VIEW_LOG_MANAGER_CHECKSIZE = "202";// 项目经理申请厂家复尺是否已读

	/** 微信用户手机号和openid的绑定 */
	public static final String BIND_STATUS_1 = "1";// 绑定
	public static final String BIND_STATUS_0 = "0";// 注销绑定

	/** 结算角色 */
	public static final String SETTLE_ROLE_1 = "1";// 项目经理
	public static final String SETTLE_ROLE_2 = "2";// 质检员

	/** 提成节点 */
	public static final String COMMISSION_NODE_1 = "1";// 中期
	public static final String COMMISSION_NODE_2 = "2";// 竣工

	/** 质检结算是否有远程费 */
	public static final String ISLONGWAYCOMMISSION_1 = "1"; // 有
	public static final String ISLONGWAYCOMMISSION_0 = "0"; // 没有

	/** 特殊手机号码和验证码 */
	public static final String USER_NAME_18519377253 = "18519377253"; // 美装管家-手机号-18519377253
	public static final String USER_NAME_18810656468 = "18810656468"; // 美装卫士-手机号-18810656468
	public static final String USER_NAME_13393163996 = "13393163996"; // 美装工匠-手机号-13393163996
	public static final String USER_NAME_18611735753 = "18611735753"; // 大美装饰管理平台-手机号-18611735753
	public static final String PASS_WORD_888888 = "888888"; // 手机号验证码-888888

	/** 问题上报 是否影响工期 */
	public static final String IS_DELAY_1 = "1"; // 是
	public static final String IS_DELAY_0 = "0"; // 否

	public static final String EVAL_REWARD_STAR = "eval_reward_star"; // 奖励星级

	public static final String REWARD_TARGET_TYPE_1 = "1"; // 评价对象 1-工人
	public static final String REWARD_TARGET_TYPE_2 = "2"; // 评价对象 2-项目经理
	public static final String REWARD_TARGET_TYPE_3 = "3"; // 评价对象 3-质检员

	public static final String IS_ENABLED_0 = "0"; // 0-停用
	public static final String IS_ENABLED_1 = "1"; // 1-启用

	public static final String EVAL_ROLE_TYPE_1 = "1"; // 评价类别 1- 项目经理评价
	public static final String EVAL_ROLE_TYPE_2 = "2"; // 评价类别 2- 质检评价
	public static final String EVAL_ROLE_TYPE_3 = "3"; // 评价类别 3- 客户评价

	/** 评价角色类型 */
	public static final String EVAL_ROLE_TYPE_101 = "101"; // 项目经理
	public static final String EVAL_ROLE_TYPE_102 = "102"; // 项目经理自动
	public static final String EVAL_ROLE_TYPE_201 = "201"; // 质检员
	public static final String EVAL_ROLE_TYPE_202 = "202"; // 质检员自动
	public static final String EVAL_ROLE_TYPE_301 = "301"; // 客户
	public static final String EVAL_ROLE_TYPE_302 = "302"; // 客户自动

	/** 评价角色状态 */
	public static final String EVAL_ROLE_STATUS_0 = "0";// 未评价
	public static final String EVAL_ROLE_STATUS_1 = "1";// 评价已完成

	/** 评价状态 */
	public static final String EVAL_STATUS_0 = "0"; // 未评价
	public static final String EVAL_STATUS_1 = "1"; // 评价中
	public static final String EVAL_STATUS_2 = "2"; // 评价完

	/** 订单分配类型 */
	public static final String DISTRIBUTE_TYPE_101 = "101";// 分配项目经理
	public static final String DISTRIBUTE_TYPE_102 = "102";// 重新分配项目经理
	public static final String DISTRIBUTE_TYPE_103 = "103";// 去除项目经理
	public static final String DISTRIBUTE_TYPE_201 = "201";// 分配项目经理
	public static final String DISTRIBUTE_TYPE_202 = "202";// 重新分配项目经理
	public static final String DISTRIBUTE_TYPE_203 = "203";// 去除项目经理
	public static final String DISTRIBUTE_TYPE_301 = "301";// 分派工人组
	public static final String DISTRIBUTE_TYPE_302 = "302";// 重派工人组
	public static final String DISTRIBUTE_TYPE_303 = "303";// 去除工人组
	public static final String DISTRIBUTE_TYPE_304 = "304";// 特殊分派工人组
	public static final String DISTRIBUTE_TYPE_305 = "305";// 特殊重派工人组

	/** 同部数据常量 */
	public static final String DATA_DIRECTION_1 = "1";// 进来 --- 数据走向
	public static final String DATA_DIRECTION_2 = "2";// 出去

	public static final String BUSINESS_TYPE_101 = "101"; // 预备订单
	public static final String BUSINESS_TYPE_201 = "201"; // 项目经理修改时间 ---业务类型
	public static final String BUSINESS_TYPE_202 = "202"; // 质检员修改时间
	public static final String BUSINESS_TYPE_301 = "301"; // 项目经理确认开工时间
	public static final String BUSINESS_TYPE_302 = "302"; // 基装验收审核通过时间
	public static final String BUSINESS_TYPE_303 = "303"; // 竣工审核通过时间

	public static final String BUSINESS_TYPE_401 = "401";// 主材验收通过 推送二级类目信息
															// 走向是2（走向是1的是二期款 ）
	public static final String BUSINESS_TYPE_402 = "402";// 尾款走向1
															// 状态是201的（废弃数据，订单不存在）

	public static final String BUSINESS_TYPE_503 = "503";// 施工变更单发送类型
	public static final String BUSINESS_TYPE_505 = "505";// 竣工验收 发送类型
	public static final String BUSINESS_TYPE_506 = "506";// 基装验收 推送墙地砖类型

	public static final String BUSINESS_TYPE_601 = "601";// 工程投诉 售后问题接收，投诉信息
	
	public static final String BUSINESS_TYPE_701 = "701";// 施工项数据类型
	public static final String BUSINESS_TYPE_702 = "702";// 施工项数据类型
	public static final String BUSINESS_TYPE_703 = "703";// 施工项数据类型
	

	public static final String SYN_STATUS_1 = "1"; // 同步成功 ---同部状态
	public static final String SYN_STATUS_2 = "2"; // 同步失败
	public static final String SYN_STATUS_3 = "3"; // 已解析
	public static final String SYN_STATUS_4 = "4"; // 待同步
	public static final String SYN_STATUS_5 = "5"; // 解析失败

	public static final String IS_AUTO_SYN_0 = "0"; // 不自动同步---是否自动重新同步
	public static final String IS_AUTO_SYN_1 = "1"; // 自动同步

	/** 预备订单状态 */
	public static final String COMPLAINT_FORFEIT_10 = "10"; // 待审核
	public static final String COMPLAINT_FORFEIT_20 = "20"; // 已审核
	public static final String COMPLAINT_FORFEIT_30 = "30"; // 已拒绝

	/** 预备订单状态 */
	public static final String PREPARE_ORDER_STATUS_10 = "10"; // 待接收
	public static final String PREPARE_ORDER_STATUS_20 = "20"; // 已拒绝
	public static final String PREPARE_ORDER_STATUS_30 = "30"; // 已接受
	public static final String PREPARE_ORDER_STATUS_15 = "15"; // 接收失败

	/** 厂家复尺状态 */
	public static final String CHECKSIZE_STATUS_10 = "10"; // 项目经理已申请
	public static final String CHECKSIZE_STATUS_20 = "20"; // 材料部已处理

	/** 中信银行 */
	public static final String ZHONGXIN_BANK_CODE = "302362051222";
	/** 公司付款账号 */
	public static final String ZHONGXIN_COMPANY_ACCOUNT = "8112301012200366212";
	/** 订单回收编号 */
	public static final String ORDERNUMBER_RECOVERY = "ZF";
	/*质检基桩约检节点*/
	public static final String PQC_CHECKED_NODE_6 = "6";

    /*项目经理考勤 基桩类型*/
    public static final String MANAGER_ATTEND_JZ = "1";
    /*项目经理考勤 主材类型*/
    public static final String MANAGER_ATTEND_ZC = "2";
    /*项目经理考勤 基桩节点*/
    public static final String MANAGER_ATTEND_JZ_NODE = "6";
    /*项目经理考勤 主材节点*/
    public static final String MANAGER_ATTEND_ZC_NODE = "9";
}
