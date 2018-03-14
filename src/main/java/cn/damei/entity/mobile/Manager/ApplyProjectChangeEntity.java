package cn.damei.entity.mobile.Manager;

import java.io.Serializable;
import java.util.Date;

/**
 * 施工变更单
 * @author 梅浩
 * @2016年11月16日
 * @mdn大美装饰管理平台
 * @author_phone : 18610507472
 * @ClassInfo:变更单
 */
public class ApplyProjectChangeEntity implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer projectChangeId;//变更单id
	private String projectChangeCode;//变更单code
	private String changeReason;//变更原因
	private Date  applyDate;//提交日期
	private Double addItemTotalPrice;//增项总价
	private Double minusItemTotalPrice;//减项总价
	private String status;//状态
	private String statusName;//状态名字
	private String statusShiro;//权限状态
	private Integer orderCount;//是否有施工变更单
	private Integer customerPhone;//客户手机
	private int signaturePic;//施工图片
	private String isScrap;//是否作废
	private String storeId;//门店ID
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
	private Integer checkManId;//检查人id
	private String checkManName;//检查人name
	public String getCheckManName() {
		return checkManName;
	}
	public void setCheckManName(String checkManName) {
		this.checkManName = checkManName;
	}
	private Date checkDate;//检查日期
	private Integer orderId;//订单id
	private String customerName;//客户名字
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
	private String orderStatus;//订单状态
	private Date  contractStartDate;//合同开始日期
	private String checkWords;//检查意见
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
