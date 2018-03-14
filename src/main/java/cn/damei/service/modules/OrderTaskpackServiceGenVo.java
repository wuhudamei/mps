/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.OrderTaskpackDaoGenVo;
import cn.damei.entity.modules.OrderTaskpackGenVo;

/**
 * 订单管理Service
 * @author llp
 * @version 2016-09-20
 */
@Service
@Transactional(readOnly = true)
public class OrderTaskpackServiceGenVo extends CrudService<OrderTaskpackDaoGenVo, OrderTaskpackGenVo> {

	@Autowired
	private OrderTaskpackDaoGenVo orderTaskpackDaoGenVo;
	
	public OrderTaskpackGenVo get(String id) {
        return super.get(id);
    }
	
	//生成任务包后
	public List<OrderTaskpackGenVo> getByOrderIdAndTaskpacksgeId(String contractStartDate,String storeId,String store,String projectMode) {
		List<OrderTaskpackGenVo> list = orderTaskpackDaoGenVo.getByOrderIdAndTaskpacksgeId(contractStartDate,storeId,store,projectMode);
		if(list.size() == 0){
			return null;
		}else{
			return list;
		}
	}

	//任务包审核中修改任务包
	public OrderTaskpackGenVo getByOrderAndEffectiveDate(String storeID, String storeID1, Date contractStartDate,String procedureNo,String taskPackageTemplatNo,String projectMode) {
		return orderTaskpackDaoGenVo.getByOrderAndEffectiveDate(storeID,storeID1,contractStartDate,procedureNo,taskPackageTemplatNo,projectMode);
	}
	
	public List<OrderTaskpackGenVo> getTemplatByOrderIdAndTaskpacksgeId(String contractStartDate,String storeId,String store,String projectMode) {
		List<OrderTaskpackGenVo> list = orderTaskpackDaoGenVo.getTemplatByOrderIdAndTaskpacksgeId(contractStartDate,storeId,store,projectMode);
		if(list.size() == 0){
			return null;
		}else{
			return list;
		}
	}

}