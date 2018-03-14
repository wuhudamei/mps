package cn.damei.web.mobile.Manager;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.AppOrder;
import cn.damei.entity.mobile.Manager.AppOrderCadfile;
import cn.damei.service.mobile.Manager.AppOrderService;


@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class AppOrderController {

	@Autowired
	private AppOrderService appOrderService;
	
	

	@RequestMapping(value = { "appOrderList", "" })
	public String appOrderList(AppOrder appOrder,HttpServletRequest request, Model model) {

		Manager manager = (Manager)request.getSession().getAttribute("manager");
		appOrder.setItemManagerId(manager.getId());
		

		List<AppOrder> order = appOrderService.findAppOrderByitemManager(appOrder);

		List<String> stateName = appOrderService.selectState(appOrder.getItemManagerId());
		
		model.addAttribute("stateName", stateName);
		model.addAttribute("order", order);
		return "mobile/modules/Manager/myorder";
	}
	
	

	@RequestMapping(value = { "appOrderCondition", "" })
	public @ResponseBody List<AppOrder> appOrderCondition(AppOrder appOrder,HttpServletRequest request) {
		
		
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		appOrder.setItemManagerId(manager.getId());
		
		List<AppOrder> order = appOrderService.findAppOrderByitemManager(appOrder);
		return order;
	}
	
	
	

	@RequestMapping(value = {"appOrderDetails",""})
	public String appOrderDetails(String id, Model model) {
		AppOrder appOrder = appOrderService.getOrder(Integer.valueOf(id));
		String house = appOrderService.findHouseType(appOrder);
		appOrder.setHouse(house);
		model.addAttribute("appOrder", appOrder);
		return "mobile/modules/Manager/details";
	}
	

	@RequestMapping(value = {"appOrderCadfile",""})
	public String appOrderCadfile(String id,Model model){
		

		AppOrder appOrder = appOrderService.getOrder(Integer.valueOf(id));

		List<AppOrderCadfile> appOrderCadfile = appOrderService.selectCadfile(Integer.valueOf(id));
		for(AppOrderCadfile aoc : appOrderCadfile){
			String path = aoc.getFilePath();
			path = path.substring(1);
			aoc.setFilePath(path);
		}
		model.addAttribute("appOrder",appOrder);
		model.addAttribute("appOrderCadfile", appOrderCadfile);
		return "mobile/modules/Manager/draw";
	}
	




























	
	
	
	
	
}