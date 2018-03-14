/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizQcManager;

/**
 * 质检管理人员DAO接口
 * @author wyb
 * @version 2016-11-02
 */
@MyBatisDao
public interface BizQcManagerDao extends CrudDao2<BizQcManager> {

	//通过人员id查询门店
	Integer findStore(Integer managerEmployeeId);
	
}