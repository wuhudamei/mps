package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizConfirmCompletedDao;
import cn.damei.entity.modules.BizConfirmCompleted;

/**
 * 订单交底
 * 确认竣工
 * @author llp
 */
@Service
@Transactional(readOnly = true)
public class BizConfirmCompletedService extends CrudService2<BizConfirmCompletedDao, BizConfirmCompleted>{
	
	@Autowired
	private BizConfirmCompletedDao bizConfirmCompletedDao;
	
	public BizConfirmCompleted get(Integer id) {
		return super.get(id);
	}
	
	public List<BizConfirmCompleted> findList(BizConfirmCompleted bizEnginInstall) {
		return super.findList(bizEnginInstall);
	}
	
	public Page<BizConfirmCompleted> findPage(Page<BizConfirmCompleted> page, BizConfirmCompleted bizEnginInstall) {
		return super.findPage(page, bizEnginInstall);
	}

	/**
	 * 订单状态修改为400
	 * @param orderID
	 * @param orderstatus400Value
	 * @param orderstatus400ValueRemark
	 * @return
	 */
	@Transactional(readOnly = false)
	public String updateByOrderStatusOrCompleted( String orderstatus400Value,
			String orderstatus400ValueRemark, Integer orderID) {
		return bizConfirmCompletedDao.updateByOrderStatusOrCompleted(orderstatus400Value,
				orderstatus400ValueRemark, orderID) ? "0" : "2";
	}

	public BizConfirmCompleted getByID(Integer id) {
		// TODO Auto-generated method stub
		return bizConfirmCompletedDao.getByID(id);
	}

}
