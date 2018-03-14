/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizMaterialsChoiceBillDao;
import cn.damei.entity.modules.BizMaterialsChoiceBill;

/**
 * 选材单表Service
 * 
 * @author wyb
 * @version 2017-06-13
 */
@Service
@Transactional(readOnly = true)
public class BizMaterialsChoiceBillService extends CrudService2<BizMaterialsChoiceBillDao, BizMaterialsChoiceBill> {

	public BizMaterialsChoiceBill get(Integer id) {
		return super.get(id);
	}

	public List<BizMaterialsChoiceBill> findList(BizMaterialsChoiceBill bizMaterialsChoiceBill) {
		return super.findList(bizMaterialsChoiceBill);
	}

	public Page<BizMaterialsChoiceBill> findPage(Page<BizMaterialsChoiceBill> page, BizMaterialsChoiceBill bizMaterialsChoiceBill) {
		return super.findPage(page, bizMaterialsChoiceBill);
	}

	@Transactional(readOnly = false)
	public void save(BizMaterialsChoiceBill bizMaterialsChoiceBill) {
		super.save(bizMaterialsChoiceBill);
	}

	@Transactional(readOnly = false)
	public void delete(BizMaterialsChoiceBill bizMaterialsChoiceBill) {
		super.delete(bizMaterialsChoiceBill);
	}

	/**
	 * 根据订单id查询订单详情以及选材清单id
	 * 
	 * @param orderId
	 * @return
	 */
	public BizMaterialsChoiceBill findOrder(Integer orderId) {
		return dao.findOrder(orderId);
	}

	/**
	 * 根据订单编号查询订单选材清单的墙地砖预算面积
	 * 
	 * @param orderNumber
	 * @return
	 */
	public BizMaterialsChoiceBill findWallFloorTileSquareBudgetAllCount(String orderNumber) {
		return dao.findWallFloorTileSquareBudgetAllCount(orderNumber);
	}

	public BizMaterialsChoiceBill getOrder(Integer orderId) {
		return dao.getOrder(orderId);
	}

}