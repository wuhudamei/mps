package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizGuaranteeMoneyBalance;

@MyBatisDao
public interface BizGuaranteeMoneyBalanceDao extends CrudDao2<BizGuaranteeMoneyBalance>{
	
	public BizGuaranteeMoneyBalance findGuaranteeMoneyBalanceByEmployeeId(Integer employeeId);
	
	public List<BizGuaranteeMoneyBalance> findManagerGuaranteeMoneyBalanceByParam(BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance);

	public List<BizGuaranteeMoneyBalance> findWorkerGuaranteeMoneyBalanceByParam(BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance);
}
