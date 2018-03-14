/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizCustomerReturnVisitInvalidRecord;

/**
 * 无效客户回访记录DAO接口
 * @author 李万财
 * @version 2017-06-27
 */
@MyBatisDao
public interface BizCustomerReturnVisitInvalidRecordDao extends CrudDao2<BizCustomerReturnVisitInvalidRecord> {
	
	
	void insertInvalidRecord(BizCustomerReturnVisitInvalidRecord returnVisitInvalidRecord);
	
	/**
	 * 订单某节点无效回访记录查询
	 * @param BizCustomerReturnVisitInvalidRecord
	 * @return
	 */
	List<BizCustomerReturnVisitInvalidRecord> findByOrderIdAndVisitNode(@Param("orderId")Integer orderId,@Param("returnVisitNode")String returnVisitNode);
	
}