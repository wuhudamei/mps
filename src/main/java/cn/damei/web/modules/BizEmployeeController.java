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
import cn.damei.common.mapper.JsonMapper;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.BizEmpArea;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.service.modules.BizEngineeringDepartmentService;
import cn.damei.entity.modules.BizMaterialsStandard;
import cn.damei.service.modules.BizMaterialsStandardService;
import cn.damei.entity.modules.BizMessagegroup;
import cn.damei.service.modules.BizMessagegroupService;
import cn.damei.service.modules.SysSequenceService;
import cn.damei.entity.modules.User;
import cn.damei.service.modules.SystemService;
import cn.damei.common.utils.DropUtils;
import cn.damei.common.utils.UserUtils;
import cn.damei.entity.modules.BizTaskPackageTemplat;
import cn.damei.service.modules.BizTaskPackageTemplatService;

/**
 * 员工信息Controller
 * 
 * @author qhy
 * @version 2016-08-24
 */
@Controller
@RequestMapping(value = "${adminPath}/employee/bizEmployee")
public class BizEmployeeController extends BaseController {

	@Autowired
	private BizMaterialsStandardService bizMaterialsStandardService;
	@Autowired
	private BizEmployeeService bizEmployeeService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizMessagegroupService bizMessagegroupService;
	@Autowired
	private SysSequenceService sysSequenceService;
	@Autowired
	private SystemService systemService;
	private List<BizEmpArea> areas_manager;

	// private List<BizEmpArea> areas_inspector;
	@ModelAttribute
	public BizEmployee get(@RequestParam(required = false) String id) {
		BizEmployee entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = bizEmployeeService.get(id);
			
		}

		if (entity == null) {
			entity = new BizEmployee();
		}
		return entity;
	}

	@RequiresPermissions("employee:bizEmployee:view")
	@RequestMapping(value = "listPage")
	public String listPage(BizEmployee bizEmployee, HttpServletRequest request, HttpServletResponse response, Model model) {

		if (UserUtils.getUser().getStoreId() != null) {
			// 当前登录用户门店
			bizEmployee.setStoreid(UserUtils.getUser().getStoreId());
		} else {
			// 门店是总部的查询所有部门信息
			if (bizEmployee.getStoreid() != null && bizEmployee.getStoreid().equals("1")) {
				// 总部
				bizEmployee.setStoreid(null);
			}
		}
		// 过滤工程模式
		if (bizEmployee.getProjectMode() == null || "".equals(bizEmployee.getProjectMode())) {
			User user = UserUtils.getUser();
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode()) || null == be.getProjectMode()) {
					bizEmployee.setProjectMode(null);
				} else {
					bizEmployee.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}
			} else {
				bizEmployee.setProjectMode(null);
			}
		}
		return "modules/employee/bizEmployeeList";
	}

	@RequiresPermissions("employee:bizEmployee:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizEmployee bizEmployee, HttpServletRequest request, HttpServletResponse response, Model model) {
		String ischecked = "";
		if (bizEmployee.getNoInGroup() != null) {
			bizEmployee.setNoInGroup(" id NOT IN (SELECT id FROM biz_emgrouprelation ) ");
			bizEmployee.setNo("");
			bizEmployee.setLoginname("");
			bizEmployee.setRealname("");
			bizEmployee.setPhone("");
			bizEmployee.setWorktype(null);
			bizEmployee.setEmpType(2);
			ischecked = "checked=\"checked\"";
		}
		if (UserUtils.getUser().getStoreId() != null) {
			// 当前登录用户门店
			bizEmployee.setStoreid(UserUtils.getUser().getStoreId());
		} else {
			// 门店是总部的查询所有部门信息
			if (bizEmployee.getStoreid() != null && bizEmployee.getStoreid().equals("1")) {
				// 总部
				bizEmployee.setStoreid(null);
			}
		}
		// 过滤工程模式
		User user = UserUtils.getUser();
		if (bizEmployee.getProjectMode() == null || "".equals(bizEmployee.getProjectMode())) {
			
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode()) || null == be.getProjectMode()) {
					bizEmployee.setProjectMode(null);
				} else {
					bizEmployee.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}
			} else {
				bizEmployee.setProjectMode(null);
			}
		}
		//工程模式控制
	/*	if(bizEmployee.getProjectMode() == null || "".equals(bizEmployee.getProjectMode())){
			
			String projectMode = user.getProjectMode();
			if(projectMode.equals("3")){
				bizEmployee.setProjectMode(null);
			}else{
				bizEmployee.setProjectMode(projectMode);
			}
			
			
		}*/
		Page<BizEmployee> page = bizEmployeeService.findPage(new Page<BizEmployee>(request, response), bizEmployee);
		model.addAttribute("page", page);
		
		model.addAttribute("noChecked", ischecked);
		return "modules/employee/bizEmployeeList";
	}

	@RequiresPermissions("employee:bizEmployee:view")
	@RequestMapping(value = "form")
	public String form(BizEmployee bizEmployee, Model model) {
		bizEmployee.setNoValid(bizEmployee.getNo());
		bizEmployee.setLoginNameValid(bizEmployee.getLoginname());
		bizEmployee.setPhoneValid(bizEmployee.getPhone());
		bizEmployee.setIdcardnoValid(bizEmployee.getIdcardno());
		if (StringUtils.isBlank(bizEmployee.getStoreid())) {
			bizEmployee.setStoreid(UserUtils.getUser().getStoreId());
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		User user = UserUtils.getUser();
		if (null == bizEmployee.getProjectMode()) {
			
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (null != be.getProjectMode()) {
					bizEmployee.setProjectMode(be.getProjectMode());
				}
			} else {
				bizEmployee.setProjectMode(ConstantUtils.EMPLOYEE_PROJECT_MODE_3);
			}
		} else {
			
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())) {
					model.addAttribute("projectModeEnable", true);
				}
			} else {
				model.addAttribute("projectModeEnable", true);
			}
		}
		model.addAttribute("userProjectMode", user.getProjectMode());
		
		model.addAttribute("bizEmployee", bizEmployee);
		return "modules/employee/bizEmployeeForm";
	}

	@RequiresPermissions("employee:bizEmployee:edit")
	@RequestMapping(value = "save")
	public String save(BizEmployee bizEmployee, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizEmployee)) {
			return form(bizEmployee, model);
		}
		if (bizEmployee.getId() == null || "".equals(bizEmployee.getId())) {
			bizEmployee.setNo(sysSequenceService.getSequence("YG")); // 说明是添加
			// bizEmployeeService.insert(bizEmployee);
		}

		Date date = new Date();
		User user = UserUtils.getUser();
		// bizEmployeeService.update(bizEmployee);
		bizEmployee.setUpdateBy(user);
		bizEmployee.setUpdateDate(date);
		bizEmployee.setCreateBy(user);
		bizEmployee.setCreateDate(date);
		bizEmployeeService.save(bizEmployee);
		addMessage(redirectAttributes, "保存员工信息成功");
		return "redirect:" + Global.getAdminPath() + "/employee/bizEmployee/?repage";
	}

	@RequiresPermissions("employee:bizEmployee:edit")
	@RequestMapping(value = "delete")
	public String delete(BizEmployee bizEmployee, RedirectAttributes redirectAttributes) {
		Date date = new Date();
		User user = UserUtils.getUser();
		bizEmployee.setUpdateBy(user);
		bizEmployee.setUpdateDate(date);
		bizEmployeeService.delete(bizEmployee);
		addMessage(redirectAttributes, "删除员工信息成功");
		return "redirect:" + Global.getAdminPath() + "/employee/bizEmployee/?repage";
	}

	/**
	 * 项目经理信息
	 */
	@RequiresPermissions("manager:bizManager:view")
	@RequestMapping(value = "manager_list_page")
	public String managerListPage(BizEmployee bizEmployee, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (UserUtils.getUser().getStoreId() != null) {
			// 当前登录用户门店
			bizEmployee.setStoreid(UserUtils.getUser().getStoreId());
		} else {
			// 门店是总部的查询所有部门信息
			if (bizEmployee.getStoreid() != null && bizEmployee.getStoreid().equals("1")) {
				// 总部
				bizEmployee.setStoreid(null);
			}
		}
		if (bizEmployee.getProjectMode() == null || "".equals(bizEmployee.getProjectMode())) {
			User user = UserUtils.getUser();
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode()) || null == be.getProjectMode()) {
					bizEmployee.setProjectMode(null);
				} else {
					bizEmployee.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}
			} else {
				bizEmployee.setProjectMode(null);
			}
		}
		return "modules/employee/bizManagerList";
	}

	@RequiresPermissions("manager:bizManager:view")
	@RequestMapping(value = { "manager_list", "manager_list1" })
	public String managerList(BizEmployee bizEmployee, HttpServletRequest request, HttpServletResponse response, Model model) {
		String ischecked = "";
		if (bizEmployee.getNoInDepartment() != null) {
			bizEmployee.setNoInDepartment(" id NOT IN (SELECT employee_id FROM biz_engin_depart_employee_position ) ");
			/*
			 * bizEmployee.setNo(""); bizEmployee.setLoginname("");
			 * bizEmployee.setRealname(""); bizEmployee.setPhone("");
			 * bizEmployee.setWorktype(null);
			 */
			bizEmployee.setEmpType(3);
			ischecked = "checked=\"checked\"";
		}
		if (null == bizEmployee.getEnginDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					bizEmployee.setEnginDepartIds(list);
				} else {
					bizEmployee.setEnginDepartIds(null);
				}
			} else {
				bizEmployee.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizEmployee.getEnginDepartId());
			bizEmployee.setEnginDepartIds(list);
		}

		if (UserUtils.getUser().getStoreId() != null) {
			// 当前登录用户门店
			bizEmployee.setStoreid(UserUtils.getUser().getStoreId());
		} else {
			// 门店是总部的查询所有部门信息
			if (bizEmployee.getStoreid() != null && bizEmployee.getStoreid().equals("1")) {
				// 总部
				bizEmployee.setStoreid(null);
			}
		}
		if (bizEmployee.getProjectMode() == null || "".equals(bizEmployee.getProjectMode())) {
			User user = UserUtils.getUser();
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode()) || null == be.getProjectMode()) {
					bizEmployee.setProjectMode(null);
				} else {
					bizEmployee.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}
			} else {
				bizEmployee.setProjectMode(null);
			}
		}
		User user = UserUtils.getUser();
		Page<BizEmployee> page = bizEmployeeService.findManagerPage(new Page<BizEmployee>(request, response), bizEmployee);
		model.addAttribute("page", page);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("noChecked", ischecked);
		model.addAttribute("bizEmployee", bizEmployee);
		return "modules/employee/bizManagerList";
	}

	@RequiresPermissions("manager:bizManager:view")
	@RequestMapping(value = "manager_form")
	public String managerForm(BizEmployee bizEmployee, Model model) {
		if (StringUtils.isBlank(bizEmployee.getStoreid())) {
			bizEmployee.setStoreid(UserUtils.getUser().getStoreId());
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		if (StringUtils.isBlank(bizEmployee.getOrderstop())) {
			bizEmployee.setOrderstop("0");
		}
		if (bizEmployee.getSex() == null) {
			bizEmployee.setSex(1);
		}
		if (bizEmployee.getIswebchat() == null) {
			bizEmployee.setIswebchat(1);
		}
		if (bizEmployee.getSmartphone() == null) {
			bizEmployee.setSmartphone(1);
		}
		bizEmployee.setAreas(areas_manager);
		bizEmployee.setNoValid(bizEmployee.getNo());
		bizEmployee.setLoginNameValid(bizEmployee.getLoginname());
		bizEmployee.setPhoneValid(bizEmployee.getPhone());
		bizEmployee.setIdcardnoValid(bizEmployee.getIdcardno());
		if (null == bizEmployee.getProjectMode()) {
			User user = UserUtils.getUser();
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (null != be.getProjectMode()) {
					if (be.getProjectMode().equals(ConstantUtils.EMPLOYEE_PROJECT_MODE_3)) {
						bizEmployee.setProjectMode(ConstantUtils.EMPLOYEE_PROJECT_MODE_2);
					} else {
						bizEmployee.setProjectMode(be.getProjectMode());
						model.addAttribute("projectModeEnable", true);
					}
				}
			} else {
				bizEmployee.setProjectMode(ConstantUtils.EMPLOYEE_PROJECT_MODE_2);
			}
		}
		User user = UserUtils.getUser();
		model.addAttribute("userProjectMode", user.getProjectMode());
		model.addAttribute("bizEmployee", bizEmployee);
		return "modules/employee/bizManagerForm";
	}

	@RequiresPermissions("manager:bizManager:edit")
	@RequestMapping(value = "manager_save")
	public String managerSave(BizEmployee bizEmployee, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizEmployee)) {
			return form(bizEmployee, model);
		}
		if (bizEmployee.getId() == null || "".equals(bizEmployee.getId())) {
			bizEmployee.setNo(sysSequenceService.getSequence("YG"));
		}
		// 页面无法取值，写死。
		bizEmployee.setEmpType(3);
		bizEmployeeService.save(bizEmployee);
		addMessage(redirectAttributes, "保存项目经理成功");
		return "redirect:" + Global.getAdminPath() + "/employee/bizEmployee/manager_list?repage&empType=3";
	}

	@RequiresPermissions("manager:bizManager:edit")
	@RequestMapping(value = "manager_delete")
	public String managerDelete(BizEmployee bizEmployee, RedirectAttributes redirectAttributes) {
		Date date = new Date();
		User user = UserUtils.getUser();
		bizEmployee.setUpdateBy(user);
		bizEmployee.setUpdateDate(date);
		bizEmployeeService.delete(bizEmployee);
		addMessage(redirectAttributes, "删除项目经理成功");
		return "redirect:" + Global.getAdminPath() + "/employee/bizEmployee/manager_list?repage&empType=3";
	}

	/**
	 * 工人信息
	 */
	@RequiresPermissions("worker:bizWorker:view")
	@RequestMapping(value = "worker_list_page")
	public String workerListPage(BizEmployee bizEmployee, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (UserUtils.getUser().getStoreId() != null) {
			// 当前登录用户门店
			bizEmployee.setStoreid(UserUtils.getUser().getStoreId());
		} else {
			// 门店是总部的查询所有部门信息
			if (bizEmployee.getStoreid() != null && bizEmployee.getStoreid().equals("1")) {
				// 总部
				bizEmployee.setStoreid(null);
			}
		}
		if (bizEmployee.getProjectMode() == null || "".equals(bizEmployee.getProjectMode())) {
			User user = UserUtils.getUser();
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode()) || null == be.getProjectMode()) {
					bizEmployee.setProjectMode(null);
				} else {
					bizEmployee.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}
			} else {
				bizEmployee.setProjectMode(null);
			}
		}
		return "modules/employee/bizWorkerList";
	}

	@RequiresPermissions("worker:bizWorker:view")
	@RequestMapping(value = { "worker_list", "worker_list1" })
	public String workerList(BizEmployee bizEmployee, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (UserUtils.getUser().getStoreId() != null) {
			// 当前登录用户门店
			bizEmployee.setStoreid(UserUtils.getUser().getStoreId());
		} else {
			// 门店是总部的查询所有部门信息
			if (bizEmployee.getStoreid() != null && bizEmployee.getStoreid().equals("1")) {
				// 总部
				bizEmployee.setStoreid(null);
			}
		}
		if (bizEmployee.getProjectMode() == null) {
			User user = UserUtils.getUser();
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode()) || null == be.getProjectMode()) {
					bizEmployee.setProjectMode(null);
				} else {
					bizEmployee.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}
			} else {
				bizEmployee.setProjectMode(null);
			}
		}
		Page<BizEmployee> page = bizEmployeeService.findPage(new Page<BizEmployee>(request, response), bizEmployee);
		model.addAttribute("page", page);
		return "modules/employee/bizWorkerList";
	}

	@RequiresPermissions("worker:bizWorker:view")
	@RequestMapping(value = "worker_form")
	public String workerForm(BizEmployee bizEmployee, Model model) {
		if (StringUtils.isBlank(bizEmployee.getStoreid())) {
			bizEmployee.setStoreid(UserUtils.getUser().getStoreId());
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		if (StringUtils.isBlank(bizEmployee.getOrderstop())) {
			bizEmployee.setOrderstop("0");
		}
		if (bizEmployee.getSex() == null) {
			bizEmployee.setSex(1);
		}
		if (bizEmployee.getIswebchat() == null) {
			bizEmployee.setIswebchat(1);
		}
		if (bizEmployee.getSmartphone() == null) {
			bizEmployee.setSmartphone(1);
		}
		if (bizEmployee.getNcms() == null) {
			bizEmployee.setNcms(1);
		}
		if (bizEmployee.getElectricancard() == null) {
			bizEmployee.setElectricancard(1);
		}
		bizEmployee.setAreas(areas_manager);
		bizEmployee.setNoValid(bizEmployee.getNo());
		bizEmployee.setLoginNameValid(bizEmployee.getLoginname());
		bizEmployee.setPhoneValid(bizEmployee.getPhone());
		bizEmployee.setIdcardnoValid(bizEmployee.getIdcardno());
		if (null == bizEmployee.getProjectMode()) {
			User user = UserUtils.getUser();
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (null != be.getProjectMode()) {
					if (be.getProjectMode().equals(ConstantUtils.EMPLOYEE_PROJECT_MODE_3)) {
						bizEmployee.setProjectMode(ConstantUtils.EMPLOYEE_PROJECT_MODE_1);
					} else {
						bizEmployee.setProjectMode(be.getProjectMode());
						model.addAttribute("projectModeEnable", true);
					}
				}
			} else {
				bizEmployee.setProjectMode(ConstantUtils.EMPLOYEE_PROJECT_MODE_1);
			}
		}
		model.addAttribute("bizEmployee", bizEmployee);
		User user = UserUtils.getUser();
		model.addAttribute("userProjectMode",user.getProjectMode());
		return "modules/employee/bizWorkerForm";
	}

	@RequiresPermissions("worker:bizWorker:edit")
	@RequestMapping(value = "worker_save")
	public String workerSave(BizEmployee bizEmployee, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizEmployee)) {
			return form(bizEmployee, model);
		}
		if (bizEmployee.getId() == null || "".equals(bizEmployee.getId())) {
			bizEmployee.setNo(sysSequenceService.getSequence("YG"));
		}
		bizEmployee.setEmpType(2);
		bizEmployeeService.save(bizEmployee);
		addMessage(redirectAttributes, "保存工人成功");
		return "redirect:" + Global.getAdminPath() + "/employee/bizEmployee/worker_list?repage&empType=2";
	}

	@RequiresPermissions("worker:bizWorker:edit")
	@RequestMapping(value = "worker_delete")
	public String workerDelete(BizEmployee bizEmployee, RedirectAttributes redirectAttributes) {
		Date date = new Date();
		User user = UserUtils.getUser();
		bizEmployee.setUpdateBy(user);
		bizEmployee.setUpdateDate(date);
		bizEmployeeService.delete(bizEmployee);
		addMessage(redirectAttributes, "删除工人成功");
		return "redirect:" + Global.getAdminPath() + "/employee/bizEmployee/worker_list?repage&empType=" + bizEmployee.getEmpType();
	}

	/**
	 * 表单验证
	 */
	@RequestMapping(value = { "validatNo", "validatNo" })
	@ResponseBody
	public String validat(BizEmployee entity) {
		String state = "false";
		int isCount = bizEmployeeService.getCountByNo(entity);
		if (isCount == 0) {
			state = "true";
		}

		// 如果是修改操作，相同的编号和登录名不做验证，如果不是相同的则验证。
		if (entity.getId() != null) {
			if (entity.getNoValid() != null && entity.getNoValid().equalsIgnoreCase(entity.getNo())) {
				state = "true";
			}
		}
		return state;
	}

	@RequestMapping(value = { "validatLoginName", "validatLoginName" })
	@ResponseBody
	public String validatLoginName(BizEmployee entity) {
		String state = "false";
		int isCount = bizEmployeeService.getCountByLoginName(entity);
		if (isCount == 0) {
			state = "true";
		}
		// 如果是修改操作，相同的编号和登录名不做验证，如果不是相同的则验证。
		if (entity.getId() != null) {
			if (entity.getLoginNameValid() != null && entity.getLoginNameValid().equalsIgnoreCase(entity.getLoginname())) {
				state = "true";
			}
		}
		return state;
	}

	@RequestMapping(value = { "validatPhone", "validatPhone" })
	@ResponseBody
	public String validatPhone(BizEmployee entity) {
		String state = "false";
		int isCount = bizEmployeeService.getCountByPhone(entity);
		if (isCount == 0) {
			state = "true";
		}
		// 如果是修改操作，相同的编号和登录名不做验证，如果不是相同的则验证。
		if (entity.getId() != null) {
			if (entity.getPhoneValid() != null && entity.getPhoneValid().equalsIgnoreCase(entity.getPhone())) {
				state = "true";
			}
		}
		return state;
	}

	@RequestMapping(value = { "validatIdcardno", "validatIdcardno" })
	@ResponseBody
	public String validatIdcardno(BizEmployee entity) {
		String state = "false";
		int isCount = bizEmployeeService.getCountByIdcardno(entity);
		if (isCount == 0) {
			state = "true";
		}
		// 如果是修改操作，相同的身份证不做验证，如果不是相同的则验证。
		if (entity.getId() != null) {
			if (entity.getIdcardnoValid() != null && entity.getIdcardnoValid().equalsIgnoreCase(entity.getIdcardno())) {
				state = "true";
			}
		}
		return state;
	}

	/**
	 * 门店下拉框事件
	 */
	@RequiresPermissions("manager:bizManager:view")
	@ResponseBody
	@RequestMapping(value = "manager_store_form")
	public String manager_StoreForm(BizEmployee bizEmployee, Model model, HttpServletRequest request) {
		String storeId = request.getParameter("storeId");
		String projectMode = request.getParameter("projectMode");
		/*
		 * BizEmpStore store=new BizEmpStore(); store.setId(storeId);
		 */
		areas_manager = bizEmployeeService.findAreaByStoreId(storeId, projectMode);
		return JsonMapper.getInstance().toJson(areas_manager);
	}

	/**
	 * 加载员工编号
	 */

	/**
	 * 质检员信息
	 */
	@RequiresPermissions("inspector:bizInspector:view")
	@RequestMapping(value = "inspector_list_page")
	public String inspectorListPage(BizEmployee bizEmployee, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (UserUtils.getUser().getStoreId() != null) {
			// 当前登录用户门店
			bizEmployee.setStoreid(UserUtils.getUser().getStoreId());
		} else {
			// 门店是总部的查询所有部门信息
			if (bizEmployee.getStoreid() != null && bizEmployee.getStoreid().equals("1")) {
				// 总部
				bizEmployee.setStoreid(null);
			}
		}
		if (bizEmployee.getProjectMode() == null || "".equals(bizEmployee.getProjectMode())) {
			User user = UserUtils.getUser();
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode()) || null == be.getProjectMode()) {
					bizEmployee.setProjectMode(null);
				} else {
					bizEmployee.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}
			} else {
				bizEmployee.setProjectMode(null);
			}
		}
		return "modules/employee/bizInspectorList";
	}

	@RequiresPermissions("inspector:bizInspector:view")
	@RequestMapping(value = { "inspector_list", "inspector_list1" })
	public String inspectorList(BizEmployee bizEmployee, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (null == bizEmployee.getEnginDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					bizEmployee.setEnginDepartIds(list);
				} else {
					bizEmployee.setEnginDepartIds(null);
				}
			} else {
				bizEmployee.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizEmployee.getEnginDepartId());
			bizEmployee.setEnginDepartIds(list);
		}
		if (UserUtils.getUser().getStoreId() != null) {
			// 当前登录用户门店
			bizEmployee.setStoreid(UserUtils.getUser().getStoreId());
		} else {
			// 门店是总部的查询所有部门信息
			if (bizEmployee.getStoreid() != null && bizEmployee.getStoreid().equals("1")) {
				// 总部
				bizEmployee.setStoreid(null);
			}
		}
		if (bizEmployee.getProjectMode() == null) {
			User user = UserUtils.getUser();
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode()) || null == be.getProjectMode()) {
					bizEmployee.setProjectMode(null);
				} else {
					bizEmployee.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}
			} else {
				bizEmployee.setProjectMode(null);
			}
		}
		Page<BizEmployee> page = bizEmployeeService.findInspectorPage(new Page<BizEmployee>(request, response), bizEmployee);
		model.addAttribute("page", page);
		return "modules/employee/bizInspectorList";
	}

	@RequiresPermissions("inspector:bizInspector:view")
	@RequestMapping(value = "inspector_form")
	public String inspectorForm(BizEmployee bizEmployee, Model model) {
		if (StringUtils.isBlank(bizEmployee.getStoreid())) {
			bizEmployee.setStoreid(UserUtils.getUser().getStoreId());
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		if (StringUtils.isBlank(bizEmployee.getOrderstop())) {
			bizEmployee.setOrderstop("0");
		}
		if (bizEmployee.getSex() == null) {
			bizEmployee.setSex(1);
		}
		bizEmployee.setAreas(areas_manager);
		bizEmployee.setNoValid(bizEmployee.getNo());
		bizEmployee.setLoginNameValid(bizEmployee.getLoginname());
		bizEmployee.setPhoneValid(bizEmployee.getPhone());
		bizEmployee.setIdcardnoValid(bizEmployee.getIdcardno());
		if (null == bizEmployee.getProjectMode()) {
			User user = UserUtils.getUser();
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (null != be.getProjectMode()) {
					if (be.getProjectMode().equals(ConstantUtils.EMPLOYEE_PROJECT_MODE_3)) {
						bizEmployee.setProjectMode(ConstantUtils.EMPLOYEE_PROJECT_MODE_2);
					} else {
						bizEmployee.setProjectMode(be.getProjectMode());
						model.addAttribute("projectModeEnable", true);
					}
				}
			} else {
				bizEmployee.setProjectMode(ConstantUtils.EMPLOYEE_PROJECT_MODE_2);
			}
		}
		User user = UserUtils.getUser();
		model.addAttribute("userProjectMode",user.getProjectMode());
		model.addAttribute("bizEmployee", bizEmployee);
		return "modules/employee/bizInspectorForm";
	}

	@RequiresPermissions("inspector:bizInspector:edit")
	@RequestMapping(value = "inspector_save")
	public String inspectorSave(BizEmployee bizEmployee, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizEmployee)) {
			return form(bizEmployee, model);
		}
		if (bizEmployee.getId() == null || "".equals(bizEmployee.getId())) {
			bizEmployee.setNo(sysSequenceService.getSequence("YG"));
		}
		bizEmployee.setEmpType(1);
		bizEmployeeService.save(bizEmployee);
		addMessage(redirectAttributes, "保存质检员成功");
		return "redirect:" + Global.getAdminPath() + "/employee/bizEmployee/inspector_list?repage&empType=1";
	}

	@RequiresPermissions("inspector:bizInspector:edit")
	@RequestMapping(value = "inspector_delete")
	public String inspectorDelete(BizEmployee bizEmployee, RedirectAttributes redirectAttributes) {
		Date date = new Date();
		User user = UserUtils.getUser();
		bizEmployee.setUpdateBy(user);
		bizEmployee.setUpdateDate(date);
		bizEmployeeService.delete(bizEmployee);
		addMessage(redirectAttributes, "删除质检员成功");
		return "redirect:" + Global.getAdminPath() + "/employee/bizEmployee/inspector_list?repage&empType=1";
	}

	/**
	 * 根据门店查询员工信息
	 */
	@ResponseBody
	@RequestMapping(value = { "employee_list_byStoreId", "employee_list_byStoreId" })
	public String employeeList(String storeid, HttpServletRequest request, HttpServletResponse response, Model model) {
		Map map = new HashMap();
		map.put("officeIds", storeid);
		map.put("empTypes", 2);
		List<DropModel> list = bizEmployeeService.findEmployeeListByCondition(map);

		return JsonMapper.getInstance().toJson(list);
	}

	// 项目经理
	@ResponseBody
	@RequestMapping(value = { "manager_list_byStoreId", "manager_list_byStoreId" })
	public String managerList(String storeId, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<List> list = new ArrayList<List>();
		Map map = new HashMap();
		map.put("officeIds", storeId);
		map.put("empTypes", 3);
		List<DropModel> list1 = bizEmployeeService.findEmployeeListByCondition(map);
		// List<DropModel> list2 =
		// bizMaterialsStandardService.findMaterialsByStroeId(storeId);
		List<BizMaterialsStandard> list2 = bizMaterialsStandardService.queryMaterialsByStoreId(storeId);
		list.add(list1);
		list.add(list2);
		return JsonMapper.getInstance().toJson(list);
	}

	/**
	 * 根据门店查询员工信息 类型为99其他类型
	 */
	@ResponseBody
	@RequestMapping(value = "other_employee_list_byStoreId")
	public String otherEmployeeList(String storeid, HttpServletRequest request, HttpServletResponse response, Model model) {
		Map map = new HashMap();
		map.put("officeIds", storeid);
		map.put("empTypes", 99);
		List<DropModel> list = bizEmployeeService.findEmployeeListByCondition(map);

		return JsonMapper.getInstance().toJson(list);
	}

	/**
	 * 根据门店查询组长信息
	 */
	@Autowired
	private BizTaskPackageTemplatService bizTaskPackageTemplatService;
	@Autowired
	private BizEngineeringDepartmentService bizEngineeringDepartmentService;

	@ResponseBody
	@RequestMapping(value = { "leader_list_byStoreId", "leader_list_byStoreId" })
	public String leaderList(String storeid, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<List> list0 = new ArrayList<List>();
		if (!"".equals(storeid)) {
			// 根据门店查询组长
			Map map = new HashMap();
			map.put("officeIds", storeid);
			map.put("empTypes", 2);
			List<DropModel> list1 = bizEmployeeService.findLeaderListByCondition(map);
			// 根据门店查询任务包
			BizTaskPackageTemplat param = new BizTaskPackageTemplat();
			param.setStatus("1");
			param.setStoreId(storeid);
			List<DropModel> list2 = bizTaskPackageTemplatService.findtaskpackageByStroeId(Integer.parseInt(storeid), ConstantUtils.IS_ENABLE_1);

			// 根据门店查询工程部
			List<DropModel> list3 = bizEngineeringDepartmentService.findEngDepListByStoreId(storeid);
			list0.add(list1);
			list0.add(list2);
			list0.add(list3);
			return JsonMapper.getInstance().toJson(list0);
		} else {
			// 根据门店查询组长
			Map map = new HashMap();
			map.put("officeIds", storeid);
			map.put("empTypes", 2);
			List<DropModel> list1 = bizEmployeeService.findLeaderListByCondition(map);
			// 根据门店查询任务包
			BizTaskPackageTemplat param = new BizTaskPackageTemplat();
			param.setStatus("1");
			param.setStoreId(storeid);
			List<DropModel> list2 = bizTaskPackageTemplatService.findtaskpackageByStroeId1(ConstantUtils.IS_ENABLE_1);

			// 根据门店查询工程部
			List<DropModel> list3 = bizEngineeringDepartmentService.findEngDepListByStoreId1();
			list0.add(list1);
			list0.add(list2);
			list0.add(list3);
			return JsonMapper.getInstance().toJson(list0);
		}
	}

	/**
	 * 根据条件(门店\员工类型\工种)查询员工信息
	 */
	@ResponseBody
	@RequestMapping(value = { "employeeListByCondition" })
	public List<DropModel> employeeListByCondition(HttpServletRequest request, HttpServletResponse response, Model model) {
		String unauth = request.getParameter("unauth");
		String empTypes = request.getParameter("empTypes");
		String workTypes = request.getParameter("workTypes");
		String storeIds = request.getParameter("storeIds");
		List<DropModel> list = DropUtils.getEmployeeListByConditionsNew(empTypes, workTypes, unauth, storeIds);
		return list;
	}

	@ResponseBody
	@RequestMapping(value = { "employeeByCondition" })
	public List<DropModel> employeeByCondition(String storeId, String empType, String projectMode) {
		if (StringUtils.isNoneBlank(storeId)) {
			List<DropModel> list = bizEmployeeService.employeeByCondition(storeId, empType, projectMode);
			return list;
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = { "queryEmployeeListByEmpType" })
	public List<DropModel> queryEmployeeListByEmpType(Integer storeId, String projectMode) {
		return bizEmployeeService.findEmployeeListByEmpType(storeId, projectMode);
	}

	/**
	 * 没有加入工人组的员工信息
	 */
	@RequiresPermissions("empNoInGroup:bizEmployee:noGroupView")
	@RequestMapping(value = { "empNoInGroup", "empNoInGroup" })
	public String empNoInGroup(BizEmployee bizEmployee, HttpServletRequest request, HttpServletResponse response, Model model) {
		bizEmployee.setNoInGroup(" id NOT IN (SELECT id FROM biz_emgrouprelation ) ");
		bizEmployee.setStoreid("");
		bizEmployee.setNo("");
		bizEmployee.setLoginname("");
		bizEmployee.setRealname("");
		bizEmployee.setPhone("");
		bizEmployee.setEmpType(null);
		bizEmployee.setWorktype(null);

		Page<BizEmployee> page = bizEmployeeService.findPage(new Page<BizEmployee>(request, response), bizEmployee);
		model.addAttribute("page", page);
		return "modules/employee/bizEmployeeList";
	}

	/**
	 * 获取发送短信人员 根据门店编号(storeId)
	 */
	@ResponseBody
	@RequestMapping(value = { "listPhone" })
	public List<BizEmployee2> listPhone(BizEmployee2 bizEmployee2, HttpServletRequest request) {
		List<Integer> list = new ArrayList<Integer>();
		String storeId = request.getParameter("storeId");
		String messageGroupType = request.getParameter("messageGroupType");

		logger.info("BizMessagegroupController listPhone storeId=" + storeId + "\t messageGroupType=" + messageGroupType);

		// 获取短信组list
		BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(storeId, messageGroupType);
		List<BizEmployee2> employeelist = null;
		if (null != bizMessagegroup) {
			String[] str = bizMessagegroup.getEmployees().split(",");
			for (String id : str) {
				list.add(Integer.valueOf(id));
			}
			employeelist = bizEmployeeService2.getById(list);
		}

		/*
		 * for(BizEmployee2 d:employeelist){
		 * logger.info("phone===="+d.getPhone()); }
		 */

		return employeelist;
	}

	@RequestMapping(value = "findEmployeeById")
	public @ResponseBody BizEmployee2 findEmployeeById(Integer id) {

		BizEmployee2 employee = bizEmployeeService2.findEmployeeById(id);
		return employee;
	}
}