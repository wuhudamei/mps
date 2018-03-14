
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizMaterialsChoiceBillItem;
import cn.damei.entity.modules.BizMaterialsChoiceCategory;
import cn.damei.dao.modules.BizMaterialsChoiceBillItemDao;


@Service
@Transactional(readOnly = true)
public class BizMaterialsChoiceBillItemService extends CrudService2<BizMaterialsChoiceBillItemDao, BizMaterialsChoiceBillItem> {

	public BizMaterialsChoiceBillItem get(Integer id) {
		return super.get(id);
	}
	
	public List<BizMaterialsChoiceBillItem> findList(BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem) {
		return super.findList(bizMaterialsChoiceBillItem);
	}
	
	public Page<BizMaterialsChoiceBillItem> findPage(Page<BizMaterialsChoiceBillItem> page, BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem) {
		return super.findPage(page, bizMaterialsChoiceBillItem);
	}
	
	@Transactional(readOnly = false)
	public void save(BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem) {
		super.save(bizMaterialsChoiceBillItem);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem) {
		super.delete(bizMaterialsChoiceBillItem);
	}


	public Page<BizMaterialsChoiceBillItem> findMaterialsPage(Page<BizMaterialsChoiceBillItem> page,
			BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem) {
		bizMaterialsChoiceBillItem.setPage(page);
		page.setList(dao.findMaterialsList(bizMaterialsChoiceBillItem));
		return page;
	}


	public List<BizMaterialsChoiceCategory> findFirstMaterialsChoiceCategory(
			BizMaterialsChoiceCategory bizMaterialsChoiceCategory) {
		return dao.findFirstMaterialsChoiceCategory(bizMaterialsChoiceCategory);
	}
	

	public List<BizMaterialsChoiceCategory> findSecondMaterialsChoiceCategory(
			BizMaterialsChoiceCategory bizMaterialsChoiceCategory) {
		return dao.findSecondMaterialsChoiceCategory(bizMaterialsChoiceCategory);
	}
	
}