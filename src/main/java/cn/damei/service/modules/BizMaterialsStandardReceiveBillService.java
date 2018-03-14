/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizMaterialsStandardReceiveBillDao;
import cn.damei.entity.modules.BizMaterialsStandardReceiveBill;

/**
 * 标化辅材记录Service
 * @author 汪文文
 * @version 2016-12-26
 */
@Service
@Transactional(readOnly = true)
public class BizMaterialsStandardReceiveBillService extends CrudService2<BizMaterialsStandardReceiveBillDao, BizMaterialsStandardReceiveBill> {

	public BizMaterialsStandardReceiveBill get(Integer id) {
		return super.get(id);
	}
	
	public List<BizMaterialsStandardReceiveBill> findList(BizMaterialsStandardReceiveBill bizMaterialsStandardReceiveBill) {
		return super.findList(bizMaterialsStandardReceiveBill);
	}
	
	public Page<BizMaterialsStandardReceiveBill> findPage(Page<BizMaterialsStandardReceiveBill> page, BizMaterialsStandardReceiveBill bizMaterialsStandardReceiveBill) {
		return super.findPage(page, bizMaterialsStandardReceiveBill);
	}
	
	@Transactional(readOnly = false)
	public void save(BizMaterialsStandardReceiveBill bizMaterialsStandardReceiveBill) {
		super.save(bizMaterialsStandardReceiveBill);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizMaterialsStandardReceiveBill bizMaterialsStandardReceiveBill) {
		super.delete(bizMaterialsStandardReceiveBill);
	}
}