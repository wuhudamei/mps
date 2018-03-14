/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizProcedureWorkerCrossReferences;

/**
 * 工序和工人星级对照DAO接口
 * @author chy
 * @version 2016-09-17
 */
@MyBatisDao
public interface BizProcedureWorkerCrossReferencesDao extends CrudDao<BizProcedureWorkerCrossReferences> {
	
}