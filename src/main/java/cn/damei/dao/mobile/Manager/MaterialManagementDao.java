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


@MyBatisDao
public interface MaterialManagementDao extends CrudDao2<MaterialManagement> {


	List<MaterialManagement> findOrderByItemManagerId(Integer itemManagerId);


	MaterialManagement findOrderById(int id);


	String findPhoneById(Integer itemManagerId);
	

	List<OrderMainMate> findWallByOrderId(int id);

	List<OrderMainMate> findFloorByOrderId(int id);

	PurchaseTwoCode getCode();

	void updateCode(PurchaseTwoCode code);

	Integer savePurchase(Purchase purchase);

	OrderMainMate findOrderMainMateById(int id);

	void savePurchaseMainMate(PurchaseMainMate purchaseMainMate);

	void saveMainPic(PurchasePic purchasePic);

	List<Purchase> findPurchaseByOrderId(int orderId);

	Purchase findPurchaseByPurchaseCode(Integer purchaseId);

	List<PurchaseMainMate> findWallByPurchaseId(Integer id);

	List<PurchaseMainMate> findfloorByPurchaseId(Integer id);

	List<PurchasePic> findPurchasePicByPurchaseId(Integer id);

	String findStatus(String status);


	void savePurchaseMainMateAll(List<PurchaseMainMate> purchaseMainMateList);


	void saveMainPicAll(List<PurchasePic> pList);


	Integer findView(ViewLog viewLog);


	void insertView(ViewLog viewLog);


	Purchase findViewAndTime(Purchase purchase);


	MaterialManagement findOrderByPurchaseId(Integer purchaseId);

}
