package cn.damei.service.modules;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.dao.modules.BizOrderFinishProjectBillDao;
import cn.damei.entity.modules.BizOrderFinishProjectBill;

/**
 * 公共上传图片
 * @author llp
 */
@Service
@Transactional(readOnly = true)
public class BizOrderFinishProjectBillService extends CrudService2<BizOrderFinishProjectBillDao, BizOrderFinishProjectBill>{
	
	@Autowired
	private BizOrderFinishProjectBillDao bizOrderFinishProjectBillDao;
	
	public BizOrderFinishProjectBill getByOrderID(Integer orderID) {
		return bizOrderFinishProjectBillDao.getByOrderID(orderID);
	}

	@Transactional(readOnly = false)
	public String updateByDate(Integer id, String realFinishProjectDate) {
		return bizOrderFinishProjectBillDao.updateByDate(realFinishProjectDate,id) ? "0" : "1";
	}

	/**
	 * 根据订单编号修改数据
	 * @param valueOf
	 * @return
	 */
	@Transactional(readOnly = false)
	public String updateByOrderID(Integer orderID,Integer jiesuanId) {
		return bizOrderFinishProjectBillDao.updateByOrderID(orderID,DateUtils.getDate1("yyyy-MM-dd HH:mm:ss"),jiesuanId,
				ConstantUtils.ORDER_FINISH_PROJECT_BILL_STATUS_3,ConstantUtils.ORDERSTATUS_340_VALUE_REMARK,DateUtils.getDate1("yyyy-MM-dd HH:mm:ss")) ? "0" : "1";
	}

	/**
	 * 330-结算员竣工审核不通过
	 * @param managerID 
	 * @param remarks 
	 */
	@Transactional(readOnly = false)
	public String updateByOrderIDOrFail(Integer orderID, String checkWords, Integer jiesuanId) {
		return bizOrderFinishProjectBillDao.updateByOrderIDOrFail(orderID,checkWords,ConstantUtils.ORDER_FINISH_PROJECT_BILL_STATUS_2,
				ConstantUtils.ORDERSTATUS_330_VALUE_REMARK,DateUtils.getDate1("yyyy-MM-dd HH:mm:ss"),jiesuanId) ? "0" : "1";
	}

	@Transactional(readOnly = false)
	public String updateByStatusOrRemarks(String orderFinishProjectBillStatus4, String orderstatus400ValueRemark,
			String date1, Integer orderId) {
		return bizOrderFinishProjectBillDao.updateByStatusOrRemarks(orderFinishProjectBillStatus4,
				orderstatus400ValueRemark,date1,orderId) ? "0" : "1";
	}
	@Transactional(readOnly = false)
	public void updateOrderById(Integer valueOf, String realFinishProjectDate) {
		// TODO Auto-generated method stub
		bizOrderFinishProjectBillDao.updateOrderById(valueOf,realFinishProjectDate);
	}

}
