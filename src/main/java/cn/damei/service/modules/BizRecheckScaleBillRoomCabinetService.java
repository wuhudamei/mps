package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizRecheckScaleBillRoomCabinetDao;
import cn.damei.entity.modules.BizRecheckScaleBillRoomCabinet;


@Service
@Transactional(readOnly = true)
public class BizRecheckScaleBillRoomCabinetService extends CrudService2<BizRecheckScaleBillRoomCabinetDao,BizRecheckScaleBillRoomCabinet>{

	@Autowired
	private BizRecheckScaleBillRoomCabinetDao bizRecheckScaleBillRoomCabinetDao;

	public List<BizRecheckScaleBillRoomCabinet> getByRecheckID(Integer recheckIDs) {
		return bizRecheckScaleBillRoomCabinetDao.getByRecheckID(recheckIDs);
	}
	
}
