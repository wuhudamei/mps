package cn.damei.common.constantUtils;

/**
 * Created by joseph on 2017/4/7.
 * 采购单相关常量维护类  (禁止存放其他业务常量)
 */
public class PurchaseConstantUtil {

	/**
	 * 采购单状态-->10:项目经理已申请
	 */
	public static final String PURCHASE_AUXILIARY_STATUS_10 = "10";
	public static final String PURCHASE_AUXILIARY_STATUS_NAME_10 = "项目经理已申请";
	
	/**
	 * 采购单状态-->21:已废弃
	 */
	public static final String PURCHASE_AUXILIARY_STATUS_21 = "21";
	public static final String PURCHASE_AUXILIARY_STATUS_NAME_21 = "已废弃";
	
	/**
	 * 采购单状态-->40:已转给供应商
	 */
	public static final String PURCHASE_AUXILIARY_STATUS_40 = "40";
	public static final String PURCHASE_AUXILIARY_STATUS_NAME_40 = "已转给供应商";
	
	/**
	 * 采购单状态-->50:已送货
	 */
	public static final String PURCHASE_AUXILIARY_STATUS_50 = "50";
	public static final String PURCHASE_AUXILIARY_STATUS_NAME_50 = "已送货";
	
	/**
	 * 采购单状态-->70:部分收货
	 */
	public static final String PURCHASE_AUXILIARY_STATUS_70 = "70";
	public static final String PURCHASE_AUXILIARY_STATUS_NAME_70 = "部分收货";
	
	/**
	 * 采购单状态-->90:已完成
	 */
	public static final String PURCHASE_AUXILIARY_STATUS_90 = "90";
	public static final String PURCHASE_AUXILIARY_STATUS_NAME_90 = "已完成";
	
    /**
     * 采购单类型-->1:辅料  2：开关面板  3： 4：  5：墙地砖  6：沙子水泥
     */
    public static final String PURCHASE_TYPE_1 = "1";//辅料
    public static final String PURCHASE_TYPE_2 = "2";//开关面板
    public static final String PURCHASE_TYPE_3 = "3";//地砖 编号
    public static final String PURCHASE_TYPE_4 = "4";//墙砖编号
    public static final String PURCHASE_TYPE_5 = "5";//墙地砖
    public static final String PURCHASE_TYPE_6 = "6";//沙子水泥
    
    /**
     * 辅料商品   是否是沙子水泥-->0：否 1：是
     */
    public static final String PURCHASE_SAND_IS_SAND_CEMENT_NO_0 = "0";
    public static final String PURCHASE_SAND_IS_SAND_CEMENT_YES_1 = "1";

    /**
     * 辅料商品--状态--是否启用-->0:停用  1：启用
     */
    public static final String PURCHASE_STATUS_NO_0 = "0";
    public static final String PURCHASE_STATUS_YES_1 = "1";
    
    /**
     * 辅料商品--状态--是否删除-->0:否  1：是
     */
    public static final String PURCHASE_DEL_FLAG_NO_0 = "0";
    public static final String PURCHASE_DEL_FLAG_YES_1 = "1";
    
    /**
     * 采购单--是否暂存-->NO:否  YES：是
     */
    public static final String PURCHASE_SUBMMIT_STATUS_NO = "NO";
    public static final String PURCHASE_SUBMMIT_STATUS_YES = "YES";
    
    /**
     * 采购单(辅料)--是否可以申请辅料-->0:否  1：是
     */
    public static final String PURCHASE_IS_CAN_APPLY_AUXILIARY_0 = "0";
    public static final String PURCHASE_IS_CAN_APPLY_AUXILIARY_1 = "1";
    public static final String PURCHASE_IS_CAN_APPLY_AUXILIARY_2 = "2";
    public static final String PURCHASE_IS_CAN_APPLY_AUXILIARY_3 = "3";
    public static final String PURCHASE_IS_CAN_APPLY_AUXILIARY_4 = "4";
    public static final String PURCHASE_IS_CAN_APPLY_AUXILIARY_5 = "5";
    public static final String PURCHASE_IS_CAN_APPLY_AUXILIARY_6 = "6";
    public static final String PURCHASE_IS_CAN_APPLY_AUXILIARY_7 = "7";
    
}
