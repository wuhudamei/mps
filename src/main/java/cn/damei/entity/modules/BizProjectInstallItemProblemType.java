package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;

/**
 * 工程安装问题分类设置Entity
 * @author wyb
 * @version 2016-10-27
 */
public class BizProjectInstallItemProblemType extends DataEntity2<BizProjectInstallItemProblemType> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;		// 门店id -- '
	private String projectMode;		// 工程模式
	private String typeName; 		//问题分类
	private String isEnabled;		// 是否启用
	private String businessType;	//所属业务
	private double punishScore;		//扣除分数
	private double punishMoney; 	//扣除金额
	private String punishRemarks;		//处罚说明

	
	public String getPunishRemarks() {
		return punishRemarks;
	}

	public void setPunishRemarks(String punishRemarks) {
		this.punishRemarks = punishRemarks;
	}

	public double getPunishScore() {
		return punishScore;
	}

	public void setPunishScore(double punishScore) {
		this.punishScore = punishScore;
	}

	public double getPunishMoney() {
		return punishMoney;
	}

	public void setPunishMoney(double punishMoney) {
		this.punishMoney = punishMoney;
	}

	public BizProjectInstallItemProblemType() {
		super();
	}

	public BizProjectInstallItemProblemType(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	@Length(min=0, max=100, message="检查项名称 -- '长度必须介于 0 和 100 之间")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Length(min=0, max=1, message="是否启用 -- '长度必须介于 0 和 1之间")
	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	
	

	
	
}