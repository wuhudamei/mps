/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ProjectItemPrice;

/**
 * 施工项价格DAO接口
 * @author 梅浩
 * @version 2016-11-16
 */
@MyBatisDao
public interface ProjectItemPriceDao extends CrudDao<ProjectItemPrice> {
	
	
	/**
	 * 当前最大版本号
	 * @return
	 */
	public Integer   setMaxVersion(ProjectItemPrice price);

    boolean checkedDate(ProjectItemPrice projectItemPrice);
}