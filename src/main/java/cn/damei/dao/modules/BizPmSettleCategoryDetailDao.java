
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmSettleBill;
import cn.damei.entity.modules.BizPmSettleCategoryDetail;

import java.util.List;
import java.util.Map;


@MyBatisDao
public interface BizPmSettleCategoryDetailDao extends CrudDao2<BizPmSettleCategoryDetail> {


    public List<BizPmSettleCategoryDetail> queryPmSettleCategoryDetailByCondition(Map<String, Object> map);


    public void updateBatch(List<BizPmSettleCategoryDetail> list);


    public List<BizPmSettleCategoryDetail> queryCateGoryAmountByCondition(Map<String, Object> map);


    public void updateRelateSummary(Map<String, Object> map);

    public void updateRelateSummaryCategory(Map<String, Object> map);

    public void updateStatus(BizPmSettleBill bill);


	public void updateStatusByOrderId(Map<String, Object> map);


	public List<BizPmSettleCategoryDetail> findByOrderId(Integer orderId, String pmSettleCategory2);

	public Double findMoneyByemployeeId(Integer pmEmployeeId, String pmSettleCategory6);

    public void updateRelateSummaryPbc(Map<String, Object> map);
    
    public void updateStatusByParam(Map<String,Object> param);
    
    public BizPmSettleCategoryDetail querySettleCategoryDetailByPmSettleBillId(Integer pmSettleBillId);
    
    public void insertBatch(List<BizPmSettleCategoryDetail> list);
    
    public BizPmSettleCategoryDetail querySettleCategoryDetailByParam(Map<String,Object> param);
}