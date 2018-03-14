/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizSupplierEmployeeGroup;

/**
 * 主材安装供应商和工人组DAO接口
 * 
 * @author ztw
 * @version 2017-07-17
 */
@MyBatisDao
public interface BizSupplierEmployeeGroupDao extends CrudDao<BizSupplierEmployeeGroup> {

	List<BizSupplierEmployeeGroup> findPageallsupp(BizSupplierEmployeeGroup bizSupplierEmployeeGroup);

	List<BizSupplierEmployeeGroup> findListEmployeegroupIds(BizSupplierEmployeeGroup bizSupplierEmployeeGroup);

	List<BizSupplierEmployeeGroup> findBizSupplierEmployeeGroup(BizSupplierEmployeeGroup bizSupplierEmployeeGroup);

	void deleteSupplier(BizSupplierEmployeeGroup bizSupplierEmployeeGroup);

}