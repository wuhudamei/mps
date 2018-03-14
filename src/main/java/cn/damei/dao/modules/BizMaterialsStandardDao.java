
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.BizMaterialsStandard;


@MyBatisDao
public interface BizMaterialsStandardDao extends CrudDao2<BizMaterialsStandard> {

	List<DropModel> findMaterialsByStroeId(String storeId);

	List<BizMaterialsStandard> queryMaterialsByStoreId(String storeId);
	
}