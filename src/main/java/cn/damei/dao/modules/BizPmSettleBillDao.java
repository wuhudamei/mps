/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialSelfbuyVo;
import cn.damei.entity.modules.BizPmSettleBill;
import cn.damei.entity.modules.InspectorPunish;
import cn.damei.entity.modules.Ownpay;
import cn.damei.entity.modules.BizPmSettleSummaryBill;

/**
 * 结算单DAO接口
 * @author qww
 * @version 2016-12-26
 */
@MyBatisDao
public interface BizPmSettleBillDao extends CrudDao2<BizPmSettleBill> {

    /**
     * 批量新增
     * @param list
     */
    public void insertBatch(List<BizPmSettleBill> list);

    public List<BizPmSettleBill> findPmSettleBillList(BizPmSettleBill bill);

    public Integer queryCountByCondition(Map<String, Object> map);

    public List<BizPmSettleSummaryBill> queryPmSettleBill(Map<String, Object> map);

    public void updateBatchByCondition(BizPmSettleBill bill);

    public List<BizPmSettleBill> findSettleBillList(BizPmSettleBill bill);

    /**
     * 自主支配项
     * @param id
     * @return
     */
	public List<Ownpay> findOwnpayAmount(Integer id);

	/**
	 * 质检罚款明细
	 * @param inspectorPunish
	 * @return
	 */
	public List<InspectorPunish> findInspector(InspectorPunish inspectorPunish);

    public List<BizPmSettleBill> findPmSettleBillListPbc(BizPmSettleBill bill);

    public List<BizPmSettleSummaryBill> queryPmSettleBillPbc(Map<String, Object> map);

    public List<BizPmSettleBill> findSettleBillListPbc(BizPmSettleBill bill);

    public Integer queryBillCountByCondition(Map<String, Object> map);

    /**
     * 批量更新关联字段
     * @param list
     */
    public void updateBatchByRelate(List<BizPmSettleBill> list);
    
    /**
     * 查询订单的自采材料信息
     * @param orderId
     * @return
     */
    public List<BizMaterialSelfbuyVo> querySelfbuyMaterial(Map<String,Object> param);
    
    
    public int queryPmSettleBillByParam(Map<String,Object> param);
    
    
    public List<BizPmSettleBill> findPmCommissionSettle(BizPmSettleBill bill);
    
    public BizPmSettleBill queryPmSettleBillInfoByParam(Map<String,Object> param);
    
    public BizPmSettleBill findPmSettleBillInfoByParam(Map<String,Object> param);
    
}