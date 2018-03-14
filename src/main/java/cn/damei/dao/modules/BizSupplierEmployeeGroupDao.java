
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizSupplierEmployeeGroup;


@MyBatisDao
public interface BizSupplierEmployeeGroupDao extends CrudDao<BizSupplierEmployeeGroup> {

	List<BizSupplierEmployeeGroup> findPageallsupp(BizSupplierEmployeeGroup bizSupplierEmployeeGroup);

	List<BizSupplierEmployeeGroup> findListEmployeegroupIds(BizSupplierEmployeeGroup bizSupplierEmployeeGroup);

	List<BizSupplierEmployeeGroup> findBizSupplierEmployeeGroup(BizSupplierEmployeeGroup bizSupplierEmployeeGroup);

	void deleteSupplier(BizSupplierEmployeeGroup bizSupplierEmployeeGroup);

}