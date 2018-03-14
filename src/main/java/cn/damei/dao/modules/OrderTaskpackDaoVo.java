
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.OrderTaskpackVo;


@MyBatisDao
public interface OrderTaskpackDaoVo extends CrudDao<OrderTaskpackVo> {

	List<OrderTaskpackVo> getByOrderIdAndTaskpacksgeId1(String id,String procedureNo);
	
	List<OrderTaskpackVo> getByOrderIdAndTaskpacksgeId(String id);


	List<OrderTaskpackVo> getByOrderIdAndNo(String orderId, String procedureNo);
	
}