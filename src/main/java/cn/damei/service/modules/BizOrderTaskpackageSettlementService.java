/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizOrderTaskpackageSettlement;
import cn.damei.dao.modules.BizOrderTaskpackageSettlementDao;

/**
 * 结算单Service
 * @author 汪文文
 * @version 2016-10-14
 */
@Service
@Transactional(readOnly = true)
public class BizOrderTaskpackageSettlementService extends CrudService2<BizOrderTaskpackageSettlementDao, BizOrderTaskpackageSettlement> {

	public BizOrderTaskpackageSettlement get(Integer id) {
		return super.get(id);
	}
	
	public List<BizOrderTaskpackageSettlement> findList(BizOrderTaskpackageSettlement bizOrderTaskpackageSettlement) {
		return super.findList(bizOrderTaskpackageSettlement);
	}
	
	public Page<BizOrderTaskpackageSettlement> findPage(Page<BizOrderTaskpackageSettlement> page, BizOrderTaskpackageSettlement bizOrderTaskpackageSettlement) {
		return super.findPage(page, bizOrderTaskpackageSettlement);
	}
	
	@Transactional(readOnly = false)
	public void save(BizOrderTaskpackageSettlement bizOrderTaskpackageSettlement) {
		super.save(bizOrderTaskpackageSettlement);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizOrderTaskpackageSettlement bizOrderTaskpackageSettlement) {
		super.delete(bizOrderTaskpackageSettlement);
	}

	public BizOrderTaskpackageSettlement findByOrderTaskpackageId(Integer orderTaskpackageId) {
		// TODO Auto-generated method stub
		return dao.findByOrderTaskpackageId(orderTaskpackageId);
	}
	
	@Transactional(readOnly = false)
	public void updateSettlementStatus(Integer taskPackageId, String status) {
		
		dao.updateSettlementStatus(taskPackageId,status);
	}

	
}