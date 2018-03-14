
package cn.damei.entity.mobile.Manager;

import org.hibernate.validator.constraints.Length;
import cn.damei.common.persistence.DataEntity2;


public class ApplyMaterialsStandardReceiveDetail extends DataEntity2<ApplyMaterialsStandardReceiveDetail> {
	
	private static final long serialVersionUID = 1L;
	private Integer materialsStandardReceiveBillId;
	private Integer materialsId;
	private String materialsType;
	private String materialsName;
	private String materialsUnit;
	private Double materialsPrice;
	private Double receiveNumber;
	private Double materialsAmount;
	private Double maxReceiveNumberSnap;
	private Double applyNumberTotalSnap;
	private Double receiveNumberTotalSnap;
	

	public Double getMaxReceiveNumberSnap() {
		return maxReceiveNumberSnap;
	}

	public void setMaxReceiveNumberSnap(Double maxReceiveNumberSnap) {
		this.maxReceiveNumberSnap = maxReceiveNumberSnap;
	}

	public Double getApplyNumberTotalSnap() {
		return applyNumberTotalSnap;
	}

	public void setApplyNumberTotalSnap(Double applyNumberTotalSnap) {
		this.applyNumberTotalSnap = applyNumberTotalSnap;
	}

	public Double getReceiveNumberTotalSnap() {
		return receiveNumberTotalSnap;
	}

	public void setReceiveNumberTotalSnap(Double receiveNumberTotalSnap) {
		this.receiveNumberTotalSnap = receiveNumberTotalSnap;
	}

	public ApplyMaterialsStandardReceiveDetail() {
		super();
	}

	public ApplyMaterialsStandardReceiveDetail(Integer id){
		super(id);
	}

	public Integer getMaterialsId() {
		return materialsId;
	}

	public void setMaterialsId(Integer materialsId) {
		this.materialsId = materialsId;
	}

	public Integer getMaterialsStandardReceiveBillId() {
		return materialsStandardReceiveBillId;
	}

	public void setMaterialsStandardReceiveBillId(Integer materialsStandardReceiveBillId) {
		this.materialsStandardReceiveBillId = materialsStandardReceiveBillId;
	}
	
	@Length(min=0, max=100, message="物料类别长度必须介于 0 和 100 之间")
	public String getMaterialsType() {
		return materialsType;
	}

	public void setMaterialsType(String materialsType) {
		this.materialsType = materialsType;
	}
	
	@Length(min=0, max=100, message="物料名称长度必须介于 0 和 100 之间")
	public String getMaterialsName() {
		return materialsName;
	}

	public void setMaterialsName(String materialsName) {
		this.materialsName = materialsName;
	}
	
	@Length(min=0, max=10, message="物料单位长度必须介于 0 和 10 之间")
	public String getMaterialsUnit() {
		return materialsUnit;
	}

	public void setMaterialsUnit(String materialsUnit) {
		this.materialsUnit = materialsUnit;
	}
	
	public Double getMaterialsPrice() {
		return materialsPrice;
	}

	public void setMaterialsPrice(Double materialsPrice) {
		this.materialsPrice = materialsPrice;
	}
	
	public Double getReceiveNumber() {
		return receiveNumber;
	}

	public void setReceiveNumber(Double receiveNumber) {
		this.receiveNumber = receiveNumber;
	}
	
	public Double getMaterialsAmount() {
		return materialsAmount;
	}

	public void setMaterialsAmount(Double materialsAmount) {
		this.materialsAmount = materialsAmount;
	}
	
}