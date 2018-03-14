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
 * 项目经理质保金Controller
 * 
 * @author hyh
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/guarantee/guaranteeManager2")
public class BizManagerGuaranteeController extends BaseController {

	@Autowired
	private BizGuaranteeMoneyBalanceService bizGuaranteeMoneyBalanceService;

	@Autowired
	private BizGuaranteeMoneyPaidUsedService bizGuaranteeMoneyPaidUsedService;

	/**
	 * 项目经理质保金查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "queryManagerGuarantee")
	public String queryManagerGuarantee(BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		bizGuaranteeMoneyBalance.setEmpType("1");
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
		Page<BizGuaranteeMoneyBalance> page = bizGuaranteeMoneyBalanceService.findGuaranteeMoneyBalanceByParam(
				new Page<BizGuaranteeMoneyBalance>(request, response), bizGuaranteeMoneyBalance);
		model.addAttribute("page", page);
		model.addAttribute("bizGuaranteeMoneyBalance", bizGuaranteeMoneyBalance);
		return "modules/qualityguaranteedeposit/managerGuaranteeList";
	}

	/***
	 * 项目经理结算上缴质保金详情
	 * 
	 * @param bizGuaranteeMoneyBalance
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "queryManagerGuaranteePaidSettleDetail")
	public String queryManagerGuaranteePaidSettleDetail(BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance = bizGuaranteeMoneyBalanceService
				.findGuaranteeMoneyBalanceByEmployeeId(bizGuaranteeMoneyPaidUsed.getUsedEmployeeId());
		Page<BizGuaranteeMoneyPaidUsed> page = bizGuaranteeMoneyPaidUsedService
				.findManagerGuaranteeMoneyAmountPaidSettle(new Page<BizGuaranteeMoneyPaidUsed>(request, response),
						bizGuaranteeMoneyPaidUsed);
		model.addAttribute("bizGuaranteeMoneyBalance", bizGuaranteeMoneyBalance);
		model.addAttribute("page", page);
		return "modules/qualityguaranteedeposit/managerGuaranteeMoneyAmountPaidSettleDetail";
	}

	/**
	 * 项目经理线下上缴质保金详情
	 * 
	 * @param bizGuaranteeMoneyBalance
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "queryManagerGuaranteePaidOffineDetail")
	public String queryManagerGuaranteePaidOffineDetail(BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance = bizGuaranteeMoneyBalanceService
				.findGuaranteeMoneyBalanceByEmployeeId(bizGuaranteeMoneyPaidUsed.getUsedEmployeeId());
		bizGuaranteeMoneyPaidUsed.setGuaranteeMoneyType("1");
		Page<BizGuaranteeMoneyPaidUsed> page = bizGuaranteeMoneyPaidUsedService
				.findPage(new Page<BizGuaranteeMoneyPaidUsed>(request, response), bizGuaranteeMoneyPaidUsed);
		model.addAttribute("bizGuaranteeMoneyBalance", bizGuaranteeMoneyBalance);
		model.addAttribute("page", page);
		return "modules/qualityguaranteedeposit/managerGuaranteeMoneyAmountPaidOffineDetail";
	}

	/**
	 * 项目经理质保金使用详情
	 * 
	 * @param bizGuaranteeMoneyBalance
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "queryManagerGuaranteeUsedDetail")
	public String queryManagerGuaranteeUsedDetail(BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance = bizGuaranteeMoneyBalanceService
				.findGuaranteeMoneyBalanceByEmployeeId(bizGuaranteeMoneyPaidUsed.getUsedEmployeeId());
		bizGuaranteeMoneyPaidUsed.setGuaranteeMoneyType("2");
		Page<BizGuaranteeMoneyPaidUsed> page = bizGuaranteeMoneyPaidUsedService
				.findPage(new Page<BizGuaranteeMoneyPaidUsed>(request, response), bizGuaranteeMoneyPaidUsed);
		model.addAttribute("bizGuaranteeMoneyBalance", bizGuaranteeMoneyBalance);
		model.addAttribute("page", page);
		return "modules/qualityguaranteedeposit/managerGuaranteeMoneyAmountUsedDetail";
	}

}
