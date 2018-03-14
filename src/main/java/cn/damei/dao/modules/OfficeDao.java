
package cn.damei.dao.modules;

import cn.damei.common.persistence.TreeDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.Office;


@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {
	
}
