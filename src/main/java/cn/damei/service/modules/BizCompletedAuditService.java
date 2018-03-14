package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizCompletedAuditDao;
import cn.damei.entity.modules.BizCompletedAudit;

/**
 * 订单交底
 * 确认竣工
 * @author llp
 */
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

	/**
	 * 修改订单状态340结算员审核通过
	 * @param orderstatus340Value
	 * @param orderstatus340ValueRemark
	 * @param valueOf
	 * @return
	 */
	@Transactional(readOnly = false)
	public String updateOrderStatus(String orderstatus, String orderstatusRemark, Integer id) {
		return bizCompletedAuditDao.updateOrderStatus(orderstatus,orderstatusRemark,id) ? "0" : "2";
	}
	/**
	 * 检查订单下的预算金额大于0,状态不等于160的任务包
	 * @param orderId
	 * @return
	 */
	public int checkOrderTaskpackByOrderId(int orderId){
		return bizCompletedAuditDao.checkOrderTaskpackByOrderId(orderId);
	}

}
