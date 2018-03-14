/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ProjectItemType;

/**
 * 施工项类型DAO接口
 * @author 梅浩
 * @version 2016-11-15
 */
@MyBatisDao
public interface ProjectItemTypeDao extends CrudDao<ProjectItemType> {
	
}