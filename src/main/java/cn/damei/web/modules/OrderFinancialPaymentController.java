package cn.damei.web.modules;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.Order;
import cn.damei.service.modules.OrderFinancialPaymentService;


/**
 * 财务收款2.0
 */
@Controller
@RequestMapping(value = "${adminPath}/orderFinancialPayment/orderFinancialPayment")
public class OrderFinancialPaymentController extends BaseController {

	@Autowired
	private OrderFinancialPaymentService orderFinancialPaymentService;


	@RequiresPermissions("orderFinancialPayment:orderFinancialPayment:view")
	@RequestMapping(value = { "list", "" })
	public String list(Order order, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		order.setStoreId("2");
		
		return "modules/orderFinancialPayment/orderFinancialPaymenyt";
	}
	
	/**
	 * 查询
	 * @param storeId
	 * @param orderNumber
	 * @param customerName
	 * @param customerPhone
	 * @return
	 */
	@RequiresPermissions("orderFinancialPayment:orderFinancialPayment:view")
	@RequestMapping(value = "order_financial_payment_ajax_list")
	public @ResponseBody List<Map<String, String>> orderFinancialPaymentAjaxList(String storeId,String orderNumber,String customerName,String customerPhone) {
		
		List<Map<String, String>> list = orderFinancialPaymentService.orderFinancialPaymentAjaxList(storeId,orderNumber,customerName,customerPhone);
		
		return list;
	}

	

}