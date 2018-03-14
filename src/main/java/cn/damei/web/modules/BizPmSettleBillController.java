/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.entity.modules.BizAssessRewardPunish;
import cn.damei.service.modules.BizAssessRewardPunishService;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.common.utils.UserUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizOrderMaterialsStandard;
import cn.damei.service.modules.BizMaterialsStandardReceiveDetailService;
import cn.damei.entity.modules.BizMaterialsStandardReceiveBill;
import cn.damei.entity.modules.PmMaterialsSettleInfo;
import cn.damei.service.modules.PmMaterialsSettleInfoService;
import cn.damei.entity.modules.BizPmGuaranteeMoneyCnfgSnap;
import cn.damei.service.modules.BizPmGuaranteeMoneyCnfgSnapService;
import cn.damei.entity.modules.BizMaterialSelfbuyVo;
import cn.damei.entity.modules.BizPmSettleBill;
import cn.damei.entity.modules.InspectorPunish;
import cn.damei.entity.modules.Ownpay;
import cn.damei.service.modules.BizPmSettleBillService;
import cn.damei.entity.modules.BizPmStarCommissionCnfgSnap;
import cn.damei.service.modules.BizGuaranteeMoneyBalanceService;
import cn.damei.service.modules.InspectorConfirmService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 结算单Controller
 * 
 * @author qww
 * @version 2016-12-26
 */
@Controller
@RequestMapping(value = "${adminPath}/pmsettlebill/bizPmSettleBill")
public class BizPmSettleBillController extends BaseController {

	@Autowired
	private BizMaterialsStandardReceiveDetailService bizMaterialsStandardReceiveDetailService;
	@Autowired
	private BizPmSettleBillService bizPmSettleBillService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private PmMaterialsSettleInfoService pmMaterialsSettleInfoService;
	@Autowired
	private InspectorConfirmService inspectorConfirmService;
	@Autowired
	private BizPmGuaranteeMoneyCnfgSnapService bizPmGuaranteeMoneyCnfgSnapService;
	@Autowired
	private BizGuaranteeMoneyBalanceService bizGuaranteeMoneyBalanceService;
	@Autowired
	private BizAssessRewardPunishService bizAssessRewardPunishService;

	@ModelAttribute
	public BizPmSettleBill get(@RequestParam(required = false) Integer id) {
		BizPmSettleBill entity = null;
		if (id != null) {
			entity = bizPmSettleBillService.get(id);
		}
		if (entity == null) {
			entity = new BizPmSettleBill();
		}
		return entity;
	}

	/**
	 * 项目经理-生成月度工程结算单
	 * 
	 * @param bizPmSettleBill
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pmsettlebill:bizPmSettleBill:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizPmSettleBill bizPmSettleBill, Model model) {
		// 过滤门店
		if (bizPmSettleBill.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizPmSettleBill.setStoreId(null);
			} else {
				bizPmSettleBill.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		model.addAttribute("bizPmSettleBill", bizPmSettleBill);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		return "modules/pmsettlebill/bizPmSettleBillList";
	}

	/**
	 * 项目经理-生成月度工程结算单
	 * 
	 * @param bizPmSettleBill
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pmsettlebill:bizPmSettleBill:view")
	@RequestMapping(value = { "loadList", "" })
	public String loadList(BizPmSettleBill bizPmSettleBill, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 过滤门店
		if (bizPmSettleBill.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizPmSettleBill.setStoreId(null);
			} else {
				bizPmSettleBill.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		// 区域
		if (bizPmSettleBill.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizPmSettleBill.setEnginDepartIds(list);
				} else {
					bizPmSettleBill.setEnginDepartIds(null);
				}
			} else {
				bizPmSettleBill.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizPmSettleBill.getEnginDepartId());
			bizPmSettleBill.setEnginDepartIds(list);
		}

		bizPmSettleBill.setStatus(ConstantUtils.PM_SETTLE_STATUS_30);
		bizPmSettleBill.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
		//2017-12-01  去掉分页  添加全选功能
		/*Page<BizPmSettleBill> page = bizPmSettleBillService
				.findPmSettleBillList(new Page<BizPmSettleBill>(request, response), bizPmSettleBill);
		model.addAttribute("page", page);*/
		List<BizPmSettleBill> list = bizPmSettleBillService.findPmSettleBillList(bizPmSettleBill);
		model.addAttribute("list", list);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("bizPmSettleBill", bizPmSettleBill);
		return "modules/pmsettlebill/bizPmSettleBillList";
	}

	/**
	 * 质检员-生成月度工程结算单
	 * 
	 * @param bizPmSettleBill
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pmsettlebill:bizPmSettleBill:view")
	@RequestMapping(value = { "listPbc", "" })
	public String listPbc(BizPmSettleBill bizPmSettleBill, Model model) {
		// 过滤门店
		if (bizPmSettleBill.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizPmSettleBill.setStoreId(null);
			} else {
				bizPmSettleBill.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("bizPmSettleBill", bizPmSettleBill);
		return "modules/pmsettlebill/bizPmSettleBillListPbc";
	}

	/**
	 * 质检员-生成月度工程结算单
	 * 
	 * @param bizPmSettleBill
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pmsettlebill:bizPmSettleBill:view")
	@RequestMapping(value = { "loadListPbc", "" })
	public String loadListPbc(BizPmSettleBill bizPmSettleBill, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 过滤门店
		if (bizPmSettleBill.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizPmSettleBill.setStoreId(null);
			} else {
				bizPmSettleBill.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		// 区域
		if (bizPmSettleBill.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizPmSettleBill.setEnginDepartIds(list);
				} else {
					bizPmSettleBill.setEnginDepartIds(null);
				}
			} else {
				bizPmSettleBill.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizPmSettleBill.getEnginDepartId());
			bizPmSettleBill.setEnginDepartIds(list);
		}

		bizPmSettleBill.setStatus(ConstantUtils.PM_SETTLE_STATUS_30);
		bizPmSettleBill.setSettleRole(ConstantUtils.SETTLE_ROLE_2);
		Page<BizPmSettleBill> page = bizPmSettleBillService
				.findPmSettleBillListPbc(new Page<BizPmSettleBill>(request, response), bizPmSettleBill);
		model.addAttribute("page", page);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("bizPmSettleBill", bizPmSettleBill);
		return "modules/pmsettlebill/bizPmSettleBillListPbc";
	}

	/**
	 * 项目经理-订单月度工程结算单查询列表
	 * 
	 * @param bizPmSettleBill
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pmsettlebill:bizPmSettleBill:view")
	@RequestMapping(value = { "settleList", "" })
	public String settleList(BizPmSettleBill bizPmSettleBill, Model model) {
		// 过滤门店
		if (bizPmSettleBill.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizPmSettleBill.setStoreId(null);
			} else {
				bizPmSettleBill.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		return "modules/pmsettlebill/bizSettleBillList";
	}

	/**
	 * 项目经理-订单月度工程结算单查询列表
	 * 
	 * @param bizPmSettleBill
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pmsettlebill:bizPmSettleBill:view")
	@RequestMapping(value = { "loadSettleList", "" })
	public String loadSettleList(BizPmSettleBill bizPmSettleBill, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 过滤门店
		if (bizPmSettleBill.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizPmSettleBill.setStoreId(null);
			} else {
				bizPmSettleBill.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		// 区域
		if (bizPmSettleBill.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizPmSettleBill.setEnginDepartIds(list);
				} else {
					bizPmSettleBill.setEnginDepartIds(null);
				}
			} else {
				bizPmSettleBill.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizPmSettleBill.getEnginDepartId());
			bizPmSettleBill.setEnginDepartIds(list);
		}

		Page<BizPmSettleBill> page = bizPmSettleBillService
				.findSettleBillList(new Page<BizPmSettleBill>(request, response), bizPmSettleBill);
		model.addAttribute("page", page);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("bizPmSettleBill", bizPmSettleBill);
		return "modules/pmsettlebill/bizSettleBillList";
	}

	/**
	 * 质检员-订单月度工程结算单查询列表
	 * 
	 * @param bizPmSettleBill
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pmsettlebill:bizPmSettleBill:view")
	@RequestMapping(value = { "settleListPbc", "" })
	public String settleListPbc(BizPmSettleBill bizPmSettleBill, Model model) {
		// 过滤门店
		if (bizPmSettleBill.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizPmSettleBill.setStoreId(null);
			} else {
				bizPmSettleBill.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		return "modules/pmsettlebill/bizSettleBillListPbc";
	}

	/**
	 * 质检员-订单月度工程结算单查询列表
	 * 
	 * @param bizPmSettleBill
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pmsettlebill:bizPmSettleBill:view")
	@RequestMapping(value = { "loadSettleListPbc", "" })
	public String loadSettleListPbc(BizPmSettleBill bizPmSettleBill, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 过滤门店
		if (bizPmSettleBill.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizPmSettleBill.setStoreId(null);
			} else {
				bizPmSettleBill.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		// 区域
		if (bizPmSettleBill.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizPmSettleBill.setEnginDepartIds(list);
				} else {
					bizPmSettleBill.setEnginDepartIds(null);
				}
			} else {
				bizPmSettleBill.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizPmSettleBill.getEnginDepartId());
			bizPmSettleBill.setEnginDepartIds(list);
		}

		Page<BizPmSettleBill> page = bizPmSettleBillService
				.findSettleBillListPbc(new Page<BizPmSettleBill>(request, response), bizPmSettleBill);
		model.addAttribute("page", page);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("bizPmSettleBill", bizPmSettleBill);
		return "modules/pmsettlebill/bizSettleBillListPbc";
	}

	@RequiresPermissions("pmsettlebill:bizPmSettleBill:view")
	@RequestMapping(value = "form")
	public String form(BizPmSettleBill bizPmSettleBill, Model model) {
		model.addAttribute("bizPmSettleBill", bizPmSettleBill);
		return "modules/pmsettlebill/bizPmSettleBillForm";
	}

	@RequiresPermissions("pmsettlebill:bizPmSettleBill:edit")
	@RequestMapping(value = "save")
	public String save(BizPmSettleBill bizPmSettleBill, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizPmSettleBill)) {
			return form(bizPmSettleBill, model);
		}
		bizPmSettleBillService.save(bizPmSettleBill);
		addMessage(redirectAttributes, "保存结算单成功");
		return "redirect:" + Global.getAdminPath() + "/pmsettlebill/bizPmSettleBill/?repage";
	}

	@RequiresPermissions("pmsettlebill:bizPmSettleBill:edit")
	@RequestMapping(value = "delete")
	public String delete(BizPmSettleBill bizPmSettleBill, RedirectAttributes redirectAttributes) {
		bizPmSettleBillService.delete(bizPmSettleBill);
		addMessage(redirectAttributes, "删除结算单成功");
		return "redirect:" + Global.getAdminPath() + "/pmsettlebill/bizPmSettleBill/?repage";
	}

	/**
	 * 项目经理-生成月度工程结算单
	 * 
	 * @param bizPmSettleBill
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pmsettlebill:bizPmSettleBill:edit")
	@RequestMapping(value = "createSettleSummaryBill")
	public String createSettleSummaryBill(BizPmSettleBill bizPmSettleBill,String orderIds, RedirectAttributes redirectAttributes) {
		bizPmSettleBillService.createSettleSummaryBill(bizPmSettleBill.getStoreId(), bizPmSettleBill.getSettleMonth(),orderIds);
		addMessage(redirectAttributes, "生成月度工程结算成功");
		return "redirect:" + Global.getAdminPath() + "/pmsettlebill/bizPmSettleBill/loadList?storeId="
				+ bizPmSettleBill.getStoreId();
	}

	/**
	 * 质检员-生成月度工程结算单
	 * 
	 * @param bizPmSettleBill
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pmsettlebill:bizPmSettleBill:edit")
	@RequestMapping(value = "createSettleSummaryBillPbc")
	public String createSettleSummaryBillPbc(BizPmSettleBill bizPmSettleBill, RedirectAttributes redirectAttributes) {
		bizPmSettleBillService.createSettleSummaryBillPbc(bizPmSettleBill.getStoreId(),
				bizPmSettleBill.getSettleMonth());
		addMessage(redirectAttributes, "生成月度工程结算成功");
		return "redirect:" + Global.getAdminPath() + "/pmsettlebill/bizPmSettleBill/loadListPbc?storeId="
				+ bizPmSettleBill.getStoreId();
	}

	@RequestMapping(value = "queryCountByCondition", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> queryCountByCondition(Integer storeId, String settleRole) {
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		map.put("settleMonth", format.format(cal.getTime()));
		Integer count = bizPmSettleBillService.queryCountByCondition(storeId, format.format(cal.getTime()), settleRole);
		Integer billCount = bizPmSettleBillService.queryBillCountByCondition(storeId, settleRole);
		map.put("billCount", billCount);
		map.put("count", count);
		return map;
	}

	/**
	 * 项目经理-生成月度工程结算单查询列表Excel下载
	 * 
	 * @param bizPmSettleBill
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "exportExcel2")
	public void exportExcel2(BizPmSettleBill bizPmSettleBill, HttpServletResponse response) throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		// 区域
		if (bizPmSettleBill.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizPmSettleBill.setEnginDepartIds(list);
				} else {
					bizPmSettleBill.setEnginDepartIds(null);
				}
			} else {
				bizPmSettleBill.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizPmSettleBill.getEnginDepartId());
			bizPmSettleBill.setEnginDepartIds(list);
		}
		HSSFWorkbook excel = bizPmSettleBillService.exportExcel2(bizPmSettleBill);
		ServletOutputStream out = null;// 创建一个输出流对象
		try {
			response.setContentType("application/binary;charset=utf-8");
			String headerStr = new String(("月度工程结算明细" + sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");// headerString为中文时转码
			response.setHeader("Content-disposition", "attachment; filename=" + headerStr + ".xls");// filename是下载的xls的名
			out = response.getOutputStream();
			excel.write(out);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}

	/**
	 * 项目经理-订单月度工程结算单查询列表Excel下载
	 * 
	 * @param bizPmSettleBill
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "exportExcel")
	public void exportExcel(BizPmSettleBill bizPmSettleBill, HttpServletResponse response) throws Exception {
		// 区域
		if (bizPmSettleBill.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizPmSettleBill.setEnginDepartIds(list);
				} else {
					bizPmSettleBill.setEnginDepartIds(null);
				}
			} else {
				bizPmSettleBill.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizPmSettleBill.getEnginDepartId());
			bizPmSettleBill.setEnginDepartIds(list);
		}

		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		HSSFWorkbook excel = bizPmSettleBillService.exportExcel(bizPmSettleBill);
		ServletOutputStream out = null;// 创建一个输出流对象
		try {
			response.setContentType("application/binary;charset=utf-8");
			String headerStr = new String(("项目经理-订单月度工程结算单" + sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");// headerString为中文时转码
			response.setHeader("Content-disposition", "attachment; filename=" + headerStr + ".xls");// filename是下载的xls的名
			out = response.getOutputStream();
			excel.write(out);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 质检员-订单月度工程结算单查询列表Excel下载
	 * 
	 * @param bizPmSettleBill
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "exportExcelPbc")
	public void exportExcelPbc(BizPmSettleBill bizPmSettleBill, HttpServletResponse response) throws Exception {
		// 区域
		if (bizPmSettleBill.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizPmSettleBill.setEnginDepartIds(list);
				} else {
					bizPmSettleBill.setEnginDepartIds(null);
				}
			} else {
				bizPmSettleBill.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizPmSettleBill.getEnginDepartId());
			bizPmSettleBill.setEnginDepartIds(list);
		}

		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		HSSFWorkbook excel = bizPmSettleBillService.exportExcelPbc(bizPmSettleBill);
		ServletOutputStream out = null;// 创建一个输出流对象
		try {
			response.setContentType("application/binary;charset=utf-8");
			String headerStr = new String(("质检员-订单月度工程结算单" + sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");// headerString为中文时转码
			response.setHeader("Content-disposition", "attachment; filename=" + headerStr + ".xls");// filename是下载的xls的名
			out = response.getOutputStream();
			excel.write(out);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 标化辅料明细
	 * 
	 * @param billId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pmsettlebill:bizPmSettleBill:view")
	@RequestMapping(value = "materialsStandardDetails")
	public String materialsStandardDetails(Integer orderId, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<BizOrderMaterialsStandard> materialsStandardList = inspectorConfirmService
				.queryMaterialsStandardByOrderId(orderId);
		BizMaterialsStandardReceiveBill details3 = new BizMaterialsStandardReceiveBill();
		details3.setReceiveBillAmount(0.0);
		if (materialsStandardList != null && materialsStandardList.size() > 0) {
			for (BizOrderMaterialsStandard materialsStandard : materialsStandardList) {
				details3.setReceiveBillAmount(details3.getReceiveBillAmount() + materialsStandard.getMaterialsAmount());
			}
		}
		model.addAttribute("materialsStandardList", materialsStandardList);
		model.addAttribute("details3", details3);
		return "modules/pmsettlebill/materailsDetail";
	}

	/**
	 * 自主支配项
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pmsettlebill:bizPmSettleBill:view")
	@RequestMapping(value = { "ownpayAmount", "" })
	public String ownpayAmount(Integer id, Model model) {

		List<Ownpay> owypayList = bizPmSettleBillService.findOwnpayAmount(id);
		model.addAttribute("owypayList", owypayList);

		return "modules/pmsettlebill/owypayDetails";
	}

	/**
	 * 中期质检罚款
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pmsettlebill:bizPmSettleBill:view")
	@RequestMapping(value = { "midwayQcCheckPunishAmount", "" })
	public String midwayQcCheckPunishAmount(Integer orderId,Integer pmEmployeeId, Model model) {
		InspectorPunish inspectorPunish = new InspectorPunish();
		inspectorPunish.setOrderId(orderId);
		inspectorPunish.setPmEmployeeId(pmEmployeeId);
		inspectorPunish.setType(ConstantUtils.PM_SETTLE_CATEGORY_401);

		List<InspectorPunish> midInspectorList = bizPmSettleBillService.findInspector(inspectorPunish);
		model.addAttribute("midInspectorList", midInspectorList);
		return "modules/pmsettlebill/midInspectorDetails";
	}

	/**
	 * 竣工质检罚款
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pmsettlebill:bizPmSettleBill:view")
	@RequestMapping(value = { "completQcCheckPunishAmount", "" })
	public String completQcCheckPunishAmount(Integer orderId,Integer pmEmployeeId, Model model) {
		InspectorPunish inspectorPunish = new InspectorPunish();
		inspectorPunish.setOrderId(orderId);
		inspectorPunish.setPmEmployeeId(pmEmployeeId);
		inspectorPunish.setType(ConstantUtils.PM_SETTLE_CATEGORY_402);
		List<InspectorPunish> completInspectorList = bizPmSettleBillService.findInspector(inspectorPunish);
		model.addAttribute("completInspectorList", completInspectorList);
		return "modules/pmsettlebill/completeInspectorDetails";
	}

	/**
	 * 自采材料信息
	 * 
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "querySelfbuyMaterial")
	public String querySelfbuyMaterial(Integer orderId,Integer pmEmployeeId, Model model) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orderId", orderId);
		param.put("pmEmployeeId",pmEmployeeId);
		List<BizMaterialSelfbuyVo> list = bizPmSettleBillService.querySelfbuyMaterial(param);
		Double totalAmount = 0.0;
		if (list != null && list.size() > 0) {
			for (BizMaterialSelfbuyVo vo : list) {
				totalAmount = totalAmount + vo.getSettleAmount();
			}
		}
		model.addAttribute("list", list);
		model.addAttribute("totalAmount", totalAmount);
		return "modules/pmsettlebill/MaterialSelfbuyDetail";
	}

	/**
	 * 任务包材结算明细
	 * 
	 * @param orderId
	 * @param settleCategory
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "queryPmMaterials")
	public String queryPmMaterials(Integer orderId, String settleCategory, String settleStatus,Integer pmEmployeeId, Model model) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", orderId);
		param.put("settleCategory", settleCategory);
		param.put("settleStatus", settleStatus);
		param.put("pmEmployeeId", pmEmployeeId);
		List<PmMaterialsSettleInfo> pmMaterials = pmMaterialsSettleInfoService.queryPmMaterialsByParam(param);
		double pmMaterialsSettleAmount = 0.00;
		if (pmMaterials != null && pmMaterials.size() > 0) {
			for (PmMaterialsSettleInfo info : pmMaterials) {
				pmMaterialsSettleAmount = pmMaterialsSettleAmount + info.getPmMaterialsSettleAmount();
			}
		}
		model.addAttribute("settleCategory", settleCategory);
		model.addAttribute("pmMaterialsSettleAmount", pmMaterialsSettleAmount);
		model.addAttribute("pmMaterials", pmMaterials);
		return "modules/pmsettlebill/pmMaterial";
	}

	/**
	 * 中期提成明细
	 * 
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "queryMidwayCommission")
	public String queryMidwayCommission(Integer orderId,Integer pmEmployeeId, Model model) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orderId",orderId);
		param.put("pmEmployeeId",pmEmployeeId);
		BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap = inspectorConfirmService
				.queryManagerCommissionByParam(param);
		model.addAttribute("bizPmStarCommissionCnfgSnap", bizPmStarCommissionCnfgSnap);
		return "modules/pmsettlebill/midwayCommission";
	}

	/**
	 * 竣工提成明细
	 * 
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "queryCompleteCommission")
	public String queryCompleteCommission(Integer orderId,Integer pmEmployeeId, Model model) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orderId",orderId);
		param.put("pmEmployeeId",pmEmployeeId);
		BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap = inspectorConfirmService
				.queryManagerCommissionByParam(param);
		model.addAttribute("bizPmStarCommissionCnfgSnap", bizPmStarCommissionCnfgSnap);
		return "modules/pmsettlebill/completeCommission";
	}

	/**
	 * 质保金明细
	 * 
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "queryGuaranteeMoney")
	public String queryGuaranteeMoney(Integer orderId, Double pmGuaranteeMoney, Model model) {
		BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap = inspectorConfirmService
				.queryManagerCommissionByOrderId(orderId);
		BizPmGuaranteeMoneyCnfgSnap gmcs = bizPmGuaranteeMoneyCnfgSnapService.findGmc(orderId);
		model.addAttribute("gmcs", gmcs);
		model.addAttribute("pmGuaranteeMoney", pmGuaranteeMoney);
		model.addAttribute("bizPmStarCommissionCnfgSnap", bizPmStarCommissionCnfgSnap);
		return "modules/pmsettlebill/guaranteeMoney";
	}
	

	/**
	 * 中期奖励明细
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "querymidwayReward")
	public String querymidwayReward(Integer orderId,Integer pmEmployeeId,double midwayRewardAmount, Model model){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orderId", orderId);
		param.put("pmEmployeeId", pmEmployeeId);
		param.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_907);
		List<BizAssessRewardPunish> list = bizAssessRewardPunishService.queryPmRewardPunishBySettleParam(param);
		model.addAttribute("list",list);
		model.addAttribute("midwayRewardAmount",midwayRewardAmount);
		return "modules/pmsettlebill/midwayReward";
	}


	/**
	 * 中期扣款明细
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "querymidwayPunish")
	public String querymidwayPunish(Integer orderId,Integer pmEmployeeId,double midwayPunishAmount, Model model){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orderId", orderId);
		param.put("pmEmployeeId", pmEmployeeId);
		param.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_908);
		List<BizAssessRewardPunish> list = bizAssessRewardPunishService.queryPmRewardPunishBySettleParam(param);
		model.addAttribute("list",list);
		model.addAttribute("midwayPunishAmount",midwayPunishAmount);
		return "modules/pmsettlebill/midwayPunish";
	}


	/**
	 * 竣工奖励明细
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "querycompleteReward")
	public String querycompleteReward(Integer orderId,Integer pmEmployeeId,double completeRewardAmount, Model model){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orderId", orderId);
		param.put("pmEmployeeId", pmEmployeeId);
		param.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_1002);
		List<BizAssessRewardPunish> list = bizAssessRewardPunishService.queryPmRewardPunishBySettleParam(param);
		model.addAttribute("list",list);
		model.addAttribute("completeRewardAmount",completeRewardAmount);
		return "modules/pmsettlebill/completeReward";
	}

	/**
	 * 竣工扣款明细
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "querycompletePunish")
	public String querycompletePunish(Integer orderId,Integer pmEmployeeId,double completePunishAmount, Model model){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orderId", orderId);
		param.put("pmEmployeeId", pmEmployeeId);
		param.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_1003);
		List<BizAssessRewardPunish> list = bizAssessRewardPunishService.queryPmRewardPunishBySettleParam(param);
		model.addAttribute("list",list);
		model.addAttribute("completePunishAmount",completePunishAmount);
		return "modules/pmsettlebill/completePunish";
	}

	/**
	 * 中期巡检奖励明细
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "queryMidwayInspectionReward")
	public String queryMidwayInspectionReward(Integer orderId,Integer pmEmployeeId,double midwayInspectionRewardAmount, Model model){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orderId", orderId);
		param.put("pmEmployeeId", pmEmployeeId);
		param.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_912);
		List<BizAssessRewardPunish> list = bizAssessRewardPunishService.queryPmRewardPunishBySettleParam(param);
		model.addAttribute("list",list);
		model.addAttribute("midwayInspectionRewardAmount",midwayInspectionRewardAmount);
		return "modules/pmsettlebill/midwayInspectionReward";
	}

	/**
	 * 中期巡检罚款明细
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "queryMidwayInspectionPunish")
	public String queryMidwayInspectionPunish(Integer orderId,Integer pmEmployeeId,double midwayInspectionPunishAmount, Model model){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orderId", orderId);
		param.put("pmEmployeeId", pmEmployeeId);
		param.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_913);
		List<BizAssessRewardPunish> list = bizAssessRewardPunishService.queryPmRewardPunishBySettleParam(param);
		model.addAttribute("list",list);
		model.addAttribute("midwayInspectionPunishAmount",midwayInspectionPunishAmount);
		return "modules/pmsettlebill/midwayInspectionPunish";
	}

	/**
	 * 竣工巡检奖励明细
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "queryCompleteInspectionReward")
	public String querycompleteInspectionReward(Integer orderId,Integer pmEmployeeId,double completeInspectionRewardAmount, Model model){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orderId", orderId);
		param.put("pmEmployeeId", pmEmployeeId);
		param.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_1012);
		List<BizAssessRewardPunish> list = bizAssessRewardPunishService.queryPmRewardPunishBySettleParam(param);
		model.addAttribute("list",list);
		model.addAttribute("completeInspectionRewardAmount",completeInspectionRewardAmount);
		return "modules/pmsettlebill/completeInspectionReward";
	}

	/**
	 * 竣工巡检罚款明细
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "queryCompleteInspectionPunish")
	public String querycompleteInspectionPunish(Integer orderId,Integer pmEmployeeId,double completeInspectionPunishAmount, Model model){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orderId", orderId);
		param.put("pmEmployeeId", pmEmployeeId);
		param.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_1013);
		List<BizAssessRewardPunish> list = bizAssessRewardPunishService.queryPmRewardPunishBySettleParam(param);
		model.addAttribute("list",list);
		model.addAttribute("completeInspectionPunishAmount",completeInspectionPunishAmount);
		return "modules/pmsettlebill/completeInspectionPunish";
	}

}