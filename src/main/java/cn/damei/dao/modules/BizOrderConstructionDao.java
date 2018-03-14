/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderConstruction;

/**
 * 施工中项目查询
 * @author wyb
 * @version 2016-09-08
 */
@MyBatisDao
public interface BizOrderConstructionDao extends CrudDao2<BizOrderConstruction>{
	
}