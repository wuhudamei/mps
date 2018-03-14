/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialsChoiceBill;

/**
 * 选材单表DAO接口
 * 
 * @author wyb
 * @version 2017-06-13
 */
@MyBatisDao
public interface BizMaterialsChoiceBillDao extends CrudDao2<BizMaterialsChoiceBill> {

	/**
	 * 查询选材单表是否存在
	 * 
	 * @param orderNumber
	 * @return
	 */
	BizMaterialsChoiceBill findorderAndMaterialsMessage(String orderNumber);

	/**
	 * 查询订单id
	 * 
	 * @param orderNumber
	 * @return
	 */
	Integer findOrderIdMessage(String orderNumber);

	/**
	 * 查询选材单
	 * 
	 * @param id
	 */
	void deleteMaterialsChoiceBill(Integer id);

	/**
	 * 根据订单id查询订单详情以及选材清单id
	 * 
	 * @param orderId
	 * @return
	 */
	BizMaterialsChoiceBill findOrder(Integer orderId);

	/**
	 * 根据订单编号查询订单选材清单的墙地砖预算面积
	 * 
	 * @param orderNumber
	 * @return
	 */
	BizMaterialsChoiceBill findWallFloorTileSquareBudgetAllCount(String orderNumber);

	/**
	 * 更新选材清单的订单id
	 * 
	 * @param materials
	 */
	void updateMaterialsChoiceBill(BizMaterialsChoiceBill materials);

	/**
	 * 根据订单id查询采购单合计面积
	 * 
	 * @param orderId
	 * @return
	 */
	Double findSquarePurchaseTotal(Integer orderId);

	/**
	 * 根据订单ID查询订单信息
	 * 
	 * @Title: getOrder
	 * @Description: TODO
	 * @param @param orderId
	 * @param @return
	 * @return BizMaterialsChoiceBill
	 * @author ZhangTongWei
	 * @throws
	 */
	BizMaterialsChoiceBill getOrder(Integer orderId);

}