package cn.damei.web.modules;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.persistence.Page;
import cn.damei.entity.modules.EmployeeAgreementPC;
import cn.damei.service.modules.EmployeeAgreementPCService;

@Controller
@RequestMapping(value="${adminPath}/employeeagreementPC")
public class EmployeeAgreementPCController {
	@Autowired
	private EmployeeAgreementPCService employeeAgreementPCService;
	
	@RequestMapping(value="list")
	public String list(EmployeeAgreementPC employeeAgreementPC,HttpServletRequest request,HttpServletResponse response,Model model){
		System.out.println("111"+employeeAgreementPC.getIsSignEmployeeAgreement());
		Page<EmployeeAgreementPC> findPage = employeeAgreementPCService.findPage(new Page<EmployeeAgreementPC>(request,response), employeeAgreementPC);
		model.addAttribute("page", findPage);
		return "/modules/employeeagreementPC/employeeAgreementPCList";
	}
	
}
