package cn.damei.web.mobile.Manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.utils.StringUtils;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Manager.balanceDetail;
import cn.damei.service.mobile.Manager.BalanceByOrderService;
import cn.damei.entity.mobile.Manager.PmSettlementBudget;
import cn.damei.entity.mobile.Manager.OrderSignVo;

@Controller
@RequestMapping(value = "${adminPath}/app/manager/balancebyorder")
public class BalanceByOrderController {

	@Autowired
	private BalanceByOrderService service;

	@RequestMapping(value = { "list", "" })
	public String list(PmSettlementBudget pmSettlementBudget, Model model, HttpServletRequest request) {

		pmSettlementBudget.setPmEmployeeId(SessionUtils.getManagerSession(request).getId());
		List<PmSettlementBudget> list = service.orderByManagerId(pmSettlementBudget);
		model.addAttribute("list", list);
		if(pmSettlementBudget.getQueryParam() != null && !pmSettlementBudget.equals("")){
			model.addAttribute("queryParam", pmSettlementBudget.getQueryParam());
		}
		return "mobile/modules/Manager/balance/order_list";
	}

	
	
	@RequestMapping(value = { "balance_detail_by_order" })
	public String balanceDetailByOrder(Model model, HttpServletRequest request, String id, String managerId) {

		if (StringUtils.isNotBlank(id)) {
			OrderSignVo orderVo = new OrderSignVo();

			orderVo.setId(Integer.parseInt(id));
			orderVo.setItemManagerId(Integer.parseInt(managerId));
			List<balanceDetail> list = service.findBalanceDetailByOrderId(orderVo);
			if (null != list && list.size() > 0) {
				balanceDetail bDetail = new balanceDetail();
				bDetail.setMidBalanceMoney(0d);
				bDetail.setCompleteBalanceMoney(0d);
				bDetail.setFreePayMoney(0d);
				bDetail.setMaterialsStandardAmount(0d);
				bDetail.setMidFineMoney(0d);
				bDetail.setComleteFineMoney(0d);
				bDetail.setMaterialSelfbuyReimburseAmount(0d);
				bDetail.setGuaranteMoney(0d);
				bDetail.setTotalMoney(0d);
				bDetail.setMidwayAuxiliaryMaterialsDeductAmount(0d);
				bDetail.setCompleteAuxiliaryMaterialsDeductAmount(0d);

				bDetail.setMidwayPunishAmount(0d);
				bDetail.setMidwayRewardAmount(0d);
				bDetail.setCompletePunishAmount(0d);
				bDetail.setCompleteRewardAmount(0d);
				for (balanceDetail bd : list) {
					bDetail.setMidBalanceMoney(bDetail.getMidBalanceMoney()
							+ (bd.getMidBalanceMoney() == null ? 0 : bd.getMidBalanceMoney()));
					bDetail.setCompleteBalanceMoney(bDetail.getCompleteBalanceMoney()
							+ (bd.getCompleteBalanceMoney() == null ? 0 : bd.getCompleteBalanceMoney()));
					bDetail.setFreePayMoney(
							(bd.getFreePayMoney() == null ? 0 : bd.getFreePayMoney()) + bDetail.getFreePayMoney());
					bDetail.setMaterialsStandardAmount(bDetail.getMaterialsStandardAmount()
							+ (bd.getMaterialsStandardAmount() == null ? 0 : bd.getMaterialsStandardAmount()));
					bDetail.setMidFineMoney(
							bDetail.getMidFineMoney() + (bd.getMidFineMoney() == null ? 0 : bd.getMidFineMoney()));
					bDetail.setComleteFineMoney(bDetail.getComleteFineMoney()
							+ (bd.getComleteFineMoney() == null ? 0 : bd.getComleteFineMoney()));
					bDetail.setMaterialSelfbuyReimburseAmount(bDetail.getMaterialSelfbuyReimburseAmount()
							+ (bd.getMaterialSelfbuyReimburseAmount() == null ? 0
									: bd.getMaterialSelfbuyReimburseAmount()));
					bDetail.setGuaranteMoney(
							bDetail.getGuaranteMoney() + (bd.getGuaranteMoney() == null ? 0 : bd.getGuaranteMoney()));
					bDetail.setMidwayAuxiliaryMaterialsDeductAmount(bDetail.getMidwayAuxiliaryMaterialsDeductAmount()
							+ (bd.getMidwayAuxiliaryMaterialsDeductAmount() == null ? 0
									: bd.getMidwayAuxiliaryMaterialsDeductAmount()));
					bDetail.setCompleteAuxiliaryMaterialsDeductAmount(
							bDetail.getCompleteAuxiliaryMaterialsDeductAmount()
									+ (bd.getCompleteAuxiliaryMaterialsDeductAmount() == null ? 0
											: bd.getCompleteAuxiliaryMaterialsDeductAmount()));
					bDetail.setMidwayRewardAmount(bDetail.getMidwayRewardAmount()
							+ (bd.getMidwayRewardAmount() == null ? 0 : bd.getMidwayRewardAmount()));
					bDetail.setMidwayPunishAmount(bDetail.getMidwayPunishAmount()
							+ (bd.getMidwayPunishAmount() == null ? 0 : bd.getMidwayPunishAmount()));
					bDetail.setCompleteRewardAmount(bDetail.getCompleteRewardAmount()
							+ (bd.getCompleteRewardAmount() == null ? 0 : bd.getCompleteRewardAmount()));
					bDetail.setCompletePunishAmount(bDetail.getCompletePunishAmount()
							+ (bd.getCompletePunishAmount() == null ? 0 : bd.getCompletePunishAmount()));
					bDetail.setTotalMoney(bDetail.getMidBalanceMoney() + bDetail.getFreePayMoney()
							+ bDetail.getCompleteBalanceMoney() + bDetail.getMaterialsStandardAmount()
							+ bDetail.getMidFineMoney() + bDetail.getComleteFineMoney() + bDetail.getGuaranteMoney()
							+ bDetail.getMaterialSelfbuyReimburseAmount()
							+ bDetail.getMidwayAuxiliaryMaterialsDeductAmount()
							+ bDetail.getCompleteAuxiliaryMaterialsDeductAmount() + bDetail.getMidwayRewardAmount()
							+ bDetail.getMidwayPunishAmount() + bDetail.getCompleteRewardAmount()
							+ bDetail.getCompletePunishAmount());
				}
				model.addAttribute("balance", bDetail);
			}

		} else {

		}

		return "mobile/modules/Manager/balance/bugdet_project_details";
	}
}
