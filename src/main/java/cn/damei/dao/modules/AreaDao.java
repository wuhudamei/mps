
package cn.damei.dao.modules;

import cn.damei.common.persistence.TreeDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.Area;


@MyBatisDao
public interface AreaDao extends TreeDao<Area> {
	
}
