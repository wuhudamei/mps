/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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

/**
 * 订单管理Service
 * @author llp
 * @version 2016-09-20
 */
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
		//根据订单id拿到当前订单的状态
		String status = orderDao2.findOrderStatusByOrderId(Integer.parseInt(orderId));
		if(Integer.parseInt(status) >=200){
			//只更新订单是否生成任务包
			flag = orderTaskpackDao.updateIsCreateTaskpackage(orderTaskPackStatus,orderId) ? true :false;
		}else{
			flag = (orderTaskpackDao.updateByOrderStatusAndTaskpackageStatus(orderStatusNumber,
					orderStatusNumberRemark,orderTaskPackStatus,orderId)) ? true :false;
		}
		return flag;
	}

	/**
	 * 重新分配经理
	 * @param orderId
	 * @param managerName
	 * @param managerId
	 */
	@Transactional(readOnly = false)
	public void updateManager(Integer orderId, String managerName, Integer managerId) {

		dao.updateManager(orderId,managerName,managerId);


		try{

			toDoItemService.updateToDoItemInfoByOrderAndManagerId(orderId,managerId);
		}catch (Exception e){

			e.printStackTrace();
		}

	}


	/**
	 * 更新待办经理id
	 */
	@Autowired
	ToDoItemService toDoItemService;





	/**
	 *
	 * 任务包审核
	 * 修改任务包-修改订单任务包总价
	 * @param sum
	 * @param taskpackageID 
	 * @return
	 */
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