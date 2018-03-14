package cn.damei.web.mobile.Inspector;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.damei.entity.mobile.Inspector.InspectOrderVo;
import cn.damei.service.mobile.Inspector.InspectOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Inspector.Inspector;

@Controller
@RequestMapping(value="${adminPath}/app/pqc/myOrder")
public class InspectOrderController {

	@Autowired
	private InspectOrderService service;
	
	
	
	@RequestMapping(value="list.php")
	public String orderList(HttpServletRequest request,Model model){
		//根据登陆的质检员查询该质检员下的所有订单
		Inspector inspector = SessionUtils.getInspectorSession(request);
	InspectOrderVo vo = 	new InspectOrderVo();
		vo.setInspectorId(inspector.getId());
		
		List<InspectOrderVo> list = service.findMyOrderInfoByInspectorId(vo);
		model.addAttribute("list", list);
		
		
		return "mobile/modules/pqc/myorder/myOrder";
	}
	@RequestMapping(value="orderDetail.php")
	public String orderDetail(HttpServletRequest request,Integer orderId,Model model){
		InspectOrderVo vo = 	new InspectOrderVo();
		vo.setOrderId(orderId);
		List<InspectOrderVo> list = service.findMyOrderInfoByInspectorId(vo);
		if(list.size()>0)
		model.addAttribute("order", list.get(0));
		
		
		return "mobile/modules/pqc/myorder/myOrderDetails";
	}
	@RequestMapping(value="ajaxSearch.php")
	public @ResponseBody List<InspectOrderVo> ajaxSearch(HttpServletRequest request,String searchContent,Model model){
		Inspector inspector = SessionUtils.getInspectorSession(request);
		InspectOrderVo vo = 	new InspectOrderVo();
		vo.setInspectorId(inspector.getId());
		vo.setText(searchContent);
		List<InspectOrderVo> list = service.findMyOrderInfoByInspectorId(vo);
	
		
		return list;
	}
}
