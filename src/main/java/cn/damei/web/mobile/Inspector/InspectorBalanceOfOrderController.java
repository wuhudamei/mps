package cn.damei.web.mobile.Inspector;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Inspector.InspectorBalanceEntity;
import cn.damei.service.mobile.Inspector.InspectBalanceOfOrderService;
import cn.damei.entity.mobile.Manager.OrderSignVo;

@Controller
@RequestMapping(value="${adminPath}/app/pqc/balanceOfOrder")
public class InspectorBalanceOfOrderController {

	@Autowired
	private InspectBalanceOfOrderService service;
	@RequestMapping(value={"balanceByOrderList"})
	public String   list(Model model  , HttpServletRequest request){

		List<OrderSignVo> list = service.orderByInspectorId(SessionUtils.getInspectorSession(request).getId());
		model.addAttribute("list", list);
		
		return "mobile/modules/pqc/balance/balanceByOrder/orderList";
	}
	@RequestMapping(value={"balance_detail_by_order"})
	public String   balanceDetailByOrder(Model model  , HttpServletRequest request,String id,String inspectorId){
		
		if(null != id && !"".equals(id)){
			InspectorBalanceEntity orderVo = new InspectorBalanceEntity();

			orderVo.setOrderId(Integer.parseInt(id));
			orderVo.setInspectId(Integer.parseInt(inspectorId));
			List<InspectorBalanceEntity> list = service.findBalanceDetailByOrderId(orderVo);
			InspectorBalanceEntity bDetail = new InspectorBalanceEntity();
			bDetail.setMidBalanceMoney(0d);
			bDetail.setCompleteBalanceMoney(
					0d);
			bDetail.setMidDistanceFee(0d);
			bDetail.setCompleteDistanceFee(0d);
			bDetail.setTotalMoney(0d);
			if (null != list && list.size() > 0) {
			
				for (InspectorBalanceEntity bd : list) {
					bDetail.setMidBalanceMoney(bDetail.getMidBalanceMoney() + (bd.getMidBalanceMoney()==null?0:bd.getMidBalanceMoney()));
					bDetail.setMidDistanceFee(bDetail.getMidDistanceFee()+(bd.getMidDistanceFee()==null?0:bd.getMidDistanceFee()));
					bDetail.setCompleteDistanceFee(bDetail.getCompleteDistanceFee()+(bd.getCompleteDistanceFee()==null?0:bd.getCompleteDistanceFee()));
					bDetail.setCompleteBalanceMoney(bDetail.getCompleteBalanceMoney() + (bd.getCompleteBalanceMoney()==null?0:bd.getCompleteBalanceMoney()));
					bDetail.setTotalMoney(bDetail.getMidBalanceMoney()+bDetail.getMidDistanceFee()+bDetail.getCompleteBalanceMoney()+bDetail.getCompleteDistanceFee());

				}
					model.addAttribute("balance", bDetail);
				}

			
		}else{
			
			
			
			
		}
		
		return "mobile/modules/pqc/balance/balanceByOrder/orderAccountList";
	}
}
