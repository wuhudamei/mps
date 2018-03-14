package cn.damei.service.mobile.Inspector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.dao.mobile.Inspector.InspectorBalanceOfMonthDao;
import cn.damei.entity.mobile.Inspector.InspectorBalanceEntity;

@Service
@Transactional(readOnly=true)
public class InspectorBalanceOfMonthService {

	@Autowired
	private  InspectorBalanceOfMonthDao dao;
	
	
	
	public List<InspectorBalanceEntity> getBalanceDetailByMonth(String month,Integer inspectId){
		
		return dao.getBalanceDetailByMonth(month, inspectId);
		
	}
}
