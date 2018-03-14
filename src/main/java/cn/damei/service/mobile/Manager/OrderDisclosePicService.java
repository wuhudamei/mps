package cn.damei.service.mobile.Manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.OrderDisclosePicDao;
import cn.damei.entity.mobile.Manager.OrderDisclosePic;


@Service
@Transactional(readOnly = true)
public class OrderDisclosePicService extends CrudService2<OrderDisclosePicDao, OrderDisclosePic> {

	@Autowired
	private OrderDisclosePicDao orderDisclosePicDao;


	@Transactional(readOnly = false)
	public String insertByDisclosePic(Integer discloseId, String type, String subPath) {
		OrderDisclosePic pic = new OrderDisclosePic();

		pic.setId(null);
		pic.setOrderDiscloseId(discloseId);
		pic.setOrderDisclosePicType(type);
		pic.setPicUrl(subPath);


		return orderDisclosePicDao.insertPic(pic) ? "0" : "2";

	}
}
