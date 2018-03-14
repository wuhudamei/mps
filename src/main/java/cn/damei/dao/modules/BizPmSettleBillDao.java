
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


@MyBatisDao
public interface BizPmSettleBillDao extends CrudDao2<BizPmSettleBill> {


    public void insertBatch(List<BizPmSettleBill> list);

    public List<BizPmSettleBill> findPmSettleBillList(BizPmSettleBill bill);

    public Integer queryCountByCondition(Map<String, Object> map);

    public List<BizPmSettleSummaryBill> queryPmSettleBill(Map<String, Object> map);

    public void updateBatchByCondition(BizPmSettleBill bill);

    public List<BizPmSettleBill> findSettleBillList(BizPmSettleBill bill);


	public List<Ownpay> findOwnpayAmount(Integer id);


	public List<InspectorPunish> findInspector(InspectorPunish inspectorPunish);

    public List<BizPmSettleBill> findPmSettleBillListPbc(BizPmSettleBill bill);

    public List<BizPmSettleSummaryBill> queryPmSettleBillPbc(Map<String, Object> map);

    public List<BizPmSettleBill> findSettleBillListPbc(BizPmSettleBill bill);

    public Integer queryBillCountByCondition(Map<String, Object> map);


    public void updateBatchByRelate(List<BizPmSettleBill> list);
    

    public List<BizMaterialSelfbuyVo> querySelfbuyMaterial(Map<String,Object> param);
    
    
    public int queryPmSettleBillByParam(Map<String,Object> param);
    
    
    public List<BizPmSettleBill> findPmCommissionSettle(BizPmSettleBill bill);
    
    public BizPmSettleBill queryPmSettleBillInfoByParam(Map<String,Object> param);
    
    public BizPmSettleBill findPmSettleBillInfoByParam(Map<String,Object> param);
    
}