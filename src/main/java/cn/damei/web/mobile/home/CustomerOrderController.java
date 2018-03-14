package cn.damei.web.mobile.home;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.entity.mobile.home.CustomerOrder;
import cn.damei.entity.mobile.home.OrderTeam;
import cn.damei.service.mobile.home.CustomerOrderService;

@Controller
@RequestMapping(value="${adminPath}/app/home")
public class CustomerOrderController{
	
	@Autowired
	private CustomerOrderService customerOrderService;
	
	@RequestMapping(value="myOrder")
	public String myOrder(Model model,HttpServletRequest request){
		
		String username = (String) request.getSession().getAttribute("customerPhone");
		List<CustomerOrder> orders = customerOrderService.findOrderByPhone(username);
		if(orders != null && orders.size()>0){
			model.addAttribute("orders", orders);
			return "mobile/modules/home/customer_order_list";
		}else{
			return "mobile/modules/home/customer_order_list_null";
		}
	}
	
	@RequestMapping(value="orderDetails")
	public String orderDetails(Integer orderId,Model model,HttpServletRequest request){
		CustomerOrder order = customerOrderService.findByOrderId(orderId);
		model.addAttribute("order", order);
		return "mobile/modules/home/customer_order_details";
	}
	
	
	//施工团队
	@RequestMapping(value="orderTeam")
	public String orderTeam(Integer orderId ,Model model,HttpServletRequest request){
		
		String username = (String) request.getSession().getAttribute("customerPhone");
		List<CustomerOrder> orders = customerOrderService.findOrderByPhone(username);
		if(orders != null && orders.size()>0){
			CustomerOrder order = customerOrderService.findByOrderId(orders.get(0).getId());
			//List<OrderTeam> teams = customerOrderService.findTeamByOrderId(orders.get(0).getId());
			model.addAttribute("order1", order);
			model.addAttribute("orders", orders);
			//model.addAttribute("teams", teams);
			model.addAttribute("ordersLength", orders.size());
			return "mobile/modules/home/team2";
		}else{
			return "mobile/modules/home/team_null";
		}
		
	}
	
	@RequestMapping(value="orderTeamChange")
	@ResponseBody
	public CustomerOrder orderTeamChange(Integer orderId ,Model model,HttpServletRequest request){
		CustomerOrder order = customerOrderService.findByOrderId(orderId);
		List<OrderTeam> teams = customerOrderService.findTeamByOrderId(orderId);
		if(teams.size() == 0 ){
			order.setWorks(null);
		}else{
			order.setWorks(teams);
		}
		return order;
	}
	
}
