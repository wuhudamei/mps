package cn.damei.entity.mobile.Inspector;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;

/**
 * 检查分类  
 * @author 梅浩
 *
 */
public class InspectKind  extends DataEntity2<InspectKind>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer inspectBillId;//质检单id
	private Integer checkKindId;//检查分类id
	private String checkKindName;//检查分类名称
	private List<InspectItem> checkItemList;//检查分类下的检查项集合
	private String isChoosed;//是否选过   1是选过 0是没选过
	private String status;//状态			0.暂存；2.项目经理已申请；5.已检查完成；6.已确认验收；
	
	private Integer actualCheckPersonId;// 实际质检人id
	private  Date checkDate;//质检时间
	private Double  totalScores;//总分
	private Double actualScores;//实际得分
	private Integer orderId;
	private Integer checkNodeId;
	private String   projectMode;//VO 工程模式
	
	
	
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
