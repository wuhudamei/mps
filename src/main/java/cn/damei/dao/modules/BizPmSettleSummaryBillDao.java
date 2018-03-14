
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmSettleSummaryBill;

import java.util.List;


@MyBatisDao
public interface BizPmSettleSummaryBillDao extends CrudDao2<BizPmSettleSummaryBill> {

    public void insertBatch(List<BizPmSettleSummaryBill> list);

    public List<BizPmSettleSummaryBill> findSummaryBillList(BizPmSettleSummaryBill bill);

    public List<BizPmSettleSummaryBill> findSummaryBillListPbc(BizPmSettleSummaryBill bill);
}