
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialsChoiceChangeBill;


@MyBatisDao
public interface BizMaterialsChoiceChangeBillDao extends CrudDao2<BizMaterialsChoiceChangeBill> {


	void deleteMaterialsChoiceChangeBill(Integer id);


	List<BizMaterialsChoiceChangeBill> findChangeBillMessage(String orderNumber);
	
}