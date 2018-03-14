package cn.damei.web.modules;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import cn.damei.common.constantUtils.BizOrderReportConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.web.mobile.home.JobSiteController;
import cn.damei.entity.modules.Order;
import cn.damei.service.modules.OrderService;
import cn.damei.entity.modules.BizOrderReport;
import cn.damei.entity.modules.OrderReportLogEntity;
import cn.damei.service.modules.BizOrderReportService;
import cn.damei.service.modules.QuarzUpdateOrderReportStatus;
import cn.damei.entity.modules.BizOrderReportRelatedOrder;
import cn.damei.service.modules.BizOrderReportRelatedOrderService;
import cn.damei.common.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/orderReport/bizOrderReport")
public class BizOrderReportController extends BaseController {

	@Autowired
	private BizOrderReportService bizOrderReportService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private BizOrderReportRelatedOrderService bizOrderReportRelatedOrderService;

	@ModelAttribute
	public BizOrderReport get(@RequestParam(required = false) Integer id) {
		BizOrderReport entity = null;
		if (null != id && !"".equals(String.valueOf(id))) {
			entity = bizOrderReportService.get(id);
		}
		if (entity == null) {
			entity = new BizOrderReport();
		}
		return entity;
	}

	@RequiresPermissions("orderReport:orderReport:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizOrderReport bizOrderReport, HttpServletRequest request, HttpServletResponse response, Model model) {
		// 过滤门店
		if (bizOrderReport.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizOrderReport.setStoreId(null);
			} else {
				bizOrderReport.setStoreId(Integer.valueOf(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		Page<BizOrderReport> page = bizOrderReportService.findPage(new Page<BizOrderReport>(request, response), bizOrderReport);
		model.addAttribute("page", page);
		model.addAttribute("bizOrderReport", bizOrderReport);
		return "modules/orderreport/bizOrderReportList";
	}

	@RequiresPermissions("orderReport:orderReport:view")
	@RequestMapping(value = "findByParam")
	public String findByParam(BizOrderReport bizOrderReport, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (bizOrderReport.getReportStatus() != null && !bizOrderReport.getReportStatus().equals("")) {
			String[] status = bizOrderReport.getReportStatus().split(",");
			bizOrderReport.setReportStatusList(Arrays.asList(status));
		}

		// 过滤门店
		if (bizOrderReport.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizOrderReport.setStoreId(null);
			} else {
				bizOrderReport.setStoreId(Integer.valueOf(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		Page<BizOrderReport> page = bizOrderReportService.findByParam(new Page<BizOrderReport>(request, response), bizOrderReport);
		model.addAttribute("page", page);
		model.addAttribute("start", bizOrderReport.getStart());
		model.addAttribute("end", bizOrderReport.getEnd());
		model.addAttribute("bizOrderReport", bizOrderReport);
		return "modules/orderreport/bizOrderReportList2";
	}

	@RequiresPermissions("orderReport:orderReport:view")
	@RequestMapping(value = "form")
	public String form(BizOrderReport bizOrderReport, Model model) {
		model.addAttribute("nowDate", new Date());
		return "modules/orderreport/bizOrderReportForm";
	}

	@RequiresPermissions("orderReport:orderReport:edit")
	@RequestMapping(value = "save")
	public String save(BizOrderReport bizOrderReport, Model model, RedirectAttributes redirectAttributes) {
		bizOrderReport.setReportStatus(BizOrderReportConstantUtil.REPORT_STATUS_10);
		bizOrderReport.setReportSourceType(BizOrderReportConstantUtil.REPORT_SOURCE_TYPE_4);
		bizOrderReport.setReportDatetime(new Date());
		bizOrderReportService.save(bizOrderReport);
		return "redirect:" + Global.getAdminPath() + "/orderReport/bizOrderReport/list";
	}

	/**
	 * 已进店未签单
	 *
	 * @param id
	 *            返单上报Id
	 * @param instoreDatetime
	 *            进店时间
	 * @param instoreRemarks
	 *            进店备注
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("orderReport:orderReport:edit")
	@RequestMapping(value = "jdNoWriteOrder")
	public @ResponseBody String jdNoWriteOrder(Integer id, Date instoreDatetime, String instoreRemarks, RedirectAttributes redirectAttributes) {
		String result = "0";
		try {
			BizOrderReport bizOrderReport = bizOrderReportService.get(id);
			bizOrderReport.setReportStatus("30");
			bizOrderReport.setInstoreDatetime(instoreDatetime);
			bizOrderReport.setInstoreRemarks(instoreRemarks);
			bizOrderReportService.save(bizOrderReport);
		} catch (Exception e) {
			result = "1";
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 已进店已签单
	 *
	 * @param id
	 *            返单上报Id
	 *            <p>
	 *            关联订单号
	 * @param instoreDatetime
	 *            进店时间
	 * @param signBillDatetime
	 *            签单时间
	 * @param signBillRemarks
	 *            签单备注
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("orderReport:orderReport:edit")
	@RequestMapping(value = "jdWriteOrder")
	public @ResponseBody String jdWriteOrder(Integer id, String relOrderNums, Date instoreDatetime, Date signBillDatetime, String signBillRemarks, RedirectAttributes redirectAttributes) {
		String result = "0";
		try {

			BizOrderReport bizOrderReport = bizOrderReportService.get(id);
			bizOrderReport.setReportStatus("40");
			bizOrderReport.setInstoreDatetime(instoreDatetime);
			bizOrderReport.setOrderNumber(relOrderNums);
			bizOrderReport.setSignBillDatetime(signBillDatetime);
			bizOrderReport.setSignBillRemarks(signBillRemarks);
			bizOrderReportService.save(bizOrderReport);
			if (relOrderNums != null) {
				String[] orderNumArr = relOrderNums.split(",");
				for (String orderNum : orderNumArr) {
					BizOrderReportRelatedOrder orderReportRelatedOrder = new BizOrderReportRelatedOrder();
					orderReportRelatedOrder.setOrderReportId(id);
					Integer orderId = orderService.getIdByOrderNumber(orderNum);
					if (orderId != null) {
						orderReportRelatedOrder.setOrderId(orderId);
					}
					orderReportRelatedOrder.setOrderNumber(orderNum);
					bizOrderReportRelatedOrderService.save(orderReportRelatedOrder);
				}
			}
		} catch (Exception e) {
			result = "1";
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 已签单
	 *
	 * @param id
	 *            返单上报Id
	 * @param relOrderNums
	 *            关联订单号
	 * @param signBillDatetime
	 *            签单时间
	 * @param signBillRemarks
	 *            签单备注
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("orderReport:orderReport:edit")
	@RequestMapping(value = "writeOrder")
	public @ResponseBody String writeOrder(Integer id, String relOrderNums, Date signBillDatetime, String signBillRemarks, RedirectAttributes redirectAttributes) {
		String result = "0";
		try {
			BizOrderReport bizOrderReport = bizOrderReportService.get(id);
			bizOrderReport.setReportStatus("50");
			bizOrderReport.setSignBillDatetime(signBillDatetime);
			bizOrderReport.setSignBillRemarks(signBillRemarks);
			bizOrderReportService.save(bizOrderReport);
			if (relOrderNums != null) {
				String[] orderNumArr = relOrderNums.split(",");
				for (String orderNum : orderNumArr) {
					BizOrderReportRelatedOrder orderReportRelatedOrder = new BizOrderReportRelatedOrder();
					orderReportRelatedOrder.setOrderReportId(id);
					Integer orderId = orderService.getIdByOrderNumber(orderNum);
					if (orderId != null) {
						orderReportRelatedOrder.setOrderId(orderId);
					}
					orderReportRelatedOrder.setOrderNumber(orderNum);
					bizOrderReportRelatedOrderService.save(orderReportRelatedOrder);
				}
			}
		} catch (Exception e) {
			result = "1";
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 返单详情流程日志
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("orderReport:orderReport:view")
	@RequestMapping(value = "orderReportView")
	public String orderReportView(Integer id, Model model) {

		BizOrderReport report = bizOrderReportService.get(id);
		if (null != report && report.getDetailAddress().split("-").length == 0) {
			report.setDetailAddress("");

		}
		model.addAttribute("orderReport", report);
		List<OrderReportLogEntity> logList1 = bizOrderReportService.findLogList1(id);
		List<OrderReportLogEntity> logList2 = bizOrderReportService.findLogList2(id);
		List<OrderReportLogEntity> logList3 = bizOrderReportService.findLogList3(id);
		List<OrderReportLogEntity> logList4 = bizOrderReportService.findLogList4(id);
		List<OrderReportLogEntity> logList5 = bizOrderReportService.findLogList5(id);
		List<OrderReportLogEntity> logList6 = bizOrderReportService.findLogList6(id);
		List<OrderReportLogEntity> logList7 = bizOrderReportService.findLogList7(id);

		if (null != logList1.get(0)) {
			model.addAttribute("logList1", logList1);

		}
		if (null != logList2.get(0)) {
			model.addAttribute("logList2", logList2);

		}
		if (null != logList3.get(0)) {
			model.addAttribute("logList3", logList3);

		}
		if (null != logList4.get(0)) {
			model.addAttribute("logList4", logList4);

		}
		if (null != logList5.get(0)) {
			model.addAttribute("logList5", logList5);

		}
		if (null != logList6.get(0)) {
			model.addAttribute("logList6", logList6);

		}
		if (null != logList7.get(0)) {
			model.addAttribute("logList7", logList7);

		}

		return "modules/orderreport/bizOrderReportView1";
	}

	/**
	 * 检查返单客户手机号是否存在
	 *
	 * @param customerPhone
	 * @return
	 */
	@RequiresPermissions("orderReport:orderReport:view")
	@RequestMapping(value = "checkCustomerPhone")
	public @ResponseBody String checkCustomerPhone(String customerPhone) {
		String result = null;
		Integer count = bizOrderReportService.getBizOrderReportByCustomerPhone(customerPhone);
		if (null == count || count == 0) {// 客户手机号不存在
			result = "0";
		} else {// 已存在
			result = "1";
		}
		return result;
	}

	/**
	 * 通过返单id(commonReturnOrderId 和orderNumber 去查询是否订单存在,及插入关联订单合同)
	 */
	@RequiresPermissions("orderReport:orderReport:view")
	@RequestMapping(value = "replenish-related-order")
	public @ResponseBody Order replenishRelatedOrder(String returnOrderId, String orderNumber) {

		if (null != orderNumber && !orderNumber.trim().equals("")) {
			Integer count = orderService.getOrderNumberById(orderNumber);
			if (null != count && count > 0) {
				// 订单编号有效
				Order order = new Order();
				order.setOrderNumber(orderNumber);
				List<Order> list = orderService.findList(order);
				//
				if (list.size() > 0) {
					Order orderInfo = list.get(0);
					orderInfo.setOrderReportId(JobSiteController.isNum(returnOrderId) ? Integer.valueOf(returnOrderId) : 0);

					if (0 == orderInfo.getOrderReportId()) {

						return null;
					} else {
						/*
						 * orderInfo.setRemarks("补签");
						 * orderService.batchInsertOrderReportRelatedInfo
						 * (orderInfo);
						 */
						// 插入补签日志

						/**
						 * 查询是否关联过
						 */
						Map<String, String> map = new HashMap<>();
						map.put("reportId", String.valueOf(orderInfo.getOrderReportId()));
						map.put("orderNumber", orderNumber);
						Integer isExist = bizOrderReportService.findOrderNumberCountIsExist(map);
						if (null != isExist && isExist > 0) {
							return null;

						} else {

							return orderInfo;
						}

					}

				} else {
					return null;
				}

			} else {

				return null;
			}

		} else {

			return null;
		}

	}

	/**
	 * 更新补签合同remarks 批量关联合同
	 *
	 * @param orderReport
	 * @return
	 */
	@RequiresPermissions("orderReport:orderReport:view")
	@RequestMapping(value = "updateReturnOrderReport")
	public @ResponseBody String updateReturnOrderReport(BizOrderReport orderReport, String[] contractOrderIds, String[] contractOrderNumbers) {

		List<BizOrderReport> orderReportList = new ArrayList<BizOrderReport>();
		StringBuffer sb = new StringBuffer();
		if (null != contractOrderIds && contractOrderIds.length > 0 && null != contractOrderNumbers && contractOrderNumbers.length > 0) {

			for (int v = 0; v < contractOrderIds.length; v++) {
				BizOrderReport orderReport1 = new BizOrderReport();
				orderReport1.setId(orderReport.getId());
				orderReport1.setOrderId(contractOrderIds[v]);
				orderReport1.setOrderNumber(contractOrderNumbers[v]);
				orderReportList.add(orderReport1);
				sb.append(v == contractOrderNumbers.length - 1 ? contractOrderNumbers[v] : contractOrderNumbers[v] + ",");
			}

		}
		bizOrderReportService.updateReturnOrder(orderReport, orderReportList, sb.toString());

		return "0";
	}

	/**
	 * // * 查询备注 // *0 // * @param returnOrderId // * @return //
	 */
	// @RequiresPermissions("orderReport:orderReport:view")
	// @RequestMapping(value = "findReturnOrderRemarks")
	// public @ResponseBody
	// String findReturnOrderRemarks(String returnOrderId) {
	//
	//
	// return bizOrderReportService.findRemarksByReturnOrderId(returnOrderId);
	// }

	/**
	 * 查询客户列表 转派
	 *
	 * @return
	 */
	@RequiresPermissions("orderReport:orderReport:view")
	@RequestMapping(value = "findServiceList")
	public @ResponseBody List<BizOrderReport> findServiceList(String serviceName) {
		Map map = new HashMap<String, String>();
		map.put("serviceName", serviceName);

		return bizOrderReportService.findServiceList(map);

	}

	/**
	 * 更新返单记录,保存转派客服信息,更新返单状态
	 *
	 * @return
	 */
	@RequiresPermissions("orderReport:orderReport:view")
	@RequestMapping(value = "saveTransferServiceInfo")
	public @ResponseBody String saveTransferServiceInfo(BizOrderReport orderReport) {
		bizOrderReportService.saveTransferServiceInfo(orderReport);

		return "0";

	}

	@Autowired
	private QuarzUpdateOrderReportStatus quarzUpdateOrderReportStatus;

	/**
	 * 更新返单记录,保存转派客服信息,更新返单状态
	 *
	 * @return
	 */
	@RequiresPermissions("orderReport:orderReport:view")
	@RequestMapping(value = "quarz")
	public @ResponseBody String quarz() {
		quarzUpdateOrderReportStatus.execute();

		return "0";

	}

	@RequestMapping(value = "exportORderReportDetailToExcel")
	public void exportInspectorFineMoneyDetailToExcel(HttpServletResponse response, BizOrderReport orderReport, HttpServletRequest request) {

		String now = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		HSSFWorkbook excel = bizOrderReportService.exportExcel(orderReport);

		ServletOutputStream out = null;
		try {

			response.setContentType("application/binary;charset=UTF-8");
			String excelHead = new String(("返单详情-" + now).getBytes(), "ISO8859-1");

			response.setHeader("Content-disposition", "attachment; filename=" + excelHead + ".xls");
			out = response.getOutputStream();
			excel.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
}