
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.PurchaseReceiveBill;
import cn.damei.dao.modules.PurchaseReceiveBillDao;


@Service
@Transactional(readOnly = true)
public class PurchaseReceiveBillService extends CrudService2<PurchaseReceiveBillDao, PurchaseReceiveBill> {

	public PurchaseReceiveBill get(Integer id) {
		return super.get(id);
	}
	
	public List<PurchaseReceiveBill> findList(PurchaseReceiveBill purchaseReceiveBill) {
		return super.findList(purchaseReceiveBill);
	}
	
	public Page<PurchaseReceiveBill> findPage(Page<PurchaseReceiveBill> page, PurchaseReceiveBill purchaseReceiveBill) {
		return super.findPage(page, purchaseReceiveBill);
	}
	
	@Transactional(readOnly = false)
	public void save(PurchaseReceiveBill purchaseReceiveBill) {
		super.save(purchaseReceiveBill);
	}
	
	@Transactional(readOnly = false)
	public void delete(PurchaseReceiveBill purchaseReceiveBill) {
		super.delete(purchaseReceiveBill);
	}
	
}