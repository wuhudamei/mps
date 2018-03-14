
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialsChoiceBillItem;
import cn.damei.entity.modules.BizMaterialsChoiceCategory;


@MyBatisDao
public interface BizMaterialsChoiceBillItemDao extends CrudDao2<BizMaterialsChoiceBillItem> {


	void deleteMaterialsChoiceBillItem(Integer id);


	boolean insertMaterialsChoiceBillItemList(List<BizMaterialsChoiceBillItem> list);


	List<BizMaterialsChoiceBillItem> findMaterialsList(BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem);


	List<BizMaterialsChoiceCategory> findFirstMaterialsChoiceCategory(BizMaterialsChoiceCategory bizMaterialsChoiceCategory);

	

	List<BizMaterialsChoiceCategory> findSecondMaterialsChoiceCategory(BizMaterialsChoiceCategory bizMaterialsChoiceCategory);

	
}