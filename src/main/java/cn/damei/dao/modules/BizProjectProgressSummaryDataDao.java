package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.Order2;
import cn.damei.entity.modules.BizProjectProgressSummaryData;

import java.util.List;


@MyBatisDao
public interface BizProjectProgressSummaryDataDao extends CrudDao2<BizProjectProgressSummaryData> {

    List<BizProjectProgressSummaryData> findList(BizProjectProgressSummaryData bizProjectProgressSummaryData);

    public int queryCountByOrderId(Integer orderId);

    public List<Order2> queryOrderByCondition2();

    public Order2 queryOrderById(Integer orderId);


}
