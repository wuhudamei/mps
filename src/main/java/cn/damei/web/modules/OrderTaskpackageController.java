/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.OrderTaskpackage;
import cn.damei.entity.modules.TeamLeaderInfo;
import cn.damei.service.modules.OrderTaskpackageService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 派工管理Controller
 * 
 * @author wyb
 * @version 2016-09-12
 */
@Controller
@RequestMapping(value = "${adminPath}/scheduling/orderTaskpackage")
public class OrderTaskpackageController extends BaseController {

	@Autowired
	private OrderTaskpackageService orderTaskpackageService;	
	
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	@ModelAttribute
	public OrderTaskpackage get(@RequestParam(required = false) String id) {
		OrderTaskpackage entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = orderTaskpackageService.get(id);
		}
		if (entity == null) {
			entity = new OrderTaskpackage();
		}
		return entity;
	}

	// 按任务包派工人
	@RequiresPermissions("scheduling:orderTaskpackage:view")
	@RequestMapping(value = { "list", "" })
	public String list(OrderTaskpackage orderTaskpackage, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 过滤门店
		User user = UserUtils.getUser();
		if (orderTaskpackage.getStoreId() == null) {
			String storeId = user.getStoreId();
			if (StringUtils.isBlank(storeId)) {
				orderTaskpackage.setStoreId(null);
			} else {
				orderTaskpackage.setStoreId(storeId);
			}
		}
		// 过滤工程模式
		if (StringUtils.isBlank(orderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
					
		
		return "modules/scheduling/orderTaskpackageList";
	}
	// 按任务包派工人
	@RequiresPermissions("scheduling:orderTaskpackage:view")
	@RequestMapping(value = { "list2", "" })
	public String list2(OrderTaskpackage orderTaskpackage, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 不是管理员就不能查全部门店
		if (!UserUtils.getUser().getOffice().getId().equals("1")) {
			// 安心查自己门店吧
			orderTaskpackage.setStoreId(UserUtils.getUser().getStoreId());
		}
		// 过滤门店
		User user = UserUtils.getUser();
		if (orderTaskpackage.getStoreId() == null) {
			String storeId = user.getStoreId();
			if (StringUtils.isBlank(storeId)) {
				orderTaskpackage.setStoreId(null);
			} else {
				orderTaskpackage.setStoreId(storeId);
			}
		}
		// 过滤工程模式
		if (StringUtils.isBlank(orderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
			
		model.addAttribute("planStartDate", orderTaskpackage.getPlanStartdate());
		
		Page<OrderTaskpackage> page = orderTaskpackageService.findPage(new Page<OrderTaskpackage>(request, response),
				orderTaskpackage);
		List<OrderTaskpackage> list = page.getList();
		for(OrderTaskpackage otpa :list){
			String id = otpa.getOrderId();
			String on = orderTaskpackageService.findOrderNumber(Integer.valueOf(id));
			otpa.setOrderNumber(on);
		}
		
		model.addAttribute("page", page);
		return "modules/scheduling/orderTaskpackageList";
	}

	// 工人接单监控
	@RequiresPermissions("scheduling:orderTaskpackage:view")
	@RequestMapping(value = { "workerList", "" })
	public String workerList(OrderTaskpackage orderTaskpackage, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (orderTaskpackage.getStoreId() == null) {
			String storeId = user.getStoreId();
			if (StringUtils.isBlank(storeId)) {
				orderTaskpackage.setStoreId(null);
			} else {
				orderTaskpackage.setStoreId(storeId);
			}
		}
		// 过滤工程模式
		if (StringUtils.isBlank(orderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		return "modules/scheduling/workerReceiveMonitoringList";
	}
	
	// 工人接单监控
	@RequiresPermissions("scheduling:orderTaskpackage:view")
	@RequestMapping(value = { "workerListNew", "" })
	public String workerListNew(OrderTaskpackage orderTaskpackage, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (orderTaskpackage.getStoreId() == null) {
			String storeId = user.getStoreId();
			if (StringUtils.isBlank(storeId)) {
				orderTaskpackage.setStoreId(null);
			} else {
				orderTaskpackage.setStoreId(storeId);
			}
		}
		// 过滤工程模式
		if (StringUtils.isBlank(orderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		
		return "modules/scheduling/workerReceiveMonitoringListNew";
	}
	
	// 特殊--已派工人组
	@RequiresPermissions("scheduling:orderTaskpackage:view")
	@RequestMapping(value = { "specialworkerList", "" })
	public String specialworkerList(OrderTaskpackage orderTaskpackage, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (orderTaskpackage.getStoreId() == null) {
			String storeId = user.getStoreId();
			if (StringUtils.isBlank(storeId)) {
				orderTaskpackage.setStoreId(null);
			} else {
				orderTaskpackage.setStoreId(storeId);
			}
		}
		// 过滤工程模式
		if (StringUtils.isBlank(orderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		
		return "modules/scheduling/workerSpecialReceiveMonitoringListNew";
	}
	// 工人接单监控
	@RequiresPermissions("scheduling:orderTaskpackage:view")
	@RequestMapping(value = { "workerList2", "" })
	public String workerList2(OrderTaskpackage orderTaskpackage, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		int x = 0;
		// 不是管理员就不能查全部门店
		if (!UserUtils.getUser().getOffice().getId().equals("1")) {
			// 安心查自己门店吧
			orderTaskpackage.setStoreId(UserUtils.getUser().getStoreId());
			x++;
		}
		User user = UserUtils.getUser();
		// 过滤工程模式
		if (StringUtils.isBlank(orderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		
		Page<OrderTaskpackage> page = orderTaskpackageService.findPageMy(new Page<OrderTaskpackage>(request, response),
				orderTaskpackage);
		
		
		List<OrderTaskpackage> lis = new ArrayList<OrderTaskpackage>();
		
		//获取所有的任务包（遍历）
		List<OrderTaskpackage> list = page.getList();
		for (OrderTaskpackage otp : list) {
			
			//通过订单id查询订单编号
			String id = otp.getOrderId();
			String on = orderTaskpackageService.findOrderNumber(Integer.valueOf(id));
			otp.setOrderNumber(on);
			
			
			
			//获取页面输入的派工时间间隔
			String time = request.getParameter("time");
			int t = 0;
			if(time != null && !time.equals("")){
				t = Integer.valueOf(time).intValue()*60000;
				if(otp.getDispatchTime()!=null){
					
					Long s = new Date().getTime()-otp.getDispatchTime().getTime();
					int ss = s.intValue();
					//如果计算出的时间间隔大于等于页面输入的时间间隔
					if(ss>=t && "50".equals(otp.getPackageStateId()) || "55".equals(otp.getPackageStateId())){
						otp.setIsOvertime("1");
					}else{
						otp.setIsOvertime("0");
					}
					//把任务包对象放入新的lis集合中
					lis.add(otp);
				}
				
			}else{
				t=0;
				if(otp.getDispatchTime()!=null){
					
					Long s = new Date().getTime()-otp.getDispatchTime().getTime();
					int ss = s.intValue();
					if(ss > 3600000 && "50".equals(otp.getPackageStateId()) || "55".equals(otp.getPackageStateId())){
						otp.setIsOvertime("1");
					}else{
						otp.setIsOvertime("0");
					}
					//把任务包对象放入新的lis集合中
					lis.add(otp);
				}
			}
			
			
		}
		
		//把lis集合放入分页插件中
		page.setList(lis);
		
		if (x > 0) {
			orderTaskpackage.setStoreId(null);

		}
		
		
		model.addAttribute("page", page);
		return "modules/scheduling/workerReceiveMonitoringList";
	}
	// 已派工人组
	@RequiresPermissions("scheduling:orderTaskpackage:view")
	@RequestMapping(value = { "workerList2New", "" })
	public String workerList2New(OrderTaskpackage orderTaskpackage, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		int x = 0;
		// 不是管理员就不能查全部门店
		if (!UserUtils.getUser().getOffice().getId().equals("1")) {
			// 安心查自己门店吧
			orderTaskpackage.setStoreId(UserUtils.getUser().getStoreId());
			x++;
		}
		User user = UserUtils.getUser();
		// 过滤工程模式
		if (StringUtils.isBlank(orderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		
		Page<OrderTaskpackage> page = orderTaskpackageService.findPageMy(new Page<OrderTaskpackage>(request, response),
				orderTaskpackage);
		
		
		List<OrderTaskpackage> lis = new ArrayList<OrderTaskpackage>();
		
		//获取所有的任务包（遍历）
		List<OrderTaskpackage> list = page.getList();
		for (OrderTaskpackage otp : list) {
			
			//通过订单id查询订单编号
			String id = otp.getOrderId();
			String on = orderTaskpackageService.findOrderNumber(Integer.valueOf(id));
			otp.setOrderNumber(on);
			
			
			
			//获取页面输入的派工时间间隔
			String time = request.getParameter("time");
			int t = 0;
			if(time != null && !time.equals("")){
				t = Integer.valueOf(time).intValue()*60000;
				if(otp.getDispatchTime()!=null){
					
					Long s = new Date().getTime()-otp.getDispatchTime().getTime();
					int ss = s.intValue();
					//如果计算出的时间间隔大于等于页面输入的时间间隔
					if(ss>=t && "50".equals(otp.getPackageStateId()) || "55".equals(otp.getPackageStateId())){
						otp.setIsOvertime("1");
					}else{
						otp.setIsOvertime("0");
					}
					//把任务包对象放入新的lis集合中
					lis.add(otp);
				}
				
			}else{
				t=0;
				if(otp.getDispatchTime()!=null){
					
					Long s = new Date().getTime()-otp.getDispatchTime().getTime();
					int ss = s.intValue();
					if(ss > 3600000 && "50".equals(otp.getPackageStateId()) || "55".equals(otp.getPackageStateId())){
						otp.setIsOvertime("1");
					}else{
						otp.setIsOvertime("0");
					}
					//把任务包对象放入新的lis集合中
					lis.add(otp);
				}
			}
			
			
		}
		
		//把lis集合放入分页插件中
		page.setList(lis);
		
		if (x > 0) {
			orderTaskpackage.setStoreId(null);
			
		}
		
		
		model.addAttribute("page", page);
		return "modules/scheduling/workerReceiveMonitoringListNew";
	}
	// 特殊-已派工人组
	@RequiresPermissions("scheduling:orderTaskpackage:view")
	@RequestMapping(value = { "specialWorkerList2New", "" })
	public String specialWorkerList2New(OrderTaskpackage orderTaskpackage, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		int x = 0;
		// 不是管理员就不能查全部门店
		if (!UserUtils.getUser().getOffice().getId().equals("1")) {
			// 安心查自己门店吧
			orderTaskpackage.setStoreId(UserUtils.getUser().getStoreId());
			x++;
		}
		User user = UserUtils.getUser();
		// 过滤工程模式
		if (StringUtils.isBlank(orderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		
		Page<OrderTaskpackage> page = orderTaskpackageService.findSpecialPageMy(new Page<OrderTaskpackage>(request, response),
				orderTaskpackage);
		
		
		List<OrderTaskpackage> lis = new ArrayList<OrderTaskpackage>();
		
		//获取所有的任务包（遍历）
		List<OrderTaskpackage> list = page.getList();
		for (OrderTaskpackage otp : list) {
			
			//通过订单id查询订单编号
			String id = otp.getOrderId();
			String on = orderTaskpackageService.findOrderNumber(Integer.valueOf(id));
			otp.setOrderNumber(on);
			
			
			
			//获取页面输入的派工时间间隔
			String time = request.getParameter("time");
			int t = 0;
			if(time != null && !time.equals("")){
				t = Integer.valueOf(time).intValue()*60000;
				if(otp.getDispatchTime()!=null){
					
					Long s = new Date().getTime()-otp.getDispatchTime().getTime();
					int ss = s.intValue();
					//如果计算出的时间间隔大于等于页面输入的时间间隔
					if(ss>=t && "50".equals(otp.getPackageStateId()) || "55".equals(otp.getPackageStateId())){
						otp.setIsOvertime("1");
					}else{
						otp.setIsOvertime("0");
					}
					//把任务包对象放入新的lis集合中
					lis.add(otp);
				}
				
			}else{
				t=0;
				if(otp.getDispatchTime()!=null){
					
					Long s = new Date().getTime()-otp.getDispatchTime().getTime();
					int sb = s.intValue();
					int ss=  Math.abs(sb);
					if(ss > 3600000 && "50".equals(otp.getPackageStateId()) || "55".equals(otp.getPackageStateId())){
						otp.setIsOvertime("1");
					}else{
						otp.setIsOvertime("0");
					}
					//把任务包对象放入新的lis集合中
					lis.add(otp);
				}
			}
			
			
		}
		
		//把lis集合放入分页插件中
		page.setList(lis);
		
		if (x > 0) {
			orderTaskpackage.setStoreId(null);
			
		}
		
		
		model.addAttribute("page", page);
		return "modules/scheduling/workerSpecialReceiveMonitoringListNew";
	}

	// 工人组信息
	@RequiresPermissions("scheduling:orderTaskpackage:edit")
	@RequestMapping(value = "findById")
	public String findById(OrderTaskpackage orderTaskpackage, HttpServletRequest request, HttpServletResponse response,
			Model model) throws IOException {
		// 传入工人组id 查询 姓名 手机号,可接任务包及图片

		// 1:查询 组长的 头像, 真实姓名 和 手机号
		TeamLeaderInfo teamLeaderInfo = orderTaskpackageService
				.findTeamLeaderInfoByEmployeeGroupId(orderTaskpackage.getEmpGroupid());
	
			ArrayList<String>  list = new ArrayList<String>();
		// 2: 查询任务包的id
		String taskPackageIds = orderTaskpackageService
				.findTaskPackageByemployeeGroupId(orderTaskpackage.getEmpGroupid());
		if (null != taskPackageIds && !"".equals(taskPackageIds)) {

			String[] split = taskPackageIds.split(",");
			for (String taskPackageId : split) {
				// 得到每一个任务包的id ,查询出name
				String packageName = orderTaskpackageService.findPackageNameById(taskPackageId);
			
			list.add(packageName);
			}
		} else {
			// 工人组啥活都不会
		}

		// 3: 设置工人数
		
		teamLeaderInfo.setTeamNumber(orderTaskpackageService.findCountByWorkerId(orderTaskpackage.getEmpGroupid()));

		model.addAttribute("teamLeaderInfo", teamLeaderInfo);
		model.addAttribute("packageNameList", list);

		return "modules/scheduling/employeegroup";
	}
	
	/**
	 * 
	 */
	@RequestMapping(value = "viewByOrderID")
	public String viewByOrderID(String orderID,HttpServletRequest request, 
			HttpServletResponse response, Model model){
		logger.info("订单编号："+orderID);
		List<OrderTaskpackage> taskpackageList = null;
		
		if(!orderID.equals("")){
			taskpackageList = orderTaskpackageService.getByOrderIDList(Integer.valueOf(orderID));
		}
		if(taskpackageList!=null) {
			logger.info("返回任务包的个数：" + taskpackageList.size());
		}
		model.addAttribute("taskpackageList", taskpackageList);
		return "modules/bizcompleted/taskpackageDetail";
	}
	
	// 按任务包派工人---2017-08-31新需求
	@RequiresPermissions("scheduling:orderTaskpackage:view")
	@RequestMapping(value = { "listNew", "" })
	public String listNew(OrderTaskpackage orderTaskpackage, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 过滤门店
		User user = UserUtils.getUser();
		if (orderTaskpackage.getStoreId() == null) {
			String storeId = user.getStoreId();
			if (StringUtils.isBlank(storeId)) {
				orderTaskpackage.setStoreId(null);
			} else {
				orderTaskpackage.setStoreId(storeId);
			}
		}
		// 过滤工程模式
		if (StringUtils.isBlank(orderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
					
		
		return "modules/scheduling/orderTaskpackageListNew";
	}
	
	// 按任务包派工人---2017-08-31新需求
	@RequiresPermissions("scheduling:orderTaskpackage:view")
	@RequestMapping(value = { "specialListNew", "" })
	public String specialListNew(OrderTaskpackage orderTaskpackage, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 过滤门店
		User user = UserUtils.getUser();
		if (orderTaskpackage.getStoreId() == null) {
			String storeId = user.getStoreId();
			if (StringUtils.isBlank(storeId)) {
				orderTaskpackage.setStoreId(null);
			} else {
				orderTaskpackage.setStoreId(storeId);
			}
		}
		// 过滤工程模式
		if (StringUtils.isBlank(orderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		
		
		return "modules/scheduling/specialOrderTaskpackageList";
	}
	
	// 按任务包派工人
	@RequiresPermissions("scheduling:orderTaskpackage:view")
	@RequestMapping(value = { "list2New", "" })
	public String list2New(OrderTaskpackage orderTaskpackage, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 不是管理员就不能查全部门店
		if (!UserUtils.getUser().getOffice().getId().equals("1")) {
			// 安心查自己门店吧
			orderTaskpackage.setStoreId(UserUtils.getUser().getStoreId());
		}
		// 过滤门店
		User user = UserUtils.getUser();
		if (orderTaskpackage.getStoreId() == null) {
			String storeId = user.getStoreId();
			if (StringUtils.isBlank(storeId)) {
				orderTaskpackage.setStoreId(null);
			} else {
				orderTaskpackage.setStoreId(storeId);
			}
		}
		// 过滤工程模式
		if (StringUtils.isBlank(orderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
			
		model.addAttribute("planStartDate", orderTaskpackage.getPlanStartdate());
		
		int count = orderTaskpackageService.getUnOrderTaskPackage(orderTaskpackage);
		model.addAttribute("count", count);
		Page<OrderTaskpackage> page = orderTaskpackageService.findPage(new Page<OrderTaskpackage>(request, response),
				orderTaskpackage);
		//待分派任务包数量
		List<OrderTaskpackage> list = page.getList();
		for(OrderTaskpackage otpa :list){
			String id = otpa.getOrderId();
			String on = orderTaskpackageService.findOrderNumber(Integer.valueOf(id));
			otpa.setOrderNumber(on);
		}
		
		model.addAttribute("page", page);
		return "modules/scheduling/orderTaskpackageListNew";
	}
	
	// 按任务包派工人特殊
	@RequiresPermissions("scheduling:orderTaskpackage:view")
	@RequestMapping(value = { "specialList2New", "" })
	public String specialList2New(OrderTaskpackage orderTaskpackage, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 不是管理员就不能查全部门店
		if (!UserUtils.getUser().getOffice().getId().equals("1")) {
			// 安心查自己门店吧
			orderTaskpackage.setStoreId(UserUtils.getUser().getStoreId());
		}
		// 过滤门店
		User user = UserUtils.getUser();
		if (orderTaskpackage.getStoreId() == null) {
			String storeId = user.getStoreId();
			if (StringUtils.isBlank(storeId)) {
				orderTaskpackage.setStoreId(null);
			} else {
				orderTaskpackage.setStoreId(storeId);
			}
		}
		// 过滤工程模式
		if (StringUtils.isBlank(orderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		
		model.addAttribute("planStartDate", orderTaskpackage.getPlanStartdate());
		int count = orderTaskpackageService.getUnOrderTaskPackage(orderTaskpackage);
		model.addAttribute("count", count);
		Page<OrderTaskpackage> page = orderTaskpackageService.findPage(new Page<OrderTaskpackage>(request, response),
				orderTaskpackage);
		//待分派任务包数量
		List<OrderTaskpackage> list = page.getList();
		for(OrderTaskpackage otpa :list){
			String id = otpa.getOrderId();
			String on = orderTaskpackageService.findOrderNumber(Integer.valueOf(id));
			otpa.setOrderNumber(on);
		}
		
		model.addAttribute("page", page);
		return "modules/scheduling/specialOrderTaskpackageList";
	}
}