/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizApplyCheckDelete;

/**
 * 约检信息管理DAO接口
 * @author 梅浩
 * @version 2017-04-21
 */
@MyBatisDao
public interface BizApplyCheckDeleteDao extends CrudDao<BizApplyCheckDelete> {
	
}