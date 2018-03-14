package cn.damei.web.modules;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.LaborCapital;
import cn.damei.service.modules.LaborCapitalService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value="${adminPath}/laborCapital/laborCapital/")
@Controller
public class LaborCapitalController extends BaseController {
	
	@Autowired
	private LaborCapitalService laborCapitalService;
	
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	@RequiresPermissions("laborCapital:laborCapital:view")
	@RequestMapping(value="queryOrderList")
	public String query(@ModelAttribute LaborCapital laborCapital,HttpServletRequest request, HttpServletResponse response, Model model){
		User user = UserUtils.getUser();

		laborCapitalService.store(laborCapital, model);
		bizEmployeeService2.projectMode(laborCapital, model, user, this);
		return "modules/laborCapital/laborCapital";
	}

	@RequiresPermissions("laborCapital:laborCapital:view")
	@RequestMapping(value="queryOrderList1")
	public String query1(@ModelAttribute LaborCapital laborCapital,HttpServletRequest request, HttpServletResponse response, Model model){
		User user = UserUtils.getUser();

		laborCapitalService.store(laborCapital, model);

		bizEmployeeService2.projectMode(laborCapital, model, user, this);
		Page<LaborCapital> page = laborCapitalService.findPage(new Page<LaborCapital>(request, response), laborCapital);
		model.addAttribute("page", page);
		return "modules/laborCapital/laborCapital";
	}


	@RequestMapping(value="exportexcel")
	@ResponseBody
	public void exportexcel(LaborCapital laborCapital,HttpServletResponse response){
		laborCapitalService.exportDetailExcel(laborCapital,response);
	}

}
