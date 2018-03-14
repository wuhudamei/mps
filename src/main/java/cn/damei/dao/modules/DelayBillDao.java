package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizNodePlan;
import cn.damei.entity.modules.DelayBill;

import java.util.List;

@MyBatisDao
public interface DelayBillDao extends CrudDao<DelayBill>{

    void delayBillInvalid(DelayBill delayBill);

    DelayBill findDelayBillDetailById(DelayBill delayBill);

    List<BizNodePlan> findNodePlan(DelayBill delayBillDetail);

    void updataCheckTime(BizNodePlan nodePlan);
}
