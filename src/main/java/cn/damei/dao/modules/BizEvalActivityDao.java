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

/**
 * 评价活动设置DAO接口
 * @author wyb
 * @version 2017-02-25
 */
@MyBatisDao
public interface BizEvalActivityDao extends CrudDao2<BizEvalActivity> {


	/**
	 * 删除评价活动
	 * @param id
	 */
	void deleteEvalActivity(Integer id);
	
	/**
	 * 删除之前与评价活动相关联的任务包
	 * @param id
	 */
	void deleteEvalActivityTaskpackTemp(Integer id);

	/**
	 * 保存之后与评价活动相关联的任务包
	 * @param packageList
	 */
	void saveEvalActivityTaskpackTemp(List<BizEvalActivityTaskpackTemp> packageList);

	/**
	 * 删除之前与评价活动相关联的评价指标设置
	 * @param id
	 */
	void deleteEvalActivityIndex(Integer id);
	
	/**
	 * 保存之后与评价活动相关联的评价指标设置
	 * @param indexList
	 */
	void saveEvalActivityIndex(List<BizEvalActivityIndex> indexList);
	
	/**
	 * 保存评价活动的阶段信息
	 */
	void saveEvalActivityStage(List<BizEvalActivityStage> stageList);

	/**
	 * 停启用
	 * @param bizEvalActivity
	 */
	void isEnabled(BizEvalActivity bizEvalActivity);

	/**
	 * 判断评价活动中的任务包是否已经存在
	 * @param map
	 * @return
	 */
	Integer isTaskpackage(Map<String, Object> map);
	
	 /**
	  * 判断评价项目经理的活动中约检节点是否存在
	  * @param map
	  * @return
	  */
	Integer isCheckStage(Map<String, Object> map);
	
	/**
	 * 修改操作 查询已选择的任务包
	 * @param id
	 * @return
	 */
	List<BizEvalActivityTaskpackTemp> findEvalActivityTaskpackTemp(Integer id);

	/**
	 * 修改操作 查询已添加的评价指标设置
	 * @param id
	 * @return
	 */
	List<BizEvalActivityIndex> findEvalActivityIndex(Integer id);

	/**
	 * 查询字典表  评价类型
	 * @param type
	 * @return
	 */
	List<Dict> findDict(String type);
	
	public Integer queryIdByMap(Map<String, Object> map);

	/**
	 * 是否可以启用
	 * @param id
	 * @return
	 */
	List<Integer> isEnabledEval(Integer id);

	/**
	 *  工人评分查询
	 * @return
	 */
	public List<BizEvalWorkGrade> queryBizEvalWorkGradeList(BizEvalWorkGrade bizEvalWorkGrade);
	
	/**
	 * 项目经理评分查询
	 * @param bizEvalWorkGrade
	 * @return
	 */
	
	public List<BizEvalWorkGrade> queryBizEvalManagerGradeList(BizEvalWorkGrade bizEvalWorkGrade);
	
	/**
	 * 查询活动指标的评分
	 * @param orderTaskpackageId
	 * @param evalRoleType
	 * @return
	 */
	public List<BizEvalActivityIndex> queryEvalIndexRoleScore(Map<String,Object> map);
	
	public List<BizEvalActivityStage> queryEvalStage(Integer evalActivityId);

	List<String> findEvalType(BizEvalActivity bizEvalActivity);

	String checkExist(BizEvalActivity bizEvalActivity);

	void saveActivityRoleCycle(BizEvalActivity bizEvalActivity);

	List<BizEvalActivity> findEvalActivityRoleCycle(BizEvalActivity bizEvalActivity);

	void updateActivityRoleCycle(BizEvalActivity bizEvalActivity);

	String findEvalActivity(BizEvalActivity bizEvalActivity);
	
}