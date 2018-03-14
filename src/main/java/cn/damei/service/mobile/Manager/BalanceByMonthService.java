package cn.damei.service.mobile.Manager;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.dao.mobile.Manager.BalanceByMonthDao;
import cn.damei.entity.mobile.Manager.balanceDetail;

@Service
@Transactional(readOnly = true)
public class BalanceByMonthService {

	@Autowired
	private BalanceByMonthDao dao;

	public List<balanceDetail> getBalanceDetailByMonth(String month, Integer managerId) {

		return dao.getBalanceDetailByMonth(month, managerId);
	}

	public List<balanceDetail> getBalanceDetailByParam(Map<String, Object> param){
		return dao.getBalanceDetailByParam(param);
	}

	public double queryTotalMoneyByParam(Map<String, Object> param){
		return dao.queryTotalMoneyByParam(param);
	}
	
	public List<balanceDetail> querySettleBillByParam(Map<String,Object> param){
		return dao.querySettleBillByParam(param);
	}
}
