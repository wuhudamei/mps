
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.TestDataChild;


@MyBatisDao
public interface TestDataChildDao extends CrudDao<TestDataChild> {
	
}