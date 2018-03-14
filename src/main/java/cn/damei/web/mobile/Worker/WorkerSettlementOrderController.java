package cn.damei.web.mobile.Worker;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.common.utils.*;
import cn.damei.service.modules.BizPhoneMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.config.Global;
import cn.damei.common.web.BaseController;
import cn.damei.common.SessionUtils;
import cn.damei.service.mobile.Manager.BizEvalRewardTaskpackService;
import cn.damei.entity.mobile.Manager.BizOrderTaskpackageProcedure;
import cn.damei.service.mobile.Manager.BizOrderTaskpackageProcedureService;
import cn.damei.entity.mobile.Worker.Message;
import cn.damei.service.mobile.Worker.MessageService;
import cn.damei.entity.mobile.Worker.EmployeeGroupRa;
import cn.damei.entity.mobile.Worker.EmployeeGroupVo;
import cn.damei.entity.mobile.Worker.OrderTaskpackageSettlementDetail;
import cn.damei.entity.mobile.Worker.SettlementAuxiliary;
import cn.damei.entity.mobile.Worker.TaskPackagePicture;
import cn.damei.service.mobile.Worker.EmployeeGroupRaService;
import cn.damei.service.mobile.Worker.EmployeeGroupVoService;
import cn.damei.service.mobile.Worker.OrderTaskpackageSettlementDetailService;
import cn.damei.service.mobile.Worker.SettlementAuxiliaryService;
import cn.damei.service.mobile.Worker.TaskPackagePictureService;
import cn.damei.entity.mobile.Worker.Worker;
import cn.damei.service.mobile.Worker.WorkerService;
import cn.damei.entity.mobile.Worker.WorkTaskPackage;
import cn.damei.service.mobile.Worker.WorkTaskPackageService;
import cn.damei.entity.mobile.home.CheckBreak;
import cn.damei.entity.mobile.home.CheckItem;
import cn.damei.entity.mobile.home.Report;
import cn.damei.service.mobile.home.HomeReportService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizMessagegroup;
import cn.damei.service.modules.BizMessagegroupService;
import cn.damei.entity.modules.BizOrderTaskpackageSettlement;
import cn.damei.service.modules.BizOrderTaskpackageSettlementService;


@Controller
@RequestMapping(value="${adminPath}/app/worker")
public class WorkerSettlementOrderController extends BaseController{
	
	@Autowired 
	private WorkTaskPackageService workTaskPackageService;
	@Autowired
	private BizOrderTaskpackageSettlementService bizOrderTaskpackageSettlementService;
	@Autowired
	private OrderTaskpackageSettlementDetailService orderTaskpackageSettlementDetailService;
	@Autowired
	private WorkerService workerService;
	@Autowired
	private EmployeeGroupRaService employeeGroupRaService;
	@Autowired
	private EmployeeGroupVoService employeeGroupVoService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizMessagegroupService bizMessagegroupService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private HomeReportService reportService;
	@Autowired
	private BizEvalRewardTaskpackService bizEvalRewardTaskpackService;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	/**
	 * 查询结算任务包 所有工人
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="salaryList")
	public String salaryList(Model model, HttpServletRequest request){
		
		Worker worker = SessionUtils.getWorkerSession(request);
		
		Integer employeeId = worker.getId(); 
		//根据员工id去查询工人组id
		EmployeeGroupRa employeeGroupRa = employeeGroupRaService.findByEmployeeId(employeeId);
		
		//根据工人组id查询组长的id
		EmployeeGroupVo employeeGroupVo = employeeGroupVoService.findById(employeeGroupRa.getGroupId());
		
		//根据组长id查询任务包
		List<WorkTaskPackage> list = workTaskPackageService.findTaskPackageForSettlement(employeeGroupVo.getGroupid());
		
		model.addAttribute("list", list);
		
		if(employeeGroupRa.getIsLead()==1){
			//组长
			return "mobile/modules/Worker/chief_salary";
		}else{
			//工人
			return "mobile/modules/Worker/worker_salary";
		}
	}
	
	/**
	 * 确认薪酬  工人
	 * @param id 任务包id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="confirmWorker")
	public String confirmWorker(Integer id,Model model, HttpServletRequest request){
		//查询工人信息
		Worker worker = SessionUtils.getWorkerSession(request);
		//查询任务包信息
		WorkTaskPackage workTaskPackage = workTaskPackageService.findTaskPackageById(id);
		BizEmployee2 employee = bizEmployeeService2.get(workTaskPackage.getManagerId());
		workTaskPackage.setManagerPhone(employee.getPhone());
		//结算单
		BizOrderTaskpackageSettlement bizOrderTaskpackageSettlement = bizOrderTaskpackageSettlementService.findByOrderTaskpackageId(id);
		//酬薪分配
		OrderTaskpackageSettlementDetail payment = orderTaskpackageSettlementDetailService.findByGroupIdAndTaskPackageId(worker.getId(),id);
		payment.setEmployeeName(worker.getRealname());
		model.addAttribute("workTaskPackage",workTaskPackage);
		model.addAttribute("bizOrderTaskpackageSettlement",bizOrderTaskpackageSettlement);
		model.addAttribute("payment",payment);
		
		return "mobile/modules/Worker/worker_salary_details";
	}
	
	
	/**
	 * 确认薪酬  组长
	 * @param id 任务包id
	 * @return
	 */
	@RequestMapping(value="confirmChief")
	public String confirmChief(Integer id,Integer settleStyle, Model model, HttpServletRequest request){
		
		DecimalFormat df = new DecimalFormat("#.00");
		
		OrderTaskpackageSettlementDetail paymentGroup = new OrderTaskpackageSettlementDetail();
		WorkTaskPackage workTaskPackage = workTaskPackageService.findTaskPackageById(id);
		BizEmployee2 employee = bizEmployeeService2.get(workTaskPackage.getManagerId());
		workTaskPackage.setManagerPhone(employee.getPhone());
		BizOrderTaskpackageSettlement bizOrderTaskpackageSettlement = bizOrderTaskpackageSettlementService.findByOrderTaskpackageId(id);
		List<OrderTaskpackageSettlementDetail> payments = orderTaskpackageSettlementDetailService.findByOrderTaskpackageId(id);
		
		for (OrderTaskpackageSettlementDetail paymentVo : payments) {
			Worker worker = workerService.get(paymentVo.getEmployeeId());
			paymentVo.setEmployeeName(worker.getRealname());
			if(paymentVo.getIsLeader().equals("1")){
				paymentGroup = paymentVo;
			}
		}
		
		double budgetTotalMoney = 0;
		double settleTotalMoney = 0;
		List<BizOrderTaskpackageProcedure> procedures = bizOrderTaskpackageProcedureService.queryOrderTaskpackageProcedure(id);
		for (BizOrderTaskpackageProcedure procedure : procedures) {
			double a = 0;
			double b = 0;
			if (settleStyle == 0 || settleStyle == 1) {// 包工包料
				a = procedure.getSettlementNumber()*procedure.getSynthesizePrice();//实际每道工序的价格
				b = procedure.getBudgetNumber()*procedure.getSynthesizePrice();//预计每道工序的价格
			} else {// 包工
				a = procedure.getSettlementNumber() * procedure.getLaborPrice();
				b = procedure.getBudgetNumber() * procedure.getLaborPrice();
			}
			procedure.setBudgetTotal(b);
			settleTotalMoney = settleTotalMoney + a;//实际总价
			budgetTotalMoney = budgetTotalMoney + b;//预计总价
		}
		settleTotalMoney = Double.parseDouble(df.format(settleTotalMoney));
		budgetTotalMoney = Double.parseDouble(df.format(budgetTotalMoney));

		// 评价奖励金额
		Double rewardAmount = bizEvalRewardTaskpackService.queryRewardAmount(id);

		model.addAttribute("rewardAmount", rewardAmount);
		model.addAttribute("settleTotalMoney", settleTotalMoney);
		model.addAttribute("bizOrderTaskpackageSettlement", bizOrderTaskpackageSettlement);
		model.addAttribute("workTaskPackage", workTaskPackage);
		model.addAttribute("payments",payments);
		model.addAttribute("paymentGroup", paymentGroup);
		
		if (settleStyle == 0 || settleStyle == 1) {// 包工包料
			return "mobile/modules/Worker/chief_salary_details";
		} else {
			return "mobile/modules/Worker/chief_salary_pkgwork_details";
		}
		
		
	}
	
	/**
	 * ajax接受时修改状态
	 * @param groupId
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="acceptSalary",method=RequestMethod.POST)
	public @ResponseBody String acceptSalary(Integer groupId,Integer taskPackageId,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		
		WorkTaskPackage workTaskPackage = workTaskPackageService.findTaskPackageById(taskPackageId);
		if(workTaskPackage.getPackageStateid().equals("110") || workTaskPackage.getPackageStateid().equals("120")){
			return "3";//工人已确认或者拒绝分配薪酬
		}
		Worker worker = SessionUtils.getWorkerSession(request);
		//根据任务id和员工id查询薪酬分配
		OrderTaskpackageSettlementDetail payment =orderTaskpackageSettlementDetailService.findByGroupIdAndTaskPackageId(worker.getId(),taskPackageId);
		//更新状态结算单明细 '1'表示接受
		orderTaskpackageSettlementDetailService.updateStatus(payment.getId(),"1",new Date());
		
		//根据任务包id查询结算单明细
		List<OrderTaskpackageSettlementDetail> payments = orderTaskpackageSettlementDetailService.findByOrderTaskpackageId(taskPackageId);
		
		boolean flag = true;
		for (OrderTaskpackageSettlementDetail paymentVo : payments) {
			if(paymentVo.getStatus().equals("2")){//未处理
				flag = false;
				break;
			}
		}
		
		
		BizEmployee2 employee = bizEmployeeService2.findEmployeeById(workTaskPackage.getGroupId());
		BizEmployee2 manager = bizEmployeeService2.get(workTaskPackage.getManagerId());
		//（任务包名称）工人已同意分配薪金（地点+业主名称+工期+工人组长+手机号） DateUtils.formatDate
		if(flag){
			
			//表示所有的人都已经同意薪酬分配
			//修改结算单状态
			workTaskPackageService.updateOrderTaskPackage(taskPackageId,"120","工人已确认分配金额");
			//给项目经理发短信--  订单（小区名-楼号-单元号-门牌号-客户姓名-手机号）的任务包（任务包名称），工人（工人组长-手机号）工人已确认薪酬，请登录APP查看详情。
			String content1 = "订单（"+workTaskPackage.getCustomerMessage()+"-"+workTaskPackage.getCustomerName()+"-"+workTaskPackage.getCustomerPhone()+"）的任务包（"+workTaskPackage.getPackageName()+"），工人（"+employee.getRealname()+"-"+employee.getPhone()+"）已确认薪酬，请登录APP查看详情。";
			bizPhoneMsgService.sendMessage(workTaskPackage.getManagerId(), manager.getPhone(),
					content1, SendMsgBusinessType.RELATED_BUSINESS_TYPE_200902, taskPackageId);

			Message message = new Message();
			message.setMsgTitle("工人确认薪酬");
			message.setMsgTime(new Date());
			message.setMsgContent(content1);
			message.setMsgType(MessagePushType.MSG_TYPE_1);
			message.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_100007001);
			message.setEmployeeId(workTaskPackage.getManagerId());
			message.setBusiIdInt(payment.getId());
			messageService.insert(message);
			
			
			//给结算员发短信-- 订单（小区名-楼号-单元号-门牌号-客户姓名-手机号）的任务包（任务包名称），项目经理（姓名-电话），工人（工人组长-手机号）工人已确认薪酬，请及时登录系统进行审核。
			BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(workTaskPackage.getStoreId()+"","7");
			if(bizMessagegroup != null){
				List<Integer> list = new ArrayList<Integer>();
				List<BizEmployee2> employeelist = null;
				String[] str = bizMessagegroup.getEmployees().split(",");
				for(String id: str){
					list.add(Integer.valueOf(id));
				}
				employeelist = bizEmployeeService2.getById(list);
				String content ="订单（"+workTaskPackage.getCustomerMessage()+"-"+workTaskPackage.getCustomerName()+"-"+workTaskPackage.getCustomerPhone()+"）的任务包（"+workTaskPackage.getPackageName()+"），项目经理（"+manager.getRealname()+"-"+manager.getPhone()+"），工人（"+employee.getRealname()+"-"+employee.getPhone()+"）工人已确认薪酬，请及时登录系统进行审核。";
				if(null != employeelist && employeelist.size()>0){
					for (BizEmployee2 bizEmployee2 : employeelist) {
						bizPhoneMsgService.sendMessage(bizEmployee2.getId(), bizEmployee2.getPhone(),
								content, SendMsgBusinessType.RELATED_BUSINESS_TYPE_200901, taskPackageId);
					}
				}
			}
			return "1";
		}else{
			//表示有人未处理
			return "2";
		}
	}
	/**
	 * 修改任务包的状态
	 * @param taskPackageId
	 * @param packageStateid
	 * @param packageStatename
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="updateOrderTaskPackageStatus")
	public String updateOrderTaskPackageStatus(Integer taskPackageId,String packageStateid,String packageStatename, HttpServletRequest request,HttpServletResponse response){
		
		workTaskPackageService.updateOrderTaskPackage(taskPackageId,packageStateid,packageStatename);
		
		return "redirect:" + Global.getAdminPath() + "/app/worker/salaryList";
	}
	
	/**
	 * 拒绝薪酬分配
	 * @param groupId
	 * @param taskPackageId
	 * @param packageStateid
	 * @param packageStatename
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value= "refuseSalary")
	public String refuseSalary(Integer groupId,Integer taskPackageId,String packageStateid,String packageStatename, HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		
		Worker worker = SessionUtils.getWorkerSession(request);
		
		WorkTaskPackage workTaskPackage = workTaskPackageService.findTaskPackageById(taskPackageId);
		
		if(workTaskPackage.getPackageStateid().equals("110") || workTaskPackage.getPackageStateid().equals("120")){
			return "redirect:" + Global.getAdminPath() + "/app/worker/salaryList";//工人已确认或者拒绝分配薪酬
		}
		
		OrderTaskpackageSettlementDetail payment = orderTaskpackageSettlementDetailService.findByGroupIdAndTaskPackageId(groupId,taskPackageId);
		
		orderTaskpackageSettlementDetailService.updateStatus(payment.getId(),"0",new Date());
		
		workTaskPackageService.updateOrderTaskPackage(taskPackageId,packageStateid,packageStatename);
		
		BizEmployee2 employee = bizEmployeeService2.findEmployeeById(workTaskPackage.getGroupId());
		BizEmployee2 manager = bizEmployeeService2.get(workTaskPackage.getManagerId());
	//	BizEmployee2 worker = bizEmployeeService2.get(workTaskPackage.getManagerId());
		
		//订单（小区名-楼号-单元号-门牌号-客户姓名-手机号）的任务包（任务包名称），工人（工人组长-手机号）工人已拒绝薪酬，请及时登录APP重新分配薪酬。
		String content1 = "订单（"+workTaskPackage.getCustomerMessage()+"-"+workTaskPackage.getCustomerName()+"-"+workTaskPackage.getCustomerPhone()+"）的任务包（"+workTaskPackage.getPackageName()+"），工人（"+employee.getRealname()+"-"+employee.getPhone()+"）已拒绝薪酬，请及时登录APP重新分配薪酬。";
		bizPhoneMsgService.sendMessage(workTaskPackage.getManagerId(), manager.getPhone(),
				content1, SendMsgBusinessType.RELATED_BUSINESS_TYPE_200903, taskPackageId);


		Message message = new Message();
		message.setMsgTitle("工人拒绝薪酬");
		message.setMsgTime(new Date());
		message.setMsgContent(content1);
		message.setMsgType(MessagePushType.MSG_TYPE_1);
		message.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_100007002);
		message.setEmployeeId(workTaskPackage.getManagerId());
		message.setBusiIdInt(payment.getId());
		messageService.insert(message);
		
		return "redirect:" + Global.getAdminPath() + "/app/worker/salaryList";
	}
	
	
	@Autowired
	private BizOrderTaskpackageProcedureService bizOrderTaskpackageProcedureService;
	
	@RequestMapping(value="account")
	public String  account(Integer id ,Integer settleStyle, Model model, HttpServletRequest request){
		
		double budgetTotalMoney = 0;
		double settleTotalMoney = 0;
		WorkTaskPackage workTaskPackage = workTaskPackageService.findTaskPackageById(id);
		BizOrderTaskpackageSettlement bizOrderTaskpackageSettlement = bizOrderTaskpackageSettlementService.findByOrderTaskpackageId(id);
		List<BizOrderTaskpackageProcedure> procedures = bizOrderTaskpackageProcedureService.queryOrderTaskpackageProcedure(id);
		for (BizOrderTaskpackageProcedure procedure : procedures) {
			double a = 0;
			double b = 0;
			if (settleStyle == 0 || settleStyle == 1) {//包工包料
				a = procedure.getSettlementNumber()*procedure.getSynthesizePrice();//实际每道工序的价格
				b = procedure.getBudgetNumber()*procedure.getSynthesizePrice();//预计每道工序的价格
			} else {// 包工
				a = procedure.getSettlementNumber() * procedure.getLaborPrice();
				b = procedure.getBudgetNumber() * procedure.getLaborPrice();
			}
			
			procedure.setBudgetTotal(b);
			settleTotalMoney = settleTotalMoney + a;//结算总价
			budgetTotalMoney = budgetTotalMoney + b;//预计总价
		}

		// 评价奖励金额
		Double rewardAmount = bizEvalRewardTaskpackService.queryRewardAmount(id);

		model.addAttribute("rewardAmount",rewardAmount);
		model.addAttribute("settleTotalMoney",settleTotalMoney);
		model.addAttribute("budgetTotalMoney",budgetTotalMoney);
		model.addAttribute("procedures", procedures);
		model.addAttribute("workTaskPackage", workTaskPackage);
		model.addAttribute("bizOrderTaskpackageSettlement", bizOrderTaskpackageSettlement);
		model.addAttribute("settleStyle", settleStyle);
		
		if (settleStyle == 0 || settleStyle == 1) { // 包工包料
			return "mobile/modules/Worker/account";
		} else { // 包工
			return "mobile/modules/Worker/account_pkgwork";
		}
	}
	
	
	
	@RequestMapping(value="accountDetails")
	public String accountDetails(Integer id,Integer settleStyle, Model model, HttpServletRequest request){
		double budgetTotalMoney = 0;
		double realTotalMoney = 0;
		
		List<BizOrderTaskpackageProcedure> procedures = bizOrderTaskpackageProcedureService.queryOrderTaskpackageProcedure1(id);
		
		if(procedures !=null && procedures.size()>0){
			for (BizOrderTaskpackageProcedure procedure : procedures) {
				double a = 0d;
				double b = 0d;
				if (settleStyle == 0 || settleStyle == 1) { // 包工包料
					a = procedure.getRealNumber()*procedure.getSynthesizePrice();//实际每道工序的价格
					b = procedure.getBudgetNumber()*procedure.getSynthesizePrice();//预计每道工序的价格
				} else {// 包工
					a = procedure.getRealNumber() * procedure.getLaborPrice();
					b = procedure.getBudgetNumber() * procedure.getLaborPrice();
				}
				procedure.setBudgetTotal(b);
				realTotalMoney = realTotalMoney + a;//实际总价
				budgetTotalMoney = budgetTotalMoney + b;//预计总价
			}
		}
		model.addAttribute("id", id);
		model.addAttribute("procedures",procedures);
		model.addAttribute("realTotalMoney",realTotalMoney);
		model.addAttribute("budgetTotalMoney",budgetTotalMoney);
		
		if (settleStyle == 0 || settleStyle == 1) { // 包工包料
			return "mobile/modules/Worker/account_details";
		} else {// 包工
			return "mobile/modules/Worker/account_pkgwork_details";
		}
		
	}
	
	@Autowired
	private SettlementAuxiliaryService settlementAuxiliaryService;
	//辅料详情
	@RequestMapping(value="auxiliaryDetails")
	public String auxiliaryDetails(Integer id ,Model model, HttpServletRequest request){
		double tatolPrice = 0;
		DecimalFormat df = new DecimalFormat("#.00");
		//根据任务包id查询辅材
		List<SettlementAuxiliary> auxiliarys = settlementAuxiliaryService.findAuxiliaryListForSettlement(id);
		for (SettlementAuxiliary settlementAuxiliary : auxiliarys) {
			tatolPrice = settlementAuxiliary.getPrice() + tatolPrice;
		}
		tatolPrice = Double.parseDouble(df.format(tatolPrice));
		model.addAttribute("id", id);
		model.addAttribute("tatolPrice",tatolPrice);
		model.addAttribute("auxiliarys",auxiliarys);
		return "mobile/modules/Worker/auxiliary_details";
	}
	
	   //沙子水泥详情
		@RequestMapping(value="sandDetails")
		public String sandDetails(Integer id ,Model model, HttpServletRequest request){
			double tatolPrice = 0;
			DecimalFormat df = new DecimalFormat("#.00");
			//根据任务包id查询沙子水泥
			List<SettlementAuxiliary> sands = settlementAuxiliaryService.findSandListForSettlement(id);
			for (SettlementAuxiliary settlementAuxiliary : sands) {
				tatolPrice = settlementAuxiliary.getPrice() + tatolPrice;
			}
			tatolPrice = Double.parseDouble(df.format(tatolPrice));
			model.addAttribute("id", id);
			model.addAttribute("tatolPrice",tatolPrice);
			model.addAttribute("sands",sands);
			return "mobile/modules/Worker/sand_details";
		}
	
	@RequestMapping(value="punishDetails")
	public String punishDetails(Integer id, Model model,HttpServletRequest request){
		List<Report> reports = reportService.queryQcBillList(id);
		//List<List<CheckItem>> items = new ArrayList<List<CheckItem>>(); ;
		for (Report report : reports) {
			for (CheckItem checkItem : report.getCheckItemList()) {
				List<CheckBreak> checkBreakList = reportService.queryCheckBreaks(checkItem.getQcBillItemId());
				checkItem.setCheckBreakList(checkBreakList);
			}
		}
		model.addAttribute("reports",reports);
		return "mobile/modules/Worker/punish_details";
	}
	
	@Autowired
	private TaskPackagePictureService taskPackagePictureService;
	//图片
	@RequestMapping(value="seePhoto")
	public String seePhoto(Integer id,Integer settleStyle, Model model, HttpServletRequest request) throws IOException{
		//根据任务包查询图片
		List<TaskPackagePicture> pictures =	taskPackagePictureService.findPicturesByPackageId(id);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("id", id);
		model.addAttribute("pictures",pictures);
		model.addAttribute("settleStyle",settleStyle);
		return "mobile/modules/Worker/photo";
	}
}