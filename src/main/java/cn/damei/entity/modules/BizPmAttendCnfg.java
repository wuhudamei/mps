/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 项目经理考勤基础设置Entity
 * @author lzm
 * @version 2017-08-02
 */
public class BizPmAttendCnfg extends DataEntity<BizPmAttendCnfg> {
	
	private static final long serialVersionUID = 1L;
	private String storeId;		
	private String projectMode;		
	private String absencePunishAmount;		
	private String signCycleDaysBasicwork;		
	private String signCycleDaysComplete;		
	private String effectMonth;		
	private String isEnabled;	
	
	private List<BizPmAttendCnfgStar> bizPmAttendCnfgStarList;
	public BizPmAttendCnfg() {
		super();
	}
	
	public List<BizPmAttendCnfgStar> getBizPmAttendCnfgStarList() {
		return bizPmAttendCnfgStarList;
	}

	public void setBizPmAttendCnfgStarList(
			List<BizPmAttendCnfgStar> bizPmAttendCnfgStarList) {
		this.bizPmAttendCnfgStarList = bizPmAttendCnfgStarList;
	}

	public BizPmAttendCnfg(String id){
		super(id);
	}

	@Length(min=0, max=11, message="store_id长度必须介于 0 和 11 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=10, message="project_mode长度必须介于 0 和 10 之间")
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
	public String getAbsencePunishAmount() {
		return absencePunishAmount;
	}

	public void setAbsencePunishAmount(String absencePunishAmount) {
		this.absencePunishAmount = absencePunishAmount;
	}
	
	@Length(min=0, max=11, message="sign_cycle_days_basicwork长度必须介于 0 和 11 之间")
	public String getSignCycleDaysBasicwork() {
		return signCycleDaysBasicwork;
	}

	public void setSignCycleDaysBasicwork(String signCycleDaysBasicwork) {
		this.signCycleDaysBasicwork = signCycleDaysBasicwork;
	}
	
	@Length(min=0, max=11, message="sign_cycle_days_complete长度必须介于 0 和 11 之间")
	public String getSignCycleDaysComplete() {
		return signCycleDaysComplete;
	}

	public void setSignCycleDaysComplete(String signCycleDaysComplete) {
		this.signCycleDaysComplete = signCycleDaysComplete;
	}
	
	@Length(min=0, max=20, message="effect_month长度必须介于 0 和 20 之间")
	public String getEffectMonth() {
		return effectMonth;
	}

	public void setEffectMonth(String effectMonth) {
		this.effectMonth = effectMonth;
	}
	
	@Length(min=0, max=1, message="is_enabled长度必须介于 0 和 1 之间")
	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}
	
}