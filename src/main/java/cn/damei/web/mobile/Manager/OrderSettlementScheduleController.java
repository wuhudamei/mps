package cn.damei.web.mobile.Manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.OrderSettlementSchedule;
import cn.damei.service.mobile.Manager.OrderSettlementScheduleService;


@Controller
@RequestMapping(value = "mobile/modules/manager/projectmanagersettlement/web/orderSettlementSchedule")
public class OrderSettlementScheduleController {
	
	@Autowired
	private OrderSettlementScheduleService orderSettlementScheduleService;


	@RequestMapping(value = "orderSettlementScheduleList")
	public String orderSettlementScheduleList(OrderSettlementSchedule orderSettlementSchedule,Model model, HttpServletRequest request){
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		orderSettlementSchedule.setPmEmployeeId(manager.getId());
		List<OrderSettlementSchedule> list = orderSettlementScheduleService.queryOrderSettlementScheduleList(orderSettlementSchedule);
		model.addAttribute("list",list);
		if(orderSettlementSchedule.getQueryParam() !=null && !orderSettlementSchedule.getQueryParam().equals("")){
			model.addAttribute("queryParam",orderSettlementSchedule.getQueryParam());
		}
		return "/mobile/modules/Manager/projectmanagersettlement/ordersettlementschedule/orderSettlementScheduleList";
	}
	

	@RequestMapping(value = "orderMidwaySettlementSchedule")
	public String orderMidwaySettlementSchedule(int orderId, Model model){
		OrderSettlementSchedule orderSettlementSchedule = orderSettlementScheduleService.queryMidwaySettlementScheduleByOrderId(orderId);
		model.addAttribute("orderSettlementSchedule",orderSettlementSchedule);
		return "/mobile/modules/Manager/projectmanagersettlement/ordersettlementschedule/midwayOrderSettlementScheduleList";
	}
	

	@RequestMapping(value = "orderCompleteSettlementSchedule")
	public String orderCompleteSettlementSchedule(int orderId, Model model){
		OrderSettlementSchedule orderSettlementSchedule = orderSettlementScheduleService.queryCompleteSettlementScheduleByOrderId(orderId);
		model.addAttribute("orderSettlementSchedule",orderSettlementSchedule);
		return "/mobile/modules/Manager/projectmanagersettlement/ordersettlementschedule/completeOrderSettlementScheduleList";
	}
}
