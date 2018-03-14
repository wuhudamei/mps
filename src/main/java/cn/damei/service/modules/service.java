/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.OrderTaskpackage;
import cn.damei.dao.modules.dao;
import cn.damei.entity.modules.orderVo;

/**
 * 订单管理Service
 * @author wyb
 * @version 2016-09-08
 */
@Service
@Transactional(readOnly = true)
public class service extends CrudService<dao, orderVo> {
	

	public List<orderVo> findList(orderVo order) {
		return super.findList(order);
	}
	public Page<orderVo> findPage(Page<orderVo> page, orderVo order) {
		return super.findPage(page, order);
	}
	/**
	 * 查询订单下的任务包   任务包计划
	 * @param orderId
	 * @return
	 */
	public List<OrderTaskpackage> getPackageByOrderId(Integer orderId){
		
		return dao.getPackageByOrderId(orderId);
	}
	
	/**
	 * 更新任务包的开始时间和结束时间    -->调整任务包计划
	 * @param pack
	 */
	@Transactional(readOnly = false)
	public  void updatePackTime(OrderTaskpackage   pack){
		
		dao.updatePackTime(pack);
	}
	/**
	 * 更新任务包的开始时间和结束时间    -->调整任务包计划
	 * @param pack
	 */
	@Transactional(readOnly = false)
	public  void updatePackTime1(OrderTaskpackage   pack){
		
		dao.updatePackTime1(pack);
	}
	

	/**
	 * 定时根据当前日期加3天,查找需要发送短信的任务包   --> 早9点定时短信发送
	 * @param planStartdate
	 * @return
	 */
	public List<OrderTaskpackage>sendFixedTimeMessageToManagerForPackPlanTime(String planStartdate){
		
		
		return dao.sendFixedTimeMessageToManagerForPackPlanTime(planStartdate);
	}
	
}