package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.PurchaseTwoCode;
import cn.damei.entity.mobile.Manager.MaterialManagement;
import cn.damei.entity.mobile.Manager.Purchase;
import cn.damei.entity.mobile.Manager.PurchaseMainMate;
import cn.damei.entity.mobile.Manager.OrderMainMate;
import cn.damei.entity.mobile.Manager.PurchasePic;
import cn.damei.entity.mobile.home.ViewLog;

/**
 * 材料管理
 * @author Administrator
 *
 */
@MyBatisDao
public interface MaterialManagementDao extends CrudDao2<MaterialManagement> {

	//通过项目经理id查询项目经理下所有的订单
	List<MaterialManagement> findOrderByItemManagerId(Integer itemManagerId);

	//通过订单id查询订单
	MaterialManagement findOrderById(int id);


	String findPhoneById(Integer itemManagerId);
	
	//通过订单id查询订单主材表的墙砖
	List<OrderMainMate> findWallByOrderId(int id);
	//通过订单id查询订单主材表的地砖
	List<OrderMainMate> findFloorByOrderId(int id);
	//采购单编码
	PurchaseTwoCode getCode();
	//更新采购单编码
	void updateCode(PurchaseTwoCode code);
	//保存采购单
	Integer savePurchase(Purchase purchase);
	//通过订单主材表的id查询订单主材表
	OrderMainMate findOrderMainMateById(int id);
	//保存到采购单主料表
	void savePurchaseMainMate(PurchaseMainMate purchaseMainMate);
	//保存图片
	void saveMainPic(PurchasePic purchasePic);
	//根据订单id查询采购单
	List<Purchase> findPurchaseByOrderId(int orderId);
	//根据采购单编码查询采购单
	Purchase findPurchaseByPurchaseCode(Integer purchaseId);
	//根据采购单id查询采购单主材表的墙砖
	List<PurchaseMainMate> findWallByPurchaseId(Integer id);
	//根据采购单id查询采购单主材表的地砖
	List<PurchaseMainMate> findfloorByPurchaseId(Integer id);
	//根据采购单id查询采购单图片
	List<PurchasePic> findPurchasePicByPurchaseId(Integer id);
	//查询采购单状态
	String findStatus(String status);

	//批量更新采购单主材表
	void savePurchaseMainMateAll(List<PurchaseMainMate> purchaseMainMateList);

	//批量插入申请墙地砖图片
	void saveMainPicAll(List<PurchasePic> pList);

	/**
	 * 消息是否已查看
	 * @param viewLog
	 * @return
	 */
	Integer findView(ViewLog viewLog);

	/**
	 * 如果未读则插入已读信息
	 * @param viewLog
	 */
	void insertView(ViewLog viewLog);

	/**
	 * 查询该订单最新一次申请墙地砖的时间是否间隔有5分钟 并且是否已读
	 * @param purchase
	 * @return
	 */
	Purchase findViewAndTime(Purchase purchase);

	/**
	 *  查询采购单(状态以及订单小区门牌号)
	 * @param purchaseId
	 * @return
	 */
	MaterialManagement findOrderByPurchaseId(Integer purchaseId);

}
