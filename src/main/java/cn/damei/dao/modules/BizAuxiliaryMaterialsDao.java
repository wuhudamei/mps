/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizAuxiliaryMaterials;

/**
 * 辅料管理DAO接口
 * @author chy
 * @version 2016-09-09
 */
@MyBatisDao
public interface BizAuxiliaryMaterialsDao extends CrudDao<BizAuxiliaryMaterials> {
	public List<BizAuxiliaryMaterials> findExport(Date date, String string);
}