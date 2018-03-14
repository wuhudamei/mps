package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.AppManagerOrder;

/**
 * 项目经理端
 * 现场交底
 * @author llp
 * 2016/10/17
 */
@MyBatisDao
public interface AppManagerOrderDao extends CrudDao2<AppManagerOrder>{

	/**
	 * 根据当前登陆项目经理的ID获取订单中所有的订单
	 * @param managerId
	 * @return List
	 */
	List<AppManagerOrder> getByItemManagerId(Integer managerId);

	/**
	 * 根据主键ID获取完整信息
	 * @param orderId
	 * @return object
	 */
	AppManagerOrder getById(Integer id);

	/**
	 * 更改订单状态为130-已现场交底
	 * @param orderstatus130Value
	 * @param orderstatus130ValueRemark
	 * @param id
	 * @return
	 */
	boolean updateByOrderStatusNumber(String orderstatus130Value, String orderstatus130ValueRemark, Integer id);

}
