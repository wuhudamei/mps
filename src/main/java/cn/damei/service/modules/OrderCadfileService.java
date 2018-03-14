/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.OrderCadfile;
import cn.damei.dao.modules.OrderCadfileDao;

/**
 * 订单图片Service
 * @author mh
 * @version 2016-09-08
 */
@Service
@Transactional(readOnly = true)
public class OrderCadfileService extends CrudService2<OrderCadfileDao, OrderCadfile> {

	public OrderCadfile get(Integer id) {
		return super.get(id);
	}
	
	public List<OrderCadfile> findList(OrderCadfile orderCadfile) {
		return super.findList(orderCadfile);
	}
	
	public Page<OrderCadfile> findPage(Page<OrderCadfile> page, OrderCadfile orderCadfile) {
		return super.findPage(page, orderCadfile);
	}
	
	@Transactional(readOnly = false)
	public void save(OrderCadfile orderCadfile) {
		super.save(orderCadfile);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrderCadfile orderCadfile) {
		super.delete(orderCadfile);
	}
	//更新图片
	@Transactional(readOnly = false)
	public void saveCadfile(OrderCadfile orderCadfile) {
		dao.saveCadfile(orderCadfile);
		
	}
	//插入图片
	@Transactional(readOnly = false)
	public void insertCadfile(OrderCadfile orderCadfile) {
		dao.insertCadfile(orderCadfile);
		
	}

	public OrderCadfile findDetail(OrderCadfile orderCadfile) {
		// TODO Auto-generated method stub
		return dao.findDtail(orderCadfile);
	}
	
}