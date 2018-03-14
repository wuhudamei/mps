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

/**
 * 材料管理--申请沙子水泥
 * 
 */

@Controller
@RequestMapping(value = "${adminPath}/app/manager/applySand")
public class ApplySandController {

	@Autowired
	private ApplySandService applySandService;
	@Autowired
	private AppOrderService appOrderService;
	@Autowired
	private HomeReportDao logDao;

	/**
	 * 申请沙子水泥的订单列表页
	 * @param model
	 * @param request
	 * @param timeError  5分钟内不可重复提交
	 * @param notRead	是否已查看沙子水泥的采购单详情
	 * @return
	 */
	@RequestMapping(value = "orderList")
	public String orderList(Model model, HttpServletRequest request) {

		//3.登录的项目经理
		Manager manager = SessionUtils.getManagerSession(request);
		
		//4.查询项目经理名下的订单（状态等于200-施工中）
		List<SwitchPanelOrderVo> list = null;
		if (manager != null && null != manager.getId()) {
			list = applySandService.getOrderListForSandByManagerId(manager.getId());
		} else{
			model.addAttribute("error", "出现了某些不可预估的错误 ,请联系管理员");
		}

		model.addAttribute("list", list);
		
		//5.返回申请沙子水泥页面
		return "mobile/modules/Manager/sand/sandApplyList";
	}

	/**
	 * 申请沙子水泥数据校验
	 * @param orderId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "applySand_data_check_ajax")
	public @ResponseBody String applySandDataCheckAjax(String orderId,HttpServletRequest request){
		
		String result = "0";
		//1.判断订单ID是否为空
		if(StringUtils.isBlank(orderId)){
			//订单ID为空
			result = "1";
			return result;
		}
		//2.登录的项目经理
		Manager manager = SessionUtils.getManagerSession(request);
		if(null==manager){
			//项目经理是否登录
			result = "2";
			return result;
		}
		
		//3.判断【基装】约检节点的状态是否大于等于10-质检员已提交约检验收。
		//如果是，则不允许再申请沙子水泥
		Integer count = applySandService.findQcBillAcceptStatus(Integer.valueOf(orderId));
		if(null==count || count>0){
			result = "3";
			return result;
		}
		
		//4.查询该订单下已经申请的最新一条沙子水泥采购单
		Purchase purchase = applySandService.selectPurchaseByOrderIdNewOne(Integer.valueOf(orderId),manager);
		
		//5.如果有采购单
		if (null != purchase){
			/*if(null==purchase.getCount() || purchase.getCount()==0){
				//如果没有查看详情 则不允许申请沙子水泥,并给出提示
				result = "4";
				return result;
			}*/
			if(purchase.getApplyTime().getTime()+300*1000 > new Date().getTime()){
				//如果小于5分钟 则不允许申请沙子水泥,并给出提示
				result = "5";
				return result;
			}

		}
		return result;
		
	}
	/**
	 * 项目经理--申请沙子水泥--页面
	 * @param orderId 订单ID
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "sandApply")
	public String sandApply(Integer orderId, Model model, HttpServletRequest request) throws IOException {

		//1.登录的项目经理
		Manager manager = SessionUtils.getManagerSession(request);
		
		//2.查询该订单下已经申请的最新一条沙子水泥采购单
		Purchase purchase = applySandService.selectPurchaseByOrderIdNewOne(orderId,manager);
		
		//3.如果有采购单
		if (null != purchase){
			
			/*if(null==purchase.getCount() || purchase.getCount()==0){
				//如果没有查看详情 则不允许申请沙子水泥,并给出提示
				return "redirect:" + Global.getAdminPath() + "/app/manager/applySand/orderList?notRead=1";
			}*/
			if(purchase.getApplyTime().getTime()+300*1000 > new Date().getTime()){
				//如果小于5分钟 则不允许申请沙子水泥,并给出提示
				return "redirect:" + Global.getAdminPath() + "/app/manager/applySand/orderList?timeError=1";
			}

		}
		
		//4查询出所有的供应商及沙子水泥商品
		List<Supplier> list = null;
		if(null!=orderId && orderId>0){
			list = applySandService.findSupplierAndGoods(orderId);
		}
		
		//5.查询订单详情
		AppOrder order = appOrderService.getOrder(orderId);

		//6.图片前缀
		String baseUrl = PicRootName.picPrefixName();
		
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("list", list);
		model.addAttribute("manager", manager);
		model.addAttribute("order", order);
		return "mobile/modules/Manager/sand/sandGet";
	}

	
	/**
	 * 保存沙子水泥采购单
	 * @param orderId  订单ID
	 * @param totalCount 申请总数
	 * @param txtBeginDate	期望到场日期
	 * @param remark	备注
	 * @param supplierId	供应商ID
	 * @param quality	是否给供应商上楼费
	 * @param floorNumber	楼层
	 * @param auxiliaryMaterialsId	商品ID
	 * @param laborPrice	商品单价
	 * @param supplierPrice 供应商价格
	 * @param wangzhenPrice 网真价格
	 * @param goodCount		商品数量
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveSand")
	public @ResponseBody String saveSand(String orderId,String totalCount,String totalMoney, String txtBeginDate, String remark, 
			String supplierId,String isElevator,String floorNumber,
			String[] auxiliaryMaterialsId,String[] laborPrice,String[] supplierPrice,String[] wangzhenPrice,String[] auxiliaryMaterialsNo, String[] goodCount,HttpServletRequest request){
		
		String result = "0";
		//1.判断订单ID是否为空
		if(StringUtils.isBlank(orderId)){
			//订单ID为空
			result = "1";
			return result;
		}
		//2.登录的项目经理
		Manager manager = SessionUtils.getManagerSession(request);
		if(null==manager){
			//项目经理是否登录
			result = "2";
			return result;
		}
		
		//11.判断【基装】约检节点的状态是否大于等于10-质检员已提交约检验收。
		//如果是，则不允许再申请沙子水泥
		Integer count = applySandService.findQcBillAcceptStatus(Integer.valueOf(orderId));
		if(null==count || count>0){
			result = "11";
			return result;
		}
		
		//3.查询该订单下已经申请的最新一条沙子水泥采购单
		Purchase purchase = applySandService.selectPurchaseByOrderIdNewOne(Integer.valueOf(orderId),manager);
		
		//4.如果有采购单
		if (null != purchase){
			/*if(null==purchase.getCount() || purchase.getCount()==0){
				//如果没有查看详情 则不允许申请沙子水泥,并给出提示
				result = "3";
				return result;
			}*/
			if(purchase.getApplyTime().getTime()+300*1000 > new Date().getTime()){
				//如果小于5分钟 则不允许申请沙子水泥,并给出提示
				result = "4";
				return result;
			}

		}
		
		//5.数据是否正确
		if(StringUtils.isBlank(totalCount) || Integer.valueOf(totalCount)<1){
			//材料数量
			result = "5";
			return result;
		}
		
		if(StringUtils.isBlank(totalMoney) || Double.parseDouble(totalMoney)<0.01){
			//材料价格
			result = "6";
			return result;
		}
		
		if(StringUtils.isBlank(supplierId) || Integer.valueOf(supplierId)<1){
			//供应商
			result = "7";
			return result;
		}
		
		/*if(null!=delayTypeValue && ""!=delayTypeValue && "1".equals(delayTypeValue)){
			if(null==floorNumber || ""==floorNumber || Integer.valueOf(floorNumber)<1){
				//如果有上楼费 楼层不为空
				result = "8";
				return result;
			}
		}*/
		
		//6.保存沙子水泥采购单
		Purchase purcharse = applySandService.savePurchase(Integer.valueOf(orderId),totalCount,totalMoney,txtBeginDate,remark,supplierId,isElevator,floorNumber,manager,PurchaseConstantUtil.PURCHASE_TYPE_6,PurchaseConstantUtil.PURCHASE_AUXILIARY_STATUS_10,PurchaseConstantUtil.PURCHASE_DEL_FLAG_NO_0);
		if(null==purcharse || null==purcharse.getId() || purcharse.getId()<1){
			//采购单保存失败
			result = "9";
			return result;
		}
		
		//7.保存商品
		Integer receivedAuxiMateCount = 0;
		boolean flag = applySandService.savePurchaseAuxiMate(auxiliaryMaterialsId,auxiliaryMaterialsNo,laborPrice,supplierPrice,wangzhenPrice,goodCount,orderId,purcharse.getId(),manager,PurchaseConstantUtil.PURCHASE_DEL_FLAG_NO_0,PurchaseConstantUtil.PURCHASE_SAND_IS_SAND_CEMENT_YES_1,PurchaseConstantUtil.PURCHASE_SUBMMIT_STATUS_YES,receivedAuxiMateCount);
		if(!flag){
			//商品保存失败
			result = "10";
			return result;
		}
		
		return result;
		
	}


	/**
	 * 订单-沙子水泥采购单
	 * @param orderId 订单ID
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "sandRecord")
	public String sandRecord(Integer orderId, Model model) {

		//1.查询订单详情
		AppOrder order = appOrderService.getOrder(orderId);

		//2.根据orderId 查询沙子水泥采购单
		List<Purchase> purchaseList =null;
		purchaseList= applySandService.selectPurchaseByOrderId(orderId);
	
		model.addAttribute("order", order);
		model.addAttribute("purchaseList", purchaseList);
	
		return "mobile/modules/Manager/sand/sandApplyDetailsList";
	}
	
	

	@RequestMapping(value = "sandRecordDetails")
	public String sandRecordDetails(Integer orderId, Integer purchaseId,Model model, HttpServletRequest request) throws IOException {

		//1.登录的项目经理
		Manager manager = SessionUtils.getManagerSession(request);
		
		//2.沙子水泥采购单是否已读
		ViewLog log = new ViewLog();

		log.setBusinessIdInt(purchaseId);
		log.setBusinessType(BusinessViewLogConstantUtil.BUSINESS_VIEW_LOG_BUSINESS_TYPE_401);
		log.setBusinessViewerOnlyMark(SessionUtils.getManagerSession(request).getPhone());
		Integer integer = logDao.findView(log);
		
		//3.如果未读 ，插入已阅读log表
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
		
		//4.查询订单详情
		AppOrder order = appOrderService.getOrder(orderId);

		//5.查询沙子水泥采购单
		Purchase purchase = applySandService.findPurchase(purchaseId);
		
		//6.查询采购单商品
		List<AuxiliaryVo> goodsList = applySandService.findPurchaseGoods(purchaseId);
		
		//7.图片前缀
		String baseUrl = PicRootName.picPrefixName();
		
		model.addAttribute("order", order);
		model.addAttribute("purchase", purchase);
		model.addAttribute("goodsList", goodsList);
		model.addAttribute("baseUrl", baseUrl);
		
		return "mobile/modules/Manager/sand/sandApplyDetails";
	}

}