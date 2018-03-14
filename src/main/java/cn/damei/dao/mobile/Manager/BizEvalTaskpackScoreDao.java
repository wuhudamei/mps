package cn.damei.dao.mobile.Manager;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.EvalScore;
import cn.damei.entity.modules.BizEvalActivity;

/** 
* @author 邱威威qww 
* @version 创建时间：2016年10月5日 下午4:28:27 
* 类说明 
*/
@MyBatisDao
public interface BizEvalTaskpackScoreDao extends CrudDao2<EvalScore>{

    public Integer queryByCondition(Map<String, Object> map);

    public List<EvalScore> queryEvalTaskpackScoreByMap(Map<String, Object> map);

    public Integer queryCountByMap(Map<String, Object> map);
    
    public EvalScore queryEvalScoreByBusinessId(Map<String, Object> map);

	public List<BizEvalActivity> findActivityRoleCycle(Integer evalActivityId);

	public List<EvalScore> queryEvalRoleOvertimeByMap(Map<String, Object> map);

	public List<EvalScore> findEvalScoreByEvalStatus();

	public List<EvalScore> findEvalRoleByEvalScoreId(Integer id);

	public void updateEvalRole(EvalScore bizEvalTaskpackScore);
}