
package cn.damei.dao.modules;


import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ScoreTeamMemberEvaluate;


@MyBatisDao
public interface ScoreTeamMemberEvaluateDao extends CrudDao<ScoreTeamMemberEvaluate> {
	

	public int evaluateValidate(Map<String,Object> map);
}