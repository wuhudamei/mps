
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.Leave;


@MyBatisDao
public interface LeaveDao extends CrudDao<Leave> {
	

	public int updateProcessInstanceId(Leave leave);
	

	public int updateRealityTime(Leave leave);
	
}
