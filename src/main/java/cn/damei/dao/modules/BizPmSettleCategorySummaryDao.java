
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmSettleBill;
import cn.damei.entity.modules.BizPmSettleCategorySummary;

import java.util.List;
import java.util.Map;


@MyBatisDao
public interface BizPmSettleCategorySummaryDao extends CrudDao2<BizPmSettleCategorySummary> {


    public void insertBatch(List<BizPmSettleCategorySummary> list);


    public List<BizPmSettleBill> queryCateGorySummaryMidByCondition(Map<String, Object> map);


    public List<BizPmSettleBill> queryCateGorySummaryLastByCondition(Map<String, Object> map);

    public List<BizPmSettleCategorySummary> queryByCondition(Map<String, Object> map);


    public void updateBatch(List<BizPmSettleCategorySummary> list);


    public void updateRelate(Map<String, Object> map);

    public void updateStatus(BizPmSettleBill bill);

    public List<BizPmSettleBill> queryCateGorySummaryMidByConditionPbc(Map<String, Object> map);

    public List<BizPmSettleBill> queryCateGorySummaryLastByConditionPbc(Map<String, Object> map);
    
    public void updateCateGorySummaryPmSettleBillId(Map<String,Object> param);
    
    public BizPmSettleCategorySummary queryCateGrrySummaryByParam(Map<String,Object> param);
}