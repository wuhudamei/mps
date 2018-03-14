package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.MaterialManagement;
import cn.damei.entity.mobile.Manager.OrderMainMate;
import cn.damei.entity.mobile.Manager.Purchase;
import cn.damei.entity.mobile.Manager.WallRecheckManage;


@MyBatisDao
public interface WallAndFloorDao {


	List<MaterialManagement> findOrderList(Integer itemManagerId);


	Purchase findPurchaseViewAndTimeMessage(Integer orderId);


	MaterialManagement findOrderMessage(Integer orderId);


	List<OrderMainMate> findWallByOrderId(Integer orderId);


	List<OrderMainMate> findFloorByOrderId(Integer orderId);


	List<OrderMainMate> findWallAndFloorByOrderId(Integer orderId);


	Double findSquarePurchaseTotal(Integer orderId);


	MaterialManagement findOrderMessage1(Integer orderId);


	void updateOrderWallFloorTile(Integer orderId);


	Double findSquareActual(Integer orderId);

	WallRecheckManage findWallRecheckManage(Integer orderId);

	Double querySquate(Integer parseInt);

}
