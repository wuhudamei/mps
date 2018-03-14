package cn.damei.common.constantUtils;

/**
 * Created by joseph on 2017/4/10.
 *
 * 各种log等 表中的 status 和 type
 * 维护时,请标注出表及字段和对应的常量值和注释
 */
public class BusinessLogConstantUtil {


    /**
     * biz_business_status_log
     * business_status
     * 保存log记录 在 BizBusinessStatusLogService serive中
     */
    //预备订单状态
    public static final String PREPARE_ORDER_STATUS_10 = "10"; //待接收
    public static final String PREPARE_ORDER_STATUS_20 = "20"; //已拒绝
    public static final String PREPARE_ORDER_STATUS_30 = "30"; //已接受
    public static final String PREPARE_ORDER_STATUS_15 = "15"; //接收失败

    //项目经理安装项申请,订单未收二期款 不允许申请时,发送短信给设计师和客户并记录的状态, 每天发送最多一次
    public static final String  ORDER_LACK_MIDDLE_MONEY_STATUS_75="75";
    //考勤批次状态
    public static final String ATTEND_BATCH_STAUS_80="80";


    public static final String BUSINESS_TYPE_101 = "101"; //预备订单
    public static final String BUSINESS_TYPE_201 = "201"; //项目经理修改时间 ---业务类型
    public static final String BUSINESS_TYPE_202 = "202"; //质检员修改时间
    public static final String BUSINESS_TYPE_301 = "301"; //项目经理确认开工时间
    public static final String BUSINESS_TYPE_302 = "302"; //基装验收审核通过时间
    public static final String BUSINESS_TYPE_303 = "303"; //竣工审核通过时间
    public static final String BUSINESS_TYPE_304 = "304"; //确认二期款
    public static final String BUSINESS_TYPE_305 = "305"; //确认尾款
    public static final String BUSINESS_TYPE_306 = "306"; //生成月度结算
    public static final String BUSINESS_TYPE_404 = "404"; //经理安装项申请时 未交二期款发送短信类型
    public static final String BUSINESS_TYPE_501="501";//返单上报
    public static final String BUSINESS_TYPE_601="601";//工人结算单
    public static final String BUSINESS_TYPE_701="701";//对账单
    public static final String BUSINESS_TYPE_801="801";//考勤批次
    
    public static final String BUSINESS_TYPE_1001="1001";//预算员审核通过
    public static final String BUSINESS_TYPE_1101="1101";//工人接受任务包
    public static final String BUSINESS_TYPE_1201="1201";//工人拒绝任务包
    
    
    public static final String BUSINESS_TYPE_901="901";//安装申请
    public static final String BUSINESS_TYPE_9011="9011";//安装申请 安装单
    public static final String BUSINESS_TYPE_9012="9012";//安装申请 安装单 施工单
    public static final String BUSINESS_TYPE_9021="9021";//安装申请 【提前申请】
    public static final String BUSINESS_TYPE_9022="9022";//主材复尺项申请 【提前申请】
    public static final String BUSINESS_TYPE_9023="9023";//主材安装计划 修改可申请安装日期
    public static final String BUSINESS_TYPE_9024="9024";//主材安装计划 修改可申请复尺日期
    
    
    public static final String BUSINESS_TYPE_210="210";//项目经理申请自采材料报销

    
    public static final String BUSINESS_TYPE_2011="2011";//项目经理申请墙地砖采购单
    public static final String BUSINESS_TYPE_2012="2012";//项目经理申请墙地砖复尺单


    /**
     * biz_pre_pm_settle_finance_receive_moeny
     * receive_money_type
     */
    public static final String  SETTLE_MONEY_TYPE_MID_1="1";//二期款接收type
    public static final String  SETTLE_MONEY_TYPE_END_2 ="2";//尾款接收type


    /**
     * biz_phone_msg
     * status
     */
    public static final String SEND_MSG_STATUS_0 = "0"; // 短信发送状态 0-待发送
    public static final String SEND_MSG_STATUS_1 = "1"; // 短信发送状态 1-发送成功
    public static final String SEND_MSG_STATUS_2 = "2"; // 短信发送状态 2-发送失败
    
    
	/**
	 * 准产业项目经理中期结算
	 */
	 public static final String PRE_MANAGER_SETTLE_TYPE_3100= "3100"; //项目经理申请约检节点
	 public static final String PRE_MANAGER_SETTLE_TYPE_3200= "3200"; //质检员确认验收约检节点
	 public static final String PRE_MANAGER_SETTLE_TYPE_3300= "3300"; //结算员创建结算单
	 public static final String PRE_MANAGER_SETTLE_TYPE_3400= "3400"; //结算员下发结算单
	 public static final String PRE_MANAGER_SETTLE_TYPE_3500= "3500"; //项目经理拒绝
	 public static final String PRE_MANAGER_SETTLE_TYPE_3600= "3600"; //结算员修改结算单
	 public static final String PRE_MANAGER_SETTLE_TYPE_3700= "3700"; //结算员已重新下发结算单
	 public static final String PRE_MANAGER_SETTLE_TYPE_3800= "3800"; //项目经理同意结算金额
	 public static final String PRE_MANAGER_SETTLE_TYPE_3900= "3900"; //已生成月度结算单
	 
	 /**
		 * 准产业项目经理竣工结算
		 */
		 public static final String PRE_MANAGER_SETTLE_TYPE_4100= "4100"; //项目经理申请竣工
		 public static final String PRE_MANAGER_SETTLE_TYPE_4200= "4200"; //结算员通过竣工
		 public static final String PRE_MANAGER_SETTLE_TYPE_4300= "4300"; //结算员创建结算单
		 public static final String PRE_MANAGER_SETTLE_TYPE_4400= "4400"; //结算员下发结算单
		 public static final String PRE_MANAGER_SETTLE_TYPE_4500= "4500"; //项目经理拒绝
		 public static final String PRE_MANAGER_SETTLE_TYPE_4600= "4600"; //结算员修改结算单
		 public static final String PRE_MANAGER_SETTLE_TYPE_4700= "4700"; //结算员已重新下发结算单
		 public static final String PRE_MANAGER_SETTLE_TYPE_4800= "4800"; //项目经理同意结算金额
		 public static final String PRE_MANAGER_SETTLE_TYPE_4900= "4900"; //已生成月度结算单
		 
		/**
		 *  后台确认开工
		 */
		 public static final String PC_CONFIRM_START= "5000"; //后台确认开工
		 /**
		  * 订单回收
		  */
		 public static final String ORDERNUMBER_RECOVERY_STATUS_6000 = "6000";//订单回收
}
