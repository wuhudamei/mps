/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizSupplier;

/**
 * 供应商管理DAO接口
 * 
 * @author lc
 * @version 2016-09-08
 */
@MyBatisDao
public interface BizSupplierDao extends CrudDao<BizSupplier> {

	public List<BizSupplier> findListByPhone(String phone);

	public List<BizSupplier> queryajaxgetSupplier(BizSupplier bizSupplier);
}