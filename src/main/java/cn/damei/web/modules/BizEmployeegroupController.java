/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.mapper.JsonMapper;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.dao.modules.BizBizEmployeegroupVoDao;
import cn.damei.entity.modules.BizEmgrouprelation;
import cn.damei.entity.modules.BizEmployeeForGroup;
import cn.damei.entity.modules.BizEmployeeStarLog;
import cn.damei.entity.modules.BizEmployeegroup;
import cn.damei.entity.modules.BizEmployeegroupVO;
import cn.damei.entity.modules.DropModel;
import cn.damei.service.modules.BizEmGroupRelationService;
import cn.damei.service.modules.BizEmployeegroupService;
import cn.damei.service.modules.BizEmployeegroupVoService;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.Office;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.DictUtils;
import cn.damei.common.utils.DropUtils;
import cn.damei.common.utils.UserUtils;

/**
 * 工人组管理Controller
 * 
 * @author qhy
 * @version 2016-09-01
 */
@Controller
@RequestMapping(value = "${adminPath}/empgroup/bizEmployeegroup")
public class BizEmployeegroupController extends BaseController {

	@Autowired
	private BizEmployeegroupService bizEmployeegroupService;

	@Autowired
	private BizEmployeegroupVoService bizEmployeegroupVoService;

	@Autowired
	private BizEmGroupRelationService bizEmGroupRelationService;

	@Autowired
	private BizEmployeeService bizEmployeeService;

	@Autowired
	private BizEmployeeService2 bizEmployeeService2;

	@Autowired
	private BizBizEmployeegroupVoDao bizBizEmployeegroupVoDao;
	@ModelAttribute
	public BizEmployeegroup get(@RequestParam(required = false) String id) {
		BizEmployeegroup entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = bizEmployeegroupService.get(id);
			// 关联数据
			BizEmgrouprelation relation = new BizEmgrouprelation();
			relation.setGroupId(id);
			List<BizEmgrouprelation> empGropRelation = bizEmGroupRelationService.findList(relation);
			for (BizEmgrouprelation reg : empGropRelation) {
				BizEmployee manager = bizEmployeeService.get(reg.getManagerId());
				if (manager != null) {
					reg.setManagerName(manager.getRealname());
				}
			}
			entity.setEmpGropRelation(empGropRelation);
		}
		if (entity == null) {
			entity = new BizEmployeegroup();
		}
		return entity;
	}

	@RequiresPermissions("empgroup:bizEmployeegroup:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizEmployeegroupVO bizEmployeegroup, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (UserUtils.getUser().getStoreId() != null) {
			// 当前登录用户门店
			bizEmployeegroup.setStoreId(UserUtils.getUser().getStoreId());
		} else {
			// 门店是总部的查询所有部门信息
			if (bizEmployeegroup.getStoreId() == null || bizEmployeegroup.getStoreId().equals("1")) {
				bizEmployeegroup.setStoreId("2");
			}
		}
		/**
		 * 工程模式控制
		 */
		User user = UserUtils.getUser();
		String projectMode = user.getProjectMode();
		if(projectMode.equals("3") || projectMode.equals("2")){
			/*bizEmployeegroup.setProjectMode(null);*/
		}else{
			bizEmployeegroup.setProjectMode(projectMode);
			model.addAttribute("projectModeEnable", true);
		}
		

		// 区域
		if (StringUtils.isBlank(bizEmployeegroup.getElactricationId())) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizEmployeegroup.setEnginDepartIds(list);
				} else {
					bizEmployeegroup.setEnginDepartIds(null);
				}
			} else {
				bizEmployeegroup.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(Integer.parseInt(bizEmployeegroup.getElactricationId()));
			bizEmployeegroup.setEnginDepartIds(list);
		}

		Page<BizEmployeegroupVO> page = bizEmployeegroupVoService.findPage(new Page<BizEmployeegroupVO>(request, response), bizEmployeegroup);
		
		model.addAttribute("page", page);
		model.addAttribute("bizEmployeegroup", bizEmployeegroup);
		model.addAttribute("userProjectMode", UserUtils.getUser().getProjectMode());
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		return "modules/empgroup/bizEmployeegroupList";
	}

	@RequiresPermissions("empgroup:bizEmployeegroup:view")
	@RequestMapping(value = "form")
	public String form(BizEmployeegroup bizEmployeegroup, Model model) {
		User user = UserUtils.getUser();
	if (null != user.getEmpId()) {
			BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
			if (null != be.getProjectMode()) {
				if (be.getProjectMode().equals(ConstantUtils.EMPLOYEE_PROJECT_MODE_3)) {
					// bizEmployee.setProjectMode(ConstantUtils.EMPLOYEE_PROJECT_MODE_1);
					model.addAttribute("projectMode", ConstantUtils.EMPLOYEE_PROJECT_MODE_1);
				} else {
					// bizEmployee.setProjectMode(be.getProjectMode());
					// model.addAttribute("projectModeEnable",true);
					model.addAttribute("projectMode", be.getProjectMode());
				}
			}
		} else {
			// bizEmployee.setProjectMode(ConstantUtils.EMPLOYEE_PROJECT_MODE_1);
			model.addAttribute("projectMode", ConstantUtils.EMPLOYEE_PROJECT_MODE_1);
		}
		if (bizEmployeegroup.getOrderstop() == null) {
			bizEmployeegroup.setOrderstop(0);
		}
		String storeIds = "";
		List<Office> offices = UserUtils.getOfficeList();
		for (Office office : offices) {
			storeIds += office.getId() + ",";
		}
		List<DropModel> dropModels = DropUtils.getEmployeeListByConditionsNew("2", "", "true", storeIds.substring(0, storeIds.length() - 1));
		model.addAttribute("dropModels", dropModels);
		model.addAttribute("bizEmployeegroup", bizEmployeegroup);
		model.addAttribute("userProjectMode", user.getProjectMode());
		
		return "modules/empgroup/bizEmployeegroupForm";
	}

	@RequiresPermissions("empgroup:bizEmployeegroup:edit")
	@RequestMapping(value = "save")
	public String save(BizEmployeegroup bizEmployeegroup, Model model, RedirectAttributes redirectAttributes) {
		System.out.println(bizEmployeegroup.getStar()+"-=-=-=-=-=-=-=-=-=");
		if (!beanValidator(model, bizEmployeegroup)) {
			return form(bizEmployeegroup, model);
		}
		if(bizEmployeegroup.getOrderstop()==1){
			bizEmployeegroup.setOrderStopOperatorEmployeeId(UserUtils.getUser().getId());
			bizEmployeegroup.setOrderStopOperateDatetime(new Date());
		}
		// 任务包 逗号
		String taskId = bizEmployeegroup.getTaskpackageid();
		if (taskId != null && !taskId.equals("")) {
			String[] taskIdArray = taskId.split(",");
			String taskIds = "";
			for (int i = 0; i < taskIdArray.length; i++) {
				taskIds += "," + taskIdArray[i];
			}
			taskIds += ",";
			bizEmployeegroup.setTaskpackageid(taskIds);
		}

		String leaderId = bizEmployeegroup.getIsLeader();// 组长
		List<BizEmgrouprelation> emgroupRelatinList = new ArrayList<BizEmgrouprelation>();
		if (!"".equals(bizEmployeegroup.getEmpId())) {
			String[] empIdArray = bizEmployeegroup.getEmpId().split(",");
			String[] ratioArray = bizEmployeegroup.getRatio().split(",");
			String[] leaderArray = bizEmployeegroup.getIsLeader().split(",");
			for (int i = 0; i < empIdArray.length; i++) {
				BizEmgrouprelation relation = new BizEmgrouprelation();
				relation.setEmpId(empIdArray[i]);
				relation.setSalaryRatio(ratioArray[i]);
				relation.setGroupId(bizEmployeegroup.getId());
				relation.setIsLeader(leaderArray[i]);
				emgroupRelatinList.add(relation);
				if ("1".equals(relation.getIsLeader())) {
					leaderId = relation.getEmpId();
				}
			}
		}
		bizEmployeegroup.setGroupid(leaderId);// 更新组长1
		bizEmployeegroupService.save(bizEmployeegroup);
		System.out.println("工人组ID——————————————" + bizEmployeegroup.getId());
		bizEmployeegroup.setEmpGropRelation(emgroupRelatinList);
		bizEmGroupRelationService.deleteEmgrouprelationByGroupId(bizEmployeegroup.getId());
		bizEmGroupRelationService.InsertEmpGroupRelations(bizEmployeegroup.getEmpGropRelation(), bizEmployeegroup.getId());
		addMessage(redirectAttributes, "保存工人组成功");
		return "redirect:" + Global.getAdminPath() + "/empgroup/bizEmployeegroup/?repage";
	}

	@RequiresPermissions("empgroup:bizEmployeegroup:edit")
	@RequestMapping(value = "delete")
	public String delete(BizEmployeegroup bizEmployeegroup, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		System.out.println("bizEmployeegroupControler delete BizEmployeegroup id:" + bizEmployeegroup.getId());

		if (bizEmployeegroup.getState() == 1) {
			bizEmployeegroup.setState(0);
		} else if (bizEmployeegroup.getState() == 0) {
			bizEmployeegroup.setState(1);
		}
		bizEmployeegroupService.delete(bizEmployeegroup);
		addMessage(redirectAttributes, "删除工人组成功");
		return "redirect:" + Global.getAdminPath() + "/empgroup/bizEmployeegroup/?repage";
	}

	/*
	 * 
	 * 工人组添加工人信息表格
	 */
	@RequiresPermissions("empgroup:bizEmployeegroup:view")
	@ResponseBody
	@RequestMapping(value = "employee_add")
	public String employee_add(BizEmployee bizemployee, Model model, HttpServletRequest request) {
		String bizemployeeId = request.getParameter("bizemployeeId");
		int hadIn = bizEmployeegroupService.hasInGroup(bizemployeeId);
		if (hadIn == 1) {
			return "{\"hadIn\":1}";
		}
		BizEmployee employee = bizEmployeeService.get(bizemployeeId);
		BizEmployee manager = bizEmployeeService.get(employee.getManagerid());
		BizEmployeeForGroup employee4Group = new BizEmployeeForGroup();

		BeanUtils.copyProperties(employee, employee4Group);
		if (employee.getWorktype() != null) {
			employee4Group.setWorkTypeName(DictUtils.getDictLabel(employee.getWorktype().toString(), "emp_work_type", ""));
		} else {
			employee4Group.setWorkTypeName("");
		}

		employee4Group.setManagerRealName(manager == null ? "" : manager.getRealname() == null ? "" : manager.getRealname());
		return JsonMapper.getInstance().toJson(employee4Group);
	}

	/*
	 * 删除工人组关联关系表
	 */
	@RequiresPermissions("empgroup:bizEmployeegroup:edit")
	@RequestMapping(value = "delete_relation")
	public String deleteRelation(Integer idr, RedirectAttributes redirectAttributes) {
		bizEmGroupRelationService.deleteRelation(idr);
		addMessage(redirectAttributes, "删除工人成功");
		return "redirect:" + Global.getAdminPath() + "/empgroup/bizEmployeegroup/?repage";
	}

	/*
	 * 
	 * 工人组星级变化明细
	 */
	@RequestMapping(value = "detail")
	public String detail(BizEmployeegroup bizEmployeegroup,  Model model, RedirectAttributes redirectAttributes, HttpServletRequest request,Date endChangeDatetime,Date startChangeDatetime,String selectId) {
		HttpSession session = request.getSession();
		if(bizEmployeegroup.getGroupid()!=null){
			String empId=bizEmployeegroup.getGroupid();
			session.setAttribute("empId", empId);
		}
		if(bizEmployeegroup.getEndChangeDatetime()!=null &&bizEmployeegroup.getStartChangeDatetime()!=null){
			request.setAttribute("end", bizEmployeegroup.getEndChangeDatetime());
			request.setAttribute("start",bizEmployeegroup.getStartChangeDatetime());
			System.out.println(bizEmployeegroup.getEndChangeDatetime()+"=============="+"startChangeDatetime"+bizEmployeegroup.getStartChangeDatetime());
		}
		selectId=(String) session.getAttribute("empId");
		String realName=bizBizEmployeegroupVoDao.selectRealName(selectId);
	 	model.addAttribute("realName", realName);
		if(endChangeDatetime!=null && startChangeDatetime!=null){
		
		List<BizEmployeeStarLog>listStarLog=bizBizEmployeegroupVoDao.selectChange(selectId,startChangeDatetime,endChangeDatetime);
		if(listStarLog.size()>0){
	 	model.addAttribute("listStarLog", listStarLog);
	 	String xdata="";
	 	String ydata="";
	 	for(BizEmployeeStarLog eaa:listStarLog){
	 		xdata=xdata+"'"+eaa.getStarChangeDatetime()+"',";
	 		ydata=ydata+"'"+eaa.getStarAfter()+"',";
	 	}
	 	xdata=xdata.substring(0, xdata.length()-1);
	 	ydata=ydata.substring(0,ydata.length()-1);
	 	System.out.println(ydata);
		model.addAttribute("xdata", xdata);
		model.addAttribute("ydata", ydata);
	 	System.out.println(listStarLog);
		}
	 	List<BizEmployeegroupVO>listchangeStar=bizBizEmployeegroupVoDao.selectChangeStarList(selectId,startChangeDatetime,endChangeDatetime);
	 		if(listchangeStar.size()>0){
	 	model.addAttribute("listchangeStar", listchangeStar);
	 		}
		}
		return  "modules/empgroup/bizEmployeegroupStarChange";
	}

	/*
	 * 
	 * 工人下拉框
	 */
	@ResponseBody
	@RequestMapping(value = "employees")
	public String employeeList(BizEmployeegroup bizEmployeegroup, Model model, HttpServletRequest request) {

		List<DropModel> dropModel = DropUtils.getEmployeeListByConditionsNew("2", "", "true", bizEmployeegroup.getStoreid());

		return JsonMapper.getInstance().toJson(dropModel);
	}

	/**
	 * 根据门店 和工种
	 * 
	 * @Title: ajaxemployeegroup
	 * @Description: TODO
	 * @param @param bizEmployeegroup
	 * @param @param model
	 * @param @param request
	 * @param @return
	 * @return String
	 * @author ZhangTongWei
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "ajaxemployeegroup")
	public Map<String, Object> ajaxemployeegroup(BizEmployeegroupVO bizEmployeegroupVO, Model model, HttpServletRequest request) {
		// List<BizEmgrouprelation> bizEmgrouprelationlist =
		// bizEmGroupRelationService.queryemployeegroup(bizEmployeegroup);
		List<BizEmployeegroupVO> bizEmployeegroupVOList = bizEmployeegroupVoService.queryemployeegroup(bizEmployeegroupVO);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("resultMap", bizEmployeegroupVOList);
		return resultMap;
	}
}