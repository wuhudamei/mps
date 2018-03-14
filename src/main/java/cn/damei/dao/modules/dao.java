/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;



import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.OrderTaskpackage;
import cn.damei.entity.modules.orderVo;

/**
 * 任务包计划调整
 * @author 梅浩
 * @version 2016-09-08
 */
@MyBatisDao
public interface dao extends CrudDao<orderVo> {
	

	
	/**
	 * 查询订单下的任务包   任务包计划
	 * @param orderId
	 * @return
	 */
	public List<OrderTaskpackage> getPackageByOrderId(Integer orderId); 
	
	/**
	 * 更新任务包的开始时间和结束时间    -->调整任务包计划
	 * @param pack
	 */
	public  void updatePackTime(OrderTaskpackage   pack);
	/**
	 * 更新任务包的开始时间和结束时间    -->调整任务包计划
	 * @param pack
	 */
	public  void updatePackTime1(OrderTaskpackage   pack);
	
	
	
	/**
	 * 定时根据当前日期加3天,查找需要发送短信的任务包   --> 早9点定时短信发送
	 * @param planStartdate
	 * @return
	 */
	public List<OrderTaskpackage>sendFixedTimeMessageToManagerForPackPlanTime(String planStartdate);
	
	
}