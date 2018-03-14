
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ProjectItemPrice;


@MyBatisDao
public interface ProjectItemPriceDao extends CrudDao<ProjectItemPrice> {
	
	

	public Integer   setMaxVersion(ProjectItemPrice price);

    boolean checkedDate(ProjectItemPrice projectItemPrice);
}