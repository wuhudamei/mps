
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ProjectInstallAndSupplier;


@MyBatisDao
public interface ProjectInstallAndSupplierDao extends CrudDao<ProjectInstallAndSupplier> {

	List<ProjectInstallAndSupplier> findPageList(ProjectInstallAndSupplier projectInstallAndSupplier);

	List<ProjectInstallAndSupplier> querySupplierId(ProjectInstallAndSupplier projectInstallAndSupplier2);

	void deleteUpdate(ProjectInstallAndSupplier projectInstallAndSupplier);

}