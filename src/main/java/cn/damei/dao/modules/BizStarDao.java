
package cn.damei.dao.modules;


import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizStar;


@MyBatisDao
public interface BizStarDao extends CrudDao<BizStar> {
	public List<BizStar> findStarByStoreId(String storeId);
}