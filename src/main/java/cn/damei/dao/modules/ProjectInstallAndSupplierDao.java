/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ProjectInstallAndSupplier;

/**
 * 主材安装供应商设置DAO接口
 * 
 * @author ztw
 * @version 2017-07-14
 */
@MyBatisDao
public interface ProjectInstallAndSupplierDao extends CrudDao<ProjectInstallAndSupplier> {

	List<ProjectInstallAndSupplier> findPageList(ProjectInstallAndSupplier projectInstallAndSupplier);

	List<ProjectInstallAndSupplier> querySupplierId(ProjectInstallAndSupplier projectInstallAndSupplier2);

	void deleteUpdate(ProjectInstallAndSupplier projectInstallAndSupplier);

}