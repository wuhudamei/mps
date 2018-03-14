/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizMaterialsChoiceChangeBill;
import cn.damei.dao.modules.BizMaterialsChoiceChangeBillDao;

/**
 * 选材变更单表Service
 * @author wyb
 * @version 2017-06-14
 */
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

	/**
	 * 根据订单编号查询该订单下所有的变更单
	 * @param orderNumber
	 * @return
	 */
	public List<BizMaterialsChoiceChangeBill> findChangeBillMessage(String orderNumber) {
		return dao.findChangeBillMessage(orderNumber);
	}
	
}