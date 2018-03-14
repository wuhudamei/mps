
package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.OrderTaskpackDaoVo;
import cn.damei.entity.modules.OrderTaskpackVo;


@Service
@Transactional(readOnly = true)
public class OrderTaskpackServiceVo extends CrudService<OrderTaskpackDaoVo, OrderTaskpackVo> {

	@Autowired
	private OrderTaskpackDaoVo orderTaskpackDaoVo;
	
	public OrderTaskpackVo get(String id) {
        return super.get(id);
    }
	

	public List<OrderTaskpackVo> getByOrderIdAndTaskpacksgeId1(String id,String procedureNo) {
		List<OrderTaskpackVo> list = orderTaskpackDaoVo.getByOrderIdAndTaskpacksgeId1(id,procedureNo);
		if(list.size() == 0){
			return null;
		}else{
			return list;
		}
	}
	

	public List<OrderTaskpackVo> getByOrderIdAndTaskpacksgeId(String id) {
		List<OrderTaskpackVo> list = orderTaskpackDaoVo.getByOrderIdAndTaskpacksgeId(id);
		if(list.size() == 0){
			return null;
		}else{
			return list;
		}
	}

	public List<OrderTaskpackVo> getByOrderIdAndNo(String orderId, String procedureNo) {
		return orderTaskpackDaoVo.getByOrderIdAndNo(orderId,procedureNo);
	}
	
}