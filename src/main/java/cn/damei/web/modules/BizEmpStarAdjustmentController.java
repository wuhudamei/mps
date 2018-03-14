/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.dao.modules.BizBizEmployeegroupVoDao;
import cn.damei.entity.modules.BizEmgrouprelation;
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
import cn.damei.common.utils.DropUtils;
import cn.damei.common.utils.UserUtils;

/**
 * 工人组管理Controller（修改权限）
 * 
 * @author ws
 * @version 2017-09-13
 */
@Controller
@RequestMapping(value = "${adminPath}/empStar/empStarAdjustment")
public class BizEmpStarAdjustmentController extends BaseController {

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
		return "modules/empStar/bizEmpStarAdjustmentList";
	}

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
		String realName=bizBizEmployeegroupVoDao.selectRealName(bizEmployeegroup.getGroupid());
	 	model.addAttribute("realName", realName);
		return "modules/empStar/bizEmpStarAdjustmentForm";
	}
	
	@RequestMapping(value = "update")
	public String update(BizEmployeegroup bizEmployeegroup, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizEmployeegroup)){
			return form(bizEmployeegroup, model);
		}
		model.addAttribute("bizEmployeegroup", bizEmployeegroup);
		System.out.println(bizEmployeegroup.getReason()+"--=-=-=-=-=-=-=-=-=-=-===="+bizEmployeegroup.getChangeDescribe());
		if(bizEmployeegroup.getReason()!=null && !bizEmployeegroup.getChangeDescribe().equals("") && bizEmployeegroup.getChangeDescribe()!=null){
			bizEmployeegroupService.updateStarLog(bizEmployeegroup.getGroupid(),bizEmployeegroup.getReason(),bizEmployeegroup.getChangeDescribe(),bizEmployeegroup.getStar());
		}
		return "redirect:"+Global.getAdminPath()+"/empStar/empStarAdjustment/list";
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