package cn.damei.common.constantUtils;

/**
 * 自选材料相关常量维护类  (禁止存放其他业务常量)
 */
public class MaterialSelfbuyConstantUtil {

	/**
	 * 自选材料   状态 是否启用   0:停用  1：启用
	 */
	public static final String MATERIAL_SELFBUY_IS_ENABLED_0 = "0";
	public static final String MATERIAL_SELFBUY_IS_ENABLED_1 = "1";
	
	/**
	 * 自选材料  工程模式  1：产业 2：传统
	 */
	public static final String MATERIAL_SELFBUY_PROJECT_MODE_1 = "1";
	public static final String MATERIAL_SELFBUY_PROJECT_MODE_2 = "2";
	
	/**
	 * 自选材料  所属结算阶段  1：中期结算 2：竣工结算
	 */
	public static final String MATERIAL_SELFBUY_SETTLE_STAGE_1 = "1";
	public static final String MATERIAL_SELFBUY_SETTLE_STAGE_2 = "2";
	
	/**
	 * 自选材料  报销类型  1：初次申报 2：重新申报
	 */
	public static final String MATERIAL_SELFBUY_REIMBURSE_TYPE_1 = "1";
	public static final String MATERIAL_SELFBUY_REIMBURSE_TYPE_2 = "2";
	
	/**
	 * 自选材料  是否删除  0：否 1：是
	 */
	public static final String MATERIAL_SELFBUY_DEL_FLAG_0 = "0";
	public static final String MATERIAL_SELFBUY_DEL_FLAG_1 = "1";

	/**
	 * 自选材料  报销类型状态 10：项目经理已申请  15：项目经理已重新申请  20：结算员已审核通过  25：结算员已驳回
	 */
	public static final String MATERIAL_SELFBUY_REIMBURSE_STATUS_10 = "10";
	public static final String MATERIAL_SELFBUY_REIMBURSE_STATUS_REMARKS_10 = "项目经理已申请";
	public static final String MATERIAL_SELFBUY_REIMBURSE_STATUS_15 = "15";
	public static final String MATERIAL_SELFBUY_REIMBURSE_STATUS_REMARKS_15 = "项目经理已重新申请";
	public static final String MATERIAL_SELFBUY_REIMBURSE_STATUS_20 = "20";
	public static final String MATERIAL_SELFBUY_REIMBURSE_STATUS_REMARKS_20 = "结算员已审核通过";
	public static final String MATERIAL_SELFBUY_REIMBURSE_STATUS_25 = "25";
	public static final String MATERIAL_SELFBUY_REIMBURSE_STATUS_REMARKS_25 = "结算员已驳回";
	public static final String MATERIAL_SELFBUY_REIMBURSE_STATUS_90 = "90";
	public static final String MATERIAL_SELFBUY_REIMBURSE_STATUS_REMARKS_90 = "自动作废申请的自采材料";
}
