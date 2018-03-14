package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizCompletedAuditDao;
import cn.damei.entity.modules.BizCompletedAudit;


@Service
@Transactional(readOnly = true)
public class BizCompletedAuditService extends CrudService2<BizCompletedAuditDao, BizCompletedAudit>{
	
	@Autowired
	private BizCompletedAuditDao bizCompletedAuditDao;
	
	public BizCompletedAudit get(Integer id) {
		return super.get(id);
	}
	
	public List<BizCompletedAudit> findList(BizCompletedAudit bizCompletedAudit) {
		return super.findList(bizCompletedAudit);
	}
	
	public Page<BizCompletedAudit> findPage(Page<BizCompletedAudit> page, BizCompletedAudit bizCompletedAudit) {
		return super.findPage(page, bizCompletedAudit);
	}


	@Transactional(readOnly = false)
	public String updateOrderStatus(String orderstatus, String orderstatusRemark, Integer id) {
		return bizCompletedAuditDao.updateOrderStatus(orderstatus,orderstatusRemark,id) ? "0" : "2";
	}

	public int checkOrderTaskpackByOrderId(int orderId){
		return bizCompletedAuditDao.checkOrderTaskpackByOrderId(orderId);
	}

}
