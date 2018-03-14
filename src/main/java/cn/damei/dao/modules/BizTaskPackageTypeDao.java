/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizTaskPackageType;

/**
 * 单表生成DAO接口
 * @author ThinkGem
 * @version 2016-09-03
 */
@MyBatisDao
public interface BizTaskPackageTypeDao extends CrudDao<BizTaskPackageType> {

	BizTaskPackageType getById(String templatNumber);
	
	String findTypeByTaskpackageId(Integer taskpackageId);
}