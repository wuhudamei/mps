package cn.damei.web.mobile.Manager;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import cn.damei.common.utils.*;
import cn.damei.entity.mobile.Manager.PurchaseTwoCode;
import cn.damei.entity.modules.BizMaterialsStandardShippingFees;
import cn.damei.service.modules.BizMaterialsStandardShippingFeesService;
import cn.damei.service.modules.BizPhoneMsgService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.damei.common.config.Global;
import cn.damei.common.constantUtils.BusinessUrgeConstantUtil;
import cn.damei.common.Base64Util;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.MaterialManagement;
import cn.damei.entity.mobile.Manager.OrderMainMate;
import cn.damei.entity.mobile.Manager.Purchase;
import cn.damei.entity.mobile.Manager.PurchaseMainMate;
import cn.damei.entity.mobile.Manager.PurchasePic;
import cn.damei.service.mobile.Manager.MaterialManagementService;
import cn.damei.entity.modules.BizBusinessUrge;
import cn.damei.service.modules.BizBusinessUrgeService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizMessagegroup;
import cn.damei.service.modules.BizMessagegroupService;
import cn.damei.entity.modules.Order2;
import cn.damei.service.modules.OrderService2;



@Controller
@RequestMapping(value="${adminPath}/app/manager")
public class MaterialManagementController {
	
	@Autowired
	private MaterialManagementService materialManagementService;
	@Autowired
	private OrderService2 orderService2;
	@Autowired
	private BizBusinessUrgeService bizBusinessUrgeService;
	@Autowired
	private BizMaterialsStandardShippingFeesService bizMaterialsStandardShippingFeesService;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;

	private Logger logger  = org.slf4j.LoggerFactory.getLogger(MaterialManagementController.class);

	@RequestMapping(value={"meterialManagementList",""})
	public String appOrderList(MaterialManagement materialManagement,HttpServletRequest request, Model model){
	String index = (String)request.getSession().getAttribute("index");
	request.getSession().removeAttribute("index");
				if(null!=index){
					if("0".equals(index)){

						return "mobile/modules/Manager/materialManagement/materialManagement";	
					}else if("1".equals(index)){

						return "mobile/modules/Manager/manager_index";
					}else{
						
						logger.warn("辅料申请路径参数index有误  index:"+index);
						return "mobile/modules/Manager/manager_index";
					}
					
				}else{
					
					logger.warn("辅料申请路径参数为空");
					return "mobile/modules/Manager/materialManagement/materialManagement";
				}
		
		
	}
	

	@RequestMapping(value={"applyMainIngredient",""})
	public String applyMainIngredient(MaterialManagement materialManagement,String timeForbidden,String isRead, HttpServletRequest request, Model model){

		Manager manager = (Manager)request.getSession().getAttribute("manager");
		materialManagement.setItemManagerId(manager.getId());
		

		List<MaterialManagement> order = materialManagementService.findOrderByItemManagerId(materialManagement.getItemManagerId());
		
		
		
		model.addAttribute("timeForbidden",timeForbidden );
		model.addAttribute("isRead",isRead );
		model.addAttribute("order",order );
		return "mobile/modules/Manager/materialManagement/wall_apply";
	}
	

	

	@RequestMapping(value={"wallAndFloorBrick",""})
	public String wallAndFloorBrick(int id, HttpServletRequest request, Model model){
		
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		Integer purchaseCount = 0;

		Purchase purchase = materialManagementService.findViewAndTime(id,manager.getId(),manager.getPhone());
		if(null!=purchase){
			if(purchase.getApplyTime().getTime()+300*1000 > new Date().getTime()){

				return "redirect:"+Global.getAdminPath()+"/app/manager/applyMainIngredient?timeForbidden=1";
			}
			
			if(purchase.getCount()==0){

				return "redirect:"+Global.getAdminPath()+"/app/manager/applyMainIngredient?isRead=0";
			}
			purchaseCount = purchase.getPurchaseCount();
		}
		

		MaterialManagement materialManagement = materialManagementService.findOrderById(id);
		
		materialManagement.setPhone(manager.getPhone());
		materialManagement.setItemManager(manager.getRealname());
		

		List<OrderMainMate> wall = materialManagementService.findWallByOrderId(id);

		List<OrderMainMate> floor = materialManagementService.findFloorByOrderId(id);
		
		int wallLength = wall.size();
		int floorLength = floor.size();
		
		model.addAttribute("materialManagement", materialManagement);
		model.addAttribute("wall", wall);
		model.addAttribute("floor", floor);
		model.addAttribute("wallLength", wallLength);
		model.addAttribute("floorLength", floorLength);
		model.addAttribute("allLength",wallLength+floorLength+1);
		model.addAttribute("purchaseCount", purchaseCount);
		
		
		return "mobile/modules/Manager/materialManagement/wall_submit";
	}
	
	
	@Autowired
	private BizMessagegroupService bizMessagegroupService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	

	@RequestMapping(value="applyWallAndFloor" ,method=RequestMethod.POST)
	public @ResponseBody String applyWallAndFloor(String[] id,String[] applyCounta,String[] remarks,String orderId,String inputDate,String getInfo,String[] photo, HttpServletRequest request) throws UnsupportedEncodingException{
		
		Date date = new Date();
		Manager manager = (Manager)request.getSession().getAttribute("manager");

		Purchase purchase2 = materialManagementService.findViewAndTime(Integer.valueOf(orderId),manager.getId(),manager.getPhone());
		if(null!=purchase2){
			if(purchase2.getApplyTime().getTime()+300*1000 > new Date().getTime()){

				return "lessTime";
			}
			if(purchase2.getCount()==0){

				return "missingDetails";
			}
		}
		

		Purchase purchase = new Purchase();
		purchase.setOrderId(Integer.valueOf(orderId));
		purchase.setPurchaseCode(purchaseCode());
		purchase.setPurchaseType("5");
		purchase.setApplyReceiveTime(DateUtils.parseDate(inputDate));
		purchase.setApplyEmployee(SessionUtils.getManagerSession(request).getId());
		purchase.setApplyTime(new Date());
		purchase.setStatus("10");
		purchase.setRemarks(getInfo);
		purchase.setCreateDate(date);
		purchase.setUpdateDate(date);
		purchase.setDelFlag("0");
		materialManagementService.savePurchase(purchase);
		

		
		List<PurchaseMainMate> purchaseMainMateList = new ArrayList<PurchaseMainMate>();
		

		if(null != applyCounta && applyCounta.length>0){
			
			for(int v=0;v<applyCounta.length;v++){
				if(applyCounta[v].length()!=0 && applyCounta[v] != null && applyCounta[v] !="" && applyCounta[v] != "," && !applyCounta[v].equals("0") && !applyCounta[v].equals("0.0") && !applyCounta[v].equals(".")){
					

					int pid = Integer.valueOf(id[v]);
					OrderMainMate orderMainMate = materialManagementService.findOrderMainMateById(pid);
					
					PurchaseMainMate purchaseMainMate = new PurchaseMainMate();
					
					purchaseMainMate.setPurchaseId(purchase.getId());
					purchaseMainMate.setMainMateType(orderMainMate.getMainMateType());
					purchaseMainMate.setPosition(orderMainMate.getPosition());
					purchaseMainMate.setBrandCombo(orderMainMate.getBrandCombo());
					purchaseMainMate.setModel(orderMainMate.getModel());
					purchaseMainMate.setSpecification(orderMainMate.getSpecification());
					purchaseMainMate.setUnit(orderMainMate.getUnit());
					purchaseMainMate.setCount(orderMainMate.getCount());
					purchaseMainMate.setIncludLossCount(orderMainMate.getIncludLossCount());
					purchaseMainMate.setApplyCounta(Double.valueOf(applyCounta[v]));
					purchaseMainMate.setOwedWallFloorCount(Double.valueOf(applyCounta[v]));
					purchaseMainMate.setReceivedWallFloorCount(Double.valueOf(0));
					if( remarks.length>v ){
						if(StringUtils.isNotBlank(remarks[v])){
							purchaseMainMate.setRemarks(remarks[v]);
						}
					}
					
					purchaseMainMate.setCreateDate(date);
					purchaseMainMate.setUpdateDate(date);
					purchaseMainMate.setDelFlag("0");
					
					purchaseMainMateList.add(purchaseMainMate);
					

					
				}
			}
			if(null!=purchaseMainMateList && purchaseMainMateList.size()>0){

				materialManagementService.savePurchaseMainMateAll(purchaseMainMateList);
			}
			
			
		}
		
		List<PurchasePic> pList = new ArrayList<PurchasePic>();

		if (null != photo && photo.length>0) {
			
			for(String p : photo){
				
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				

				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + ConstantUtils.UPLOAD_WALLAPPLY + DateUtils.getDate1());

				if(!filePath.exists() && !filePath.isDirectory()){
					filePath.mkdirs();
				}
				String filepath = filePath + filePath.separator + uuid + ".jpeg";
				Base64Util.generateImage(p, filepath);
				
				String picpath = ConstantUtils.UPLOAD_WALLAPPLY + DateUtils.getDate1()+filePath.separator + uuid + ".jpeg";
				
				
				PurchasePic purchasePic = new PurchasePic();
				purchasePic.setId(null);
				purchasePic.setPurchaseId(purchase.getId());
				purchasePic.setPicUrl(picpath);
				purchasePic.setDelFlag("0");
				purchasePic.setCreateDate(date);
				purchasePic.setUpdateDate(date);
				pList.add(purchasePic);

			}

			materialManagementService.saveMainPicAll(pList);
		}
		
		
		String managerName = SessionUtils.getManagerSession(request).getRealname();
		String managerPhone = SessionUtils.getManagerSession(request).getPhone();
		String storeId = SessionUtils.getManagerSession(request).getStoreid();
		

		BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(storeId,"4");
		List<Integer> list = new ArrayList<Integer>();
		List<BizEmployee2> employeelist = null;
		
		if(null != bizMessagegroup ){
			Order2 order = orderService2.findOrderById(Integer.parseInt(orderId));
			String[] str = bizMessagegroup.getEmployees().split(",");
			for(String id1: str){
				list.add(Integer.valueOf(id1));
			}
			employeelist = bizEmployeeService2.getById(list);

			String content = "订单（"+order.getOrderNumber()+","+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()+"-"+order.getCustomerPhone()+",项目经理（"+managerName+"-"+managerPhone+"），项目经理已申请墙地砖，请尽快登录系统查看详情。";
			if(null != employeelist && employeelist.size()>0){
				for (BizEmployee2 bizEmployee2 : employeelist) {
					bizPhoneMsgService.sendMessage(bizEmployee2.getId(), bizEmployee2.getPhone(),
							content, SendMsgBusinessType.RELATED_BUSINESS_TYPE_800401, Integer.valueOf(orderId));
				}
			}
		}
		return String.valueOf(purchase.getId());
	}
	

	public String  purchaseCode(){

		String purchaseCode = ConstantUtils.PURCHASE_NUMBER;
	
		
		StringBuilder builder = new StringBuilder();
		

		PurchaseTwoCode purchaseObj = materialManagementService.getCode();

		purchaseObj.setPurchaseCode(String.valueOf(Integer.parseInt(purchaseObj.getPurchaseCode())+1));

		materialManagementService.updateCode(purchaseObj);
		

		String format = new SimpleDateFormat("yyyyMMdd").format( purchaseObj.getAuxiliaryDate());

		String code = purchaseObj.getPurchaseCode();
	
		builder.append(purchaseCode).append(format);

		if(code.length()==1){
			
			builder.append("000").append(code);
			
		}else if(code.length()==2){

			builder.append("00").append(code);
		}else if(code.length()==3){
			builder.append("0").append(code);
		}else if(code.length()==4){
			builder.append(code);
		}
		

		return builder.toString();
	}
	

	@RequestMapping(value="savePic",method=RequestMethod.POST)
	public @ResponseBody List<String> savePic(HttpServletRequest request,@RequestParam(value="pic",required=false) MultipartFile[] pic) throws IllegalStateException,IOException {
		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;

		String pathRoot = req.getSession().getServletContext().getRealPath("");
		String path="";
		List<String> picPath = new ArrayList<String>();
		for(MultipartFile mf : pic){
			if(!mf.isEmpty()){

				String uuid = UUID.randomUUID().toString().replaceAll("-", "");

				String contentType = mf.getContentType();
				String picName = contentType.substring(contentType.indexOf("/")+1);
				path = "/static/mobile/modules/Manager/css/savePhoto/"+uuid+"."+picName;
				mf.transferTo(new File(pathRoot+path));
				picPath.add(path);
			}
			
		}
		
		return picPath;
	}
	
	
	

	@RequestMapping(value={"wallAndFloorBrickRecord",""})
	public String wallAndFloorBrickRecord(int id, HttpServletRequest request, Model model){

		MaterialManagement materialManagement = materialManagementService.findOrderById(id);
		
		model.addAttribute("materialManagement", materialManagement);
		
		return "mobile/modules/Manager/materialManagement/wall_record";
	}
	

	@RequestMapping(value="search_wallandFloor_list_ajax")
	public @ResponseBody List<Purchase> searchWallandFloorListAjax(String orderId){
		

		List<Purchase> purchaseList = null;
		if(null!=orderId && !orderId.equals("")){
			purchaseList = materialManagementService.findPurchaseByOrderId(Integer.valueOf(orderId));
		}
		
		return purchaseList;
	}
	
	
	
	

	@RequestMapping(value={"wallAndFloorBrickDetails",""})
	public String wallAndFloorBrickDetails(String id,String purchaseId,Model model,HttpServletRequest request) throws Exception{
		
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		

		MaterialManagement materialManagement = materialManagementService.findOrderById(Integer.valueOf(id));

		Purchase purchase = materialManagementService.findPurchaseByPurchaseCode(Integer.valueOf(purchaseId));




		List<PurchaseMainMate> wall = materialManagementService.findWallByPurchaseId(purchase.getId());

		List<PurchaseMainMate> floor = materialManagementService.findfloorByPurchaseId(purchase.getId());

		List<PurchasePic> purchasePic = materialManagementService.findPurchasePicByPurchaseId(purchase.getId());
		
		String baseUrl = PicRootName.picPrefixName();
		
		model.addAttribute("materialManagement", materialManagement);
		model.addAttribute("purchase", purchase);
		model.addAttribute("wall", wall);
		model.addAttribute("floor", floor);
		model.addAttribute("purchasePic", purchasePic);
		model.addAttribute("baseUrl", baseUrl);
		

		Integer count = materialManagementService.findView(purchase.getId(),ConstantUtils.VIEW_lOG_MANAGER_WALLANDFLOOR,manager.getPhone(),manager.getId());
		if(count==0){

			materialManagementService.insertView(purchase.getId(),ConstantUtils.VIEW_lOG_MANAGER_WALLANDFLOOR,manager.getPhone(),manager.getId());
		}
		
		return "mobile/modules/Manager/materialManagement/wall_apply_details";
	}
	

	@RequestMapping(value={"urgeWallAndFloorList",""})
	public String urgeWallAndFloorList(String orderId,String purchaseId,Model model,HttpServletRequest request) throws Exception{
		

		MaterialManagement materialManagement = null;
		if(null!=purchaseId && !purchaseId.equals("")){
			materialManagement = materialManagementService.findOrderByPurchaseId(Integer.valueOf(purchaseId));
		}
		model.addAttribute("materialManagement", materialManagement);
		model.addAttribute("orderId", orderId);
		model.addAttribute("purchaseId", purchaseId);
		
		return "mobile/modules/Manager/materialManagement/wallQuickList";
	}
	

	@RequestMapping(value="wallAndFloor_urgeLogList_ajax_list")
    public @ResponseBody  List<BizBusinessUrge> wallAndFloorUrgeLogListAjaxList(String purchaseId ){

		List<BizBusinessUrge> businessUrgeList = null;
		if(null!=purchaseId && !("").equals(purchaseId)){
			
			BizBusinessUrge bizBusinessUrge = new BizBusinessUrge();

			bizBusinessUrge.setBusinessOnlyMarkInt(Integer.valueOf(purchaseId));

			bizBusinessUrge.setBusinesType(BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_2);
			
			businessUrgeList = bizBusinessUrgeService.findList(bizBusinessUrge);
		}
		
       return businessUrgeList;

    }
	

	@RequestMapping(value="wallAndFloor_push_installation_ajax")
    public @ResponseBody  String wallAndFloorPushInstallationAjax(String purchaseId,HttpServletRequest request){
		
		String result  = "0";
		Integer count = null; 
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		if(null!=purchaseId && !("").equals(purchaseId)){
			count = bizBusinessUrgeService.findCount(manager,Integer.valueOf(purchaseId),BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_2,BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_1,BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATOR_TYPE_1);
		}
		if(null!=count && count > 0){
			result = "1";
		}

       return result;

    }
	

	@RequestMapping(value = { "wallAndFloor_push_installation", "" })
	public String wallAndFloorPushInstallation(String purchaseId,String orderId, HttpServletRequest request, Model model) {

		
		model.addAttribute("orderId", orderId);
		model.addAttribute("purchaseId", purchaseId);
		
		return "mobile/modules/Manager/materialManagement/wallQuick";
	}


	@RequestMapping(value="save_wallAndFloor_push_installation_ajax")
    public @ResponseBody  String saveWallAndFloorPushInstallationAjax(String purchaseId,String orderId,String operateContent,HttpServletRequest request){
		
		String result  = "0";
		

		if(null==purchaseId || purchaseId.equals("")){
			result = "1";
			return result;
		}

		Manager manager = (Manager) request.getSession().getAttribute("manager");
		if(null==manager || null==manager.getId()){
			result = "2";
			return result;
		}

		Integer count = bizBusinessUrgeService.findCountByfiveTime(Integer.valueOf(purchaseId),manager.getId(),BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_2,BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_1,BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATOR_TYPE_1 );
		if(null!=count && count>0){
			result = "3";
			return result;
		}

		if(null==operateContent || operateContent.equals("")){
			result = "4";
			return result;
		}

		Integer urgeId = bizBusinessUrgeService.saveBusinessUrge(manager.getId(),operateContent,Integer.valueOf(purchaseId),BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_2,BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_1,BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATOR_TYPE_1);
		if(null==urgeId){
			result = "5";
			return result;
		}

		
		return result;

    }
	
	
	

	@RequestMapping(value = { "method", "" })
	public String method() {
		return "mobile/modules/Manager/method";
	}

	@RequestMapping(value = { "waterPower", "" })
	public String waterPower() {
		return "mobile/modules/Manager/waterPower";
	}

	@RequestMapping(value = { "mud", "" })
	public String mud() {
		return "mobile/modules/Manager/mud";
	}

	@RequestMapping(value = { "wood", "" })
	public String wood() {
		return "mobile/modules/Manager/wood";
	}

	@RequestMapping(value = { "paint", "" })
	public String paint() {
		return "mobile/modules/Manager/paint";
	}

	@RequestMapping(value = { "funiture", "" })
	public String funiture() {
		return "mobile/modules/Manager/funiture";
	}

	@RequestMapping(value = { "service", "" })
	public String service() {
		return "mobile/modules/Manager/service";
	}


	@RequestMapping(value="/getShippingFee")
	public @ResponseBody
	BizMaterialsStandardShippingFees getShippingFee( Integer bizMaterialsStandardType, HttpServletRequest request) {
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		String storeid1 = manager.getStoreid();
		return bizMaterialsStandardShippingFeesService.getShippingFee(Integer.valueOf(storeid1),bizMaterialsStandardType);
		}
	}
