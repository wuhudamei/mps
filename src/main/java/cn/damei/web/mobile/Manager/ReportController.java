package cn.damei.web.mobile.Manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.utils.PicRootName;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Inspector.ReportCheck;
import cn.damei.entity.mobile.Inspector.ReportCheckDetails;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.service.mobile.Manager.ReportService;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 质检报告
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="${adminPath}/app/manager/report")
public class ReportController {
	
	@Autowired
	private ReportService reportService;

	/**
	 * 查询项目经理下所有的订单列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="orderList")
	public String orderList(HttpServletRequest request,Model model){
		Manager manager = SessionUtils.getManagerSession(request);
		String text = (String)request.getSession().getAttribute("reportManagerText");
		model.addAttribute("managerId", manager.getId());
		model.addAttribute("text", text);
		return "mobile/modules/Manager/report/report_order";
	}

	/**
	 * 动态加载质检报告订单列表
	 * @param managerId
	 * @param text
	 * @return
	 */
	@RequestMapping(value="order_list_ajax")
	public @ResponseBody List<ReportCheck> orderListAjax(Integer managerId, String text,HttpServletRequest request){
		request.getSession().setAttribute("reportManagerText", text);
		return reportService.queryOrderList(managerId,text);
	}


	/**
	 * 查询订单所有的质检报告
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="reportList")
	public String reportList(Integer orderId,Model model){
		
		//查询订单所有的质检报告
		List<ReportCheck> list = reportService.findReportByManagerId(orderId);
		model.addAttribute("list", list);
		return "mobile/modules/Manager/report/report_check";
	}

	/**
	 * 查询质检报告详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="reportDetails")
	public String reportDetails(Integer id,Model model){
		
		//根据质检单id查询质检报告详情
		ReportCheck reportCheckDetails = reportService.findReportDetailsById(id);
		//查询质检详情
		List<ReportCheckDetails> itemDetails = reportService.findItemById(reportCheckDetails.getId());
		//通过质检单id查询图片数量
		Integer count = reportService.findPicNum(id);
		
		
		model.addAttribute("reportCheckDetails", reportCheckDetails);
		model.addAttribute("itemDetails", itemDetails);
		model.addAttribute("count", count);
		
		return "mobile/modules/Manager/report/report_details";
	}

	/**
	 * 查看图片
	 * @param qcBillId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="reportPic")
	public String reportPic(Integer qcBillId,Model model) throws Exception{
		
		//通过质检单id查询图片
		List<ReportCheckDetailsPic> picList = reportService.findPic(qcBillId);
		
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("picList", picList);
		model.addAttribute("baseUrl", baseUrl);
		return "mobile/modules/Manager/report/photo";
	}
	
	
	
}
