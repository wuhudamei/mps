package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizRecheckScaleBillFlatOpenDoorDao;
import cn.damei.entity.modules.BizRecheckScaleBillFlatOpenDoor;

/**
 * 上报复尺(20161107-20161113)
 * 推拉门
 * @author 
 * llp 
 * 2016-11-15
 */
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
