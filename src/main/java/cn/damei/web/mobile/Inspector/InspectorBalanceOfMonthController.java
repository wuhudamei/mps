package cn.damei.web.mobile.Inspector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Inspector.InspectorBalanceEntity;
import cn.damei.service.mobile.Inspector.InspectorBalanceOfMonthService;

@Controller
@RequestMapping(value="${adminPath}/app/pqc/balance")
public class InspectorBalanceOfMonthController {

	private Logger logger = LoggerFactory.getLogger(InspectorBalanceOfMonthController.class);
	
	@Autowired
	private InspectorBalanceOfMonthService service ;
	@RequestMapping(value = "list")
	public String list(HttpServletRequest request,Model model) {
		
		String format = new SimpleDateFormat("yyyyMM").format(new Date());
		List<InspectorBalanceEntity> list = service.getBalanceDetailByMonth(format,SessionUtils.getInspectorSession(request).getId());
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
		}
			model.addAttribute("balance", bDetail);
		return "mobile/modules/pqc/balance/balanByMonth/orderMonthList";
		

	}
	
	@RequestMapping(value = "query_by_month.php")
	public @ResponseBody InspectorBalanceEntity queryByMonth(Integer queryMonth,HttpServletRequest request) {

		if (null != queryMonth) {
			try {
				Date month = new SimpleDateFormat("yyyyMM").parse(String.valueOf(queryMonth));
				String format = new SimpleDateFormat("yyyyMM").format(month);
				
				
				List<InspectorBalanceEntity> list = service.getBalanceDetailByMonth(format,SessionUtils.getInspectorSession(request).getId());
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
				}
				return bDetail;
			} catch (ParseException e) {

				logger.warn("按月结算时:  传入参数月份格式不对或参数不合法  =====" + queryMonth);
				e.printStackTrace();
				return null;

			}

		}
		return null;

	}
}
