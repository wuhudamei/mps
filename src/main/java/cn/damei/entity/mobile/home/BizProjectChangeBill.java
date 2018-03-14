/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.mobile.home;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import cn.damei.entity.mobile.home.BizChangeItem;
import cn.damei.entity.mobile.home.BizOrder;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 施工变更单Entity
 * @author wyb
 * @version 2016-11-16
 */
public class BizProjectChangeBill implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String type; //审核
	private Integer projectChangeId;		//变更单id
	private Integer orderId;		// 订单id -- '
	private String projectChangeBillCode;		// 施工变更单编号 -- '
	private String changeReason;		// 变更原因 -- '
	private Date applyDate;		// 提报日期 -- '
	private Double addItemTotalPrice;		// 增项总价 -- '
	private Double subItemTotalPrice;		// 减项总价 -- '
	private Double allPrice;	//总价
	private String status;		// 状态 -- '
	private Integer checkEmployeeId;		// 审核人员工id -- '
	private Date checkDate;		// 审核日期 -- '
	private String checkWords;		// 审核意见 -- '
	private Date beginApplyDate;		// 开始 提报日期 -- '
	private Date endApplyDate;		// 结束 提报日期 -- '
	
	
	private String storeId;	//门店
	private String orderNumber;	//订单编码
	private String communityName;	//小区
	private String buildNumber;	//几号楼
	private String buildUnit;	//几单元
	private String buildRoom;	//几室
	private String customerName;	//客户姓名
	private String itemManager;	//项目经理
	private Integer itemManagerId; //项目经理id
	
	private String customerPhone; //客户电话
	private String contractArea; //建筑面积
	private String itemManagerPhone; //项目经理电话
	private String designerName; //设计师
	private String designerPhone; //设计师电话
	private String contractNumber; //合同编号
	private String isElevator; //有无电梯
	
	private Integer viewCount;//日志表
	private BizOrder order; //订单
	
	private List<BizChangeItem> changeItemList; //变更项列表


	public Integer getProjectChangeId() {
		return projectChangeId;
	}

	public void setProjectChangeId(Integer projectChangeId) {
		this.projectChangeId = projectChangeId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=64, message="施工变更单编号 -- '长度必须介于 0 和 64 之间")
	public String getProjectChangeBillCode() {
		return projectChangeBillCode;
	}

	public void setProjectChangeBillCode(String projectChangeBillCode) {
		this.projectChangeBillCode = projectChangeBillCode;
	}
	
	@Length(min=0, max=255, message="变更原因 -- '长度必须介于 0 和 255 之间")
	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
	public Double getAddItemTotalPrice() {
		return addItemTotalPrice;
	}

	public void setAddItemTotalPrice(Double addItemTotalPrice) {
		this.addItemTotalPrice = addItemTotalPrice;
	}
	
	public Double getSubItemTotalPrice() {
		return subItemTotalPrice;
	}

	public void setSubItemTotalPrice(Double subItemTotalPrice) {
		this.subItemTotalPrice = subItemTotalPrice;
	}
	
	@Length(min=0, max=10, message="状态 -- '长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Integer getCheckEmployeeId() {
		return checkEmployeeId;
	}

	public void setCheckEmployeeId(Integer checkEmployeeId) {
		this.checkEmployeeId = checkEmployeeId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	
	@Length(min=0, max=255, message="审核意见 -- '长度必须介于 0 和 255 之间")
	public String getCheckWords() {
		return checkWords;
	}

	public void setCheckWords(String checkWords) {
		this.checkWords = checkWords;
	}
	
	public Date getBeginApplyDate() {
		return beginApplyDate;
	}

	public void setBeginApplyDate(Date beginApplyDate) {
		this.beginApplyDate = beginApplyDate;
	}
	
	public Date getEndApplyDate() {
		return endApplyDate;
	}

	public void setEndApplyDate(Date endApplyDate) {
		this.endApplyDate = endApplyDate;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getContractArea() {
		return contractArea;
	}

	public void setContractArea(String contractArea) {
		this.contractArea = contractArea;
	}

	public String getItemManagerPhone() {
		return itemManagerPhone;
	}

	public void setItemManagerPhone(String itemManagerPhone) {
		this.itemManagerPhone = itemManagerPhone;
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

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getIsElevator() {
		return isElevator;
	}

	public void setIsElevator(String isElevator) {
		this.isElevator = isElevator;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public BizOrder getOrder() {
		return order;
	}

	public void setOrder(BizOrder order) {
		this.order = order;
	}

	public Double getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(Double allPrice) {
		this.allPrice = allPrice;
	}

	public List<BizChangeItem> getChangeItemList() {
		return changeItemList;
	}

	public void setChangeItemList(List<BizChangeItem> changeItemList) {
		this.changeItemList = changeItemList;
	}

	public Integer getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}
		
}