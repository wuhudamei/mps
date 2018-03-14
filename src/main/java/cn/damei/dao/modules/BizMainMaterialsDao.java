/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMainMaterials;

/**
 * 主材管理DAO接口
 * @author qww
 * @version 2016-10-10
 */
@MyBatisDao
public interface BizMainMaterialsDao extends CrudDao2<BizMainMaterials> {
	
}