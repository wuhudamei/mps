/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizCusServiceProblem;
import cn.damei.entity.modules.Order;
import cn.damei.entity.modules.OrderInstallItemVo;

/**
 * 订单管理DAO接口
 * 
 * @author wyb
 * @version 2016-09-08
 */
@MyBatisDao
public interface OrderDao extends CrudDao<Order> {

	/**
	 * ajax订单编号 去重
	 * 
	 * @return
	 */
	public Integer getOrderNumberById(String orderNumber);

	public Integer getIdByOrderNumber(String orderNumber);

	public Order getByIdAndStoreId(String id, String storeId);

	/**
	 * 接单区域
	 * 
	 * @return
	 */
	public List<Order> getAcceptAreaForOrder(Order order);

	/**
	 * 参数: 门店id,工程模式 ajax 加载安装项模板
	 * 
	 * @param
	 * @return
	 */
	public List<OrderInstallItemVo> findInstallItemByStoreId(Order order);

	/**
	 * 根据安装项id 查询该对象
	 * 
	 * @param installItemId
	 * @return
	 */
	public OrderInstallItemVo findInstallItemByInstallItemId(Integer installItemId);

	/**
	 * 根据订单安装项,查询是否生成了 安装项计划 (订单id和 安装项id)
	 * 
	 * @param vo
	 * @return
	 */
	public Integer isGeneratedInstallItemPlan(OrderInstallItemVo vo);

	/**
	 * 保存订单安装项
	 * 
	 * @param vo
	 */
	public void saveOrderInstallItem(OrderInstallItemVo vo);

	/**
	 * 根据订单id查询该订单下的所有安装项
	 * 
	 * @param orderId
	 * @return
	 */
	public List<OrderInstallItemVo> findInstallItemByOrderId(Integer orderId);

	/**
	 * 根据订单id(状态为200) 查询订单安装项计划中, 对应的安装项状态 返回值: orderId id status
	 * 
	 * @param orderId
	 * @return
	 */
	public List<OrderInstallItemVo> findOrderInstallItemPlanStatus(Integer orderId);

	/**
	 * 根据订单id 删除不是已经申请和验收的安装项
	 * 
	 * @param
	 */
	public void deleteOrderInstallItem(Integer orderId);

	/**
	 * 根据订单id ,删除安装项计划中状态为1的安装项
	 * 
	 * @param orderId
	 */
	public void deleteOrderInstallItemPlan(Integer orderId);

	/**
	 * 查询是否有记录
	 * 
	 * @param vo
	 * @return
	 */
	public Integer selectInstallItemCount(OrderInstallItemVo vo);

	/**
	 * 更新安装项计划
	 * 
	 * @param vo
	 */
	public void saveOrderInstallItemPlan(OrderInstallItemVo vo);

	/**
	 * 订单状态小于200 不必考虑 全删, 并保存最新的
	 * 
	 * @param orderId
	 */
	public void deleteAllInstallItem(Integer orderId);

	/**
	 * 订单状态小于200 不必考虑 全删, 并更新计划最新的
	 * 
	 * @param orderId
	 */
	void deleteAllInstallItemPlan(Integer orderId);

	List<Order> findEngineDepartByStoreIdProjectModeIdAndEmpId(Order order);

	List<Integer> selectProjectInstallItemIds(OrderInstallItemVo vo);

	List<OrderInstallItemVo> selectOrderProjectInstallItemToAdd(Map<String, Object> map);

	void batchSaveOrderInstallItem(List<OrderInstallItemVo> list);

	void batchSaveOrderInstallItemPlan(List<OrderInstallItemVo> list);

	List<Map<String, Object>> selectOrderInstallItemIds(Map<String, Object> map);

	Date selectActualStartDate(OrderInstallItemVo vo);

	// 根据订单 id 查询是否竣工 1 是 0 否
	public Map<String, String> selectOver(Integer orderId);

	/**
	 * 查询新增订单客户手机号和返单关联的信息
	 * 
	 * @param customerPhone
	 * @return
	 */
	Order findRelatedReportInfoByCustomerPhone(String customerPhone);

	void batchInsertOrderReportRelatedInfo(Order order);

	/**
	 * 根据storeID 和 orderNu获取
	 * 
	 * @param order
	 * @return
	 */
	public Order getOrderByNuAndStoreId(Order order);

	/**
	 * @param
	 *  // 根据订单编号,手机号.客户名称查询订单信息
	 * 
	 * @Title: findlistProject
	 * @Description: TODO
	 * @param @param oNumAndcusNameIph
	 * @param @return
	 * @return List<Order>
	 * @author ZhangTongWei
	 * @throws
	 */

	public List<BizCusServiceProblem> findlistProject(BizCusServiceProblem bizCusServiceProblem);

	public Order getProjectbyId(String id);

	public List< Order>  getProjectName(String customerNameNot);

	/**
	 * 根据安装项ID修改安装项
	 * 
	 * @Title: update
	 * @Description: TODO
	 * @param @param item
	 * @return void
	 * @author ZhangTongWei
	 * @throws
	 */

	public void updateInstallItem(OrderInstallItemVo item);

	public List<OrderInstallItemVo> selectOrderInstallItemIdsList(Map map);

	public void updateInstallMode(OrderInstallItemVo orderInstallItemVo);

	public void updateInstallplanMode(OrderInstallItemVo orderInstallItemVo);

	public List<Order> finList3(Order order);

	public Integer queryCheckStatus(Integer orderId, Integer projectInstallItemId);
	
	public List<Order> findOrderByParam(Order order);

	/**
	 * 删除该订单下的订单安装项，匹配条件：不包含在提交的工程安装项id中的订单安装项
	 * @param param
	 */
	public void deleteOrderInstallItemSomeThing(Map<String, Object> param);

	/**
	 * 删除该订单下的订单安装项计划，匹配条件：不包含在订单安装项中的订单安装项计划
	 * @param orderId
	 */
	public void deleteOrderInstallItemPlanSomeThing(Integer orderId);

    String findStoreId(String orderId);

    OrderInstallItemVo findInstallStatus(Integer id);
}