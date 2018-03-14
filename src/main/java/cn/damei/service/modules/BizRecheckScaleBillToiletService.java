package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizRecheckScaleBillToiletDao;
import cn.damei.entity.modules.BizRecheckScaleBillToilet;


@Service
@Transactional(readOnly = true)
public class BizRecheckScaleBillToiletService extends CrudService2<BizRecheckScaleBillToiletDao,BizRecheckScaleBillToilet>{

	@Autowired
	private BizRecheckScaleBillToiletDao bizRecheckScaleBillToiletDao;

	public List<BizRecheckScaleBillToilet> getByRecheckID(Integer recheckIDs) {
		return bizRecheckScaleBillToiletDao.getByRecheckID(recheckIDs);
	}
	
}
