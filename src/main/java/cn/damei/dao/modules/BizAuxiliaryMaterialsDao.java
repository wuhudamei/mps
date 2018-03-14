
package cn.damei.dao.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizAuxiliaryMaterials;


@MyBatisDao
public interface BizAuxiliaryMaterialsDao extends CrudDao<BizAuxiliaryMaterials> {
	public List<BizAuxiliaryMaterials> findExport(Date date, String string);
}