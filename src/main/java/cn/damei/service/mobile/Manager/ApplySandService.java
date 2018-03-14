package cn.damei.service.mobile.Manager;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BusinessViewLogConstantUtil;
import cn.damei.common.constantUtils.OrderConstantUtil;
import cn.damei.common.constantUtils.PurchaseConstantUtil;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.dao.mobile.Manager.ApplySandDao;
import cn.damei.entity.mobile.Manager.Supplier;
import cn.damei.entity.mobile.Manager.PurchaseCode;
import cn.damei.entity.mobile.Manager.SwitchPanelOrderVo;
import cn.damei.entity.mobile.Manager.AuxiliaryVo;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.Purchase;



@Service
@Transactional(readOnly=true)
public class ApplySandService extends  CrudService2<ApplySandDao,SwitchPanelOrderVo> {
	
	
	

	public List<SwitchPanelOrderVo> getOrderListForSandByManagerId(Integer managerId){
		
		SwitchPanelOrderVo switchPanelOrderVo = new SwitchPanelOrderVo();

		switchPanelOrderVo.setItemManagerId(managerId);

		switchPanelOrderVo.setOrderStatus(OrderConstantUtil.ORDER_STATUS_CONFIRM_START_200);
		
		return  dao.getOrderListForSandByManagerId(switchPanelOrderVo);
	}
	

	public Purchase selectPurchaseByOrderIdNewOne(Integer orderId, Manager manager) {
		
		Purchase purchase = new Purchase();

		purchase.setOrderId(orderId);

		purchase.setPurchaseType(PurchaseConstantUtil.PURCHASE_TYPE_6);

		purchase.setBusinessType(BusinessViewLogConstantUtil.BUSINESS_VIEW_LOG_BUSINESS_TYPE_401);

		purchase.setBusinessViewerOnlyMark(manager.getPhone());

		purchase.setBusinessViewerEmployeeId(manager.getId());
		
		return dao.selectPurchaseByOrderIdNewOne(purchase);
	}
	

	public List<Supplier> findSupplierAndGoods(Integer orderId) {
		Supplier supplier = new Supplier();

		supplier.setOrderId(orderId);

		supplier.setStatus(PurchaseConstantUtil.PURCHASE_STATUS_YES_1);

		supplier.setDelFlag(PurchaseConstantUtil.PURCHASE_DEL_FLAG_NO_0);

		supplier.setIsSandCement(PurchaseConstantUtil.PURCHASE_SAND_IS_SAND_CEMENT_YES_1);
		
		return dao.findSupplierAndGoods(supplier);
	}
	

	@Transactional(readOnly=false)
	public Purchase savePurchase(Integer orderId, String totalCount, String totalMoney, String txtBeginDate,
			String remark, String supplierId, String isElevator, String floorNumber, Manager manager, 
			String purchaseType6, String purchaseAuxiliaryStatus10, String purchaseDelFlagNo0){
		
		Date date = new Date();
		
		Purchase purcharse = new Purchase();

		purcharse.setOrderId(orderId);

		purcharse.setPurchaseCode(purchaseCode());

		purcharse.setPurchaseType(purchaseType6);

		purcharse.setApplyReceiveTime(DateUtils.parseDate(txtBeginDate));

		purcharse.setApplyEmployee(manager.getId());

		purcharse.setApplyTime(date);

		purcharse.setRemarks(remark);

		purcharse.setStatus(purchaseAuxiliaryStatus10);

		purcharse.setTotalPrice(Double.parseDouble(totalMoney));

		purcharse.setCreateDate(date);

		purcharse.setUpdateDate(date);

		purcharse.setDelFlag(purchaseDelFlagNo0);

		purcharse.setSupplierId(Integer.valueOf(supplierId));

		purcharse.setIsElevator(isElevator);

		purcharse.setUpstairsPay(null);

		if(StringUtils.isNotBlank(floorNumber) && Integer.valueOf(floorNumber)>0 ){
			purcharse.setUpstairsFloor(Integer.valueOf(floorNumber));
		}
		try {
			dao.savePurchase(purcharse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return purcharse;
		
	}
	

	@Transactional(readOnly=false)
	public boolean savePurchaseAuxiMate(String[] auxiliaryMaterialsId,String[] auxiliaryMaterialsNo, String[] laborPrice,
			String[] supplierPrice,String[] wangzhenPrice,String[] goodCount, String orderId, Integer purchaseId, Manager manager, String purchaseDelFlagNo0, 
			String purchaseSandIsSandCementYes1, String submmitStatus, Integer receivedAuxiMateCount) {
		
		boolean flag = false;
		List<AuxiliaryVo> list = new ArrayList<AuxiliaryVo>(); 
		Date date = new Date();
		if(null!=auxiliaryMaterialsId && auxiliaryMaterialsId.length>0){
			for(int v=0; v<auxiliaryMaterialsId.length; v++){
				if(null!=goodCount[v] && goodCount[v] != "" && goodCount[v] != "," && !goodCount[v].equals("0")){
					
					AuxiliaryVo AuxiliaryVo = new AuxiliaryVo();

					AuxiliaryVo.setPurchaseId(purchaseId);

					AuxiliaryVo.setAuxiMateCode(auxiliaryMaterialsNo[v]);

					AuxiliaryVo.setAuxiMateCount(Integer.valueOf(goodCount[v]));

					AuxiliaryVo.setSubmmitStatus(submmitStatus);

					AuxiliaryVo.setCreateDate(date);

					AuxiliaryVo.setUpdateDate(date);

					AuxiliaryVo.setDelFlag(purchaseDelFlagNo0);

					AuxiliaryVo.setOrderId(Integer.valueOf(orderId));

					AuxiliaryVo.setReceivedAuxiMateCount(receivedAuxiMateCount);

					AuxiliaryVo.setOwedAuxiMateCount(Integer.valueOf(goodCount[v]));

					AuxiliaryVo.setPrice(Double.parseDouble(laborPrice[v]));

					AuxiliaryVo.setSupplierPrice(Double.parseDouble(supplierPrice[v]));

					AuxiliaryVo.setWangzhenPrice(Double.parseDouble(wangzhenPrice[v]));

					AuxiliaryVo.setIsSandCement(purchaseSandIsSandCementYes1);
					
					list.add(AuxiliaryVo);
				}
			}
		}

		if(null!=list && list.size()>0){
			flag = dao.savePurchaseAuxiMate(list);
		}
		

		if(!flag){
			dao.deletePurchase(purchaseId);
		}
		return flag;
	}
	

	public List<Purchase> selectPurchaseByOrderId(Integer orderId) {
		
		Purchase purchase = new Purchase();

		purchase.setOrderId(orderId);

		purchase.setPurchaseType(PurchaseConstantUtil.PURCHASE_TYPE_6);

		purchase.setDelFlag(PurchaseConstantUtil.PURCHASE_DEL_FLAG_NO_0);
		
		return dao.selectPurchaseByOrderId(purchase);
	}


	public Purchase findPurchase(Integer purchaseId) {
		return dao.findPurchase(purchaseId);
	}


	public List<AuxiliaryVo> findPurchaseGoods(Integer purchaseId) {
		
		AuxiliaryVo auxiliaryVo = new AuxiliaryVo();

		auxiliaryVo.setIsSandCement(PurchaseConstantUtil.PURCHASE_SAND_IS_SAND_CEMENT_YES_1);

		auxiliaryVo.setPurchaseId(purchaseId);

		auxiliaryVo.setDelFlag(PurchaseConstantUtil.PURCHASE_DEL_FLAG_NO_0);
		
		return dao.findPurchaseGoods(auxiliaryVo);
	}
	
	

	public String purchaseCode() {

		String purchaseCode = ConstantUtils.PURCHASE_NUMBER;

		StringBuilder builder = new StringBuilder();


		PurchaseCode purchaseObj = dao.getCode();

		purchaseObj.setPurchaseCode(String.valueOf(Integer.parseInt(purchaseObj.getPurchaseCode()) + 1));

		dao.updateCode(purchaseObj);


		String format = new SimpleDateFormat("yyyyMMdd").format(purchaseObj.getAuxiliaryDate());

		String code = purchaseObj.getPurchaseCode();

		builder.append(purchaseCode).append(format);

		if (code.length() == 1) {

			builder.append("000").append(code);

		} else if (code.length() == 2) {

			builder.append("00").append(code);
		} else if (code.length() == 3) {
			builder.append("0").append(code);
		} else if (code.length() == 4) {
			builder.append(code);
		}


		return builder.toString();
	}


	public Integer findQcBillAcceptStatus(Integer orderId) {
		return dao.findQcBillAcceptStatus(orderId);
	}

	

	

	
}	