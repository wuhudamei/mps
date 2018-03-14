/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.IllegalModality;

/**
 * PC违规形式DAO接口
 * @author 梅浩
 * @version 2016-10-26
 */
@MyBatisDao
public interface IllegalModalityDao extends CrudDao2<IllegalModality> {
	
	
	/**
	 * 根据 违规形式id,查询门店,检查项,检查分类信息
	 * @param id
	 * @return
	 */
	public IllegalModality getStoreKindItemInfoByIllegalModalityId(Integer id);
	
}