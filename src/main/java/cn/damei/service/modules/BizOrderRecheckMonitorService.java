package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderRecheckMonitorDao;
import cn.damei.entity.modules.BizOrderRecheckMonitor;

/**
 * 复尺未转单监控(20161114-20161120)
 * @author llp
 * @version 2016-11-21
 */
@Service
@Transactional(readOnly = true)
public class BizOrderRecheckMonitorService extends CrudService2<BizOrderRecheckMonitorDao, BizOrderRecheckMonitor> {
	
	@Autowired
	private BizOrderRecheckMonitorDao bizOrderRecheckMonitorDao;
	
	public BizOrderRecheckMonitor get(Integer id) {
		return super.get(id);
	}

	public List<BizOrderRecheckMonitor> findList(BizOrderRecheckMonitor bizOrderRecheckMonitor) {
		return super.findList(bizOrderRecheckMonitor);
	}
}
