package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity2;

/**
 * 主材催促Entity
 * @author wyb
 * @version 2017-05-03
 */
public class InstallBusinessUrge extends DataEntity2<InstallBusinessUrge> {
	
	private static final long serialVersionUID = 1L;
	private Integer businessId;	//业务id
	private Integer businessOnlyMarkInt;		// 业务唯一标识整型
	private String businessOnlyMarkVarchar;		// 业务唯一标识字符型
	private String businesType;		// 业务类型
	private String businesTypeName;		// 业务类型 名称
	private String operateType;		// 操作类型
	private String operateTypeName;		// 操作类型 名称
	private String operateContent;		// 操作内容
	private Integer operatorEmployeeId;		// 操作人员工id
	private String operatorEmployeeName;		// 操作人员工id 名称
	private String operatorType;		// 操作人类型
	private String operatorTypeName;		// 操作人类型 名称
	private Date operateDatetime;		// 操作日期时间
	private Date beginOperateDatetime;		// 操作日期时间 开始
	private Date endOperateDatetime;		// 操作日期时间 结束
	
	private Integer installId;	//安装项id
	private String installItemName;//安装项名称
	
	private Integer orderId; //订单id
	private String communityName;//小区
	private String buildNumber;//楼
	private String buildUnit; //单元
	private String buildRoom; //室
	
	private String customerName; //客户姓名
	private String customerPhone; //客户电话
	
	private Integer itemManagerId;//项目经理id
	private String itemManagerName;//项目经理姓名
	private String itemManagerPhone; //项目经理电话
	
	private Integer storeId; //门店
	private String storeName; //门店名称
	
	private String projectMode; //工程模式
	private String projectModeName; //工程模式名称
	
	private Integer enginDepartId; //区域id
	private String enginDepartName;//区域名称
	private Integer urgeCount;	//催促次数
	private Date applyIntoDate;//申请日期
	private Date supplierConfirmIntoDate;//供应商确认日期
	
	private String isOn; //是否启用（安装项名称）
	private Integer projectInstallItemId; // 安装项模板id
	private Integer projectInstallItemIdStop; // 安装项模板id(停用的)
	private	List<Integer> projectInstallItemIdList = null; //安装项id集合
	

	public Date getApplyIntoDate() {
		return applyIntoDate;
	}

	public void setApplyIntoDate(Date applyIntoDate) {
		this.applyIntoDate = applyIntoDate;
	}

	public Date getSupplierConfirmIntoDate() {
		return supplierConfirmIntoDate;
	}

	public void setSupplierConfirmIntoDate(Date supplierConfirmIntoDate) {
		this.supplierConfirmIntoDate = supplierConfirmIntoDate;
	}

	public InstallBusinessUrge() {
		super();
	}

	public InstallBusinessUrge(Integer id){
		super(id);
	}

	public Integer getBusinessOnlyMarkInt() {
		return businessOnlyMarkInt;
	}

	public void setBusinessOnlyMarkInt(Integer businessOnlyMarkInt) {
		this.businessOnlyMarkInt = businessOnlyMarkInt;
	}
	
	@Length(min=0, max=100, message="业务唯一标识字符型长度必须介于 0 和 100 之间")
	public String getBusinessOnlyMarkVarchar() {
		return businessOnlyMarkVarchar;
	}

	public void setBusinessOnlyMarkVarchar(String businessOnlyMarkVarchar) {
		this.businessOnlyMarkVarchar = businessOnlyMarkVarchar;
	}
	
	@Length(min=0, max=10, message="业务类型长度必须介于 0 和 10 之间")
	public String getBusinesType() {
		return businesType;
	}

	public void setBusinesType(String businesType) {
		this.businesType = businesType;
	}
	
	@Length(min=0, max=10, message="操作类型长度必须介于 0 和 10 之间")
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	
	@Length(min=0, max=255, message="操作内容长度必须介于 0 和 255 之间")
	public String getOperateContent() {
		return operateContent;
	}

	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}
	
	public Integer getOperatorEmployeeId() {
		return operatorEmployeeId;
	}

	public void setOperatorEmployeeId(Integer operatorEmployeeId) {
		this.operatorEmployeeId = operatorEmployeeId;
	}
	
	@Length(min=0, max=10, message="操作人类型长度必须介于 0 和 10 之间")
	public String getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOperateDatetime() {
		return operateDatetime;
	}

	public void setOperateDatetime(Date operateDatetime) {
		this.operateDatetime = operateDatetime;
	}

	public String getBusinesTypeName() {
		return businesTypeName;
	}

	public void setBusinesTypeName(String businesTypeName) {
		this.businesTypeName = businesTypeName;
	}

	public String getOperateTypeName() {
		return operateTypeName;
	}

	public void setOperateTypeName(String operateTypeName) {
		this.operateTypeName = operateTypeName;
	}

	public String getOperatorEmployeeName() {
		return operatorEmployeeName;
	}

	public void setOperatorEmployeeName(String operatorEmployeeName) {
		this.operatorEmployeeName = operatorEmployeeName;
	}

	public String getOperatorTypeName() {
		return operatorTypeName;
	}

	public void setOperatorTypeName(String operatorTypeName) {
		this.operatorTypeName = operatorTypeName;
	}

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public Date getBeginOperateDatetime() {
		return beginOperateDatetime;
	}

	public void setBeginOperateDatetime(Date beginOperateDatetime) {
		this.beginOperateDatetime = beginOperateDatetime;
	}

	public Date getEndOperateDatetime() {
		return endOperateDatetime;
	}

	public void setEndOperateDatetime(Date endOperateDatetime) {
		this.endOperateDatetime = endOperateDatetime;
	}

	public Integer getInstallId() {
		return installId;
	}

	public void setInstallId(Integer installId) {
		this.installId = installId;
	}

	public String getInstallItemName() {
		return installItemName;
	}

	public void setInstallItemName(String installItemName) {
		this.installItemName = installItemName;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public Integer getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}

	public String getItemManagerName() {
		return itemManagerName;
	}

	public void setItemManagerName(String itemManagerName) {
		this.itemManagerName = itemManagerName;
	}

	public String getItemManagerPhone() {
		return itemManagerPhone;
	}

	public void setItemManagerPhone(String itemManagerPhone) {
		this.itemManagerPhone = itemManagerPhone;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public String getProjectModeName() {
		return projectModeName;
	}

	public void setProjectModeName(String projectModeName) {
		this.projectModeName = projectModeName;
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

	public Integer getUrgeCount() {
		return urgeCount;
	}

	public void setUrgeCount(Integer urgeCount) {
		this.urgeCount = urgeCount;
	}

	public String getIsOn() {
		return isOn;
	}

	public void setIsOn(String isOn) {
		this.isOn = isOn;
	}

	public Integer getProjectInstallItemId() {
		return projectInstallItemId;
	}

	public void setProjectInstallItemId(Integer projectInstallItemId) {
		this.projectInstallItemId = projectInstallItemId;
	}

	public Integer getProjectInstallItemIdStop() {
		return projectInstallItemIdStop;
	}

	public void setProjectInstallItemIdStop(Integer projectInstallItemIdStop) {
		this.projectInstallItemIdStop = projectInstallItemIdStop;
	}

	public List<Integer> getProjectInstallItemIdList() {
		return projectInstallItemIdList;
	}

	public void setProjectInstallItemIdList(List<Integer> projectInstallItemIdList) {
		this.projectInstallItemIdList = projectInstallItemIdList;
	}
	
}