package cn.damei.web.mobile.Inspector;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.entity.mobile.Inspector.Inspector;
import cn.damei.service.mobile.Inspector.InspectorBizOrderReportService;
import cn.damei.entity.modules.BizOrderReport;

/**
 * 质检返单上报controller
 * @author hyh
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/app/pqc/orderReport")
public class InspectorOrderReportController {

	@Autowired
	private InspectorBizOrderReportService inspectorBizOrderReportService;
	
	

	
	@RequestMapping(value = "orderreport")
	public String WorkerOrderReport() {
		return "mobile/modules/pqc/orderreport/orderreport1";
	}

	@RequestMapping(value = "toOrderReportRecord")
	public String toOrderReportRecord(Model model, HttpServletRequest request) {
		BizOrderReport bizOrderReport = new BizOrderReport();
		Inspector inspector = (Inspector) request.getSession().getAttribute("inspector");
		bizOrderReport.setReporterEmployeeId(inspector.getId());
		bizOrderReport.setReporterType("2");// 质检
		List<BizOrderReport> list = inspectorBizOrderReportService.findList(bizOrderReport);
		if (null != list && list.size() > 0) {
			model.addAttribute("list", list);
			return "mobile/modules/pqc/orderreport/orderReportRecord1";
		} else {
			model.addAttribute("error", "您目前没有返单");
			return "mobile/modules/pqc/orderreport/orderReportRecord1";
		}

	}

	@RequestMapping(value = "orderReportSave")
	public @ResponseBody String orderReportSave(BizOrderReport bizOrderReport, HttpServletRequest request, Model model) {
		Inspector inspector = (Inspector) request.getSession().getAttribute("inspector");
		try{
			inspectorBizOrderReportService.save(bizOrderReport,inspector);
			return "0";
		}catch (Exception e){

			e.printStackTrace();
			return "1";
		}



	}

	/**
	 * 检查返单客户手机号是否存在
	 * 
	 * @param customerPhone
	 * @return
	 */
	@RequestMapping(value = "checkCustomerPhone")
	public @ResponseBody String checkCustomerPhone(String customerPhone) {
		String result = null;
		Integer count = inspectorBizOrderReportService.getBizOrderReportByCustomerPhone(customerPhone);
		if (null==count || count == 0) {// 客户手机号不存在
			result = "0";
		} else {// 已存在
			result = "1";
		}
		return result;
	}

	@RequestMapping(value = { "queryOrderReportByParam", "" })
	public @ResponseBody List<BizOrderReport> queryOrderReportByParam(String searchText, Model model,
			HttpServletRequest request) {
		Inspector inspector = (Inspector) request.getSession().getAttribute("inspector");
		BizOrderReport bizOrderReport = new BizOrderReport();
		bizOrderReport.setSearchText(searchText);
		bizOrderReport.setReporterEmployeeId(inspector.getId());
		bizOrderReport.setReporterType("2");
		List<BizOrderReport> list = inspectorBizOrderReportService.queryOrderReportByParam(bizOrderReport);
		if (null != list && list.size() > 0) {
			return list;
		} else {
			return null;

		}
	}
}
