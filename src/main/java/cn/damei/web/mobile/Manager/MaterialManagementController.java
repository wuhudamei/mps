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

/**
 * 材料管理Controller
 * @author Administrator
 *
 */

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
	//材料管理页面
	@RequestMapping(value={"meterialManagementList",""})
	public String appOrderList(MaterialManagement materialManagement,HttpServletRequest request, Model model){
	String index = (String)request.getSession().getAttribute("index");
	request.getSession().removeAttribute("index");
				if(null!=index){
					if("0".equals(index)){
						//下面的
						return "mobile/modules/Manager/materialManagement/materialManagement";	
					}else if("1".equals(index)){
						//上面的
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
	
	//申请墙地砖页面
	@RequestMapping(value={"applyMainIngredient",""})
	public String applyMainIngredient(MaterialManagement materialManagement,String timeForbidden,String isRead, HttpServletRequest request, Model model){
		//获得项目经理
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		materialManagement.setItemManagerId(manager.getId());
		
		//通过项目经理id查询项目经理下所有的订单
		List<MaterialManagement> order = materialManagementService.findOrderByItemManagerId(materialManagement.getItemManagerId());
		
		
		
		model.addAttribute("timeForbidden",timeForbidden );
		model.addAttribute("isRead",isRead );
		model.addAttribute("order",order );
		return "mobile/modules/Manager/materialManagement/wall_apply";
	}
	

	
	//墙地砖申请
	@RequestMapping(value={"wallAndFloorBrick",""})
	public String wallAndFloorBrick(int id, HttpServletRequest request, Model model){
		
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		Integer purchaseCount = 0;
		//查询该订单最新一次申请墙地砖的时间是否间隔有5分钟 并且是否已读
		Purchase purchase = materialManagementService.findViewAndTime(id,manager.getId(),manager.getPhone());
		if(null!=purchase){
			if(purchase.getApplyTime().getTime()+300*1000 > new Date().getTime()){
				//如果小于5分钟 则不允许申请墙地砖,并给出提示
				return "redirect:"+Global.getAdminPath()+"/app/manager/applyMainIngredient?timeForbidden=1";
			}
			
			if(purchase.getCount()==0){
				//如果没有查看详情 则不允许申请墙地砖,并给出提示
				return "redirect:"+Global.getAdminPath()+"/app/manager/applyMainIngredient?isRead=0";
			}
			purchaseCount = purchase.getPurchaseCount();
		}
		
		//通过订单id查询订单
		MaterialManagement materialManagement = materialManagementService.findOrderById(id);
		
		materialManagement.setPhone(manager.getPhone());
		materialManagement.setItemManager(manager.getRealname());
		
		//通过订单id查询订单主材表的墙砖
		List<OrderMainMate> wall = materialManagementService.findWallByOrderId(id);
		//通过订单id查询订单主材表的地砖
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
	
	
	//确认申请---墙地砖
	@RequestMapping(value="applyWallAndFloor" ,method=RequestMethod.POST)
	public @ResponseBody String applyWallAndFloor(String[] id,String[] applyCounta,String[] remarks,String orderId,String inputDate,String getInfo,String[] photo, HttpServletRequest request) throws UnsupportedEncodingException{
		
		Date date = new Date();
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		//查询该订单最新一次申请墙地砖的时间是否间隔有5分钟 并且是否已读
		Purchase purchase2 = materialManagementService.findViewAndTime(Integer.valueOf(orderId),manager.getId(),manager.getPhone());
		if(null!=purchase2){
			if(purchase2.getApplyTime().getTime()+300*1000 > new Date().getTime()){
				//如果小于5分钟 则不允许申请墙地砖,并给出提示
				return "lessTime";
			}
			if(purchase2.getCount()==0){
				//如果没有查看详情 则不允许申请墙地砖,并给出提示
				return "missingDetails";
			}
		}
		
		//保存采购单
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
		
		/*System.out.println("--------applyCounta----------");
		if(null != applyCounta && applyCounta.length>0){
			for(int v=0;v<applyCounta.length;v++){
				System.out.println("applyCounta["+ v +"]:"+applyCounta[v]);
			}
			
		}
		System.out.println("--------remarks----------");
		if(null != remarks && remarks.length>0){
			for(int v=0;v<remarks.length;v++){
				System.out.println("remarks["+ v +"]:"+remarks[v]);
			}
			
		}*/
		
		List<PurchaseMainMate> purchaseMainMateList = new ArrayList<PurchaseMainMate>();
		
		//保存采购单主材表
		if(null != applyCounta && applyCounta.length>0){
			
			for(int v=0;v<applyCounta.length;v++){
				if(applyCounta[v].length()!=0 && applyCounta[v] != null && applyCounta[v] !="" && applyCounta[v] != "," && !applyCounta[v].equals("0") && !applyCounta[v].equals("0.0") && !applyCounta[v].equals(".")){
					
					//通过订单主材表的id查询订单主材表
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
					
//					materialManagementService.savePurchaseMainMate(purchaseMainMate);
					
				}
			}
			if(null!=purchaseMainMateList && purchaseMainMateList.size()>0){
				//批量更新采购单主材表
				materialManagementService.savePurchaseMainMateAll(purchaseMainMateList);
			}
			
			
		}
		
		List<PurchasePic> pList = new ArrayList<PurchasePic>();
		//保存图片
		if (null != photo && photo.length>0) {
			
			for(String p : photo){
				
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				
//				String rootPath = RootName.SystemEnvironment(request);
				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + ConstantUtils.UPLOAD_WALLAPPLY + DateUtils.getDate1());
				//判断该文件是否存在
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
//				materialManagementService.saveMainPic(purchasePic);
			}
			//批量插入申请墙地砖图片
			materialManagementService.saveMainPicAll(pList);
		}
		
		
		String managerName = SessionUtils.getManagerSession(request).getRealname();
		String managerPhone = SessionUtils.getManagerSession(request).getPhone();
		String storeId = SessionUtils.getManagerSession(request).getStoreid();
		
		//根据门店和短信组类型查找  messageGroupType : '4';
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
			//订单（小区名-楼号-单元号-门牌号-客户姓名-手机号），项目经理（姓名-手机号），项目经理已申请墙地砖，请尽快登录系统查看详情。
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
	
	//采购单编号生成方法
	public String  purchaseCode(){
		//以PO开头
		String purchaseCode = ConstantUtils.PURCHASE_NUMBER;
	
		
		StringBuilder builder = new StringBuilder();
		
		//num和date
		PurchaseTwoCode purchaseObj = materialManagementService.getCode();
		//流水号+1
		purchaseObj.setPurchaseCode(String.valueOf(Integer.parseInt(purchaseObj.getPurchaseCode())+1));
		//更新数据库
		materialManagementService.updateCode(purchaseObj);
		
		//格式后的时间戳
		String format = new SimpleDateFormat("yyyyMMdd").format( purchaseObj.getAuxiliaryDate());
		//得到的流水号
		String code = purchaseObj.getPurchaseCode();
	
		builder.append(purchaseCode).append(format);
		//判断长度
		if(code.length()==1){
			
			builder.append("000").append(code);
			
		}else if(code.length()==2){
			//拼接采购单编号
			builder.append("00").append(code);
		}else if(code.length()==3){
			builder.append("0").append(code);
		}else if(code.length()==4){
			builder.append(code);
		}
		
		//返回采购单编号
		return builder.toString();
	}
	
	//上传图片
	@RequestMapping(value="savePic",method=RequestMethod.POST)
	public @ResponseBody List<String> savePic(HttpServletRequest request,@RequestParam(value="pic",required=false) MultipartFile[] pic) throws IllegalStateException,IOException {
		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
		//获得物理路径webapp所在路径
		String pathRoot = req.getSession().getServletContext().getRealPath("");
		String path="";
		List<String> picPath = new ArrayList<String>();
		for(MultipartFile mf : pic){
			if(!mf.isEmpty()){
				//生成uuid作为文件名称
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				//获得文件类型（可以判断如果不是图片，禁止上传）
				String contentType = mf.getContentType();
				String picName = contentType.substring(contentType.indexOf("/")+1);
				path = "/static/mobile/modules/Manager/css/savePhoto/"+uuid+"."+picName;
				mf.transferTo(new File(pathRoot+path));
				picPath.add(path);
			}
			
		}
		
		return picPath;
	}
	
	
	
	//申请记录
	@RequestMapping(value={"wallAndFloorBrickRecord",""})
	public String wallAndFloorBrickRecord(int id, HttpServletRequest request, Model model){
		//通过订单id查询订单
		MaterialManagement materialManagement = materialManagementService.findOrderById(id);
		
		model.addAttribute("materialManagement", materialManagement);
		
		return "mobile/modules/Manager/materialManagement/wall_record";
	}
	
	/**
	 * 根据订单id查询采购单
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value="search_wallandFloor_list_ajax")
	public @ResponseBody List<Purchase> searchWallandFloorListAjax(String orderId){
		
		//通过订单id查询采购单
		List<Purchase> purchaseList = null;
		if(null!=orderId && !orderId.equals("")){
			purchaseList = materialManagementService.findPurchaseByOrderId(Integer.valueOf(orderId));
		}
		
		return purchaseList;
	}
	
	
	
	
	//申请详情
	@RequestMapping(value={"wallAndFloorBrickDetails",""})
	public String wallAndFloorBrickDetails(String id,String purchaseId,Model model,HttpServletRequest request) throws Exception{
		
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		
		//根据订单id查询订单
		MaterialManagement materialManagement = materialManagementService.findOrderById(Integer.valueOf(id));
		//根据采购单编码查询采购单
		Purchase purchase = materialManagementService.findPurchaseByPurchaseCode(Integer.valueOf(purchaseId));
		//查询采购单状态
//		String status = materialManagementService.findStatus(purchase.getStatus());
//		purchase.setStatus(status);
		//根据采购单id查询采购单主材表的墙砖
		List<PurchaseMainMate> wall = materialManagementService.findWallByPurchaseId(purchase.getId());
		//根据采购单id查询采购单主材表的地砖
		List<PurchaseMainMate> floor = materialManagementService.findfloorByPurchaseId(purchase.getId());
		//根据采购单id查询采购单图片
		List<PurchasePic> purchasePic = materialManagementService.findPurchasePicByPurchaseId(purchase.getId());
		
		String baseUrl = PicRootName.picPrefixName();
		
		model.addAttribute("materialManagement", materialManagement);
		model.addAttribute("purchase", purchase);
		model.addAttribute("wall", wall);
		model.addAttribute("floor", floor);
		model.addAttribute("purchasePic", purchasePic);
		model.addAttribute("baseUrl", baseUrl);
		
		//查看墙地砖申请是否已查看
		Integer count = materialManagementService.findView(purchase.getId(),ConstantUtils.VIEW_lOG_MANAGER_WALLANDFLOOR,manager.getPhone(),manager.getId());
		if(count==0){
			//如果未查看则插入已读信息
			materialManagementService.insertView(purchase.getId(),ConstantUtils.VIEW_lOG_MANAGER_WALLANDFLOOR,manager.getPhone(),manager.getId());
		}
		
		return "mobile/modules/Manager/materialManagement/wall_apply_details";
	}
	
	/**
	 * 催促送货
	 * @param orderId
	 * @param purchaseId
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"urgeWallAndFloorList",""})
	public String urgeWallAndFloorList(String orderId,String purchaseId,Model model,HttpServletRequest request) throws Exception{
		
		//根据采购单id查询订单以及采购单状态
		MaterialManagement materialManagement = null;
		if(null!=purchaseId && !purchaseId.equals("")){
			materialManagement = materialManagementService.findOrderByPurchaseId(Integer.valueOf(purchaseId));
		}
		model.addAttribute("materialManagement", materialManagement);
		model.addAttribute("orderId", orderId);
		model.addAttribute("purchaseId", purchaseId);
		
		return "mobile/modules/Manager/materialManagement/wallQuickList";
	}
	
	/**
	 * 动态加载  进度日志
	 * @param purchaseId 采购单id
	 * @return
	 */
	@RequestMapping(value="wallAndFloor_urgeLogList_ajax_list")
    public @ResponseBody  List<BizBusinessUrge> wallAndFloorUrgeLogListAjaxList(String purchaseId ){

		List<BizBusinessUrge> businessUrgeList = null;
		if(null!=purchaseId && !("").equals(purchaseId)){
			
			BizBusinessUrge bizBusinessUrge = new BizBusinessUrge();
			//业务唯一标识整形
			bizBusinessUrge.setBusinessOnlyMarkInt(Integer.valueOf(purchaseId));
			//业务类型
			bizBusinessUrge.setBusinesType(BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_2);
			
			businessUrgeList = bizBusinessUrgeService.findList(bizBusinessUrge);
		}
		
       return businessUrgeList;

    }
	
	/**
	 * ajax 催促送货，一天最多允许催促1次
	 * @param purchaseId 采购单id
	 * @return
	 */
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
	
	/**
	 * 催促送货页面
	 * @param purchaseId
	 * @param orderId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "wallAndFloor_push_installation", "" })
	public String wallAndFloorPushInstallation(String purchaseId,String orderId, HttpServletRequest request, Model model) {

		
		model.addAttribute("orderId", orderId);
		model.addAttribute("purchaseId", purchaseId);
		
		return "mobile/modules/Manager/materialManagement/wallQuick";
	}

	/**
	 * ajax 催促送货
	 * @param purchaseId
	 * @param orderId
	 * @param operateContent
	 * @param request
	 * @return
	 */
	@RequestMapping(value="save_wallAndFloor_push_installation_ajax")
    public @ResponseBody  String saveWallAndFloorPushInstallationAjax(String purchaseId,String orderId,String operateContent,HttpServletRequest request){
		
		String result  = "0";
		
		//1.催促送货采购单ID为空
		if(null==purchaseId || purchaseId.equals("")){
			result = "1";
			return result;
		}
		//2.获取项目经理
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		if(null==manager || null==manager.getId()){
			result = "2";
			return result;
		}
		//3.催促送货内容--5分钟校验
		Integer count = bizBusinessUrgeService.findCountByfiveTime(Integer.valueOf(purchaseId),manager.getId(),BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_2,BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_1,BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATOR_TYPE_1 );
		if(null!=count && count>0){
			result = "3";
			return result;
		}
		//4.催促送货内容为空
		if(null==operateContent || operateContent.equals("")){
			result = "4";
			return result;
		}
		//5.保存催促送货
		Integer urgeId = bizBusinessUrgeService.saveBusinessUrge(manager.getId(),operateContent,Integer.valueOf(purchaseId),BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_2,BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_1,BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATOR_TYPE_1);
		if(null==urgeId){
			result = "5";
			return result;
		}
		//6.保存催促送货图片
		
		return result;

    }
	
	
	
	//项目经理端工艺工法
	@RequestMapping(value = { "method", "" })
	public String method() {
		return "mobile/modules/Manager/method";
	}
	//项目经理端工艺工法--水电工程
	@RequestMapping(value = { "waterPower", "" })
	public String waterPower() {
		return "mobile/modules/Manager/waterPower";
	}
	//项目经理端工艺工法--泥瓦工程
	@RequestMapping(value = { "mud", "" })
	public String mud() {
		return "mobile/modules/Manager/mud";
	}
	//项目经理端工艺工法--木土工程
	@RequestMapping(value = { "wood", "" })
	public String wood() {
		return "mobile/modules/Manager/wood";
	}
	//项目经理端工艺工法--油工工程
	@RequestMapping(value = { "paint", "" })
	public String paint() {
		return "mobile/modules/Manager/paint";
	}
	//项目经理端工艺工法--木作安装
	@RequestMapping(value = { "funiture", "" })
	public String funiture() {
		return "mobile/modules/Manager/funiture";
	}
	//项目经理端工艺工法--服务规范
	@RequestMapping(value = { "service", "" })
	public String service() {
		return "mobile/modules/Manager/service";
	}

	/**
	 * ajax 获取门店和材料对应的配送费
	 * @param bizMaterialsStandardType
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getShippingFee")
	public @ResponseBody
	BizMaterialsStandardShippingFees getShippingFee( Integer bizMaterialsStandardType, HttpServletRequest request) {
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		String storeid1 = manager.getStoreid();
		return bizMaterialsStandardShippingFeesService.getShippingFee(Integer.valueOf(storeid1),bizMaterialsStandardType);
		}
	}
