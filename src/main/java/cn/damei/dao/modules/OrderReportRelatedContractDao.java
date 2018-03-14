
package cn.damei.dao.modules;


import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.OrderReportRelatedContract;

import java.util.List;
import java.util.Map;


@MyBatisDao
public interface OrderReportRelatedContractDao extends CrudDao<OrderReportRelatedContract> {



    List<OrderReportRelatedContract> findOrderInfoByReportId(String reportId);
    void deleteRelatedInfoByOrderId(Map<String,Object> orderIdMap);
    void deleteRelatedInfoByOrderId2(OrderReportRelatedContract orderReportRelatedContract);
	void updateOrderReportStatusById(String reportId);


}