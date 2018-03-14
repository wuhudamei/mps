package cn.damei.service.mobile.Manager;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.DateUtils;
import cn.damei.dao.mobile.Manager.OrderConfirmStartworkPicDao;
import cn.damei.entity.mobile.Manager.OrderConfirmStartworkPic;


@Service
@Transactional(readOnly = true)
public class OrderConfirmStartworkPicService extends CrudService2<OrderConfirmStartworkPicDao, OrderConfirmStartworkPic>{

	@Autowired
	private OrderConfirmStartworkPicDao orderConfirmStartworkPicDao;

	@Transactional(readOnly = false)
	public boolean insertConfirmStartworkPic(int orderConfirmStartworkID, String path, String startRemark) {
		OrderConfirmStartworkPic workPic = new OrderConfirmStartworkPic();
		workPic.setId(null);
		workPic.setOrderConfirmStartworkId(orderConfirmStartworkID);
		workPic.setPicUrl(path);
		workPic.setRemarks(startRemark);

		workPic.setCreateDate(DateUtils.addDate(new Date(), 0));
		workPic.setUpdateDate(DateUtils.addDate(new Date(), 0));

		workPic.setDelFlag("0");
		
		return (orderConfirmStartworkPicDao.insertConfirmStartworkPic(workPic)) ? true : false;
	}

}
