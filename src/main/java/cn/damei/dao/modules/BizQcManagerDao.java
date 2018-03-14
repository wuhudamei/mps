
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizQcManager;


@MyBatisDao
public interface BizQcManagerDao extends CrudDao2<BizQcManager> {


	Integer findStore(Integer managerEmployeeId);
	
}