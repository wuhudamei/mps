
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMessagegroup;


@MyBatisDao
public interface BizMessagegroupDao extends CrudDao<BizMessagegroup> {


	BizMessagegroup getByStoreId(String storeId,String messageGroupType);
	
}