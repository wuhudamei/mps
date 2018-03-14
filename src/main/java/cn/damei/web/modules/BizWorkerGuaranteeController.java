package cn.damei.web.modules;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizGuaranteeMoneyBalance;
import cn.damei.entity.modules.BizGuaranteeMoneyPaidUsed;
import cn.damei.service.modules.BizGuaranteeMoneyBalanceService;
import cn.damei.service.modules.BizGuaranteeMoneyPaidUsedService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 工人质保金Controller
 * @author hyh
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/guarantee/guaranteeWorker3")
public class BizWorkerGuaranteeController extends BaseController{
	
	@Autowired
	private BizGuaranteeMoneyBalanceService bizGuaranteeMoneyBalanceService;
	
	@Autowired
	private BizGuaranteeMoneyPaidUsedService bizGuaranteeMoneyPaidUsedService;
	
	
	/**
	 * 工人质保金查询
	 * @return
	 */
	@RequestMapping(value = "queryWorkerGuarantee")
	public String queryWorkerGuarantee(BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance, HttpServletRequest request,
			HttpServletResponse response, Model model){
		bizGuaranteeMoneyBalance.setEmpType("2");
		User user = UserUtils.getUser();
		// 过滤门店
		if (null == bizGuaranteeMoneyBalance.getStoreId()) {
			if (null != user.getStoreId()) {
				bizGuaranteeMoneyBalance.setStoreId(user.getStoreId());
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		// 过滤工程模式
		if (StringUtils.isBlank(bizGuaranteeMoneyBalance.getProjectMode())) {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizGuaranteeMoneyBalance.setProjectMode(user.getProjectMode());
			}
		} else {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizGuaranteeMoneyBalance.setProjectMode(user.getProjectMode());
			}
		}
		Page<BizGuaranteeMoneyBalance> page = bizGuaranteeMoneyBalanceService.findGuaranteeMoneyBalanceByParam(new Page<BizGuaranteeMoneyBalance>(request, response), bizGuaranteeMoneyBalance);
		model.addAttribute("page", page);
		model.addAttribute("bizGuaranteeMoneyBalance", bizGuaranteeMoneyBalance);
		return "modules/qualityguaranteedeposit/workerGuaranteeList";
	}

	/***
	 * 工人结算上缴质保金详情
	 * @param bizGuaranteeMoneyBalance
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "queryWorkerGuaranteePaidSettleDetail")
	public String queryWorkerGuaranteePaidSettleDetail(BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed, HttpServletRequest request,
			HttpServletResponse response, Model model){
		BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance = bizGuaranteeMoneyBalanceService.findGuaranteeMoneyBalanceByEmployeeId(bizGuaranteeMoneyPaidUsed.getUsedEmployeeId());
		Page<BizGuaranteeMoneyPaidUsed> page = bizGuaranteeMoneyPaidUsedService.findWorkerGuaranteeMoneyAmountPaidSettle(new Page<BizGuaranteeMoneyPaidUsed>(request, response), bizGuaranteeMoneyPaidUsed);
		model.addAttribute("bizGuaranteeMoneyBalance",bizGuaranteeMoneyBalance);
		model.addAttribute("page",page);
		return "modules/qualityguaranteedeposit/workerGuaranteeMoneyAmountPaidSettleDetail";
	}
	
	/**
	 * 工人线下上缴质保金详情
	 * @param bizGuaranteeMoneyBalance
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "queryWorkerGuaranteePaidOffineDetail")
	public String queryWorkerGuaranteePaidOffineDetail(BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed, HttpServletRequest request,
			HttpServletResponse response, Model model){
		BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance = bizGuaranteeMoneyBalanceService.findGuaranteeMoneyBalanceByEmployeeId(bizGuaranteeMoneyPaidUsed.getUsedEmployeeId());
		bizGuaranteeMoneyPaidUsed.setGuaranteeMoneyType("1");
		Page<BizGuaranteeMoneyPaidUsed> page = bizGuaranteeMoneyPaidUsedService.findPage(new Page<BizGuaranteeMoneyPaidUsed>(request, response), bizGuaranteeMoneyPaidUsed);
		model.addAttribute("bizGuaranteeMoneyBalance",bizGuaranteeMoneyBalance);
		model.addAttribute("page",page);
		return "modules/qualityguaranteedeposit/workerGuaranteeMoneyAmountPaidOffineDetail";
	}
	
	/**
	 * 工人质保金使用详情
	 * @param bizGuaranteeMoneyBalance
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "queryWorkerGuaranteeUsedDetail")
	public String queryWorkerGuaranteeUsedDetail(BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed, HttpServletRequest request,
			HttpServletResponse response, Model model){
		BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance = bizGuaranteeMoneyBalanceService.findGuaranteeMoneyBalanceByEmployeeId(bizGuaranteeMoneyPaidUsed.getUsedEmployeeId());
		bizGuaranteeMoneyPaidUsed.setGuaranteeMoneyType("2");
		Page<BizGuaranteeMoneyPaidUsed> page = bizGuaranteeMoneyPaidUsedService.findPage(new Page<BizGuaranteeMoneyPaidUsed>(request, response), bizGuaranteeMoneyPaidUsed);
		model.addAttribute("bizGuaranteeMoneyBalance",bizGuaranteeMoneyBalance);
		model.addAttribute("page",page);
	return "modules/qualityguaranteedeposit/workerGuaranteeMoneyAmountUsedDetail";	
	}
}
