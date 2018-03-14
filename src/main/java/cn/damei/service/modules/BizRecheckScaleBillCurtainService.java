package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizRecheckScaleBillCurtainDao;
import cn.damei.entity.modules.BizRecheckScaleBillCurtain;


@Service
@Transactional(readOnly = true)
public class BizRecheckScaleBillCurtainService extends CrudService2<BizRecheckScaleBillCurtainDao,BizRecheckScaleBillCurtain>{

	@Autowired
	private BizRecheckScaleBillCurtainDao bizRecheckScaleBillCurtainDao;


	public List<BizRecheckScaleBillCurtain> getByRecheckID(Integer recheckIDs) {

		return bizRecheckScaleBillCurtainDao.getByRecheckID(recheckIDs);
	}
	

}
