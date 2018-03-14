package cn.damei.web.mobile.Worker;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.EmployeeContantUtil;
import cn.damei.entity.mobile.Worker.Worker;
import cn.damei.entity.modules.EmployeeAgreementPC;
import cn.damei.service.modules.EmployeeAgreementPCService;
@Controller
@RequestMapping(value="${adminPath}/app/worker/employeeagreement")
public class EmployeeAgreementController {
	@Autowired
	private EmployeeAgreementPCService employeeAgreementPCService;
	@RequestMapping(value="sign")
	@ResponseBody
	public String sign(String sign,HttpServletRequest request,HttpServletResponse response){
		Worker worker = (Worker)request.getSession().getAttribute("worker");
		EmployeeAgreementPC employeeAgreement = new EmployeeAgreementPC();
		employeeAgreement.preInsert();
		employeeAgreement.setEmployeeType(EmployeeContantUtil.EMPLOYEE_EMP_TYPE_2);
		employeeAgreement.setEmployeeId(worker.getId()+"");
		employeeAgreement.setIsSignEmployeeAgreement(sign);
		employeeAgreement.setEmployeeAgreementSignDatetime(new Date());
		employeeAgreement.setEmployeeAgreementReadDatetime(new Date());
		employeeAgreementPCService.insertEmployeeAgreement(employeeAgreement);
		return "success";
	}
}
