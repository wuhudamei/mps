
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizSupplier;


@MyBatisDao
public interface BizSupplierDao extends CrudDao<BizSupplier> {

	public List<BizSupplier> findListByPhone(String phone);

	public List<BizSupplier> queryajaxgetSupplier(BizSupplier bizSupplier);
}