/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;



import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.ItemManagerMap;
import cn.damei.entity.modules.Order2;

/**
 * 订单管理DAO接口
 * @author wyb
 * @version 2016-09-08
 */
@MyBatisDao
public interface OrderDao2 extends CrudDao2<Order2> {
	
	/**
	 * ajax订单编号 去重
	 * @return
	 */
	public String  getOrderNumberById(String orderNumber);

	/**
	 *
	 * 查询全国每天已竣工订单数据
	 * @return
	 */
	public List<Map<String ,String>> orderByActualEndDate(String _parameter);

	/**
	 *
	 * 查询全国已竣工订单数据
	 * @return
	 */
	public List<Map<String,String>> findOrder();
	/**
	 * 质检员累计检查订单数
	 * @param id
	 * @return
	 */
	public Integer findCheckedCount(Integer iorderInspectorIdd);
	
	/**
	 * 质检员在检订单数
	 * @param id
	 * @return
	 */
	public Integer findCheckingCount(Integer orderInspectorId);

	
	/**
	 * 项目经理订单在施工数
	 * @param id
	 * @return
	 */
	public Integer findBuildingCount(Integer itemManagerId);
	
	
	/**
	 * 项目经理已接的订单数
	 * @param id
	 * @return
	 */
	public Integer findOrderCount(Integer itemManagerId);

	/**
	 * 查询
	 * @param order
	 * @return
	 */
	public List<Order2> findOrderList(Order2 order);

	/**
	 * 查询
	 * @param order
	 * @return
	 */
	public List<Order2> findOrderPaymentList(Order2 order);

	/**
	 * 根据地图分配项目经理--根据订单id查询项目经理
	 * @param itemManagerMap
	 * @return
	 */
	public List<ItemManagerMap> findMapList(ItemManagerMap itemManagerMap);

	public List<DropModel> findOrderListByCondition(Integer empId);

	public List<Order2> findOrderByPhone(String username);

	public List<Order2> findOrderManagerGuranteeMoneyList(Order2 order);

	public Integer findUnfinishedOrderByEmployeeId(Map<String, Integer> map);

	public String findOrderStatusByOrderId(int parseInt);
	//地图经理数据查询 
	/**
	 * 
	 * @param managerName,star,storeId,engineDepartId,projectMode
	 * @return
	 */
	public List<Order2>findManagerInfo(Order2 Order2);
	public List<Order2>findWorkerMapInfo(Order2 Order2);
	public List<ItemManagerMap> findManagerMoreCount(String date);
	public List<ItemManagerMap> findManagerMoreCount1(List<ItemManagerMap> list);

	public int getUnAllotCount(Order2 order);

	public int getUnInspectorCount(Order2 order);
	
	//派项目经理日报表
	public List<Order2> getProMangerDaily(Order2 order);
	//门店当前待派项目经理订单
	public int getProMangerCount(Order2 order);
	
	//派质检员日报表	
	public List<Order2> getInspectorDaily(Order2 order);
	//门店当前待派质检员订单
	public int getInspectorCount(Order2 order);
	//查询设计师的ID
	public String findDesignerId(Order2 orderId);
}