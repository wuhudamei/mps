package cn.damei.service.mobile.Manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.OrderDisclosePicDao;
import cn.damei.entity.mobile.Manager.OrderDisclosePic;

/**
 * 项目经理端 现场交底
 * 
 * @author llp 2016/10/17
 */
@Service
@Transactional(readOnly = true)
public class OrderDisclosePicService extends CrudService2<OrderDisclosePicDao, OrderDisclosePic> {

	@Autowired
	private OrderDisclosePicDao orderDisclosePicDao;

	/**
	 * biz_order_disclosePic写数据
	 * 
	 * @param picsVal1
	 * @param picsVal2
	 * @param picsVal3
	 * @param discloseId
	 * @param subPath
	 * @param string
	 * @return
	 */
	@Transactional(readOnly = false)
	public String insertByDisclosePic(Integer discloseId, String type, String subPath) {
		OrderDisclosePic pic = new OrderDisclosePic();

		pic.setId(null);
		pic.setOrderDiscloseId(discloseId);
		pic.setOrderDisclosePicType(type);
		pic.setPicUrl(subPath);

		// batch插入
		return orderDisclosePicDao.insertPic(pic) ? "0" : "2";

	}
}
