package cn.damei.web.mobile.home;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.home.CheckItem;
import cn.damei.entity.mobile.home.Order;
import cn.damei.entity.mobile.home.Report;
import cn.damei.service.mobile.home.HomeReportService;


@Controller
@RequestMapping(value = "${adminPath}/app/home/report")
public class HomeReportController {

	@Autowired
	private HomeReportService reportService;

	


	@RequestMapping(value = "list")
	public String list(Integer orderId,HttpServletRequest request, Model model) {
		
		String customerPhone = (String) request.getSession().getAttribute("customerPhone");
		

		Order order = reportService.findQcBill(customerPhone,orderId);
		
		if(null!=order){
			List<Report> reportList = order.getReportList();
			String exists ="0";
			if(null!=reportList && reportList.size()>0){
				for(Report a :reportList){
					if(null!=a.getQcBillId()){
						exists="1";
					}
				}
			}
			model.addAttribute("exists",exists);
			model.addAttribute("order", order);
		}
		

		List<Order> list = reportService.findOrderList(customerPhone);
		if(null!=list && list.size()>0){
			if(list.size()>1){
				model.addAttribute("list", list);
			}
			model.addAttribute("ordersLength",list.size());
		}else{
			return "mobile/modules/home/report/report_null";
		}
				
		return "mobile/modules/home/report/reportList";
	}
	



	@RequestMapping(value = "details")
	public String details(Model model,HttpServletRequest request, Integer qcBillId) {

		String customerPhone = (String) request.getSession().getAttribute("customerPhone");
		

		Integer count = reportService.findView(qcBillId,customerPhone);
		if(count==0){

			reportService.insertView(qcBillId,customerPhone);
		}
		

		Report report = reportService.reportDetail(qcBillId);
		if(null!=report){
			model.addAttribute("report", report);
		}else{
			return "mobile/modules/home/report/report_null";
		}
		
		return "mobile/modules/home/report/reportDetails";
	}
	

	@RequestMapping(value = "reportPic")
	public String reportPic(Model model, Integer qcBillId) {


		List<ReportCheckDetailsPic> picList = reportService.findPic(qcBillId);

		String baseUrl = reportService.findPicBefore();
		
		if(null!=picList && picList.size()>0 && null != baseUrl && !baseUrl.equals("")){
			model.addAttribute("baseUrl", baseUrl);
			model.addAttribute("picList", picList);
		}
		return "mobile/modules/home/report/pic";
	}
	

	@RequestMapping(value = "checkBreak",method=RequestMethod.GET)
	public @ResponseBody CheckItem checkBreak(Integer qcBillItemId,HttpServletRequest request, Model model) {
		

		CheckItem checkItem = reportService.findCheckBreak(qcBillItemId);
			return checkItem;
	}

}
