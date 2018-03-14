package cn.damei.dao.mobile.Inspector;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.InspectorBalanceEntity;

@MyBatisDao
public interface InspectorBalanceOfMonthDao {

	
	public List<InspectorBalanceEntity> getBalanceDetailByMonth(String month,Integer inspectId);
}
