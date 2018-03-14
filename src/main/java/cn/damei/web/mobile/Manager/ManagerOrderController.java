package cn.damei.web.mobile.Manager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.ManagerOrderVo;
import cn.damei.service.mobile.Manager.MyOrderService;
import org.springframework.ui.Model;

/**
 * 
 * @author 汪文文
 * @version 2016-9-19
 */


@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class ManagerOrderController {
	
	@Autowired
	private MyOrderService myOrderService;
	
	@RequestMapping(value = "toindex")
	public String toindex(Model model,HttpServletRequest request){
		return "mobile/modules/Manager/manager_index";
	}
	
	@RequestMapping(value = "indexMine")
	public String indexMine(Model model,HttpServletRequest request){
		// 已登录的项目经理
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		ManagerOrderVo mov = new ManagerOrderVo();
		//根据项目经理的id去查询订单数-订单总数
		int totalCount = myOrderService.findCountByManagerName(manager.getId());
		mov.setTotalCount(totalCount);
		//根据项目经理的id和订单的状态去查询订单数-在施工数
		int buildingCount = myOrderService.findCountByManagerNameAndOrderStatus(manager.getId());
		mov.setBuildingCount(buildingCount);
		model.addAttribute("mov", mov);
		model.addAttribute("manager",manager);
		return "mobile/modules/Manager/manager_index_mine";
	}
	@RequestMapping(value = "common-issue")
	public String commonIssue(Model model,HttpServletRequest request){
		return "mobile/modules/Manager/common-issue/question-list";
	}
	@RequestMapping(value = "question-page1.html")
	public String questionPage1(Model model,HttpServletRequest request){
		return "mobile/modules/Manager/common-issue/questionPage1";
	}
	@RequestMapping(value = "question-page2.html")
	public String questionPage2(Model model,HttpServletRequest request){
		return "mobile/modules/Manager/common-issue/questionPage2";
	}
	@RequestMapping(value = "question-page3.html")
	public String questionPage3(Model model,HttpServletRequest request)	{
	return "mobile/modules/Manager/common-issue/questionPage3";
	}
	@RequestMapping(value = "question-page4.html")
	public String questionPage4(Model model,HttpServletRequest request){
		return "mobile/modules/Manager/common-issue/questionPage4";
	}
}
