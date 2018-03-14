
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizTaskPackageTemplatCheckNodeRel;


@MyBatisDao
public interface BizTaskPackageTemplatCheckNodeRelDao extends CrudDao2<BizTaskPackageTemplatCheckNodeRel> {

	void updateStatus(Integer id, String status);
	
}