package cn.damei.service.modules;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.InspectorDao;
import cn.damei.entity.modules.Inspector;

@Service
@Transactional(readOnly = true)
public class InspectorService extends CrudService2<InspectorDao, Inspector>{

	public List<Inspector> findListForOrder(Inspector inspector) {
		// TODO Auto-generated method stub
		return dao.findListForOrder(inspector);
	}

	public List<Inspector> findlistForOrderTradition(Inspector inspector) {
		// TODO Auto-generated method stub
		return dao.findListForOrderTradition(inspector);
	}

	
}
