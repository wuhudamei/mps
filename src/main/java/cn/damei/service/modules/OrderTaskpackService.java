
package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.OrderTaskpack;
import cn.damei.entity.modules.Dict;
import cn.damei.dao.modules.OrderDao2;
import cn.damei.dao.modules.OrderTaskpackDao;


@Service
@Transactional(readOnly = true)
public class OrderTaskpackService extends CrudService<OrderTaskpackDao, OrderTaskpack> {

	@Autowired
	private OrderTaskpackDao orderTaskpackDao;
	@Autowired
	private OrderDao2 orderDao2;
	public OrderTaskpack get(String id) {
		return super.get(id);
	}
	
	public List<OrderTaskpack> findList(OrderTaskpack orderTaskpack) {
		return super.findList(orderTaskpack);
	}
	
	public Page<OrderTaskpack> findPage(Page<OrderTaskpack> page, OrderTaskpack orderTaskpack) {
		return super.findPage(page, orderTaskpack);
	}
	
	@Transactional(readOnly = false)
	public void save(OrderTaskpack orderTaskpack) {
		super.save(orderTaskpack);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrderTaskpack orderTaskpack) {
		super.delete(orderTaskpack);
	}
	@Transactional(readOnly = false)
	public boolean updateByOrderStatusAndTaskpackageStatus(String orderStatusNumber,String orderStatusNumberRemark,
			String orderTaskPackStatus,String orderId) {
		boolean flag = false;

		String status = orderDao2.findOrderStatusByOrderId(Integer.parseInt(orderId));
		if(Integer.parseInt(status) >=200){

			flag = orderTaskpackDao.updateIsCreateTaskpackage(orderTaskPackStatus,orderId) ? true :false;
		}else{
			flag = (orderTaskpackDao.updateByOrderStatusAndTaskpackageStatus(orderStatusNumber,
					orderStatusNumberRemark,orderTaskPackStatus,orderId)) ? true :false;
		}
		return flag;
	}


	@Transactional(readOnly = false)
	public void updateManager(Integer orderId, String managerName, Integer managerId) {

		dao.updateManager(orderId,managerName,managerId);


		try{

			toDoItemService.updateToDoItemInfoByOrderAndManagerId(orderId,managerId);
		}catch (Exception e){

			e.printStackTrace();
		}

	}



	@Autowired
	ToDoItemService toDoItemService;






	@Transactional(readOnly = false)
	public boolean updateTotal(Double sum, String taskpackageID, Double laborSum, Double auxiliarySum) {
		return (orderTaskpackDao.updateTotal(sum, taskpackageID,laborSum, auxiliarySum)) ? true : false;
	}
	
	public List<Dict> getOrderStatus(Integer number){
		return orderTaskpackDao.getOrderStatus(number);
	}
	
	public Integer getStayOrdCount(OrderTaskpack orderTaskpack){
		return orderTaskpackDao.getStayOrdCount(orderTaskpack);
	}

	public Page<OrderTaskpack> myFindPage(Page<OrderTaskpack> page, OrderTaskpack orderTaskpack) {
		orderTaskpack.setPage(page);
		page.setList(orderTaskpackDao.myFindList(orderTaskpack));
		return page;
	}
	
}