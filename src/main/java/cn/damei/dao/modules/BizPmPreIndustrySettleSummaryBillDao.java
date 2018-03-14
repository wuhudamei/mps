package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmPreIndustrySettleBill;
import cn.damei.entity.modules.BizPmPreIndustrySettleSummaryBill;


@MyBatisDao
public interface BizPmPreIndustrySettleSummaryBillDao extends CrudDao2<BizPmPreIndustrySettleSummaryBill>{

	public BizPmPreIndustrySettleBill queryPmPreIndustrySettleByParam(Map<String,Object> param);

	public List<BizPmPreIndustrySettleSummaryBill> findListByOrder(BizPmPreIndustrySettleSummaryBill bizPmPreIndustrySettleSummaryBill);
}
