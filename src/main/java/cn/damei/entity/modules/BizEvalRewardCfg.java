
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;


public class BizEvalRewardCfg extends DataEntity2<BizEvalRewardCfg> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String projectMode;
	private String rewardTargetType;
	private String rewardName;
	private Date rewardStartDatetime;
	private Date rewardEndDatetime;
	private String isEnabled;

	private List<BizEvalRewardStar> list;
	
	public BizEvalRewardCfg() {
		super();
	}

	public BizEvalRewardCfg(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=10, message="&sup1;&curren;&sup3;&Igrave;&Auml;&pound;&Ecirc;&frac12; -- '长度必须介于 0 和 10 之间")
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public String getRewardTargetType() {
		return rewardTargetType;
	}

	public void setRewardTargetType(String rewardTargetType) {
		this.rewardTargetType = rewardTargetType;
	}
	
	@Length(min=0, max=100, message="&frac12;&plusmn;&Agrave;&oslash;&Atilde;&ucirc;&sup3;&AElig; -- '长度必须介于 0 和 100 之间")
	public String getRewardName() {
		return rewardName;
	}

	public void setRewardName(String rewardName) {
		this.rewardName = rewardName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRewardStartDatetime() {
		return rewardStartDatetime;
	}

	public void setRewardStartDatetime(Date rewardStartDatetime) {
		this.rewardStartDatetime = rewardStartDatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRewardEndDatetime() {
		return rewardEndDatetime;
	}

	public void setRewardEndDatetime(Date rewardEndDatetime) {
		this.rewardEndDatetime = rewardEndDatetime;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	public List<BizEvalRewardStar> getList() {
		return list;
	}

	public void setList(List<BizEvalRewardStar> list) {
		this.list = list;
	}
}