package cn.damei.service.modules;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.DateUtils;
import cn.damei.dao.modules.BizRecheckScaleBillDao;
import cn.damei.entity.modules.BizRecheckScaleBill;

/**
 * 上报复尺(20161107-20161113)
 * @author llp
 * 2016-11-15
 */
@Service
@Transactional(readOnly = true)
public class BizRecheckScaleBillService extends CrudService2<BizRecheckScaleBillDao,BizRecheckScaleBill>{

	@Autowired
	private BizRecheckScaleBillDao bizRecheckScaleBillDao;

	@Transactional(readOnly = false)
	public void updateByRecheckStatus(String status, String remarks, Integer recheckID) {
		bizRecheckScaleBillDao.updateByRecheckStatus(status,remarks,DateUtils.addDate(new Date(), 0),recheckID);
	}	

}
