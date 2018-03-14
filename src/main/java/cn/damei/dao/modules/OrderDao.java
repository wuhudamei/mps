
package cn.damei.dao.modules;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizCusServiceProblem;
import cn.damei.entity.modules.Order;
import cn.damei.entity.modules.OrderInstallItemVo;


@MyBatisDao
public interface OrderDao extends CrudDao<Order> {


	public Integer getOrderNumberById(String orderNumber);

	public Integer getIdByOrderNumber(String orderNumber);

	public Order getByIdAndStoreId(String id, String storeId);


	public List<Order> getAcceptAreaForOrder(Order order);


	public List<OrderInstallItemVo> findInstallItemByStoreId(Order order);


	public OrderInstallItemVo findInstallItemByInstallItemId(Integer installItemId);


	public Integer isGeneratedInstallItemPlan(OrderInstallItemVo vo);


	public void saveOrderInstallItem(OrderInstallItemVo vo);


	public List<OrderInstallItemVo> findInstallItemByOrderId(Integer orderId);


	public List<OrderInstallItemVo> findOrderInstallItemPlanStatus(Integer orderId);


	public void deleteOrderInstallItem(Integer orderId);


	public void deleteOrderInstallItemPlan(Integer orderId);


	public Integer selectInstallItemCount(OrderInstallItemVo vo);


	public void saveOrderInstallItemPlan(OrderInstallItemVo vo);


	public void deleteAllInstallItem(Integer orderId);


	void deleteAllInstallItemPlan(Integer orderId);

	List<Order> findEngineDepartByStoreIdProjectModeIdAndEmpId(Order order);

	List<Integer> selectProjectInstallItemIds(OrderInstallItemVo vo);

	List<OrderInstallItemVo> selectOrderProjectInstallItemToAdd(Map<String, Object> map);

	void batchSaveOrderInstallItem(List<OrderInstallItemVo> list);

	void batchSaveOrderInstallItemPlan(List<OrderInstallItemVo> list);

	List<Map<String, Object>> selectOrderInstallItemIds(Map<String, Object> map);

	Date selectActualStartDate(OrderInstallItemVo vo);


	public Map<String, String> selectOver(Integer orderId);


	Order findRelatedReportInfoByCustomerPhone(String customerPhone);

	void batchInsertOrderReportRelatedInfo(Order order);


	public Order getOrderByNuAndStoreId(Order order);



	public List<BizCusServiceProblem> findlistProject(BizCusServiceProblem bizCusServiceProblem);

	public Order getProjectbyId(String id);

	public List< Order>  getProjectName(String customerNameNot);



	public void updateInstallItem(OrderInstallItemVo item);

	public List<OrderInstallItemVo> selectOrderInstallItemIdsList(Map map);

	public void updateInstallMode(OrderInstallItemVo orderInstallItemVo);

	public void updateInstallplanMode(OrderInstallItemVo orderInstallItemVo);

	public List<Order> finList3(Order order);

	public Integer queryCheckStatus(Integer orderId, Integer projectInstallItemId);
	
	public List<Order> findOrderByParam(Order order);


	public void deleteOrderInstallItemSomeThing(Map<String, Object> param);


	public void deleteOrderInstallItemPlanSomeThing(Integer orderId);

    String findStoreId(String orderId);

    OrderInstallItemVo findInstallStatus(Integer id);
}