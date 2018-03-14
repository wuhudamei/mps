
package cn.damei.service.modules;

import java.util.List;

import cn.damei.entity.modules.BizOrderDistributeLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderDistributeLogDao;


@Service
@Transactional(readOnly = true)
public class BizOrderDistributeLogService extends CrudService2<BizOrderDistributeLogDao, BizOrderDistributeLog> {

	public BizOrderDistributeLog get(Integer id) {
		return super.get(id);
	}
	
	public List<BizOrderDistributeLog> findList(BizOrderDistributeLog bizOrderDistributeLog) {
		return super.findList(bizOrderDistributeLog);
	}
	
	public Page<BizOrderDistributeLog> findPage(Page<BizOrderDistributeLog> page, BizOrderDistributeLog bizOrderDistributeLog) {
		return super.findPage(page, bizOrderDistributeLog);
	}
	
	@Transactional(readOnly = false)
	public void save(BizOrderDistributeLog bizOrderDistributeLog) {
		super.save(bizOrderDistributeLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizOrderDistributeLog bizOrderDistributeLog) {
		super.delete(bizOrderDistributeLog);
	}
	
	@Transactional(readOnly = false)
	public void insert(BizOrderDistributeLog log) {
		dao.insert(log);
	}
	
	public List<BizOrderDistributeLog> queryOrderPmDistributeLogByOrderId(Integer orderId){
		return dao.queryOrderPmDistributeLogByOrderId(orderId);
	}
	
}