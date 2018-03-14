/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizQcMaxCount;

/**
 * 约检数量配置DAO接口
 * @author 梅浩
 * @version 2017-04-20
 */
@MyBatisDao
public interface BizQcMaxCountDao extends CrudDao<BizQcMaxCount> {
	Integer storeOnlyForPqcCount(Integer storeId);
}