
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizMaterialsChoiceChangeBill;
import cn.damei.dao.modules.BizMaterialsChoiceChangeBillDao;


@Service
@Transactional(readOnly = true)
public class BizMaterialsChoiceChangeBillService extends CrudService2<BizMaterialsChoiceChangeBillDao, BizMaterialsChoiceChangeBill> {

	public BizMaterialsChoiceChangeBill get(Integer id) {
		return super.get(id);
	}
	
	public List<BizMaterialsChoiceChangeBill> findList(BizMaterialsChoiceChangeBill bizMaterialsChoiceChangeBill) {
		return super.findList(bizMaterialsChoiceChangeBill);
	}
	
	public Page<BizMaterialsChoiceChangeBill> findPage(Page<BizMaterialsChoiceChangeBill> page, BizMaterialsChoiceChangeBill bizMaterialsChoiceChangeBill) {
		return super.findPage(page, bizMaterialsChoiceChangeBill);
	}
	
	@Transactional(readOnly = false)
	public void save(BizMaterialsChoiceChangeBill bizMaterialsChoiceChangeBill) {
		super.save(bizMaterialsChoiceChangeBill);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizMaterialsChoiceChangeBill bizMaterialsChoiceChangeBill) {
		super.delete(bizMaterialsChoiceChangeBill);
	}


	public List<BizMaterialsChoiceChangeBill> findChangeBillMessage(String orderNumber) {
		return dao.findChangeBillMessage(orderNumber);
	}
	
}