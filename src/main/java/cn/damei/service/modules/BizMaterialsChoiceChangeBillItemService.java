
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizMaterialsChoiceChangeBillItemDao;
import cn.damei.entity.modules.BizMaterialsChoiceChangeBillItem;
	

@Service
@Transactional(readOnly = true)
public class BizMaterialsChoiceChangeBillItemService extends CrudService2<BizMaterialsChoiceChangeBillItemDao, BizMaterialsChoiceChangeBillItem> {

	public BizMaterialsChoiceChangeBillItem get(Integer id) {
		return super.get(id);
	}
	
	public List<BizMaterialsChoiceChangeBillItem> findList(BizMaterialsChoiceChangeBillItem bizMaterialsChoiceChangeBillItem) {
		return super.findList(bizMaterialsChoiceChangeBillItem);
	}
	
	public Page<BizMaterialsChoiceChangeBillItem> findPage(Page<BizMaterialsChoiceChangeBillItem> page, BizMaterialsChoiceChangeBillItem bizMaterialsChoiceChangeBillItem) {
		return super.findPage(page, bizMaterialsChoiceChangeBillItem);
	}
	
	@Transactional(readOnly = false)
	public void save(BizMaterialsChoiceChangeBillItem bizMaterialsChoiceChangeBillItem) {
		super.save(bizMaterialsChoiceChangeBillItem);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizMaterialsChoiceChangeBillItem bizMaterialsChoiceChangeBillItem) {
		super.delete(bizMaterialsChoiceChangeBillItem);
	}
	
}