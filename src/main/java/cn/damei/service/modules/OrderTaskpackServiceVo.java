/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.OrderTaskpackDaoVo;
import cn.damei.entity.modules.OrderTaskpackVo;

/**
 * 订单管理Service
 * @author llp
 * @version 2016-09-20
 */
@Service
@Transactional(readOnly = true)
public class OrderTaskpackServiceVo extends CrudService<OrderTaskpackDaoVo, OrderTaskpackVo> {

	@Autowired
	private OrderTaskpackDaoVo orderTaskpackDaoVo;
	
	public OrderTaskpackVo get(String id) {
        return super.get(id);
    }
	
	//生成任务包后"查看详情"
	public List<OrderTaskpackVo> getByOrderIdAndTaskpacksgeId1(String id,String procedureNo) {
		List<OrderTaskpackVo> list = orderTaskpackDaoVo.getByOrderIdAndTaskpacksgeId1(id,procedureNo);
		if(list.size() == 0){
			return null;
		}else{
			return list;
		}
	}
	
	//生成任务包后"查看详情"
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