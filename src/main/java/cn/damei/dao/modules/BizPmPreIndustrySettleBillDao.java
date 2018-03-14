package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmPreIndustrySettleBill;
import cn.damei.entity.modules.BizPmPreIndustrySettleSummaryBill;

/**
 * 项目经理准产业结算单Dao
 * @author hyh
 *
 */
@MyBatisDao
public interface BizPmPreIndustrySettleBillDao extends CrudDao2<BizPmPreIndustrySettleBill>{
	
	public int queryCountPmPreIndustrySettleBillByParam(Map<String,Object> param);
	
	public BizPmPreIndustrySettleBill queryPmPreIndustrySettleBillByParam(Map<String,Object> param);
	
	public void updateSettleCategorySummaryStatusByParam(Map<String,Object> param);
	
	public void updateSettleCategoryStatusByParam(Map<String,Object> param);
	
	public int queryCountByParam(Map<String,Object> param);
	
	public int querySummaryBillCountByParam(Map<String,Object> param);
	
	public  List<BizPmPreIndustrySettleSummaryBill> querySettleSummaryBillByParam(Map<String,Object> param);
	
	public void updateBatchSettleBillStatus(Map<String,Object> param);
	
	public void updateBatchCateGorgSummaryStatus(Map<String,Object> param);
	
	public void updateBatchCateGorgStatus(Map<String,Object> param);
	
	public void updateBatchByRelate(List<BizPmPreIndustrySettleBill> list);
	
	public int checkPmPreIndustrySettleBillByParam(Map<String,Object> param);

}
