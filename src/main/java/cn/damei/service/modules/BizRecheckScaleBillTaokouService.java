package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizRecheckScaleBillTaokouDao;
import cn.damei.entity.modules.BizRecheckScaleBillTaokou;


@Service
@Transactional(readOnly = true)
public class BizRecheckScaleBillTaokouService extends CrudService2<BizRecheckScaleBillTaokouDao,BizRecheckScaleBillTaokou>{

	@Autowired
	private BizRecheckScaleBillTaokouDao bizRecheckScaleBillTaokouDao;

	public List<BizRecheckScaleBillTaokou> getByRecheckID(Integer recheckID) {

		return bizRecheckScaleBillTaokouDao.getByRecheckID(recheckID);
	}
	
}
