
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.OaNotify;


@MyBatisDao
public interface OaNotifyDao extends CrudDao<OaNotify> {
	

	public Long findCount(OaNotify oaNotify);
	
}