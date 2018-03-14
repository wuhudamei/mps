/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity2;

/**
 * 上传复尺Entity
 * @author wyb
 * @version 2016-10-20
 */
public class BizOrderChecksizeEntity extends DataEntity2<BizOrderChecksizeEntity> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;//主键
	private String orderNumber;//订单编号
	private String customerAddress;//客户地址
	private String communityName;//小区名称
	private String buildNumber;//几号楼
	private String buildUnit;//几单元
	private String buildRoom;//哪一室
	private String customerName;//客户姓名
	private Date contractStartDate;//合同开工日期
	private Date beginContractStartDate;//合同开工日期
	private Date endContractStartDate;//合同开工日期
	
	private Date contractEndDate;//合同竣工日期
	private Integer contractTime;//合同工期
	private String orderStatusNumber;//订单状态码  创建订单成功默认状态码 105 
	private String orderStatusDescription;//订单状态码详情   默认详情 确认订单   状态码105
	private String itemManager;//项目经理
	private Integer storeId;//门店ID
	private Integer itemManagerId;//项目经理id
	private String phone;	//项目经理电话
	
	private Integer orderInstallItemId;
	private Integer orderChecksizeId;	//复尺id
	private Integer orderId;		// 订单id -- '
	private String checksizeType;		// 复尺类型 -- '
	private String checksizeTypeold;	
	private String checksizeTypeName;		// 复尺类型名称 -- '
	private String checksizeTypeNameold;
	private Date checksizeDate;		// 复尺日期 -- '
	private String checksizeEmployeeId;		// 复尺人员工id -- '
	private Date beginChecksizeDate;		// 开始 复尺日期 -- '
	private Date endChecksizeDate;		// 结束 复尺日期 -- '
	private Integer checksizePhoto;		//复尺图片总数
	private String checksizeStatus;	//复尺状态
	private String checksizeStatusName;	//复尺状态名称
	private Date checksizeStatusDatetime;	//复尺状态日期时间
	private Date supplierConfirmDate;	//供应商确认时间
	private Date beginSupplierConfirmDate;	//供应商确认时间
	private Date endSupplierConfirmDate;	//供应商确认时间
	private Date materialDepartmentDealDatetime;	//材料部处理日期时间
	private Integer materialDepartmentDealEmployeeId;	//材料部处理人员工ID
	private String materialDepartmentDealEmployeeName;	//材料部处理人员工
	private String materialDepartmentDealReply;	//材料部处理回复
	
	private String storeName;//门店名字
	private String projectMode;		//工程模式   1-产业模式；2-传统模式
	private Date beginCreateDate;
	private Date endCreateDate;	
	private String applyDate; //申请日期
	private List<String> phones;
	private String userId;
	
	private String installItemName;
	
	
	public String getInstallItemName() {
		return installItemName;
	}

	public void setInstallItemName(String installItemName) {
		this.installItemName = installItemName;
	}

	public String getChecksizeTypeold() {
		return checksizeTypeold;
	}

	public void setChecksizeTypeold(String checksizeTypeold) {
		this.checksizeTypeold = checksizeTypeold;
	}

	public String getChecksizeTypeNameold() {
		return checksizeTypeNameold;
	}

	public void setChecksizeTypeNameold(String checksizeTypeNameold) {
		this.checksizeTypeNameold = checksizeTypeNameold;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public BizOrderChecksizeEntity() {
		super();
	}

	public BizOrderChecksizeEntity(Integer id){
		super(id);
	}

	@Length(min=0, max=11, message="订单id -- '长度必须介于 0 和 11 之间")
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=10, message="复尺类型 -- '长度必须介于 0 和 10 之间")
	public String getChecksizeType() {
		return checksizeType;
	}

	public void setChecksizeType(String checksizeType) {
		this.checksizeType = checksizeType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getChecksizeDate() {
		return checksizeDate;
	}

	public void setChecksizeDate(Date checksizeDate) {
		this.checksizeDate = checksizeDate;
	}
	
	@Length(min=0, max=11, message="复尺人员工id -- '长度必须介于 0 和 11 之间")
	public String getChecksizeEmployeeId() {
		return checksizeEmployeeId;
	}

	public void setChecksizeEmployeeId(String checksizeEmployeeId) {
		this.checksizeEmployeeId = checksizeEmployeeId;
	}
	
	public Date getBeginChecksizeDate() {
		return beginChecksizeDate;
	}

	public void setBeginChecksizeDate(Date beginChecksizeDate) {
		this.beginChecksizeDate = beginChecksizeDate;
	}
	
	public Date getEndChecksizeDate() {
		return endChecksizeDate;
	}

	public void setEndChecksizeDate(Date endChecksizeDate) {
		this.endChecksizeDate = endChecksizeDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
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

	public Integer getContractTime() {
		return contractTime;
	}

	public void setContractTime(Integer contractTime) {
		this.contractTime = contractTime;
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

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}

	public Integer getChecksizePhoto() {
		return checksizePhoto;
	}

	public void setChecksizePhoto(Integer checksizePhoto) {
		this.checksizePhoto = checksizePhoto;
	}

	public Date getBeginContractStartDate() {
		return beginContractStartDate;
	}

	public void setBeginContractStartDate(Date beginContractStartDate) {
		this.beginContractStartDate = beginContractStartDate;
	}

	public Date getEndContractStartDate() {
		return endContractStartDate;
	}

	public void setEndContractStartDate(Date endContractStartDate) {
		this.endContractStartDate = endContractStartDate;
	}

	public Integer getOrderChecksizeId() {
		return orderChecksizeId;
	}

	public void setOrderChecksizeId(Integer orderChecksizeId) {
		this.orderChecksizeId = orderChecksizeId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getChecksizeTypeName() {
		return checksizeTypeName;
	}

	public void setChecksizeTypeName(String checksizeTypeName) {
		this.checksizeTypeName = checksizeTypeName;
	}

	public String getChecksizeStatus() {
		return checksizeStatus;
	}

	public void setChecksizeStatus(String checksizeStatus) {
		this.checksizeStatus = checksizeStatus;
	}

	public String getChecksizeStatusName() {
		return checksizeStatusName;
	}

	public void setChecksizeStatusName(String checksizeStatusName) {
		this.checksizeStatusName = checksizeStatusName;
	}

	public Date getChecksizeStatusDatetime() {
		return checksizeStatusDatetime;
	}

	public void setChecksizeStatusDatetime(Date checksizeStatusDatetime) {
		this.checksizeStatusDatetime = checksizeStatusDatetime;
	}

	public Date getSupplierConfirmDate() {
		return supplierConfirmDate;
	}

	public void setSupplierConfirmDate(Date supplierConfirmDate) {
		this.supplierConfirmDate = supplierConfirmDate;
	}

	public Date getMaterialDepartmentDealDatetime() {
		return materialDepartmentDealDatetime;
	}

	public void setMaterialDepartmentDealDatetime(Date materialDepartmentDealDatetime) {
		this.materialDepartmentDealDatetime = materialDepartmentDealDatetime;
	}

	public Integer getMaterialDepartmentDealEmployeeId() {
		return materialDepartmentDealEmployeeId;
	}

	public void setMaterialDepartmentDealEmployeeId(Integer materialDepartmentDealEmployeeId) {
		this.materialDepartmentDealEmployeeId = materialDepartmentDealEmployeeId;
	}

	public String getMaterialDepartmentDealReply() {
		return materialDepartmentDealReply;
	}

	public void setMaterialDepartmentDealReply(String materialDepartmentDealReply) {
		this.materialDepartmentDealReply = materialDepartmentDealReply;
	}

	public String getMaterialDepartmentDealEmployeeName() {
		return materialDepartmentDealEmployeeName;
	}

	public void setMaterialDepartmentDealEmployeeName(String materialDepartmentDealEmployeeName) {
		this.materialDepartmentDealEmployeeName = materialDepartmentDealEmployeeName;
	}

	public Date getBeginSupplierConfirmDate() {
		return beginSupplierConfirmDate;
	}

	public void setBeginSupplierConfirmDate(Date beginSupplierConfirmDate) {
		this.beginSupplierConfirmDate = beginSupplierConfirmDate;
	}

	public Date getEndSupplierConfirmDate() {
		return endSupplierConfirmDate;
	}

	public void setEndSupplierConfirmDate(Date endSupplierConfirmDate) {
		this.endSupplierConfirmDate = endSupplierConfirmDate;
	}

	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}

	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public Integer getOrderInstallItemId() {
		return orderInstallItemId;
	}

	public void setOrderInstallItemId(Integer orderInstallItemId) {
		this.orderInstallItemId = orderInstallItemId;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	
	
	
		
}