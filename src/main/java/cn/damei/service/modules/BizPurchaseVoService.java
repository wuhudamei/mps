package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizPurchaseVoDao;
import cn.damei.entity.modules.BizPurchaseVo;

@Service
@Transactional(readOnly = true)
public class BizPurchaseVoService extends CrudService2<BizPurchaseVoDao,BizPurchaseVo>{
	
	public BizPurchaseVo get(Integer id) {
		return super.get(id);
	}
	
	public List<BizPurchaseVo> findList(BizPurchaseVo bizPurchaseVo) {
		return super.findList(bizPurchaseVo);
	}
	
	public Page<BizPurchaseVo> findPage(Page<BizPurchaseVo> page, BizPurchaseVo bizPurchaseVo) {
		return super.findPage(page, bizPurchaseVo);
	}

	public BizPurchaseVo findById(Integer id) {
//		return dao.findById(id);
		return dao.get(id);
	}

	@Transactional(readOnly = false)
	public void updateStatusById(Integer id) {
		dao.updateStatusById(id);
	}

	public List<BizPurchaseVo> findList1(Integer applyemployeeId,String type) {
		// TODO Auto-generated method stub
		return dao.findList1(applyemployeeId,type);
	}

	/**
	 * 供应商列表
	 * @param phone
	 * @param purchaseType6
	 * @param purchaseStatusYes1
	 * @param purchaseDelFlagNo0
	 * @return
	 */
	public List<BizPurchaseVo> findSupplierList(String phone, String purchaseType6, String purchaseStatusYes1,
			String purchaseDelFlagNo0) {
		
		BizPurchaseVo bizPurchaseVo = new BizPurchaseVo();
		bizPurchaseVo.setPurchaseType(purchaseType6);
		bizPurchaseVo.setSupplierContactsPhone(phone);
		bizPurchaseVo.setDelFlag(purchaseDelFlagNo0);
		bizPurchaseVo.setStatus(purchaseStatusYes1);
		
		return dao.findSupplierList(bizPurchaseVo);
	}
	
}
