package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.MaterialManagement;
import cn.damei.entity.mobile.Manager.OrderMainMate;
import cn.damei.entity.mobile.Manager.Purchase;
import cn.damei.entity.mobile.Manager.WallRecheckManage;

/**
 * 材料管理 申请墙地砖
 * 
 * @author Administrator
 *
 */
@MyBatisDao
public interface WallAndFloorDao {

	/**
	 * 通过项目经理id查询项目经理下所有的订单
	 * 
	 * @param itemManagerId
	 * @return
	 */
	List<MaterialManagement> findOrderList(Integer itemManagerId);

	/**
	 * 申请墙地砖5分钟校验和是否查看详情
	 * 
	 * @param orderId
	 * @return
	 */
	Purchase findPurchaseViewAndTimeMessage(Integer orderId);

	/**
	 * 根据订单id查询订单信息
	 * 
	 * @param orderId
	 * @return
	 */
	MaterialManagement findOrderMessage(Integer orderId);

	/**
	 * 通过订单id查询订单主材表的墙砖
	 * 
	 * @param orderId
	 * @return
	 */
	List<OrderMainMate> findWallByOrderId(Integer orderId);

	/**
	 * 通过订单id查询订单主材表的地砖
	 * 
	 * @param orderId
	 * @return
	 */
	List<OrderMainMate> findFloorByOrderId(Integer orderId);

	/**
	 * 通过订单id查询订单主材表的材料
	 * 
	 * @param orderId
	 * @return
	 */
	List<OrderMainMate> findWallAndFloorByOrderId(Integer orderId);

	/**
	 * 查询采购单面积之和（有效面积）
	 * 
	 * @param orderId
	 * @return
	 */
	Double findSquarePurchaseTotal(Integer orderId);

	/**
	 * 查询预算面积
	 * 
	 * @Title: findOrderMessage1
	 * @Description: TODO
	 * @param @param orderId
	 * @param @return
	 * @return MaterialManagement
	 * @author ZhangTongWei
	 * @throws
	 */
	MaterialManagement findOrderMessage1(Integer orderId);

	/**
	 * 更新订单墙地砖表（已申请商品数量）
	 * 
	 * @param orderId
	 */
	void updateOrderWallFloorTile(Integer orderId);

	/**
	 * 根据订单id查询实测面积
	 * 
	 * @Title: findSquareActual
	 * @Description: TODO
	 * @param @param orderId
	 * @param @return
	 * @return Double
	 * @author ZhangTongWei
	 * @throws
	 */
	Double findSquareActual(Integer orderId);

	WallRecheckManage findWallRecheckManage(Integer orderId);

	Double querySquate(Integer parseInt);

}
