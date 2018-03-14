package cn.damei.service.modules;

import java.util.*;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizAssessRewardPunishDao;
import cn.damei.entity.modules.BizAssessRewardPunish;

/**
 * 产业项目经理奖惩Service
 * @author  hyh
 *
 */
@Service
@Transactional(readOnly = true)
public class BizAssessRewardPunishService extends CrudService2<BizAssessRewardPunishDao, BizAssessRewardPunish> {
	public double queryTotalAmountByParam(BizAssessRewardPunish bizAssessRewardPunish){
		return dao.queryTotalAmountByParam(bizAssessRewardPunish);
	}
	
	public List<BizAssessRewardPunish> queryPmRewardPunishBySettleParam(Map<String,Object> param){
		return dao.queryPmRewardPunishBySettleParam(param);
	}

	public Page<BizAssessRewardPunish> findInspectionPage(Page<BizAssessRewardPunish> page, BizAssessRewardPunish bizAssessRewardPunish) {
		bizAssessRewardPunish.setPage(page);
		page.setList(dao.findInspectionList(bizAssessRewardPunish));
		return page;
	}
	
	public void updateByParam(Map<String,Object> param){
		 dao.updateByParam(param);
	}
	
	public List<BizAssessRewardPunish> queryEvalPmRewardPunishInfoByParam(BizAssessRewardPunish bizAssessRewardPunish){
		return dao.queryEvalPmRewardPunishInfoByParam(bizAssessRewardPunish);
	}
	
	public List<BizAssessRewardPunish> queryAssessRewardPunishByParam(Map<String,Object> param){
		return dao.queryAssessRewardPunishByParam(param);
	}

	@Transactional(readOnly = false)
	public void saveBizInspectionReward(BizAssessRewardPunish bizAssessRewardPunish,String[] assessRuleTypeIds,
							String[] assessRuleIds, String[] rewardPunishAmounts,
							String[] rewardPunishScores ,String[]   detailRemarks) {
		Date date =new Date();
		User user = UserUtils.getUser();
		// 如果已存在，先进行删除
          if(bizAssessRewardPunish.getIds() != null && !bizAssessRewardPunish.getIds().equals("")){
              List<String> ids = new ArrayList<String>();
              String[] idsArr = bizAssessRewardPunish.getIds().split(",");
              for(int i = 0;i < idsArr.length; i++){
				  ids.add(idsArr[i]);
			  }
			  Map<String,Object> param = new HashMap<String,Object>();
			  param.put("ids",ids);
			  dao.deleteBatch(param);
		  }

		  List<BizAssessRewardPunish> saveList = new ArrayList<BizAssessRewardPunish>();
		  for(int i =0 ;i<assessRuleTypeIds.length;i++){
			  BizAssessRewardPunish rewardPunish = new BizAssessRewardPunish();
			  rewardPunish.setRelatedBusinessType("1");
			  rewardPunish.setRelatedBusinessIdInt(bizAssessRewardPunish.getRelatedBusinessIdInt());
			  rewardPunish.setAssessRuleId(Integer.valueOf(assessRuleIds[i]));
			  if(null!=detailRemarks&&!StringUtils.isEmpty(detailRemarks[i])){
				  rewardPunish.setDetailRemarks(detailRemarks[i]);
			  }
			  rewardPunish.setGeneralRemarks(bizAssessRewardPunish.getGeneralRemarks());
			  rewardPunish.setIsRewardOrPunish(bizAssessRewardPunish.getIsRewardOrPunish());
			  rewardPunish.setRewardPunishTargetType(bizAssessRewardPunish.getRewardPunishTargetType());
			  rewardPunish.setRewardPunishTargetEmployeeType(bizAssessRewardPunish.getRewardPunishTargetEmployeeType());
			  rewardPunish.setRewardPunishTargetEmployeeId(bizAssessRewardPunish.getRewardPunishTargetEmployeeId());
			  rewardPunish.setRewardPunishAmount(Double.valueOf(rewardPunishAmounts[i]));
			  rewardPunish.setRewardPunishScore(Double.valueOf(rewardPunishScores[i]));
			  rewardPunish.setRewardPunishDatetime(date);
			  rewardPunish.setRewardPunishStatus("1");
			  rewardPunish.setRewardPunishDatetime(date);
			  if (user.getEmpId() != null) {
				  rewardPunish.setStatusOperator(Integer.valueOf(user.getEmpId()));
			  }
			  rewardPunish.setStatusDescribe("未关联结算");
			  rewardPunish.setIsMonthInspection(bizAssessRewardPunish.getIsMonthInspection());
			  rewardPunish.preInsert();
			  rewardPunish.setCreateDate(date);
			  rewardPunish.setUpdateDate(date);
			  saveList.add(rewardPunish);
		  }

		  dao.insertBatch(saveList);
	}

	@Transactional(readOnly = false)
	public void delUpdateBatch(BizAssessRewardPunish bizAssessRewardPunish){
		Map<String,Object> param = new HashMap<String,Object>();
		List<String> ids = new ArrayList<String>();
		String[] idsArr = bizAssessRewardPunish.getIds().split(",");
		for(int i = 0;i < idsArr.length; i++){
			ids.add(idsArr[i]);
		}
		param.put("ids",ids);
		dao.delUpdateBatch(param);
	}
}
