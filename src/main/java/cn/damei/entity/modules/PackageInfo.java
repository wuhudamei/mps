package cn.damei.entity.modules;

/**
 * 基装成本接口任务包信息实体类
 * @author hyh
 *
 */
public class PackageInfo{

	private String storeId; //门店id
	
	private String orderNum;//订单编号
	
	private String packageName;//任务包名称
	
	private String workerGroupName;//工人组长
	
	private Double laborSettleAmount;//人工费结算总金额
	
	private Double auxiliaryMaterialsSettleAmount;//辅料费结算总金额
	
	private Double laborAuxiliaryMaterialsSettleAmount;//（人工+辅料）工料费结算总金额
	
//	private Double materialsAmount;//领用材料费：辅料扣款 + 沙子水泥扣款 
	
	private Double guaranteeMoneyAmount; // 质保金金额
	
	private Double rewardPunishAmount;//奖罚：工期扣款 + 管理处罚 + 质检罚款 + 公司罚款 + 奖励金额
	
	private Double workerGroupSettleAmount;//结算金额：工人组结算金额
	
	
	private Integer settleStyle;  //任务包结算方式 1、包工包料；2、包工
	
	private Double settlementAmount;//结算总金额
	
	private Double auxiliaryAmountWangzhen;//任务包材料费用（网真）
	
	private Double auxiliaryAmountworker;//任务包材料费用（工人结算价）
	
	private Double packageBudgetAmount; //任务包预算总金额
	
	public Double getLaborSettleAmount() {
		return laborSettleAmount;
	}

	public void setLaborSettleAmount(Double laborSettleAmount) {
		this.laborSettleAmount = laborSettleAmount;
	}

	public Double getAuxiliaryMaterialsSettleAmount() {
		return auxiliaryMaterialsSettleAmount;
	}

	public void setAuxiliaryMaterialsSettleAmount(Double auxiliaryMaterialsSettleAmount) {
		this.auxiliaryMaterialsSettleAmount = auxiliaryMaterialsSettleAmount;
	}

	public Double getLaborAuxiliaryMaterialsSettleAmount() {
		return laborAuxiliaryMaterialsSettleAmount;
	}

	public void setLaborAuxiliaryMaterialsSettleAmount(Double laborAuxiliaryMaterialsSettleAmount) {
		this.laborAuxiliaryMaterialsSettleAmount = laborAuxiliaryMaterialsSettleAmount;
	}

//	public Double getMaterialsAmount() {
//		return materialsAmount;
//	}
//
//	public void setMaterialsAmount(Double materialsAmount) {
//		this.materialsAmount = materialsAmount;
//	}

	public Double getGuaranteeMoneyAmount() {
		return guaranteeMoneyAmount;
	}

	public void setGuaranteeMoneyAmount(Double guaranteeMoneyAmount) {
		this.guaranteeMoneyAmount = guaranteeMoneyAmount;
	}

	public Double getRewardPunishAmount() {
		return rewardPunishAmount;
	}

	public void setRewardPunishAmount(Double rewardPunishAmount) {
		this.rewardPunishAmount = rewardPunishAmount;
	}

	public Double getWorkerGroupSettleAmount() {
		return workerGroupSettleAmount;
	}

	public void setWorkerGroupSettleAmount(Double workerGroupSettleAmount) {
		this.workerGroupSettleAmount = workerGroupSettleAmount;
	}

	public Double getPackageBudgetAmount() {
		return packageBudgetAmount;
	}

	public void setPackageBudgetAmount(Double packageBudgetAmount) {
		this.packageBudgetAmount = packageBudgetAmount;
	}

	public String getWorkerGroupName() {
		return workerGroupName;
	}

	public void setWorkerGroupName(String workerGroupName) {
		this.workerGroupName = workerGroupName;
	}

	public Double getSettlementAmount() {
		return settlementAmount;
	}

	public void setSettlementAmount(Double settlementAmount) {
		this.settlementAmount = settlementAmount;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Integer getSettleStyle() {
		return settleStyle;
	}

	public void setSettleStyle(Integer settleStyle) {
		this.settleStyle = settleStyle;
	}

	public Double getAuxiliaryAmountWangzhen() {
		return auxiliaryAmountWangzhen;
	}

	public void setAuxiliaryAmountWangzhen(Double auxiliaryAmountWangzhen) {
		this.auxiliaryAmountWangzhen = auxiliaryAmountWangzhen;
	}

	public Double getAuxiliaryAmountworker() {
		return auxiliaryAmountworker;
	}

	public void setAuxiliaryAmountworker(Double auxiliaryAmountworker) {
		this.auxiliaryAmountworker = auxiliaryAmountworker;
	}
	
	
}
