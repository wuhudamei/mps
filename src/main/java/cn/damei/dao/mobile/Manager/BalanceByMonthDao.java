package cn.damei.dao.mobile.Manager;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.balanceDetail;

@MyBatisDao
public interface BalanceByMonthDao {

	
	public List<balanceDetail> getBalanceDetailByMonth(String month,Integer managerId);
	
	public List<balanceDetail> getBalanceDetailByParam(Map<String,Object> param);
	
	public double queryTotalMoneyByParam(Map<String,Object> param);
	
	public List<balanceDetail> querySettleBillByParam(Map<String,Object> param);
}
