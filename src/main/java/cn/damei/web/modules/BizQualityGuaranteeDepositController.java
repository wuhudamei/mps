package cn.damei.web.modules;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.config.Global;
import cn.damei.common.constantUtils.GuaranteeMoneyConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.Order;
import cn.damei.service.modules.OrderService;
import cn.damei.entity.modules.BizGuaranteeMoneyBalance;
import cn.damei.entity.modules.BizGuaranteeMoneyPaidUsed;
import cn.damei.service.modules.BizGuaranteeMoneyBalanceService;
import cn.damei.service.modules.BizGuaranteeMoneyPaidUsedService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 质保金Controller
 * 
 * @author hyh
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/guarantee/guaranteeManager")
public class BizQualityGuaranteeDepositController extends BaseController {
	@Autowired
	private OrderService orderService;

	@Autowired
	private BizEmployeeService bizEmployeeService;

	@Autowired
	private BizEmployeeService2 bizEmployeeService2;

	@Autowired
	private BizGuaranteeMoneyPaidUsedService bizGuaranteeMoneyPaidUsedService;

	@Autowired
	private BizGuaranteeMoneyBalanceService bizGuaranteeMoneyBalanceService;

	// 查询使用质保金
	@RequestMapping(value = "queryUseGuarantee")
	public String queryUseGuarantee(BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		bizGuaranteeMoneyPaidUsed.setGuaranteeMoneyType(GuaranteeMoneyConstantUtil.guaranteeMoneyType_2);
		// 过滤门店
		if (null == bizGuaranteeMoneyPaidUsed.getStoreId()) {
			if (null != user.getStoreId()) {
				bizGuaranteeMoneyPaidUsed.setStoreId(user.getStoreId());
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		// 过滤工程模式
		if (StringUtils.isBlank(bizGuaranteeMoneyPaidUsed.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizGuaranteeMoneyPaidUsed.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizGuaranteeMoneyPaidUsed.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		if (bizGuaranteeMoneyPaidUsed.getStoreId() != null && bizGuaranteeMoneyPaidUsed.getProjectMode() != null) {
			Order order = new Order();
			order.setEmpId(UserUtils.getUser().getEmpId());
			order.setProjectMode(bizGuaranteeMoneyPaidUsed.getProjectMode());
			order.setStoreId(bizGuaranteeMoneyPaidUsed.getStoreId());
			List<Order> list = orderService.findEngineDepartByStoreIdProjectModeIdAndEmpId(order);
			model.addAttribute("list", list);
		}
		Page<BizGuaranteeMoneyPaidUsed> page = bizGuaranteeMoneyPaidUsedService
				.findPage(new Page<BizGuaranteeMoneyPaidUsed>(request, response), bizGuaranteeMoneyPaidUsed);
		model.addAttribute("page", page);
		model.addAttribute("bizGuaranteeMoneyPaidUsed", bizGuaranteeMoneyPaidUsed);
		return "modules/qualityguaranteedeposit/queryUseGuarantee";
	}

	// 打开保质金使用添加页面
	@RequestMapping(value = "openUseGuaranteFrom")
	public String openUseGuaranteFrom(BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		boolean isShowArea = true;
		User user = UserUtils.getUser();
		// 过滤门店
		if (null == bizGuaranteeMoneyPaidUsed.getStoreId()) {
			if (null != user.getStoreId()) {
				bizGuaranteeMoneyPaidUsed.setStoreId(user.getStoreId());
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			isShowArea = false;
			model.addAttribute("storeDropEnable", true);
		}
		// 过滤工程模式
		if (StringUtils.isBlank(bizGuaranteeMoneyPaidUsed.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizGuaranteeMoneyPaidUsed.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizGuaranteeMoneyPaidUsed.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		model.addAttribute("isShowArea", isShowArea);
		return "modules/qualityguaranteedeposit/useGuaranteeFrom";
	}

	// 打开质保金使用修改页面
	@RequestMapping(value = "openUseGuaranteeUpdate")
	public String openUseGuaranteeUpdate(BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		bizGuaranteeMoneyPaidUsed = bizGuaranteeMoneyPaidUsedService.get(bizGuaranteeMoneyPaidUsed);
		// 过滤门店
		if (null == bizGuaranteeMoneyPaidUsed.getStoreId()) {
			if (null != user.getStoreId()) {
				bizGuaranteeMoneyPaidUsed.setStoreId(user.getStoreId());
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		// 过滤工程模式
		if (StringUtils.isBlank(bizGuaranteeMoneyPaidUsed.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizGuaranteeMoneyPaidUsed.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizGuaranteeMoneyPaidUsed.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		model.addAttribute("bizGuaranteeMoneyPaidUsed", bizGuaranteeMoneyPaidUsed);
		return "modules/qualityguaranteedeposit/useGuaranteeUpdate";
	};

	// 保存使用质保金
	@RequestMapping(value = "saveUseGuarantee")
	public String saveUseGuarantee(BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		bizGuaranteeMoneyPaidUsedService.save(bizGuaranteeMoneyPaidUsed);
		return "redirect:" + Global.getAdminPath() + "/guarantee/guaranteeManager/queryUseGuarantee";
	}

	// 获取质保金使用详情
	@RequestMapping(value = "getUseGuaranteeDetail")
	public String getUseGuaranteeDetail(BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		bizGuaranteeMoneyPaidUsed = bizGuaranteeMoneyPaidUsedService.get(bizGuaranteeMoneyPaidUsed);
		model.addAttribute("bizGuaranteeMoneyPaidUsed", bizGuaranteeMoneyPaidUsed);
		return "modules/qualityguaranteedeposit/useGuaranteeDetail";
	}

	// 查询线下上缴质保金
	@RequestMapping(value = "queryPaidGuarantee")
	public String queryPaidGuarantee(BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		bizGuaranteeMoneyPaidUsed.setGuaranteeMoneyType(GuaranteeMoneyConstantUtil.guaranteeMoneyType_1);
		User user = UserUtils.getUser();
		// 过滤门店
		if (null == bizGuaranteeMoneyPaidUsed.getStoreId()) {
			if (null != user.getStoreId()) {
				bizGuaranteeMoneyPaidUsed.setStoreId(user.getStoreId());
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		// 过滤工程模式
		if (StringUtils.isBlank(bizGuaranteeMoneyPaidUsed.getProjectMode())) {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizGuaranteeMoneyPaidUsed.setProjectMode(user.getProjectMode());
			}
		} else {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizGuaranteeMoneyPaidUsed.setProjectMode(user.getProjectMode());
			}
		}
		Page<BizGuaranteeMoneyPaidUsed> page = bizGuaranteeMoneyPaidUsedService
				.findPage(new Page<BizGuaranteeMoneyPaidUsed>(request, response), bizGuaranteeMoneyPaidUsed);
		model.addAttribute("page", page);
		model.addAttribute("bizGuaranteeMoneyPaidUsed", bizGuaranteeMoneyPaidUsed);
		return "modules/qualityguaranteedeposit/queryPaidGuarantee";
	}

	// 打开质保金上缴添加页面
	@RequestMapping(value = "openPaidGuaranteeFrom")
	public String openPaidGuaranteeFrom(BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		// 过滤门店
		if (null == bizGuaranteeMoneyPaidUsed.getStoreId()) {
			if (null != user.getStoreId()) {
				bizGuaranteeMoneyPaidUsed.setStoreId(user.getStoreId());
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		// 过滤工程模式
		if (StringUtils.isBlank(bizGuaranteeMoneyPaidUsed.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizGuaranteeMoneyPaidUsed.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizGuaranteeMoneyPaidUsed.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		return "modules/qualityguaranteedeposit/paidGuaranteeFrom";
	}

	// 打开质保金上缴修改页面
	@RequestMapping(value = "openPaidGuaranteeUpdate")
	public String openPaidGuaranteeUpdate(BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		bizGuaranteeMoneyPaidUsed = bizGuaranteeMoneyPaidUsedService.get(bizGuaranteeMoneyPaidUsed);
		// 过滤门店
		if (null == bizGuaranteeMoneyPaidUsed.getStoreId()) {
			if (null != user.getStoreId()) {
				bizGuaranteeMoneyPaidUsed.setStoreId(user.getStoreId());
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		// 过滤工程模式
		if (StringUtils.isBlank(bizGuaranteeMoneyPaidUsed.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizGuaranteeMoneyPaidUsed.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizGuaranteeMoneyPaidUsed.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		model.addAttribute("bizGuaranteeMoneyPaidUsed", bizGuaranteeMoneyPaidUsed);
		return "modules/qualityguaranteedeposit/paidGuaranteeUpdate";
	};

	// 保存上缴质保金
	@RequestMapping(value = "savePaidGuarantee")
	public String savePaidGuarantee(BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		bizGuaranteeMoneyPaidUsedService.save(bizGuaranteeMoneyPaidUsed);
		return "redirect:" + Global.getAdminPath() + "/guarantee/guaranteeManager/queryPaidGuarantee";
	}

	// 获取上缴质保金详情
	@RequestMapping(value = "getPaidGuaranteeDetail")
	public String getPaidGuaranteeDetail(BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		bizGuaranteeMoneyPaidUsed = bizGuaranteeMoneyPaidUsedService.get(bizGuaranteeMoneyPaidUsed);
		model.addAttribute("bizGuaranteeMoneyPaidUsed", bizGuaranteeMoneyPaidUsed);
		return "modules/qualityguaranteedeposit/paidGuaranteeDetail";
	}

	/**
	 * 根据订单Id获取项目经理信息
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "findItemManagerInfoByOrderId")
	public @ResponseBody List<BizEmployee> findItemManagerInfoByOrderId(Integer orderId) {
		return bizEmployeeService.findItemManagerInfoByOrderId(orderId);
	}

	/**
	 * 根据订单Id获取工人组长信息
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "findWorkGroupInfoByOrderId")
	public @ResponseBody List<BizEmployee> findWorkGroupInfoByOrderId(Integer orderId) {
		return bizEmployeeService.findWorkGroupInfoByOrderId(orderId);
	}

	/**
	 * 根据人员Id获取质保金余额信息
	 * 
	 * @param employeeId
	 * @return
	 */
	@RequestMapping(value = "findGuaranteeMoneyBalanceByEmployeeId")
	public @ResponseBody BizGuaranteeMoneyBalance findGuaranteeMoneyBalanceByEmployeeId(Integer employeeId) {
		return bizGuaranteeMoneyBalanceService.findGuaranteeMoneyBalanceByEmployeeId(employeeId);
	}

}
