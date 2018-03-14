/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.mobile.Manager;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.AppOrderDao;
import cn.damei.entity.mobile.Manager.AppOrder;
import cn.damei.entity.mobile.Manager.AppOrderCadfile;

/**
 * 订单管理Service
 * @author wyb
 * @version 2016-09-08
 */
@Service
@Transactional(readOnly = true)
public class AppOrderService extends CrudService2<AppOrderDao, AppOrder> {
	
	@Autowired
	private AppOrderDao appOrderDao;
	
	//通过项目经理名称，查询项目经理下的所有订单
	public List<AppOrder> findAppOrderByitemManager(AppOrder appOrder) {
		return dao.findOrderByitemManager(appOrder);
	}
	//查询项目经理下所有订单的状态
	public List<String> selectState(int itemManagerId){
		return dao.selectState(itemManagerId);
	}
	//通过订单id查询订单详情
	public AppOrder getOrder(Integer id) {
		return dao.getOrder(id);
	}
	
	//确认开工中提交数据需修改delay_type(延期类型0代表公司原因1代表客户原因)
	@Transactional(readOnly = false)
	public boolean updateDelayType(String typeValue, String orderId) {
		return (appOrderDao.updateDelayType(typeValue,orderId)) ? true :false;
	}
	
	//查询房屋户型--字典表
	public String findHouseType(AppOrder appOrder) {
		
		return dao.findHouseType(appOrder);
	}
	
	//通过订单id查询订单图纸
	public List<AppOrderCadfile> selectCadfile(Integer id) {
		return dao.selectCadfile(id);
	}

	public AppOrder queryOrderByOrderTaskpackageId(Integer orderTaskpackageId){
		return dao.queryOrderByOrderTaskpackageId(orderTaskpackageId);
	}
	public String getOrderStatusByMaterislType(String orderId,String materislType){
	return	dao.getOrderStatusByMaterislType(orderId, materislType);
	}
}