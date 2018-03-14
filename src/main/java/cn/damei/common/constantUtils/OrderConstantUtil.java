package cn.damei.common.constantUtils;

/**
 * Created by joseph on 2017/4/7.
 * 订单相关常量维护类  (禁止存放其他业务常量)
 */
public class OrderConstantUtil {

    /**
     * 100-营销部已转单给工程部
     */
    public static final String ORDER_STATUS_CONVERT_THE_ORDER_100 = "100";
    public static final String ORDER_STATUS_NAME_CONVERT_THE_ORDER_100 = "营销部已转单给工程部";
	/**
	 * 101-工程部退回订单
	 */
	public static final String ORDER_STATUS_RETURN_THE_ORDER_101 = "101";
	public static final String ORDER_STATUS_NAME_RETURN_THE_ORDER_101 = "工程部退回订单";
	/**
	 * 105-工程部确认接收订单
	 */
	public static final String ORDER_STATUS_RECEIVE_THE_ORDER_105 = "105";
	public static final String ORDER_STATUS_NAME_RECEIVE_THE_ORDER_105 = "工程部确认接收订单";
	/**
	 * 110-已生成预算
	 */
	public static final String ORDER_STATUS_GENERATE_BUDGET_110 = "110";
	public static final String ORDER_STATUS_NAME_GENERATE_BUDGET_110 = "已生成预算";
	/**
	 * 120-已派项目经理
	 */
	public static final String ORDER_STATUS_DISTRIBUTE_MANAGER_120 = "120";
	public static final String ORDER_STATUS_NAME_DISTRIBUTE_MANAGER_120= "已派项目经理";
	/**
	 * 125-已派质检员
	 */
	public static final String ORDER_STATUS_DISTRIBUTE_INSPECTION_125 = "125";
	public static final String ORDER_STATUS_NAME_DISTRIBUTE_INSPECTION_125 = "已派质检员";
	/**
	 * 130-已现场交底
	 */
	public static final String ORDER_STATUS_DISCLOSE_130 = "130";
	public static final String ORDER_STATUS_NAME_DISCLOSE_130 = "已现场交底";
	/**
     * 200-施工中
     */
    public static final String  ORDER_STATUS_CONFIRM_START_200="200";
    public static final String  ORDER_STATUS_NAME_CONFIRM_START_200="施工中";
    /**
     * 300-项目经理已申请竣工
     */
	public static final String ORDER_STATUS_APPLY_COMPLETION_300 = "300";
	public static final String ORDER_STATUS_NAME_APPLY_COMPLETION_300 = "项目经理已申请竣工";
	/**
	 * 310-质检竣工审核不通过
	 */
	public static final String ORDER_STATUS_INSPECTION_CHECK_REFUSE_310 = "310";
	public static final String ORDER_STATUS_NAME_INSPECTION_CHECK_REFUSE_310 = "质检竣工审核不通过";
	/**
	 * 320-质检竣工审核通过
	 */
	public static final String ORDER_STATUS_INSPECTION_CHECK_PASS_320 = "320";
	public static final String ORDER_STATUS_NAME_INSPECTION_CHECK_PASS_320 = "质检竣工审核通过";
	/**
	 * 330-结算员竣工审核不通过
	 */
	public static final String ORDER_STATUS_SETTLEMENT_CHECK_REFUSE_330 = "330";
	public static final String ORDER_STATUS_NAME_SETTLEMENT_CHECK_REFUSE_330 = "结算员竣工审核不通过";
	/**
	 * 340-结算员竣工审核通过
	 */
	public static final String ORDER_STATUS_SETTLEMENT_CHECK_PASS_340 = "340";
	public static final String ORDER_STATUS_NAME_SETTLEMENT_CHECK_PASS_340 = "结算员竣工审核通过";
	/**
	 * 400-确认已竣工
	 */
	public static final String ORDER_STATUS_COMPLETION_400 = "400";
	public static final String ORDER_STATUS_NAME_COMPLETION_400 = "确认已竣工";
	
	
	/**
	 * 订单新老房-->0:老房  1：新房
	 */
	public static final String ORDER_HOUSE_IS_NEW_OLDHOUSE_0 = "0";
    public static final String ORDER_HOUSE_IS_NEW_NEWHOUSE_1 = "1";
	
    /**
	 * 订单有无电梯-->0:无  1：有
	 */
    public static final String ORDER_IS_ELEVATOR_NO_0 = "0";
    public static final String ORDER_IS_ELEVATOR_YES_1 = "1";
    
	
    /**
     * 订单工程模式-->1:产业  2:传统 4:准产业
     */
    public static final String ORDER_PROJECT_MODE_INDUSTRY_1 = "1";
    public static final String ORDER_PROJECT_MODE_TRADITION_2 = "2";
    public static final String ORDER_PROJECT_MODE_INDUSTRY_4 = "4";
    
    /**
     * 订单客户类型-->0:VIP客户  1：普通客户 2：串单
     */
    public static final String ORDER_CUS_TYPE_VIP_0 = "0";
    public static final String ORDER_CUS_TYPE_ORDINARY_1 = "1";
    public static final String ORDER_CUS_TYPE_SINGLE_2 = "2";
   
    /**
     * 订单房屋类型-->0:平层楼房  1：别墅  2：LOFT
     */
    public static final String ORDER_BUILD_TYPE_BUILDING_0 = "0";
    public static final String ORDER_BUILD_TYPE_VILLA_1 = "1";
    public static final String ORDER_BUILD_TYPE_LOFT_2 = "2";
   
    /**
     * 订单户型-->1:一居室  2：二居室  3：三居室 4：四居室  5：五居室  6：其他
     */
    public static final String ORDER_HOUSE_TYPE_1 = "1";
    public static final String ORDER_HOUSE_TYPE_2 = "2";
    public static final String ORDER_HOUSE_TYPE_3 = "3";
    public static final String ORDER_HOUSE_TYPE_4 = "4";
    public static final String ORDER_HOUSE_TYPE_5 = "5";
    public static final String ORDER_HOUSE_TYPE_6 = "6";
    
    /**
     * 订单--是否删除-->0:否  1：是
     */
    public static final String ORDER_DEL_FLAG_NO_0 = "0";
    public static final String ORDER_DEL_FLAG_YES_1 = "1";
    
    /**
     * 订单--任务包生成状态--> 0:未生成 1:已生成
     */
    public static final String ORDER_ORDERTASKPACKAGE_STATUS_NO_0 = "0";
	public static final String ORDER_ORDERTASKPACKAGE_STATUS_YES_1 = "1";
	
	/**
	 * 订单--是否作废--> 0:否 1:是
	 */
	public static final String ORDER_IS_SCRAP_NO_0 = "0";
	public static final String ORDER_IS_SCRAP_YES_1 = "1";

}
