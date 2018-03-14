
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ArticleData;


@MyBatisDao
public interface ArticleDataDao extends CrudDao<ArticleData> {
	
}
