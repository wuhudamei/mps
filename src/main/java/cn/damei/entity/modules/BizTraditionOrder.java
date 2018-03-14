
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;
import cn.damei.common.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class BizTraditionOrder extends DataEntity2<BizTraditionOrder> {

	private static final long serialVersionUID = 1L;

	private Integer orderId;
	private Integer storeId;
	private String storeName;
	private Integer engineDepartId;
	private String engineDepartName;
	private String area;
	private String houseIsNew;
	private String houseIsNewName;
	private Date orderCreateDate;
	private String customerName;
	private String customerPhone;
	private String detailAddress;
	private String itemManager;
	private String itemManagerPhone;
	private String inspector;
	private String inspectorPhone;
	private String designerName;
	private String designerPhone;
	private Date getOrderDatetime;
	private Date contractStartDate;
	private Date contractEndDate;
	private Date actualStartDate;
	private Date actualEndDate;
	private Integer startDiffDay;
	private String isNeedSign;
	private Integer selfDecorateDelayDays;
	private String isSelfDecorateNeedSign;
	private Date beginActualStartDate;
	private Date endActualStartDate;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private List<BizNodePlanProject> nodePlanList;
	private List<Integer> engineDepartIds = new ArrayList<Integer>();

    private String nodeIndex;
    private String planDoneDate;
    private String realDoneDate;
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