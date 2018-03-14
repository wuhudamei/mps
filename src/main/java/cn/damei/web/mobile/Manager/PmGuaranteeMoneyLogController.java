package cn.damei.web.mobile.Manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.PmGuaranteeMoneyLog;
import cn.damei.service.mobile.Manager.PmGuaranteeMoneyLogService;
import cn.damei.entity.modules.BizPmAttendSalaryBill;
import cn.damei.service.modules.BizPmAttendSalaryBillService;
import cn.damei.entity.modules.BizGuaranteeMoneyBalance;
import cn.damei.entity.modules.BizGuaranteeMoneyPaidUsed;
import cn.damei.service.modules.BizGuaranteeMoneyBalanceService;
import cn.damei.service.modules.BizGuaranteeMoneyPaidUsedService;

@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class PmGuaranteeMoneyLogController extends BaseController{

	@Autowired
	private PmGuaranteeMoneyLogService pmGuaranteeMoneyLogService;
	
	@Autowired
	private BizGuaranteeMoneyBalanceService bizGuaranteeMoneyBalanceService;
	
	@Autowired
	private BizGuaranteeMoneyPaidUsedService bizGuaranteeMoneyPaidUsedService;
	
	@Autowired
	private BizPmAttendSalaryBillService bizPmAttendSalaryBillService;

	@RequestMapping(value="toQueryPmGuaranteeMoneyLog")
	public String toQueryPmGuaranteeMoneyLog(){
		return "mobile/modules/Manager/budget_manage";
	}

	@RequestMapping(value="queryPmGuaranteeMoneyLog")
	public String queryPmGuaranteeMoneyLog(HttpServletRequest request, Model model){

		Manager manager = SessionUtils.getManagerSession(request);

		BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance = bizGuaranteeMoneyBalanceService.findGuaranteeMoneyBalanceByEmployeeId(manager.getId());

		List<PmGuaranteeMoneyLog> list = pmGuaranteeMoneyLogService.queryPmGuaranteeMoneyLog(manager.getId());

		BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed = new BizGuaranteeMoneyPaidUsed();
		bizGuaranteeMoneyPaidUsed.setUsedEmployeeId(manager.getId());
		bizGuaranteeMoneyPaidUsed.setGuaranteeMoneyType("1");
		List<BizGuaranteeMoneyPaidUsed> PaidOffineList = bizGuaranteeMoneyPaidUsedService.findList(bizGuaranteeMoneyPaidUsed);

		bizGuaranteeMoneyPaidUsed.setGuaranteeMoneyType("2");
		List<BizGuaranteeMoneyPaidUsed> usedList = bizGuaranteeMoneyPaidUsedService.findList(bizGuaranteeMoneyPaidUsed);
		if(bizGuaranteeMoneyBalance == null){
			bizGuaranteeMoneyBalance = new BizGuaranteeMoneyBalance();
		}
		model.addAttribute("bizGuaranteeMoneyBalance", bizGuaranteeMoneyBalance);
		model.addAttribute("list", list);
		model.addAttribute("usedList", usedList);
		model.addAttribute("PaidOffineList", PaidOffineList);
		return "mobile/modules/Manager/guar_money2";
	}
	
	@RequestMapping(value="queryManagerPaidMoneyDetail")
	public String queryManagerPaidMoneyDetail(Integer id,HttpServletRequest request, Model model){
		BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed = bizGuaranteeMoneyPaidUsedService.get(id);
		model.addAttribute("bizGuaranteeMoneyPaidUsed",bizGuaranteeMoneyPaidUsed);
		return "mobile/modules/Manager/guar_paidMoneyDetail";
	}
	
	@RequestMapping(value="queryManagerUsedMoneyDetail")
	public String queryManagerUsedMoneyDetail(Integer id,HttpServletRequest request, Model model){
		BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed = bizGuaranteeMoneyPaidUsedService.get(id);
		model.addAttribute("bizGuaranteeMoneyPaidUsed",bizGuaranteeMoneyPaidUsed);
		return "mobile/modules/Manager/guar_usedMoneyDetail";
	}
	
	@RequestMapping(value="attendSettlement")
	public String attendSettlement(Integer id,HttpServletRequest request, HttpServletResponse response, Model model){

		Manager manager = SessionUtils.getManagerSession(request);
		BizPmAttendSalaryBill bizPmAttendSalaryBill= new BizPmAttendSalaryBill();
		bizPmAttendSalaryBill.setItemManagerId(manager.getId().toString());
		List<BizPmAttendSalaryBill> findSalaryBillAuditingBatchList = bizPmAttendSalaryBillService.findSalaryBillAuditingBatchList2(new Page<BizPmAttendSalaryBill>(request, response),bizPmAttendSalaryBill);
		model.addAttribute("billAuditingBatchList",findSalaryBillAuditingBatchList);
		return "mobile/modules/Manager/attendSettlement";
	}
}

