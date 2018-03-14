/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;


import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.OrderCadfile;

/**
 * 订单图片DAO接口
 * @author mh
 * @version 2016-09-08
 */
@MyBatisDao
public interface OrderCadfileDao extends CrudDao2<OrderCadfile> {
	//更新图片
	void saveCadfile(OrderCadfile orderCadfile);
	//插入图片
	void insertCadfile(OrderCadfile orderCadfile);
	OrderCadfile findDtail(OrderCadfile orderCadfile);
	
	/*
	//通过订单id查询所有图纸对象
	public List<OrderCadfile> getOrderCadfileByOrderId(String orderId);
	*/
}