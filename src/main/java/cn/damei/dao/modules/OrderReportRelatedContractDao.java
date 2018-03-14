/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;


import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.OrderReportRelatedContract;

import java.util.List;
import java.util.Map;

/**
 * 返单关联合同信息DAO接口
 * @author mh
 * @version 2017-05-08
 */
@MyBatisDao
public interface OrderReportRelatedContractDao extends CrudDao<OrderReportRelatedContract> {



    List<OrderReportRelatedContract> findOrderInfoByReportId(String reportId);
    void deleteRelatedInfoByOrderId(Map<String,Object> orderIdMap);
    void deleteRelatedInfoByOrderId2(OrderReportRelatedContract orderReportRelatedContract);
	void updateOrderReportStatusById(String reportId);


}