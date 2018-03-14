/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmSettleBill;
import cn.damei.entity.modules.BizPmSettleCategoryDetail;

import java.util.List;
import java.util.Map;

/**
 * 结算类目明细DAO接口
 * @author qww
 * @version 2016-12-26
 */
@MyBatisDao
public interface BizPmSettleCategoryDetailDao extends CrudDao2<BizPmSettleCategoryDetail> {

    /**
     * 根据订单id及结算状态查询
     * @param map
     * @return
     */
    public List<BizPmSettleCategoryDetail> queryPmSettleCategoryDetailByCondition(Map<String, Object> map);

    /**
     * 批量更新
     * @param list
     */
    public void updateBatch(List<BizPmSettleCategoryDetail> list);

    /**
     * 根据订单和状态查询类目分别总金额
     * @param map
     * @return
     */
    public List<BizPmSettleCategoryDetail> queryCateGoryAmountByCondition(Map<String, Object> map);

    /**
     * 关联更新
     * @param map
     */
    public void updateRelateSummary(Map<String, Object> map);

    public void updateRelateSummaryCategory(Map<String, Object> map);

    public void updateStatus(BizPmSettleBill bill);

    /**
     * 更新结算明细
     * @param map
     */
	public void updateStatusByOrderId(Map<String, Object> map);

	/**
	 * 查询自主支配明细根据订单id
	 * @param orderId
	 * @param pmSettleCategory2
	 * @return
	 */
	public List<BizPmSettleCategoryDetail> findByOrderId(Integer orderId, String pmSettleCategory2);

	public Double findMoneyByemployeeId(Integer pmEmployeeId, String pmSettleCategory6);

    public void updateRelateSummaryPbc(Map<String, Object> map);
    
    public void updateStatusByParam(Map<String,Object> param);
    
    public BizPmSettleCategoryDetail querySettleCategoryDetailByPmSettleBillId(Integer pmSettleBillId);
    
    public void insertBatch(List<BizPmSettleCategoryDetail> list);
    
    public BizPmSettleCategoryDetail querySettleCategoryDetailByParam(Map<String,Object> param);
}