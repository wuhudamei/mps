package cn.damei.web.mobile.Manager;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.service.modules.BizPhoneMsgService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.OrderConstantUtil;
import cn.damei.common.constantUtils.PurchaseConstantUtil;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.SessionUtils;
import cn.damei.service.mobile.Manager.ApplySandService;
import cn.damei.entity.mobile.Manager.AuxiliaryPackageState;
import cn.damei.entity.mobile.Manager.AuxiliaryVo;
import cn.damei.entity.mobile.Manager.OrderVo;
import cn.damei.entity.mobile.Manager.PurchaseTwoCode;
import cn.damei.entity.mobile.Manager.PurchaseTwoVo;
import cn.damei.service.mobile.Manager.AuxiliaryApplyService;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.dao.mobile.home.HomeReportDao;
import cn.damei.entity.mobile.home.ViewLog;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizMessagegroup;
import cn.damei.service.modules.BizMessagegroupService;
import cn.damei.entity.modules.Order2;
import cn.damei.service.modules.OrderService2;




@Controller
@RequestMapping(value = "${adminPath}/app/manager/auxiliary")
public class AuxiliaryApplyController {

	@Autowired
	private AuxiliaryApplyService auxiliaryApplyService;
	@Autowired
	private OrderService2 orderService2;
	@Autowired
	private ApplySandService applySandService;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;

	private Logger  logger =  LoggerFactory.getLogger(AuxiliaryApplyController.class);


	@RequestMapping(value = { "order", "" })
	public String order(HttpServletRequest request, Model model,String  index) {
		
		if("0".equals(index)||"1".equals(index)){
			request.getSession().setAttribute("index", index);
		}else{
			logger.warn("辅料访问参数有误 ,无法识别路径参数: index :"+index);
		}
		

		Manager manager = SessionUtils.getManagerSession(request);

		List<OrderVo> list = auxiliaryApplyService.orderByManagerId(manager.getId());

		model.addAttribute("orderList", list);

		return "mobile/modules/Manager/auxiliary_apply";
	}


	@RequestMapping(value = { "goOnChoose", "" })
	public String goOnChoose(AuxiliaryVo auxiliaryVo, HttpServletRequest request, Model model) {



		

		Order2 order = orderService2.findOrderById(auxiliaryVo.getOrderId());
		List<AuxiliaryPackageState> packageStateList = null;
		if(null!=order && (order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_1) || order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_4))){

			packageStateList = auxiliaryApplyService.findAuxiliaryPackageState(auxiliaryVo.getOrderId());
			
		}
		
		model.addAttribute("packageStateList", packageStateList);
		model.addAttribute("orderId", auxiliaryVo.getOrderId());
		model.addAttribute("order", order);
				
		return "mobile/modules/Manager/auxiliary_choose";
	}
	
	

	@RequestMapping(value = "applyAuxiliary_data_check_ajax")
	public @ResponseBody String applyAuxiliaryDataCheckAjax(String orderId,HttpServletRequest request){
		
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
		

		List<PurchaseTwoVo> purchaseByOrderId = auxiliaryApplyService.selectPurchaseByOrderId(Integer.valueOf(orderId));
		if(purchaseByOrderId.size()>0){
				

			ViewLog log = new ViewLog();
			log.setBusinessIdInt(purchaseByOrderId.get(0).getPurchaseId());
			log.setBusinessType("302");
			log.setBusinessViewerOnlyMark(manager.getPhone());
			Integer integer = logDao.findView(log);
	
			if (null == integer || integer == 0) {


				result = "4";
				return result;

			}		
			if (purchaseByOrderId.get(0).getApplyTime().getTime() + 300 * 1000 > new Date().getTime()) {


				result = "5";
				return result;
			}		
			
		}
				
		return result;
		
	}
	

	@Autowired
	private HomeReportDao logDao;

	@RequestMapping(value = { "auxiliarychoose", "" })
	public String auxiliarychoose(AuxiliaryVo auxiliaryVo, HttpServletRequest request, Model model) {

		
		Integer allCount = 0;
		Double allMoney = (double) 0;






		List<AuxiliaryVo> list = auxiliaryApplyService.checkIsSubmit(auxiliaryVo.getOrderId());

		if (null != list && list.size() > 0) {
			List<AuxiliaryVo> list2 = new ArrayList<AuxiliaryVo>();

			for (AuxiliaryVo auxiliaryVo2 : list) {
					auxiliaryVo2.setOrderId(auxiliaryVo.getOrderId());

				List<AuxiliaryVo> list3 = auxiliaryApplyService.auxiliaryChoose(auxiliaryVo2);

				if(list3.size()>0){


					list3.get(0).setCount(auxiliaryVo2.getCount());
					list3.get(0).setTotalPrice(list3.get(0).getPrice() * list3.get(0).getCount());

					list2.add(list3.get(0));
					String string = list3.get(0).getTotalPrice().toString();

					allMoney += Double.parseDouble((string));
					allCount += list3.get(0).getCount();
				}

			}
			String remarks = SessionUtils.getManagerSession(request).getRealname() + "-"
					+ SessionUtils.getManagerSession(request).getPhone();


			Order2 order = orderService2.findOrderById(auxiliaryVo.getOrderId());
			List<AuxiliaryPackageState> packageStateList = null;
			if(null!=order && (order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_1) || order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_4))){

				packageStateList = auxiliaryApplyService.findAuxiliaryPackageState(auxiliaryVo.getOrderId());
				
			}
			model.addAttribute("packageStateList", packageStateList);
			model.addAttribute("order", order);
			model.addAttribute("remarks", remarks);
			model.addAttribute("auxiliaryList", list2);
			model.addAttribute("allMoney", allMoney);
			model.addAttribute("allCount", allCount);
			model.addAttribute("orderId", auxiliaryVo.getOrderId());

			return "mobile/modules/Manager/auxiliary_submit";

		}
		
		


		Order2 order = orderService2.findOrderById(auxiliaryVo.getOrderId());
		List<AuxiliaryPackageState> packageStateList = null;
		if(null!=order && (order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_1) || order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_4))){

			packageStateList = auxiliaryApplyService.findAuxiliaryPackageState(auxiliaryVo.getOrderId());
			
		}
		
		model.addAttribute("packageStateList", packageStateList);
		model.addAttribute("orderId", auxiliaryVo.getOrderId());
		model.addAttribute("order", order);
		
		return "mobile/modules/Manager/auxiliary_choose";
	}


	@RequestMapping(value = "categoryItems")
	public @ResponseBody List<AuxiliaryVo> categoryItems(String  workerType,String orderId) {
		List<AuxiliaryVo> items = auxiliaryApplyService.categoryItems(workerType,orderId);
		return items;
	}
	

	@RequestMapping(value = "check_auxiliary_package_state_ajax")
	public @ResponseBody List<AuxiliaryPackageState> checkAuxiliaryPackageStateAjax(String orderId) {
		
		Order2 order = orderService2.findOrderById(Integer.valueOf(orderId));
		List<AuxiliaryPackageState> list = null;
		
		if(null!=order && (order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_1) || order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_4))){



















			list = auxiliaryApplyService.findAuxiliaryPackageState(Integer.valueOf(orderId));
			
		}
		
		return list;
	}


	@RequestMapping(value = "auxiliarybuy", method = RequestMethod.POST)
	public String auxiliarybuy(String[] id, String[] count, String[] auxiMateCode, String orderId, Model model,
			HttpServletRequest request) {

		Integer allCount = 0;
		Double allMoney = (double) 0;


		String remarks = SessionUtils.getManagerSession(request).getRealname() + "-"
				+ SessionUtils.getManagerSession(request).getPhone();

		ArrayList<AuxiliaryVo> list = new ArrayList<AuxiliaryVo>();
		HashSet<AuxiliaryVo> set = new HashSet<AuxiliaryVo>();


		List<AuxiliaryVo> AuxiliarybyOrderId = auxiliaryApplyService.getApplyRecordByOrderId(Integer.valueOf(orderId));

		if (null != AuxiliarybyOrderId && AuxiliarybyOrderId.size() > 0) {
			



			List<AuxiliaryVo> auxiliaryDetailList = auxiliaryApplyService.selectAuxiliaryByCodeList(AuxiliarybyOrderId);
			
						if(auxiliaryDetailList.size()>0) {
							for (AuxiliaryVo auxiliaryVo3 : auxiliaryDetailList) {
								for (AuxiliaryVo auxiliaryVo2 : AuxiliarybyOrderId) {
									if (auxiliaryVo3.getAuxiMateCode().equals(auxiliaryVo2.getAuxiMateCode())) {


										auxiliaryVo3.setTotalPrice(auxiliaryVo2.getCount() * auxiliaryVo3.getPrice());
										auxiliaryVo3.setCount(auxiliaryVo2.getCount());


										set.add(auxiliaryVo3);
										break;


									}


								}
							}
						}
			
		

		}
		
		if(null != auxiMateCode && auxiMateCode.length>0){
			for (int v = 0; v < auxiMateCode.length; v++) {
				if (StringUtils.isNotBlank(count[v]) && count[v] != "," && !count[v].equals("0")) {
					
					String[] split = auxiMateCode[v].split("/");
					
					for (String itemId : split) {
						if (!itemId.equals("/")) {
							

							AuxiliaryVo auxiliaryVo = auxiliaryApplyService.selectAuxiliaryById((itemId));
							auxiliaryVo.setOrderId(Integer.parseInt(orderId));

							AuxiliaryVo auxiliaryVo2 = auxiliaryApplyService
									.getApplyRecordById(auxiliaryVo);
							
							if (null != auxiliaryVo2) {

								auxiliaryVo2.setCount(Integer.valueOf(count[v]));

								auxiliaryVo2.setTotalPrice(auxiliaryVo2.getCount() * auxiliaryVo.getPrice());
								auxiliaryVo2.setName(auxiliaryVo.getName());
								auxiliaryVo2.setPic(auxiliaryVo.getPic());
								auxiliaryVo2.setPrice(auxiliaryVo.getPrice());
								auxiliaryVo2.setSupplierPrice(auxiliaryVo.getSupplierPrice());
								auxiliaryVo2.setWangzhenPrice(auxiliaryVo.getWangzhenPrice());
								auxiliaryVo2.setUnit(auxiliaryVo.getUnit());
								auxiliaryVo2.setSpecifications(auxiliaryVo.getSpecifications());
								auxiliaryVo2.setWorkType(auxiliaryVo.getWorkType());
								auxiliaryVo2.setWorkTypeName(auxiliaryVo.getWorkTypeName());

								
								Iterator<AuxiliaryVo> iterator = set.iterator();
								
								while (iterator.hasNext()) {
									AuxiliaryVo vo = iterator.next();
									if (vo.getAuxiMateCode().equals(auxiliaryVo2.getAuxiMateCode())) {
										iterator.remove();
										
									}
								}
								
								set.add(auxiliaryVo2);
								auxiliaryVo2.setSubmmitStatus("NO");
								

								auxiliaryApplyService.updateAuxliary(auxiliaryVo2);
								
							} else {

								auxiliaryVo.setCount(Integer.valueOf(count[v]));
								auxiliaryVo.setTotalPrice(auxiliaryVo.getPrice() * auxiliaryVo.getCount());

								auxiliaryVo.setSubmmitStatus("NO");
								auxiliaryVo.setOrderId(Integer.valueOf(orderId));
								

								set.add(auxiliaryVo);
								
								auxiliaryVo.setId(null);

								auxiliaryApplyService.saveAuxliary(auxiliaryVo);
								
							}
							
						}
						
					}
				}
				
			}
		}

		for (AuxiliaryVo auxiliaryVo2 : set) {
			String string = auxiliaryVo2.getTotalPrice().toString();
			allMoney += Double.parseDouble((string));
			allCount += auxiliaryVo2.getCount();

			list.add(auxiliaryVo2);
		}
		
		
		

		Order2 order = orderService2.findOrderById(Integer.valueOf(orderId));
		List<AuxiliaryPackageState> packageStateList = null;
		if(null!=order && (order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_1) || order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_4))){

			packageStateList = auxiliaryApplyService.findAuxiliaryPackageState(Integer.valueOf(orderId));
			
		}
		model.addAttribute("packageStateList", packageStateList);
		model.addAttribute("order", order);
		
		

		model.addAttribute("remarks", remarks);

		model.addAttribute("auxiliaryList", list);
		model.addAttribute("orderId", orderId);
		model.addAttribute("allMoney", allMoney);
		model.addAttribute("allCount", allCount);

		return "mobile/modules/Manager/auxiliary_submit";
	}

	
	
	
	@Autowired
	private BizMessagegroupService bizMessagegroupService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	

	@RequestMapping(value = "auxiliarypay")
	public @ResponseBody String auxiliarypay(String hopeForTime, String orderId, String remarks, String AuxiliaryAllMoney,
			Model model, String[] auxiMateCode, String[] workType,String[] auxiliaryCount, HttpServletRequest request)
			throws ParseException, UnsupportedEncodingException {


		Order2 order = orderService2.findOrderById(Integer.valueOf(orderId));
		if(null!=order && (order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_1) || order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_4))){

			List<AuxiliaryPackageState> packageStateList = auxiliaryApplyService.findAuxiliaryPackageState(Integer.valueOf(orderId));
			if(CollectionUtils.isNotEmpty(packageStateList) && null!=workType && workType.length>0){
				for(AuxiliaryPackageState a :packageStateList){
					if(PurchaseConstantUtil.PURCHASE_IS_CAN_APPLY_AUXILIARY_2.equals(a.getIsCanApplyAuxiliary()) ||
							PurchaseConstantUtil.PURCHASE_IS_CAN_APPLY_AUXILIARY_3.equals(a.getIsCanApplyAuxiliary()) ||
							PurchaseConstantUtil.PURCHASE_IS_CAN_APPLY_AUXILIARY_4.equals(a.getIsCanApplyAuxiliary()) ||
							PurchaseConstantUtil.PURCHASE_IS_CAN_APPLY_AUXILIARY_6.equals(a.getIsCanApplyAuxiliary()) ){
						for(int x = 0; x < workType.length; x++){
							if(StringUtils.isNotBlank(workType[x]) && workType[x].equals(a.getEmpWorkType())){
								return "gaoliang";
							}
						}
					}
				}
			}
		}
				

		PurchaseTwoVo purcharse = new PurchaseTwoVo();

		purcharse.setOrderId(Integer.valueOf(orderId));

		purcharse.setStatus("10");

		purcharse.setApplyPerson(SessionUtils.getManagerSession(request).getId());

		purcharse.setApplyTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf.parse(hopeForTime);

		purcharse.setRemarks(remarks);

		purcharse.setHopeForTime(date);
		purcharse.setCreateDate(new Date());

		purcharse.setPurchaseCode(purchaseCode());
		purcharse.setStatus("10");
		purcharse.setDelFlag("0");
		purcharse.setCreateDate(new Date());
		purcharse.setAuxiliaryAllMoney(Double.parseDouble(AuxiliaryAllMoney));
		purcharse.setPurchaseType(ConstantUtils.AUXILIARY_NUMBER);
		auxiliaryApplyService.savePurchase(purcharse);
		
		
		


		for (int x = 0; x < auxiliaryCount.length; x++) {
			if (StringUtils.isNotBlank(auxiliaryCount[x]) && auxiliaryCount[x] != "," && !auxiliaryCount[x].equals("/")) {
				StringTokenizer tokenizer = new StringTokenizer(auxiMateCode[x], "/", false);
				while (tokenizer.hasMoreTokens()) {
					String chuanruCode = tokenizer.nextToken();
					
					List<AuxiliaryVo> list = auxiliaryApplyService.getApplyRecordByOrderId(Integer.valueOf(orderId));

					for (AuxiliaryVo auxiliaryVo : list) {

						if(auxiliaryVo.getAuxiMateCode().equals(chuanruCode)){
							
							if(auxiliaryCount[x].equals("0")){

								auxiliaryApplyService.deleteAuxiliaryByCode(auxiliaryVo);
								continue;
							}
							

							auxiliaryVo.setSubmmitStatus("YES");

							auxiliaryVo.setCount(Integer.parseInt(auxiliaryCount[x]));
							auxiliaryVo.setOwedAuxiMateCount(auxiliaryVo.getCount());
							auxiliaryVo.setReceivedAuxiMateCount(0);
							auxiliaryVo.setCreateDate(new Date());
							auxiliaryVo.setDelFlag("0");
							auxiliaryVo.setPurchaseId(purcharse.getId());

							auxiliaryApplyService.updateAuxliary(auxiliaryVo);
						}
					}
				}
			}
		}
		String managerName = SessionUtils.getManagerSession(request).getRealname();
		String managerPhone = SessionUtils.getManagerSession(request).getPhone();
		String storeId = SessionUtils.getManagerSession(request).getStoreid();
		

		BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(storeId,"5");
		List<Integer> list = new ArrayList<Integer>();
		List<BizEmployee2> employeelist = null;
		if(null != bizMessagegroup ){
			String[] str = bizMessagegroup.getEmployees().split(",");
			for(String id: str){
				list.add(Integer.valueOf(id));
			}
			employeelist = bizEmployeeService2.getById(list);

			String content ="订单（"+order.getOrderNumber()+","+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()+"-"+order.getCustomerPhone()+",项目经理（"+managerName+"-"+managerPhone+"），项目经理已申请辅料，请尽快登录系统查看详情。";
			if(null != employeelist && employeelist.size()>0){
				for (BizEmployee2 bizEmployee2 : employeelist) {
					bizPhoneMsgService.sendMessage(bizEmployee2.getId(), bizEmployee2.getPhone(),
							content, SendMsgBusinessType.RELATED_BUSINESS_TYPE_700202, Integer.valueOf(orderId));
				}
	    	}
		}
		return String.valueOf(purcharse.getId());
}
	

	

	
	
	
	
	

	public String  purchaseCode(){

		String purchaseCode = ConstantUtils.PURCHASE_NUMBER;
	
		
		StringBuilder builder = new StringBuilder();
		

		PurchaseTwoCode purchaseObj = auxiliaryApplyService.getCode();
		
		if(null==purchaseObj || null==purchaseObj.getAuxiliaryDate()){
			if(null==purchaseObj){
				purchaseObj = new PurchaseTwoCode();
			}
			purchaseObj.setPurchaseCode("0");
			purchaseObj.setAuxiliaryDate(new Date());
			purchaseObj.setId(2);
			auxiliaryApplyService.updateCode(purchaseObj);
		}
		
		
		if(new SimpleDateFormat("yyyyMMdd").format(purchaseObj.getAuxiliaryDate()).equals(new SimpleDateFormat("yyyyMMdd").format(new Date()))){
		
		

		purchaseObj.setPurchaseCode(String.valueOf(Integer.parseInt(purchaseObj.getPurchaseCode())+1));
		}else{

			purchaseObj.setPurchaseCode("1");
			
		}
		purchaseObj.setAuxiliaryDate(new Date());

		auxiliaryApplyService.updateCode(purchaseObj);
		

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
	
	
	
	
	
	@RequestMapping(value = "deleteAuxiliary")
	public @ResponseBody String deleteAuxiliary(String auxiliaryCode,String orderId){
		AuxiliaryVo   vo =new AuxiliaryVo();
		vo.setAuxiMateCode(auxiliaryCode);
		vo.setOrderId(Integer.parseInt(orderId));
		

	auxiliaryApplyService.deleteAuxiliaryByCode(vo);
		
		
		return "1";
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public void  setModelAttribute(AuxiliaryVo auxiliaryVo,HttpServletRequest request, Model model){

				List<AuxiliaryVo> list = auxiliaryApplyService.auxiliaryChoose(auxiliaryVo);

				model.addAttribute("waterLightCategory", list);
				model.addAttribute("orderId", auxiliaryVo.getOrderId());
		
	}
	
	
	
	
}