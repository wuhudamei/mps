/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 工程进度大看板Entity
 * @author qww
 * @version 2016-10-26
 */
public class BizProjectProgressBoning extends DataEntity2<BizProjectProgressBoning> {

	private static final long serialVersionUID = 1L;

	private Integer orderId; // 订单id
	private String orderNumber; //订单编号
	private String orderStatus;//订单状态
	private String area;//片区
	private Integer storeId; // 门店id
	private Integer enginDepartId; // 区域id
	private String enginDepartName; // 区域名称
	private String houseIsNew; // 新房老房  1为新房  0为老房  默认老房
	private Date orderCreateDate; // 订单创建日期
	private String customerName; // 客户姓名
	private String customerPhone; // 客户电话
	private String detailAddress; // 工程地址（详细地址）
	private String contractArea;//订单合同面积
	private String buildType;//房屋类型
	private String itemManager; // 项目经理
	private String itemManagerPhone; // 项目经理手机号
	private String designerName;		// 设计师姓名
	private String designerPhone;		// 设计师电话

	private String orderInspector;		//质检员姓名
	private String inspectorPhone;      //质检员电话
	private Date contractStartDate; // 合同开工日期
	private Date contractEndDate; // 合同竣工日期
	private Date actualStartDate; // 实际开工日期
	private Date actualEndDate; // 实际竣工日期
	private Integer startDiffDay; // 开工延期天数
	private String isNeedSign; // 开工是否需要客户签字 -- '0.否；1.是
	private Integer selfDecorateDelayDays; // 自装延期天数
	private String isSelfDecorateNeedSign; // 自装是否需要客户签字 -- '0.否；1.是
	private Integer isScrap;//是否作废 
	//防盗门核尺
	private Date node1PlanSubDate;//节点1的计划提报日期  取确认开工时间
	private Date node1SubmDate;   // 节点1实际提报日期   项目经理提交的第一个申请厂家复尺单且【复尺类型是防盗门】的【提交时间时分秒】（复尺单内容为【防盗门】的创建时间）
	private Integer node1SubDiffDay;//节点1提报延期天数 （实际提报时间-计划提报日期）
	private Date node1ApplyEntryDate;//节点1期望进场日期  项目经理第一次申请防盗门复尺的【期望复尺时间】
	private Date node1ActualEntryDate;//节点1实际进场日期  
	private Integer node1EntryDiffDay;//节点1进场延期天数   （实际进场日期  -期望进场日期）
	private Date node1PlanDate; // 节点1计划完成日期
	private Date node1ActualDate; // 节点1实际完成日期   项目经理提交的第一个申请厂家复尺单且【复尺类型是防盗门】的【复尺日期】
	private Integer node1DiffDay; // 节点1正常/延期/提前天数 (实际完成日期 -计划完成日期)
	//辅材进场
	private Date node2PlanSubDate;//节点2计划提报日期 
	private Date node2SubmDate;   //节点2实际提报时间    项目经理申请第一单辅料单的【提交时间时分秒】
	private Integer node2SubDiffDay; //节点2提报延期天数 （实际提报时间-计划提报日期 ）
	private Date node2ExpectDate; // 节点2期望进场日期 项目经理申请第一单辅料单的【期望进场日期】
	private Date node2ActualExpectDate;//节点2实际进场日期  
	private Integer node2EntryDiffDay;//节点2进场延期天数   （实际进场日期  -期望进场日期）
	private Date node2PlanDate; // 节点2计划完成日期
	private Date node2ActualDate; // 节点2实际收货时间 项目经理提交的第一个【收货类型=辅料】的收货单的【实际送货日期】
	private Integer node2DiffDay; // 节点2提前天数  （实际收货时间-计划完成日期）
	//瓷砖
	private Date node3PlanSubDate;//节点3计划提报日期 
	private Date node3SubmDate;  //节点3实际提报时间 项目经理申请第一单墙地砖单的【提交时间时分秒】
	private Integer node3SubDiffDay; //节点3提报延期天数 （实际提报时间 -计划提报日期  ）
	private Date node3ExpectDate; // 节点3期望到货日期 项目经理申请第一单墙地砖单的【期望进场日期】
	private Date node3ActualExpectDate;//节点3实际进场日期  
	private Integer node3EntryDiffDay;//节点3进场延期天数   （实际进场日期  -期望到货日期）
	private Date node3PlanDate; // 节点3计划完成日期
	private Date node3ActualDate; // 节点3实际收货时间 项目经理提交的第一个【收货类型=墙地砖】的收货单的【实际送货日期】
	private Integer node3DiffDay; // 节点3提前天数 （实际收货时间-计划完成日期）
	//水电隐蔽验收
	private Date node4PlanSubDate;//节点4计划提报日期 
	private Date node4SubmDate;//节点4实际提报时间 biz_qc_bill【约检单】项目经理申请的【节点是水电隐蔽验收】的【提交时间时分秒】
	private Integer node4SubDiffDay; //节点4提报延期天数 （实际提报时间-计划提报日期 ）
	private Date node4ExpectDate;//节点4期望验收日期  biz_qc_bill【约检单】项目经理申请的【节点是水电隐蔽验收】的【期望上门日期】
	private Date node4ActualExpectDate;//节点4质检上门日期 质检员签到查询表中的【签到日期】
	private Integer node4EntryDiffDay;//节点4上门延期天数 (质检上门日期-期望验收日期)
	private Date node4PlanDate; // 节点4计划验收日期
	private Date node4ActualDate; // 节点4实际验收合格日期  biz_qc_bill【约检单】且【状态是已质检确认】且【节点是水电隐蔽验收】的验收日期accept_check_datetime
	private Integer node4DiffDay; // 节点4提前天数 （实际验收合格日期 - 计划验收日期）
	//防水验收
	private Date node5PlanSubDate;//节点5计划提报日期 
	private Date node5SubmDate;//节点5实际提报时间  biz_qc_bill【约检单】项目经理申请的【节点是防水验收】的【提交时间时分秒】
	private Integer node5SubDiffDay; //节点5提报延期天数 （实际提报时间-计划提报日期 ）
	private Date node5ExpectDate;//节点5期望验收日期  biz_qc_bill【约检单】项目经理申请的【节点是防水验收】的【期望上门日期】
	private Date node5ActualExpectDate;//节点5质检上门日期 质检员签到查询表中的【签到日期】
	private Integer node5EntryDiffDay;//节点5上门延期天数 (质检上门日期-期望验收日期)
	private Date node5PlanDate; // 节点5计划验收日期
	private Date node5ActualDate; // 节点5实际验收合格日期 biz_qc_bill【约检单】且【状态是已质检确认】且【节点是防水验收】的验收日期accept_check_datetime
	private Integer node5DiffDay; // 节点5提前天数 (实际验收合格日期 -计划验收日期)
	//橱柜核尺
	private Date node6PlanSubDate;//节点6计划提报日期 
	private Date node6SubmDate;//节点6实际提报时间  项目经理提交的第一个申请厂家复尺单且【复尺类型是橱柜】的【提交时间时分秒】
	private Integer node6SubDiffDay; //节点6提报延期天数 （实际提报时间-计划提报日期 ）
	private Date node6ExpectDate;//节点6期望进场日期  项目经理提报橱柜核尺的【期望进场日期】
	private Date node6ActualExpectDate;//节点6实际进场日期 
	private Integer node6EntryDiffDay;//节点6进场延期天数 (实际进场日期-期望进场日期)
	private Date node6PlanDate; // 节点6计划完成日期
	private Date node6ActualDate; // 节点6实际日期 项目经理提交的第一个申请厂家复尺单且【复尺类型是橱柜】的【复尺日期】
	private Integer node6DiffDay; // 节点6提前天数 (实际日期-计划完成日期)
	//瓦工验收
	private Date node7PlanSubDate;//节点7计划提报日期 
	private Date node7SubmDate;//节点7提报日期  biz_qc_bill【约检单】项目经理申请的【节点是瓦工验收】的【提交时间时分秒】
	private Integer node7SubDiffDay; //节点7提报延期天数 （实际提报时间-计划提报日期 ）
	private Date node7ExpectDate;//节点7期望验收日期 biz_qc_bill【约检单】项目经理申请的【节点是瓦工验收】的【期望上门日期】
	private Date node7ActualExpectDate;//节点7实际进场日期 
	private Integer node7EntryDiffDay;//节点7进场延期天数 (实际进场日期-期望验收日期)
	private Date node7PlanDate; // 节点7计划验收日期
	private Date node7ActualDate; // 节点7实际验收合格日期 biz_qc_bill【约检单】且【状态是已质检确认】且【节点是瓦工验收】的验收日期accept_check_datetime
	private Integer node7DiffDay; // 节点7提前天数 (实际验收合格日期-计划验收日期)
	//二期款（同瓦工验收日期）
	private Date node8PlanDate; // 节点8计划日期
	private Date node8AmountDate; // 节点8催款日期
	private Date node8ActualDate; // 节点8实际日期 客户实际收交二期款的日期，数据来源biz_pre_pm_settle_finance_receive_moeny表中receive_money_type为1的交款时间receive_money_datetime
	private Integer node8DiffDay; // 节点8提前天数
	//基础施工验收
	private Date node9PlanSubDate;//节点9计划提报日期
	private Date node9SubmDate;//节点9实际提报时间 biz_qc_bill【约检单】项目经理申请的【节点是基装验收】的【提交时间时分秒】
	private Integer node9SubDiffDay; //节点9提报延期天数 （实际提报时间-计划提报日期 ）
	private Date node9ExpectDate;//节点9期望验收日期 biz_qc_bill【约检单】项目经理申请的【节点是基装验收】的【期望上门日期】
	private Date node9ActualExpectDate;//节点9实际进场日期 
	private Integer node9EntryDiffDay;//节点9进场延期天数 (实际进场日期-期望验收日期)
	private Date node9PlanDate; // 节点9计划验收日期
	private Date node9ActualDate; // 节点9实际验收合格日期 biz_qc_bill【约检单】且【状态是已质检确认】且【节点是基装验收】的验收日期accept_check_datetime
	private Integer node9DiffDay; // 节点9提前天数 (实际验收合格日期-计划验收日期)
	//厨卫吊顶
	private Date node10PlanSubDate;//节点10计划提报日期
	private Date node10SubmDate;//节点10实际提报时间  biz_order_install_plan中【安装项是铝扣板安装】的【提交时间时分秒】
	private Integer node10SubDiffDay; //节点10提报延期天数 （实际提报时间-计划提报日期 ） 
	private Date node10ApplyEntryDate;//节点10期望进场日期  biz_order_install_plan中【安装项是铝扣板安装】的【申请进场日期】
	private Date node10ActualEntryDate;//节点10实际进场日期  biz_order_install_plan中【安装项是铝扣板安装】的【实际进场日期】
	private Integer node10EntryDiffDay;//节点10进场延期天数 (实际进场日期-期望进场日期) 
	private Date node10PlanDate; // 节点10计划完成日期
	private Date node10ActualDate; // 节点10实际完工日期 biz_order_install_plan中【安装项是铝扣板安装】的【实际完工日期
	private Date node10ActualCheckDate;//节点10实际验收日期  biz_order_install_plan中【安装项是铝扣板安装】的【实际验收日期】
	private Integer node10DiffDay; // 节点10提前天数(实际验收日期-计划完工日期)
	//洁具
	private Date node11PlanSubDate;//节点11计划提报日期
	private Date node11SubmDate;//节点11实际提报时间
	private Integer node11SubDiffDay; //节点11提报延期天数 （实际提报时间-计划提报日期 ） 
	private Date node11ApplyEntryDate;//节点11期望进场日期
	private Date node11ActualEntryDate;//节点11实际进场日期
	private Integer node11EntryDiffDay;//节点11进场延期天数 (实际进场日期-期望进场日期) 
	private Date node11PlanDate; // 节点11计划完成日期
	private Date node11InstallDate; // 节点11实际完工日期 biz_order_install_plan中【安装项是洁具】的【实际完工日期】
	private Date node11ActualDate; // 节点11实际验收日期 biz_order_install_plan中【安装项是洁具】的【实际验收日期】
	private Integer node11DiffDay; // 节点11提前天数 (实际验收日期-计划完成日期)
	//五金，灯具，开关面板
	private Date node12PlanSubDate;//节点12计划提报日期
	private Date node12SubmDate;//节点12实际提报时间
	private Integer node12SubDiffDay; //节点12提报延期天数 （实际提报时间-计划提报日期 ） 
	private Date node12ApplyEntryDate;//节点12期望进场日期
	private Date node12ActualEntryDate;//节点12实际进场日期
	private Integer node12EntryDiffDay;//节点12进场延期天数 (实际进场日期-期望进场日期) 
	private Date node12PlanDate; // 节点12计划完成日期
	private Date node12InstallDate; // 节点12计划完成日期 biz_order_install_plan中【安装项是灯具】的【实际完工日期】
	private Date node12ActualDate; // 节点12实际验收日期 biz_order_install_plan中【安装项是灯具】的【实际验收日期】
	private Integer node12DiffDay; // 节点12提前天数(实际验收日期-计划完成日期)
	//橱柜
	private Date node13PlanSubDate;//节点13计划提报日期
	private Date node13SubmDate;//节点13实际提报时间
	private Integer node13SubDiffDay; //节点13提报延期天数 （实际提报时间-计划提报日期 ） 
	private Date node13RuleDate;//节点13核尺时间     项目经理提报复尺单，类别为【橱柜】的【创建时间】
	private Date node13ApplyEntryDate;//节点13期望进场时间
	private Date node13ActualEntryDate;//节点13实际进场日期
	private Integer node13EntryDiffDay;//节点13进场延期天数 (实际进场日期-期望进场时间) 
	private Date node13PlanDate; // 节点13计划完成日期
	private Date node13InstallDate; // 节点13实际完工日期  biz_order_install_plan中【安装项是橱柜】的【实际完工日期】
	private Date node13ActualDate; // 节点13实际验收日期 biz_order_install_plan中【安装项是橱柜】的【实际验收日期】
	private Integer node13DiffDay; // 节点13提前天数(实际验收日期-计划完成日期)
	//定制衣柜
	private Date node14PlanSubDate;//节点14计划提报日期
	private Date node14SubmDate;//节点14实际提报时间
	private Integer node14SubDiffDay; //节点14提报延期天数 （实际提报时间-计划提报日期 ） 
	private Date node14RuleDate;//节点14核尺时间     项目经理提报复尺单，类别为【定制衣柜】的【创建时间】
	private Date node14ApplyEntryDate;//节点14期望进场时间
	private Date node14ActualEntryDate;//节点14实际进场日期
	private Integer node14EntryDiffDay;//节点14进场延期天数 (实际进场日期-期望进场时间) 
	private Date node14PlanDate; // 节点14计划完成日期
	private Date node14InstallDate; // 节点14实际完工日期 biz_order_install_plan中【安装项是定制衣柜】的【实际完工日期】
	private Date node14ActualDate; // 节点14实际验收日期  biz_order_install_plan中【安装项是定制衣柜】的【实际验收日期】
	private Integer node14DiffDay; // 节点14提前天数(实际验收日期-计划完成日期)
	//壁纸
	private Date node15PlanSubDate;//节点15计划提报日期
	private Date node15SubmDate;//节点15实际提报时间
	private Integer node15SubDiffDay; //节点15提报延期天数 （实际提报时间-计划提报日期 ） 
	private Date node15ApplyEntryDate;//节点15期望进场时间
	private Date node15ActualEntryDate;//节点15实际进场日期
	private Integer node15EntryDiffDay;//节点15进场延期天数 (实际进场日期-期望进场时间) 
	private Date node15PlanDate; // 节点15计划完成日期
	private Date node15InstallDate; // 节点15实际完工日期 biz_order_install_plan中【安装项是壁纸】的【实际完工日期】
	private Date node15ActualDate; // 节点15实际验收日期   biz_order_install_plan中【安装项是壁纸】的【实际验收日期】
	private Integer node15DiffDay; // 节点15提前天数(实际验收日期-计划完成日期)
	//木门，铝镁门，门窗套
	private Date node16PlanSubDate;//节点16计划提报日期
	private Date node16SubmDate;//节点16实际提报时间
	private Integer node16SubDiffDay; //节点16提报延期天数 （实际提报时间-计划提报日期 ） 
	private Date node16RuleDate;//节点16核尺时间  项目经理提交申请核尺单，类别为【木门，铝镁门，门窗套】的【创建时间】
	private Date node16ApplyEntryDate;//节点16期望验收日期
	private Date node16ActualEntryDate;//节点16实际进场日期
	private Integer node16EntryDiffDay;//节点16进场延期天数 (实际进场日期-期望验收日期) 
	private Date node16PlanDate; // 节点16计划完成日期
	private Date node16InstallDate; // 节点16实际完工日期  biz_order_install_plan中【安装项是木门】的【实际完工日期】
	private Date node16ActualDate; // 节点16实际验收日期  biz_order_install_plan中【安装项是木门】的【实际验收日期】
	private Integer node16DiffDay; // 节点16提前天数(实际验收日期-计划完成日期)
	//木地板
	private Date node17PlanSubDate;//节点17计划提报日期
	private Date node17SubmDate;//节点17实际提报时间
	private Integer node17SubDiffDay; //节点17提报延期天数 （实际提报时间-计划提报日期 ） 
	private Date node17ApplyEntryDate;//节点17期望进场时间
	private Date node17ActualEntryDate;//节点17实际进场日期
	private Integer node17EntryDiffDay;//节点17进场延期天数 (实际进场日期-期望进场时间) 
	private Date node17PlanDate; // 节点17计划完成日期
	private Date node17InstallDate; // 节点17实际完工日期  biz_order_install_plan中【安装项是木地板】的【实际完工日期】
	private Date node17ActualDate; // 节点17实际验收日期  biz_order_install_plan中【安装项是木地板】的【实际验收日期】
	private Integer node17DiffDay; // 节点17提前天数(实际验收日期-计划完成日期)
	//窗帘
	private Date node18PlanSubDate;//节点18计划提报日期
	private Date node18SubmDate;//节点18实际提报时间
	private Integer node18SubDiffDay; //节点18提报延期天数 （实际提报时间-计划提报日期 ） 
	private Date node18RuleDate;//节点18核尺时间          项目经理提报复尺单，类别为【窗帘】的创建时间
	private Date node18ApplyEntryDate;//节点18期望进场时间
	private Date node18ActualEntryDate;//节点18实际进场日期
	private Integer node18EntryDiffDay;//节点18进场延期天数 (实际进场日期-期望进场时间) 
	private Date node18PlanDate; // 节点18计划完成日期
	private Date node18InstallDate; // 节点18实际完工日期  biz_order_install_plan中【安装项是窗帘】的【实际完工日期】
	private Date node18ActualDate; // 节点18实际验收日期  biz_order_install_plan中【安装项是窗帘】的【实际验收日期】
	private Integer node18DiffDay; // 节点18提前天数(实际验收日期-计划完成日期)
	//竣工验收
	private Date node19PlanSubDate;//节点19计划提报日期
	private Date node19SubmDate;//节点19实际提报时间
	private Integer node19SubDiffDay; //节点19提报延期天数 （实际提报时间-计划提报日期 ） 
	private Date node19ExpectDate;//节点19期望验收日期
	private Date node19ActualEntryDate;//节点19实际进场日期 
	private Integer node19EntryDiffDay;//节点19进场延期天数 (实际进场日期-期望验收日期) 
	private Date node19PlanDate; // 节点19计划验收日期
	private Date node19ActualDate; // 节点19实际验收合格日期  biz_qc_bill【约检单】且【状态是已质检确认】且【节点是竣工验收】的验收日期accept_check_datetime
	private Integer node19DiffDay; // 节点19提前天数(实际验收合格日期  -计划验收日期)
	//尾款
	private Date node20PlanDate; // 节点20计划日期
	private Date node20ActualDate; // 节点20实际日期  客户实际收交二期款的日期来源，数据来源biz_pre_pm_settle_finance_receive_moeny表中receive_money_type为2的交款时间receive_money_datetime
	private Integer node20DiffDay; // 节点20提前天数
	//家电
	private Date node21PlanSubDate;//节点21计划提报日期
	private Date node21SubmDate;//节点21实际提报时间
	private Integer node21SubDiffDay; //节点21提报延期天数 （实际提报时间-计划提报日期 ） 
	private Date node21ApplyEntryDate;//节点21期望进场时间
	private Date node21ActualEntryDate;//节点21实际进场日期
	private Integer node21EntryDiffDay;//节点21进场延期天数 (实际进场日期-期望进场时间) 
	private Date node21PlanDate; // 节点21计划验收日期
	private Date node21InstallDate; // 节点21实际完工日期  biz_order_install_plan中【安装项是家电】的【实际完工日期】
	private Date node21ActualDate; // 节点21实际验收合格日期  biz_order_install_plan中【安装项是家电】的【实际验收日期】
	private Integer node21DiffDay; // 节点21提前天数 (实际验收合格日期- 计划验收日期)
	//家具
	private Date node22PlanSubDate;//节点22计划提报日期
	private Date node22SubmDate;//节点22实际提报时间
	private Integer node22SubDiffDay; //节点22提报延期天数 （实际提报时间-计划提报日期 ） 
	private Date node22ApplyEntryDate;//节点22期望进场日期
	private Date node22ActualEntryDate;//节点22实际进场日期
	private Integer node22EntryDiffDay;//节点22进场延期天数 (实际进场日期-期望进场时间) 
	private Date node22PlanDate; // 节点22计划验收日期
	private Date node22InstallDate; // 节点22实际完工日期  biz_order_install_plan中【安装项是家具】的【实际完工日期】
	private Date node22ActualDate; // 节点22实际验收合格日期  biz_order_install_plan中【安装项是家具】的【实际验收日期】
	private Integer node22DiffDay; // 节点22提前天数(实际验收合格日期-计划验收日期)

	private Date beginActualStartDate; // 实际开工日期时间段(搜索时使用)
	private Date endActualStartDate; // 实际开工日期时间段(搜索时使用)
	private Integer nodeIndex; // 节点序号
	private Date planDoneDate; // 节点计划日期
	private String nodeName; // 节点名称
	private Date nodeLastActualDate;
	private List<Integer> enginDepartIds = new ArrayList<Integer>();
	private String projectMode;
	private Integer delayDays;

	
	public Integer getDelayDays() {
		return delayDays;
	}

	public void setDelayDays(Integer delayDays) {
		this.delayDays = delayDays;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Date getNode1SubmDate() {
		return node1SubmDate;
	}

	public void setNode1SubmDate(Date node1SubmDate) {
		this.node1SubmDate = node1SubmDate;
	}

	public Date getNode2SubmDate() {
		return node2SubmDate;
	}

	public void setNode2SubmDate(Date node2SubmDate) {
		this.node2SubmDate = node2SubmDate;
	}

	public Date getNode3SubmDate() {
		return node3SubmDate;
	}

	public void setNode3SubmDate(Date node3SubmDate) {
		this.node3SubmDate = node3SubmDate;
	}

	public Date getNode4SubmDate() {
		return node4SubmDate;
	}

	public void setNode4SubmDate(Date node4SubmDate) {
		this.node4SubmDate = node4SubmDate;
	}

	public Date getNode4ExpectDate() {
		return node4ExpectDate;
	}

	public void setNode4ExpectDate(Date node4ExpectDate) {
		this.node4ExpectDate = node4ExpectDate;
	}

	public Date getNode5SubmDate() {
		return node5SubmDate;
	}

	public void setNode5SubmDate(Date node5SubmDate) {
		this.node5SubmDate = node5SubmDate;
	}

	public Date getNode5ExpectDate() {
		return node5ExpectDate;
	}

	public void setNode5ExpectDate(Date node5ExpectDate) {
		this.node5ExpectDate = node5ExpectDate;
	}

	public Date getNode6SubmDate() {
		return node6SubmDate;
	}

	public void setNode6SubmDate(Date node6SubmDate) {
		this.node6SubmDate = node6SubmDate;
	}

	public Date getNode7SubmDate() {
		return node7SubmDate;
	}

	public void setNode7SubmDate(Date node7SubmDate) {
		this.node7SubmDate = node7SubmDate;
	}

	public Date getNode7ExpectDate() {
		return node7ExpectDate;
	}

	public void setNode7ExpectDate(Date node7ExpectDate) {
		this.node7ExpectDate = node7ExpectDate;
	}

	public Date getNode9SubmDate() {
		return node9SubmDate;
	}

	public void setNode9SubmDate(Date node9SubmDate) {
		this.node9SubmDate = node9SubmDate;
	}

	public Date getNode9ExpectDate() {
		return node9ExpectDate;
	}

	public void setNode9ExpectDate(Date node9ExpectDate) {
		this.node9ExpectDate = node9ExpectDate;
	}

	public Date getNode10SubmDate() {
		return node10SubmDate;
	}

	public void setNode10SubmDate(Date node10SubmDate) {
		this.node10SubmDate = node10SubmDate;
	}

	public Date getNode10ApplyEntryDate() {
		return node10ApplyEntryDate;
	}

	public void setNode10ApplyEntryDate(Date node10ApplyEntryDate) {
		this.node10ApplyEntryDate = node10ApplyEntryDate;
	}

	public Date getNode10ActualEntryDate() {
		return node10ActualEntryDate;
	}

	public void setNode10ActualEntryDate(Date node10ActualEntryDate) {
		this.node10ActualEntryDate = node10ActualEntryDate;
	}

	public Date getNode10ActualCheckDate() {
		return node10ActualCheckDate;
	}

	public void setNode10ActualCheckDate(Date node10ActualCheckDate) {
		this.node10ActualCheckDate = node10ActualCheckDate;
	}

	public Date getNode11SubmDate() {
		return node11SubmDate;
	}

	public void setNode11SubmDate(Date node11SubmDate) {
		this.node11SubmDate = node11SubmDate;
	}

	public Date getNode11ApplyEntryDate() {
		return node11ApplyEntryDate;
	}

	public void setNode11ApplyEntryDate(Date node11ApplyEntryDate) {
		this.node11ApplyEntryDate = node11ApplyEntryDate;
	}

	public Date getNode11ActualEntryDate() {
		return node11ActualEntryDate;
	}

	public void setNode11ActualEntryDate(Date node11ActualEntryDate) {
		this.node11ActualEntryDate = node11ActualEntryDate;
	}

	public Date getNode12SubmDate() {
		return node12SubmDate;
	}

	public void setNode12SubmDate(Date node12SubmDate) {
		this.node12SubmDate = node12SubmDate;
	}

	public Date getNode12ApplyEntryDate() {
		return node12ApplyEntryDate;
	}

	public void setNode12ApplyEntryDate(Date node12ApplyEntryDate) {
		this.node12ApplyEntryDate = node12ApplyEntryDate;
	}

	public Date getNode12ActualEntryDate() {
		return node12ActualEntryDate;
	}

	public void setNode12ActualEntryDate(Date node12ActualEntryDate) {
		this.node12ActualEntryDate = node12ActualEntryDate;
	}

	public Date getNode13SubmDate() {
		return node13SubmDate;
	}

	public void setNode13SubmDate(Date node13SubmDate) {
		this.node13SubmDate = node13SubmDate;
	}

	public Date getNode13ApplyEntryDate() {
		return node13ApplyEntryDate;
	}

	public void setNode13ApplyEntryDate(Date node13ApplyEntryDate) {
		this.node13ApplyEntryDate = node13ApplyEntryDate;
	}

	public Date getNode13ActualEntryDate() {
		return node13ActualEntryDate;
	}

	public void setNode13ActualEntryDate(Date node13ActualEntryDate) {
		this.node13ActualEntryDate = node13ActualEntryDate;
	}

	public Date getNode14SubmDate() {
		return node14SubmDate;
	}

	public void setNode14SubmDate(Date node14SubmDate) {
		this.node14SubmDate = node14SubmDate;
	}

	public Date getNode14ApplyEntryDate() {
		return node14ApplyEntryDate;
	}

	public void setNode14ApplyEntryDate(Date node14ApplyEntryDate) {
		this.node14ApplyEntryDate = node14ApplyEntryDate;
	}

	public Date getNode14ActualEntryDate() {
		return node14ActualEntryDate;
	}

	public void setNode14ActualEntryDate(Date node14ActualEntryDate) {
		this.node14ActualEntryDate = node14ActualEntryDate;
	}

	public Date getNode15SubmDate() {
		return node15SubmDate;
	}

	public void setNode15SubmDate(Date node15SubmDate) {
		this.node15SubmDate = node15SubmDate;
	}

	public Date getNode15ApplyEntryDate() {
		return node15ApplyEntryDate;
	}

	public void setNode15ApplyEntryDate(Date node15ApplyEntryDate) {
		this.node15ApplyEntryDate = node15ApplyEntryDate;
	}

	public Date getNode15ActualEntryDate() {
		return node15ActualEntryDate;
	}

	public void setNode15ActualEntryDate(Date node15ActualEntryDate) {
		this.node15ActualEntryDate = node15ActualEntryDate;
	}

	public Date getNode16SubmDate() {
		return node16SubmDate;
	}

	public void setNode16SubmDate(Date node16SubmDate) {
		this.node16SubmDate = node16SubmDate;
	}

	public Date getNode16ApplyEntryDate() {
		return node16ApplyEntryDate;
	}

	public void setNode16ApplyEntryDate(Date node16ApplyEntryDate) {
		this.node16ApplyEntryDate = node16ApplyEntryDate;
	}

	public Date getNode16ActualEntryDate() {
		return node16ActualEntryDate;
	}

	public void setNode16ActualEntryDate(Date node16ActualEntryDate) {
		this.node16ActualEntryDate = node16ActualEntryDate;
	}

	public Date getNode17SubmDate() {
		return node17SubmDate;
	}

	public void setNode17SubmDate(Date node17SubmDate) {
		this.node17SubmDate = node17SubmDate;
	}

	public Date getNode17ApplyEntryDate() {
		return node17ApplyEntryDate;
	}

	public void setNode17ApplyEntryDate(Date node17ApplyEntryDate) {
		this.node17ApplyEntryDate = node17ApplyEntryDate;
	}

	public Date getNode17ActualEntryDate() {
		return node17ActualEntryDate;
	}

	public void setNode17ActualEntryDate(Date node17ActualEntryDate) {
		this.node17ActualEntryDate = node17ActualEntryDate;
	}

	public Date getNode18SubmDate() {
		return node18SubmDate;
	}

	public void setNode18SubmDate(Date node18SubmDate) {
		this.node18SubmDate = node18SubmDate;
	}

	public Date getNode18ApplyEntryDate() {
		return node18ApplyEntryDate;
	}

	public void setNode18ApplyEntryDate(Date node18ApplyEntryDate) {
		this.node18ApplyEntryDate = node18ApplyEntryDate;
	}

	public Date getNode18ActualEntryDate() {
		return node18ActualEntryDate;
	}

	public void setNode18ActualEntryDate(Date node18ActualEntryDate) {
		this.node18ActualEntryDate = node18ActualEntryDate;
	}

	public Date getNode19SubmDate() {
		return node19SubmDate;
	}

	public void setNode19SubmDate(Date node19SubmDate) {
		this.node19SubmDate = node19SubmDate;
	}

	public Date getNode19ExpectDate() {
		return node19ExpectDate;
	}

	public void setNode19ExpectDate(Date node19ExpectDate) {
		this.node19ExpectDate = node19ExpectDate;
	}

	public Date getNode21SubmDate() {
		return node21SubmDate;
	}

	public void setNode21SubmDate(Date node21SubmDate) {
		this.node21SubmDate = node21SubmDate;
	}

	public Date getNode21ApplyEntryDate() {
		return node21ApplyEntryDate;
	}

	public void setNode21ApplyEntryDate(Date node21ApplyEntryDate) {
		this.node21ApplyEntryDate = node21ApplyEntryDate;
	}

	public Date getNode21ActualEntryDate() {
		return node21ActualEntryDate;
	}

	public void setNode21ActualEntryDate(Date node21ActualEntryDate) {
		this.node21ActualEntryDate = node21ActualEntryDate;
	}

	public Date getNode22SubmDate() {
		return node22SubmDate;
	}

	public void setNode22SubmDate(Date node22SubmDate) {
		this.node22SubmDate = node22SubmDate;
	}

	public Date getNode22ApplyEntryDate() {
		return node22ApplyEntryDate;
	}

	public void setNode22ApplyEntryDate(Date node22ApplyEntryDate) {
		this.node22ApplyEntryDate = node22ApplyEntryDate;
	}

	public Date getNode22ActualEntryDate() {
		return node22ActualEntryDate;
	}

	public void setNode22ActualEntryDate(Date node22ActualEntryDate) {
		this.node22ActualEntryDate = node22ActualEntryDate;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getHouseIsNew() {
		return houseIsNew;
	}

	public void setHouseIsNew(String houseIsNew) {
		this.houseIsNew = houseIsNew;
	}

	public Date getOrderCreateDate() {
		return orderCreateDate;
	}

	public void setOrderCreateDate(Date orderCreateDate) {
		this.orderCreateDate = orderCreateDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public String getItemManagerPhone() {
		return itemManagerPhone;
	}

	public void setItemManagerPhone(String itemManagerPhone) {
		this.itemManagerPhone = itemManagerPhone;
	}

	public Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public Date getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public Integer getStartDiffDay() {
		return startDiffDay;
	}

	public void setStartDiffDay(Integer startDiffDay) {
		this.startDiffDay = startDiffDay;
	}

	public String getIsNeedSign() {
		return isNeedSign;
	}

	public void setIsNeedSign(String isNeedSign) {
		this.isNeedSign = isNeedSign;
	}

	public Integer getSelfDecorateDelayDays() {
		return selfDecorateDelayDays;
	}

	public void setSelfDecorateDelayDays(Integer selfDecorateDelayDays) {
		this.selfDecorateDelayDays = selfDecorateDelayDays;
	}

	public String getIsSelfDecorateNeedSign() {
		return isSelfDecorateNeedSign;
	}

	public void setIsSelfDecorateNeedSign(String isSelfDecorateNeedSign) {
		this.isSelfDecorateNeedSign = isSelfDecorateNeedSign;
	}

	public Date getNode1PlanDate() {
		return node1PlanDate;
	}

	public void setNode1PlanDate(Date node1PlanDate) {
		this.node1PlanDate = node1PlanDate;
	}

	public Date getNode1ActualDate() {
		return node1ActualDate;
	}

	public void setNode1ActualDate(Date node1ActualDate) {
		this.node1ActualDate = node1ActualDate;
	}

	public Integer getNode1DiffDay() {
		return node1DiffDay;
	}

	public void setNode1DiffDay(Integer node1DiffDay) {
		this.node1DiffDay = node1DiffDay;
	}

	public Date getNode2PlanDate() {
		return node2PlanDate;
	}

	public void setNode2PlanDate(Date node2PlanDate) {
		this.node2PlanDate = node2PlanDate;
	}

	public Date getNode2ExpectDate() {
		return node2ExpectDate;
	}

	public void setNode2ExpectDate(Date node2ExpectDate) {
		this.node2ExpectDate = node2ExpectDate;
	}

	public Date getNode2ActualDate() {
		return node2ActualDate;
	}

	public void setNode2ActualDate(Date node2ActualDate) {
		this.node2ActualDate = node2ActualDate;
	}

	public Integer getNode2DiffDay() {
		return node2DiffDay;
	}

	public void setNode2DiffDay(Integer node2DiffDay) {
		this.node2DiffDay = node2DiffDay;
	}

	public Date getNode3PlanDate() {
		return node3PlanDate;
	}

	public void setNode3PlanDate(Date node3PlanDate) {
		this.node3PlanDate = node3PlanDate;
	}

	public Date getNode3ExpectDate() {
		return node3ExpectDate;
	}

	public void setNode3ExpectDate(Date node3ExpectDate) {
		this.node3ExpectDate = node3ExpectDate;
	}

	public Date getNode3ActualDate() {
		return node3ActualDate;
	}

	public void setNode3ActualDate(Date node3ActualDate) {
		this.node3ActualDate = node3ActualDate;
	}

	public Integer getNode3DiffDay() {
		return node3DiffDay;
	}

	public void setNode3DiffDay(Integer node3DiffDay) {
		this.node3DiffDay = node3DiffDay;
	}

	public Date getNode4PlanDate() {
		return node4PlanDate;
	}

	public void setNode4PlanDate(Date node4PlanDate) {
		this.node4PlanDate = node4PlanDate;
	}

	public Date getNode4ActualDate() {
		return node4ActualDate;
	}

	public void setNode4ActualDate(Date node4ActualDate) {
		this.node4ActualDate = node4ActualDate;
	}

	public Integer getNode4DiffDay() {
		return node4DiffDay;
	}

	public void setNode4DiffDay(Integer node4DiffDay) {
		this.node4DiffDay = node4DiffDay;
	}

	public Date getNode5PlanDate() {
		return node5PlanDate;
	}

	public void setNode5PlanDate(Date node5PlanDate) {
		this.node5PlanDate = node5PlanDate;
	}

	public Date getNode5ActualDate() {
		return node5ActualDate;
	}

	public void setNode5ActualDate(Date node5ActualDate) {
		this.node5ActualDate = node5ActualDate;
	}

	public Integer getNode5DiffDay() {
		return node5DiffDay;
	}

	public void setNode5DiffDay(Integer node5DiffDay) {
		this.node5DiffDay = node5DiffDay;
	}

	public Date getNode6PlanDate() {
		return node6PlanDate;
	}

	public void setNode6PlanDate(Date node6PlanDate) {
		this.node6PlanDate = node6PlanDate;
	}

	public Date getNode6ActualDate() {
		return node6ActualDate;
	}

	public void setNode6ActualDate(Date node6ActualDate) {
		this.node6ActualDate = node6ActualDate;
	}

	public Integer getNode6DiffDay() {
		return node6DiffDay;
	}

	public void setNode6DiffDay(Integer node6DiffDay) {
		this.node6DiffDay = node6DiffDay;
	}

	public Date getNode7PlanDate() {
		return node7PlanDate;
	}

	public void setNode7PlanDate(Date node7PlanDate) {
		this.node7PlanDate = node7PlanDate;
	}

	public Date getNode7ActualDate() {
		return node7ActualDate;
	}

	public void setNode7ActualDate(Date node7ActualDate) {
		this.node7ActualDate = node7ActualDate;
	}

	public Integer getNode7DiffDay() {
		return node7DiffDay;
	}

	public void setNode7DiffDay(Integer node7DiffDay) {
		this.node7DiffDay = node7DiffDay;
	}

	public Date getNode8PlanDate() {
		return node8PlanDate;
	}

	public void setNode8PlanDate(Date node8PlanDate) {
		this.node8PlanDate = node8PlanDate;
	}

	public Date getNode8AmountDate() {
		return node8AmountDate;
	}

	public void setNode8AmountDate(Date node8AmountDate) {
		this.node8AmountDate = node8AmountDate;
	}

	public Date getNode8ActualDate() {
		return node8ActualDate;
	}

	public void setNode8ActualDate(Date node8ActualDate) {
		this.node8ActualDate = node8ActualDate;
	}

	public Integer getNode8DiffDay() {
		return node8DiffDay;
	}

	public void setNode8DiffDay(Integer node8DiffDay) {
		this.node8DiffDay = node8DiffDay;
	}

	public Date getNode9PlanDate() {
		return node9PlanDate;
	}

	public void setNode9PlanDate(Date node9PlanDate) {
		this.node9PlanDate = node9PlanDate;
	}

	public Date getNode9ActualDate() {
		return node9ActualDate;
	}

	public void setNode9ActualDate(Date node9ActualDate) {
		this.node9ActualDate = node9ActualDate;
	}

	public Integer getNode9DiffDay() {
		return node9DiffDay;
	}

	public void setNode9DiffDay(Integer node9DiffDay) {
		this.node9DiffDay = node9DiffDay;
	}

	public Date getNode10PlanDate() {
		return node10PlanDate;
	}

	public void setNode10PlanDate(Date node10PlanDate) {
		this.node10PlanDate = node10PlanDate;
	}

	public Date getNode10ActualDate() {
		return node10ActualDate;
	}

	public void setNode10ActualDate(Date node10ActualDate) {
		this.node10ActualDate = node10ActualDate;
	}

	public Integer getNode10DiffDay() {
		return node10DiffDay;
	}

	public void setNode10DiffDay(Integer node10DiffDay) {
		this.node10DiffDay = node10DiffDay;
	}

	public Date getNode11PlanDate() {
		return node11PlanDate;
	}

	public void setNode11PlanDate(Date node11PlanDate) {
		this.node11PlanDate = node11PlanDate;
	}

	public Date getNode11InstallDate() {
		return node11InstallDate;
	}

	public void setNode11InstallDate(Date node11InstallDate) {
		this.node11InstallDate = node11InstallDate;
	}

	public Date getNode11ActualDate() {
		return node11ActualDate;
	}

	public void setNode11ActualDate(Date node11ActualDate) {
		this.node11ActualDate = node11ActualDate;
	}

	public Integer getNode11DiffDay() {
		return node11DiffDay;
	}

	public void setNode11DiffDay(Integer node11DiffDay) {
		this.node11DiffDay = node11DiffDay;
	}

	public Date getNode12PlanDate() {
		return node12PlanDate;
	}

	public void setNode12PlanDate(Date node12PlanDate) {
		this.node12PlanDate = node12PlanDate;
	}

	public Date getNode12InstallDate() {
		return node12InstallDate;
	}

	public void setNode12InstallDate(Date node12InstallDate) {
		this.node12InstallDate = node12InstallDate;
	}

	public Date getNode12ActualDate() {
		return node12ActualDate;
	}

	public void setNode12ActualDate(Date node12ActualDate) {
		this.node12ActualDate = node12ActualDate;
	}

	public Integer getNode12DiffDay() {
		return node12DiffDay;
	}

	public void setNode12DiffDay(Integer node12DiffDay) {
		this.node12DiffDay = node12DiffDay;
	}

	public Date getNode13PlanDate() {
		return node13PlanDate;
	}

	public void setNode13PlanDate(Date node13PlanDate) {
		this.node13PlanDate = node13PlanDate;
	}

	public Date getNode13InstallDate() {
		return node13InstallDate;
	}

	public void setNode13InstallDate(Date node13InstallDate) {
		this.node13InstallDate = node13InstallDate;
	}

	public Date getNode13ActualDate() {
		return node13ActualDate;
	}

	public void setNode13ActualDate(Date node13ActualDate) {
		this.node13ActualDate = node13ActualDate;
	}

	public Integer getNode13DiffDay() {
		return node13DiffDay;
	}

	public void setNode13DiffDay(Integer node13DiffDay) {
		this.node13DiffDay = node13DiffDay;
	}

	public Date getNode14PlanDate() {
		return node14PlanDate;
	}

	public void setNode14PlanDate(Date node14PlanDate) {
		this.node14PlanDate = node14PlanDate;
	}

	public Date getNode14InstallDate() {
		return node14InstallDate;
	}

	public void setNode14InstallDate(Date node14InstallDate) {
		this.node14InstallDate = node14InstallDate;
	}

	public Date getNode14ActualDate() {
		return node14ActualDate;
	}

	public void setNode14ActualDate(Date node14ActualDate) {
		this.node14ActualDate = node14ActualDate;
	}

	public Integer getNode14DiffDay() {
		return node14DiffDay;
	}

	public void setNode14DiffDay(Integer node14DiffDay) {
		this.node14DiffDay = node14DiffDay;
	}

	public Date getNode15PlanDate() {
		return node15PlanDate;
	}

	public void setNode15PlanDate(Date node15PlanDate) {
		this.node15PlanDate = node15PlanDate;
	}

	public Date getNode15InstallDate() {
		return node15InstallDate;
	}

	public void setNode15InstallDate(Date node15InstallDate) {
		this.node15InstallDate = node15InstallDate;
	}

	public Date getNode15ActualDate() {
		return node15ActualDate;
	}

	public void setNode15ActualDate(Date node15ActualDate) {
		this.node15ActualDate = node15ActualDate;
	}

	public Integer getNode15DiffDay() {
		return node15DiffDay;
	}

	public void setNode15DiffDay(Integer node15DiffDay) {
		this.node15DiffDay = node15DiffDay;
	}

	public Date getNode16PlanDate() {
		return node16PlanDate;
	}

	public void setNode16PlanDate(Date node16PlanDate) {
		this.node16PlanDate = node16PlanDate;
	}

	public Date getNode16InstallDate() {
		return node16InstallDate;
	}

	public void setNode16InstallDate(Date node16InstallDate) {
		this.node16InstallDate = node16InstallDate;
	}

	public Date getNode16ActualDate() {
		return node16ActualDate;
	}

	public void setNode16ActualDate(Date node16ActualDate) {
		this.node16ActualDate = node16ActualDate;
	}

	public Integer getNode16DiffDay() {
		return node16DiffDay;
	}

	public void setNode16DiffDay(Integer node16DiffDay) {
		this.node16DiffDay = node16DiffDay;
	}

	public Date getNode17PlanDate() {
		return node17PlanDate;
	}

	public void setNode17PlanDate(Date node17PlanDate) {
		this.node17PlanDate = node17PlanDate;
	}

	public Date getNode17InstallDate() {
		return node17InstallDate;
	}

	public void setNode17InstallDate(Date node17InstallDate) {
		this.node17InstallDate = node17InstallDate;
	}

	public Date getNode17ActualDate() {
		return node17ActualDate;
	}

	public void setNode17ActualDate(Date node17ActualDate) {
		this.node17ActualDate = node17ActualDate;
	}

	public Integer getNode17DiffDay() {
		return node17DiffDay;
	}

	public void setNode17DiffDay(Integer node17DiffDay) {
		this.node17DiffDay = node17DiffDay;
	}

	public Date getNode18PlanDate() {
		return node18PlanDate;
	}

	public void setNode18PlanDate(Date node18PlanDate) {
		this.node18PlanDate = node18PlanDate;
	}

	public Date getNode18InstallDate() {
		return node18InstallDate;
	}

	public void setNode18InstallDate(Date node18InstallDate) {
		this.node18InstallDate = node18InstallDate;
	}

	public Date getNode18ActualDate() {
		return node18ActualDate;
	}

	public void setNode18ActualDate(Date node18ActualDate) {
		this.node18ActualDate = node18ActualDate;
	}

	public Integer getNode18DiffDay() {
		return node18DiffDay;
	}

	public void setNode18DiffDay(Integer node18DiffDay) {
		this.node18DiffDay = node18DiffDay;
	}

	public Date getNode19PlanDate() {
		return node19PlanDate;
	}

	public void setNode19PlanDate(Date node19PlanDate) {
		this.node19PlanDate = node19PlanDate;
	}

	public Date getNode19ActualDate() {
		return node19ActualDate;
	}

	public void setNode19ActualDate(Date node19ActualDate) {
		this.node19ActualDate = node19ActualDate;
	}

	public Integer getNode19DiffDay() {
		return node19DiffDay;
	}

	public void setNode19DiffDay(Integer node19DiffDay) {
		this.node19DiffDay = node19DiffDay;
	}

	public Date getNode20PlanDate() {
		return node20PlanDate;
	}

	public void setNode20PlanDate(Date node20PlanDate) {
		this.node20PlanDate = node20PlanDate;
	}

	public Date getNode20ActualDate() {
		return node20ActualDate;
	}

	public void setNode20ActualDate(Date node20ActualDate) {
		this.node20ActualDate = node20ActualDate;
	}

	public Integer getNode20DiffDay() {
		return node20DiffDay;
	}

	public void setNode20DiffDay(Integer node20DiffDay) {
		this.node20DiffDay = node20DiffDay;
	}

	public Date getNode21PlanDate() {
		return node21PlanDate;
	}

	public void setNode21PlanDate(Date node21PlanDate) {
		this.node21PlanDate = node21PlanDate;
	}

	public Date getNode21InstallDate() {
		return node21InstallDate;
	}

	public void setNode21InstallDate(Date node21InstallDate) {
		this.node21InstallDate = node21InstallDate;
	}

	public Date getNode21ActualDate() {
		return node21ActualDate;
	}

	public void setNode21ActualDate(Date node21ActualDate) {
		this.node21ActualDate = node21ActualDate;
	}

	public Integer getNode21DiffDay() {
		return node21DiffDay;
	}

	public void setNode21DiffDay(Integer node21DiffDay) {
		this.node21DiffDay = node21DiffDay;
	}

	public Date getNode22PlanDate() {
		return node22PlanDate;
	}

	public void setNode22PlanDate(Date node22PlanDate) {
		this.node22PlanDate = node22PlanDate;
	}

	public Date getNode22InstallDate() {
		return node22InstallDate;
	}

	public void setNode22InstallDate(Date node22InstallDate) {
		this.node22InstallDate = node22InstallDate;
	}

	public Date getNode22ActualDate() {
		return node22ActualDate;
	}

	public void setNode22ActualDate(Date node22ActualDate) {
		this.node22ActualDate = node22ActualDate;
	}

	public Integer getNode22DiffDay() {
		return node22DiffDay;
	}

	public void setNode22DiffDay(Integer node22DiffDay) {
		this.node22DiffDay = node22DiffDay;
	}

	public Date getBeginActualStartDate() {
		return beginActualStartDate;
	}

	public void setBeginActualStartDate(Date beginActualStartDate) {
		this.beginActualStartDate = beginActualStartDate;
	}

	public Date getEndActualStartDate() {
		return endActualStartDate;
	}

	public void setEndActualStartDate(Date endActualStartDate) {
		this.endActualStartDate = endActualStartDate;
	}

	public Integer getNodeIndex() {
		return nodeIndex;
	}

	public void setNodeIndex(Integer nodeIndex) {
		this.nodeIndex = nodeIndex;
	}

	public Date getPlanDoneDate() {
		return planDoneDate;
	}

	public void setPlanDoneDate(Date planDoneDate) {
		this.planDoneDate = planDoneDate;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public Date getNodeLastActualDate() {
		return nodeLastActualDate;
	}

	public void setNodeLastActualDate(Date nodeLastActualDate) {
		this.nodeLastActualDate = nodeLastActualDate;
	}

	public Integer getEnginDepartId() {
		return enginDepartId;
	}

	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}

	public String getEnginDepartName() {
		return enginDepartName;
	}

	public void setEnginDepartName(String enginDepartName) {
		this.enginDepartName = enginDepartName;
	}

	public List<Integer> getEnginDepartIds() {
		return enginDepartIds;
	}

	public void setEnginDepartIds(List<Integer> enginDepartIds) {
		this.enginDepartIds = enginDepartIds;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
	public String getDesignerName() {
		return designerName;
	}

	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}

	public String getDesignerPhone() {
		return designerPhone;
	}

	public void setDesignerPhone(String designerPhone) {
		this.designerPhone = designerPhone;
	}

	public String getOrderInspector() {
		return orderInspector;
	}

	public void setOrderInspector(String orderInspector) {
		this.orderInspector = orderInspector;
	}

	public String getInspectorPhone() {
		return inspectorPhone;
	}

	public void setInspectorPhone(String inspectorPhone) {
		this.inspectorPhone = inspectorPhone;
	}

	public Integer getIsScrap() {
		return isScrap;
	}

	public void setIsScrap(Integer isScrap) {
		this.isScrap = isScrap;
	}

	public String getContractArea() {
		return contractArea;
	}

	public void setContractArea(String contractArea) {
		this.contractArea = contractArea;
	}

	public String getBuildType() {
		return buildType;
	}

	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}

	public Date getNode1PlanSubDate() {
		return node1PlanSubDate;
	}

	public void setNode1PlanSubDate(Date node1PlanSubDate) {
		this.node1PlanSubDate = node1PlanSubDate;
	}

	public Integer getNode1SubDiffDay() {
		return node1SubDiffDay;
	}

	public void setNode1SubDiffDay(Integer node1SubDiffDay) {
		this.node1SubDiffDay = node1SubDiffDay;
	}

	public Date getNode1ApplyEntryDate() {
		return node1ApplyEntryDate;
	}

	public void setNode1ApplyEntryDate(Date node1ApplyEntryDate) {
		this.node1ApplyEntryDate = node1ApplyEntryDate;
	}

	public Date getNode1ActualEntryDate() {
		return node1ActualEntryDate;
	}

	public void setNode1ActualEntryDate(Date node1ActualEntryDate) {
		this.node1ActualEntryDate = node1ActualEntryDate;
	}

	public Integer getNode1EntryDiffDay() {
		return node1EntryDiffDay;
	}

	public void setNode1EntryDiffDay(Integer node1EntryDiffDay) {
		this.node1EntryDiffDay = node1EntryDiffDay;
	}

	public Date getNode2PlanSubDate() {
		return node2PlanSubDate;
	}

	public void setNode2PlanSubDate(Date node2PlanSubDate) {
		this.node2PlanSubDate = node2PlanSubDate;
	}

	public Integer getNode2SubDiffDay() {
		return node2SubDiffDay;
	}

	public void setNode2SubDiffDay(Integer node2SubDiffDay) {
		this.node2SubDiffDay = node2SubDiffDay;
	}

	public Date getNode2ActualExpectDate() {
		return node2ActualExpectDate;
	}

	public void setNode2ActualExpectDate(Date node2ActualExpectDate) {
		this.node2ActualExpectDate = node2ActualExpectDate;
	}

	public Integer getNode2EntryDiffDay() {
		return node2EntryDiffDay;
	}

	public void setNode2EntryDiffDay(Integer node2EntryDiffDay) {
		this.node2EntryDiffDay = node2EntryDiffDay;
	}

	public Date getNode3PlanSubDate() {
		return node3PlanSubDate;
	}

	public void setNode3PlanSubDate(Date node3PlanSubDate) {
		this.node3PlanSubDate = node3PlanSubDate;
	}

	public Integer getNode3SubDiffDay() {
		return node3SubDiffDay;
	}

	public void setNode3SubDiffDay(Integer node3SubDiffDay) {
		this.node3SubDiffDay = node3SubDiffDay;
	}

	public Date getNode3ActualExpectDate() {
		return node3ActualExpectDate;
	}

	public void setNode3ActualExpectDate(Date node3ActualExpectDate) {
		this.node3ActualExpectDate = node3ActualExpectDate;
	}

	public Integer getNode3EntryDiffDay() {
		return node3EntryDiffDay;
	}

	public void setNode3EntryDiffDay(Integer node3EntryDiffDay) {
		this.node3EntryDiffDay = node3EntryDiffDay;
	}

	public Date getNode4PlanSubDate() {
		return node4PlanSubDate;
	}

	public void setNode4PlanSubDate(Date node4PlanSubDate) {
		this.node4PlanSubDate = node4PlanSubDate;
	}

	public Integer getNode4SubDiffDay() {
		return node4SubDiffDay;
	}

	public void setNode4SubDiffDay(Integer node4SubDiffDay) {
		this.node4SubDiffDay = node4SubDiffDay;
	}

	public Date getNode4ActualExpectDate() {
		return node4ActualExpectDate;
	}

	public void setNode4ActualExpectDate(Date node4ActualExpectDate) {
		this.node4ActualExpectDate = node4ActualExpectDate;
	}

	public Integer getNode4EntryDiffDay() {
		return node4EntryDiffDay;
	}

	public void setNode4EntryDiffDay(Integer node4EntryDiffDay) {
		this.node4EntryDiffDay = node4EntryDiffDay;
	}

	public Date getNode5PlanSubDate() {
		return node5PlanSubDate;
	}

	public void setNode5PlanSubDate(Date node5PlanSubDate) {
		this.node5PlanSubDate = node5PlanSubDate;
	}

	public Integer getNode5SubDiffDay() {
		return node5SubDiffDay;
	}

	public void setNode5SubDiffDay(Integer node5SubDiffDay) {
		this.node5SubDiffDay = node5SubDiffDay;
	}

	public Date getNode5ActualExpectDate() {
		return node5ActualExpectDate;
	}

	public void setNode5ActualExpectDate(Date node5ActualExpectDate) {
		this.node5ActualExpectDate = node5ActualExpectDate;
	}

	public Integer getNode5EntryDiffDay() {
		return node5EntryDiffDay;
	}

	public void setNode5EntryDiffDay(Integer node5EntryDiffDay) {
		this.node5EntryDiffDay = node5EntryDiffDay;
	}

	public Date getNode6PlanSubDate() {
		return node6PlanSubDate;
	}

	public void setNode6PlanSubDate(Date node6PlanSubDate) {
		this.node6PlanSubDate = node6PlanSubDate;
	}

	public Integer getNode6SubDiffDay() {
		return node6SubDiffDay;
	}

	public void setNode6SubDiffDay(Integer node6SubDiffDay) {
		this.node6SubDiffDay = node6SubDiffDay;
	}

	public Date getNode6ExpectDate() {
		return node6ExpectDate;
	}

	public void setNode6ExpectDate(Date node6ExpectDate) {
		this.node6ExpectDate = node6ExpectDate;
	}

	public Date getNode6ActualExpectDate() {
		return node6ActualExpectDate;
	}

	public void setNode6ActualExpectDate(Date node6ActualExpectDate) {
		this.node6ActualExpectDate = node6ActualExpectDate;
	}

	public Integer getNode6EntryDiffDay() {
		return node6EntryDiffDay;
	}

	public void setNode6EntryDiffDay(Integer node6EntryDiffDay) {
		this.node6EntryDiffDay = node6EntryDiffDay;
	}

	public Date getNode7PlanSubDate() {
		return node7PlanSubDate;
	}

	public void setNode7PlanSubDate(Date node7PlanSubDate) {
		this.node7PlanSubDate = node7PlanSubDate;
	}

	public Integer getNode7SubDiffDay() {
		return node7SubDiffDay;
	}

	public void setNode7SubDiffDay(Integer node7SubDiffDay) {
		this.node7SubDiffDay = node7SubDiffDay;
	}

	public Date getNode7ActualExpectDate() {
		return node7ActualExpectDate;
	}

	public void setNode7ActualExpectDate(Date node7ActualExpectDate) {
		this.node7ActualExpectDate = node7ActualExpectDate;
	}

	public Integer getNode7EntryDiffDay() {
		return node7EntryDiffDay;
	}

	public void setNode7EntryDiffDay(Integer node7EntryDiffDay) {
		this.node7EntryDiffDay = node7EntryDiffDay;
	}

	public Date getNode9PlanSubDate() {
		return node9PlanSubDate;
	}

	public void setNode9PlanSubDate(Date node9PlanSubDate) {
		this.node9PlanSubDate = node9PlanSubDate;
	}

	public Integer getNode9SubDiffDay() {
		return node9SubDiffDay;
	}

	public void setNode9SubDiffDay(Integer node9SubDiffDay) {
		this.node9SubDiffDay = node9SubDiffDay;
	}

	public Date getNode9ActualExpectDate() {
		return node9ActualExpectDate;
	}

	public void setNode9ActualExpectDate(Date node9ActualExpectDate) {
		this.node9ActualExpectDate = node9ActualExpectDate;
	}

	public Integer getNode9EntryDiffDay() {
		return node9EntryDiffDay;
	}

	public void setNode9EntryDiffDay(Integer node9EntryDiffDay) {
		this.node9EntryDiffDay = node9EntryDiffDay;
	}

	public Date getNode10PlanSubDate() {
		return node10PlanSubDate;
	}

	public void setNode10PlanSubDate(Date node10PlanSubDate) {
		this.node10PlanSubDate = node10PlanSubDate;
	}

	public Integer getNode10SubDiffDay() {
		return node10SubDiffDay;
	}

	public void setNode10SubDiffDay(Integer node10SubDiffDay) {
		this.node10SubDiffDay = node10SubDiffDay;
	}

	public Integer getNode10EntryDiffDay() {
		return node10EntryDiffDay;
	}

	public void setNode10EntryDiffDay(Integer node10EntryDiffDay) {
		this.node10EntryDiffDay = node10EntryDiffDay;
	}

	public Date getNode11PlanSubDate() {
		return node11PlanSubDate;
	}

	public void setNode11PlanSubDate(Date node11PlanSubDate) {
		this.node11PlanSubDate = node11PlanSubDate;
	}

	public Integer getNode11SubDiffDay() {
		return node11SubDiffDay;
	}

	public void setNode11SubDiffDay(Integer node11SubDiffDay) {
		this.node11SubDiffDay = node11SubDiffDay;
	}

	public Integer getNode11EntryDiffDay() {
		return node11EntryDiffDay;
	}

	public void setNode11EntryDiffDay(Integer node11EntryDiffDay) {
		this.node11EntryDiffDay = node11EntryDiffDay;
	}

	public Date getNode12PlanSubDate() {
		return node12PlanSubDate;
	}

	public void setNode12PlanSubDate(Date node12PlanSubDate) {
		this.node12PlanSubDate = node12PlanSubDate;
	}

	public Integer getNode12SubDiffDay() {
		return node12SubDiffDay;
	}

	public void setNode12SubDiffDay(Integer node12SubDiffDay) {
		this.node12SubDiffDay = node12SubDiffDay;
	}

	public Integer getNode12EntryDiffDay() {
		return node12EntryDiffDay;
	}

	public void setNode12EntryDiffDay(Integer node12EntryDiffDay) {
		this.node12EntryDiffDay = node12EntryDiffDay;
	}

	public Date getNode13PlanSubDate() {
		return node13PlanSubDate;
	}

	public void setNode13PlanSubDate(Date node13PlanSubDate) {
		this.node13PlanSubDate = node13PlanSubDate;
	}

	public Integer getNode13SubDiffDay() {
		return node13SubDiffDay;
	}

	public void setNode13SubDiffDay(Integer node13SubDiffDay) {
		this.node13SubDiffDay = node13SubDiffDay;
	}

	public Date getNode13RuleDate() {
		return node13RuleDate;
	}

	public void setNode13RuleDate(Date node13RuleDate) {
		this.node13RuleDate = node13RuleDate;
	}

	public Integer getNode13EntryDiffDay() {
		return node13EntryDiffDay;
	}

	public void setNode13EntryDiffDay(Integer node13EntryDiffDay) {
		this.node13EntryDiffDay = node13EntryDiffDay;
	}

	public Date getNode14PlanSubDate() {
		return node14PlanSubDate;
	}

	public void setNode14PlanSubDate(Date node14PlanSubDate) {
		this.node14PlanSubDate = node14PlanSubDate;
	}

	public Integer getNode14SubDiffDay() {
		return node14SubDiffDay;
	}

	public void setNode14SubDiffDay(Integer node14SubDiffDay) {
		this.node14SubDiffDay = node14SubDiffDay;
	}

	public Date getNode14RuleDate() {
		return node14RuleDate;
	}

	public void setNode14RuleDate(Date node14RuleDate) {
		this.node14RuleDate = node14RuleDate;
	}

	public Integer getNode14EntryDiffDay() {
		return node14EntryDiffDay;
	}

	public void setNode14EntryDiffDay(Integer node14EntryDiffDay) {
		this.node14EntryDiffDay = node14EntryDiffDay;
	}

	public Date getNode15PlanSubDate() {
		return node15PlanSubDate;
	}

	public void setNode15PlanSubDate(Date node15PlanSubDate) {
		this.node15PlanSubDate = node15PlanSubDate;
	}

	public Integer getNode15SubDiffDay() {
		return node15SubDiffDay;
	}

	public void setNode15SubDiffDay(Integer node15SubDiffDay) {
		this.node15SubDiffDay = node15SubDiffDay;
	}

	public Integer getNode15EntryDiffDay() {
		return node15EntryDiffDay;
	}

	public void setNode15EntryDiffDay(Integer node15EntryDiffDay) {
		this.node15EntryDiffDay = node15EntryDiffDay;
	}

	public Date getNode16PlanSubDate() {
		return node16PlanSubDate;
	}

	public void setNode16PlanSubDate(Date node16PlanSubDate) {
		this.node16PlanSubDate = node16PlanSubDate;
	}

	public Integer getNode16SubDiffDay() {
		return node16SubDiffDay;
	}

	public void setNode16SubDiffDay(Integer node16SubDiffDay) {
		this.node16SubDiffDay = node16SubDiffDay;
	}

	public Date getNode16RuleDate() {
		return node16RuleDate;
	}

	public void setNode16RuleDate(Date node16RuleDate) {
		this.node16RuleDate = node16RuleDate;
	}

	public Integer getNode16EntryDiffDay() {
		return node16EntryDiffDay;
	}

	public void setNode16EntryDiffDay(Integer node16EntryDiffDay) {
		this.node16EntryDiffDay = node16EntryDiffDay;
	}

	public Date getNode17PlanSubDate() {
		return node17PlanSubDate;
	}

	public void setNode17PlanSubDate(Date node17PlanSubDate) {
		this.node17PlanSubDate = node17PlanSubDate;
	}

	public Integer getNode17SubDiffDay() {
		return node17SubDiffDay;
	}

	public void setNode17SubDiffDay(Integer node17SubDiffDay) {
		this.node17SubDiffDay = node17SubDiffDay;
	}

	public Integer getNode17EntryDiffDay() {
		return node17EntryDiffDay;
	}

	public void setNode17EntryDiffDay(Integer node17EntryDiffDay) {
		this.node17EntryDiffDay = node17EntryDiffDay;
	}

	public Date getNode18PlanSubDate() {
		return node18PlanSubDate;
	}

	public void setNode18PlanSubDate(Date node18PlanSubDate) {
		this.node18PlanSubDate = node18PlanSubDate;
	}

	public Integer getNode18SubDiffDay() {
		return node18SubDiffDay;
	}

	public void setNode18SubDiffDay(Integer node18SubDiffDay) {
		this.node18SubDiffDay = node18SubDiffDay;
	}

	public Date getNode18RuleDate() {
		return node18RuleDate;
	}

	public void setNode18RuleDate(Date node18RuleDate) {
		this.node18RuleDate = node18RuleDate;
	}

	public Integer getNode18EntryDiffDay() {
		return node18EntryDiffDay;
	}

	public void setNode18EntryDiffDay(Integer node18EntryDiffDay) {
		this.node18EntryDiffDay = node18EntryDiffDay;
	}

	public Date getNode19PlanSubDate() {
		return node19PlanSubDate;
	}

	public void setNode19PlanSubDate(Date node19PlanSubDate) {
		this.node19PlanSubDate = node19PlanSubDate;
	}

	public Integer getNode19SubDiffDay() {
		return node19SubDiffDay;
	}

	public void setNode19SubDiffDay(Integer node19SubDiffDay) {
		this.node19SubDiffDay = node19SubDiffDay;
	}

	public Date getNode19ActualEntryDate() {
		return node19ActualEntryDate;
	}

	public void setNode19ActualEntryDate(Date node19ActualEntryDate) {
		this.node19ActualEntryDate = node19ActualEntryDate;
	}

	public Integer getNode19EntryDiffDay() {
		return node19EntryDiffDay;
	}

	public void setNode19EntryDiffDay(Integer node19EntryDiffDay) {
		this.node19EntryDiffDay = node19EntryDiffDay;
	}

	public Date getNode21PlanSubDate() {
		return node21PlanSubDate;
	}

	public void setNode21PlanSubDate(Date node21PlanSubDate) {
		this.node21PlanSubDate = node21PlanSubDate;
	}

	public Integer getNode21SubDiffDay() {
		return node21SubDiffDay;
	}

	public void setNode21SubDiffDay(Integer node21SubDiffDay) {
		this.node21SubDiffDay = node21SubDiffDay;
	}

	public Integer getNode21EntryDiffDay() {
		return node21EntryDiffDay;
	}

	public void setNode21EntryDiffDay(Integer node21EntryDiffDay) {
		this.node21EntryDiffDay = node21EntryDiffDay;
	}

	public Date getNode22PlanSubDate() {
		return node22PlanSubDate;
	}

	public void setNode22PlanSubDate(Date node22PlanSubDate) {
		this.node22PlanSubDate = node22PlanSubDate;
	}

	public Integer getNode22SubDiffDay() {
		return node22SubDiffDay;
	}

	public void setNode22SubDiffDay(Integer node22SubDiffDay) {
		this.node22SubDiffDay = node22SubDiffDay;
	}

	public Integer getNode22EntryDiffDay() {
		return node22EntryDiffDay;
	}

	public void setNode22EntryDiffDay(Integer node22EntryDiffDay) {
		this.node22EntryDiffDay = node22EntryDiffDay;
	}
	
	
}