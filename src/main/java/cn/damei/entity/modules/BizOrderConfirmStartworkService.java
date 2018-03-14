package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.damei.entity.modules.BizCustomerReturnVisitTraditionOrderData;
import cn.damei.service.modules.BizCustomerReturnVisitRecordService;
import cn.damei.dao.modules.OrderDao2;
import cn.damei.entity.modules.Order2;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.OrderConfirmStartworkDao;
import cn.damei.entity.mobile.Manager.OrderConfirmStartwork;

/**
 * 确认开工功能
 * 
 * @author llp
 *
 */
@Service
@Transactional(readOnly = true)
public class BizOrderConfirmStartworkService extends CrudService2<OrderConfirmStartworkDao, OrderConfirmStartwork> {

	@Autowired
	private OrderConfirmStartworkDao orderConfirmStartworkDao;// 确认开工功能

	@Autowired
	private OrderDao2 orderDao2;

	//传统待回访订单
	@Autowired
	private BizCustomerReturnVisitRecordService bizCustomerReturnVisitRecordService;
	@Transactional(readOnly = false)
	public int insertConfirmStartwork(String selDecorateDelayDays, String isSelfDecorateNeedSign, 
			String orderId, String isNeedSign, String remark) {
		
		OrderConfirmStartwork sWork = new OrderConfirmStartwork();
		sWork.setId(null);
		sWork.setOrderId(Integer.valueOf(orderId));
		sWork.setIsNeedSign(isNeedSign);
		sWork.setSelfDecorateDelayDays(Integer.valueOf(selDecorateDelayDays));
		sWork.setIsSelfDecorateNeedSign(isSelfDecorateNeedSign);
		sWork.setRemarks(remark);
		sWork.setCreateBy(null);
		sWork.setCreateDate(DateUtils.addDays(new Date(), 0));
		sWork.setUpdateBy(null);
		sWork.setUpdateDate(DateUtils.addDays(new Date(), 0));
		sWork.setDelFlag("0");
		
		orderConfirmStartworkDao.insertConfirmStartwork(sWork);

		Order2 order = orderDao2.get(Integer.parseInt(orderId));
		//查询回访节点表是否设置过这个节点
		List<Map<String,Object>>ll=bizCustomerReturnVisitRecordService.findIsThereNode(order.getStoreId(),0);
		if(ll.size()>0){
			//判断传统订单表中是否插入过该订单对应的节点数据
			Integer i=0;
			if(null!=orderId){
				i=bizCustomerReturnVisitRecordService.findExistCount(Integer.parseInt(orderId),0);
			}
			if(i==0){
				BizCustomerReturnVisitTraditionOrderData bto=new BizCustomerReturnVisitTraditionOrderData();
				bto.setOrderId(orderId);
				bto.setReturnVisitNode("0");
				bto.setReturnVisitStatus(1);
				Date date=new Date();
				bto.setCreateDate(date);
				bizCustomerReturnVisitRecordService.insertBizCustomerReturnVisitTraditionOrderData(bto);
				bto.preInsert();
			}
		}
		//将状态变为已过期
		if(null!=orderId) {
			List<BizCustomerReturnVisitTraditionOrderData> list = bizCustomerReturnVisitRecordService.findReturnVisitNode(Integer.parseInt(orderId));
			if (list.size() > 0) {
				for (BizCustomerReturnVisitTraditionOrderData bto : list) {
					bto.setReturnVisitStatus(0);
					bizCustomerReturnVisitRecordService.updateStatus(bto);
				}
			}
		}
		logger.info("返回的主键ID："+sWork.getId());
		return sWork.getId();//返回biz_order_confirm_startwork主键ID
	}

}
