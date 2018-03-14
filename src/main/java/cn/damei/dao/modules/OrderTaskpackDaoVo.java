/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.OrderTaskpackVo;

/**
 * 订单管理DAO接口
 * @author llp
 * @version 2016-09-20
 */
@MyBatisDao
public interface OrderTaskpackDaoVo extends CrudDao<OrderTaskpackVo> {

	List<OrderTaskpackVo> getByOrderIdAndTaskpacksgeId1(String id,String procedureNo);
	
	List<OrderTaskpackVo> getByOrderIdAndTaskpacksgeId(String id);

	/**
	 * 根据门店和任务包模板编号
	 * @param orderId
	 * @param procedureNo
	 * @return List
	 */
	List<OrderTaskpackVo> getByOrderIdAndNo(String orderId, String procedureNo);
	
}