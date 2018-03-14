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


@MyBatisDao
public interface InspectorEvaluateWorkerDao{


	List<EvaluateWorker> findEvalList(EvaluateWorker evaluateWorker);


	EvaluateWorker findOrderTaskpack(Integer evalTaskpackScoreId);


	List<BizEvalActivityIndex> findEvalActivityIndex(EvaluateWorker evaluateWorker);


	BizEvalScore issave(BizEvalScore item);


	void updateEvalTaskpackScore(BizEvalScore item);


	Integer insertEvalTaskpackRoleScore(BizEvalScoreRole item2);


	void insertEvalTaskpackRoleIndexScore(List<BizEvalScoreRoleIndex> list);


	BizEvalScoreRole isEndEvaluate(BizEvalScoreRole scoreBean);


	BizEvalRewardStar isReward(BizEvalScore item);


	void insertEvalRewardTaskpack(EvalRewardTaskpack evalRewardTaskpack);
	

	Integer checkEvalActivityByOrderTaskpackage(Integer orderTaskpackageId);
	
	
	List<BizEvalActivityIndex> queryEvalActivityIndexByPackageId(Integer orderTaskpackageId);

	List<BizEvalActivityIndex> queryManagerEvalActivityList(Map<String,Object> map);
	
	List<BizEvalActivityIndex> queryEvalActivityIndexByActivityId(Integer bizEvalActivityId);
	
	BizEvalManagerDetail queryEvalManagerDetail(Integer evalScoreId);

	void updateEvalTaskpackRoleScore(BizEvalScoreRole item2);

	String findEvalScoreRoleId(BizEvalScoreRole item2);

}
