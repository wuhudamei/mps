
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialsChoiceBill;


@MyBatisDao
public interface BizMaterialsChoiceBillDao extends CrudDao2<BizMaterialsChoiceBill> {


	BizMaterialsChoiceBill findorderAndMaterialsMessage(String orderNumber);


	Integer findOrderIdMessage(String orderNumber);


	void deleteMaterialsChoiceBill(Integer id);


	BizMaterialsChoiceBill findOrder(Integer orderId);


	BizMaterialsChoiceBill findWallFloorTileSquareBudgetAllCount(String orderNumber);


	void updateMaterialsChoiceBill(BizMaterialsChoiceBill materials);


	Double findSquarePurchaseTotal(Integer orderId);


	BizMaterialsChoiceBill getOrder(Integer orderId);

}