/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizTaskPackageAuxiliaryMaterials;

/**
 * 任务包辅料对照表管理DAO接口
 * @author wangchao
 * @version 2016-09-09
 */
@MyBatisDao
public interface BizTaskPackageAuxiliaryMaterialsDao extends CrudDao<BizTaskPackageAuxiliaryMaterials> {
	
	public List<BizTaskPackageAuxiliaryMaterials> checkTaskPackageByTemplateId(Integer templateId);
	
}