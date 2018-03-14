package cn.damei.entity.mobile.Manager;

import java.util.Date;
import java.util.List;

/**
 * Created by joseph on 2017/4/14.
 * 传统经理结算-订单信息
 */
public class ManagerTraditionSettleEntity{

    private String communityName;		// 小区名称
    private String text;
    private String itemManager;		// 项目经理
    private Integer itemManagerId;		// 项目经理id
    private String managerPhone;

    private String contractNumber;		// 合同编号
    private String orderNumber;
    private String customerName;		// 客户姓名
    private String customerPhone;		// 客户电话
    private String orderStatusNumber;		// 订单状态码  创建订单成功默认状态码 105
    private String orderStatusDescription;		// 订单状态码详情   默认详情 确认订单   状态码105
    private Integer orderId;
    private String buildNumber;		// 几号楼
    private String buildUnit;		// 几单元
    private String buildRoom;		// 哪一室
    private String buildType;		// 房屋类型
    private String houseType;		//房屋户型
    private String house;		//房屋
    private String contractArea;		// 合同面积
    private Date contractStartDate;		// 合同开工日期
    private Date contractEndDate;		// 合同竣工日期
    private String contractTime;		// 合同工期
    private Date actualStartDate;		//实际开工日期
    private Date actualEndDate; 		//实际竣工日期
    private String orderBy;				//排序
    private String delayType;//延期类型
    private String designerName;		// 设计师姓名
    private String designerPhone;		// 设计师电话
    private String orderReporterName;		// 跟单员姓名
    private String orderReporterPhone;		// 跟单员电话
    private String serviceName;		// 客服姓名
    private String servicePhone;		// 客服电话
    private String customerAddress;
    private String projectMode; //工程模式
    private Integer storeId; //门店
    private String orderInspector;		// 质检员
    private String orderInspectorPhone;		// 质检员电话
    private Integer orderInspectorId;		// 质检员id
    private List<ManagerNormalSettle> settleList;//订单下的结算节点对应的集合
    private String checkNodeName;//冗余节点名称
    private Integer settleNodeId;//节点id

    public String getSettleApplyRemarks() {
        return settleApplyRemarks;
    }

    public void setSettleApplyRemarks(String settleApplyRemarks) {
        this.settleApplyRemarks = settleApplyRemarks;
    }

    private String settleApplyRemarks;//提交


    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }


    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getCheckNodeName() {
        return checkNodeName;
    }

    public void setCheckNodeName(String checkNodeName) {
        this.checkNodeName = checkNodeName;
    }
    public Integer getSettleNodeId() {
        return settleNodeId;
    }

    public void setSettleNodeId(Integer settleNodeId) {
        this.settleNodeId = settleNodeId;
    }

    public List<ManagerNormalSettle> getSettleList() {
        return settleList;
    }

    public void setSettleList(List<ManagerNormalSettle> settleList) {
        this.settleList = settleList;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public String getItemManager() {
        return itemManager;
    }

    public void setItemManager(String itemManager) {
        this.itemManager = itemManager;
    }

    public Integer getItemManagerId() {
        return itemManagerId;
    }

    public void setItemManagerId(Integer itemManagerId) {
        this.itemManagerId = itemManagerId;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
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

    public String getOrderStatusNumber() {
        return orderStatusNumber;
    }

    public void setOrderStatusNumber(String orderStatusNumber) {
        this.orderStatusNumber = orderStatusNumber;
    }

    public String getOrderStatusDescription() {
        return orderStatusDescription;
    }

    public void setOrderStatusDescription(String orderStatusDescription) {
        this.orderStatusDescription = orderStatusDescription;
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

    public String getBuildType() {
        return buildType;
    }

    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getContractArea() {
        return contractArea;
    }

    public void setContractArea(String contractArea) {
        this.contractArea = contractArea;
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

    public String getContractTime() {
        return contractTime;
    }

    public void setContractTime(String contractTime) {
        this.contractTime = contractTime;
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

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getDelayType() {
        return delayType;
    }

    public void setDelayType(String delayType) {
        this.delayType = delayType;
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

    public String getOrderReporterName() {
        return orderReporterName;
    }

    public void setOrderReporterName(String orderReporterName) {
        this.orderReporterName = orderReporterName;
    }

    public String getOrderReporterPhone() {
        return orderReporterPhone;
    }

    public void setOrderReporterPhone(String orderReporterPhone) {
        this.orderReporterPhone = orderReporterPhone;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getProjectMode() {
        return projectMode;
    }

    public void setProjectMode(String projectMode) {
        this.projectMode = projectMode;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getOrderInspector() {
        return orderInspector;
    }

    public void setOrderInspector(String orderInspector) {
        this.orderInspector = orderInspector;
    }

    public String getOrderInspectorPhone() {
        return orderInspectorPhone;
    }

    public void setOrderInspectorPhone(String orderInspectorPhone) {
        this.orderInspectorPhone = orderInspectorPhone;
    }

    public Integer getOrderInspectorId() {
        return orderInspectorId;
    }

    public void setOrderInspectorId(Integer orderInspectorId) {
        this.orderInspectorId = orderInspectorId;
    }








}
