
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizSeiralnum;


@MyBatisDao
public interface BizSeiralnumDao extends CrudDao2<BizSeiralnum> {
	
	public BizSeiralnum querySeiralnumByType(String bussinessType);

}