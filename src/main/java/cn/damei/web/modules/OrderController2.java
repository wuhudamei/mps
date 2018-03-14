package cn.damei.web.modules;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.BizOrderDistributeLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.constantUtils.OrderConstantUtil;
import cn.damei.common.mapper.JsonMapper;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.MessagePushType;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.common.MD5Utils;
import cn.damei.entity.mobile.Worker.Message;
import cn.damei.service.mobile.Worker.MessageService;
import cn.damei.service.modules.MaterialInterfaceService;
import cn.damei.service.modules.BizOrderDistributeLogService;
import cn.damei.entity.modules.BizSynData;
import cn.damei.service.modules.BizSynDataService;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.entity.modules.Inspector;
import cn.damei.entity.modules.ItemManager;
import cn.damei.service.modules.BizEmployeeService;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.service.modules.InspectorService;
import cn.damei.service.modules.ItemManagerService;
import cn.damei.service.modules.BizMessagegroupService;
import cn.damei.entity.modules.ItemManagerMap;
import cn.damei.entity.modules.Order;
import cn.damei.entity.modules.Order2;
import cn.damei.service.modules.OrderService;
import cn.damei.service.modules.OrderService2;
import cn.damei.service.modules.OrderTaskpackService;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.BizPhoneMsgService;
import cn.damei.entity.modules.Dict;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.DictUtils;
import cn.damei.common.utils.UserUtils;

import net.sf.json.JSONObject;


@Controller
@RequestMapping(value = "${adminPath}/order2/order2")
public class OrderController2 extends BaseController {

	@Autowired
	private OrderService2 orderService2;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private InspectorService inspectorService;
	@Autowired
	private ItemManagerService itemManagerService;
	@Autowired
	private OrderTaskpackService orderTaskpackService;
	@Autowired
	private BizMessagegroupService bizMessagegroupService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private BizOrderDistributeLogService bizOrderDistributeLogService;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	@Autowired
	private BizSynDataService bizSynDataService;
	@Autowired
	private BizEmployeeService bizEmployeeService;
	@Autowired
	private MaterialInterfaceService materialInterfaceService;
	
	@ModelAttribute
	public Order2 get(@RequestParam(required = false) Integer id) {
		Order2 entity = null;
		if (null!=id && !"".equals(String.valueOf(id))){
			entity = orderService2.get(id);
		}
		if (entity == null){
			entity = new Order2();
		}
		return entity;
	}
	@RequiresPermissions("order:order:view")
	@RequestMapping(value = "listAllotPage")
	public String listAllotPage(Order2 order, HttpServletRequest request, HttpServletResponse response, Model model){
		if(null == order.getEnginDepartId()){
			if(null!= UserUtils.getUser().getEmpId()){
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if(list != null && list.size()>0){
					order.setEnginDepartIds(list);
				}else{
					order.setEnginDepartIds(null);
				}
			} else {
				order.setEnginDepartIds(null);
			}
		}else{
			List<Integer> list = new ArrayList<>();
			list.add(order.getEnginDepartId());
			order.setEnginDepartIds(list);
		}
		if(UserUtils.getUser().getStoreId()!=null){

			order.setStoreId(UserUtils.getUser().getStoreId());
		}
		else{

			if(order.getStoreId()!=null && order.getStoreId().equals("1")){

					order.setStoreId(null);
			}
		}
		User user = UserUtils.getUser();
		if(null != user.getEmpId()){
			BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
			if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
				order.setProjectMode(null);
			}else{
				order.setProjectMode(be.getProjectMode());
				model.addAttribute("projectModeEnable", true);
			}
		}else{
			order.setProjectMode(null);
		}
		model.addAttribute("order", order);
		return "modules/order/orderListAllot";
	}
	

		@RequiresPermissions("order:order:view")
		@RequestMapping(value = { "listAllot", "" })
		public String listAllot(Order2 order, HttpServletRequest request,String managerType, HttpServletResponse response, Model model) {
			List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
			List<String> orderStatus = new ArrayList<String>();
			orderStatus.add("105");
			orderStatus.add("110");
			orderStatus.add("120");
			orderStatus.add("125");
			order.setOrderStatus(orderStatus);
			model.addAttribute("orderStatus", dictListByType);
			return "modules/order/orderListAllot";
		}

		@RequiresPermissions("order:order:view")
		@RequestMapping(value = "listAllot1")
		public String listAllot1(Order2 order, HttpServletRequest request, HttpServletResponse response, Model model) {
			order.setIsAllotManager("0");
			order.setProjectMode("1");
			List<String> orderStatus = order.getOrderStatus();
			if(null != orderStatus){
				String statusStr = orderStatus.toString();
				statusStr = statusStr.replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
				order.setOrderStatusNumber(statusStr);
			}
			if(null == order.getEnginDepartId()){
				if(null!= UserUtils.getUser().getEmpId()){
					List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
					if(list != null && list.size()>0){
						order.setEnginDepartIds(list);
					}else{
						order.setEnginDepartIds(null);
					}
				} else {
					order.setEnginDepartIds(null);
				}
			}else{
				List<Integer> list = new ArrayList<>();
				list.add(order.getEnginDepartId());
				order.setEnginDepartIds(list);
			}
			if(UserUtils.getUser().getStoreId()!=null){

				order.setStoreId(UserUtils.getUser().getStoreId());
			}
			else{

				if(order.getStoreId()!=null && order.getStoreId().equals("1")){

						order.setStoreId(null);
				}
			}
			if(null == order.getProjectMode() || "".equals(order.getProjectMode())){
				User user = UserUtils.getUser();
				if(null !=user.getEmpId()){
					BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
					if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
						order.setProjectMode(null);
					}else{
						order.setProjectMode(be.getProjectMode());
						model.addAttribute("projectModeEnable", true);
					}
				}else{
					order.setProjectMode(null);
				}
			}
			List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
			Page<Order2> page = orderService2.findPage(new Page<Order2>(request, response), order);
			model.addAttribute("page", page);
			model.addAttribute("order", order);
			model.addAttribute("orderStatus", dictListByType);
			model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
			return "modules/order/orderListAllot";
		}
		

		@RequiresPermissions("order:order:view")
		@RequestMapping(value = { "listUnAllot", "" })
		public String listUnAllot(Order2 order, HttpServletRequest request,String managerType, HttpServletResponse response, Model model) {
			List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
			model.addAttribute("orderStatus", dictListByType);
			return "modules/order/orderListUnAllot";
		}
		

		@RequiresPermissions("order:order:view")
		@RequestMapping(value = { "listUnAllot1", "" })
		public String listUnAllot1(Order2 order, HttpServletRequest request,String managerType, HttpServletResponse response, Model model) {
			order.setIsAllotManager("1");
			order.setProjectMode("1");
			order.setIsScrap("0");
			if(null == order.getEnginDepartId()){
				if(null!= UserUtils.getUser().getEmpId()){
					List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
					if(list != null && list.size()>0){
						order.setEnginDepartIds(list);
					}else{
						order.setEnginDepartIds(null);
					}
				} else {
					order.setEnginDepartIds(null);
				}
			}else{
				List<Integer> list = new ArrayList<>();
				list.add(order.getEnginDepartId());
				order.setEnginDepartIds(list);
			}
			if(UserUtils.getUser().getStoreId()!=null){

				order.setStoreId(UserUtils.getUser().getStoreId());
			}
			else{

				if(order.getStoreId()!=null && order.getStoreId().equals("1")){

						order.setStoreId(null);
				}
			}
			if(null == order.getProjectMode() || "".equals(order.getProjectMode())){
				User user = UserUtils.getUser();
				if(null !=user.getEmpId()){
					BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
					if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
						order.setProjectMode(null);
					}else{
						order.setProjectMode(be.getProjectMode());
						model.addAttribute("projectModeEnable", true);
					}
				}else{
					order.setProjectMode(null);
				}
			}
			Page<Order2> page = orderService2.findPage(new Page<Order2>(request, response), order);
			model.addAttribute("page", page);
			model.addAttribute("order", order);
			model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
			if(null!=managerType && !managerType.equals("")){
				model.addAttribute("managerType",managerType);
			}
			









			return "modules/order/orderListUnAllot";
		}
		

		@ResponseBody
		@RequestMapping(value = "getUnAllotCount")
		public int getUnAllotCount(HttpServletRequest request,
				HttpServletResponse response, Model model,String projectModel){
			Order2 order = new Order2();
			order.setProjectMode(projectModel);
			order.setIsScrap("0");
			return orderService2.getUnAllotCount(order);
		}

		@ResponseBody
		@RequestMapping(value = "getUnAllotInspector")
		public int getUnAllotInspector(HttpServletRequest request,
				HttpServletResponse response, Model model,String projectModel){
			Order2 order = new Order2();
			order.setProjectMode(projectModel);
			order.setIsScrap("0");
			return orderService2.getUnInspectorCount(order);
		}
		


		@RequiresPermissions("order:order:view")
		@RequestMapping(value = "allotManager")
		public String allotManager(Order2 order, ItemManager itemManager, HttpServletRequest request,
				HttpServletResponse response, Model model) {
			itemManager.setEmpType(3);
			itemManager.setEnginDepartId(order.getEnginDepartId());
			itemManager.setStoreid(order.getStoreId());
			if(!"2".equals(order.getProjectMode())){
				itemManager.setProjectMode(order.getProjectMode());
			}
			itemManager.setOrderstop("0");
			String mapCoordinate = order.getMapCoordinate();
			String[] split = mapCoordinate.split(",");
			itemManager.setOrderPointx(split[0]);
			itemManager.setOrderPointy(split[1]);
			
			List<ItemManager> list = itemManagerService.findListForOrder(itemManager);
			

			
			model.addAttribute("list", list);
			model.addAttribute("order", order);
			return "modules/order/orderAllotManager";
		}


		@RequiresPermissions("order:order:view")
		@RequestMapping(value = "reAllotManager")
		public String reAllotManager(Order2 order, ItemManager itemManager, HttpServletRequest request,
				HttpServletResponse response, Model model,String itemManagerId) {
				itemManager.setEmpType(3);
				itemManager.setEnginDepartId(order.getEnginDepartId());
				itemManager.setStoreid(order.getStoreId());
				itemManager.setProjectMode(order.getProjectMode());
				itemManager.setOrderstop("0");
				String mapCoordinate = order.getMapCoordinate();
				String[] split = mapCoordinate.split(",");
				itemManager.setOrderPointx(split[0]);
				itemManager.setOrderPointy(split[1]);
				List<ItemManager> list = itemManagerService.findListForOrder(itemManager);

				model.addAttribute("order", order);
				model.addAttribute("list", list);

				return "modules/order/orderReAllotManager";
		}

		@RequiresPermissions("order:order:edit")
		@RequestMapping(value = "saveManager")
		public String saveManager(Integer tradition,Order2 order,String managerName,Integer managerId, String managerPhone,Model model, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException, NoSuchAlgorithmException {
			

			String managerType = "0";

			String messageVerification = orderService2.verificationPrepareOrder(order);




			if(null!=messageVerification && messageVerification.equals("error")){
				managerType = "1";
				if(!StringUtils.isEmpty(order.getProjectMode())){
					if("1".equals(order.getProjectMode())){
						return "redirect:" + Global.getAdminPath() + "/order2/order2/listUnAllot?managerType="+managerType;
					}else if("2".equals(order.getProjectMode())){
						return "redirect:" + Global.getAdminPath() + "/order2/order2/listUnAllotManagerTradition?firstRequest=1&managerType="+managerType;
					}else{
						return "redirect:" + Global.getAdminPath() + "/order2/order2/quasiIndustryDfpxmjl?managerType="+managerType;
					}
				}
			}
			
			if(Integer.parseInt(order.getOrderStatusNumber())<200){
				order.setOrderStatusNumber("120");
				order.setOrderStatusDescription("已派项目经理");
			}
			order.setItemManager(managerName);
			order.setItemManagerId(managerId);
			if(order.getOrderTaskPackStatus() != null && order.getOrderTaskPackStatus().equals("1")){

				orderTaskpackService.updateManager(order.getId(),managerName,managerId);
			}

			Map<String,Integer> map = new HashMap<String,Integer>();
			map.put("itemManagerId", order.getItemManagerId());
			Integer unfinishedOrder = orderService2.findUnfinishedOrderByEmployeeId(map);
			
			orderService2.updateOrder(order);
			

			BizOrderDistributeLog log = new BizOrderDistributeLog();

			log.setOrderId(order.getId());

			log.setDistributeType(ConstantUtils.DISTRIBUTE_TYPE_101);
			log.setDistributedEmployeeId(order.getItemManagerId());
			log.setUnfinishedOrderCountBefore(unfinishedOrder);
			log.setDistributeOrderCount(1);
			log.setUnfinishedOrderCountAfter(orderService2.findUnfinishedOrderByEmployeeId(map));
			log.preInsert();
			bizOrderDistributeLogService.insert(log);
			

			Map<String,String> jsonMap = new HashMap<String,String>();
			jsonMap.put("type", "1");
			jsonMap.put("orderId", order.getOrderNumber());
			jsonMap.put("name", managerName);
			jsonMap.put("mobile", managerPhone);
			jsonMap.put("time",DateUtils.formatDateTime(new Date()));
			String key = MD5Utils.MD5Secret(jsonMap);
			jsonMap.put("key",key);
			BizSynData bizSynData = new BizSynData();
			bizSynData.setDataDirection(ConstantUtils.DATA_DIRECTION_2);
			bizSynData.setBusinessType(ConstantUtils.BUSINESS_TYPE_201);
			bizSynData.setBusinessOnlyMarkInt(order.getId());
			bizSynData.setBusinessData(JSONObject.fromObject(jsonMap).toString());
			bizSynData.setSynStatus(ConstantUtils.SYN_STATUS_4);
			bizSynData.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
			bizSynData.preInsert();
			bizSynDataService.insert(bizSynData);
			

			if(order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_1)){
				materialInterfaceService.saveWallFloorTileSquareBudget(order.getOrderNumber());
			}
			

			if(null==tradition){
			

			String content ="订单（订单编号："+order.getOrderNumber()+","+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()+"-"+order.getCustomerPhone()+"，设计师："+order.getDesignerName()+"-"+order.getDesignerPhone()+"），订单已分配给您，您可登录APP查看订单详情。";

			BizPhoneMsg phoneMsg = new BizPhoneMsg();
			phoneMsg.setReceiveEmployeeId(managerId);
			phoneMsg.setReceivePhone(managerPhone);
			phoneMsg.setMsgContent(content);
			phoneMsg.setMsgGenerateDatetime(new Date());
			phoneMsg.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
			phoneMsg.setRelatedBusinessIdInt(order.getId());
			phoneMsg.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_200202);
			phoneMsg.preInsert();
			bizPhoneMsgService.insert(phoneMsg);
			
			Message message = new Message();
			message.setMsgTitle("订单分配项目经理");
			message.setMsgTime(new Date());
			message.setMsgContent(content);
			message.setMsgType(MessagePushType.MSG_TYPE_1);
			message.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_100001001);
			message.setEmployeeId(managerId);
			message.setBusiIdInt(order.getId());
			messageService.insert(message);

			String content1 ="订单（订单编号："+order.getOrderNumber()+","+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()+"-"+order.getCustomerPhone()+"），已分派项目经理（"+managerName+"-"+managerPhone+"），如有问题或变更请及时联系项目经理。";

			BizPhoneMsg phoneMsg1 = new BizPhoneMsg();

			phoneMsg1.setReceivePhone(order.getDesignerPhone());
			phoneMsg1.setMsgContent(content1);
			phoneMsg1.setMsgGenerateDatetime(new Date());
			phoneMsg1.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
			phoneMsg1.setRelatedBusinessIdInt(order.getId());
			phoneMsg1.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_200203);
			phoneMsg1.preInsert();
			bizPhoneMsgService.insert(phoneMsg1);
			}
			
			

			if("1".equals(order.getProjectMode())){
				return "redirect:" + Global.getAdminPath() + "/order2/order2/listUnAllot?managerType="+managerType;
			}else if("2".equals(order.getProjectMode())){
				return "redirect:" + Global.getAdminPath() + "/order2/order2/listUnAllotManagerTradition?firstRequest=1&managerType="+managerType;
			}else{
				return "redirect:" + Global.getAdminPath() + "/order2/order2/quasiIndustryDfpxmjl?managerType="+managerType;
			}
		}


		@RequiresPermissions("order:order:edit")
		@RequestMapping(value = "resendManager")
		public String resendManager(Integer tradition,Order2 order,String managerName,Integer managerId,String managerPhone,Model model, RedirectAttributes redirectAttributes,
									String reasonRemarks,String reasonType) throws UnsupportedEncodingException, NoSuchAlgorithmException {
			

			String managerType = "2";

			String messageVerification = orderService2.verificationPrepareOrder(order);





			if(null!=messageVerification && messageVerification.equals("error")){
				managerType = "3";
				if(!StringUtils.isEmpty(order.getProjectMode())){
					if("1".equals(order.getProjectMode())){
						return "redirect:" + Global.getAdminPath() + "/order2/order2/listAllot?managerType="+managerType;
					}else if("2".equals(order.getProjectMode())){
						return "redirect:" + Global.getAdminPath() + "/order2/order2/listAllotManagerTradition?firstRequest=1&managerType="+managerType;
					}else{
						return "redirect:" + Global.getAdminPath() + "/order2/order2/quasiIndustryYfpxmjl?managerType="+managerType;
					}
				}
			}
			
			Integer oldManagerId =order.getItemManagerId();
			order.setItemManager(managerName);
			order.setItemManagerId(managerId);
			if(order.getOrderTaskPackStatus() != null && order.getOrderTaskPackStatus().equals("1")){

				orderTaskpackService.updateManager(order.getId(),managerName,managerId);
			}
			

			Map<String,Integer> map = new HashMap<String,Integer>();
			map.put("itemManagerId", order.getItemManagerId());
			Integer unfinishedOrder = orderService2.findUnfinishedOrderByEmployeeId(map);
			orderService2.updateOrder(order);
			

			BizEmployee bizEmployee = bizEmployeeService.selectExchangeOrderTimesById(oldManagerId);
			

			if(bizEmployee.getExchangeOrderTimes()==null || bizEmployee.getExchangeOrderTimes()==0){
				bizEmployee.setExchangeOrderTimes(1);
			}else{
				Integer times = bizEmployee.getExchangeOrderTimes();

				bizEmployee.setExchangeOrderTimes(times+=1);
			}
			bizEmployee.setUpdateBy(UserUtils.getUser());
			bizEmployee.setUpdateDate(new Date());

			bizEmployeeService.updateExchangeOrderTimes(bizEmployee);
			

			BizOrderDistributeLog log = new BizOrderDistributeLog();
			log.setOrderId(order.getId());

			log.setDistributeType(ConstantUtils.DISTRIBUTE_TYPE_102);

			log.setDistributedEmployeeId(order.getItemManagerId());

			log.setOldEmployeeId(oldManagerId);
			log.preInsert();
			
			log.setReasonRemarks(reasonRemarks);
			log.setReasonType(reasonType);
			log.setUnfinishedOrderCountBefore(unfinishedOrder);
			
			log.setDistributeOrderCount(1);
			
			log.setUnfinishedOrderCountAfter(orderService2.findUnfinishedOrderByEmployeeId(map));
			
			bizOrderDistributeLogService.insert(log);
			
			

			Map<String,String> jsonMap = new HashMap<String,String>();
			jsonMap.put("type", "1");
			jsonMap.put("orderId", order.getOrderNumber());
			jsonMap.put("name", managerName);
			jsonMap.put("mobile", managerPhone);
			jsonMap.put("time",DateUtils.formatDateTime(new Date()));
			String key = MD5Utils.MD5Secret(jsonMap);
			jsonMap.put("key",key);
			
			BizSynData bizSynData = new BizSynData();
			bizSynData.setDataDirection(ConstantUtils.DATA_DIRECTION_2);
			bizSynData.setBusinessType(ConstantUtils.BUSINESS_TYPE_201);
			bizSynData.setBusinessOnlyMarkInt(order.getId());
			bizSynData.setBusinessData(JSONObject.fromObject(jsonMap).toString());
			bizSynData.setSynStatus(ConstantUtils.SYN_STATUS_4);
			bizSynData.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
			bizSynData.preInsert();
			bizSynDataService.insert(bizSynData);
			

			if(null==tradition){
			if(!oldManagerId.equals(managerId)){

				String content ="订单（订单编号："+order.getOrderNumber()+","+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()+"-"+order.getCustomerPhone()+",设计师："+order.getDesignerName()+"-"+order.getDesignerPhone()+"，订单已分配给您，您可登录APP查看订单详情。";

				
				BizPhoneMsg phoneMsg = new BizPhoneMsg();
				phoneMsg.setReceiveEmployeeId(managerId);
				phoneMsg.setReceivePhone(managerPhone);
				phoneMsg.setMsgContent(content);
				phoneMsg.setMsgGenerateDatetime(new Date());
				phoneMsg.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
				phoneMsg.setRelatedBusinessIdInt(order.getId());
				phoneMsg.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_200204);
				phoneMsg.preInsert();
				bizPhoneMsgService.insert(phoneMsg);
				

				String content1 = "亲，非常抱歉，（"+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"）已经更换了其他的项目经理，客户（"+order.getCustomerName()+"-"+order.getCustomerPhone()+"），请您知晓，如有疑问可与派单员联系。";
				BizEmployee2 bizEmployee2 = bizEmployeeService2.get(oldManagerId);

				
				BizPhoneMsg phoneMsg1 = new BizPhoneMsg();
				phoneMsg1.setReceiveEmployeeId(oldManagerId);
				phoneMsg1.setReceivePhone(bizEmployee2.getPhone());
				phoneMsg1.setMsgContent(content1);
				phoneMsg1.setMsgGenerateDatetime(new Date());
				phoneMsg1.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
				phoneMsg1.setRelatedBusinessIdInt(order.getId());
				phoneMsg1.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_200205);
				phoneMsg1.preInsert();
				bizPhoneMsgService.insert(phoneMsg1);
				
				Message message = new Message();
				message.setMsgTitle("订单重新分配项目经理");
				message.setMsgTime(new Date());
				message.setMsgContent(content);
				message.setMsgType(MessagePushType.MSG_TYPE_1);
				message.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_100001001);
				message.setEmployeeId(managerId);
				message.setBusiIdInt(order.getId());
				messageService.insert(message);
				
				Message message1 = new Message();
				message1.setMsgTitle("订单重新分配项目经理");
				message1.setMsgTime(new Date());
				message1.setMsgContent(content1);
				message1.setMsgType(MessagePushType.MSG_TYPE_1);
				message1.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_100001001);
				message1.setEmployeeId(oldManagerId);
				message1.setBusiIdInt(order.getId());
				messageService.insert(message1);
				

				String content2 = "（"+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()+"），已经更换了新的项目经理（"+managerName+"-"+managerPhone+"），请您及时与新的项目经理交底。";

				BizPhoneMsg phoneMsg2 = new BizPhoneMsg();
				phoneMsg2.setReceivePhone(order.getDesignerPhone());
				phoneMsg2.setMsgContent(content2);
				phoneMsg2.setMsgGenerateDatetime(new Date());
				phoneMsg2.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
				phoneMsg2.setRelatedBusinessIdInt(order.getId());
				phoneMsg2.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_200206);
				phoneMsg2.preInsert();
				bizPhoneMsgService.insert(phoneMsg2);
			}
			}

			if("1".equals(order.getProjectMode())){
				return "redirect:" + Global.getAdminPath() + "/order2/order2/listAllot?managerType="+managerType;
			}else if("2".equals(order.getProjectMode())){
				return "redirect:" + Global.getAdminPath() + "/order2/order2/listAllotManagerTradition?firstRequest=1&managerType="+managerType;
			}else{
				return "redirect:" + Global.getAdminPath() + "/order2/order2/quasiIndustryYfpxmjl?managerType="+managerType;
			}
		}


		@RequiresPermissions("order:order:view")
		@RequestMapping(value = "listAllotInspectorPage")
		public String listAllotInspectorPage(Order2 order, HttpServletRequest request, HttpServletResponse response,
				Model model){
			if(null == order.getEnginDepartId()){
				if(null!= UserUtils.getUser().getEmpId()){
					List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
					if(list != null && list.size()>0){
						order.setEnginDepartIds(list);
					}else{
						order.setEnginDepartIds(null);
					} 
				} else {
					order.setEnginDepartIds(null);
				}
			}else{
				List<Integer> list = new ArrayList<>();
				list.add(order.getEnginDepartId());
				order.setEnginDepartIds(list);
			}
			if(UserUtils.getUser().getStoreId()!=null){

				order.setStoreId(UserUtils.getUser().getStoreId());
			}
			else{

				if(order.getStoreId()!=null && order.getStoreId().equals("1")){

						order.setStoreId(null);
				}
			}
			
			User user = UserUtils.getUser();
			if(null !=user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
					order.setProjectMode(null);
				}else{
					order.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}
			}else{
				order.setProjectMode(null);
			}
			model.addAttribute("order", order);
			return "modules/order/orderListAllotInspector";
		}
		
		
		@RequiresPermissions("order:order:view")
		@RequestMapping(value = { "listAllotInspector", "" })
		public String listAllotInspector(Order2 order,String inspectorType, HttpServletRequest request, HttpServletResponse response,
				Model model) {
			List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
			List<String> orderStatus = new ArrayList<String>();
			orderStatus.add("105");
			orderStatus.add("110");
			orderStatus.add("120");
			orderStatus.add("125");
			order.setOrderStatus(orderStatus);
			model.addAttribute("orderStatus", dictListByType);
			return "modules/order/orderListAllotInspector";
		}
		
		@RequiresPermissions("order:order:view")
		@RequestMapping(value = "listAllotInspector1")
		public String listAllotInspector1(Order2 order, HttpServletRequest request, HttpServletResponse response,
				Model model) {
			order.setProjectMode("1");
			order.setIsAllotInspector("0");
			List<String> orderStatus = order.getOrderStatus();
			if(null != orderStatus){
				String statusStr = orderStatus.toString();
				statusStr = statusStr.replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
				order.setOrderStatusNumber(statusStr);
			}
			if(null == order.getEnginDepartId()){
				if(null!= UserUtils.getUser().getEmpId()){
					List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
					if(list != null && list.size()>0){
						order.setEnginDepartIds(list);
					}else{
						order.setEnginDepartIds(null);
					}
				} else {
					order.setEnginDepartIds(null);
				}
			}else{
				List<Integer> list = new ArrayList<>();
				list.add(order.getEnginDepartId());
				order.setEnginDepartIds(list);
			}
			if(UserUtils.getUser().getStoreId()!=null){

				order.setStoreId(UserUtils.getUser().getStoreId());
			}
			else{

				if(order.getStoreId()!=null && order.getStoreId().equals("1")){

						order.setStoreId(null);
				}
			}
			if(null == order.getProjectMode() || "".equals(order.getProjectMode())){
				User user = UserUtils.getUser();
				if(null !=user.getEmpId()){
					BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
					if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
						order.setProjectMode(null);
					}else{
						order.setProjectMode(be.getProjectMode());
						model.addAttribute("projectModeEnable", true);
					}
				}else{
					order.setProjectMode(null);
				}
			}
			List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
			Page<Order2> page = orderService2.findPage(new Page<Order2>(request, response), order);
			model.addAttribute("page", page);
			model.addAttribute("order", order);
			model.addAttribute("orderStatus", dictListByType);
			model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
			return "modules/order/orderListAllotInspector";
		}
		
		
		@RequiresPermissions("order:order:view")
		@RequestMapping(value = { "listUnAllotInspector", "" })
		public String listUnAllotInspector(Order2 order,String inspectorType, HttpServletRequest request, HttpServletResponse response,
				Model model) {
			List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
			model.addAttribute("orderStatus", dictListByType);
			return "modules/order/orderListUnAllotInspector";
		}
		
		@RequiresPermissions("order:order:view")
		@RequestMapping(value = "listUnAllotInspector1")
		public String listUnAllotInspector1(Order2 order, HttpServletRequest request, HttpServletResponse response,
				Model model) {
			order.setProjectMode("1");
			order.setIsAllotInspector("1");
			if(null == order.getEnginDepartId()){
				if(null!= UserUtils.getUser().getEmpId()){
					List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
					if(list != null && list.size()>0){
						order.setEnginDepartIds(list);
					}else{
						order.setEnginDepartIds(null);
					}
				} else {
					order.setEnginDepartIds(null);
				}
			}else{
				List<Integer> list = new ArrayList<>();
				list.add(order.getEnginDepartId());
				order.setEnginDepartIds(list);
			}
			if(UserUtils.getUser().getStoreId()!=null){

				order.setStoreId(UserUtils.getUser().getStoreId());
			}
			else{

				if(order.getStoreId()!=null && order.getStoreId().equals("1")){

						order.setStoreId(null);
				}
			}
			if(null == order.getProjectMode() || "".equals(order.getProjectMode())){
				User user = UserUtils.getUser();
				if(null !=user.getEmpId()){
					BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
					if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
						order.setProjectMode(null);
					}else{
						order.setProjectMode(be.getProjectMode());
						model.addAttribute("projectModeEnable", true);
					}
				}else{
					order.setProjectMode(null);
				}
			}
			Page<Order2> page = orderService2.findPage(new Page<Order2>(request, response), order);
			model.addAttribute("page", page);
			model.addAttribute("order", order);
			model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
			return "modules/order/orderListUnAllotInspector";
		}


		@RequiresPermissions("order:order:view")
		@RequestMapping(value = "allotInspector")
		public String allotInspector(Order2 order, Inspector inspector, HttpServletRequest request,
				HttpServletResponse response, Model model) {
			
			inspector.setEmpType(1);
			inspector.setStoreid(order.getStoreId());
			inspector.setEnginDepartId(order.getEnginDepartId());
			if(!"2".equals(order.getProjectMode())){
				inspector.setProjectMode(order.getProjectMode());
			}
			inspector.setOrderstop("0");
			String mapCoordinate = order.getMapCoordinate();
			String[] split = mapCoordinate.split(",");
			inspector.setOrderPointx(split[0]);
			inspector.setOrderPointy(split[1]);

			List<Inspector> list = inspectorService.findListForOrder(inspector);
			model.addAttribute("order", order);
			model.addAttribute("list", list);
			return "modules/order/orderAllotInspector";
		}


		@RequiresPermissions("order:order:view")
		@RequestMapping(value = "reAllotInspector")
		public String reAllotInspector(Order2 order, Inspector inspector, HttpServletRequest request,
				HttpServletResponse response, Model model) {
			inspector.setEmpType(1);
			inspector.setEnginDepartId(order.getEnginDepartId());
			inspector.setStoreid(order.getStoreId());
			inspector.setProjectMode(order.getProjectMode());
			inspector.setOrderstop("0");
			String mapCoordinate = order.getMapCoordinate();
			String[] split = mapCoordinate.split(",");
			inspector.setOrderPointx(split[0]);
			inspector.setOrderPointy(split[1]);
			List<Inspector> list = inspectorService.findListForOrder(inspector);

			model.addAttribute("order", order);
			model.addAttribute("list", list);
			return "modules/order/orderReAllotInspector";
		}


		@RequiresPermissions("order:order:edit")
		@RequestMapping(value = "saveInspector")
		public String saveInspector(Integer tradition,Order2 order,String isLongwayCommission, String inspectorName, Integer inspectorId,String inspectorPhone, Model model, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException, NoSuchAlgorithmException {
			

			String inspectorType = "0";

			String messageVerification = orderService2.verificationPrepareOrder(order);




			if(null!=messageVerification && messageVerification.equals("error")){
				inspectorType = "1";
				if(!StringUtils.isEmpty(order.getProjectMode())){
					if("1".equals(order.getProjectMode())){
						return "redirect:" + Global.getAdminPath() + "/order2/order2/listUnAllotInspector?inspectorType="+inspectorType;
					}else if("2".equals(order.getProjectMode())){
						return "redirect:" + Global.getAdminPath() + "/order2/order2/listUnAllotInspectorTradition?firstRequest=1&inspectorType="+inspectorType;
					}else{
						return "redirect:" + Global.getAdminPath() + "/order2/order2/quasiIndustryDfpzjy?inspectorType="+inspectorType;
					}
				}
			}
			
			if(Integer.parseInt(order.getOrderStatusNumber())<200){
				order.setOrderStatusNumber("125");
				order.setOrderStatusDescription("已派质检员");
			}
			order.setIsLongwayCommission(isLongwayCommission);
			order.setOrderInspector(inspectorName);
			order.setOrderInspectorId(inspectorId);

			Map<String,Integer> map = new HashMap<String,Integer>();
			map.put("inspectorId", order.getOrderInspectorId());
			Integer unfinishedOrder = orderService2.findUnfinishedOrderByEmployeeId(map);
			orderService2.updateOrderInspector(order);

			BizOrderDistributeLog log = new BizOrderDistributeLog();
			log.setOrderId(order.getId());
			log.setDistributeType(ConstantUtils.DISTRIBUTE_TYPE_201);
			log.setDistributedEmployeeId(order.getOrderInspectorId());
			log.setUnfinishedOrderCountBefore(unfinishedOrder);
			log.setDistributeOrderCount(1);
			log.setUnfinishedOrderCountAfter(orderService2.findUnfinishedOrderByEmployeeId(map));
			log.preInsert();
			bizOrderDistributeLogService.insert(log);

			Map<String,String> jsonMap = new HashMap<String,String>();
			jsonMap.put("type", "2");
			jsonMap.put("orderId", order.getOrderNumber());
			jsonMap.put("name", inspectorName);
			jsonMap.put("mobile", inspectorPhone);
			jsonMap.put("time",DateUtils.formatDateTime(new Date()));
			String key = MD5Utils.MD5Secret(jsonMap);
			jsonMap.put("key",key);
			BizSynData bizSynData = new BizSynData();
			bizSynData.setDataDirection(ConstantUtils.DATA_DIRECTION_2);
			bizSynData.setBusinessType(ConstantUtils.BUSINESS_TYPE_202);
			bizSynData.setBusinessOnlyMarkInt(order.getId());
			bizSynData.setBusinessData(JSONObject.fromObject(jsonMap).toString());
			bizSynData.setSynStatus(ConstantUtils.SYN_STATUS_4);
			bizSynData.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
			bizSynData.preInsert();
			bizSynDataService.insert(bizSynData);
			

			if(null==tradition){

			if(null != order.getItemManagerId()){

				BizEmployee2 manager = bizEmployeeService2.get(order.getItemManagerId());
				String content ="订单（订单编号："+order.getOrderNumber()+","+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()+"-"+order.getCustomerPhone()+"）,已分配质检员：（"+inspectorName+"-"+inspectorPhone+"），请知晓。";

				BizPhoneMsg phoneMsg = new BizPhoneMsg();
				phoneMsg.setReceiveEmployeeId(manager.getId());
				phoneMsg.setReceivePhone(manager.getPhone());
				phoneMsg.setMsgContent(content);
				phoneMsg.setMsgGenerateDatetime(new Date());
				phoneMsg.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
				phoneMsg.setRelatedBusinessIdInt(order.getId());
				phoneMsg.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_200302);
				phoneMsg.preInsert();
				bizPhoneMsgService.insert(phoneMsg);
				
				Message message = new Message();
				message.setMsgTitle("订单分配质检员");
				message.setMsgTime(new Date());
				message.setMsgContent(content);
				message.setMsgType(MessagePushType.MSG_TYPE_1);
				message.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_100001002);
				message.setEmployeeId(manager.getId());
				message.setBusiIdInt(order.getId());
				messageService.insert(message);
				
			}

			String content1 ="订单（订单编号："+order.getOrderNumber()+","+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()+"-"+order.getCustomerPhone()+"）,订单已分配给您，您可登录APP查看订单详情。";
			BizPhoneMsg phoneMsg1 = new BizPhoneMsg();
			phoneMsg1.setReceiveEmployeeId(inspectorId);
			phoneMsg1.setReceivePhone(inspectorPhone);
			phoneMsg1.setMsgContent(content1);
			phoneMsg1.setMsgGenerateDatetime(new Date());
			phoneMsg1.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
			phoneMsg1.setRelatedBusinessIdInt(order.getId());
			phoneMsg1.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_200303);
			phoneMsg1.preInsert();
			bizPhoneMsgService.insert(phoneMsg1);


			}
			

			if("1".equals(order.getProjectMode())){
				return "redirect:" + Global.getAdminPath() + "/order2/order2/listUnAllotInspector?inspectorType="+inspectorType;
			}else if("2".equals(order.getProjectMode())){
				return "redirect:" + Global.getAdminPath() + "/order2/order2/listUnAllotInspectorTradition?firstRequest=1&inspectorType="+inspectorType;
			}else{
				return "redirect:" + Global.getAdminPath() + "/order2/order2/quasiIndustryDfpzjy?inspectorType="+inspectorType;
			}
		}


		@RequiresPermissions("order:order:edit")
		@RequestMapping(value = "resendInspector")
		public String resendInspector(Integer tradition,Order2 order,String inspectorName, String isLongwayCommission, Integer inspectorId,String inspectorPhone,String projectModel, Model model, RedirectAttributes redirectAttributes,String reasonRemarks,String reasonType) throws UnsupportedEncodingException, NoSuchAlgorithmException {
			

			String inspectorType = "2";

			String messageVerification = orderService2.verificationPrepareOrder(order);




			order.setProjectMode(projectModel);
			if(null!=messageVerification && messageVerification.equals("error")){
				inspectorType = "3";
				if(!StringUtils.isEmpty(order.getProjectMode())){
					if("1".equals(order.getProjectMode())){
						return "redirect:" + Global.getAdminPath() + "/order2/order2/listAllotInspector?inspectorType="+inspectorType;
					}else if("2".equals(order.getProjectMode())){
						return "redirect:" + Global.getAdminPath() + "/order2/order2/listAllotInspectorTradition?firstRequest=1&inspectorType="+inspectorType;
					}else{
						return "redirect:" + Global.getAdminPath() + "/order2/order2/quasiIndustryYfpzjy?inspectorType="+inspectorType;
					}
				}
				
			}
			
			Integer oldInspectorId = order.getOrderInspectorId();
			order.setIsLongwayCommission(isLongwayCommission);
			order.setOrderInspector(inspectorName);
			order.setOrderInspectorId(inspectorId);

			Map<String,Integer> map = new HashMap<String,Integer>();
			map.put("inspectorId", order.getOrderInspectorId());
			Integer unfinishedOrder = orderService2.findUnfinishedOrderByEmployeeId(map);
			orderService2.updateOrderInspector(order);
			

			BizEmployee bizEmployee = bizEmployeeService.selectExchangeOrderTimesById(oldInspectorId);
			

			if(bizEmployee.getExchangeOrderTimes()==null || bizEmployee.getExchangeOrderTimes()==0){
				bizEmployee.setExchangeOrderTimes(1);
			}else{
				Integer times = bizEmployee.getExchangeOrderTimes();

				bizEmployee.setExchangeOrderTimes(times+=1);
			}
			bizEmployee.setUpdateBy(UserUtils.getUser());
			bizEmployee.setUpdateDate(new Date());

			bizEmployeeService.updateExchangeOrderTimes(bizEmployee);
			

			BizOrderDistributeLog log = new BizOrderDistributeLog();
			
			log.setOrderId(order.getId());

			log.setDistributeType(ConstantUtils.DISTRIBUTE_TYPE_202);
			log.setDistributedEmployeeId(order.getOrderInspectorId());
			log.setUnfinishedOrderCountBefore(unfinishedOrder);
			log.setDistributeOrderCount(1);
			log.setUnfinishedOrderCountAfter(orderService2.findUnfinishedOrderByEmployeeId(map));
			log.setOldEmployeeId(oldInspectorId);
			log.preInsert();
			log.setReasonType(reasonType);
			log.setReasonRemarks(reasonRemarks);
			bizOrderDistributeLogService.insert(log);
			

			Map<String,String> jsonMap = new HashMap<String,String>();
			jsonMap.put("type", "2");
			jsonMap.put("orderId", order.getOrderNumber());
			jsonMap.put("name", inspectorName);
			jsonMap.put("mobile", inspectorPhone);
			jsonMap.put("time",DateUtils.formatDateTime(new Date()));
			String key = MD5Utils.MD5Secret(jsonMap);
			jsonMap.put("key",key);
			BizSynData bizSynData = new BizSynData();
			bizSynData.setDataDirection(ConstantUtils.DATA_DIRECTION_2);
			bizSynData.setBusinessType(ConstantUtils.BUSINESS_TYPE_202);
			bizSynData.setBusinessOnlyMarkInt(order.getId());
			bizSynData.setBusinessData(JSONObject.fromObject(jsonMap).toString());
			bizSynData.setSynStatus(ConstantUtils.SYN_STATUS_4);
			bizSynData.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
			bizSynData.preInsert();
			bizSynDataService.insert(bizSynData);
			if(null==tradition){
			if(!oldInspectorId.equals(inspectorId) ){

				if(null != order.getItemManagerId()){

					BizEmployee2 manager = bizEmployeeService2.get(order.getItemManagerId());
					String content ="（"+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()+"）,已经更换了新的质检员（"+inspectorName+"-"+inspectorPhone+"），请您知晓。";

					BizPhoneMsg phoneMsg = new BizPhoneMsg();
					phoneMsg.setReceiveEmployeeId(manager.getId());
					phoneMsg.setReceivePhone(manager.getPhone());
					phoneMsg.setMsgContent(content);
					phoneMsg.setMsgGenerateDatetime(new Date());
					phoneMsg.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
					phoneMsg.setRelatedBusinessIdInt(order.getId());
					phoneMsg.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_200306);
					phoneMsg.preInsert();
					bizPhoneMsgService.insert(phoneMsg);
					
					Message message = new Message();
					message.setMsgTitle("订单重新分配质检员");
					message.setMsgTime(new Date());
					message.setMsgContent(content);
					message.setMsgType(MessagePushType.MSG_TYPE_1);
					message.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_100001002);
					message.setEmployeeId(manager.getId());
					message.setBusiIdInt(order.getId());
					messageService.insert(message);
				}

				String content1 ="订单（订单编号："+order.getOrderNumber()+","+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()+"-"+order.getCustomerPhone()+"）,订单已分配给您，您可登录APP查看订单详情。";

				BizPhoneMsg phoneMsg1 = new BizPhoneMsg();
				phoneMsg1.setReceiveEmployeeId(inspectorId);
				phoneMsg1.setReceivePhone(inspectorPhone);
				phoneMsg1.setMsgContent(content1);
				phoneMsg1.setMsgGenerateDatetime(new Date());
				phoneMsg1.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
				phoneMsg1.setRelatedBusinessIdInt(order.getId());
				phoneMsg1.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_200304);
				phoneMsg1.preInsert();
				bizPhoneMsgService.insert(phoneMsg1);

				BizEmployee2 employee = bizEmployeeService2.get(oldInspectorId);				
				String content2 = "亲，非常抱歉，（"+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"）已经更换了其他的质检员，客户（"+order.getCustomerName()+"-"+order.getCustomerPhone()+"），请您知晓，如有疑问可与派单员联系。";

				BizPhoneMsg phoneMsg2 = new BizPhoneMsg();
				phoneMsg2.setReceiveEmployeeId(oldInspectorId);
				phoneMsg2.setReceivePhone(employee.getPhone());
				phoneMsg2.setMsgContent(content2);
				phoneMsg2.setMsgGenerateDatetime(new Date());
				phoneMsg2.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
				phoneMsg2.setRelatedBusinessIdInt(order.getId());
				phoneMsg2.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_200305);
				phoneMsg2.preInsert();
				bizPhoneMsgService.insert(phoneMsg2);
			}
			}

			if("1".equals(order.getProjectMode())){
				return "redirect:" + Global.getAdminPath() + "/order2/order2/listAllotInspector?inspectorType="+inspectorType;
			}else if("2".equals(order.getProjectMode())){
				return "redirect:" + Global.getAdminPath() + "/order2/order2/listAllotInspectorTradition?firstRequest=1&inspectorType="+inspectorType;
			}else{
				return "redirect:" + Global.getAdminPath() + "/order2/order2/quasiIndustryYfpzjy?inspectorType="+inspectorType;
			}
		}


		@RequiresPermissions("order:order:view")
		@RequestMapping(value = "checkOrderNumber")
		public @ResponseBody String orderNumberAjax(String orderNumber, Model model) {

			String result = orderService2.getOrderNumberById(orderNumber);
			return result;
		}


		@RequiresPermissions("order:order:view")
		@RequestMapping(value = "findOrderById")
		public @ResponseBody Order2 findOrderById(Integer id, Model model) {
			
			Order2 order = orderService2.findOrderById(id);
			return order;
	
		}

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = { "orderList", "" })
	public String orderList(Order2 order, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		model.addAttribute("order", order);
		return "modules/order/bizOrderList";
	}

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = { "orderLoadList", "" })
	public String orderLoadList(Order2 order, HttpServletRequest request, HttpServletResponse response, Model model) {

		if(StringUtils.isBlank(order.getStoreId())){
			String storeId = UserUtils.getUser().getStoreId();
			if(StringUtils.isBlank(storeId)){
				order.setStoreId(null);
			}else{
				order.setStoreId(storeId);
			}
		}
		if(StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		Page<Order2> page = orderService2.findOrderPage(new Page<Order2>(request, response), order);
		model.addAttribute("page", page);
		model.addAttribute("order", order);
		return "modules/order/bizOrderList";
	}

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = { "orderPaymentList", "" })
	public String orderPaymentList(Order2 order, HttpServletRequest request, HttpServletResponse response, Model model) {

		if(StringUtils.isBlank(order.getStoreId())){
			String storeId = UserUtils.getUser().getStoreId();
			if(StringUtils.isBlank(storeId)){
				order.setStoreId(null);
			}else{
				order.setStoreId(storeId);
			}
		}
		if(StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		Page<Order2> page = orderService2.findOrderPaymentPage(new Page<Order2>(request, response), order);
		model.addAttribute("page", page);
		model.addAttribute("order", order);
		return "modules/order/bizOrderPaymentList";
	}
	
	@Autowired
	private OrderService orderService;

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = "mapManager")
	public String mapManager(String orderId,String distance,String star, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		
		Order order = orderService.get(orderId);

		model.addAttribute("order",order);





		
		return "modules/order/mapManager";
	}

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = "mapReManager")
	public String mapReManager(String orderId,String distance,String star, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
	
		Order order = orderService.get(orderId);

		model.addAttribute("order",order);





		
		return "modules/order/mapReManager";
	}
	
	
	@RequestMapping(value="managerAllotMap")
	public @ResponseBody List<ItemManagerMap> managerAllotMap(Integer orderId,String distance,String star){
		ItemManagerMap itemManagerMap = new ItemManagerMap();
		itemManagerMap.setOrderId(orderId);
		itemManagerMap.setEmpType(3);
		if(distance!=null && !distance.equals("")){
			itemManagerMap.setDistance(Double.valueOf(distance));
		}
		if(star!=null && !star.equals("")){
			itemManagerMap.setStar(Integer.valueOf(star));
		}

		List<ItemManagerMap> list = orderService2.findMapList(itemManagerMap);
		List<ItemManagerMap> list2 = orderService2.findManagerMoreCount( new SimpleDateFormat("yyyy-MM").format(new Date()));
			List<ItemManagerMap> list3 = orderService2.findManagerMoreCount1(list2);
			if(list.size()>0&&list2.size()>0){
				for (ItemManagerMap itemManagerMap1 : list) {
					
					for (ItemManagerMap itemManagerMap2 : list2) {
						
							if(itemManagerMap2.getId().equals(itemManagerMap1.getId())){
								
								itemManagerMap1.setAlreadyDistributeCount(itemManagerMap2.getAlreadyDistributeCount());
								
								break;
							}
						
						
					}
				}
				
				
				
			}
			
			if(list.size()>0&&list3.size()>0){
				for (ItemManagerMap itemManagerMap1 : list) {
					
					for (ItemManagerMap itemManagerMap2 : list3) {
						
							if(itemManagerMap2.getId().equals(itemManagerMap1.getId())){
								
								itemManagerMap1.setDoNow(itemManagerMap2.getDoNow());
								
								break;
							}
						
						
					}
				}
				
				
				
			}
				
			
			
			
		
		
		return list;
	}
	
	@RequestMapping(value="managerMapCenterPre")
	public  String managerMapCenterPre(Order2 Order2){
		
		return "modules/map-manager-worker/mapmanager";
	}
	@RequestMapping(value="managerMapCenter")
	public @ResponseBody List<Order2> managerMapCenter(Order2 Order2){
		
		
		List<Order2> list = 	orderService2.findManagerInfo(Order2);
		
		
		return list;
	}
	@RequestMapping(value="workerMapCenterPre")
	public  String workerMapCenterPre(Order2 Order2){
		
		return "modules/map-manager-worker/mapworker";
	}
	@RequestMapping(value="workerMapCenter")
	public @ResponseBody List<Order2> workerMapCenter(Order2 Order2){
		List<Order2> list = 	orderService2.findWorkerMapInfo(Order2);
		
		return list;
	}
	

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = "mapInspector")
	public String mapInspector(String orderId,String distance,String star, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		
		Order order = orderService.get(orderId);

		model.addAttribute("order",order);





		
		return "modules/order/mapInspector";
	}

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = "mapReInspector")
	public String mapReInspector(String orderId,String distance,String star, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		
		Order order = orderService.get(orderId);

		model.addAttribute("order",order);





		
		return "modules/order/mapReInspector";
	}
	
	
	@RequestMapping(value="inspectorAllotMap")
	public @ResponseBody List<ItemManagerMap> inspectorAllotMap(Integer orderId,String distance,String star){
		
		ItemManagerMap itemManagerMap = new ItemManagerMap();
		itemManagerMap.setOrderId(orderId);
		itemManagerMap.setEmpType(1);
		if(distance!=null && !distance.equals("")){
			itemManagerMap.setDistance(Double.valueOf(distance));
		}
		if(star!=null && !star.equals("")){
			itemManagerMap.setStar(Integer.valueOf(star));
		}

		List<ItemManagerMap> list = orderService2.findMapList(itemManagerMap);
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = { "order_list_byEmpId", "order_list_byEmpId" })
	public String orderList(Integer empId,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<DropModel> list = orderService2.findOrderListByCondition(empId);
		return JsonMapper.getInstance().toJson(list);
	}

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = { "orderManagerGuranteeMoneyList", "" })
	public String orderManagerGuranteeMoneyList(Order2 order, Model model) {

		if(StringUtils.isBlank(order.getStoreId())){
			String storeId = UserUtils.getUser().getStoreId();
			if(StringUtils.isBlank(storeId)){
				order.setStoreId(null);
			}else{
				order.setStoreId(storeId);
			}
		}
		if(StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}


		if(StringUtils.isBlank(order.getProjectMode())){
			User user = UserUtils.getUser();
			if(StringUtils.isNoneBlank(user.getEmpId())){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(ConstantUtils.PROJECT_MODE_1.equals(be.getProjectMode()) || ConstantUtils.PROJECT_MODE_2.equals(be.getProjectMode())){
					order.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}else{
					order.setProjectMode(null);
				}
			}else{
				order.setProjectMode(null);
			}
		}
		model.addAttribute("order", order);
		return "modules/order/orderManagerGuranteeMoneyList";
	}

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = { "orderManagerGuranteeMoneyLoadList", "" })
	public String orderManagerGuranteeMoneyLoadList(Order2 order, HttpServletRequest request, HttpServletResponse response, Model model) {

		if(StringUtils.isBlank(order.getStoreId())){
			String storeId = UserUtils.getUser().getStoreId();
			if(StringUtils.isBlank(storeId)){
				order.setStoreId(null);
			}else{
				order.setStoreId(storeId);
			}
		}
		if(StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}


		User user = UserUtils.getUser();
		if(StringUtils.isNoneBlank(user.getEmpId())){
			BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
			if(ConstantUtils.PROJECT_MODE_1.equals(be.getProjectMode()) || ConstantUtils.PROJECT_MODE_2.equals(be.getProjectMode())){
				model.addAttribute("projectModeEnable", be.getProjectMode());
			}
		}

		Page<Order2> page = orderService2.findOrderManagerGuranteeMoneyPage(new Page<Order2>(request, response), order);
		model.addAttribute("page", page);
		model.addAttribute("order", order);
		return "modules/order/orderManagerGuranteeMoneyList";
	}
	

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = { "listAllotInspectorTradition", "" })
	public String listAllotInspectorTradition(Integer firstRequest,String inspectorType,Order2 order, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
		List<String> orderStatus = new ArrayList<String>();
		orderStatus.add("105");
		orderStatus.add("110");
		orderStatus.add("120");
		orderStatus.add("125");
		order.setOrderStatus(orderStatus);
		model.addAttribute("orderStatus", dictListByType);
		return "modules/order/tradition-distribute/orderListAllotInspector";
	}
	

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = { "listAllotInspectorTradition1", "" })
	public String listAllotInspectorTradition1(Integer firstRequest,String inspectorType,Order2 order, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		
		order.setIsAllotInspector("0");
		order.setProjectMode("2");
		List<String> orderStatus = order.getOrderStatus();
		if(null != orderStatus){
			String statusStr = orderStatus.toString();
			statusStr = statusStr.replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
			order.setOrderStatusNumber(statusStr);
		}
		if(UserUtils.getUser().getStoreId()!=null){

			order.setStoreId(UserUtils.getUser().getStoreId());
		}
		else{

			if(order.getStoreId()!=null && order.getStoreId().equals("1")){

					order.setStoreId(null);
			}
		}
		if(null == order.getProjectMode() || "".equals(order.getProjectMode())){
			User user = UserUtils.getUser();
			if(null !=user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
					order.setProjectMode(null);
				}else{
					order.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}
			}else{
				order.setProjectMode(null);
			}
		}
		order.setEnginDepartIds(null);
		List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
		Page<Order2> page = orderService2.findPage(new Page<Order2>(request, response), order);
		model.addAttribute("page", page);
		model.addAttribute("orderStatus", dictListByType);
		model.addAttribute("order", order);
		model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
		
		if(null!=inspectorType && !inspectorType.equals("")){
			model.addAttribute("inspectorType", inspectorType);
		}









		
		return "modules/order/tradition-distribute/orderListAllotInspector";
	}
	

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = { "listUnAllotInspectorTradition", "" })
	public String listUnAllotInspectorTradition(Integer firstRequest,String inspectorType,Order2 order, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
		model.addAttribute("orderStatus", dictListByType);
		return "modules/order/tradition-distribute/orderListUnAllotInspector";
	}
	
	

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = { "listUnAllotInspectorTradition1", "" })
	public String listUnAllotInspectorTradition1(Integer firstRequest,String inspectorType,Order2 order, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		
		order.setIsAllotInspector("1");
		order.setProjectMode("2");
		
		if(UserUtils.getUser().getStoreId()!=null){

			order.setStoreId(UserUtils.getUser().getStoreId());
		}
		else{

			if(order.getStoreId()!=null && order.getStoreId().equals("1")){

					order.setStoreId(null);
			}
		}
		if(null == order.getProjectMode() || "".equals(order.getProjectMode())){
			User user = UserUtils.getUser();
			if(null !=user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
					order.setProjectMode(null);
				}else{
					order.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}
			}else{
				order.setProjectMode(null);
			}
		}
		order.setEnginDepartIds(null);
		Page<Order2> page = orderService2.findPage(new Page<Order2>(request, response), order);
		model.addAttribute("page", page);
		model.addAttribute("order", order);
		model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
		
		if(null!=inspectorType && !inspectorType.equals("")){
			model.addAttribute("inspectorType", inspectorType);
		}









		
		return "modules/order/tradition-distribute/orderListUnAllotInspector";
	}
	
	

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = "allotInspectorTradition")
	public String allotInspectorTradition(Integer tradition,Order2 order, Inspector inspector, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		inspector.setEmpType(1);
		inspector.setStoreid(order.getStoreId());
		inspector.setOrderstop("0");
		String mapCoordinate = order.getMapCoordinate();
		String[] split = mapCoordinate.split(",");
		inspector.setOrderPointx(split[0]);
		inspector.setOrderPointy(split[1]);

		List<Inspector> list = inspectorService.findlistForOrderTradition(inspector);
		model.addAttribute("order", order);
		model.addAttribute("list", list);
		model.addAttribute("tradition", tradition);
		return "modules/order/tradition-distribute/orderAllotInspector";
	}
	
	
	
	
	

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = { "listAllotManagerTradition", "" })
	public String listAllotManagerTradition(Integer firstRequest,String managerType,Order2 order, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
		List<String> orderStatus = new ArrayList<String>();
		orderStatus.add("105");
		orderStatus.add("110");
		orderStatus.add("120");
		orderStatus.add("125");
		order.setOrderStatus(orderStatus);
		model.addAttribute("orderStatus", dictListByType);
		return "modules/order/tradition-distribute/orderListAllot";
	}

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = { "listAllotManagerTradition1", "" })
	public String listAllotManagerTradition1(Integer firstRequest,String managerType,Order2 order, HttpServletRequest request, HttpServletResponse response, Model model) {
		order.setProjectMode("2");
		order.setIsAllotManager("0");
		List<String> orderStatus = order.getOrderStatus();
		if(null != orderStatus){
			String statusStr = orderStatus.toString();
			statusStr = statusStr.replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
			order.setOrderStatusNumber(statusStr);
		}
		if(UserUtils.getUser().getStoreId()!=null){

			order.setStoreId(UserUtils.getUser().getStoreId());
		}
		else{

			if(order.getStoreId()!=null && order.getStoreId().equals("1")){

					order.setStoreId(null);
			}
		}
		if(null == order.getProjectMode() || "".equals(order.getProjectMode())){
			User user = UserUtils.getUser();
			if(null !=user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
					order.setProjectMode(null);
				}else{
					order.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}
			}else{
				order.setProjectMode(null);
			}
		}
		order.setEnginDepartIds(null);
		List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
		Page<Order2> page = orderService2.findPage(new Page<Order2>(request, response), order);
		model.addAttribute("page", page);
		model.addAttribute("order", order);
		model.addAttribute("orderStatus", dictListByType);
		model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
		
		if(null!=managerType && !managerType.equals("")){
			model.addAttribute("managerType", managerType);
		}









		
		return "modules/order/tradition-distribute/orderListAllot";
	}
	

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = { "listUnAllotManagerTradition", "" })
	public String listUnAllotManagerTradition(Integer firstRequest,String managerType,Order2 order, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
		model.addAttribute("orderStatus", dictListByType);
		return "modules/order/tradition-distribute/orderListUnAllot";
	}
	

		@RequiresPermissions("order:order:view")
		@RequestMapping(value = { "listUnAllotManagerTradition1", "" })
		public String listUnAllotManagerTradition1(Integer firstRequest,String managerType,Order2 order, HttpServletRequest request, HttpServletResponse response, Model model) {
			order.setProjectMode("2");
			order.setIsAllotManager("1");
			order.setIsScrap("0");
			if(UserUtils.getUser().getStoreId()!=null){

				order.setStoreId(UserUtils.getUser().getStoreId());
			}
			else{

				if(order.getStoreId()!=null && order.getStoreId().equals("1")){

						order.setStoreId(null);
				}
			}
			if(null == order.getProjectMode() || "".equals(order.getProjectMode())){
				User user = UserUtils.getUser();
				if(null !=user.getEmpId()){
					BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
					if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
						order.setProjectMode(null);
					}else{
						order.setProjectMode(be.getProjectMode());
						model.addAttribute("projectModeEnable", true);
					}
				}else{
					order.setProjectMode(null);
				}
			}
			order.setEnginDepartIds(null);
			
			Page<Order2> page = orderService2.findPage(new Page<Order2>(request, response), order);
			model.addAttribute("page", page);
			model.addAttribute("order", order);
		
			model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
			
			if(null!=managerType && !managerType.equals("")){
				model.addAttribute("managerType", managerType);
			}









			
			return "modules/order/tradition-distribute/orderListUnAllot";
		}
	
	
	
	

			@RequiresPermissions("order:order:view")
			@RequestMapping(value = "allotManagerTradition")
			public String allotManagerTradition(Order2 order,Integer tradition, ItemManager itemManager, HttpServletRequest request,
					HttpServletResponse response, Model model) {
				itemManager.setEmpType(3);
				itemManager.setStoreid(order.getStoreId());
				itemManager.setOrderstop("0");
				if(!"2".equals(order.getProjectMode())){
					itemManager.setProjectMode(order.getProjectMode());
				}
				String mapCoordinate = order.getMapCoordinate();
				String[] split = mapCoordinate.split(",");
				itemManager.setOrderPointx(split[0]);
				itemManager.setOrderPointy(split[1]);
				
				List<ItemManager> list = itemManagerService.findListForOrderTradition(itemManager);
				model.addAttribute("tradition", tradition);
				model.addAttribute("list", list);
				model.addAttribute("order", order);
				return "modules/order/tradition-distribute/orderAllotManager";
			}
			
		

		@RequiresPermissions("order:order:view")
		@RequestMapping(value = "proMangerDaily")
		public String proMangerDaily(Integer firstRequest,String managerType,Order2 order, HttpServletRequest request, HttpServletResponse response, Model model) {
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();  
	        calendar.setTime(date);  
	        calendar.add(Calendar.DAY_OF_MONTH, -1);  
	        date = calendar.getTime();  
			order.setBeginGeneratedDatetime(date);
			order.setEndGeneratedDatetime(date);
			model.addAttribute("order", order);
			return "modules/order/proMangerDaily";
		}
		
		@RequiresPermissions("order:order:view")
		@RequestMapping(value = "proMangerDailyList")
		public String proMangerDailyList(Integer firstRequest,String managerType,Order2 order, HttpServletRequest request, HttpServletResponse response, Model model) {
			int count = orderService2.getProMangerCount(order);
			Page<Order2> page2 = orderService2.getProMangerDaily(new Page<Order2>(request, response), order);
			model.addAttribute("page", page2);
			model.addAttribute("order", order);
			model.addAttribute("proMangerCount", count);
			return "modules/order/proMangerDaily";
		}

		@RequiresPermissions("order:order:view")
		@RequestMapping(value = "inspectorDaily")
		public String inspectorDaily(Integer firstRequest,String managerType,Order2 order, HttpServletRequest request, HttpServletResponse response, Model model) {
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();  
	        calendar.setTime(date);  
	        calendar.add(Calendar.DAY_OF_MONTH, -1);  
	        date = calendar.getTime();  
			order.setBeginGeneratedDatetime(date);
			order.setEndGeneratedDatetime(date);
			model.addAttribute("order", order);
			return "modules/order/inspectorDaily";
		}
		
		@RequiresPermissions("order:order:view")
		@RequestMapping(value = "inspectorDailyList")
		public String inspectorDailyList(Integer firstRequest,String managerType,Order2 order, HttpServletRequest request, HttpServletResponse response, Model model) {
			int count = orderService2.getInspectorCount(order);
			Page<Order2> page = orderService2.getInspectorDaily(new Page<Order2>(request, response), order);
			model.addAttribute("page", page);
			model.addAttribute("order", order);
			model.addAttribute("inspectorDailyCount", count);
			return "modules/order/inspectorDaily";
		}
		
		
		
		

		

		@RequiresPermissions("order:order:view")
		@RequestMapping(value = { "listAllotCY", "" })
		public String listAllotCY(Order2 order, HttpServletRequest request,String managerType, HttpServletResponse response, Model model) {
			List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
			model.addAttribute("orderStatus", dictListByType);
			List<String> orderStatus = new ArrayList<String>();
			orderStatus.add("105");
			orderStatus.add("110");
			orderStatus.add("120");
			orderStatus.add("125");
			order.setOrderStatus(orderStatus);
			return "modules/order/specialRoles/orderListAllotCY";
		}

		@RequiresPermissions("order:order:view")
		@RequestMapping(value = "listAllotCY1")
		public String listAllotCY1(Order2 order, HttpServletRequest request, HttpServletResponse response, Model model) {
			order.setIsAllotManager("0");
			order.setProjectMode("1");
			List<String> orderStatus = order.getOrderStatus();
			if(null != orderStatus){
				String statusStr = orderStatus.toString();
				statusStr = statusStr.replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
				order.setOrderStatusNumber(statusStr);
			}
			if(null == order.getEnginDepartId()){
				if(null!= UserUtils.getUser().getEmpId()){
					List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
					if(list != null && list.size()>0){
						order.setEnginDepartIds(list);
					}else{
						order.setEnginDepartIds(null);
					}
				} else {
					order.setEnginDepartIds(null);
				}
			}else{
				List<Integer> list = new ArrayList<>();
				list.add(order.getEnginDepartId());
				order.setEnginDepartIds(list);
			}
			if(UserUtils.getUser().getStoreId()!=null){

				order.setStoreId(UserUtils.getUser().getStoreId());
			}
			else{

				if(order.getStoreId()!=null && order.getStoreId().equals("1")){

						order.setStoreId(null);
				}
			}
			if(null == order.getProjectMode() || "".equals(order.getProjectMode())){
				User user = UserUtils.getUser();
				if(null !=user.getEmpId()){
					BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
					if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
						order.setProjectMode(null);
					}else{
						order.setProjectMode(be.getProjectMode());
						model.addAttribute("projectModeEnable", true);
					}
				}else{
					order.setProjectMode(null);
				}
			}
			List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
			Page<Order2> page = orderService2.findPage(new Page<Order2>(request, response), order);
			model.addAttribute("page", page);
			model.addAttribute("order2", order);
			model.addAttribute("orderStatus", dictListByType);
			model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
			return "modules/order/specialRoles/orderListAllotCY";
		}
		
		@RequiresPermissions("order:order:view")
		@RequestMapping(value = { "listAllotZCY", "" })
		public String listAllotZCY(Order2 order, HttpServletRequest request,String managerType, HttpServletResponse response, Model model) {
			List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
			model.addAttribute("orderStatus", dictListByType);
			List<String> orderStatus = new ArrayList<String>();
			orderStatus.add("105");
			orderStatus.add("110");
			orderStatus.add("120");
			orderStatus.add("125");
			order.setOrderStatus(orderStatus);
			return "modules/order/specialRoles/orderListAllotZCY";
		}

		@RequiresPermissions("order:order:view")
		@RequestMapping(value = "listAllotZCY1")
		public String listAllotZCY1(Order2 order, HttpServletRequest request, HttpServletResponse response, Model model) {
			order.setIsAllotManager("0");
			order.setProjectMode("4");
			List<String> orderStatus = order.getOrderStatus();
			if(null != orderStatus){
				String statusStr = orderStatus.toString();
				statusStr = statusStr.replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
				order.setOrderStatusNumber(statusStr);
			}
			if(null == order.getEnginDepartId()){
				if(null!= UserUtils.getUser().getEmpId()){
					List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
					if(list != null && list.size()>0){
						order.setEnginDepartIds(list);
					}else{
						order.setEnginDepartIds(null);
					}
				} else {
					order.setEnginDepartIds(null);
				}
			}else{
				List<Integer> list = new ArrayList<>();
				list.add(order.getEnginDepartId());
				order.setEnginDepartIds(list);
			}
			if(UserUtils.getUser().getStoreId()!=null){

				order.setStoreId(UserUtils.getUser().getStoreId());
			}
			else{

				if(order.getStoreId()!=null && order.getStoreId().equals("1")){

						order.setStoreId(null);
				}
			}
			if(null == order.getProjectMode() || "".equals(order.getProjectMode())){
				User user = UserUtils.getUser();
				if(null !=user.getEmpId()){
					BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
					if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
						order.setProjectMode(null);
					}else{
						order.setProjectMode(be.getProjectMode());
						model.addAttribute("projectModeEnable", true);
					}
				}else{
					order.setProjectMode(null);
				}
			}
			List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
			Page<Order2> page = orderService2.findPage(new Page<Order2>(request, response), order);
			model.addAttribute("page", page);
			model.addAttribute("order2", order);
			model.addAttribute("orderStatus", dictListByType);
			model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
			return "modules/order/specialRoles/orderListAllotZCY";
		}
			

		@RequiresPermissions("order:order:view")
		@RequestMapping(value = { "listUnAllotCY", "" })
		public String listUnAllotCY(Order2 order, HttpServletRequest request,String managerType, HttpServletResponse response, Model model) {
			List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
			model.addAttribute("orderStatus", dictListByType);
			return "modules/order/specialRoles/orderListUnAllotCY";
		}
		

		@RequiresPermissions("order:order:view")
		@RequestMapping(value = { "listUnAllotCY1", "" })
		public String listUnAllotCY1(Order2 order, HttpServletRequest request,String managerType, HttpServletResponse response, Model model) {
			order.setIsAllotManager("1");
			order.setProjectMode("1");
			if(null == order.getEnginDepartId()){
				if(null!= UserUtils.getUser().getEmpId()){
					List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
					if(list != null && list.size()>0){
						order.setEnginDepartIds(list);
					}else{
						order.setEnginDepartIds(null);
					}
				} else {
					order.setEnginDepartIds(null);
				}
			}else{
				List<Integer> list = new ArrayList<>();
				list.add(order.getEnginDepartId());
				order.setEnginDepartIds(list);
			}
			if(UserUtils.getUser().getStoreId()!=null){

				order.setStoreId(UserUtils.getUser().getStoreId());
			}
			else{

				if(order.getStoreId()!=null && order.getStoreId().equals("1")){

						order.setStoreId(null);
				}
			}
			if(null == order.getProjectMode() || "".equals(order.getProjectMode())){
				User user = UserUtils.getUser();
				if(null !=user.getEmpId()){
					BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
					if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
						order.setProjectMode(null);
					}else{
						order.setProjectMode(be.getProjectMode());
						model.addAttribute("projectModeEnable", true);
					}
				}else{
					order.setProjectMode(null);
				}
			}
			Page<Order2> page = orderService2.findPage(new Page<Order2>(request, response), order);
			model.addAttribute("page", page);
			model.addAttribute("order", order);
			model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
			if(null!=managerType && !managerType.equals("")){
				model.addAttribute("managerType",managerType);
			}
			









			return "modules/order/specialRoles/orderListUnAllotCY";
		}
		
		
		

		@RequiresPermissions("order:order:view")
		@RequestMapping(value = { "listUnAllotZCY", "" })
		public String listUnAllotZCY(Order2 order, HttpServletRequest request,String managerType, HttpServletResponse response, Model model) {
			List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
			model.addAttribute("orderStatus", dictListByType);
			return "modules/order/specialRoles/orderListUnAllotZCY";
		}
		

		@RequiresPermissions("order:order:view")
		@RequestMapping(value = { "listUnAllotZCY1", "" })
		public String listUnAllotZCY1(Order2 order, HttpServletRequest request,String managerType, HttpServletResponse response, Model model) {
			order.setIsAllotManager("1");
			order.setProjectMode("4");
			if(null == order.getEnginDepartId()){
				if(null!= UserUtils.getUser().getEmpId()){
					List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
					if(list != null && list.size()>0){
						order.setEnginDepartIds(list);
					}else{
						order.setEnginDepartIds(null);
					}
				} else {
					order.setEnginDepartIds(null);
				}
			}else{
				List<Integer> list = new ArrayList<>();
				list.add(order.getEnginDepartId());
				order.setEnginDepartIds(list);
			}
			if(UserUtils.getUser().getStoreId()!=null){

				order.setStoreId(UserUtils.getUser().getStoreId());
			}
			else{

				if(order.getStoreId()!=null && order.getStoreId().equals("1")){

						order.setStoreId(null);
				}
			}
			if(null == order.getProjectMode() || "".equals(order.getProjectMode())){
				User user = UserUtils.getUser();
				if(null !=user.getEmpId()){
					BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
					if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
						order.setProjectMode(null);
					}else{
						order.setProjectMode(be.getProjectMode());
						model.addAttribute("projectModeEnable", true);
					}
				}else{
					order.setProjectMode(null);
				}
			}
			Page<Order2> page = orderService2.findPage(new Page<Order2>(request, response), order);
			model.addAttribute("page", page);
			model.addAttribute("order", order);
			model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
			if(null!=managerType && !managerType.equals("")){
				model.addAttribute("managerType",managerType);
			}
			









			return "modules/order/specialRoles/orderListUnAllotZCY";
		}
		
		
		

		@RequiresPermissions("order:order:view")
		@RequestMapping(value = { "listAllotManagerTraditionCT", "" })
		public String listAllotManagerTraditionCT(Integer firstRequest,String managerType,Order2 order, HttpServletRequest request, HttpServletResponse response, Model model) {
			List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
			model.addAttribute("orderStatus", dictListByType);
			List<String> orderStatus = new ArrayList<String>();
			orderStatus.add("105");
			orderStatus.add("110");
			orderStatus.add("120");
			orderStatus.add("125");
			order.setOrderStatus(orderStatus);
			return "modules/order/specialRoles/orderListAllotCT";
		}

		@RequiresPermissions("order:order:view")
		@RequestMapping(value = { "listAllotManagerTraditionCT1", "" })
		public String listAllotManagerTraditionCT1(Integer firstRequest,String managerType,Order2 order, HttpServletRequest request, HttpServletResponse response, Model model) {
			order.setProjectMode("2");
			order.setIsAllotManager("0");
			List<String> orderStatus = order.getOrderStatus();
			if(null != orderStatus){
				String statusStr = orderStatus.toString();
				statusStr = statusStr.replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
				order.setOrderStatusNumber(statusStr);
			}
			if(UserUtils.getUser().getStoreId()!=null){

				order.setStoreId(UserUtils.getUser().getStoreId());
			}
			else{

				if(order.getStoreId()!=null && order.getStoreId().equals("1")){

						order.setStoreId(null);
				}
			}
			if(null == order.getProjectMode() || "".equals(order.getProjectMode())){
				User user = UserUtils.getUser();
				if(null !=user.getEmpId()){
					BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
					if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
						order.setProjectMode(null);
					}else{
						order.setProjectMode(be.getProjectMode());
						model.addAttribute("projectModeEnable", true);
					}
				}else{
					order.setProjectMode(null);
				}
			}
			order.setEnginDepartIds(null);
			List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
			Page<Order2> page = orderService2.findPage(new Page<Order2>(request, response), order);
			model.addAttribute("page", page);
			model.addAttribute("order", order);
			model.addAttribute("orderStatus", dictListByType);
			model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
			
			if(null!=managerType && !managerType.equals("")){
				model.addAttribute("managerType", managerType);
			}









			
			return "modules/order/specialRoles/orderListAllotCT";
		}
		

		@RequiresPermissions("order:order:view")
		@RequestMapping(value = { "listUnAllotManagerTraditionCT", "" })
		public String listUnAllotManagerTraditionCT(Integer firstRequest,String managerType,Order2 order, HttpServletRequest request, HttpServletResponse response, Model model) {
			List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
			model.addAttribute("orderStatus", dictListByType);
			return "modules/order/specialRoles/orderListUnAllotCT";
		}
		

			@RequiresPermissions("order:order:view")
			@RequestMapping(value = { "listUnAllotManagerTraditionCT1", "" })
			public String listUnAllotManagerTraditionCT1(Integer firstRequest,String managerType,Order2 order, HttpServletRequest request, HttpServletResponse response, Model model) {
				order.setProjectMode("2");
				order.setIsAllotManager("1");
				if(UserUtils.getUser().getStoreId()!=null){

					order.setStoreId(UserUtils.getUser().getStoreId());
				}
				else{

					if(order.getStoreId()!=null && order.getStoreId().equals("1")){

							order.setStoreId(null);
					}
				}
				if(null == order.getProjectMode() || "".equals(order.getProjectMode())){
					User user = UserUtils.getUser();
					if(null !=user.getEmpId()){
						BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
						if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
							order.setProjectMode(null);
						}else{
							order.setProjectMode(be.getProjectMode());
							model.addAttribute("projectModeEnable", true);
						}
					}else{
						order.setProjectMode(null);
					}
				}
				order.setEnginDepartIds(null);
				
				Page<Order2> page = orderService2.findPage(new Page<Order2>(request, response), order);
				model.addAttribute("page", page);
				model.addAttribute("order", order);
			
				model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
				
				if(null!=managerType && !managerType.equals("")){
					model.addAttribute("managerType", managerType);
				}









				
				return "modules/order/specialRoles/orderListUnAllotCT";
			}
			
			
			

			@RequiresPermissions("order:order:view")
			@RequestMapping(value = "allotManager1")
			public String allotManager1(Order2 order, ItemManager itemManager, HttpServletRequest request,
					HttpServletResponse response, Model model) {
				itemManager.setEmpType(3);
				itemManager.setEnginDepartId(order.getEnginDepartId());
				itemManager.setStoreid(order.getStoreId());
				itemManager.setProjectMode(order.getProjectMode());
				itemManager.setOrderstop("0");
				String mapCoordinate = order.getMapCoordinate();
				String[] split = mapCoordinate.split(",");
				itemManager.setOrderPointx(split[0]);
				itemManager.setOrderPointy(split[1]);
				
				List<ItemManager> list = itemManagerService.findListForOrder1(itemManager);
				
				model.addAttribute("list", list);
				model.addAttribute("order", order);
				return "modules/order/specialRoles/orderAllotManagerCT";
			}


			@RequiresPermissions("order:order:view")
			@RequestMapping(value = "reAllotManager1")
			public String reAllotManager1(Order2 order, ItemManager itemManager, HttpServletRequest request,
					HttpServletResponse response, Model model) {
				itemManager.setEmpType(3);
				itemManager.setEnginDepartId(order.getEnginDepartId());
				itemManager.setStoreid(order.getStoreId());
				itemManager.setProjectMode(order.getProjectMode());
				itemManager.setOrderstop("0");
				String mapCoordinate = order.getMapCoordinate();
				String[] split = mapCoordinate.split(",");
				itemManager.setOrderPointx(split[0]);
				itemManager.setOrderPointy(split[1]);
				List<ItemManager> list = itemManagerService.findListForOrder1(itemManager);
				model.addAttribute("order", order);
				model.addAttribute("list", list);

				return "modules/order/specialRoles/orderReAllotManager";
			}
			

			@RequiresPermissions("order:order:view")
			@RequestMapping(value = "allotManagerTradition1")
			public String allotManagerTradition1(Order2 order,Integer tradition, ItemManager itemManager, HttpServletRequest request,
					HttpServletResponse response, Model model) {
				itemManager.setEmpType(3);
				itemManager.setStoreid(order.getStoreId());
				itemManager.setProjectMode(order.getProjectMode());
				itemManager.setOrderstop("0");
				String mapCoordinate = order.getMapCoordinate();
				String[] split = mapCoordinate.split(",");
				itemManager.setOrderPointx(split[0]);
				itemManager.setOrderPointy(split[1]);
				
				List<ItemManager> list = itemManagerService.findListForOrderTradition1(itemManager);
				model.addAttribute("tradition", tradition);
				model.addAttribute("list", list);
				model.addAttribute("order", order);
				return "modules/order/specialRoles/orderReAllotManager";
			}
			

			@RequiresPermissions("order:order:edit")
			@RequestMapping(value = "saveManager1")
			public String saveManager1(Integer tradition,Order2 order,String managerName,Integer managerId, String managerPhone,Model model, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException, NoSuchAlgorithmException {
				

				String managerType = "0";

				String messageVerification = orderService2.verificationPrepareOrder(order);




				if(null!=messageVerification && messageVerification.equals("error")){
					managerType = "1";
					if(!StringUtils.isEmpty(order.getProjectMode())){
						if("1".equals(order.getProjectMode())){
							return "redirect:" + Global.getAdminPath() + "/order2/order2/listUnAllot?managerType="+managerType;
						}else if("2".equals(order.getProjectMode())){
							return "redirect:" + Global.getAdminPath() + "/order2/order2/listUnAllotManagerTradition?firstRequest=1&managerType="+managerType;
						}else{
							return "redirect:" + Global.getAdminPath() + "/order2/order2/quasiIndustryDfpxmjl?managerType="+managerType;
						}
					}
				}
				
				if(Integer.parseInt(order.getOrderStatusNumber())<200){
					order.setOrderStatusNumber("120");
					order.setOrderStatusDescription("已派项目经理");
				}
				order.setItemManager(managerName);
				order.setItemManagerId(managerId);
				if(order.getOrderTaskPackStatus() != null && order.getOrderTaskPackStatus().equals("1")){

					orderTaskpackService.updateManager(order.getId(),managerName,managerId);
				}

				Map<String,Integer> map = new HashMap<String,Integer>();
				map.put("itemManagerId", order.getItemManagerId());
				Integer unfinishedOrder = orderService2.findUnfinishedOrderByEmployeeId(map);
				
				orderService2.updateOrder(order);
				

				BizOrderDistributeLog log = new BizOrderDistributeLog();

				log.setOrderId(order.getId());

				log.setDistributeType(ConstantUtils.DISTRIBUTE_TYPE_101);
				log.setDistributedEmployeeId(order.getItemManagerId());
				log.setUnfinishedOrderCountBefore(unfinishedOrder);
				log.setDistributeOrderCount(1);
				log.setUnfinishedOrderCountAfter(orderService2.findUnfinishedOrderByEmployeeId(map));
				log.preInsert();
				bizOrderDistributeLogService.insert(log);
				

				Map<String,String> jsonMap = new HashMap<String,String>();
				jsonMap.put("type", "1");
				jsonMap.put("orderId", order.getOrderNumber());
				jsonMap.put("name", managerName);
				jsonMap.put("mobile", managerPhone);
				jsonMap.put("time",DateUtils.formatDateTime(new Date()));
				String key = MD5Utils.MD5Secret(jsonMap);
				jsonMap.put("key",key);
				BizSynData bizSynData = new BizSynData();
				bizSynData.setDataDirection(ConstantUtils.DATA_DIRECTION_2);
				bizSynData.setBusinessType(ConstantUtils.BUSINESS_TYPE_201);
				bizSynData.setBusinessOnlyMarkInt(order.getId());
				bizSynData.setBusinessData(JSONObject.fromObject(jsonMap).toString());
				bizSynData.setSynStatus(ConstantUtils.SYN_STATUS_4);
				bizSynData.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
				bizSynData.preInsert();
				bizSynDataService.insert(bizSynData);
				

				if(order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_1)){
					materialInterfaceService.saveWallFloorTileSquareBudget(order.getOrderNumber());
				}
				

				if(null==tradition){
				

				String content ="订单（订单编号："+order.getOrderNumber()+","+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()+"-"+order.getCustomerPhone()+"，设计师："+order.getDesignerName()+"-"+order.getDesignerPhone()+"），订单已分配给您，您可登录APP查看订单详情。";

				BizPhoneMsg phoneMsg = new BizPhoneMsg();
				phoneMsg.setReceiveEmployeeId(managerId);
				phoneMsg.setReceivePhone(managerPhone);
				phoneMsg.setMsgContent(content);
				phoneMsg.setMsgGenerateDatetime(new Date());
				phoneMsg.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
				phoneMsg.setRelatedBusinessIdInt(order.getId());
				phoneMsg.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_200202);
				phoneMsg.preInsert();
				bizPhoneMsgService.insert(phoneMsg);
				
				Message message = new Message();
				message.setMsgTitle("订单分配项目经理");
				message.setMsgTime(new Date());
				message.setMsgContent(content);
				message.setMsgType(MessagePushType.MSG_TYPE_1);
				message.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_100001001);
				message.setEmployeeId(managerId);
				message.setBusiIdInt(order.getId());
				messageService.insert(message);

				String content1 ="订单（订单编号："+order.getOrderNumber()+","+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()+"-"+order.getCustomerPhone()+"），已分派项目经理（"+managerName+"-"+managerPhone+"），如有问题或变更请及时联系项目经理。";

				BizPhoneMsg phoneMsg1 = new BizPhoneMsg();

				phoneMsg1.setReceivePhone(order.getDesignerPhone());
				phoneMsg1.setMsgContent(content1);
				phoneMsg1.setMsgGenerateDatetime(new Date());
				phoneMsg1.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
				phoneMsg1.setRelatedBusinessIdInt(order.getId());
				phoneMsg1.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_200203);
				phoneMsg1.preInsert();
				bizPhoneMsgService.insert(phoneMsg1);
				}

				if("1".equals(order.getProjectMode())){
					return "redirect:" + Global.getAdminPath() + "/order2/order2/listUnAllotCY?managerType="+managerType;
				}else if("2".equals(order.getProjectMode())){
					return "redirect:" + Global.getAdminPath() + "/order2/order2/listUnAllotManagerTraditionCT?firstRequest=1&managerType="+managerType;
				}else{
					return "redirect:" + Global.getAdminPath() + "/order2/order2/quasiIndustryDfpxmjl?managerType="+managerType;
				}
			}
			

			@RequiresPermissions("order:order:edit")
			@RequestMapping(value = "resendManager1")
			public String resendManager1(Integer tradition,Order2 order,String managerName,Integer managerId,String managerPhone,Model model, RedirectAttributes redirectAttributes,
										String reasonRemarks,String reasonType) throws UnsupportedEncodingException, NoSuchAlgorithmException {
				

				String managerType = "2";

				String messageVerification = orderService2.verificationPrepareOrder(order);





				if(null!=messageVerification && messageVerification.equals("error")){
					managerType = "3";
					if(!StringUtils.isEmpty(order.getProjectMode())){
						if("1".equals(order.getProjectMode())){
							return "redirect:" + Global.getAdminPath() + "/order2/order2/listAllot?managerType="+managerType;
						}else{
							return "redirect:" + Global.getAdminPath() + "/order2/order2/listAllotManagerTradition?firstRequest=1&managerType="+managerType;
						}
					}
				}
				
				Integer oldManagerId =order.getItemManagerId();
				order.setItemManager(managerName);
				order.setItemManagerId(managerId);
				if(order.getOrderTaskPackStatus() != null && order.getOrderTaskPackStatus().equals("1")){

					orderTaskpackService.updateManager(order.getId(),managerName,managerId);
				}
				

				Map<String,Integer> map = new HashMap<String,Integer>();
				map.put("itemManagerId", order.getItemManagerId());
				Integer unfinishedOrder = orderService2.findUnfinishedOrderByEmployeeId(map);
				orderService2.updateOrder(order);
				

				BizEmployee bizEmployee = bizEmployeeService.selectExchangeOrderTimesById(oldManagerId);
				

				if(bizEmployee.getExchangeOrderTimes()==null || bizEmployee.getExchangeOrderTimes()==0){
					bizEmployee.setExchangeOrderTimes(1);
				}else{
					Integer times = bizEmployee.getExchangeOrderTimes();

					bizEmployee.setExchangeOrderTimes(times+=1);
				}
				bizEmployee.setUpdateBy(UserUtils.getUser());
				bizEmployee.setUpdateDate(new Date());

				bizEmployeeService.updateExchangeOrderTimes(bizEmployee);
				

				BizOrderDistributeLog log = new BizOrderDistributeLog();
				log.setOrderId(order.getId());

				log.setDistributeType(ConstantUtils.DISTRIBUTE_TYPE_102);

				log.setDistributedEmployeeId(order.getItemManagerId());

				log.setOldEmployeeId(oldManagerId);
				log.preInsert();
				
				log.setReasonRemarks(reasonRemarks);
				log.setReasonType(reasonType);
				log.setUnfinishedOrderCountBefore(unfinishedOrder);
				
				log.setDistributeOrderCount(1);
				
				log.setUnfinishedOrderCountAfter(orderService2.findUnfinishedOrderByEmployeeId(map));
				
				bizOrderDistributeLogService.insert(log);
				
				

				Map<String,String> jsonMap = new HashMap<String,String>();
				jsonMap.put("type", "1");
				jsonMap.put("orderId", order.getOrderNumber());
				jsonMap.put("name", managerName);
				jsonMap.put("mobile", managerPhone);
				jsonMap.put("time",DateUtils.formatDateTime(new Date()));
				String key = MD5Utils.MD5Secret(jsonMap);
				jsonMap.put("key",key);
				
				BizSynData bizSynData = new BizSynData();
				bizSynData.setDataDirection(ConstantUtils.DATA_DIRECTION_2);
				bizSynData.setBusinessType(ConstantUtils.BUSINESS_TYPE_201);
				bizSynData.setBusinessOnlyMarkInt(order.getId());
				bizSynData.setBusinessData(JSONObject.fromObject(jsonMap).toString());
				bizSynData.setSynStatus(ConstantUtils.SYN_STATUS_4);
				bizSynData.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
				bizSynData.preInsert();
				bizSynDataService.insert(bizSynData);
				

				if(null==tradition){
				if(!oldManagerId.equals(managerId)){

					String content ="订单（订单编号："+order.getOrderNumber()+","+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()+"-"+order.getCustomerPhone()+",设计师："+order.getDesignerName()+"-"+order.getDesignerPhone()+"，订单已分配给您，您可登录APP查看订单详情。";

					
					BizPhoneMsg phoneMsg = new BizPhoneMsg();
					phoneMsg.setReceiveEmployeeId(managerId);
					phoneMsg.setReceivePhone(managerPhone);
					phoneMsg.setMsgContent(content);
					phoneMsg.setMsgGenerateDatetime(new Date());
					phoneMsg.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
					phoneMsg.setRelatedBusinessIdInt(order.getId());
					phoneMsg.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_200204);
					phoneMsg.preInsert();
					bizPhoneMsgService.insert(phoneMsg);
					

					String content1 = "亲，非常抱歉，（"+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"）已经更换了其他的项目经理，客户（"+order.getCustomerName()+"-"+order.getCustomerPhone()+"），请您知晓，如有疑问可与派单员联系。";
					BizEmployee2 bizEmployee2 = bizEmployeeService2.get(oldManagerId);

					
					BizPhoneMsg phoneMsg1 = new BizPhoneMsg();
					phoneMsg1.setReceiveEmployeeId(oldManagerId);
					phoneMsg1.setReceivePhone(bizEmployee2.getPhone());
					phoneMsg1.setMsgContent(content1);
					phoneMsg1.setMsgGenerateDatetime(new Date());
					phoneMsg1.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
					phoneMsg1.setRelatedBusinessIdInt(order.getId());
					phoneMsg1.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_200205);
					phoneMsg1.preInsert();
					bizPhoneMsgService.insert(phoneMsg1);
					
					Message message = new Message();
					message.setMsgTitle("订单重新分配项目经理");
					message.setMsgTime(new Date());
					message.setMsgContent(content);
					message.setMsgType(MessagePushType.MSG_TYPE_1);
					message.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_100001001);
					message.setEmployeeId(managerId);
					message.setBusiIdInt(order.getId());
					messageService.insert(message);
					
					Message message1 = new Message();
					message1.setMsgTitle("订单重新分配项目经理");
					message1.setMsgTime(new Date());
					message1.setMsgContent(content1);
					message1.setMsgType(MessagePushType.MSG_TYPE_1);
					message1.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_100001001);
					message1.setEmployeeId(oldManagerId);
					message1.setBusiIdInt(order.getId());
					messageService.insert(message1);
					

					String content2 = "（"+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()+"），已经更换了新的项目经理（"+managerName+"-"+managerPhone+"），请您及时与新的项目经理交底。";

					BizPhoneMsg phoneMsg2 = new BizPhoneMsg();
					phoneMsg2.setReceivePhone(order.getDesignerPhone());
					phoneMsg2.setMsgContent(content2);
					phoneMsg2.setMsgGenerateDatetime(new Date());
					phoneMsg2.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
					phoneMsg2.setRelatedBusinessIdInt(order.getId());
					phoneMsg2.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_200206);
					phoneMsg2.preInsert();
					bizPhoneMsgService.insert(phoneMsg2);
				}
				}

				if("1".equals(order.getProjectMode())){
					return "redirect:" + Global.getAdminPath() + "/order2/order2/listAllotCY?managerType="+managerType;
				}else{
					return "redirect:" + Global.getAdminPath() + "/order2/order2/listAllotManagerTraditionCT?firstRequest=1&managerType="+managerType;
				}
			}
			
			
			
			
			
			
			

			

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = { "quasiIndustryYfpxmjl", "" })
	public String quasiIndustryYfpxmjl(Order2 order, HttpServletRequest request,String managerType, HttpServletResponse response, Model model) {
		List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
		List<String> orderStatus = new ArrayList<String>();
		orderStatus.add("105");
		orderStatus.add("110");
		orderStatus.add("120");
		orderStatus.add("125");
		order.setOrderStatus(orderStatus);
		model.addAttribute("orderStatus", dictListByType);
		return "modules/order/quasiIndustryYfpxmjl";
	}	
	

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = "quasiIndustryYfpxmjl1")
	public String quasiIndustryYfpxmjl1(Order2 order, HttpServletRequest request, HttpServletResponse response, Model model) {
		order.setIsAllotManager("0");
		order.setProjectMode("4");
		List<String> orderStatus = order.getOrderStatus();
		if(null != orderStatus){
			String statusStr = orderStatus.toString();
			statusStr = statusStr.replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
			order.setOrderStatusNumber(statusStr);
		}
		if(null == order.getEnginDepartId()){
			if(null!= UserUtils.getUser().getEmpId()){
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if(list != null && list.size()>0){
					order.setEnginDepartIds(list);
				}else{
					order.setEnginDepartIds(null);
				}
			} else {
				order.setEnginDepartIds(null);
			}
		}else{
			List<Integer> list = new ArrayList<>();
			list.add(order.getEnginDepartId());
			order.setEnginDepartIds(list);
		}
		if(UserUtils.getUser().getStoreId()!=null){

			order.setStoreId(UserUtils.getUser().getStoreId());
		}
		else{

			if(order.getStoreId()!=null && order.getStoreId().equals("1")){

					order.setStoreId(null);
			}
		}
		if(null == order.getProjectMode() || "".equals(order.getProjectMode())){
			User user = UserUtils.getUser();
			if(null !=user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
					order.setProjectMode(null);
				}else{
					order.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}
			}else{
				order.setProjectMode(null);
			}
		}
		List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
		Page<Order2> page = orderService2.findPage(new Page<Order2>(request, response), order);
		model.addAttribute("page", page);
		model.addAttribute("order", order);
		model.addAttribute("orderStatus", dictListByType);
		model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
		return "modules/order/quasiIndustryYfpxmjl";
	}
	

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = { "quasiIndustryDfpxmjl", "" })
	public String quasiIndustryDfpxmjl(Order2 order, HttpServletRequest request,String managerType, HttpServletResponse response, Model model) {
		List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
		model.addAttribute("orderStatus", dictListByType);
		return "modules/order/quasiIndustryDfpxmjl";
	}	
	

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = { "quasiIndustryDfpxmjl1", "" })
	public String quasiIndustryDfpxmjl1(Order2 order, HttpServletRequest request,String managerType, HttpServletResponse response, Model model) {
		order.setIsAllotManager("1");
		order.setProjectMode("4");
		order.setIsScrap("0");
		if(null == order.getEnginDepartId()){
			if(null!= UserUtils.getUser().getEmpId()){
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if(list != null && list.size()>0){
					order.setEnginDepartIds(list);
				}else{
					order.setEnginDepartIds(null);
				}
			} else {
				order.setEnginDepartIds(null);
			}
		}else{
			List<Integer> list = new ArrayList<>();
			list.add(order.getEnginDepartId());
			order.setEnginDepartIds(list);
		}
		if(UserUtils.getUser().getStoreId()!=null){

			order.setStoreId(UserUtils.getUser().getStoreId());
		}
		else{

			if(order.getStoreId()!=null && order.getStoreId().equals("1")){

					order.setStoreId(null);
			}
		}
		if(null == order.getProjectMode() || "".equals(order.getProjectMode())){
			User user = UserUtils.getUser();
			if(null !=user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
					order.setProjectMode(null);
				}else{
					order.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}
			}else{
				order.setProjectMode(null);
			}
		}
		Page<Order2> page = orderService2.findPage(new Page<Order2>(request, response), order);
		model.addAttribute("page", page);
		model.addAttribute("order", order);
		model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
		if(null!=managerType && !managerType.equals("")){
			model.addAttribute("managerType",managerType);
		}
		









		return "modules/order/quasiIndustryDfpxmjl";
	}
			

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = { "quasiIndustryYfpzjy", "" })
	public String quasiIndustryYfpzjy(Order2 order,String inspectorType, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
		List<String> orderStatus = new ArrayList<String>();
		orderStatus.add("105");
		orderStatus.add("110");
		orderStatus.add("120");
		orderStatus.add("125");
		order.setOrderStatus(orderStatus);
		model.addAttribute("orderStatus", dictListByType);
		return "modules/order/quasiIndustryYfpzjy";
	}
	
	@RequiresPermissions("order:order:view")
	@RequestMapping(value = "quasiIndustryYfpzjy1")
	public String quasiIndustryYfpzjy1(Order2 order, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		order.setProjectMode("4");
		order.setIsAllotInspector("0");
		List<String> orderStatus = order.getOrderStatus();
		if(null != orderStatus){
			String statusStr = orderStatus.toString();
			statusStr = statusStr.replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
			order.setOrderStatusNumber(statusStr);
		}
		if(null == order.getEnginDepartId()){
			if(null!= UserUtils.getUser().getEmpId()){
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if(list != null && list.size()>0){
					order.setEnginDepartIds(list);
				}else{
					order.setEnginDepartIds(null);
				}
			} else {
				order.setEnginDepartIds(null);
			}
		}else{
			List<Integer> list = new ArrayList<>();
			list.add(order.getEnginDepartId());
			order.setEnginDepartIds(list);
		}
		if(UserUtils.getUser().getStoreId()!=null){

			order.setStoreId(UserUtils.getUser().getStoreId());
		}
		else{

			if(order.getStoreId()!=null && order.getStoreId().equals("1")){

					order.setStoreId(null);
			}
		}
		if(null == order.getProjectMode() || "".equals(order.getProjectMode())){
			User user = UserUtils.getUser();
			if(null !=user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
					order.setProjectMode(null);
				}else{
					order.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}
			}else{
				order.setProjectMode(null);
			}
		}
		List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
		Page<Order2> page = orderService2.findPage(new Page<Order2>(request, response), order);
		model.addAttribute("page", page);
		model.addAttribute("order", order);
		model.addAttribute("orderStatus", dictListByType);
		model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
		return "modules/order/quasiIndustryYfpzjy";
	}
	
			

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = { "quasiIndustryDfpzjy", "" })
	public String quasiIndustryDfpzjy(Order2 order,String inspectorType, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<Dict> dictListByType = DictUtils.getDictListByType("order_status_new");
		model.addAttribute("orderStatus", dictListByType);
		return "modules/order/quasiIndustryDfpzjy";
	}	
	

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = { "checkPmPreIndustry", "" })
	@ResponseBody
	public Integer checkPmPreIndustry(Integer id,Integer itemManagerId,Model model) {
		Map<String,Object> paramaterMap = new HashMap<>();
		paramaterMap.put("orderId", id);
		paramaterMap.put("pmEmployeeId", itemManagerId);
		return orderService2.checkPmPreIndustry(paramaterMap);
	}	
	
	@RequiresPermissions("order:order:view")
	@RequestMapping(value = "quasiIndustryDfpzjy1")
	public String quasiIndustryDfpzjy1(Order2 order, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		order.setProjectMode("4");
		order.setIsAllotInspector("1");
		if(null == order.getEnginDepartId()){
			if(null!= UserUtils.getUser().getEmpId()){
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if(list != null && list.size()>0){
					order.setEnginDepartIds(list);
				}else{
					order.setEnginDepartIds(null);
				}
			} else {
				order.setEnginDepartIds(null);
			}
		}else{
			List<Integer> list = new ArrayList<>();
			list.add(order.getEnginDepartId());
			order.setEnginDepartIds(list);
		}
		if(UserUtils.getUser().getStoreId()!=null){

			order.setStoreId(UserUtils.getUser().getStoreId());
		}
		else{

			if(order.getStoreId()!=null && order.getStoreId().equals("1")){

					order.setStoreId(null);
			}
		}
		if(null == order.getProjectMode() || "".equals(order.getProjectMode())){
			User user = UserUtils.getUser();
			if(null !=user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
					order.setProjectMode(null);
				}else{
					order.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}
			}else{
				order.setProjectMode(null);
			}
		}
		Page<Order2> page = orderService2.findPage(new Page<Order2>(request, response), order);
		model.addAttribute("page", page);
		model.addAttribute("order", order);
		model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
		return "modules/order/quasiIndustryDfpzjy";
	}
	
		
}