package cn.damei.web.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.OrderDetails;
import cn.damei.entity.modules.OrderDetailsInstallPlan;
import cn.damei.entity.modules.OrderDtailsEmployee;
import cn.damei.service.modules.OrderDetailsService;

@Controller
@RequestMapping(value="${adminPath}/orderDtails")
public class OrderDetailsController extends BaseController{
	@Autowired
	private OrderDetailsService orderDetailsService;
	
	@ModelAttribute
	public OrderDetails get(@RequestParam(required = false) Integer id) {
		OrderDetails entity = null;
		if (id != null){
			entity = orderDetailsService.get(id);
		}
		if (entity == null){
			entity = new OrderDetails();
		}
		return entity;
	}
	
	

	@RequestMapping(value="orderDtailsLook")
	public String dtails(String orderId,Model model){
		OrderDetails od = orderDetailsService.findOrderDtailsById(orderId);
		model.addAttribute("orderDetails", od);
		List<OrderDetailsInstallPlan> op = orderDetailsService.findIntallPlanByOrderId(orderId);
		model.addAttribute("listInstall", op);
		List<OrderDtailsEmployee> oe = orderDetailsService.findEmployeeByOrderId(orderId);
		for (OrderDtailsEmployee orderDtailsEmployee : oe) {
			String name = orderDtailsEmployee.getName();
			String substring = name.substring(0, name.length()-1);
			orderDtailsEmployee.setName(substring);
		}
		model.addAttribute("employee", oe);
		String phone = orderDetailsService.findInspector(orderId);
		model.addAttribute("inspectorPhone", phone);
		return "modules/orderDetails/orderFormDetails";
	}
}
