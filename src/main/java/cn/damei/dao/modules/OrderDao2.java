
package cn.damei.dao.modules;



import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.ItemManagerMap;
import cn.damei.entity.modules.Order2;


@MyBatisDao
public interface OrderDao2 extends CrudDao2<Order2> {
	

	public String  getOrderNumberById(String orderNumber);


	public List<Map<String ,String>> orderByActualEndDate(String _parameter);


	public List<Map<String,String>> findOrder();

	public Integer findCheckedCount(Integer iorderInspectorIdd);
	

	public Integer findCheckingCount(Integer orderInspectorId);

	

	public Integer findBuildingCount(Integer itemManagerId);
	
	

	public Integer findOrderCount(Integer itemManagerId);


	public List<Order2> findOrderList(Order2 order);


	public List<Order2> findOrderPaymentList(Order2 order);


	public List<ItemManagerMap> findMapList(ItemManagerMap itemManagerMap);

	public List<DropModel> findOrderListByCondition(Integer empId);

	public List<Order2> findOrderByPhone(String username);

	public List<Order2> findOrderManagerGuranteeMoneyList(Order2 order);

	public Integer findUnfinishedOrderByEmployeeId(Map<String, Integer> map);

	public String findOrderStatusByOrderId(int parseInt);


	public List<Order2>findManagerInfo(Order2 Order2);
	public List<Order2>findWorkerMapInfo(Order2 Order2);
	public List<ItemManagerMap> findManagerMoreCount(String date);
	public List<ItemManagerMap> findManagerMoreCount1(List<ItemManagerMap> list);

	public int getUnAllotCount(Order2 order);

	public int getUnInspectorCount(Order2 order);
	

	public List<Order2> getProMangerDaily(Order2 order);

	public int getProMangerCount(Order2 order);
	

	public List<Order2> getInspectorDaily(Order2 order);

	public int getInspectorCount(Order2 order);

	public String findDesignerId(Order2 orderId);
}