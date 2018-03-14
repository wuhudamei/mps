
package cn.damei.entity.modules;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity;


public class ScoreOrder extends DataEntity<ScoreOrder> {
	
	private static final long serialVersionUID = 1L;

	private Integer orderId;
	private String scoreType;
	private String scoreTypeCn;
	private Integer scoreValue;
	private String scoreContent;
	private Date scoreTime;
	private String stakeholder;
	
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