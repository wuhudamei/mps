package cn.damei.entity.mobile.Inspector;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;


public class InspectKind  extends DataEntity2<InspectKind>{


	private static final long serialVersionUID = 1L;

	private Integer inspectBillId;
	private Integer checkKindId;
	private String checkKindName;
	private List<InspectItem> checkItemList;
	private String isChoosed;
	private String status;
	
	private Integer actualCheckPersonId;
	private  Date checkDate;
	private Double  totalScores;
	private Double actualScores;
	private Integer orderId;
	private Integer checkNodeId;
	private String   projectMode;
	
	
	
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getCheckNodeId() {
		return checkNodeId;
	}
	public void setCheckNodeId(Integer checkNodeId) {
		this.checkNodeId = checkNodeId;
	}
	public Integer getActualCheckPersonId() {
		return actualCheckPersonId;
	}
	public void setActualCheckPersonId(Integer actualCheckPersonId) {
		this.actualCheckPersonId = actualCheckPersonId;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public Double getTotalScores() {
		return totalScores;
	}
	public void setTotalScores(Double totalScores) {
		this.totalScores = totalScores;
	}
	public Double getActualScores() {
		return actualScores;
	}
	public void setActualScores(Double actualScores) {
		this.actualScores = actualScores;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsChoosed() {
		return isChoosed;
	}
	public void setIsChoosed(String isChoosed) {
		this.isChoosed = isChoosed;
	}
	public Integer getInspectBillId() {
		return inspectBillId;
	}
	public void setInspectBillId(Integer inspectBillId) {
		this.inspectBillId = inspectBillId;
	}
	public List<InspectItem> getCheckItemList() {
		return checkItemList;
	}
	public void setCheckItemList(List<InspectItem> checkItemList) {
		this.checkItemList = checkItemList;
	}
	public Integer getCheckKindId() {
		return checkKindId;
	}
	public void setCheckKindId(Integer checkKindId) {
		this.checkKindId = checkKindId;
	}
	public String getCheckKindName() {
		return checkKindName;
	}
	public void setCheckKindName(String checkKindName) {
		this.checkKindName = checkKindName;
	}
	
	
	
	
}
