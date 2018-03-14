/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity;

/**
 * 预警
 * @author 张康健
 * @version 2017-05-11
 */
public class MaterialWarningOrder extends DataEntity<MaterialWarningOrder> {

	private static final long serialVersionUID = 1L;
	
	
	//private Integer id; //订单id
	private String projectMode;
	private String sort;
	private String nodeName;//节点名称
	
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	private String customerName;		// 客户姓名
	private String customerPhone;		// 客户电话
	private String storeId;		// 门店id
	private String communityName;		// 小区名称
	private String isNewHouse;		// 新房老房  1为新房  0为老房  默认老房
	private List<String> orderIds;
	
	
	public List<String> getOrderIds() {
		return orderIds;
	}
	public void setOrderIds(List<String> orderIds) {
		this.orderIds = orderIds;
	}
	private String designerName;		// 设计师姓名
	private String designerPhone;		// 设计师电话
	private Date contractStartDate;		// 合同开工日期
	private Date contractEndDate;		// 合同竣工日期
	private Date planDoneDate;		// 计划完成日期
	private String extensionDays; //延期天数
	private String enginDepartId; //接单区域
	private String orderInspector;		// 订单质检员
	private String orderInspectorPhone;
	private String itemManager;		// 项目经理
	private Integer itemManagerId;
	private Integer orderInspectorId;
	private String managerName;
	private String managerPhone;
	private Date actualStartDate;//实际开工时间
	
	public String getOrderInspectorPhone() {
		return orderInspectorPhone;
	}
	public void setOrderInspectorPhone(String orderInspectorPhone) {
		this.orderInspectorPhone = orderInspectorPhone;
	}
	public Date getActualStartDate() {
		return actualStartDate;
	}
	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public Date getPlanDoneDate() {
		return planDoneDate;
	}
	public void setPlanDoneDate(Date planDoneDate) {
		this.planDoneDate = planDoneDate;
	}
	public String getExtensionDays() {
		Date date = new Date();
		long time = date.getTime();
		long time2 = planDoneDate.getTime();
		String str = (time - time2)/(24*3600*1000)+"";
		return str;
	}
	public void setExtensionDays(String extensionDays) {
		this.extensionDays = extensionDays;
	}
	public String getEnginDepartId() {
		return enginDepartId;
	}
	public void setEnginDepartId(String enginDepartId) {
		this.enginDepartId = enginDepartId;
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
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	
	public String getIsNewHouse() {
		return isNewHouse;
	}
	public void setIsNewHouse(String isNewHouse) {
		this.isNewHouse = isNewHouse;
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
	public String getOrderInspector() {
		return orderInspector;
	}
	public void setOrderInspector(String orderInspector) {
		this.orderInspector = orderInspector;
	}
	public String getItemManager() {
		return itemManager;
	}
	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public Integer getItemManagerId() {
		return itemManagerId;
	}
	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}
	public Integer getOrderInspectorId() {
		return orderInspectorId;
	}
	public void setOrderInspectorId(Integer orderInspectorId) {
		this.orderInspectorId = orderInspectorId;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	
	
	
}