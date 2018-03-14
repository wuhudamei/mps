package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizGuaranteeMoneyPaidUsed;

@MyBatisDao
public interface BizGuaranteeMoneyPaidUsedDao extends CrudDao2<BizGuaranteeMoneyPaidUsed>{
	
	public BizGuaranteeMoneyPaidUsed findNewestBizGuaranteeMoneyPaidUsed(BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed);
	
	public List<BizGuaranteeMoneyPaidUsed> findManagerGuaranteeMoneyAmountPaidSettle (BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed);

	public List<BizGuaranteeMoneyPaidUsed> findWorkerGuaranteeMoneyAmountPaidSettle(BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed);
}
