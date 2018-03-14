/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;


import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizStar;

/**
 * 星级承接量DAO接口
 * @author wyb
 * @version 2016-09-05
 */
@MyBatisDao
public interface BizStarDao extends CrudDao<BizStar> {
	public List<BizStar> findStarByStoreId(String storeId);
}