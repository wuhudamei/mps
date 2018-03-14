package cn.damei.service.mobile.Manager;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.OrderConfirmStartworkDao;
import cn.damei.entity.mobile.Manager.OrderConfirmStartwork;
import cn.damei.entity.mobile.Manager.Manager;

/**
 * 确认开工功能
 * 
 * @author llp
 *
 */
@Service
@Transactional(readOnly = true)
public class OrderConfirmStartworkService extends CrudService2<OrderConfirmStartworkDao, OrderConfirmStartwork> {

	@Autowired
	private OrderConfirmStartworkDao orderConfirmStartworkDao;// 确认开工功能

	@Transactional(readOnly = false)
	public int insertConfirmStartwork(String selDecorateDelayDays, String isSelfDecorateNeedSign, 
			String orderId, String isNeedSign, String remark, Manager manager) {

		OrderConfirmStartwork sWork = new OrderConfirmStartwork();
		sWork.setId(null);
		sWork.setOrderId(Integer.valueOf(orderId));
		sWork.setIsNeedSign(isNeedSign);
		sWork.setSelfDecorateDelayDays(Integer.valueOf(selDecorateDelayDays));
		sWork.setIsSelfDecorateNeedSign(isSelfDecorateNeedSign);
		sWork.setRemarks(remark);
		sWork.setCreateByAuthor(manager.getId().toString());
		sWork.setCreateDate(DateUtils.addDays(new Date(), 0));
		sWork.setUpdateByAuthor(manager.getId().toString());
		sWork.setUpdateDate(DateUtils.addDays(new Date(), 0));
		sWork.setDelFlag("0");
		
		orderConfirmStartworkDao.insertConfirmStartwork(sWork);
		
		logger.info("返回的主键ID："+sWork.getId());
		return sWork.getId();//返回biz_order_confirm_startwork主键ID
	}

	public Integer findByOrderId(Integer orderId) {
		return orderConfirmStartworkDao.findByOrderId(orderId);
	}

}
