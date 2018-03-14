/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizTaskPackageWorkPlanTemplatRel;

/**
 * 任务包派工计划模板DAO接口
 * @author chy
 * @version 2016-09-03
 */
@MyBatisDao
public interface BizTaskPackageWorkPlanTemplatRelDao extends CrudDao<BizTaskPackageWorkPlanTemplatRel> {

	BizTaskPackageWorkPlanTemplatRel getByStroeIdAndDelflag(String storeId, String houseIsNew,String templatNumber,String projectMode);
	
}