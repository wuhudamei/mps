
package cn.damei.web.modules;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentVo;
import cn.damei.service.modules.BizOrderTaskpackagePaymentService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.ExportFinanceExcel;
import cn.damei.common.utils.ExportProjectExcel;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetail;
import cn.damei.entity.modules.PaymentDetailForExcel;
import cn.damei.service.modules.BizOrderTaskpackagePaymentDetailService;
import cn.damei.service.modules.BizOrderTaskpackagePaymentDetailVoService;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentSummary;
import cn.damei.entity.modules.OrderInformation;
import cn.damei.service.modules.BizOrderTaskpackagePaymentSummaryService;
import cn.damei.service.modules.BizOrderTaskpackagePaymentDetailMergeService;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailSplitVo;
import cn.damei.entity.modules.PaymentDetailSplitForExcel;
import cn.damei.service.modules.BizOrderTaskpackagePaymentDetailSplitService;
import cn.damei.service.modules.BizTaskPackageTypeService;


@Controller
@RequestMapping(value = "${adminPath}/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary")
public class BizOrderTaskpackagePaymentSummaryController extends BaseController {

	@Autowired
	private BizOrderTaskpackagePaymentSummaryService bizOrderTaskpackagePaymentSummaryService;

	@Autowired
	private BizOrderTaskpackagePaymentDetailMergeService bizOrderTaskpackagePaymentDetailMergeService;

	@Autowired
	private BizOrderTaskpackagePaymentDetailSplitService bizOrderTaskpackagePaymentDetailSplitService;

	@Autowired
	private BizOrderTaskpackagePaymentDetailService bizOrderTaskpackagePaymentDetailService;

	@Autowired
	private BizOrderTaskpackagePaymentDetailVoService bizOrderTaskpackagePaymentDetailVoService;

	@Autowired
	private BizTaskPackageTypeService bizTaskPackageTypeService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizOrderTaskpackagePaymentService bizOrderTaskpackagePaymentService;

	@ModelAttribute
	public BizOrderTaskpackagePaymentSummary get(@RequestParam(required = false) Integer id) {
		BizOrderTaskpackagePaymentSummary entity = null;
		if (id != null) {
			entity = bizOrderTaskpackagePaymentSummaryService.get(id);
		}
		if (entity == null) {
			entity = new BizOrderTaskpackagePaymentSummary();
		}
		return entity;
	}

	@RequiresPermissions("ordertaskpackagepaymentsummary:bizOrderTaskpackagePaymentSummary:view")
	@RequestMapping(value = "listPage")
	public String listPage(BizOrderTaskpackagePaymentSummary bizOrderTaskpackagePaymentSummary,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		if (null == bizOrderTaskpackagePaymentSummary.getEnginDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					bizOrderTaskpackagePaymentSummary.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackagePaymentSummary.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackagePaymentSummary.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackagePaymentSummary.getEnginDepartId());
			bizOrderTaskpackagePaymentSummary.setEnginDepartIds(list);
		}
		if (UserUtils.getUser().getStoreId() != null) {

			bizOrderTaskpackagePaymentSummary.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		} else {

			if (bizOrderTaskpackagePaymentSummary.getStoreId() != null
					&& bizOrderTaskpackagePaymentSummary.getStoreId() == 1) {

				bizOrderTaskpackagePaymentSummary.setStoreId(null);
			}
		}
		model.addAttribute("bizOrderTaskpackagePaymentSummary", bizOrderTaskpackagePaymentSummary);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		return "modules/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummaryList";
	}

	@RequiresPermissions("ordertaskpackagepaymentsummary:bizOrderTaskpackagePaymentSummary:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizOrderTaskpackagePaymentSummary bizOrderTaskpackagePaymentSummary, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (null == bizOrderTaskpackagePaymentSummary.getEnginDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					bizOrderTaskpackagePaymentSummary.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackagePaymentSummary.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackagePaymentSummary.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackagePaymentSummary.getEnginDepartId());
			bizOrderTaskpackagePaymentSummary.setEnginDepartIds(list);
		}
		if (UserUtils.getUser().getStoreId() != null) {

			bizOrderTaskpackagePaymentSummary.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		} else {

			if (bizOrderTaskpackagePaymentSummary.getStoreId() != null
					&& bizOrderTaskpackagePaymentSummary.getStoreId() == 1) {

				bizOrderTaskpackagePaymentSummary.setStoreId(null);
			}
		}


		if (StringUtils.isBlank(bizOrderTaskpackagePaymentSummary.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackagePaymentSummary.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackagePaymentSummary.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		Page<BizOrderTaskpackagePaymentSummary> page = bizOrderTaskpackagePaymentSummaryService.findPage(
				new Page<BizOrderTaskpackagePaymentSummary>(request, response), bizOrderTaskpackagePaymentSummary);
		model.addAttribute("page", page);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("bizOrderTaskpackagePaymentSummary", bizOrderTaskpackagePaymentSummary);
		return "modules/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummaryList";
	}

	@RequiresPermissions("ordertaskpackagepaymentsummary:bizOrderTaskpackagePaymentSummary:view")
	@RequestMapping(value = "form")
	public String form(BizOrderTaskpackagePaymentSummary bizOrderTaskpackagePaymentSummary, Model model) {
		model.addAttribute("bizOrderTaskpackagePaymentSummary", bizOrderTaskpackagePaymentSummary);
		return "modules/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummaryForm";
	}

	@RequiresPermissions("ordertaskpackagepaymentsummary:bizOrderTaskpackagePaymentSummary:edit")
	@RequestMapping(value = "save")
	public String save(BizOrderTaskpackagePaymentSummary bizOrderTaskpackagePaymentSummary, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizOrderTaskpackagePaymentSummary)) {
			return form(bizOrderTaskpackagePaymentSummary, model);
		}
		bizOrderTaskpackagePaymentSummaryService.save(bizOrderTaskpackagePaymentSummary);
		addMessage(redirectAttributes, "保存付款单批次成功");
		return "redirect:" + Global.getAdminPath()
				+ "/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/?repage";
	}

	@RequiresPermissions("ordertaskpackagepaymentsummary:bizOrderTaskpackagePaymentSummary:edit")
	@RequestMapping(value = "delete")
	public String delete(BizOrderTaskpackagePaymentSummary bizOrderTaskpackagePaymentSummary,
			RedirectAttributes redirectAttributes) {
		bizOrderTaskpackagePaymentSummaryService.delete(bizOrderTaskpackagePaymentSummary);
		addMessage(redirectAttributes, "删除付款单批次成功");
		return "redirect:" + Global.getAdminPath()
				+ "/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/?repage";
	}


	@RequestMapping(value = "checkPaymentByIds")
	public @ResponseBody String checkPaymentByIds(String ids) {
		String result = "0";
		List<BizOrderTaskpackagePaymentVo> list = bizOrderTaskpackagePaymentService.checkPaymentByIds(ids);
		if (list != null && list.size() > 0) {
			result = "1";
		}
		return result;
	}

	@RequiresPermissions("ordertaskpackagepaymentsummary:bizOrderTaskpackagePaymentSummary:edit")
	@RequestMapping(value = "guaranteeOrderTaskpackagePaymentSummary")
	public String guaranteeOrderTaskpackagePaymentSummary(String paymentIds, RedirectAttributes redirectAttributes) {
		bizOrderTaskpackagePaymentSummaryService.saveSummary(paymentIds.split(","));
		addMessage(redirectAttributes, "付款单生成批次成功");
		return "redirect:" + Global.getAdminPath() + "/ordertaskpackagepayment/bizOrderTaskpackagePayment/list";
	}


	@RequestMapping(value = "exportProjectExcel")
	public void exportProjectExcel(Integer id, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		ServletOutputStream ouputStream = null;
		List<PaymentDetailForExcel> list = bizOrderTaskpackagePaymentDetailVoService.findPaymentDatailsBySummaryId(id);

		HSSFWorkbook projectExcel = ExportProjectExcel.exportProject(list);
		try {
			response.setContentType("application/binary;charset=utf-8");
			String headerStr = new String(("产业工人结算汇总表-大美装饰管理平台-工程" + sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");
			response.setHeader("Content-disposition", "attachment; filename=" + headerStr + ".xls");
			ouputStream = response.getOutputStream();
			projectExcel.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}


	@RequestMapping(value = "splitAndFinance")
	public String splitAndFinance(Integer id, Model model) {
		DecimalFormat df = new DecimalFormat("#.00");
		double totalMoney = 0;
		BizOrderTaskpackagePaymentSummary summary = bizOrderTaskpackagePaymentSummaryService.get(id);

		List<BizOrderTaskpackagePaymentDetailSplitVo> splits = bizOrderTaskpackagePaymentDetailSplitService
				.findPaymentDetailSplitBySummaryId(id);
		List<Integer> employeeIds = new ArrayList<Integer>();

		List<BizOrderTaskpackagePaymentDetail> details = bizOrderTaskpackagePaymentDetailService
				.queryPaymentDetailBySummaryId(id);
		for (BizOrderTaskpackagePaymentDetail detail : details) {
			totalMoney = totalMoney + detail.getAmount();
			employeeIds.add(detail.getEmployeeId());
		}
		Set set = new HashSet(employeeIds);

		totalMoney = Double.parseDouble(df.format(totalMoney));
		model.addAttribute("summary", summary);
		model.addAttribute("totalMoney", totalMoney);

		model.addAttribute("size", set.size());
		model.addAttribute("splits", splits);
		return "modules/ordertaskpackagepaymentsummary/splitAndFinance";
	}

	@RequestMapping(value = "splitCheck")
	public @ResponseBody String splitCheck(String a) {
		DecimalFormat df = new DecimalFormat("#.00");
		Map<Integer, Double> map = new HashMap<Integer, Double>();
		Map<Integer, Double> map1 = new HashMap<Integer, Double>();
		String[] split = a.trim().split(",");
		for (int i = 0; i < split.length; i++) {
			String[] split2 = split[i].trim().split("-");
			if (map.get(Integer.parseInt(split2[0].trim())) == null) {
				BizOrderTaskpackagePaymentDetail paymentDetail = bizOrderTaskpackagePaymentDetailService
						.get(Integer.parseInt(split2[0].trim()));
				map.put(Integer.parseInt(split2[0].trim()), paymentDetail.getAmount());
			}
			if (map1.get(Integer.parseInt(split2[0].trim())) == null) {
				map1.put(Integer.parseInt(split2[0].trim()), Double.parseDouble(split2[1].trim()));
			} else {
				map1.put(Integer.parseInt(split2[0].trim()), map1.get(Integer.parseInt(split2[0].trim())).doubleValue()
						+ Double.parseDouble(split2[1].trim()));
			}
		}
		boolean flag = true;
		Set<Integer> keySet = map.keySet();
		for (Integer key : keySet) {
			Double money = map.get(key);
			if (money == Double.parseDouble(df.format(map1.get(key)))) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}
		if (flag) {
			return "1";
		} else {
			return "0";
		}
	}

	@RequestMapping(value = "splitSave")
	public String splitSave(int[] splitId, double[] splitMoney, Integer splitsummaryId, HttpServletRequest request,
			HttpServletResponse response) {

		for (int i = 0; i < splitId.length; i++) {
			bizOrderTaskpackagePaymentDetailSplitService.updateDetailSplitById(splitId[i], splitMoney[i]);
		}
		return "redirect:" + Global.getAdminPath()
				+ "/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/splitAndFinance?id="
				+ splitsummaryId;
	}

	@RequestMapping(value = "splitSummary")

	public @ResponseBody Map<String, Object> splitSummary(Integer id) {
		return bizOrderTaskpackagePaymentDetailSplitService.insertPaymentDetailSplit(id);
	}


	@RequestMapping(value = "exportFinanceExcel")
	public void exportFinanceExcel(Integer id, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		ServletOutputStream ouputStream = null;



		List<PaymentDetailSplitForExcel> list = new ArrayList<PaymentDetailSplitForExcel>();


		List<BizOrderTaskpackagePaymentDetailSplitVo> splits = bizOrderTaskpackagePaymentDetailSplitService
				.findPaymentDetailSplitBySummaryId(id);

		for (BizOrderTaskpackagePaymentDetailSplitVo split : splits) {
			PaymentDetailSplitForExcel splitForExcel = new PaymentDetailSplitForExcel();

			String paymentCode = split.getPaymentCode();
			OrderInformation orderInformation = bizOrderTaskpackagePaymentSummaryService
					.queryOrderByPaymentCode(paymentCode);
			Integer taskpackageId = orderInformation.getTaskpackageId();

			String packageType = bizTaskPackageTypeService.findTypeByTaskpackageId(taskpackageId);
			splitForExcel.setCustomerName(orderInformation.getCustomerName());
			splitForExcel.setCustomerPhone(orderInformation.getCustomerPhone());
			splitForExcel.setCustomerAddress(orderInformation.getCustomerAddress());
			splitForExcel.setContractArea(orderInformation.getContractArea());
			splitForExcel.setItemManager(orderInformation.getManagerName());
			splitForExcel.setWorkerName(split.getRelatedName());
			splitForExcel.setPackageType(packageType);
			splitForExcel.setOrderNumber(orderInformation.getOrderNumber());
			if ("0".equals(orderInformation.getPaymentType())) {
				splitForExcel.setAdvancePayment(split.getPayAmountSplit());
			} else {
				splitForExcel.setRestPayment(split.getPayAmountSplit());
			}
			list.add(splitForExcel);
		}

		HSSFWorkbook financeExcel = ExportFinanceExcel.exportFinance(list);
		try {
			response.setContentType("application/binary;charset=utf-8");
			String headerStr = new String(("产业工人结算汇总表-大美装饰管理平台-财务" + sf.format(new Date())).getBytes("utf-8"),
					"ISO8859-1");
			response.setHeader("Content-disposition", "attachment; filename=" + headerStr + ".xls");
			ouputStream = response.getOutputStream();
			financeExcel.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}


	}

	@RequiresPermissions("ordertaskpackagepaymentsummary:bizOrderTaskpackagePaymentSummary:edit")
	@RequestMapping(value = "approvePass")
	public String approvePass(Integer id, RedirectAttributes redirectAttributes) {
		BizOrderTaskpackagePaymentSummary summary = bizOrderTaskpackagePaymentSummaryService.get(id);
		if (summary.getStatus().equals(ConstantUtils.SUMMARY_STATUS_20)) {
			addMessage(redirectAttributes, "批次已审核通过！");
		} else if (summary.getStatus().equals(ConstantUtils.SUMMARY_STATUS_90)) {
			addMessage(redirectAttributes, "批次已作废！");
		} else {
			bizOrderTaskpackagePaymentSummaryService.aprovePass(id);
			addMessage(redirectAttributes, "批次审核通过成功");
		}
		return "redirect:" + Global.getAdminPath()
				+ "/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/list";
	}



	@RequiresPermissions("ordertaskpackagepaymentsummary:bizOrderTaskpackagePaymentSummary:edit")
	@RequestMapping(value = "summaryAbolish")
	public @ResponseBody String summaryAbolish(Integer id, String cancleReason, RedirectAttributes redirectAttributes) {
		String result = null;
		try {
			result = bizOrderTaskpackagePaymentSummaryService.summaryAbolish(id, cancleReason);
		} catch (Exception e) {
			result = "error";
			e.printStackTrace();
			throw new RuntimeException();
		}
		return result;


	}

	@RequiresPermissions("ordertaskpackagepaymentsummary:bizOrderTaskpackagePaymentSummary:view")
	@RequestMapping(value = "summaryList")
	public String summaryList(BizOrderTaskpackagePaymentSummary bizOrderTaskpackagePaymentSummary, Model model) {
		User user = UserUtils.getUser();

		if (bizOrderTaskpackagePaymentSummary.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizOrderTaskpackagePaymentSummary.setStoreId(null);
			} else {
				bizOrderTaskpackagePaymentSummary.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}


		if (StringUtils.isBlank(bizOrderTaskpackagePaymentSummary.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackagePaymentSummary.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackagePaymentSummary.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}


		if (bizOrderTaskpackagePaymentSummary.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizOrderTaskpackagePaymentSummary.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackagePaymentSummary.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackagePaymentSummary.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackagePaymentSummary.getEnginDepartId());
			bizOrderTaskpackagePaymentSummary.setEnginDepartIds(list);
		}

		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		return "modules/ordertaskpackagepaymentsummary/orderTaskpackagePaymentSummaryList";
	}

	@RequiresPermissions("ordertaskpackagepaymentsummary:bizOrderTaskpackagePaymentSummary:view")
	@RequestMapping(value = "summaryLoadList")
	public String summaryLoadList(BizOrderTaskpackagePaymentSummary bizOrderTaskpackagePaymentSummary,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();

		if (bizOrderTaskpackagePaymentSummary.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizOrderTaskpackagePaymentSummary.setStoreId(null);
			} else {
				bizOrderTaskpackagePaymentSummary.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}


		if (StringUtils.isBlank(bizOrderTaskpackagePaymentSummary.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackagePaymentSummary.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackagePaymentSummary.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}


		if (bizOrderTaskpackagePaymentSummary.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizOrderTaskpackagePaymentSummary.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackagePaymentSummary.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackagePaymentSummary.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackagePaymentSummary.getEnginDepartId());
			bizOrderTaskpackagePaymentSummary.setEnginDepartIds(list);
		}

		Page<BizOrderTaskpackagePaymentSummary> page = bizOrderTaskpackagePaymentSummaryService.findSummaryPage(
				new Page<BizOrderTaskpackagePaymentSummary>(request, response), bizOrderTaskpackagePaymentSummary);
		model.addAttribute("page", page);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("bizOrderTaskpackagePaymentSummary", bizOrderTaskpackagePaymentSummary);
		return "modules/ordertaskpackagepaymentsummary/orderTaskpackagePaymentSummaryList";
	}

	@RequiresPermissions("ordertaskpackagepaymentsummary:bizOrderTaskpackagePaymentSummary:view")
	@RequestMapping(value = { "paymentSummaryList", "" })
	public String paymentSummaryList(BizOrderTaskpackagePaymentSummary bizOrderTaskpackagePaymentSummary, Model model) {

		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		model.addAttribute("bizOrderTaskpackagePaymentSummary", bizOrderTaskpackagePaymentSummary);
		return "modules/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummaryAllList";
	}

	@RequiresPermissions("ordertaskpackagepaymentsummary:bizOrderTaskpackagePaymentSummary:view")
	@RequestMapping(value = { "paymentSummaryLoadList", "" })
	public String paymentSummaryLoadList(BizOrderTaskpackagePaymentSummary bizOrderTaskpackagePaymentSummary,
			HttpServletRequest request, HttpServletResponse response, Model model) {

		if (bizOrderTaskpackagePaymentSummary.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizOrderTaskpackagePaymentSummary.setStoreId(null);
			} else {
				bizOrderTaskpackagePaymentSummary.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		Page<BizOrderTaskpackagePaymentSummary> page = bizOrderTaskpackagePaymentSummaryService.findPaymentSummaryList(
				new Page<BizOrderTaskpackagePaymentSummary>(request, response), bizOrderTaskpackagePaymentSummary);
		model.addAttribute("page", page);
		model.addAttribute("bizOrderTaskpackagePaymentSummary", bizOrderTaskpackagePaymentSummary);
		return "modules/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummaryAllList";
	}

	@RequiresPermissions("ordertaskpackagepaymentsummary:bizOrderTaskpackagePaymentSummary:view")
	@RequestMapping(value = { "summaryAllList", "" })
	public String summaryAllList(BizOrderTaskpackagePaymentSummary bizOrderTaskpackagePaymentSummary, Model model) {

		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		model.addAttribute("bizOrderTaskpackagePaymentSummary", bizOrderTaskpackagePaymentSummary);
		return "modules/ordertaskpackagepaymentsummary/bizOrderTaskpackageSummaryAllList";
	}

	@RequiresPermissions("ordertaskpackagepaymentsummary:bizOrderTaskpackagePaymentSummary:view")
	@RequestMapping(value = { "summaryAllLoadList", "" })
	public String summaryAllLoadList(BizOrderTaskpackagePaymentSummary bizOrderTaskpackagePaymentSummary,
			HttpServletRequest request, HttpServletResponse response, Model model) {

		if (bizOrderTaskpackagePaymentSummary.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizOrderTaskpackagePaymentSummary.setStoreId(null);
			} else {
				bizOrderTaskpackagePaymentSummary.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		Page<BizOrderTaskpackagePaymentSummary> page = bizOrderTaskpackagePaymentSummaryService
				.findPaymentSummaryAllList(new Page<BizOrderTaskpackagePaymentSummary>(request, response),
						bizOrderTaskpackagePaymentSummary);
		model.addAttribute("page", page);
		model.addAttribute("bizOrderTaskpackagePaymentSummary", bizOrderTaskpackagePaymentSummary);
		return "modules/ordertaskpackagepaymentsummary/bizOrderTaskpackageSummaryAllList";
	}
}