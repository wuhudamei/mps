package cn.damei.service.mobile.Manager;

import java.util.Date;

import org.apache.http.impl.cookie.DateParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.DateUtils;
import cn.damei.dao.mobile.Manager.OrderFinishProjectBillDao;
import cn.damei.entity.mobile.Manager.OrderFinishProjectBill;

/**
 * 确认竣工
 * @author llp
 */
@Service
@Transactional(readOnly = true)
public class OrderFinishProjectBillService extends CrudService2<OrderFinishProjectBillDao, OrderFinishProjectBill>{
	
	@Autowired
	private OrderFinishProjectBillDao orderFinishProjectBillDao;

	/**
	 * 确认竣工申请功能
	 * @param orderID 
	 * @param managerID 
	 * @return String
	 */
	@Transactional(readOnly = false)
	public int insert(String realFinishProjectDate, String orderID, String number, Integer managerID) 
			throws DateParseException {
		Date nowDate = DateUtils.addDays(new Date(), 0);
		OrderFinishProjectBill projectBill = new OrderFinishProjectBill();
		
		projectBill.setId(null);
		projectBill.setOrderId(Integer.valueOf(orderID));
		if(number.equals("")){
			projectBill.setOrderFinishProjectBillCode("");
		}else{
			projectBill.setOrderFinishProjectBillCode(number);
		}
		projectBill.setRealFinishProjectDate(DateUtils.parseDate(realFinishProjectDate));
		projectBill.setApplyEmployeeId(managerID);
		projectBill.setApplyDatetime(nowDate);
		projectBill.setCheckEmployeeId(null);
		projectBill.setCheckWords(null);
		projectBill.setStatus("1");
		projectBill.setRemarks("项目经理已申请");
		projectBill.setCreateDate(nowDate);
		projectBill.setUpdateDate(nowDate);
		projectBill.setDelFlag("0");
		
		orderFinishProjectBillDao.insertBill(projectBill);
		
		int id = projectBill.getId();
		logger.info("返回的主键ID："+id);
		return id;
	}

	/**
	 * 根据orderID查询所有
	 * @param orderID
	 * @return List
	 */
	public OrderFinishProjectBill queryListByOrderID(Integer orderID) {
		return orderFinishProjectBillDao.queryListByOrderID(orderID);
	}

	/**
	 * 根据订单编号获取整条数据
	 * @param orderID
	 * @return bean
	 */
	public OrderFinishProjectBill getByOrderID(Integer orderID) {
		return orderFinishProjectBillDao.getByOrderID(orderID);
	}



	//如果竣工单 存在, 更新竣工单 并删除无用的图片
	@Transactional(readOnly = false)
	public String updateByMore(String realFinishProjectDate, String orderID, Integer managerID, String updateDate,String billId) {


	boolean result = orderFinishProjectBillDao.updateByMore(realFinishProjectDate,orderID,managerID,updateDate) ;
			orderFinishProjectBillDao.deleteUselessPicsByRelatedId(billId);
	if(result){

		return "0";
	}else {

		return "2";
	}
	}	

}
