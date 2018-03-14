package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizRecheckScaleBillFlatOpenDoorDao;
import cn.damei.entity.modules.BizRecheckScaleBillFlatOpenDoor;


@Service
@Transactional(readOnly = true)
public class BizRecheckScaleBillFlatOpenDoorService
		extends CrudService2<BizRecheckScaleBillFlatOpenDoorDao, BizRecheckScaleBillFlatOpenDoor> {

	@Autowired
	private BizRecheckScaleBillFlatOpenDoorDao bizRecheckScaleBillFlatOpenDoorDao;

	public List<BizRecheckScaleBillFlatOpenDoor> getByRecheckID(Integer recheckIDs) {
		return bizRecheckScaleBillFlatOpenDoorDao.getByRecheckID(recheckIDs);
	}

}
