/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizArea;

/**
 * 区域管理DAO接口
 * @author qhy
 * @version 2016-09-08
 */
@MyBatisDao
public interface BizAreaDao extends CrudDao<BizArea> {
	
}