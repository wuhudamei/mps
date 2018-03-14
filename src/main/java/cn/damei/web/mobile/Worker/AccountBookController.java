package cn.damei.web.mobile.Worker;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Worker.Worker;
import cn.damei.entity.modules.PaymentDetailForBook;
import cn.damei.service.modules.BizOrderTaskpackagePaymentDetailService;

@Controller
@RequestMapping(value="${adminPath}/app/worker")
public class AccountBookController{

	@Autowired
	private BizOrderTaskpackagePaymentDetailService bizOrderTaskpackagePaymentDetailService;
	
	@RequestMapping(value="bookList")
	public String bookList(Model model, HttpServletRequest request,HttpServletResponse response){
		
		Worker worker = SessionUtils.getWorkerSession(request);
		
		//已付
		List<PaymentDetailForBook> payed = bizOrderTaskpackagePaymentDetailService.queryPayedByEmployeeId(worker.getId(),ConstantUtils.PAYMENT_DETAIL_STATUS_1);
		double payedMoney = bizOrderTaskpackagePaymentDetailService.queryPayedAmountByEmployeeId(worker.getId(),ConstantUtils.PAYMENT_DETAIL_STATUS_1);
		//未付
		List<PaymentDetailForBook> paying = bizOrderTaskpackagePaymentDetailService.queryPayingByEmployeeId(worker.getId(),ConstantUtils.PAYMENT_DETAIL_STATUS_0);
		double payingMoney = bizOrderTaskpackagePaymentDetailService.queryPayingAmountByEmployeeId(worker.getId(),ConstantUtils.PAYMENT_DETAIL_STATUS_0);
		
		model.addAttribute("payed", payed);
		model.addAttribute("payedMoney", payedMoney);
		model.addAttribute("paying", paying);
		model.addAttribute("payingMoney", payingMoney);
		
		return "mobile/modules/Worker/account_coll";
	}
}
