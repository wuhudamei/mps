package cn.damei.service.modules;

import java.util.Map;

import cn.damei.entity.modules.BizOrderChange;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderChangeDao;


@Service
@Transactional(readOnly = true)
public class BizOrderChangeService extends CrudService2<BizOrderChangeDao, BizOrderChange>{

	@Transactional(readOnly = false)
	public void save(BizOrderChange bizOrderChange) {
		super.save(bizOrderChange);
	}
	
	public BizOrderChange queryOrderChangeByParam(Map<String,Object> param){
		return dao.queryOrderChangeByParam(param);
	}
}
