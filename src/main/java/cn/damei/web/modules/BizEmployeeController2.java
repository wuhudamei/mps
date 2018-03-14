
package cn.damei.web.modules;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.entity.modules.BizEmployeeGuaranteeMoney;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(value = "${adminPath}/employee/bizEmployee2")
public class BizEmployeeController2 extends BaseController {

	@Autowired
	private BizEmployeeService2 bizEmployeeService2;

	@ModelAttribute
	public BizEmployee2 get(@RequestParam(required = false) Integer id) {
		BizEmployee2 entity = null;
		if (id != null) {
			entity = bizEmployeeService2.get(id);
		}
		if (entity == null) {
			entity = new BizEmployee2();
		}
		return entity;
	}

	@RequiresPermissions("employee:bizEmployee2:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizEmployee2 bizEmployee2, Model model) {

		if(bizEmployee2.getStoreid() == null){
			String storeId = UserUtils.getUser().getStoreId();
			if(StringUtils.isBlank(storeId)){
				bizEmployee2.setStoreid(null);
			}else{
				bizEmployee2.setStoreid(storeId);
			}
		}
		if(StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		return "modules/employee/bizGuaranteeMoneyList";
	}

	@RequiresPermissions("employee:bizEmployee2:view")
	@RequestMapping(value = {"loadList", ""})
	public String loadList(BizEmployee2 bizEmployee2, Model model, HttpServletRequest request, HttpServletResponse response) {

		if(bizEmployee2.getStoreid() == null){
			String storeId = UserUtils.getUser().getStoreId();
			if(StringUtils.isBlank(storeId)){
				bizEmployee2.setStoreid(null);
			}else{
				bizEmployee2.setStoreid(storeId);
			}
		}
		if(StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		Page<BizEmployee2> page = bizEmployeeService2.findWorkerGroupGuaranteeMoneyPage(new Page<BizEmployee2>(request, response), bizEmployee2);
		model.addAttribute("page", page);
		return "modules/employee/bizGuaranteeMoneyList";
	}

	@RequiresPermissions("employee:bizEmployee2:view")
	@RequestMapping(value = {"guaranteeMoneyDetail", ""})
	public String guaranteeMoneyDetail(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		List<BizEmployeeGuaranteeMoney> list = bizEmployeeService2.queryGuaranteeMoneyList(id);
		Double totalAmount = bizEmployeeService2.queryGuaranteeMoneyTotal(id);
		model.addAttribute("list", list);
		model.addAttribute("totalAmount", totalAmount);
		return "modules/employee/bizGuaranteeMoneyDetail";
	}

	@RequiresPermissions("employee:bizEmployee2:view")
	@RequestMapping(value = {"managerGuranteeMoneyList", ""})
	public String managerGuranteeMoneyList(BizEmployee2 bizEmployee2, Model model, HttpServletRequest request, HttpServletResponse response) {

		if(StringUtils.isBlank(bizEmployee2.getStoreid())){
			String storeId = UserUtils.getUser().getStoreId();
			if(StringUtils.isBlank(storeId)){
				bizEmployee2.setStoreid(null);
			}else{
				bizEmployee2.setStoreid(storeId);
			}
		}
		if(StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}


		if(bizEmployee2.getProjectMode() == null || "".equals(bizEmployee2.getProjectMode())){
			User user = UserUtils.getUser();
			if(null !=user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
					bizEmployee2.setProjectMode(null);
				}else{
					bizEmployee2.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}
			}else{
				bizEmployee2.setProjectMode(null);
			}
		}

		return "modules/employee/managerGuranteeMoneyList";
	}

	@RequiresPermissions("employee:bizEmployee2:view")
	@RequestMapping(value = {"managerGuranteeMoneyLoadList", ""})
	public String managerGuranteeMoneyLoadList(BizEmployee2 bizEmployee2, Model model, HttpServletRequest request, HttpServletResponse response) {

		if(StringUtils.isBlank(bizEmployee2.getStoreid())){
			String storeId = UserUtils.getUser().getStoreId();
			if(StringUtils.isBlank(storeId)){
				bizEmployee2.setStoreid(null);
			}else{
				bizEmployee2.setStoreid(storeId);
			}
		}
		if(StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}


		if(bizEmployee2.getProjectMode() == null || "".equals(bizEmployee2.getProjectMode())){
			User user = UserUtils.getUser();
			if(null !=user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
					bizEmployee2.setProjectMode(null);
				}else{
					bizEmployee2.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}
			}else{
				bizEmployee2.setProjectMode(null);
			}
		}

		Page<BizEmployee2> page = bizEmployeeService2.findManagerGuaranteeMoneyPage(new Page<BizEmployee2>(request, response), bizEmployee2);
		model.addAttribute("page", page);
		return "modules/employee/managerGuranteeMoneyList";
	}

	@RequiresPermissions("employee:bizEmployee2:view")
	@RequestMapping(value = {"deleteEmployeeList", ""})
	public String deleteEmployeeList(BizEmployee2 bizEmployee2, Model model) {

		if(StringUtils.isBlank(bizEmployee2.getStoreid())){
			String storeId = UserUtils.getUser().getStoreId();
			if(StringUtils.isBlank(storeId)){
				bizEmployee2.setStoreid(null);
			}else{
				bizEmployee2.setStoreid(storeId);
			}
		}
		if(StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		return "modules/employee/deleteEmployeeList";
	}

	@RequiresPermissions("employee:bizEmployee2:view")
	@RequestMapping(value = {"deleteEmployeeLoadList", ""})
	public String deleteEmployeeLoadList(BizEmployee2 bizEmployee2, Model model, HttpServletRequest request, HttpServletResponse response) {

		if(StringUtils.isBlank(bizEmployee2.getStoreid())){
			String storeId = UserUtils.getUser().getStoreId();
			if(StringUtils.isBlank(storeId)){
				bizEmployee2.setStoreid(null);
			}else{
				bizEmployee2.setStoreid(storeId);
			}
		}
		if(StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		Page<BizEmployee2> page = bizEmployeeService2.findDeleteEmployeePage(new Page<BizEmployee2>(request, response), bizEmployee2);
		model.addAttribute("page", page);
		return "modules/employee/deleteEmployeeList";
	}
	
	@RequiresPermissions("employee:bizEmployee2:edit")
	@RequestMapping(value = "reduction")
	public String reduction(BizEmployee2 bizEmployee2, RedirectAttributes redirectAttributes) {
		Date date = new Date();
		User user = UserUtils.getUser();
		bizEmployee2.setUpdateBy(user);
		bizEmployee2.setUpdateDate(date);
		bizEmployeeService2.delete(bizEmployee2);
		addMessage(redirectAttributes, "还原员工信息成功");
		return "redirect:" + Global.getAdminPath() + "/employee/bizEmployee2/deleteEmployeeLoadList?repage";
	}
}