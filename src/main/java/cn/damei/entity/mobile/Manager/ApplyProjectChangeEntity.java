package cn.damei.entity.mobile.Manager;

import java.io.Serializable;
import java.util.Date;


public class ApplyProjectChangeEntity implements Serializable {

	

	private static final long serialVersionUID = 1L;
	private Integer projectChangeId;
	private String projectChangeCode;
	private String changeReason;
	private Date  applyDate;
	private Double addItemTotalPrice;
	private Double minusItemTotalPrice;
	private String status;
	private String statusName;
	private String statusShiro;
	private Integer orderCount;
	private Integer customerPhone;
	private int signaturePic;
	private String isScrap;
	private String storeId;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getIsScrap() {
		return isScrap;
	}
	public void setIsScrap(String isScrap) {
		this.isScrap = isScrap;
	}
	public int getSignaturePic() {
		return signaturePic;
	}
	public void setSignaturePic(int signaturePic) {
		this.signaturePic = signaturePic;
	}
	public Integer getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(Integer customerPhone) {
		this.customerPhone = customerPhone;
	}
	public Integer getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}
	public String getStatusShiro() {
		return statusShiro;
	}
	public void setStatusShiro(String statusShiro) {
		this.statusShiro = statusShiro;
	}
	private Integer checkManId;
	private String checkManName;
	public String getCheckManName() {
		return checkManName;
	}
	public void setCheckManName(String checkManName) {
		this.checkManName = checkManName;
	}
	private Date checkDate;
	private Integer orderId;
	private String customerName;
	private String  xiaoqu;
	private String lou;
	private String danyuan;
	public String getXiaoqu() {
		return xiaoqu;
	}
	public void setXiaoqu(String xiaoqu) {
		this.xiaoqu = xiaoqu;
	}
	public String getLou() {
		return lou;
	}
	public void setLou(String lou) {
		this.lou = lou;
	}
	public String getDanyuan() {
		return danyuan;
	}
	public void setDanyuan(String danyuan) {
		this.danyuan = danyuan;
	}
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	private String shi;
	private String orderStatus;
	private Date  contractStartDate;
	private String checkWords;
	public String getCheckWords() {
		return checkWords;
	}
	public void setCheckWords(String checkWords) {
		this.checkWords = checkWords;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Date getContractStartDate() {
		return contractStartDate;
	}
	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
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
	public String getProjectChangeCode() {
		return projectChangeCode;
	}
	public void setProjectChangeCode(String projectChangeCode) {
		this.projectChangeCode = projectChangeCode;
	}
	public String getChangeReason() {
		return changeReason;
	}
	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}
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
	public Double getMinusItemTotalPrice() {
		return minusItemTotalPrice;
	}
	public void setMinusItemTotalPrice(Double minusItemTotalPrice) {
		this.minusItemTotalPrice = minusItemTotalPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCheckManId() {
		return checkManId;
	}
	public void setCheckManId(Integer checkManId) {
		this.checkManId = checkManId;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	
	
}
