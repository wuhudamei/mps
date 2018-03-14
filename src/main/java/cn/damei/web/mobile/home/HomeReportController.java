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

/**
 * 客户端 质检报告
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/app/home/report")
public class HomeReportController {

	@Autowired
	private HomeReportService reportService;

	

	/**
	 * 质检报告列表页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list")
	public String list(Integer orderId,HttpServletRequest request, Model model) {
		
		String customerPhone = (String) request.getSession().getAttribute("customerPhone");
		
		//查询质检报告
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
		
		//查询订单列表
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
	


	/**
	 * 根据质检报告详情
	 * 
	 * @param model
	 * @param qcBillId
	 * @return
	 */
	@RequestMapping(value = "details")
	public String details(Model model,HttpServletRequest request, Integer qcBillId) {

		String customerPhone = (String) request.getSession().getAttribute("customerPhone");
		
		//查看消息是否已读
		Integer count = reportService.findView(qcBillId,customerPhone);
		if(count==0){
			//如果未读则插入已读信息
			reportService.insertView(qcBillId,customerPhone);
		}
		
		//质检报告详情
		Report report = reportService.reportDetail(qcBillId);
		if(null!=report){
			model.addAttribute("report", report);
		}else{
			return "mobile/modules/home/report/report_null";
		}
		
		return "mobile/modules/home/report/reportDetails";
	}
	
	/**
	 * 质检图片
	 * @param model
	 * @param qcBillId
	 * @return
	 */
	@RequestMapping(value = "reportPic")
	public String reportPic(Model model, Integer qcBillId) {

		//质检图片
		List<ReportCheckDetailsPic> picList = reportService.findPic(qcBillId);
		//图片前缀
		String baseUrl = reportService.findPicBefore();
		
		if(null!=picList && picList.size()>0 && null != baseUrl && !baseUrl.equals("")){
			model.addAttribute("baseUrl", baseUrl);
			model.addAttribute("picList", picList);
		}
		return "mobile/modules/home/report/pic";
	}
	
	/**
	 * 违规形式及处理方式
	 * @param qcBillItemId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "checkBreak",method=RequestMethod.GET)
	public @ResponseBody CheckItem checkBreak(Integer qcBillItemId,HttpServletRequest request, Model model) {
		
		//违规形式及处理方式
		CheckItem checkItem = reportService.findCheckBreak(qcBillItemId);
			return checkItem;
	}

}
