/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizPurchase;
import cn.damei.dao.modules.BizPurchaseDao;

/**
 * 采购单Service
 * @author 汪文文
 * @version 2016-09-28
 */
@Service
@Transactional(readOnly = true)
public class BizPurchaseService extends CrudService2<BizPurchaseDao, BizPurchase> {

	public BizPurchase get(Integer id) {
		return super.get(id);
	}
	
	public List<BizPurchase> findList(BizPurchase bizPurchase) {
		return super.findList(bizPurchase);
	}
	
	public Page<BizPurchase> findPage(Page<BizPurchase> page, BizPurchase bizPurchase) {
		return super.findPage(page, bizPurchase);
	}
	
	@Transactional(readOnly = false)
	public void save(BizPurchase bizPurchase) {
		super.save(bizPurchase);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizPurchase bizPurchase) {
		super.delete(bizPurchase);
	}
	@Transactional(readOnly = false)
	public void updateStatusById(Integer id) {
		dao.updateStatusById(id);
		
	}

	public BizPurchase findByPurchaseId(Integer id) {
		return dao.get(id);
	}
	
	@Transactional(readOnly = false)
	public void updateStatus(BizPurchase bizPurchase) {
		dao.update(bizPurchase);
	}

	/**
	 * 开关面板采购单废弃
	 * @param purchaseId
	 * @param status
	 * @param urgeReply
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean updateMainPanelStatus(String purchaseId, String status, String urgeReply) {
		
		BizPurchase bizPurchase = new BizPurchase();
		bizPurchase.setId(Integer.valueOf(purchaseId));
		bizPurchase.setStatus(status);
		bizPurchase.setStatusDescribe(urgeReply);
		
		return (dao.updateMainPanelStatus(bizPurchase)?true:false);
		
		
	}

	/**
	 * 判断项目经理的中期结算单是否已存在
	 * @param valueOf
	 * @return
	 */
	public Integer findSettlementIsExist(Integer orderId) {
		return dao.findSettlementIsExist(orderId);
	}

	
	
}