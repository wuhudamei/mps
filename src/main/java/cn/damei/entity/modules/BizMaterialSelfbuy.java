
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;


public class BizMaterialSelfbuy extends DataEntity2<BizMaterialSelfbuy> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String projectMode;
	private String materialName;
	private String materialCode;
	private Integer settleRate;
	private String settleStage;
	private String isEnabled;
	
	private Double settleRateTwo;
	private String storeName;
	private String projectmodeName;
	private String settleStageName;

	public BizMaterialSelfbuy() {
		super();
	}

	public BizMaterialSelfbuy(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=10, message="工程模式长度必须介于 0 和 10 之间")
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
	@Length(min=0, max=100, message="材料名称长度必须介于 0 和 100 之间")
	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	
	@Length(min=0, max=100, message="材料编码长度必须介于 0 和 100 之间")
	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	
	
	public Integer getSettleRate() {
		return settleRate;
	}

	public void setSettleRate(Integer settleRate) {
		this.settleRate = settleRate;
	}

	public Double getSettleRateTwo() {
		return settleRateTwo;
	}

	public void setSettleRateTwo(Double settleRateTwo) {
		this.settleRateTwo = settleRateTwo;
	}

	@Length(min=0, max=10, message="所属结算阶段长度必须介于 0 和 10 之间")
	public String getSettleStage() {
		return settleStage;
	}

	public void setSettleStage(String settleStage) {
		this.settleStage = settleStage;
	}
	
	@Length(min=0, max=1, message="是否启用长度必须介于 0 和 1 之间")
	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getProjectmodeName() {
		return projectmodeName;
	}

	public void setProjectmodeName(String projectmodeName) {
		this.projectmodeName = projectmodeName;
	}

	public String getSettleStageName() {
		return settleStageName;
	}

	public void setSettleStageName(String settleStageName) {
		this.settleStageName = settleStageName;
	}
	
	
	
}