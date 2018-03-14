package cn.damei.web.mobile.Manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.SessionUtils;
import cn.damei.service.mobile.Manager.BalanceByMonthService;
import cn.damei.entity.mobile.Manager.balanceDetail;
import cn.damei.entity.mobile.Manager.Manager;

@Controller
@RequestMapping(value = "${adminPath}/app/manager/balancebymonth")
public class BalanceByMonthController {
	private Logger logger = LoggerFactory.getLogger(BalanceByMonthController.class);
	@Autowired
	private BalanceByMonthService service;

	@RequestMapping(value = "list")
	public String list(HttpServletRequest request,Model model) {
		
		String format = new SimpleDateFormat("yyyyMM").format(new Date());
		List<balanceDetail> list = service.getBalanceDetailByMonth(format,SessionUtils.getManagerSession(request).getId());

		if (null != list && list.size() > 0) {
			balanceDetail bDetail = new balanceDetail();
			bDetail.setMidBalanceMoney(0d);
			bDetail.setCompleteBalanceMoney(
					0d);
			bDetail.setFreePayMoney(0d);
			bDetail.setMaterialsStandardAmount(
					0d);
			bDetail.setMidFineMoney(0d);
			bDetail.setComleteFineMoney(0d);
			bDetail.setMaterialSelfbuyReimburseAmount(0d);
			bDetail.setGuaranteMoney(0d);
			bDetail.setTotalMoney(0d);
			bDetail.setMidwayAuxiliaryMaterialsDeductAmount(0d);
			bDetail.setCompleteAuxiliaryMaterialsDeductAmount(0d);
			for (balanceDetail bd : list) {
				bDetail.setMidBalanceMoney(bDetail.getMidBalanceMoney() + (bd.getMidBalanceMoney()==null?0:bd.getMidBalanceMoney()));
				bDetail.setCompleteBalanceMoney(
						bDetail.getCompleteBalanceMoney() + (bd.getCompleteBalanceMoney()==null?0:bd.getCompleteBalanceMoney()));
				bDetail.setFreePayMoney((bd.getFreePayMoney()==null?0:bd.getFreePayMoney()) + bDetail.getFreePayMoney());
				bDetail.setMaterialsStandardAmount(
						bDetail.getMaterialsStandardAmount() + (bd.getMaterialsStandardAmount()==null?0:bd.getMaterialsStandardAmount()));
				bDetail.setMidFineMoney(bDetail.getMidFineMoney() + (bd.getMidFineMoney()==null?0:bd.getMidFineMoney()));
				bDetail.setComleteFineMoney(bDetail.getComleteFineMoney() + (bd.getComleteFineMoney()==null?0:bd.getComleteFineMoney()));
				bDetail.setMaterialSelfbuyReimburseAmount(bDetail.getMaterialSelfbuyReimburseAmount()+(bd.getMaterialSelfbuyReimburseAmount()==null?0:bd.getMaterialSelfbuyReimburseAmount()));
				bDetail.setGuaranteMoney(bDetail.getGuaranteMoney() + (bd.getGuaranteMoney()==null?0:bd.getGuaranteMoney()));
				bDetail.setMidwayAuxiliaryMaterialsDeductAmount(bDetail.getMidwayAuxiliaryMaterialsDeductAmount()+(bd.getMidwayAuxiliaryMaterialsDeductAmount()==null?0:bd.getMidwayAuxiliaryMaterialsDeductAmount()));
				bDetail.setCompleteAuxiliaryMaterialsDeductAmount(bDetail.getCompleteAuxiliaryMaterialsDeductAmount()+(bd.getCompleteAuxiliaryMaterialsDeductAmount()==null?0:bd.getCompleteAuxiliaryMaterialsDeductAmount()));
				bDetail.setTotalMoney(bDetail.getMidBalanceMoney()+bDetail.getFreePayMoney()
				+bDetail.getCompleteBalanceMoney()+bDetail.getMaterialsStandardAmount()+bDetail.getMidFineMoney()
				+bDetail.getComleteFineMoney()+bDetail.getGuaranteMoney()+bDetail.getMaterialSelfbuyReimburseAmount()
				+bDetail.getCompleteAuxiliaryMaterialsDeductAmount()+bDetail.getMidwayAuxiliaryMaterialsDeductAmount());
			}
			model.addAttribute("balance", bDetail);
		}

		return "mobile/modules/Manager/balance/order_budget";
	}
	
	
	@RequestMapping(value = "queryBalanceDetailList")
	public String queryBalanceDetailList(String startSettleMonth,HttpServletRequest request,Model model) throws Exception{
		Manager  manager = SessionUtils.getManagerSession(request);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Calendar calendar = Calendar.getInstance();
		Date date =new Date();
		if(startSettleMonth == null){
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, -6);
		}else{
			calendar.setTime(sdf.parse(startSettleMonth));
			calendar.add(Calendar.MONTH, -6);
		}
		startSettleMonth = sdf.format(calendar.getTime());
		String endSettleMonth = sdf.format(date);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("startSettleMonth", startSettleMonth);
		param.put("endSettleMonth", endSettleMonth);
		param.put("pmEmployeeId", manager.getId());
		List<balanceDetail> list = service.getBalanceDetailByParam(param);
		double totalMoney = service.queryTotalMoneyByParam(param);
		model.addAttribute("list",list);
		model.addAttribute("totalMoney",totalMoney);
		model.addAttribute("startSettleMonth",startSettleMonth);
		return "mobile/modules/Manager/balance/month_list";
	}
	
	@RequestMapping(value = "querySettleBillByParam")
	public String querySettleBillByParam(String settleMonth,HttpServletRequest request,Model model) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Manager  manager = SessionUtils.getManagerSession(request);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("settleMonth", settleMonth);
		param.put("pmEmployeeId", manager.getId());
		List<balanceDetail> list = service.querySettleBillByParam(param);
		model.addAttribute("list",list);
		model.addAttribute("settleMonthDate",sdf.parse(settleMonth));
		return "mobile/modules/Manager/balance/monthListDetails";
	}

	@RequestMapping(value = "query_by_month")
	public @ResponseBody balanceDetail queryByMonth(Integer queryMonth,HttpServletRequest request) {

		if (null != queryMonth) {
			try {
				Date month = new SimpleDateFormat("yyyyMM").parse(String.valueOf(queryMonth));
				String format = new SimpleDateFormat("yyyyMM").format(month);
				
				
				List<balanceDetail> list = service.getBalanceDetailByMonth(format,SessionUtils.getManagerSession(request).getId());
				balanceDetail bDetail = new balanceDetail();
				bDetail.setMidBalanceMoney(0d);
				bDetail.setCompleteBalanceMoney(
						0d);
				bDetail.setFreePayMoney(0d);
				bDetail.setMaterialsStandardAmount(
						0d);
				bDetail.setMidFineMoney(0d);
				bDetail.setComleteFineMoney(0d);
				bDetail.setGuaranteMoney(0d);
				bDetail.setMaterialSelfbuyReimburseAmount(0d);
				bDetail.setTotalMoney(0d);
				bDetail.setMidwayAuxiliaryMaterialsDeductAmount(0d);
				bDetail.setCompleteAuxiliaryMaterialsDeductAmount(0d);
				if (null != list && list.size() > 0) {
				
					for (balanceDetail bd : list) {
						bDetail.setMidBalanceMoney(bDetail.getMidBalanceMoney() + (bd.getMidBalanceMoney()==null?0:bd.getMidBalanceMoney()));
						bDetail.setCompleteBalanceMoney(
								bDetail.getCompleteBalanceMoney() + (bd.getCompleteBalanceMoney()==null?0:bd.getCompleteBalanceMoney()));
						bDetail.setFreePayMoney((bd.getFreePayMoney()==null?0:bd.getFreePayMoney()) + bDetail.getFreePayMoney());
						bDetail.setMaterialsStandardAmount(
								bDetail.getMaterialsStandardAmount() + (bd.getMaterialsStandardAmount()==null?0:bd.getMaterialsStandardAmount()));
						bDetail.setMidFineMoney(bDetail.getMidFineMoney() + (bd.getMidFineMoney()==null?0:bd.getMidFineMoney()));
						bDetail.setComleteFineMoney(bDetail.getComleteFineMoney() + (bd.getComleteFineMoney()==null?0:bd.getComleteFineMoney()));
						bDetail.setMaterialSelfbuyReimburseAmount(bDetail.getMaterialSelfbuyReimburseAmount()+(bd.getMaterialSelfbuyReimburseAmount()==null?0:bd.getMaterialSelfbuyReimburseAmount()));
						bDetail.setGuaranteMoney(bDetail.getGuaranteMoney() + (bd.getGuaranteMoney()==null?0:bd.getGuaranteMoney()));
						bDetail.setMidwayAuxiliaryMaterialsDeductAmount(bDetail.getMidwayAuxiliaryMaterialsDeductAmount()+(bd.getMidwayAuxiliaryMaterialsDeductAmount()==null?0:bd.getMidwayAuxiliaryMaterialsDeductAmount()));
						bDetail.setCompleteAuxiliaryMaterialsDeductAmount(bDetail.getCompleteAuxiliaryMaterialsDeductAmount()+(bd.getCompleteAuxiliaryMaterialsDeductAmount()==null?0:bd.getCompleteAuxiliaryMaterialsDeductAmount()));
						bDetail.setTotalMoney(bDetail.getMidBalanceMoney()+bDetail.getFreePayMoney()
						+bDetail.getCompleteBalanceMoney()+bDetail.getMaterialsStandardAmount()+bDetail.getMidFineMoney()
						+bDetail.getComleteFineMoney()+bDetail.getGuaranteMoney()+bDetail.getMaterialSelfbuyReimburseAmount()
						+bDetail.getCompleteAuxiliaryMaterialsDeductAmount()+bDetail.getMidwayAuxiliaryMaterialsDeductAmount());
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
