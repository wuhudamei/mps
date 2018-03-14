package cn.damei.web.mobile.Inspector;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.utils.PicRootName;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Inspector.Inspector;
import cn.damei.entity.mobile.Inspector.ReportCheck;
import cn.damei.entity.mobile.Inspector.ReportCheckDetails;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.service.mobile.Inspector.ReportCheckService;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value="${adminPath}/app/pqc/report")
public class ReportCheckController {
	
	@Autowired
	private ReportCheckService reportCheckService;


	@RequestMapping(value="orderList")
	public String orderList(HttpServletRequest request,Model model){
		Inspector inspector = SessionUtils.getInspectorSession(request);
		String text = (String)request.getSession().getAttribute("reportInspectorText");
		model.addAttribute("inspectorId", inspector.getId());
		model.addAttribute("text", text);
		return "mobile/modules/pqc/report/report_order";
	}


	@RequestMapping(value="order_list_ajax")
	public @ResponseBody List<ReportCheck> orderListAjax(Integer inspectorId, String text,HttpServletRequest request){
		request.getSession().setAttribute("reportInspectorText", text);
		return reportCheckService.queryOrderList(inspectorId,text);
	}


	@RequestMapping(value="reportList")
	public String reportList(Model model,Integer orderId){
		

		List<ReportCheck> list = reportCheckService.findReportByInspectorId(orderId);
		model.addAttribute("list", list);
		return "mobile/modules/pqc/report/report_check";
	}


	@RequestMapping(value="reportDetails")
	public String reportDetails(String managerRealName,int id,Model model){
		

		ReportCheck reportCheckDetails = reportCheckService.findReportDetailsById(id);

		List<ReportCheckDetails> itemDetails = reportCheckService.findItemById(reportCheckDetails.getId());

		Integer count = reportCheckService.findPicNum(id);

		model.addAttribute("reportCheckDetails", reportCheckDetails);
		model.addAttribute("managerRealName", managerRealName);
		model.addAttribute("itemDetails", itemDetails);
		model.addAttribute("count", count);
		
		return "mobile/modules/pqc/report/report_details";
	}
	

	@RequestMapping(value="reportPic")
	public String reportPic(Integer qcBillId,Model model) throws Exception{
		

		List<ReportCheckDetailsPic> picList = reportCheckService.findPic(qcBillId);
		
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("picList", picList);
		model.addAttribute("baseUrl", baseUrl);
		return "mobile/modules/pqc/report/photo";
	}
	
	
	
}
