package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizRecheckScaleBillRoomCabinetDao;
import cn.damei.entity.modules.BizRecheckScaleBillRoomCabinet;

/**
 * 上报复尺(20161107-20161113)
 * @author llp
 * 2016-11-15
 * 浴室柜复尺
 * BIZ_RECHECK_SCALE_BILL_BATHROOM_CABINET
 */
@Service
@Transactional(readOnly = true)
public class BizRecheckScaleBillRoomCabinetService extends CrudService2<BizRecheckScaleBillRoomCabinetDao,BizRecheckScaleBillRoomCabinet>{

	@Autowired
	private BizRecheckScaleBillRoomCabinetDao bizRecheckScaleBillRoomCabinetDao;

	public List<BizRecheckScaleBillRoomCabinet> getByRecheckID(Integer recheckIDs) {
		return bizRecheckScaleBillRoomCabinetDao.getByRecheckID(recheckIDs);
	}
	
}
