package cn.damei.dao.mobile.Inspector;


import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.BizEvalManagerDetail;
import cn.damei.entity.mobile.Inspector.BizEvalScore;
import cn.damei.entity.mobile.Inspector.BizEvalScoreRole;
import cn.damei.entity.mobile.Inspector.BizEvalScoreRoleIndex;
import cn.damei.entity.mobile.Inspector.EvalRewardTaskpack;
import cn.damei.entity.mobile.Inspector.EvaluateWorker;
import cn.damei.entity.modules.BizEvalRewardStar;
import cn.damei.entity.modules.BizEvalActivityIndex;

/**
 * 质检员评价工人
 * @author Administrator
 *
 */
@MyBatisDao
public interface InspectorEvaluateWorkerDao{

	/**
	 * 质检评价工人
	 * @param evaluateWorker
	 * @return
	 */
	List<EvaluateWorker> findEvalList(EvaluateWorker evaluateWorker);

	/**
	 * 通过评价单ID查询任务包相关信息
	 * @param evalTaskpackScoreId
	 * @return
	 */
	EvaluateWorker findOrderTaskpack(Integer evalTaskpackScoreId);

	/**
	 * 根据任务包ID查询相关联的评价活动
	 * @param evaluateWorker
	 * @return
	 */
	List<BizEvalActivityIndex> findEvalActivityIndex(EvaluateWorker evaluateWorker);

	/**
	 * 判断是否超过了24小时
	 * @param item
	 * @return
	 */
	BizEvalScore issave(BizEvalScore item);

	/**
	 * 更新评价任务包得分表
	 * @param item
	 */
	void updateEvalTaskpackScore(BizEvalScore item);

	/**
	 * 保存评价任务包角色得分表
	 * @param item2
	 */
	Integer insertEvalTaskpackRoleScore(BizEvalScoreRole item2);

	/**
	 * 保存评价任务包角色指标得分表
	 * @param list
	 */
	void insertEvalTaskpackRoleIndexScore(List<BizEvalScoreRoleIndex> list);

	/**
	 * 判断是否评价结束
	 * @param evalTaskpackScoreId
	 * @return
	 */
	BizEvalScoreRole isEndEvaluate(BizEvalScoreRole scoreBean);

	/**
	 * 根据评价分数查询奖金金额
	 * @param item
	 * @return
	 */
	BizEvalRewardStar isReward(BizEvalScore item);

	/**
	 * 保存评价奖励任务包表
	 * @param evalRewardTaskpack
	 */
	void insertEvalRewardTaskpack(EvalRewardTaskpack evalRewardTaskpack);
	
	/**
	 * 判断任务包是否存在评价活动
	 * @param orderTaskpackageId
	 * @return
	 */
	Integer checkEvalActivityByOrderTaskpackage(Integer orderTaskpackageId);
	
	
	List<BizEvalActivityIndex> queryEvalActivityIndexByPackageId(Integer orderTaskpackageId);

	List<BizEvalActivityIndex> queryManagerEvalActivityList(Map<String,Object> map);
	
	List<BizEvalActivityIndex> queryEvalActivityIndexByActivityId(Integer bizEvalActivityId);
	
	BizEvalManagerDetail queryEvalManagerDetail(Integer evalScoreId);

	void updateEvalTaskpackRoleScore(BizEvalScoreRole item2);

	String findEvalScoreRoleId(BizEvalScoreRole item2);

}
