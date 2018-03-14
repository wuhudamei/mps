package cn.damei.common.constantUtils.projectProblem;

/**
 * Created by joseph on 2017/7/4.
 */
public class ProjectProblemConstantUtil {

	public static final String PROJECT_PROBLEM_STATUS_0 = "0";// 创建
	public static final String PROJECT_PROBLEM_STATUS_10 = "10";// 工人已处理
	public static final String PROJECT_PROBLEM_STATUS_20 = "20";// 项目经理 或者质检
																// 处理完毕

	public static final String PROJECT_DEAL_PERSON_TYPE_MANAGER_1 = "1"; // 经理
	public static final String PROJECT_DEAL_PERSON_TYPE_WORKER_2 = "2";// 工人
	public static final String PROJECT_DEAL_PERSON_TYPE_PQC_3 = "3";// 质检

	/**
	 * 问题项状态 biz_order_complaint_problem
	 */
	public static final String PROJECT_PROBLEM_ITEM_STATUS_10 = "10";// 待处理
	public static final String PROJECT_PROBLEM_ITEM_STATUS_20 = "20";// 处理中
	public static final String PROJECT_PROBLEM_ITEM_STATUS_30 = "30";// 已处理

	public static final String PROJECT_PROBLEM_COMPLAINT_SOURCE_1 = "1";// 1 客户
	public static final String PROJECT_PROBLEM_COMPLAINT_SOURCE_5 = "5";// 设计部
	public static final String PROJECT_PROBLEM_COMPLAINT_SOURCE_6 = "6";// 材料部
	public static final String PROJECT_PROBLEM_COMPLAINT_SOURCE_7 = "7";// 客管部

	public static final String PROJECT_PROBLEM_COMPLAINT_DATA_INPUT_CHANNEL_1 = "1";// 售后
	public static final String PROJECT_PROBLEM_COMPLAINT_DATA_INPUT_CHANNEL_2 = "2";// 部门上报
	public static final String PROJECT_PROBLEM_COMPLAINT_DATA_INPUT_CHANNEL_3 = "3";// 其他部门投诉

	/**
	 * 售后单状态 biz_cus_service_problem
	 */
	public static final String PROJECT_PROBLEM_COMPLAINT_STATUS_10 = "10";// 10
																			// 已接收
	public static final String PROJECT_PROBLEM_COMPLAINT_STATUS_20 = "20";// 20
																			// 已处理
	public static final String PROJECT_PROBLEM_COMPLAINT_STATUS_30 = "30";// 30
																			// 已驳回
	public static final String PROJECT_PROBLEM_COMPLAINT_STATUS_40 = "40";// 40
																			// 待处理
	/**
	 * 投诉单状态 biz_order_complaint
	 */
	public static final String COMPLAINT_STATUS_10 = "10";// 10 待处理

	/**
	 * 预投诉单状态 biz_order_complaint_pre
	 *
	 */
	public static final String PROJECT_PRE_PROBLEM_COMPLAINT_STATUS_CREATE = "10";// 10待处理
	public static final String PROJECT_PRE_PROBLEM_COMPLAINT_STATUS_ACCEPET = "20";// 20
																					// 已接收
	public static final String PROJECT_PRE_PROBLEM_COMPLAINT_STATUS_REFUSE = "30";// 30
																					// 已拒绝
	public static final String PROJECT_PRE_PROBLEM_COMPLAINT_STATUS_DEAL = "40";// 40
																				// 已处理

	/**
	 * 关联业务类型
	 */

	public static final String ORDER_COMPLAINT_RELATED_BUSINESS_TYPE_1 = "1"; // 预投诉类型
	public static final String ORDER_COMPLAINT_RELATED_BUSINESS_TYPE_2 = "2"; // 售后
	public static final String ORDER_COMPLAINT_RELATED_BUSINESS_TYPE_3 = "3"; // 部门上报

}
