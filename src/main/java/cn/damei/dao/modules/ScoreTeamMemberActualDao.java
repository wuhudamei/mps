
package cn.damei.dao.modules;


import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ScoreTeamMemberActual;


@MyBatisDao
public interface ScoreTeamMemberActualDao extends CrudDao<ScoreTeamMemberActual> {
	
	

	public void updateEmployee(Map<String,Object> map);
	

	public ScoreTeamMemberActual selectByEmployee(Map<String,Object> map);
}