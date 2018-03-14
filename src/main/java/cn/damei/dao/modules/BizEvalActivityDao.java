package cn.damei.dao.modules;


import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizEvalActivity;
import cn.damei.entity.modules.BizEvalActivityIndex;
import cn.damei.entity.modules.BizEvalActivityStage;
import cn.damei.entity.modules.BizEvalActivityTaskpackTemp;
import cn.damei.entity.modules.BizEvalWorkGrade;
import cn.damei.entity.modules.Dict;


@MyBatisDao
public interface BizEvalActivityDao extends CrudDao2<BizEvalActivity> {



	void deleteEvalActivity(Integer id);
	

	void deleteEvalActivityTaskpackTemp(Integer id);


	void saveEvalActivityTaskpackTemp(List<BizEvalActivityTaskpackTemp> packageList);


	void deleteEvalActivityIndex(Integer id);
	

	void saveEvalActivityIndex(List<BizEvalActivityIndex> indexList);
	

	void saveEvalActivityStage(List<BizEvalActivityStage> stageList);


	void isEnabled(BizEvalActivity bizEvalActivity);


	Integer isTaskpackage(Map<String, Object> map);
	

	Integer isCheckStage(Map<String, Object> map);
	

	List<BizEvalActivityTaskpackTemp> findEvalActivityTaskpackTemp(Integer id);


	List<BizEvalActivityIndex> findEvalActivityIndex(Integer id);


	List<Dict> findDict(String type);
	
	public Integer queryIdByMap(Map<String, Object> map);


	List<Integer> isEnabledEval(Integer id);


	public List<BizEvalWorkGrade> queryBizEvalWorkGradeList(BizEvalWorkGrade bizEvalWorkGrade);
	

	
	public List<BizEvalWorkGrade> queryBizEvalManagerGradeList(BizEvalWorkGrade bizEvalWorkGrade);
	

	public List<BizEvalActivityIndex> queryEvalIndexRoleScore(Map<String,Object> map);
	
	public List<BizEvalActivityStage> queryEvalStage(Integer evalActivityId);

	List<String> findEvalType(BizEvalActivity bizEvalActivity);

	String checkExist(BizEvalActivity bizEvalActivity);

	void saveActivityRoleCycle(BizEvalActivity bizEvalActivity);

	List<BizEvalActivity> findEvalActivityRoleCycle(BizEvalActivity bizEvalActivity);

	void updateActivityRoleCycle(BizEvalActivity bizEvalActivity);

	String findEvalActivity(BizEvalActivity bizEvalActivity);
	
}