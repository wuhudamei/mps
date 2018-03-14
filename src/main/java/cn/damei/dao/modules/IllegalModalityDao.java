
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.IllegalModality;


@MyBatisDao
public interface IllegalModalityDao extends CrudDao2<IllegalModality> {
	
	

	public IllegalModality getStoreKindItemInfoByIllegalModalityId(Integer id);
	
}