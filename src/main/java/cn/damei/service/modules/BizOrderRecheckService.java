package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderRecheckDao;
import cn.damei.entity.modules.BizOrderRecheck;

/**
 * 上传复尺Service
 * @author llp
 * @version 2016-11-21
 */
@Service
@Transactional(readOnly = true)
public class BizOrderRecheckService extends CrudService2<BizOrderRecheckDao, BizOrderRecheck> {
	
	@Autowired
	private BizOrderRecheckDao bizOrderRecheckDao;
	
	public BizOrderRecheck get(Integer id) {
		return super.get(id);
	}

	public List<BizOrderRecheck> findList(BizOrderRecheck bizOrderRecheck) {
		return super.findList(bizOrderRecheck);
	}
}
