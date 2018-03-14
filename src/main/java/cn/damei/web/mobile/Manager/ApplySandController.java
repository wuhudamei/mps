package cn.damei.web.mobile.Manager;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.config.Global;
import cn.damei.common.constantUtils.BusinessViewLogConstantUtil;
import cn.damei.common.constantUtils.PurchaseConstantUtil;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Manager.Supplier;
import cn.damei.service.mobile.Manager.ApplySandService;
import cn.damei.entity.mobile.Manager.SwitchPanelOrderVo;
import cn.damei.entity.mobile.Manager.AuxiliaryVo;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.Purchase;
import cn.damei.entity.mobile.Manager.AppOrder;
import cn.damei.service.mobile.Manager.AppOrderService;
import cn.damei.dao.mobile.home.HomeReportDao;
import cn.damei.entity.mobile.home.ViewLog;



@Controller
@RequestMapping(value = "${adminPath}/app/manager/applySand")
public class ApplySandController {

	@Autowired
	private ApplySandService applySandService;
	@Autowired
	private AppOrderService appOrderService;
	@Autowired
	private HomeReportDao logDao;


	@RequestMapping(value = "orderList")
	public String orderList(Model model, HttpServletRequest request) {


		Manager manager = SessionUtils.getManagerSession(request);
		

		List<SwitchPanelOrderVo> list = null;
		if (manager != null && null != manager.getId()) {
			list = applySandService.getOrderListForSandByManagerId(manager.getId());
		} else{
			model.addAttribute("error", "出现了某些不可预估的错误 ,请联系管理员");
		}

		model.addAttribute("list", list);
		

		return "mobile/modules/Manager/sand/sandApplyList";
	}


	@RequestMapping(value = "applySand_data_check_ajax")
	public @ResponseBody String applySandDataCheckAjax(String orderId,HttpServletRequest request){
		
		String result = "0";

		if(StringUtils.isBlank(orderId)){

			result = "1";
			return result;
		}

		Manager manager = SessionUtils.getManagerSession(request);
		if(null==manager){

			result = "2";
			return result;
		}
		


		Integer count = applySandService.findQcBillAcceptStatus(Integer.valueOf(orderId));
		if(null==count || count>0){
			result = "3";
			return result;
		}
		

		Purchase purchase = applySandService.selectPurchaseByOrderIdNewOne(Integer.valueOf(orderId),manager);
		

		if (null != purchase){

			if(purchase.getApplyTime().getTime()+300*1000 > new Date().getTime()){

				result = "5";
				return result;
			}

		}
		return result;
		
	}

	@RequestMapping(value = "sandApply")
	public String sandApply(Integer orderId, Model model, HttpServletRequest request) throws IOException {


		Manager manager = SessionUtils.getManagerSession(request);
		

		Purchase purchase = applySandService.selectPurchaseByOrderIdNewOne(orderId,manager);
		

		if (null != purchase){
			

			if(purchase.getApplyTime().getTime()+300*1000 > new Date().getTime()){

				return "redirect:" + Global.getAdminPath() + "/app/manager/applySand/orderList?timeError=1";
			}

		}
		

		List<Supplier> list = null;
		if(null!=orderId && orderId>0){
			list = applySandService.findSupplierAndGoods(orderId);
		}
		

		AppOrder order = appOrderService.getOrder(orderId);


		String baseUrl = PicRootName.picPrefixName();
		
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("list", list);
		model.addAttribute("manager", manager);
		model.addAttribute("order", order);
		return "mobile/modules/Manager/sand/sandGet";
	}

	

	@RequestMapping(value = "saveSand")
	public @ResponseBody String saveSand(String orderId,String totalCount,String totalMoney, String txtBeginDate, String remark, 
			String supplierId,String isElevator,String floorNumber,
			String[] auxiliaryMaterialsId,String[] laborPrice,String[] supplierPrice,String[] wangzhenPrice,String[] auxiliaryMaterialsNo, String[] goodCount,HttpServletRequest request){
		
		String result = "0";

		if(StringUtils.isBlank(orderId)){

			result = "1";
			return result;
		}

		Manager manager = SessionUtils.getManagerSession(request);
		if(null==manager){

			result = "2";
			return result;
		}
		


		Integer count = applySandService.findQcBillAcceptStatus(Integer.valueOf(orderId));
		if(null==count || count>0){
			result = "11";
			return result;
		}
		

		Purchase purchase = applySandService.selectPurchaseByOrderIdNewOne(Integer.valueOf(orderId),manager);
		

		if (null != purchase){

			if(purchase.getApplyTime().getTime()+300*1000 > new Date().getTime()){

				result = "4";
				return result;
			}

		}
		

		if(StringUtils.isBlank(totalCount) || Integer.valueOf(totalCount)<1){

			result = "5";
			return result;
		}
		
		if(StringUtils.isBlank(totalMoney) || Double.parseDouble(totalMoney)<0.01){

			result = "6";
			return result;
		}
		
		if(StringUtils.isBlank(supplierId) || Integer.valueOf(supplierId)<1){

			result = "7";
			return result;
		}
		

		

		Purchase purcharse = applySandService.savePurchase(Integer.valueOf(orderId),totalCount,totalMoney,txtBeginDate,remark,supplierId,isElevator,floorNumber,manager,PurchaseConstantUtil.PURCHASE_TYPE_6,PurchaseConstantUtil.PURCHASE_AUXILIARY_STATUS_10,PurchaseConstantUtil.PURCHASE_DEL_FLAG_NO_0);
		if(null==purcharse || null==purcharse.getId() || purcharse.getId()<1){

			result = "9";
			return result;
		}
		

		Integer receivedAuxiMateCount = 0;
		boolean flag = applySandService.savePurchaseAuxiMate(auxiliaryMaterialsId,auxiliaryMaterialsNo,laborPrice,supplierPrice,wangzhenPrice,goodCount,orderId,purcharse.getId(),manager,PurchaseConstantUtil.PURCHASE_DEL_FLAG_NO_0,PurchaseConstantUtil.PURCHASE_SAND_IS_SAND_CEMENT_YES_1,PurchaseConstantUtil.PURCHASE_SUBMMIT_STATUS_YES,receivedAuxiMateCount);
		if(!flag){

			result = "10";
			return result;
		}
		
		return result;
		
	}



	@RequestMapping(value = "sandRecord")
	public String sandRecord(Integer orderId, Model model) {


		AppOrder order = appOrderService.getOrder(orderId);


		List<Purchase> purchaseList =null;
		purchaseList= applySandService.selectPurchaseByOrderId(orderId);
	
		model.addAttribute("order", order);
		model.addAttribute("purchaseList", purchaseList);
	
		return "mobile/modules/Manager/sand/sandApplyDetailsList";
	}
	
	

	@RequestMapping(value = "sandRecordDetails")
	public String sandRecordDetails(Integer orderId, Integer purchaseId,Model model, HttpServletRequest request) throws IOException {


		Manager manager = SessionUtils.getManagerSession(request);
		

		ViewLog log = new ViewLog();

		log.setBusinessIdInt(purchaseId);
		log.setBusinessType(BusinessViewLogConstantUtil.BUSINESS_VIEW_LOG_BUSINESS_TYPE_401);
		log.setBusinessViewerOnlyMark(SessionUtils.getManagerSession(request).getPhone());
		Integer integer = logDao.findView(log);
		

		if (null == integer || integer == 0) {
			Date date = new Date();
			log.setBusinessType(BusinessViewLogConstantUtil.BUSINESS_VIEW_LOG_BUSINESS_TYPE_401);
			log.setBusinessIdInt(purchaseId);
			log.setBusinessViewDatetime(date);
			log.setBusinessViewerEmployeeId(manager.getId());
			log.setBusinessViewerOnlyMark(manager.getPhone());
			log.setCreateDate(date);
			log.setUpdateDate(date);
			log.setDelFlag(BusinessViewLogConstantUtil.BUSINESS_VIEW_LOG_DEL_FLAG_NO_0);
			logDao.insertView(log);
		}
		

		AppOrder order = appOrderService.getOrder(orderId);


		Purchase purchase = applySandService.findPurchase(purchaseId);
		

		List<AuxiliaryVo> goodsList = applySandService.findPurchaseGoods(purchaseId);
		

		String baseUrl = PicRootName.picPrefixName();
		
		model.addAttribute("order", order);
		model.addAttribute("purchase", purchase);
		model.addAttribute("goodsList", goodsList);
		model.addAttribute("baseUrl", baseUrl);
		
		return "mobile/modules/Manager/sand/sandApplyDetails";
	}

}