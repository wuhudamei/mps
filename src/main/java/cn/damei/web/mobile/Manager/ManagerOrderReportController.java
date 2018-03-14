package cn.damei.web.mobile.Manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.service.mobile.Manager.ManagerBizOrderReportService;
import cn.damei.entity.modules.BizOrderReport;

/**
 * 项目经理返单上报
 * @author hyh
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/app/manager/orderReport")
public class ManagerOrderReportController {

	@Autowired
	private ManagerBizOrderReportService managerBizOrderReportService;

	
	@RequestMapping(value = "orderreport")
	public String ManagerOrderReport(){
		return "mobile/modules/Manager/orderreport/orderreport1";
	}
	
	@RequestMapping(value = "toOrderReportManage")
	public String toOrderReportManage(){
		return "mobile/modules/Manager/orderreport/orderReportManager1";
	}
	
	@RequestMapping(value = "toOrderReportRecord")
	public String toOrderReportRecord(Model model, HttpServletRequest request){
		BizOrderReport bizOrderReport = new BizOrderReport();
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		bizOrderReport.setReporterEmployeeId(manager.getId());
		bizOrderReport.setReporterType("1");//项目经理
		List<BizOrderReport> list = managerBizOrderReportService.findList(bizOrderReport);
		if(null!=list&&list.size()>0){
			model.addAttribute("list",list);
			return "mobile/modules/Manager/orderreport/orderReportRecord1";
		}else{
			model.addAttribute("error","您目前没有返单");
			return "mobile/modules/Manager/orderreport/orderReportRecord1";
		}
		
	}

	/**
	 * 保存返单
	 * @param bizOrderReport
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "orderReportSave")
	public @ResponseBody String orderReportSave(BizOrderReport bizOrderReport,HttpServletRequest request, Model model){
		Manager manager = (Manager)request.getSession().getAttribute("manager");

		try{
			managerBizOrderReportService.save(bizOrderReport,manager);
			return "0";
		}catch(Exception e){

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
		String result;
		Integer count = managerBizOrderReportService.getPhoneCountByCustomerPhone(customerPhone);
		if (null==count ||count==0) {// 客户手机号不存在
			result = "0";
		} else {// 已存在
			result = "1";
		}
		return result;
	}
	
	@RequestMapping(value = { "queryOrderReportByParam", "" })
	public @ResponseBody List<BizOrderReport> queryOrderReportByParam(String searchText,Model model, HttpServletRequest request){
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		BizOrderReport bizOrderReport = new BizOrderReport();
		bizOrderReport.setSearchText(searchText);
		bizOrderReport.setReporterEmployeeId(manager.getId());
	    bizOrderReport.setReporterType("1");
	    List<BizOrderReport> list = managerBizOrderReportService.queryOrderReportByParam(bizOrderReport);
	    if(null!=list&&list.size()>0){
			return list;
		}else{
			return null;
			
		}
	}
	
}
