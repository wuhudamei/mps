/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.ReportCheckDetailsPic;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizOrderTaskpackagePayment;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetails;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentFreeze;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentSummaryVo;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentVo;
import cn.damei.service.modules.BizOrderTaskpackagePaymentFreezeService;
import cn.damei.service.modules.BizOrderTaskpackagePaymentService;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetail;
import cn.damei.service.modules.BizOrderTaskpackagePaymentDetailService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 付款单Controller
 * 
 * @author qww
 * @version 2016-10-26
 */
@Controller
@RequestMapping(value = "${adminPath}/ordertaskpackagepayment/bizOrderTaskpackagePayment")
public class BizOrderTaskpackagePaymentController extends BaseController {

	@Autowired
	private BizOrderTaskpackagePaymentService bizOrderTaskpackagePaymentService;

	@Autowired
	private BizOrderTaskpackagePaymentDetailService bizOrderTaskpackagePaymentDetailService;

	@Autowired
	private BizEmployeeService2 bizEmployeeService2;

	@Autowired
	private BizOrderTaskpackagePaymentFreezeService bizOrderTaskpackagePaymentFreezeService;

	@ModelAttribute
	public BizOrderTaskpackagePayment get(@RequestParam(required = false) Integer id) {
		BizOrderTaskpackagePayment entity = null;
		if (id != null) {
			entity = bizOrderTaskpackagePaymentService.get(id);
		}
		if (entity == null) {
			entity = new BizOrderTaskpackagePayment();
		}
		return entity;
	}

	@RequiresPermissions("ordertaskpackagepayment:bizOrderTaskpackagePayment:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizOrderTaskpackagePaymentVo bizOrderTaskpackagePaymentVo, Model model) {
		User user = UserUtils.getUser();
		// 过滤门店
		if (bizOrderTaskpackagePaymentVo.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizOrderTaskpackagePaymentVo.setStoreId(null);
			} else {
				bizOrderTaskpackagePaymentVo.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		// 过滤工程模式
		if (StringUtils.isBlank(bizOrderTaskpackagePaymentVo.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackagePaymentVo.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackagePaymentVo.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		// 过滤区域
		if (null == bizOrderTaskpackagePaymentVo.getEnginDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					bizOrderTaskpackagePaymentVo.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackagePaymentVo.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackagePaymentVo.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackagePaymentVo.getEnginDepartId());
			bizOrderTaskpackagePaymentVo.setEnginDepartIds(list);
		}

		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("bizOrderTaskpackagePaymentVo", bizOrderTaskpackagePaymentVo);
		return "modules/ordertaskpackagepayment/bizOrderTaskpackagePaymentList";
	}

	@RequiresPermissions("ordertaskpackagepayment:bizOrderTaskpackagePayment:view")
	@RequestMapping(value = { "loadList", "" })
	public String loadList(BizOrderTaskpackagePaymentVo bizOrderTaskpackagePaymentVo, Model model) {
		User user = UserUtils.getUser();
		// 过滤门店
		if (bizOrderTaskpackagePaymentVo.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizOrderTaskpackagePaymentVo.setStoreId(null);
			} else {
				bizOrderTaskpackagePaymentVo.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		// 过滤工程模式
		if (StringUtils.isBlank(bizOrderTaskpackagePaymentVo.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackagePaymentVo.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackagePaymentVo.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		// 区域
		if (bizOrderTaskpackagePaymentVo.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizOrderTaskpackagePaymentVo.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackagePaymentVo.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackagePaymentVo.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackagePaymentVo.getEnginDepartId());
			bizOrderTaskpackagePaymentVo.setEnginDepartIds(list);
		}

		List<BizOrderTaskpackagePaymentVo> list = bizOrderTaskpackagePaymentService
				.queryPaymentByCondition(bizOrderTaskpackagePaymentVo);
		model.addAttribute("list", list);
		model.addAttribute("bizOrderTaskpackagePaymentVo", bizOrderTaskpackagePaymentVo);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		return "modules/ordertaskpackagepayment/bizOrderTaskpackagePaymentList";
	}

	// 查询需审核的付款单尾款
	@RequestMapping(value = "list1")
	public String list1(BizOrderTaskpackagePaymentVo bizOrderTaskpackagePaymentVo, Model model) {

		// 过滤门店
		if (bizOrderTaskpackagePaymentVo.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizOrderTaskpackagePaymentVo.setStoreId(null);
			} else {
				bizOrderTaskpackagePaymentVo.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		return "modules/ordertaskpackagepayment/bizOrderTaskpackagePaymentList1";
	}

	// 查询需审核的付款单尾款
	@RequestMapping(value = "alist1")
	public String alist1(BizOrderTaskpackagePaymentVo bizOrderTaskpackagePaymentVo, Model model) {

		// 过滤门店
		if (bizOrderTaskpackagePaymentVo.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizOrderTaskpackagePaymentVo.setStoreId(null);
			} else {
				bizOrderTaskpackagePaymentVo.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", ConstantUtils.PAYMENT_STATUS_10);
		map.put("qcStatus", "10");
		map.put("qcType", "1");
		map.put("ischeck", "0");
		map.put("cnrStatus", "1");// 任务包和之间节点关系状态--启用
		map.put("orderTaskpackagePaymentType", ConstantUtils.PAYMENT_TYPE_1);

		if (bizOrderTaskpackagePaymentVo.getStoreId() != null) {
			map.put("storeId", bizOrderTaskpackagePaymentVo.getStoreId());
		}
		if (bizOrderTaskpackagePaymentVo.getStartDate() != null) {
			map.put("startDate", bizOrderTaskpackagePaymentVo.getStartDate());
		}
		if (bizOrderTaskpackagePaymentVo.getEndDate() != null) {
			map.put("endDate", bizOrderTaskpackagePaymentVo.getEndDate());
		}
		if (bizOrderTaskpackagePaymentVo.getSettlementNo() != null
				&& !("").equals(bizOrderTaskpackagePaymentVo.getSettlementNo().trim())) {
			map.put("settlementNo", bizOrderTaskpackagePaymentVo.getSettlementNo());
		}
		List<BizOrderTaskpackagePaymentVo> list = bizOrderTaskpackagePaymentService.queryPaymentForCheck(map);
		model.addAttribute("list", list);
		return "modules/ordertaskpackagepayment/bizOrderTaskpackagePaymentList1";
	}

	// 查询需审核的付款单尾款--查看质检员验收详情
	@RequestMapping(value = "list2")
	public String list2(Integer qcBillId, Model model) throws Exception {
		// 验收详情
		BizOrderTaskpackagePaymentDetails qcBill = bizOrderTaskpackagePaymentService.findQcBill(qcBillId);
		// 验收图片
		List<ReportCheckDetailsPic> picList = bizOrderTaskpackagePaymentService.findPic(qcBillId);

		model.addAttribute("qcBill", qcBill);
		model.addAttribute("picList", picList);
		model.addAttribute("baseUrl", PicRootName.picPrefixName());

		return "modules/ordertaskpackagepayment/bizOrderTaskpackagePaymentDetails";
	}

	// 需审核的付款单尾款 --通过 修改质检单的状态 同时 修改付款单的状态
	@RequiresPermissions("ordertaskpackagepayment:bizOrderTaskpackagePayment:view")
	@RequestMapping(value = "approvePass")
	public String approvePass(Integer qcBillId, Integer paymentId, RedirectAttributes redirectAttributes) {
		boolean flag = true;
		// 修改该付款单的状态 --15
		bizOrderTaskpackagePaymentService.updateStatusByPaymentId(paymentId, ConstantUtils.PAYMENT_STATUS_15);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", ConstantUtils.PAYMENT_STATUS_10);
		// map.put("qcStatus", "10");
		map.put("qcType", "1");
		map.put("ischeck", "0");
		map.put("orderTaskpackagePaymentType", ConstantUtils.PAYMENT_TYPE_1);
		map.put("qcBillId", qcBillId);
		map.put("cnrStatus", "1");// 任务包和之间节点关系状态--启用
		List<BizOrderTaskpackagePaymentVo> list = bizOrderTaskpackagePaymentService.queryPaymentForCheckByQcBillId(map);

		if (list != null && list.size() > 0) {
			for (BizOrderTaskpackagePaymentVo bizOrderTaskpackagePaymentVo : list) {
				if ("10".equals(bizOrderTaskpackagePaymentVo.getStatus())) {
					flag = false;
					break;
				}
			}
		}
		// 修改质检单的状态 --30 质检单所有的尾款付款单
		if (flag) {
			bizOrderTaskpackagePaymentService.updateQcbillStatusById(qcBillId, "30", "");
		}
		addMessage(redirectAttributes, "结算单尾款审核通过");
		return "redirect:" + Global.getAdminPath() + "/ordertaskpackagepayment/bizOrderTaskpackagePayment/list1";
	}

	// 需审核的付款单尾款 --驳回 修改质检单的状态 修改质检单下的尾款付款单状态为10
	@RequiresPermissions("ordertaskpackagepayment:bizOrderTaskpackagePayment:view")
	@RequestMapping(value = "refusePass")
	public String refusePass(Integer qcBillId, String reason, RedirectAttributes redirectAttributes) {
		// 修改质检单的状态--20+驳回原因
		bizOrderTaskpackagePaymentService.updateQcbillStatusById(qcBillId, "20", reason);

		// 质检单下的尾款付款单
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", ConstantUtils.PAYMENT_STATUS_10);
		// map.put("qcStatus", "10");
		map.put("qcType", "1");
		map.put("ischeck", "0");
		map.put("orderTaskpackagePaymentType", ConstantUtils.PAYMENT_TYPE_1);
		map.put("qcBillId", qcBillId);
		map.put("cnrStatus", "1");// 任务包和之间节点关系状态--启用
		List<BizOrderTaskpackagePaymentVo> list = bizOrderTaskpackagePaymentService.queryPaymentForCheckByQcBillId(map);
		if (list != null && list.size() > 0) {
			for (BizOrderTaskpackagePaymentVo bizOrderTaskpackagePaymentVo : list) {
				bizOrderTaskpackagePaymentService.updateStatusByPaymentId(bizOrderTaskpackagePaymentVo.getId(),
						ConstantUtils.PAYMENT_STATUS_10);
			}
		}
		addMessage(redirectAttributes, "结算单尾款审核驳回");
		return "redirect:" + Global.getAdminPath() + "/ordertaskpackagepayment/bizOrderTaskpackagePayment/list1";
	}

	@RequiresPermissions("ordertaskpackagepayment:bizOrderTaskpackagePayment:view")
	@RequestMapping(value = "form")
	public String form(BizOrderTaskpackagePayment bizOrderTaskpackagePayment, Model model) {
		model.addAttribute("bizOrderTaskpackagePayment", bizOrderTaskpackagePayment);
		return "modules/ordertaskpackagepayment/bizOrderTaskpackagePaymentForm";
	}

	@RequiresPermissions("ordertaskpackagepayment:bizOrderTaskpackagePayment:edit")
	@RequestMapping(value = "save")
	public String save(BizOrderTaskpackagePayment bizOrderTaskpackagePayment, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizOrderTaskpackagePayment)) {
			return form(bizOrderTaskpackagePayment, model);
		}
		bizOrderTaskpackagePaymentService.save(bizOrderTaskpackagePayment);
		addMessage(redirectAttributes, "保存付款单成功");
		return "redirect:" + Global.getAdminPath() + "/ordertaskpackagepayment/bizOrderTaskpackagePayment/?repage";
	}

	@RequiresPermissions("ordertaskpackagepayment:bizOrderTaskpackagePayment:edit")
	@RequestMapping(value = "delete")
	public String delete(BizOrderTaskpackagePayment bizOrderTaskpackagePayment, RedirectAttributes redirectAttributes) {
		bizOrderTaskpackagePaymentService.delete(bizOrderTaskpackagePayment);
		addMessage(redirectAttributes, "删除付款单成功");
		return "redirect:" + Global.getAdminPath() + "/ordertaskpackagepayment/bizOrderTaskpackagePayment/?repage";
	}

	@RequiresPermissions("ordertaskpackagepayment:bizOrderTaskpackagePayment:view")
	@RequestMapping(value = { "paymentList", "" })
	public String paymentList(BizOrderTaskpackagePaymentVo bizOrderTaskpackagePaymentVo, Model model) {
		User user = UserUtils.getUser();
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		// 过滤工程模式
		if (StringUtils.isBlank(bizOrderTaskpackagePaymentVo.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackagePaymentVo.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackagePaymentVo.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		// 区域
		if (bizOrderTaskpackagePaymentVo.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizOrderTaskpackagePaymentVo.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackagePaymentVo.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackagePaymentVo.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackagePaymentVo.getEnginDepartId());
			bizOrderTaskpackagePaymentVo.setEnginDepartIds(list);
		}
		model.addAttribute("bizOrderTaskpackagePaymentVo", bizOrderTaskpackagePaymentVo);
		return "modules/ordertaskpackagepayment/bizOrderTaskpackagePaymentAllList";
	}

	@RequiresPermissions("ordertaskpackagepayment:bizOrderTaskpackagePayment:view")
	@RequestMapping(value = { "paymentLoadList", "" })
	public String paymentLoadList(BizOrderTaskpackagePaymentVo bizOrderTaskpackagePaymentVo, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		User user = UserUtils.getUser();
		// 过滤门店
		if (bizOrderTaskpackagePaymentVo.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizOrderTaskpackagePaymentVo.setStoreId(null);
			} else {
				bizOrderTaskpackagePaymentVo.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		// 过滤工程模式
		if (StringUtils.isBlank(bizOrderTaskpackagePaymentVo.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackagePaymentVo.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackagePaymentVo.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		// 区域
		if (bizOrderTaskpackagePaymentVo.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizOrderTaskpackagePaymentVo.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackagePaymentVo.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackagePaymentVo.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackagePaymentVo.getEnginDepartId());
			bizOrderTaskpackagePaymentVo.setEnginDepartIds(list);
		}

		Page<BizOrderTaskpackagePaymentVo> page = bizOrderTaskpackagePaymentService.findPaymentPage(
				new Page<BizOrderTaskpackagePaymentVo>(request, response), bizOrderTaskpackagePaymentVo);
		model.addAttribute("page", page);
		model.addAttribute("bizOrderTaskpackagePaymentVo", bizOrderTaskpackagePaymentVo);
		return "modules/ordertaskpackagepayment/bizOrderTaskpackagePaymentAllList";
	}

	@RequiresPermissions("ordertaskpackagepayment:bizOrderTaskpackagePayment:view")
	@RequestMapping(value = { "paymentListView", "" })
	public String paymentListView(Integer id, Model model) {
		BizOrderTaskpackagePaymentVo payment = bizOrderTaskpackagePaymentService.findPaymentListView(id);
		List<BizOrderTaskpackagePaymentDetail> paymentDetailList = bizOrderTaskpackagePaymentDetailService
				.queryOrderTaskpackagePaymentDetailEmployeeAmount(id);
		model.addAttribute("payment", payment);
		model.addAttribute("paymentDetailList", paymentDetailList);
		return "modules/ordertaskpackagepayment/bizOrderTaskpackagePaymentAllListDetail";
	}

	@RequiresPermissions("ordertaskpackagepayment:bizOrderTaskpackagePayment:view")
	@RequestMapping(value = { "paymentSummaryList", "" })
	public String paymentSummaryList(BizOrderTaskpackagePaymentSummaryVo bizOrderTaskpackagePaymentSummaryVo,
			Model model, HttpServletRequest request, HttpServletResponse response) {
		// 过滤门店
		if (bizOrderTaskpackagePaymentSummaryVo.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizOrderTaskpackagePaymentSummaryVo.setStoreId(null);
			} else {
				bizOrderTaskpackagePaymentSummaryVo.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		return "modules/ordertaskpackagepayment/bizOrderTaskpackagePaymentSummaryList";
	}

	@RequiresPermissions("ordertaskpackagepayment:bizOrderTaskpackagePayment:view")
	@RequestMapping(value = { "paymentSummaryLoadList", "" })
	public String paymentSummaryLoadList(BizOrderTaskpackagePaymentSummaryVo bizOrderTaskpackagePaymentSummaryVo,
			Model model, HttpServletRequest request, HttpServletResponse response) {
		// 过滤门店
		if (bizOrderTaskpackagePaymentSummaryVo.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizOrderTaskpackagePaymentSummaryVo.setStoreId(null);
			} else {
				bizOrderTaskpackagePaymentSummaryVo.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		Page<BizOrderTaskpackagePaymentSummaryVo> page = bizOrderTaskpackagePaymentService.findPaymentSummaryPage(
				new Page<BizOrderTaskpackagePaymentSummaryVo>(request, response), bizOrderTaskpackagePaymentSummaryVo);
		model.addAttribute("page", page);
		return "modules/ordertaskpackagepayment/bizOrderTaskpackagePaymentSummaryList";
	}

	/**
	 * 付款单冻结/解冻查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "paymentFreezeList")
	public String paymentFreezeList(BizOrderTaskpackagePaymentVo bizOrderTaskpackagePaymentVo, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		User user = UserUtils.getUser();
		// 过滤门店
		if (bizOrderTaskpackagePaymentVo.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizOrderTaskpackagePaymentVo.setStoreId(null);
			} else {
				bizOrderTaskpackagePaymentVo.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		// 过滤工程模式
		if (StringUtils.isBlank(bizOrderTaskpackagePaymentVo.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackagePaymentVo.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackagePaymentVo.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		// 区域
		if (bizOrderTaskpackagePaymentVo.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizOrderTaskpackagePaymentVo.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackagePaymentVo.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackagePaymentVo.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackagePaymentVo.getEnginDepartId());
			bizOrderTaskpackagePaymentVo.setEnginDepartIds(list);
		}

		Page<BizOrderTaskpackagePaymentVo> page = bizOrderTaskpackagePaymentService.findPaymentFreezePage(
				new Page<BizOrderTaskpackagePaymentVo>(request, response), bizOrderTaskpackagePaymentVo);
		model.addAttribute("page", page);
		model.addAttribute("bizOrderTaskpackagePaymentVo", bizOrderTaskpackagePaymentVo);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());

		return "modules/ordertaskpackagepayment/bizOrderTaskpackagePaymentFreezeList";
	}

	/**
	 * 解冻/冻结付款单
	 * 
	 * @param id
	 * isFrozenType 1:冻结首款+尾款  0：不是
	 * @return
	 */
	@RequestMapping(value = "freezePayment")
	public @ResponseBody String freezePayment(Integer id, String status, String frozenRemarks,int isFrozenType) {
		String result = null;
		try {
			result = bizOrderTaskpackagePaymentFreezeService.freezePayment(id, status, frozenRemarks,isFrozenType);
		} catch (Exception e) {
			result = "error";
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	/**
	 * 付款单冻结解冻详情
	 * 
	 * @param bizOrderTaskpackagePaymentFreeze
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "findfreezePaymentDetailList")
	public String findfreezePaymentDetailList(BizOrderTaskpackagePaymentFreeze bizOrderTaskpackagePaymentFreeze,
			Model model, HttpServletRequest request, HttpServletResponse response) {
		List<BizOrderTaskpackagePaymentFreeze> list = bizOrderTaskpackagePaymentFreezeService
				.findList(bizOrderTaskpackagePaymentFreeze);
		model.addAttribute("list", list);
		return "modules/ordertaskpackagepayment/bizOrderTaskpackagePaymentFreezeDetailList";
	}
	
	@RequestMapping(value = "findBalancePaymentByPaymentId")
	public @ResponseBody BizOrderTaskpackagePayment findBalancePaymentByPaymentId(Integer paymentId){
		return bizOrderTaskpackagePaymentService.findBalancePaymentByPaymentId(paymentId);
	}

}