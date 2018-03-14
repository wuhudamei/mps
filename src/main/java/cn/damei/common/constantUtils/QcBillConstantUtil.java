package cn.damei.common.constantUtils;

/**
 * Created by joseph on 2017/4/7.
 * 质检相关常量维护类  (禁止存放其他业务常量)
 */
public class QcBillConstantUtil {

    /**
     * 约检单状态  0：暂存
     */
    public static final String QC_BILL_CHECK_STATUS_0 = "0";
    public static final String QC_BILL_CHECK_STATUS_NAME_0 = "暂存";
	/**
	 * 约检单状态  2：项目经理已申请
	 */
	public static final String QC_BILL_CHECK_STATUS_2 = "2";
	public static final String QC_BILL_CHECK_STATUS_NAME_2 = "项目经理已申请";
	/**
	 * 约检单状态  5：已检查完成
	 */
	public static final String QC_BILL_CHECK_STATUS_5 = "5";
	public static final String QC_BILL_CHECK_STATUS_NAME_5 = "已检查完成";
	/**
	 * 约检单状态  10：质检员已提交约检验收
	 */
	public static final String QC_BILL_CHECK_STATUS_10 = "10";
	public static final String QC_BILL_CHECK_STATUS_NAME_10 = "质检员已提交约检验收";
	/**
	 * 约检单状态  20：结算员已驳回约检验收
	 */
	public static final String QC_BILL_CHECK_STATUS_20 = "20";
	public static final String QC_BILL_CHECK_STATUS_NAME_20= "结算员已驳回约检验收";
	/**
	 * 约检单状态  30：已通过约检验收
	 */
	public static final String QC_BILL_CHECK_STATUS_30 = "30";
	public static final String QC_BILL_CHECK_STATUS_NAME_30 = "已通过约检验收";
	
	
	
	/**
	 * 抽检单状态  -1：未选择检查项
	 */
	public static final String QC_BILL_SELECT_CHECK_STATUS_MINUS_1 = "-1";
	public static final String QC_BILL_SELECT_CHECK_STATUS_NAME_MINUS_1 = "未选择检查项";
	/**
     * 抽检单状态  0：暂存
     */
    public static final String QC_BILL_SELECT_CHECK_STATUS_0 = "0";
    public static final String QC_BILL_SELECT_CHECK_STATUS_NAME_0 = "暂存";
	/**
	 * 抽检单状态  5：已检查完成
	 */
	public static final String QC_BILL_SELECT_CHECK_STATUS_5 = "5";
	public static final String QC_BILL_SELECT_CHECK_STATUS_NAME_5 = "已检查完成";
	
	
	/**
	 * 复检单状态  1：创建
	 */
	public static final String QC_BILL_RECHECK_STATUS_1 = "1";
	public static final String QC_BILL_RECHECK_STATUS_NAME_1 = "创建";
	/**
     * 复检单状态  2：项目经理已申请
     */
    public static final String QC_BILL_RECHECK_STATUS_2 = "2";
    public static final String QC_BILL_RECHECK_STATUS_NAME_2 = "项目经理已申请";
	/**
	 * 复检单状态  3：复检不合格
	 */
	public static final String QC_BILL_RECHECK_STATUS_3 = "3";
	public static final String QC_BILL_RECHECK_STATUS_NAME_3 = "复检不合格";
	/**
	 * 复检单状态  4：复检合格
	 */
	public static final String QC_BILL_RECHECK_STATUS_4 = "4";
	public static final String QC_BILL_RECHECK_STATUS_NAME_4 = "复检合格";
	
	
	/**
	 * 质检单类型--> 1：约检  2：抽检
	 */
	public static final String QC_BILL_TYPE_CHECK_1 = "1";
    public static final String QC_BILL_TYPE_SELECT_CHECK_2 = "2";
    /**
	 * 质检单是否为复检-->0:否  1：是
	 */
    public static final String QC_BILL_IS_RECHECK_NO_0 = "0";
    public static final String QC_BILL_IS_RECHECK_YES_1 = "1";
    
	
    /**
     * 约检验收单审核状态-->0:驳回  1:通过
     */
    public static final String QC_BILL_REVIEW_STATUS_REFUSE_0 = "0";
    public static final String QC_BILL_REVIEW_STATUS_PASS_1 = "1";
    
    /**
     * 质检--检查项---是否合格-->0:不合格  1：合格
     */
    public static final String QC_BILL_IS_PASSED_NO_0 = "0";
    public static final String QC_BILL_IS_PASSED_YES_1 = "1";
  
    /**
     * 质检--检查项--不合格--是否警告-->0:不警告  1：警告
     */
    public static final String QC_BILL_IS_WARNED_NO_0 = "0";
    public static final String QC_BILL_IS_WARNED_YES_1 = "1";
    
    /**
     * 质检--检查项--不合格--是否现场整改-->0:否  1：是
     */
    public static final String QC_BILL_IS_LOCALE_REPAIRE_NO_0 = "0";
    public static final String QC_BILL_IS_LOCALE_REPAIRE_YES_1 = "1";
  
    /**
     * 质检--检查项--不合格--是否限期整改-->0:否  1：是
     */
    public static final String QC_BILL_IS_LIMIT_DATE_REPAIRE_NO_0 = "0";
    public static final String QC_BILL_IS_LIMIT_DATE_REPAIRE_YES_1 = "1";
   
    /**
     * 质检--检查项--不合格--限期整改检查方式 -->0:线上检查  1：线下检查
     */
    public static final String QC_BILL_LIMIT_DATE_REPAIRE_CHECK_STYLE_LINE_UP_0 = "0";
    public static final String QC_BILL_LIMIT_DATE_REPAIRE_CHECK_STYLE_LINE_DOWN_1 = "1";
    
    /**
     * 质检--检查项--不合格--是否罚款 -->0:否  1：是
     */
    public static final String QC_BILL_IS_PUNISH_MONEY_NO_0 = "0";
    public static final String QC_BILL_IS_PUNISH_MONEY_YES_1 = "1";
    
    /**
     * 质检--检查项--不合格--检查方式 -->0:线上 1：线下
     */
    public static final String QC_BILL_CHECK_STYLE_LINE_UP_0 = "0";
    public static final String QC_BILL_CHECK_STYLE_LINE_DOWN_1 = "1";
  
    /**
     * 质检--不合格检查项--违规形式--是否填写备注 -->0:否 1：是
     */
    public static final String QC_BILL_IS_REQUIRED_REMARKS_NO_0 = "0";
    public static final String QC_BILL_IS_REQUIRED_REMARKS_YES_1 = "1";
    
    /**
     * 检查项设置--状态--是否启用-->0:停用  1：启用
     */
    public static final String QC_BILL_STATUS_NO_0 = "0";
    public static final String QC_BILL_STATUS_YES_1 = "1";
    
    /**
     * 检查项设置--是否红线-->0:否 1：是
     */
    public static final String QC_BILL_IS_REDLINE_NO_0 = "0";
    public static final String QC_BILL_IS_REDLINE_YES_1 = "1";
    
    /**
     * 检查项设置--是否必检-->0:否 1：是
     */
    public static final String QC_BILL_IS_REQUIRED_NO_0 = "0";
    public static final String QC_BILL_IS_REQUIRED_YES_1 = "1";
    
    /**
     * 检查项设置--工程模式-->1:产业  2:传统
     */
    public static final String QC_BILL_PROJECT_MODE_INDUSTRY_1 = "1";
    public static final String QC_BILL_PROJECT_MODE_TRADITION_2 = "2";
    
    /**
     * 约检节点设置--是否缴催-->0:否 1：是
     */
    public static final String QC_BILL_IS_URGE_PAY_NO_0 = "0";
    public static final String QC_BILL_IS_URGE_PAY_YES_1 = "1";
    

}
