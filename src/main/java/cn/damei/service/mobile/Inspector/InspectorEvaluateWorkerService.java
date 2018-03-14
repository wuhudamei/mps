package cn.damei.service.mobile.Inspector;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.dao.mobile.Inspector.InspectorEvaluateWorkerDao;
import cn.damei.entity.mobile.Inspector.BizEvalManagerDetail;
import cn.damei.entity.mobile.Inspector.BizEvalScore;
import cn.damei.entity.mobile.Inspector.BizEvalScoreRole;
import cn.damei.entity.mobile.Inspector.BizEvalScoreRoleIndex;
import cn.damei.entity.mobile.Inspector.EvalRewardTaskpack;
import cn.damei.entity.mobile.Inspector.EvaluateWorker;
import cn.damei.entity.modules.BizEvalRewardStar;
import cn.damei.entity.modules.BizEvalActivityIndex;



/**
 * 质检评价工人
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly=true)
public class InspectorEvaluateWorkerService{
	
	@Autowired
	private InspectorEvaluateWorkerDao dao;

	/**
	 * 质检评价工人
	 * @param evaluateWorker
	 * @return
	 */
	public List<EvaluateWorker> findEvalList(EvaluateWorker evaluateWorker) {
		return dao.findEvalList(evaluateWorker);
	}

	/**
	 * 通过评价单ID查询任务包相关信息
	 * @param evalTaskpackScoreId
	 * @return
	 */
	public EvaluateWorker findOrderTaskpack(Integer evalTaskpackScoreId) {
		return dao.findOrderTaskpack(evalTaskpackScoreId);
	}

	/**
	 * 根据任务包ID查询相关联的评价活动
	 * @param evaluateWorker
	 * @return
	 */
	public List<BizEvalActivityIndex> findEvalActivityIndex(EvaluateWorker evaluateWorker) {
		return dao.findEvalActivityIndex(evaluateWorker);
	}

	/**
	 * 提交评价
	 * @param evalTaskpackScoreId
	 * @param evalActivityIndexId
	 * @param evalTotalScore
	 * @param number
	 * @param advise
	 * @param evalRoleType201 
	 * @return
	 */
	@Transactional(readOnly=false)
	public String evaluate(String evalTaskpackScoreId, String[] evalActivityIndexId, String[] evalTotalScore,
			String[] number, String advise,Integer employeeId,String phone, String evalRoleType) {
		
		Date date = new Date();
		//1.更新评价任务包得分表
		BizEvalScore item = new BizEvalScore();
		item.setId(Integer.valueOf(evalTaskpackScoreId));
		item.setUpdateDate(date);
		item.setStatusDatetime(date);
		item.setEvalStatus(ConstantUtils.EVAL_STATUS_1);
		item.setEvalType("1");
		dao.updateEvalTaskpackScore(item);
		
		List<BizEvalScoreRoleIndex> list = new ArrayList<BizEvalScoreRoleIndex>();
		double gotScore = 0; 
		if(null!=number && number.length>0){
			for(int i=0;i<number.length;i++){
				BizEvalScoreRoleIndex item3 = new BizEvalScoreRoleIndex();
				item3.setEvalActivityIndexId(Integer.valueOf(evalActivityIndexId[i]));
				item3.setGotScore((Integer.valueOf(number[i])) * (Double.valueOf(evalTotalScore[i])) * 0.2);
				list.add(item3);
				gotScore += (Integer.valueOf(number[i])) * (Double.valueOf(evalTotalScore[i])) * 0.2;
			}
		}
		
		
		//2.更新评价任务包角色得分表
		BizEvalScoreRole item2 = new BizEvalScoreRole();
		item2.setEvalScoreId(Integer.valueOf(evalTaskpackScoreId));
		/*item2.setEvalRoleType(evalRoleType);*/
		item2.setEvalByEmployeeId(employeeId);
		item2.setEvalByCusPhone(phone);
		item2.setGotScore(gotScore);
		item2.setEvalFeedback(advise);
		item2.setEvalDatetime(date);
		/*item2.setCreateDate(date);*/
		item2.setUpdateDate(date);
		/*item2.setDelFlag("0");*/
		item2.setEvalStatus(ConstantUtils.EVAL_ROLE_STATUS_1);
		item2.setEvalRoleType(ConstantUtils.EVAL_ROLE_TYPE_201);
		dao.updateEvalTaskpackRoleScore(item2);
		
		String scoreRoleId =  dao.findEvalScoreRoleId(item2);
		
		//保存评价任务包角色指标得分表
		if(null!=list && list.size()>0){
			for(BizEvalScoreRoleIndex index:list){
				index.setEvalScoreRoleId(Integer.parseInt(scoreRoleId));
				index.setCreateDate(date);
				index.setUpdateDate(date);
				index.setDelFlag("0");
				
			}
			dao.insertEvalTaskpackRoleIndexScore(list);
		}
		
		return "0";
	}

	/**
	 * 判断是否超过了12小时
	 * @param item
	 * @return
	 */
	public BizEvalScore issave(BizEvalScore item) {
		return dao.issave(item);
	}

	/**
	 * 判断是否评价结束
	 * @param valueOf
	 * @return
	 */
	public BizEvalScoreRole isEndEvaluate(BizEvalScoreRole scoreBean) {
		return dao.isEndEvaluate(scoreBean);
	}

	/**
	 * 更新评价任务包得分表
	 * @param evalTaskpackScore
	 */
	@Transactional(readOnly=false)
	public void updateEvalTaskpackScore(BizEvalScore evalTaskpackScore) {
		dao.updateEvalTaskpackScore(evalTaskpackScore);
	}

	/**
	 * 根据评价分数查询奖金金额
	 * @param item
	 * @return
	 */
	public BizEvalRewardStar isReward(BizEvalScore item) {
		return dao.isReward(item);
	}

	/**
	 * 保存评价奖励任务包表
	 * @param evalRewardTaskpack
	 */
	@Transactional(readOnly=false)
	public void insertEvalRewardTaskpack(EvalRewardTaskpack evalRewardTaskpack) {
		dao.insertEvalRewardTaskpack(evalRewardTaskpack);
	}
	
	public List<BizEvalActivityIndex> queryEvalActivityIndexByPackageId(Integer orderTaskpackageId){
		return dao.queryEvalActivityIndexByPackageId(orderTaskpackageId);
	}
	
	public List<BizEvalActivityIndex> queryEvalActivityIndexByActivityId(Integer bizEvalActivityId){
		return dao.queryEvalActivityIndexByActivityId(bizEvalActivityId);
	}
	
	public List<BizEvalActivityIndex> queryManagerEvalActivityList(Map<String,Object> map){
		return dao.queryManagerEvalActivityList(map);
	}
	
	public BizEvalManagerDetail queryEvalManagerDetail(Integer evalScoreId){
		return dao.queryEvalManagerDetail(evalScoreId);
	}

}
