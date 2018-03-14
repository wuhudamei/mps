/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity;

/**
 * 设计师审核Entity
 * @author wyb
 * @version 2016-11-16
 */
public class BizProjectChangeBill extends DataEntity<BizProjectChangeBill> {
	
	private static final long serialVersionUID = 1L;
	private String type; //审核
	private Integer projectChangeId;		//变更单id
	private Integer orderId;		// 订单id -- '
	private String projectChangeBillCode;		// 施工变更单编号 -- '
	private String changeReason;		// 变更原因 -- '
	private Date applyDate;		// 提报日期 -- '
	private Double addItemTotalPrice;		// 增项总价 -- '
	private Double subItemTotalPrice;		// 减项总价 -- '
	private String status;		// 状态 -- '
	private Integer checkEmployeeId;		// 审核人员工id -- '
	private Date checkDate;		// 审核日期 -- '
	private String checkWords;		// 审核意见 -- '
	private Date beginApplyDate;		// 开始 提报日期 -- '
	private Date endApplyDate;		// 结束 提报日期 -- '
	private String elactricationId;//区域
	
	private String storeId;	//门店
	private String orderNumber;	//订单编码
	private String communityName;	//小区
	private String buildNumber;	//几号楼
	private String buildUnit;	//几单元
	private String buildRoom;	//几室
	private String customerName;	//客户姓名
	private Integer itemManagerId;	//项目经理id
	private String itemManager;	//项目经理
	private String engineDepartId;//区域
	private String customerPhone; //客户电话
	private String contractArea; //建筑面积
	private String itemManagerPhone; //项目经理电话
	private String designerName; //设计师
	private String designerPhone; //设计师电话
	private String contractNumber; //合同编号
	private String isElevator; //有无电梯
	private String projectMode;		//工程模式   1-产业模式；2-传统模式
	
	private int signaturePic; //图片数量
	private String officeId;
	private String userId;
	private String parentId;
	private String storeName;
	
	private Date stylistCheckDate;
	private Date auditCheckDate;
	private String stylistCheckBy;
	private String auditCheckBy;
	
	private List<String> ids;
	private String dealEmplyeeName; //处理员工姓名
	private String isDealed;//是否处理
	private String ealDatetime;//处理时间
	
	
	
	public String getElactricationId() {
		return elactricationId;
	}
	public void setElactricationId(String elactricationId) {
		this.elactricationId = elactricationId;
	}
	public String getDealEmplyeeName() {
		return dealEmplyeeName;
	}
	public void setDealEmplyeeName(String dealEmplyeeName) {
		this.dealEmplyeeName = dealEmplyeeName;
	}
	public String getIsDealed() {
		return isDealed;
	}
	public void setIsDealed(String isDealed) {
		this.isDealed = isDealed;
	}
	public String getEalDatetime() {
		return ealDatetime;
	}
	public void setEalDatetime(String ealDatetime) {
		this.ealDatetime = ealDatetime;
	}
	public String getEngineDepartId() {
		return engineDepartId;
	}
	public void setEngineDepartId(String engineDepartId) {
		this.engineDepartId = engineDepartId;
	}
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	public Date getStylistCheckDate() {
		return stylistCheckDate;
	}
	public void setStylistCheckDate(Date stylistCheckDate) {
		this.stylistCheckDate = stylistCheckDate;
	}
	public Date getAuditCheckDate() {
		return auditCheckDate;
	}
	public void setAuditCheckDate(Date auditCheckDate) {
		this.auditCheckDate = auditCheckDate;
	}
	public String getStylistCheckBy() {
		return stylistCheckBy;
	}
	public void setStylistCheckBy(String stylistCheckBy) {
		this.stylistCheckBy = stylistCheckBy;
	}
	public String getAuditCheckBy() {
		return auditCheckBy;
	}
	public void setAuditCheckBy(String auditCheckBy) {
		this.auditCheckBy = auditCheckBy;
	}
	private List<String> phones;
	
	
	public List<String> getPhones() {
		return phones;
	}
	public void setPhones(List<String> phones) {
		this.phones = phones;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
	public BizProjectChangeBill() {
		super();
	}

	public BizProjectChangeBill(String id){
		super(id);
	}
	

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


	public Integer getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}
	public int getSignaturePic() {
		return signaturePic;
	}
	public void setSignaturePic(int signaturePic) {
		this.signaturePic = signaturePic;
	}
		
}