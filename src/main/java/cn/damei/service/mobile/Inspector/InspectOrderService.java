package cn.damei.service.mobile.Inspector;

import java.util.List;

import cn.damei.dao.mobile.Inspector.InspectorOrderDao;
import cn.damei.entity.mobile.Inspector.InspectOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly=true)
public class InspectOrderService {

	
	@Autowired
	private InspectorOrderDao dao;
	
	
	public List<InspectOrderVo> findMyOrderInfoByInspectorId(InspectOrderVo  vo){
		
		
		
		return dao.findMyOrderInfoByInspectorId(vo);
	}
}
