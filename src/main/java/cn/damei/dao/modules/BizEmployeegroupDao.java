
package cn.damei.dao.modules;

import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizEmployeegroup;


@MyBatisDao
public interface BizEmployeegroupDao extends CrudDao<BizEmployeegroup> {


	

	public int hasInGroup(Map<String,Object> param);

	public BizEmployeegroup findBizEmployeegroup(String string);
	
	public void updateStarLog(String groupid, Integer integer, String string, Integer integer2);
}