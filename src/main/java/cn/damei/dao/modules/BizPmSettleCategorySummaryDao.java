/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmSettleBill;
import cn.damei.entity.modules.BizPmSettleCategorySummary;

import java.util.List;
import java.util.Map;

/**
 * 结算类目汇总DAO接口
 * @author qww
 * @version 2016-12-26
 */
@MyBatisDao
public interface BizPmSettleCategorySummaryDao extends CrudDao2<BizPmSettleCategorySummary> {

    /**
     * 批量新增
     * @param list
     */
    public void insertBatch(List<BizPmSettleCategorySummary> list);

    /**
     * 查询中期结算类目
     * @param map
     * @return
     */
    public List<BizPmSettleBill> queryCateGorySummaryMidByCondition(Map<String, Object> map);

    /**
     * 查询尾期结算类目
     * @param map
     * @return
     */
    public List<BizPmSettleBill> queryCateGorySummaryLastByCondition(Map<String, Object> map);

    public List<BizPmSettleCategorySummary> queryByCondition(Map<String, Object> map);

    /**
     * 批量更新
     * @param list
     */
    public void updateBatch(List<BizPmSettleCategorySummary> list);

    /**
     * 批量关联更新
     * @param map
     */
    public void updateRelate(Map<String, Object> map);

    public void updateStatus(BizPmSettleBill bill);

    public List<BizPmSettleBill> queryCateGorySummaryMidByConditionPbc(Map<String, Object> map);

    public List<BizPmSettleBill> queryCateGorySummaryLastByConditionPbc(Map<String, Object> map);
    
    public void updateCateGorySummaryPmSettleBillId(Map<String,Object> param);
    
    public BizPmSettleCategorySummary queryCateGrrySummaryByParam(Map<String,Object> param);
}