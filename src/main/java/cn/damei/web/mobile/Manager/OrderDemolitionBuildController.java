package cn.damei.web.mobile.Manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.config.Global;
import cn.damei.entity.mobile.Manager.OrderDemolitionBuild;
import cn.damei.service.mobile.Manager.OrderDemolitionBuildService;
import cn.damei.entity.mobile.Manager.SignDetail;
import cn.damei.entity.modules.Order;

@Controller
@RequestMapping("/mobile/modules/manager/orderDemolitionBuild/web/orderDemolitionBuild")
public class OrderDemolitionBuildController {

	@Autowired
	private OrderDemolitionBuildService orderDemolitionBuildService;

	@RequestMapping(value="/list")
	public String list(HttpServletRequest request,Model model,String flag,String isDisclose){
		List<Order> list = orderDemolitionBuildService.findOrderDemolitionBuildList(request);
		model.addAttribute("list", list);
		model.addAttribute("flag", flag);
		model.addAttribute("isDisclose", isDisclose);
		return "/mobile/modules/Manager/orderDemolitionBuild/orderDemolitionBuildList";
	}
	

	@RequestMapping(value="/sceneSign")
	@ResponseBody
	public String sceneSign(HttpServletRequest request,Model model,SignDetail signDetail){

		String sceneSign = orderDemolitionBuildService.sceneSign(signDetail,request);
		return sceneSign;
	}
	

	@RequestMapping(value="/disclose")
	@ResponseBody
	public String disclose(HttpServletRequest request,Model model,SignDetail signDetail){

		String disclose = orderDemolitionBuildService.disclose(signDetail,request);
		return disclose;
	}
	

	@RequestMapping(value="/form")
	public String form(HttpServletRequest request,SignDetail signDetail,Model model){

		signDetail = orderDemolitionBuildService.findOrderSignDatetime(signDetail,request);
		model.addAttribute("signDetail", signDetail);
		return "/mobile/modules/Manager/orderDemolitionBuild/orderDemolitionBuildForm";
	}
	
	

	@RequestMapping(value="/save")
	public String save(HttpServletRequest request,OrderDemolitionBuild orderDemolitionBuild,Model model){
		String saveDisclose = orderDemolitionBuildService.saveDisclose(orderDemolitionBuild,request);
		return "forward:"+Global.getRootPath()+"/mobile/modules/manager/orderDemolitionBuild/web/orderDemolitionBuild/list?flag=1&isDisclose="+saveDisclose;
		
	}
	
	
}
