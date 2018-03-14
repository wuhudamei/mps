package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizAssessRewardPunish;


@MyBatisDao
public interface BizAssessRewardPunishDao extends CrudDao2<BizAssessRewardPunish> {

	public double queryTotalAmountByParam(BizAssessRewardPunish bizAssessRewardPunish);
	
	public List<BizAssessRewardPunish> queryAssessRewardPunishByParam(Map<String,Object> param);
	
	public void updateRewardPunishStatus(List<BizAssessRewardPunish> list);
	
	public List<BizAssessRewardPunish> queryPmRewardPunishBySettleParam(Map<String,Object> param);
	
	public void updateByParam(Map<String,Object> param);
	
	public List<BizAssessRewardPunish> queryEvalPmRewardPunishInfoByParam(BizAssessRewardPunish bizAssessRewardPunish);

	public void deleteBatch(Map<String,Object> param);

	public void insertBatch(List<BizAssessRewardPunish> list);

	public List<BizAssessRewardPunish> findInspectionList(BizAssessRewardPunish bizAssessRewardPunish);

	public void delUpdateBatch(Map<String,Object> param);
}
