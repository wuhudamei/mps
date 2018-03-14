package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizRecheckScaleBillPushPullDoorDao;
import cn.damei.entity.modules.BizRecheckScaleBillPushPullDoor;


@Service
@Transactional(readOnly = true)
public class BizRecheckScaleBillPushPullDoorService
		extends CrudService2<BizRecheckScaleBillPushPullDoorDao, BizRecheckScaleBillPushPullDoor> {

	@Autowired
	private BizRecheckScaleBillPushPullDoorDao bizRecheckScaleBillPushpullDoorDao;

	public List<BizRecheckScaleBillPushPullDoor> getByRecheckID(Integer recheckIDs) {
		return bizRecheckScaleBillPushpullDoorDao.getByRecheckID(recheckIDs);
	}

}
