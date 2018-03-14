/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizSeiralnum;

/**
 * 编号序列管理DAO接口
 * @author qww
 * @version 2016-08-21
 */
@MyBatisDao
public interface BizSeiralnumDao extends CrudDao2<BizSeiralnum> {
	
	public BizSeiralnum querySeiralnumByType(String bussinessType);

}