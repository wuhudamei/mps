/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMessagegroup;

/**
 * 短信组DAO接口
 * @author 汪文文
 * @version 2016-09-06
 */
@MyBatisDao
public interface BizMessagegroupDao extends CrudDao<BizMessagegroup> {

	/**
	 * 获取短信组
	 * @param storeId
	 * @return BizMessagegroup
	 */
	BizMessagegroup getByStoreId(String storeId,String messageGroupType);
	
}