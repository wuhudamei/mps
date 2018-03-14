/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizTaskPackageTemplatCheckNodeRel;

/**
 * 付款单付款尾款节点设置DAO接口
 * @author www
 * @version 2016-11-15
 */
@MyBatisDao
public interface BizTaskPackageTemplatCheckNodeRelDao extends CrudDao2<BizTaskPackageTemplatCheckNodeRel> {

	void updateStatus(Integer id, String status);
	
}