package cn.damei.common.constantUtils;

/**
 * Created by joseph on 2017/4/28.
 * 返单信息常量维护表
 */
public class BizOrderReportConstantUtil {


    /**
     * 返单来源
     */
    public static final String   REPORT_SOURCE_TYPE_1= "1"; //返单来源  1:项目经理app
    public static final String   REPORT_SOURCE_TYPE_2= "2"; //返单来源  2:质检app
    public static final String   REPORT_SOURCE_TYPE_3= "3"; //返单来源  3:工人app
    public static final String   REPORT_SOURCE_TYPE_4= "4"; //返单来源  4:后台


    public static final  String  SEND_LOG_TYPE_1 ="1";//自动分配客服
    public static final  String  SEND_LOG_TYPE_2 ="2";//人工分配客服


    /**
     * 返单类型
     */
    public  static final String  REPORT_TYPE_1="1";//1:项目经理
    public  static final String  REPORT_TYPE_2="2";  //2:质检
    public  static final String  REPORT_TYPE_3="3";  //3:工人
    public  static final String  REPORT_TYPE_4="4";  //4:工程部
    public  static final String  REPORT_TYPE_5="5";  //5:审计部
    public  static final String  REPORT_TYPE_6="6";  //6:财务部
    public  static final String  REPORT_TYPE_7="7";  //7:人力部


    /**
     * 远程接口请求路径
     * 测试环境：http://103.249.255.142:8001
     * 生产环境：http://order.bj.mdni.cn
     */


  //远程接口加密key
    public static String REMOTE_INTERFACE_PARAM_KEY = "7b5df6aq2we4r3t6y1vxnmhjklpewd23";//远程接口加密key

    //进店大定的信息key
    public static String REMOTE_ORDER_REPORT_KEY = "GTFROIYBGTHH123GFERUIHGFDRTLUP";






    public  static final String  REPORT_STATUS_10="10";//返单状态: 返单上报
    public  static final String  REPORT_STATUS_10_WORD="返单上报";



    public static final  String  REPORT_STATUS_20 ="20";//信息无效
    public static final  String  REPORT_STATUS_20_WORD ="信息无效";

    public static final  String  REPORT_STATUS_25 ="25";//已分派客服
    public static final  String  REPORT_STATUS_25_WORD ="已分派客服";

    public static final  String  REPORT_STATUS_26 ="26";
    public static final  String  REPORT_STATUS_26_WORD ="转派客服";

    public static final  String  REPORT_STATUS_30 ="30";//客户进店未签约
    public static final  String  REPORT_STATUS_30_WORD ="客户进店未签单";

    public static final  String  REPORT_STATUS_40 ="40";//客户进店已签约
    public static final  String  REPORT_STATUS_40_WORD ="客户进店已签单";

    public static final  String  REPORT_STATUS_50 ="50";//客户已签施工合同
    public static final  String  REPORT_STATUS_50_WORD ="客户已签订施工合同";

    public static final  String  REPORT_STATUS_55 ="55";//补签施工合同
    public static final  String  REPORT_STATUS_55_WORD ="补签订施工合同";

    public static final  String  REPORT_STATUS_90 ="90";//返单已失效
    public static final  String  REPORT_STATUS_90_WORD ="90";//返单已失效





    public static final String  INSOTRE_TYPE_1="1"; //进店类型 1:进店未签单,  2:进店已签单
    public static final String  INSOTRE_TYPE_2="2"; //进店类型 1:进店未签单,  2:进店已签单
    public static final String  SIGN_TYPE_1="1"; //签单类型: 1:已签施工合同 2:补签施工合同
    public static final String  SIGN_TYPE_2="2";//签单类型: 1:已签施工合同 2:补签施工合同
    public static final String DISTRIBUTE_SERVICE_1="1"; //分配客服
     public static final String DISTRIBUTE_SERVICE_2="2"; //转派客服

}
