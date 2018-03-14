/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.mapper.JsonMapper;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.Order;
import cn.damei.entity.modules.Order2;
import cn.damei.service.modules.OrderService;
import cn.damei.service.modules.OrderService2;
import cn.damei.entity.modules.OrderTaskpack;
import cn.damei.entity.modules.OrderTaskpackGenVo;
import cn.damei.entity.modules.OrderTaskpackVo;
import cn.damei.service.modules.OrderTaskpackService;
import cn.damei.service.modules.OrderTaskpackServiceGenVo;
import cn.damei.service.modules.OrderTaskpackServiceVo;
import cn.damei.service.modules.TaskpackageTemplatService;
import cn.damei.entity.modules.BizOrderTaskpackage;
import cn.damei.service.modules.BizOrderTaskpackageService;
import cn.damei.service.modules.OrderTaskpackageProcedureService;
import cn.damei.service.modules.BizProcedureService;
import cn.damei.service.modules.OrderTaskpackageService;
import cn.damei.service.modules.BizSeiralnumService;
import cn.damei.entity.modules.Dict;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import cn.damei.entity.modules.BizTaskPackageTemplatBugetAmount;
import cn.damei.service.modules.BizTaskPackageTemplatBugetAmountService;

/**
 * 订单管理Controller
 * @author llp
 * @version 2016-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/ordertaskpack/orderTaskpack")
public class OrderTaskpackController extends BaseController {

	@Autowired
	private OrderTaskpackService orderTaskpackService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private OrderTaskpackServiceVo orderTaskpackServiceVo;
	@Autowired
	private OrderTaskpackServiceGenVo orderTaskpackServiceGenVo;
	@Autowired
	private OrderTaskpackageService OrderTaskpackageService;//订单任务包
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderService2 orderService2;
	@Autowired
	private BizProcedureService bizProcedureService;
	@Autowired
	private TaskpackageTemplatService taskpackageTemplatService;
	@Autowired
	private OrderTaskpackageProcedureService orderTaskpackageProcedureService;//订单任务包工序
	@Autowired
	private BizSeiralnumService bizSeiralnumService;
	@Autowired
	private OrderTaskpackageService orderTaskpackageService;
	@Autowired
	private BizTaskPackageTemplatBugetAmountService bizTaskPackageTemplatBugetAmountService;
	@Autowired
	private BizOrderTaskpackageService bizOrderTaskpackageService;
	
	@ModelAttribute
	public OrderTaskpack get(@RequestParam(required=false) String id) {
		OrderTaskpack entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orderTaskpackService.get(id);
		}
		if (entity == null){
			entity = new OrderTaskpack();
		}
		return entity;
	}
	
	@RequiresPermissions("ordertaskpack:orderTaskpack:view")
	@RequestMapping(value = {"preList", ""})
	public String preList(OrderTaskpack orderTaskpack, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		//过滤区域
		if(null == orderTaskpack.getEnginDepartId()){
			if(null!= UserUtils.getUser().getEmpId()){
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if(list != null && list.size()>0){
					orderTaskpack.setEnginDepartIds(list);
				}else{
					orderTaskpack.setEnginDepartIds(null);
				}
			} else {
				orderTaskpack.setEnginDepartIds(null);
			}
		}else{
			List<Integer> list = new ArrayList<>();
			list.add(orderTaskpack.getEnginDepartId());
			orderTaskpack.setEnginDepartIds(list);
		}
		//过滤门店
		if(null == orderTaskpack.getStoreId()){
			if(null != user.getStoreId()){
				orderTaskpack.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(orderTaskpack.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						orderTaskpack.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}else{
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						orderTaskpack.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		List<String> orderStatusNumbers = orderTaskpack.getOrderStatusNumbers();
		if(null != orderStatusNumbers){
			String statusStr = orderStatusNumbers.toString();
			statusStr = statusStr.replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
			orderTaskpack.setOrderStatusNumber(statusStr);
		}
		List<Dict> dictListByType = orderTaskpackService.getOrderStatus(Integer.valueOf(110));
		
		List<String> list = new ArrayList<String>();
		list.add("110");
		list.add("120");
		list.add("125");
		list.add("130");
		orderTaskpack.setOrderStatusNumbers(list);
		
		model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
		model.addAttribute("orderTaskpack", orderTaskpack);
		model.addAttribute("orderStatusNumbers", dictListByType);
		return "modules/ordertaskpack/orderTaskpackList";
	}
	
	// 已生成任务包的订单
	@RequiresPermissions("ordertaskpack:orderTaskpack:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrderTaskpack orderTaskpack,String taskpackType, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		//过滤区域
		if(null == orderTaskpack.getEnginDepartId()){
			if(null!= UserUtils.getUser().getEmpId()){
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if(list != null && list.size()>0){
					orderTaskpack.setEnginDepartIds(list);
				}else{
					orderTaskpack.setEnginDepartIds(null);
				}
			} else {
				orderTaskpack.setEnginDepartIds(null);
			}
		}else{
			List<Integer> list = new ArrayList<>();
			list.add(orderTaskpack.getEnginDepartId());
			orderTaskpack.setEnginDepartIds(list);
		}
		//过滤门店
		if(null == orderTaskpack.getStoreId()){
			if(null != user.getStoreId()){
				orderTaskpack.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(orderTaskpack.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						orderTaskpack.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}else{
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						orderTaskpack.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		List<String> orderStatusNumbers = orderTaskpack.getOrderStatusNumbers();
		if(null != orderStatusNumbers){
			String statusStr = orderStatusNumbers.toString();
			statusStr = statusStr.replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
			orderTaskpack.setOrderStatusNumber(statusStr);
		}
		List<Dict> dictListByType = orderTaskpackService.getOrderStatus(Integer.valueOf(110));
		
		orderTaskpack.setOrdertaskpackStatus("1");// 设置生成任务包状态为1：已生成
		orderTaskpack.setOrderStatusNumber("110");// 设置订单状态
		
		Page<OrderTaskpack> page = orderTaskpackService.myFindPage(new Page<OrderTaskpack>(request, response), orderTaskpack);
		model.addAttribute("page", page);
		model.addAttribute("orderStatusNumbers", dictListByType);
		model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
		model.addAttribute("orderTaskpack", orderTaskpack);
		if(null!=taskpackType && !taskpackType.equals("")){
			model.addAttribute("taskpackType", taskpackType);
		}
		return "modules/ordertaskpack/orderTaskpackList";
	}
	
	// 待生成任务包的订单
	@RequiresPermissions("ordertaskpack:orderTaskpack:view")
	@RequestMapping(value = {"stayList", ""})
	public String stayList(OrderTaskpack orderTaskpack,String taskpackType, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		//过滤区域
		if(null == orderTaskpack.getEnginDepartId()){
			if(null!= UserUtils.getUser().getEmpId()){
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if(list != null && list.size()>0){
					orderTaskpack.setEnginDepartIds(list);
				}else{
					orderTaskpack.setEnginDepartIds(null);
				}
			} else {
				orderTaskpack.setEnginDepartIds(null);
			}
		}else{
			List<Integer> list = new ArrayList<>();
			list.add(orderTaskpack.getEnginDepartId());
			orderTaskpack.setEnginDepartIds(list);
		}
		//过滤门店
		if(null == orderTaskpack.getStoreId()){
			if(null != user.getStoreId()){
				orderTaskpack.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(orderTaskpack.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						orderTaskpack.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}else{
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						orderTaskpack.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		orderTaskpack.setOrdertaskpackStatus("0");// 设置生成任务包状态为0：未生成
		Integer stayCount = orderTaskpackService.getStayOrdCount(orderTaskpack);
		
		Page<OrderTaskpack> page = orderTaskpackService.findPage(new Page<OrderTaskpack>(request, response), orderTaskpack); 
		model.addAttribute("page", page);
		model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
		model.addAttribute("orderTaskpack", orderTaskpack);
		model.addAttribute("stayCount", stayCount);
		if(null!=taskpackType && !taskpackType.equals("")){
			model.addAttribute("taskpackType", taskpackType);
		}
		return "modules/ordertaskpack/orderTaskpackStayList";
	}

	@RequiresPermissions("ordertaskpack:orderTaskpack:view")
	@RequestMapping(value = "form")
	public String form(OrderTaskpackVo orderTaskpack, Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) throws ParseException {
		String mark = request.getParameter("mark");//标识1=生成任务包，2=查看详情
		String id = request.getParameter("id");//订单id
		String storeId = request.getParameter("storeId");//门店Id
		String projectMode = request.getParameter("projectMode");//工程模式
		
		logger.info("标识："+mark+"\t order_taskpackage_id:" + id);
		List<OrderTaskpackVo> list = null;
		List<OrderTaskpackGenVo> listGen = null;
		if(mark.equals("1")){
			
			//1.校验数据是否正确
			Order2 order2 = orderService2.get(Integer.valueOf(id));
			String messageVerification = orderService2.verificationPrepareOrder(order2);
			if(null!=messageVerification && messageVerification.equals("error")){
				//addMessage(redirectAttributes, "生成任务包失败，请完善该订单数据，再操作此功能");
				String taskpackType = "1";
				return "redirect:" + Global.getAdminPath() + "/ordertaskpack/orderTaskpack/list?taskpackType="+taskpackType;
			}
			
			
			//生成任务包
			String contractStartDate = request.getParameter("contractStartDate");//合同开始时间
			logger.info("contractStartDate==="+contractStartDate);
			listGen = orderTaskpackServiceGenVo.getByOrderIdAndTaskpacksgeId(contractStartDate,storeId,storeId,projectMode);//根据合同开始时间、门店查询对应的任务包--工序--工序价格
			logger.info("查询出来的数据为："+listGen.size());
			
			Order order = orderService.getByIdAndStoreId(id,storeId);
			
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("storeId", storeId);
			param.put("projectMode", projectMode);
			param.put("area", order.getContractArea());
			param.put("storeOrder", 1);
			
			List<BizTaskPackageTemplatBugetAmount> templatBugetAmountList =bizTaskPackageTemplatBugetAmountService.queryTaskPackageTemplatByParam(param);
			
			
			List<OrderTaskpackGenVo> taskpackageTemplatList = orderTaskpackServiceGenVo.getTemplatByOrderIdAndTaskpacksgeId(contractStartDate,storeId,storeId,projectMode);
			
			model.addAttribute("taskpackageTemplatList",taskpackageTemplatList);
			model.addAttribute("countAll", listGen.size());//查询总数
			model.addAttribute("storeId", storeId);//门店ID
			model.addAttribute("projectMode",projectMode);//工程模式
			model.addAttribute("order", order);//订单
			model.addAttribute("orderTaskpackGenList", listGen);
			model.addAttribute("orderId", id);//订单ID
			model.addAttribute("templatBugetAmountMaxList",templatBugetAmountList);
			return "modules/ordertaskpack/orderTaskpackGen";
		}else{//显示详情
			Order order = orderService.getByIdAndStoreId(id,storeId);
			list = orderTaskpackServiceVo.getByOrderIdAndTaskpacksgeId(id);
			BizOrderTaskpackage taskpackage = new BizOrderTaskpackage();
			taskpackage.setOrderId(Integer.valueOf(id));
			taskpackage.setEnginDepartIds(null);
			taskpackage.setStatusList(null);
			Double totalAmount= 0.00;
			List<BizOrderTaskpackage> orderTaskpackagekList = bizOrderTaskpackageService.findList(taskpackage);
			if(orderTaskpackagekList != null && orderTaskpackagekList.size()>0){
				for(BizOrderTaskpackage task : orderTaskpackagekList){
					totalAmount = totalAmount + task.getLaborAuxiliaryMaterialsBudgetAmount();
				}
				model.addAttribute("orderTaskpackagekList",orderTaskpackagekList);
			}
			model.addAttribute("totalAmount",totalAmount);
			model.addAttribute("order", order);
			model.addAttribute("orderTaskpackList", list);
			return "modules/ordertaskpack/orderTaskpackDetail";
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequiresPermissions("ordertaskpack:orderTaskpack:edit")
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public @ResponseBody String save(OrderTaskpackGenVo orderTaskpack, Model model, RedirectAttributes redirectAttributes,
			HttpServletRequest request) throws Exception {
		String orderId = request.getParameter("orderId");//订单ID
		String storeId = request.getParameter("storeId");//门店ID
		String projectMode = request.getParameter("projectMode");//工程模式
		String json1 = request.getParameter("val");//JSON数据
		return orderTaskpackageService.createTaskpackage(orderTaskpack, orderId, storeId, projectMode, json1);
	}

	
	/**
	 * 创建特殊任务包
	 * @param id
	 * @return
	 */
	@RequestMapping(value="toCreateSpecialTaskpackage")
	public String toCreateSpecialTaskpackage(String id,Model model){
		Order2 order = orderService2.get(Integer.parseInt(id));
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("storeId", order.getStoreId());
		param.put("projectMode", order.getProjectMode());
		param.put("area", order.getContractArea());
		param.put("storeOrder", "0");
		List<BizTaskPackageTemplatBugetAmount> templatBugetAmountList =bizTaskPackageTemplatBugetAmountService.queryTaskPackageTemplatByParam(param);
		model.addAttribute("order",order);
		if(templatBugetAmountList!= null && templatBugetAmountList.size()>0){
			model.addAttribute("templatBugetAmountMax",templatBugetAmountList.get(0));
		}
		
		return "modules/ordertaskpack/createSpecialTaskpackagePage";
	}
	
	/*@RequestMapping(value="addProcedure")
	@ResponseBody
	public String addProcedure(String procedureNo){
		
		System.out.println("addProcedure ok");
		return null;
	}*/
	
	@RequestMapping(value="procedures")
	@ResponseBody
	public String procedures(HttpServletRequest request, HttpServletResponse response){
		List<DropModel> list = bizProcedureService.findAllProcedure();
		return JsonMapper.getInstance().toJson(list);
	}
	
	@RequestMapping(value="getProcedureById")
	@ResponseBody
	public String getProcedureById(String procedureId,Integer orderId){
		Order2 order = orderService2.get(orderId);
		OrderTaskpackVo procedure = bizProcedureService.findProcedureById(procedureId,order.getStoreId(),order.getProjectMode(),order.getContractStartDate());
		if(procedure != null){
			procedure.setPackageName("特殊任务包");
			return JsonMapper.getInstance().toJson(procedure);
		}else{
			return null;
		}
//		System.out.println(procedure);
		//JsonMapper.getInstance().toJson();
		
	}
	
	/**
	 * 生成特殊任务包
	 * @param packageName
	 * @param parmeters
	 * @param startDate
	 * @param endDate
	 * @param orderId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="createTaskpackage")
	@ResponseBody
	public String createTaskpackage(String packageName,String parmeters,String startDate,String endDate,Integer orderId,HttpServletRequest request) throws Exception{
		String flag = "error";
		/*try{
			SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
			Date startDate1 = sdf.parse(startDate);
			Date endDate1 = sdf.parse(endDate);
			OrderDetail2 order = orderService2.get(orderId);
			//根据特殊任务包查询特殊的任务包  门店
			TaskpackageTemplat taskpackageTemplat = taskpackageTemplatService.findByStoreId(Integer.parseInt(order.getStoreId()),Integer.parseInt(order.getProjectMode()));
			OrderTaskpackage taskpackage = new OrderTaskpackage();
			taskpackage.setOrderId(order.getId().toString());
			taskpackage.setStoreId(order.getStoreId());
			taskpackage.setProjectMode(order.getProjectMode());
			taskpackage.setPackageCode(taskpackageTemplat.getNo());
			taskpackage.setPackageName(packageName);//--页面传过来
			taskpackage.setPlanStartdate(startDate1);
			taskpackage.setPlanEnddate(endDate1);
			taskpackage.setPackageStateId(ConstantUtils.ORDERTASKPACKAGE_ID_CREATE);
			taskpackage.setPackageStatename(ConstantUtils.ORDERTASKPACKAGE_ID_CREATE_REMARKS);
			Integer managerId = order.getItemManagerId();
			if(managerId == null){
				taskpackage.setItemManagerId(null);
				taskpackage.setItemCustomer(null);
			}else{
				taskpackage.setItemManagerId(order.getItemManagerId().toString());
				taskpackage.setItemCustomer(order.getItemManager());
			}
			taskpackage.setCustomerName(order.getCustomerName());
			taskpackage.setCustomerPhone(order.getCustomerPhone());
			taskpackage.setCustomerMessage(order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom());
			taskpackage.setTaskTackageTempId(taskpackageTemplat.getId());
			String code = bizSeiralnumService.getDateSequence("RW");
			taskpackage.setOrderTaskPackageCode(code);
			taskpackage.setTaskPackageType(taskpackageTemplat.getTaskPackageTypeId());
			taskpackage.setIsOvertime("0");
			Double total = 0.0;
			Double laborBudgetAmount = 0.0;
			Double auxiliaryMaterialsBudgetAmount = 0.0;
			List<OrderTaskpackGenVo> list = new ArrayList<OrderTaskpackGenVo>();
			String[] parmeter = parmeters.split("####");
			for (String parme : parmeter) {
				String[] split = parme.split("-");  //split[0]-id split[1]-number split[2]-total
				OrderTaskpackGenVo taskpackageProcedure = bizProcedureService.findTaskpackageProcedureById(split[0],order.getStoreId(),order.getContractStartDate());
				taskpackageProcedure.setBudgetNumber(split[1]);
				taskpackageProcedure.setLaborAuxiliaryMaterialsBudgetAmount(split[2]);
				taskpackageProcedure.setRemarks(split[3]);
				taskpackageProcedure.setProjectMode(order.getProjectMode());
				taskpackageProcedure.setLaborBudgetAmount(String.valueOf(split[4]));//人工费预算金额
				taskpackageProcedure.setAuxiliaryMaterialsBudgetAmount(String.valueOf(split[5]));//辅料费预算金额
				total = total+ Double.parseDouble(split[2]);
				laborBudgetAmount = laborBudgetAmount + Double.parseDouble(split[4]);
				auxiliaryMaterialsBudgetAmount = auxiliaryMaterialsBudgetAmount + Double.parseDouble(split[5]);
				list.add(taskpackageProcedure);
			}
			taskpackage.setLaborAuxiliaryMaterialsBudgetAmount(total);// 任务包工料费预算总金额
			taskpackage.setLaborBudgetAmount(laborBudgetAmount);// 任务包人工费预算总金额
			taskpackage.setAuxiliaryMaterialsBudgetAmount(auxiliaryMaterialsBudgetAmount);// 任务包辅料费预算总金额
			taskpackage.setSettleStyle(taskpackageTemplat.getSettleStyle());
			taskpackage.preInsert();
			orderTaskpackageService.insertTaskpackage(taskpackage);
			OrderTaskpackage tp = orderTaskpackageService.findPackageByCode(code);
			for(OrderTaskpackGenVo vo :list){
				vo.setTaskpackageId(tp.getId());
				vo.setPackageName(tp.getPackageName());
				vo.preInsert();
				//orderTaskpackageService.insertProcedure(vo);
			}
			orderTaskpackageService.insertProcedureList(list);
			flag = "success";
		}catch(Exception e){
			e.printStackTrace();
			flag ="error";
			throw e;
		}*/
		flag = orderTaskpackageService.createSpecialTaskPackage( packageName, parmeters, startDate, endDate, orderId);
		return flag;
	}
}