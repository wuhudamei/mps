
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizTaskPackageAuxiliaryMaterials;


@MyBatisDao
public interface BizTaskPackageAuxiliaryMaterialsDao extends CrudDao<BizTaskPackageAuxiliaryMaterials> {
	
	public List<BizTaskPackageAuxiliaryMaterials> checkTaskPackageByTemplateId(Integer templateId);
	
}