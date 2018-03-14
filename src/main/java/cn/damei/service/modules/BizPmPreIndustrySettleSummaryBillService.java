package cn.damei.service.modules;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizPmPreIndustrySettleBill;
import cn.damei.dao.modules.BizPmPreIndustrySettleSummaryBillDao;
import cn.damei.entity.modules.BizPmPreIndustrySettleSummaryBill;


@Service
@Transactional(readOnly = true)
public class BizPmPreIndustrySettleSummaryBillService extends CrudService2<BizPmPreIndustrySettleSummaryBillDao, BizPmPreIndustrySettleSummaryBill>{

	public Page<BizPmPreIndustrySettleSummaryBill> findPage(Page<BizPmPreIndustrySettleSummaryBill> page,
			BizPmPreIndustrySettleSummaryBill bizPmPreIndustrySettleSummaryBill) {
		return super.findPage(page, bizPmPreIndustrySettleSummaryBill);
	}
	
	public BizPmPreIndustrySettleBill queryPmPreIndustrySettleByParam(Map<String,Object> param){
		return dao.queryPmPreIndustrySettleByParam(param);
	}

	public  List<BizPmPreIndustrySettleSummaryBill> findListByOrder(BizPmPreIndustrySettleSummaryBill bizPmPreIndustrySettleSummaryBill){
		return dao.findListByOrder(bizPmPreIndustrySettleSummaryBill);
	}

	public Page<BizPmPreIndustrySettleSummaryBill> findListByOrder(Page<BizPmPreIndustrySettleSummaryBill> page,
																   BizPmPreIndustrySettleSummaryBill bizPmPreIndustrySettleSummaryBill){
		bizPmPreIndustrySettleSummaryBill.setPage(page);
		page.setList(dao.findListByOrder(bizPmPreIndustrySettleSummaryBill));
		return page;
	}
}
