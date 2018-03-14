/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;
import cn.damei.common.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 传统订单工程进度大看板Entity
 * @author qww
 * @version 2016-10-26
 */
public class BizTraditionOrder extends DataEntity2<BizTraditionOrder> {

	private static final long serialVersionUID = 1L;

	private Integer orderId; // 订单id
	private Integer storeId; // 门店id
	private String storeName;	//门店名称
	private Integer engineDepartId; // 区域id
	private String engineDepartName; // 区域名称
	private String area;		// 片区
	private String houseIsNew; // 新房老房  1为新房  0为老房  默认老房
	private String houseIsNewName;	//新老房名称
	private Date orderCreateDate; // 订单创建日期
	private String customerName; // 客户姓名
	private String customerPhone; // 客户电话
	private String detailAddress; // 工程地址（详细地址）
	private String itemManager; // 项目经理
	private String itemManagerPhone; // 项目经理手机号
	private String inspector; // 质检员
	private String inspectorPhone; // 质检员手机号
	private String designerName; // 设计师
	private String designerPhone; // 设计师手机号
	private Date getOrderDatetime;	//接单日期
	private Date contractStartDate; // 合同开工日期
	private Date contractEndDate; // 合同竣工日期
	private Date actualStartDate; // 实际开工日期
	private Date actualEndDate; // 实际竣工日期
	private Integer startDiffDay; // 开工延期天数
	private String isNeedSign; // 开工是否需要客户签字 -- '0.否；1.是
	private Integer selfDecorateDelayDays; // 自装延期天数
	private String isSelfDecorateNeedSign; // 自装是否需要客户签字 -- '0.否；1.是
	private Date beginActualStartDate; // 实际开工日期时间段(搜索时使用)
	private Date endActualStartDate; // 实际开工日期时间段(搜索时使用)
	private String communityName;	//小区
	private String buildNumber;	//楼
	private String buildUnit;	//单元
	private String buildRoom;	//室
	private List<BizNodePlanProject> nodePlanList;	//进度节点list
	private List<Integer> engineDepartIds = new ArrayList<Integer>();

    private String nodeIndex;		// 节点序号 -- '
    private String planDoneDate;		// 计划完成日期 -- '
    private String realDoneDate;		// 实际完成日期 -- '
    private String planDiffDay;

    private List<String>  planDoneDateList;
    private List<String>  realDoneDateList;
    private List<String>  planDiffDayList;


    public BizTraditionOrder() {
        if(StringUtils.isNoneBlank(planDoneDate)){
            planDoneDateList = Arrays.asList(planDoneDate.split(","));
        }
       if(StringUtils.isNoneBlank(realDoneDate)){
            realDoneDateList = Arrays.asList(realDoneDate.split(","));
        }
        if(StringUtils.isNoneBlank(planDiffDay)){
            planDiffDayList = Arrays.asList(planDiffDay.split(","));
        }
    }
//    获取计划时间
    public String getPlanDoneDateByList(int i) {
        if (StringUtils.isNoneBlank(planDoneDate)) {
            if (planDoneDateList == null) {
                planDoneDateList = Arrays.asList(planDoneDate.split(","));
            }
            String flag = planDoneDateList.get(i);
            if (flag == null || flag.equals("0")) {
                return "";
            } else {
                return flag;
            }
        }
        return "";
    }



//  获取天数
    public String getPlanDiffDayList(int i) {
        if (StringUtils.isNoneBlank(planDiffDay)) {
            if (planDiffDayList == null) {
                planDiffDayList = Arrays.asList(planDiffDay.split(","));
            }
            String flag = planDiffDayList.get(i);
            if (flag == null || flag.equals("0")) {
                return "";
            } else {
                return flag;
            }
        }
        return "";
    }
//  获取实际时间
    public String getRealDoneDateByList(int i) {
        if (StringUtils.isNoneBlank(realDoneDate)) {
            if (realDoneDateList == null) {
                realDoneDateList = Arrays.asList(realDoneDate.split(","));
            }
            String flag = realDoneDateList.get(i);
            if (flag == null || flag.equals("0")) {
                return "";
            } else {
                return flag;
            }
        }
        return "";
    }


    public String getNodeIndex() {

        return nodeIndex;
    }

    public void setNodeIndex(String nodeIndex) {
        this.nodeIndex = nodeIndex;
    }

    public String getPlanDoneDate() {

        return planDoneDate;
    }

    public void setPlanDoneDate(String planDoneDate) {
        this.planDoneDate = planDoneDate;
    }

    public String getRealDoneDate() {
        return realDoneDate;
    }

    public void setRealDoneDate(String realDoneDate) {
        this.realDoneDate = realDoneDate;
    }

    public String getPlanDiffDay() {
        return planDiffDay;
    }

    public void setPlanDiffDay(String planDiffDay) {
        this.planDiffDay = planDiffDay;
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

	public Integer getEngineDepartId() {
		return engineDepartId;
	}

	public void setEngineDepartId(Integer engineDepartId) {
		this.engineDepartId = engineDepartId;
	}

	public String getEngineDepartName() {
		return engineDepartName;
	}

	public void setEngineDepartName(String engineDepartName) {
		this.engineDepartName = engineDepartName;
	}

	public List<BizNodePlanProject> getNodePlanList() {
		return nodePlanList;
	}

	public void setNodePlanList(List<BizNodePlanProject> nodePlanList) {
		this.nodePlanList = nodePlanList;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getHouseIsNewName() {
		return houseIsNewName;
	}

	public void setHouseIsNewName(String houseIsNewName) {
		this.houseIsNewName = houseIsNewName;
	}

	public Date getGetOrderDatetime() {
		return getOrderDatetime;
	}

	public void setGetOrderDatetime(Date getOrderDatetime) {
		this.getOrderDatetime = getOrderDatetime;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getBuildNumber() {
		return buildNumber;
	}

	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}

	public String getBuildUnit() {
		return buildUnit;
	}

	public void setBuildUnit(String buildUnit) {
		this.buildUnit = buildUnit;
	}

	public String getBuildRoom() {
		return buildRoom;
	}

	public void setBuildRoom(String buildRoom) {
		this.buildRoom = buildRoom;
	}

	public List<Integer> getEngineDepartIds() {
		return engineDepartIds;
	}

	public void setEngineDepartIds(List<Integer> engineDepartIds) {
		this.engineDepartIds = engineDepartIds;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public String getInspectorPhone() {
		return inspectorPhone;
	}

	public void setInspectorPhone(String inspectorPhone) {
		this.inspectorPhone = inspectorPhone;
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
	
	

}