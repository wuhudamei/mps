package cn.damei.service.mobile.Manager;

import java.util.Date;
import java.util.List;

import cn.damei.entity.mobile.Manager.PurchaseTwoCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.MaterialManagementDao;
import cn.damei.entity.mobile.Manager.MaterialManagement;
import cn.damei.entity.mobile.Manager.Purchase;
import cn.damei.entity.mobile.Manager.PurchaseMainMate;
import cn.damei.entity.mobile.Manager.OrderMainMate;
import cn.damei.entity.mobile.Manager.PurchasePic;
import cn.damei.entity.mobile.home.ViewLog;


@Service
@Transactional(readOnly = true)
public class MaterialManagementService extends CrudService2<MaterialManagementDao,MaterialManagement>{


	public List<MaterialManagement> findOrderByItemManagerId(Integer itemManagerId) {
		return dao.findOrderByItemManagerId(itemManagerId);
	}
	

	public MaterialManagement findOrderById(int id) {
		return dao.findOrderById(id);
	}
	

	public String findPhoneById(Integer itemManagerId) {
		
		return dao.findPhoneById(itemManagerId);
	}
	
	
	
	
	
	

	public List<OrderMainMate> findWallByOrderId(int id) {
		return dao.findWallByOrderId(id);
	}

	public List<OrderMainMate> findFloorByOrderId(int id) {
		return dao.findFloorByOrderId(id);
	}
	

	public PurchaseTwoCode getCode() {
		return dao.getCode();
	}

	@Transactional(readOnly=false)
	public void updateCode(PurchaseTwoCode code) {
		dao.updateCode(code);
	}
	

	@Transactional(readOnly=false)
	public Integer savePurchase(Purchase purchase) {
		return dao.savePurchase(purchase);
	}


	public OrderMainMate findOrderMainMateById(int id) {
		return dao.findOrderMainMateById(id);
	}
	

	@Transactional(readOnly = false)
	public void savePurchaseMainMate(PurchaseMainMate purchaseMainMate) {
		dao.savePurchaseMainMate(purchaseMainMate);
		
	}

	@Transactional(readOnly = false)
	public void saveMainPic(PurchasePic purchasePic) {
		dao.saveMainPic(purchasePic);
		
	}
	

	public List<Purchase> findPurchaseByOrderId(int orderId) {
		return dao.findPurchaseByOrderId(orderId);
	}


	public Purchase findPurchaseByPurchaseCode(Integer purchaseId) {
		return dao.findPurchaseByPurchaseCode(purchaseId);
	}
	

	public List<PurchaseMainMate> findWallByPurchaseId(Integer id) {
		return dao.findWallByPurchaseId(id);
	}

	public List<PurchaseMainMate> findfloorByPurchaseId(Integer id) {
		return dao.findfloorByPurchaseId(id);
	}

	public List<PurchasePic> findPurchasePicByPurchaseId(Integer id) {
		return dao.findPurchasePicByPurchaseId(id);
	}


	public String findStatus(String status) {
		return dao.findStatus(status);
	}


	@Transactional(readOnly = false)
	public void savePurchaseMainMateAll(List<PurchaseMainMate> purchaseMainMateList) {
		dao.savePurchaseMainMateAll(purchaseMainMateList);
	}


	@Transactional(readOnly = false)
	public void saveMainPicAll(List<PurchasePic> pList) {
		dao.saveMainPicAll(pList);
		
	}


	public Integer findView(Integer id, String type, String phone, Integer employeeId) {
		
		ViewLog viewLog = new ViewLog();
		viewLog.setBusinessIdInt(id);
		viewLog.setBusinessType(type);
		viewLog.setBusinessViewerOnlyMark(phone);
		viewLog.setBusinessViewerEmployeeId(employeeId);
		return dao.findView(viewLog);
	}


	@Transactional(readOnly=false)
	public void insertView(Integer id, String type, String phone, Integer employeeId) {
		
		Date date = new Date();
		ViewLog viewLog = new ViewLog();
		viewLog.setBusinessIdInt(id);
		viewLog.setBusinessType(type);
		viewLog.setBusinessViewDatetime(date);
		viewLog.setBusinessViewerOnlyMark(phone);
		viewLog.setBusinessViewerEmployeeId(employeeId);
		viewLog.setCreateBy(employeeId);
		viewLog.setCreateDate(date);
		viewLog.setUpdateBy(employeeId);
		viewLog.setUpdateDate(date);
		viewLog.setDelFlag("0");
		dao.insertView(viewLog);
		
	}


	public Purchase findViewAndTime(int id, Integer managerId, String phone) {
		Purchase purchase = new Purchase();
		purchase.setOrderId(id);
		purchase.setApplyEmployee(managerId);
		purchase.setReceiverPhone(phone);
		return dao.findViewAndTime(purchase);
	}


	public MaterialManagement findOrderByPurchaseId(Integer purchaseId) {
		return dao.findOrderByPurchaseId(purchaseId);
	}

}
