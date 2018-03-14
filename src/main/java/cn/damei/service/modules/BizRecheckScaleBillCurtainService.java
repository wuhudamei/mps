package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizRecheckScaleBillCurtainDao;
import cn.damei.entity.modules.BizRecheckScaleBillCurtain;

/**
 * 上报复尺(20161107-20161113)
 * @author llp
 * 2016-11-15
 */
@Service
@Transactional(readOnly = true)
public class BizRecheckScaleBillCurtainService extends CrudService2<BizRecheckScaleBillCurtainDao,BizRecheckScaleBillCurtain>{

	@Autowired
	private BizRecheckScaleBillCurtainDao bizRecheckScaleBillCurtainDao;

	//根据订单编号查询该订单复尺的所有内容
	public List<BizRecheckScaleBillCurtain> getByRecheckID(Integer recheckIDs) {
		// TODO Auto-generated method stub
		return bizRecheckScaleBillCurtainDao.getByRecheckID(recheckIDs);
	}
	

}
