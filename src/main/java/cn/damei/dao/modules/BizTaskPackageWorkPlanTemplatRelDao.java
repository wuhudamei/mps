
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizTaskPackageWorkPlanTemplatRel;


@MyBatisDao
public interface BizTaskPackageWorkPlanTemplatRelDao extends CrudDao<BizTaskPackageWorkPlanTemplatRel> {

	BizTaskPackageWorkPlanTemplatRel getByStroeIdAndDelflag(String storeId, String houseIsNew,String templatNumber,String projectMode);
	
}