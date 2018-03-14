/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 工程投诉分类项Entity
 * 
 * @author mh
 * @version 2017-07-03
 */
public class BizComplaintProblemItem extends DataEntity<BizComplaintProblemItem> {

	private static final long serialVersionUID = 1L;
	private Integer storeId; // 门店id
	private Integer itemId; // 事项id
	private Integer complaintProblemTypeId; // 分类问题id
	private String typeName;
	private String itemid; // 分类问题项ID
	private String itemName; // 分类项名称
	private Double responseTime; // 响应时间
	private Double executeTimeLimit; // ִ执行时限
	private Double specialTimeLimit; // 特殊时间
	private Double deductScore; // 扣分
	private Double punishMoney; // 罚款
	private String itemRemarks; // 事项备注
	private String isEnabled; // 停启用
	private String packName;
	private String dealPersonType;
	private String complaintproblemid; // 投诉问题ID
	private Date startCreateTime;  //查询日期段--开始创建时间
	private Date endCreateTime;	 //查询时间段--截至创建时间	
	private String complaintProblemTimes; //出现次数
	
	
	public String getComplaintProblemTimes() {
		return complaintProblemTimes;
	}

	public void setComplaintProblemTimes(String complaintProblemTimes) {
		this.complaintProblemTimes = complaintProblemTimes;
	}

	public Date getStartCreateTime() {
		return startCreateTime;
	}

	public void setStartCreateTime(Date startCreateTime) {
		this.startCreateTime = startCreateTime;
	}

	public Date getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(Date endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public String getDealPersonType() {
		return dealPersonType;
	}

	public void setDealPersonType(String dealPersonType) {
		this.dealPersonType = dealPersonType;
	}

	public String getItemid() {
		return itemid;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getComplaintproblemid() {
		return complaintproblemid;
	}

	public void setComplaintproblemid(String complaintproblemid) {
		this.complaintproblemid = complaintproblemid;
	}

	public String getPackName() {
		return packName;
	}

	public void setPackName(String packName) {
		this.packName = packName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public BizComplaintProblemItem() {
		super();
	}

	public BizComplaintProblemItem(String id) {
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getComplaintProblemTypeId() {
		return complaintProblemTypeId;
	}

	public void setComplaintProblemTypeId(Integer complaintProblemTypeId) {
		this.complaintProblemTypeId = complaintProblemTypeId;
	}

	@Length(min = 0, max = 100, message = "分类项名称长度必须介于 0 和 100 之间")
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Double responseTime) {
		this.responseTime = responseTime;
	}

	public Double getExecuteTimeLimit() {
		return executeTimeLimit;
	}

	public void setExecuteTimeLimit(Double executeTimeLimit) {
		this.executeTimeLimit = executeTimeLimit;
	}

	public Double getSpecialTimeLimit() {
		return specialTimeLimit;
	}

	public void setSpecialTimeLimit(Double specialTimeLimit) {
		this.specialTimeLimit = specialTimeLimit;
	}

	public Double getDeductScore() {
		return deductScore;
	}

	public void setDeductScore(Double deductScore) {
		this.deductScore = deductScore;
	}

	public Double getPunishMoney() {
		return punishMoney;
	}

	public void setPunishMoney(Double punishMoney) {
		this.punishMoney = punishMoney;
	}

	@Length(min = 0, max = 255, message = "事项备注长度必须介于 0 和 255 之间")
	public String getItemRemarks() {
		return itemRemarks;
	}

	public void setItemRemarks(String itemRemarks) {
		this.itemRemarks = itemRemarks;
	}

	@Length(min = 0, max = 1, message = "停启用长度必须介于 0 和 1 之间")
	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

}