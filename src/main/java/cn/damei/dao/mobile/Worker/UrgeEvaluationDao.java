package cn.damei.dao.mobile.Worker;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Worker.UrgeEvaluation;

@MyBatisDao
public interface UrgeEvaluationDao {

	List<UrgeEvaluation> findEvaluationTaskpageByGroupId(Integer emgrouprelationId);

	List<UrgeEvaluation> findEvaluationTaskpageByGroupId(UrgeEvaluation urgeEvaluation);

}
