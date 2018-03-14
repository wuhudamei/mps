
package cn.damei.dao.modules;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizCustomerReturnVisitInvalidRecord;


@MyBatisDao
public interface BizCustomerReturnVisitInvalidRecordDao extends CrudDao2<BizCustomerReturnVisitInvalidRecord> {
	
	
	void insertInvalidRecord(BizCustomerReturnVisitInvalidRecord returnVisitInvalidRecord);
	

	List<BizCustomerReturnVisitInvalidRecord> findByOrderIdAndVisitNode(@Param("orderId")Integer orderId,@Param("returnVisitNode")String returnVisitNode);
	
}