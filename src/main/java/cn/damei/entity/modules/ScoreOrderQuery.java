package cn.damei.entity.modules;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity;

/**
 * 订单评分详情Entity
 * @author lft
 * @version 2017-04-15
 */
public class ScoreOrderQuery extends DataEntity<ScoreOrderQuery> {
	private String name; //门店
	
	private String typeName; //评分类型
	private String typeCode; //评分编号
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	private String orderNumber;// 订单编号
	private String customerName;//客户名字
	private String customerPhone;// 客户电话
	private String province; //订单的地址
	private String city;
	private String county;
	private String detailAddress;
	private String stakeholder; //干系
	private Integer scoreValue;//评分值
	private Integer scoreBegin; //评分区间
	private Integer scoreEnd;
	private Date scoreTime; //评分时间
	private Date scoreDateBegin; //评分时间区间
	private Date scoreDateEnd;
	private String scoreContent; //评价内容
	private String scoreQuery;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getStakeholder() {
		return stakeholder;
	}
	public void setStakeholder(String stakeholder) {
		this.stakeholder = stakeholder;
	}
	public Integer getScoreValue() {
		return scoreValue;
	}
	public void setScoreValue(Integer scoreValue) {
		this.scoreValue = scoreValue;
	}
	public Integer getScoreBegin() {
		return scoreBegin;
	}
	public void setScoreBegin(Integer scoreBegin) {
		this.scoreBegin = scoreBegin;
	}
	public Integer getScoreEnd() {
		return scoreEnd;
	}
	public void setScoreEnd(Integer scoreEnd) {
		this.scoreEnd = scoreEnd;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getScoreTime() {
		return scoreTime;
	}
	public void setScoreTime(Date scoreTime) {
		this.scoreTime = scoreTime;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getScoreDateBegin() {
		return scoreDateBegin;
	}
	public void setScoreDateBegin(Date scoreDateBegin) {
		this.scoreDateBegin = scoreDateBegin;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getScoreDateEnd() {
		return scoreDateEnd;
	}
	public void setScoreDateEnd(Date scoreDateEnd) {
		this.scoreDateEnd = scoreDateEnd;
	}
	public String getScoreContent() {
		return scoreContent;
	}
	public void setScoreContent(String scoreContent) {
		this.scoreContent = scoreContent;
	}
	public String getScoreQuery() {
		return scoreQuery;
	}
	public void setScoreQuery(String scoreQuery) {
		this.scoreQuery = scoreQuery;
	}
	@Override
	public String toString() {
		return "ScoreOrderQuery [name=" + name + ", typeName=" + typeName + ", typeCode=" + typeCode + ", orderNumber="
				+ orderNumber + ", customerName=" + customerName + ", customerPhone=" + customerPhone + ", province="
				+ province + ", city=" + city + ", county=" + county + ", detailAddress=" + detailAddress
				+ ", stakeholder=" + stakeholder + ", scoreValue=" + scoreValue + ", scoreBegin=" + scoreBegin
				+ ", scoreEnd=" + scoreEnd + ", scoreTime=" + scoreTime + ", scoreDateBegin=" + scoreDateBegin
				+ ", scoreDateEnd=" + scoreDateEnd + ", scoreContent=" + scoreContent + ", scoreQuery=" + scoreQuery
				+ "]";
	}
	
	
	
}