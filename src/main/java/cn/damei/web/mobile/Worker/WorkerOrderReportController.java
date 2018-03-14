package cn.damei.web.mobile.Worker;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.service.mobile.Worker.WorkerBizOrderReportService;
import cn.damei.entity.mobile.Worker.EmployeeGroup;
import cn.damei.service.mobile.Worker.EmployeeGroupService;
import cn.damei.entity.mobile.Worker.Worker;
import cn.damei.entity.modules.BizOrderReport;


@Controller
@RequestMapping(value = "${adminPath}/app/worker/orderReport")
public class WorkerOrderReportController {
	@Autowired
	private WorkerBizOrderReportService workerBizOrderReportService;


	
	@Autowired 
	private EmployeeGroupService employeeGroupService;

	@RequestMapping(value = "orderreport")
	public String WorkerOrderReport(Model model, HttpServletRequest request) {
		Worker worker = (Worker)request.getSession().getAttribute("worker");
		EmployeeGroup employeeGroup = employeeGroupService.selectEmployeeGroupByGroupId(worker.getId());
		String sta="0";
		if(employeeGroup != null){
			sta="0";
		}else{
			sta="1";
		}
		model.addAttribute("sta",sta);
		return "mobile/modules/Worker/orderreport/orderreport1";
	}

	@RequestMapping(value = "toOrderReportRecord")
	public String toOrderReportRecord(Model model, HttpServletRequest request) {
		BizOrderReport bizOrderReport = new BizOrderReport();
		Worker worker = (Worker)request.getSession().getAttribute("worker");
		EmployeeGroup employeeGroup = employeeGroupService.selectEmployeeGroupByGroupId(worker.getId());
		String sta="0";
		if(employeeGroup != null){
			sta="0";
		}else{
			sta="1";
		}
		
		bizOrderReport.setReporterEmployeeId(worker.getId());
		bizOrderReport.setReporterType("3");
		List<BizOrderReport> list = workerBizOrderReportService.findList(bizOrderReport);
		model.addAttribute("sta",sta);
		if (null != list && list.size() > 0) {
			model.addAttribute("list", list);
			return "mobile/modules/Worker/orderreport/orderReportRecord1";
		} else {
			model.addAttribute("error", "您目前没有返单");
			return "mobile/modules/Worker/orderreport/orderReportRecord1";
		}

	}

	@RequestMapping(value = "orderReportSave")
	public @ResponseBody String orderReportSave(BizOrderReport bizOrderReport, HttpServletRequest request, Model model) {
		Worker worker = (Worker)request.getSession().getAttribute("worker");
		try{
			workerBizOrderReportService.save(bizOrderReport,worker);
			return "0";
		}catch (Exception e){
			e.printStackTrace();
			return "1";
		}




	}


	@RequestMapping(value = "checkCustomerPhone")
	public @ResponseBody String checkCustomerPhone(String customerPhone) {
		String result = null;
	Integer count = workerBizOrderReportService.getBizOrderReportByCustomerPhone(customerPhone);
		if (null==count ||count==0) {
			result = "0";
		} else {
			result = "1";
		}
		return result;
	}

	@RequestMapping(value = { "queryOrderReportByParam", "" })
	public @ResponseBody List<BizOrderReport> queryOrderReportByParam(String searchText, Model model,
			HttpServletRequest request) {
		Worker worker = (Worker)request.getSession().getAttribute("worker");
		BizOrderReport bizOrderReport = new BizOrderReport();
		bizOrderReport.setSearchText(searchText);
		bizOrderReport.setReporterEmployeeId(worker.getId());
		bizOrderReport.setReporterType("3");
		List<BizOrderReport> list = workerBizOrderReportService.queryOrderReportByParam(bizOrderReport);
		if (null != list && list.size() > 0) {
			return list;
		} else {
			return null;

		}
	}
}
