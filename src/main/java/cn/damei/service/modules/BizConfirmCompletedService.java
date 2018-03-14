package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizConfirmCompletedDao;
import cn.damei.entity.modules.BizConfirmCompleted;


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


	@Transactional(readOnly = false)
	public String updateByOrderStatusOrCompleted( String orderstatus400Value,
			String orderstatus400ValueRemark, Integer orderID) {
		return bizConfirmCompletedDao.updateByOrderStatusOrCompleted(orderstatus400Value,
				orderstatus400ValueRemark, orderID) ? "0" : "2";
	}

	public BizConfirmCompleted getByID(Integer id) {

		return bizConfirmCompletedDao.getByID(id);
	}

}
