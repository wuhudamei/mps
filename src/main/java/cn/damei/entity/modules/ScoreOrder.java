/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity;

/**
 * 订单评分Entity
 * @author lwc
 * @version 2017-04-12
 */
public class ScoreOrder extends DataEntity<ScoreOrder> {
	
	private static final long serialVersionUID = 1L;

	private Integer orderId;	// 订单id
	private String scoreType;	// 评价类型，1：综合评分；2，销售评分。。。。
	private String scoreTypeCn;	// 评价类型中文值，1：综合评分；2，销售评分。。。。
	private Integer scoreValue;	// 评分值
	private String scoreContent;// 评分内容
	private Date scoreTime;		// 评分时间
	private String stakeholder;	// 干系人
	
	public ScoreOrder() {
		super();
	}

	public ScoreOrder(String id){
		super(id);
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getScoreType() {
		return scoreType;
	}

	public void setScoreType(String scoreType) {
		this.scoreType = scoreType;
	}
	
	public String getScoreTypeCn() {
		return scoreTypeCn;
	}

	public void setScoreTypeCn(String scoreTypeCn) {
		this.scoreTypeCn = scoreTypeCn;
	}

	public Integer getScoreValue() {
		return scoreValue;
	}

	public void setScoreValue(Integer scoreValue) {
		this.scoreValue = scoreValue;
	}

	public String getScoreContent() {
		return scoreContent;
	}

	public void setScoreContent(String scoreContent) {
		this.scoreContent = scoreContent;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getScoreTime() {
		return scoreTime;
	}

	public void setScoreTime(Date scoreTime) {
		this.scoreTime = scoreTime;
	}

	public String getStakeholder() {
		return stakeholder;
	}

	public void setStakeholder(String stakeholder) {
		this.stakeholder = stakeholder;
	}
	
}