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

/**
 * 材料管理Service
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly = true)
public class MaterialManagementService extends CrudService2<MaterialManagementDao,MaterialManagement>{

	//通过项目经理id查询项目经理下所有的订单
	public List<MaterialManagement> findOrderByItemManagerId(Integer itemManagerId) {
		return dao.findOrderByItemManagerId(itemManagerId);
	}
	
	//通过订单id查询订单
	public MaterialManagement findOrderById(int id) {
		return dao.findOrderById(id);
	}
	

	public String findPhoneById(Integer itemManagerId) {
		
		return dao.findPhoneById(itemManagerId);
	}
	
	
	
	
	
	
	//通过订单id查询订单主材表的墙砖
	public List<OrderMainMate> findWallByOrderId(int id) {
		return dao.findWallByOrderId(id);
	}
	//通过订单id查询订单主材表的地砖
	public List<OrderMainMate> findFloorByOrderId(int id) {
		return dao.findFloorByOrderId(id);
	}
	
	//采购单编码
	public PurchaseTwoCode getCode() {
		return dao.getCode();
	}
	//更新采购单编码
	@Transactional(readOnly=false)
	public void updateCode(PurchaseTwoCode code) {
		dao.updateCode(code);
	}
	
	//保存采购单
	@Transactional(readOnly=false)
	public Integer savePurchase(Purchase purchase) {
		return dao.savePurchase(purchase);
	}

	//通过订单主材表的id查询订单主材表
	public OrderMainMate findOrderMainMateById(int id) {
		return dao.findOrderMainMateById(id);
	}
	
	//保存到采购单主料表
	@Transactional(readOnly = false)
	public void savePurchaseMainMate(PurchaseMainMate purchaseMainMate) {
		dao.savePurchaseMainMate(purchaseMainMate);
		
	}
	//保存图片
	@Transactional(readOnly = false)
	public void saveMainPic(PurchasePic purchasePic) {
		dao.saveMainPic(purchasePic);
		
	}
	
	//根据订单id查询采购单
	public List<Purchase> findPurchaseByOrderId(int orderId) {
		return dao.findPurchaseByOrderId(orderId);
	}

	//根据采购单编码查询采购单
	public Purchase findPurchaseByPurchaseCode(Integer purchaseId) {
		return dao.findPurchaseByPurchaseCode(purchaseId);
	}
	
	//根据采购单id查询采购单主材表的墙砖
	public List<PurchaseMainMate> findWallByPurchaseId(Integer id) {
		return dao.findWallByPurchaseId(id);
	}
	//根据采购单id查询采购单主材表的地砖
	public List<PurchaseMainMate> findfloorByPurchaseId(Integer id) {
		return dao.findfloorByPurchaseId(id);
	}
	//根据采购单id查询采购单图片
	public List<PurchasePic> findPurchasePicByPurchaseId(Integer id) {
		return dao.findPurchasePicByPurchaseId(id);
	}

	//查询采购单状态
	public String findStatus(String status) {
		return dao.findStatus(status);
	}

	/**
	 * 批量更新采购单主材表
	 * @param purchaseMainMateList
	 */
	@Transactional(readOnly = false)
	public void savePurchaseMainMateAll(List<PurchaseMainMate> purchaseMainMateList) {
		dao.savePurchaseMainMateAll(purchaseMainMateList);
	}

	/**
	 * 批量插入申请墙地砖图片
	 * @param pList
	 */
	@Transactional(readOnly = false)
	public void saveMainPicAll(List<PurchasePic> pList) {
		dao.saveMainPicAll(pList);
		
	}

	/**
	 * 消息是否已查看
	 * @param viewLog
	 * @return
	 */
	public Integer findView(Integer id, String type, String phone, Integer employeeId) {
		
		ViewLog viewLog = new ViewLog();
		viewLog.setBusinessIdInt(id);
		viewLog.setBusinessType(type);
		viewLog.setBusinessViewerOnlyMark(phone);
		viewLog.setBusinessViewerEmployeeId(employeeId);
		return dao.findView(viewLog);
	}

	/**
	 * 如果未读则插入已读信息
	 * @param id
	 * @param viewLogManagerWallandfloor
	 * @param phone
	 * @param id2
	 */
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

	/**
	 * 查询该订单最新一次申请墙地砖的时间是否间隔有5分钟 并且是否已读
	 * @param id
	 * @param id2
	 * @param phone
	 * @return
	 */
	public Purchase findViewAndTime(int id, Integer managerId, String phone) {
		Purchase purchase = new Purchase();
		purchase.setOrderId(id);
		purchase.setApplyEmployee(managerId);
		purchase.setReceiverPhone(phone);
		return dao.findViewAndTime(purchase);
	}

	/**
	 * 查询采购单(状态以及订单小区门牌号)
	 * @param purchaseId
	 * @return
	 */
	public MaterialManagement findOrderByPurchaseId(Integer purchaseId) {
		return dao.findOrderByPurchaseId(purchaseId);
	}

}
