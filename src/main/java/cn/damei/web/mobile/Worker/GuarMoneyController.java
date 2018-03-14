package cn.damei.web.mobile.Worker;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.web.BaseController;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Worker.GuarMoney;
import cn.damei.service.mobile.Worker.GuarMoneyService;
import cn.damei.entity.mobile.Worker.Worker;
import cn.damei.entity.modules.BizGuaranteeMoneyBalance;
import cn.damei.entity.modules.BizGuaranteeMoneyPaidUsed;
import cn.damei.service.modules.BizGuaranteeMoneyBalanceService;
import cn.damei.service.modules.BizGuaranteeMoneyPaidUsedService;

@Controller
@RequestMapping(value = "${adminPath}/app/worker")
public class GuarMoneyController extends BaseController {

	@Autowired
	private GuarMoneyService guarMoneyService;

	@Autowired
	private BizGuaranteeMoneyBalanceService bizGuaranteeMoneyBalanceService;

	@Autowired
	private BizGuaranteeMoneyPaidUsedService bizGuaranteeMoneyPaidUsedService;

	@RequestMapping(value = "guarMoneyList")
	public String guarMoneyList(HttpServletRequest request, Model model) {
		Worker worker = SessionUtils.getWorkerSession(request);

		BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance = bizGuaranteeMoneyBalanceService
				.findGuaranteeMoneyBalanceByEmployeeId(worker.getId());

		List<GuarMoney> list = guarMoneyService.queryGuarMoney(worker.getId());

		BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed = new BizGuaranteeMoneyPaidUsed();
		bizGuaranteeMoneyPaidUsed.setUsedEmployeeId(worker.getId());
		bizGuaranteeMoneyPaidUsed.setGuaranteeMoneyType("1");
		List<BizGuaranteeMoneyPaidUsed> PaidOffineList = bizGuaranteeMoneyPaidUsedService
				.findList(bizGuaranteeMoneyPaidUsed);

		bizGuaranteeMoneyPaidUsed.setGuaranteeMoneyType("2");
		List<BizGuaranteeMoneyPaidUsed> usedList = bizGuaranteeMoneyPaidUsedService.findList(bizGuaranteeMoneyPaidUsed);
		if (bizGuaranteeMoneyBalance == null) {
			bizGuaranteeMoneyBalance = new BizGuaranteeMoneyBalance();
		}

		model.addAttribute("bizGuaranteeMoneyBalance", bizGuaranteeMoneyBalance);
		model.addAttribute("list", list);
		model.addAttribute("usedList", usedList);
		model.addAttribute("PaidOffineList", PaidOffineList);
		return "mobile/modules/Worker/guar_money2";
	}
	
	@RequestMapping(value="queryWorkerPaidMoneyDetail")
	public String queryManagerPaidMoneyDetail(Integer id,HttpServletRequest request, Model model){
		BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed = bizGuaranteeMoneyPaidUsedService.get(id);
		model.addAttribute("bizGuaranteeMoneyPaidUsed",bizGuaranteeMoneyPaidUsed);
		return "mobile/modules/Worker/guar_paidMoneyDetail";
	}
	
	@RequestMapping(value="queryWorkerUsedMoneyDetail")
	public String queryManagerUsedMoneyDetail(Integer id,HttpServletRequest request, Model model){
		BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed = bizGuaranteeMoneyPaidUsedService.get(id);
		model.addAttribute("bizGuaranteeMoneyPaidUsed",bizGuaranteeMoneyPaidUsed);
		return "mobile/modules/Worker/guar_usedMoneyDetail";
	}
}
