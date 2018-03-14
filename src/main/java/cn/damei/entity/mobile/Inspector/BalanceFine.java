package cn.damei.entity.mobile.Inspector;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author 梅浩
 * @2016年12月26日
 * @mdn美得你
 * @author_phone : 18610507472
 * @ClassInfo:结算罚款类目明细
 */
public class BalanceFine  implements Serializable{

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer   id;//主键
	private Integer orderId;//订单id
	private Integer managerId;//经理id
	private String settleCategory;//分类 4: 罚款
	private Double settleAmount;//结算金额  罚款金额
	private String settleStatus;//状态  : 创建 10
	private Date settleStatusTime;//new date()
	private Integer relatedBussinessId;//关联业务id
	private String remarks;
	private Integer createBy;
	private Integer updateBy;
	private Date createDate;
	private Date updateDate;
	private String  delFlag;
	private String settleRole; //1项目经理 2质检员
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public Integer getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public String getSettleCategory() {
		return settleCategory;
	}
	public void setSettleCategory(String settleCategory) {
		this.settleCategory = settleCategory;
	}
	public Double getSettleAmount() {
		return settleAmount;
	}
	public void setSettleAmount(Double settleAmount) {
		this.settleAmount = settleAmount;
	}
	public String getSettleStatus() {
		return settleStatus;
	}
	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}
	public Date getSettleStatusTime() {
		return settleStatusTime;
	}
	public void setSettleStatusTime(Date settleStatusTime) {
		this.settleStatusTime = settleStatusTime;
	}
	public Integer getRelatedBussinessId() {
		return relatedBussinessId;
	}
	public void setRelatedBussinessId(Integer relatedBussinessId) {
		this.relatedBussinessId = relatedBussinessId;
	}
	public String getSettleRole() {
		return settleRole;
	}
	public void setSettleRole(String settleRole) {
		this.settleRole = settleRole;
	}
	
	
}
