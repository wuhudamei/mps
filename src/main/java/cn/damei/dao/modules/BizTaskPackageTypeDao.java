
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizTaskPackageType;


@MyBatisDao
public interface BizTaskPackageTypeDao extends CrudDao<BizTaskPackageType> {

	BizTaskPackageType getById(String templatNumber);
	
	String findTypeByTaskpackageId(Integer taskpackageId);
}