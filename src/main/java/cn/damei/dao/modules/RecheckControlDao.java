/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.RecheckControl;

/**
 * 复检单监控表DAO接口
 * @author wyb
 * @version 2016-10-31
 */
@MyBatisDao
public interface RecheckControlDao extends CrudDao<RecheckControl> {
	
}