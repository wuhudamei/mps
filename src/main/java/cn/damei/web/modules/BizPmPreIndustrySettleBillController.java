package cn.damei.web.modules;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizBusinessRewardPunish;
import cn.damei.entity.modules.BizOrderChange;
import cn.damei.entity.modules.BizOrderContractSettle;
import cn.damei.service.modules.BizBusinessRewardPunishService;
import cn.damei.service.modules.BizOrderChangeService;
import cn.damei.service.modules.BizOrderContractSettleService;
import cn.damei.entity.modules.BizOrderMaterialCarryCost;
import cn.damei.service.modules.BizOrderMaterialCarryCostService;
import cn.damei.entity.modules.BizPmPreIndustrySettleBill;
import cn.damei.service.modules.BizPmPreIndustrySettleBillService;
import cn.damei.entity.modules.BizQcLongwayCommissionLog;
import cn.damei.service.modules.BizQcLongwayCommissionLogService;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.Order2;
import cn.damei.service.modules.OrderService2;
import cn.damei.common.utils.UserUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping(value = "${adminPath}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill")
public class BizPmPreIndustrySettleBillController extends BaseController {

	@Autowired
	private BizPmPreIndustrySettleBillService bizPmPreIndustrySettleBillService;

	@Autowired
	private BizEmployeeService2 bizEmployeeService2;

	@Autowired
	private OrderService2 orderService2;

	@Autowired
	private BizOrderContractSettleService bizOrderContractSettleService;

	@Autowired
	private BizOrderChangeService bizOrderChangeService;

	@Autowired
	private BizBusinessRewardPunishService bizBusinessRewardPunishService;

	@Autowired
	private BizOrderMaterialCarryCostService bizOrderMaterialCarryCostService;

	@Autowired
	private BizQcLongwayCommissionLogService bizQcLongwayCommissionLogService;

	@RequestMapping(value = "openPmPreIndustrySettleBill")
	public String openPmPreIndustrySettleBill(BizPmPreIndustrySettleBill bizPmPreIndustrySettleBill,
			HttpServletRequest request, HttpServletResponse response, Model model) {

		if (bizPmPreIndustrySettleBill.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizPmPreIndustrySettleBill.setStoreId(null);
			} else {
				bizPmPreIndustrySettleBill.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		} else {
			bizPmPreIndustrySettleBill.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}
		List<String> statusList = new ArrayList<String>();
		statusList.add("10");
		statusList.add("45");
		bizPmPreIndustrySettleBill.setStatusList(statusList);
		return "modules/bizPmPreIndustrySettleBill/pmPreIndustrySettleBillList";

	}

	@RequestMapping(value = "pmPreIndustrySettleBillList")
	public String pmPreIndustrySettleBillList(BizPmPreIndustrySettleBill bizPmPreIndustrySettleBill,
			HttpServletRequest request, HttpServletResponse response, Model model) {

		if (bizPmPreIndustrySettleBill.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizPmPreIndustrySettleBill.setStoreId(null);
			} else {
				bizPmPreIndustrySettleBill.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		} else {
			bizPmPreIndustrySettleBill.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}

		if (bizPmPreIndustrySettleBill.getProjectMode() == null) {
			bizPmPreIndustrySettleBill.setProjectMode(4);
		}

		if (bizPmPreIndustrySettleBill.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizPmPreIndustrySettleBill.setEnginDepartIds(list);
				} else {
					bizPmPreIndustrySettleBill.setEnginDepartIds(null);
				}
			} else {
				bizPmPreIndustrySettleBill.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizPmPreIndustrySettleBill.getEnginDepartId());
			bizPmPreIndustrySettleBill.setEnginDepartIds(list);
		}
		List<String> statusList = new ArrayList<String>();
		if (bizPmPreIndustrySettleBill.getStatus() != null) {
			String[] statusArr = bizPmPreIndustrySettleBill.getStatus().split(",");
			if (statusArr != null && statusArr.length > 0) {
				for (String status : statusArr) {
					statusList.add(status);
				}
			}
		} else {
			statusList.add("10");
			statusList.add("45");
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("storeId", bizPmPreIndustrySettleBill.getStoreId());
		param.put("projectMode", bizPmPreIndustrySettleBill.getProjectMode());
		param.put("status", 10);
		int createCount = bizPmPreIndustrySettleBillService.queryCountByParam(param);
		param.put("status", 45);
		int rejectCount = bizPmPreIndustrySettleBillService.queryCountByParam(param);
		bizPmPreIndustrySettleBill.setStatusList(statusList);
		Page<BizPmPreIndustrySettleBill> page = bizPmPreIndustrySettleBillService
				.findPage(new Page<BizPmPreIndustrySettleBill>(request, response), bizPmPreIndustrySettleBill);
		model.addAttribute("page", page);
		model.addAttribute("createCount", createCount);
		model.addAttribute("rejectCount", rejectCount);
		model.addAttribute("bizPmPreIndustrySettleBill", bizPmPreIndustrySettleBill);
		return "modules/bizPmPreIndustrySettleBill/pmPreIndustrySettleBillList";
	}


	@RequestMapping(value = "sendingSettleBill")
	public @ResponseBody String sendingSettleBill(Integer id) {
		String result = "0";
		try {
			bizPmPreIndustrySettleBillService.sendingSettleBill(id);
		} catch (Exception e) {
			result = "1";
			e.printStackTrace();
			throw e;
		}
		return result;
	}


	@RequestMapping(value = "editSettleBill")
	public String editSettleBill(Integer id, HttpServletRequest request, HttpServletResponse response, Model model) {
		BizPmPreIndustrySettleBill settleBill = bizPmPreIndustrySettleBillService.get(id);
        String isNewSettleBill = settleBill.getIsNewSettleBill();
		Order2 order2 = orderService2.get(settleBill.getOrderId());
		model.addAttribute("order2", order2);
		model.addAttribute("settleBill", settleBill);
		String result = null;
		if (settleBill.getSettleBillType().equals("1")) {
            if("0".equals(isNewSettleBill)){
                result = "modules/proIndustryPmSettle/editMidwaySettleInfo";
            }else {
                result = "modules/proIndustryPmSettle/newEditMidwaySettleInfo";
            }
		} else if (settleBill.getSettleBillType().equals("2")) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("orderId", settleBill.getOrderId());
			param.put("settleBillType", 1);
			BizPmPreIndustrySettleBill midwaySettleBill = bizPmPreIndustrySettleBillService
					.queryPmPreIndustrySettleBillByParam(param);
			model.addAttribute("midwaySettleBill", midwaySettleBill);
            if("0".equals(isNewSettleBill)){
                result = "modules/proIndustryPmSettle/editCompleteSettleInfo";
            }else {
                result = "modules/proIndustryPmSettle/newEditCompleteSettleInfo";
            }
		}
		return result;
	}


	@RequestMapping(value = "monthlyPmPreIndustrySettle")
	public String monthlyPmPreIndustrySettle(BizPmPreIndustrySettleBill bizPmPreIndustrySettleBill,
			HttpServletRequest request, HttpServletResponse response, Model model) {

		if (bizPmPreIndustrySettleBill.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizPmPreIndustrySettleBill.setStoreId(null);
			} else {
				bizPmPreIndustrySettleBill.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		} else {
			bizPmPreIndustrySettleBill.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}
		return "modules/bizPmPreIndustrySettleBill/monthlyPmPreIndustrySettle";
	}


	@RequestMapping(value = "monthlyPmPreIndustrySettleList")
	public String monthlyPmPreIndustrySettleList(BizPmPreIndustrySettleBill bizPmPreIndustrySettleBill,
			HttpServletRequest request, HttpServletResponse response, Model model) {

		if (bizPmPreIndustrySettleBill.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizPmPreIndustrySettleBill.setStoreId(null);
			} else {
				bizPmPreIndustrySettleBill.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		} else {
			bizPmPreIndustrySettleBill.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}

		if (bizPmPreIndustrySettleBill.getProjectMode() == null) {
			bizPmPreIndustrySettleBill.setProjectMode(4);
		}


		if (bizPmPreIndustrySettleBill.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizPmPreIndustrySettleBill.setEnginDepartIds(list);
				} else {
					bizPmPreIndustrySettleBill.setEnginDepartIds(null);
				}
			} else {
				bizPmPreIndustrySettleBill.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizPmPreIndustrySettleBill.getEnginDepartId());
			bizPmPreIndustrySettleBill.setEnginDepartIds(list);
		}
		List<String> statusList = new ArrayList<String>();
		statusList.add("40");
		bizPmPreIndustrySettleBill.setStatusList(statusList);
		List<BizPmPreIndustrySettleBill> list = bizPmPreIndustrySettleBillService.findList(bizPmPreIndustrySettleBill);
		model.addAttribute("list", list);
		return "modules/bizPmPreIndustrySettleBill/monthlyPmPreIndustrySettle";
	}

	@RequestMapping(value = "checkSettleBill")
	public @ResponseBody Map<String, Object> checkSettleBill(Integer storeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		map.put("settleMonth", format.format(cal.getTime()));
		map.put("status", 50);
		map.put("storeId", storeId);
		int count = bizPmPreIndustrySettleBillService.querySummaryBillCountByParam(map);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("count", count);
		resultMap.put("settleMonth", format.format(cal.getTime()));
		return resultMap;
	}


	@RequestMapping(value = "createMonthlySettle")
	public String createMonthlySettle(String ids, String settleMonth, String storeId,
			RedirectAttributes redirectAttributes) {
		bizPmPreIndustrySettleBillService.createMonthlySettle(ids, settleMonth, storeId);
		addMessage(redirectAttributes, "生成月度工程结算成功");
		return "redirect:" + Global.getAdminPath()
				+ "/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/monthlyPmPreIndustrySettleList?storeId="
				+ storeId;
	}

	@RequestMapping(value = "queryPmSettleBill")
	public String queryPmSettleBill(BizPmPreIndustrySettleBill bizPmPreIndustrySettleBill, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		if (bizPmPreIndustrySettleBill.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizPmPreIndustrySettleBill.setStoreId(null);
			} else {
				bizPmPreIndustrySettleBill.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		} else {
			bizPmPreIndustrySettleBill.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}
		List<String> statusList = new ArrayList<String>();
		statusList.add("50");
		bizPmPreIndustrySettleBill.setStatusList(statusList);
		return "modules/bizPmPreIndustrySettleBill/queryPmPreIndustrySettleBillList";
	}

	@RequestMapping(value = "queryPmSettleBillList")
	public String queryPmSettleBillList(BizPmPreIndustrySettleBill bizPmPreIndustrySettleBill,
			HttpServletRequest request, HttpServletResponse response, Model model) {

		if (bizPmPreIndustrySettleBill.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizPmPreIndustrySettleBill.setStoreId(null);
			} else {
				bizPmPreIndustrySettleBill.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		} else {
			bizPmPreIndustrySettleBill.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}

		if (bizPmPreIndustrySettleBill.getProjectMode() == null) {
			bizPmPreIndustrySettleBill.setProjectMode(4);
		}

		if (bizPmPreIndustrySettleBill.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizPmPreIndustrySettleBill.setEnginDepartIds(list);
				} else {
					bizPmPreIndustrySettleBill.setEnginDepartIds(null);
				}
			} else {
				bizPmPreIndustrySettleBill.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizPmPreIndustrySettleBill.getEnginDepartId());
			bizPmPreIndustrySettleBill.setEnginDepartIds(list);
		}
		List<String> statusList = new ArrayList<String>();
		if (bizPmPreIndustrySettleBill.getStatus() != null) {
			String[] statusArr = bizPmPreIndustrySettleBill.getStatus().split(",");
			if (statusArr != null && statusArr.length > 0) {
				for (String status : statusArr) {
					statusList.add(status);
				}
			}
		} else {
			statusList.add("50");
		}
		bizPmPreIndustrySettleBill.setStatusList(statusList);
		Page<BizPmPreIndustrySettleBill> page = bizPmPreIndustrySettleBillService
				.findPage(new Page<BizPmPreIndustrySettleBill>(request, response), bizPmPreIndustrySettleBill);
		model.addAttribute("page", page);
		model.addAttribute("bizPmPreIndustrySettleBill", bizPmPreIndustrySettleBill);
		return "modules/bizPmPreIndustrySettleBill/queryPmPreIndustrySettleBillList";
	}

	@RequestMapping(value = "openSettleInfoInfo")
	public String openSettleInfoInfo(Integer settleBillId, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		BizPmPreIndustrySettleBill bizPmPreIndustrySettleBill = bizPmPreIndustrySettleBillService.get(settleBillId);
		Order2 order2 = orderService2.get(bizPmPreIndustrySettleBill.getOrderId());
		model.addAttribute("settleBill", bizPmPreIndustrySettleBill);
		model.addAttribute("order2", order2);
		String result = null;
        String isNewSettleBill = bizPmPreIndustrySettleBill.getIsNewSettleBill();
		if (bizPmPreIndustrySettleBill.getSettleBillType().equals("1")) {
            if("0".equals(isNewSettleBill)){
                result = "modules/bizPmPreIndustrySettleBill/midwaySettleInfo";
            }else {
                result = "modules/bizPmPreIndustrySettleBill/newMidwaySettleInfo";
            }
		} else if (bizPmPreIndustrySettleBill.getSettleBillType().equals("2")) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("orderId", bizPmPreIndustrySettleBill.getOrderId());
			param.put("settleBillType", 1);
			BizPmPreIndustrySettleBill midwaySettleBill = bizPmPreIndustrySettleBillService
					.queryPmPreIndustrySettleBillByParam(param);
			model.addAttribute("midwaySettleBill", midwaySettleBill);
            if("0".equals(isNewSettleBill)){
                result = "modules/bizPmPreIndustrySettleBill/completeSettleInfo";
            }else {
                result = "modules/bizPmPreIndustrySettleBill/newCompleteSettleInfo";
            }
		}
		return result;
	}

	@RequestMapping(value = "openContractTotal")
	public String openContractTotal(Integer orderId, Integer settleStage, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		Order2 order2 = orderService2.get(orderId);
		Map<String, Object> settParam = new HashMap<String, Object>();
		settParam.put("orderId", orderId);
		settParam.put("settleStage", settleStage);
		BizOrderContractSettle contractSettle = bizOrderContractSettleService.findOrderContractSettleByParam(settParam);
		model.addAttribute("contractSettle", contractSettle);
		model.addAttribute("order2", order2);
		return "modules/bizPmPreIndustrySettleBill/contractTotal";
	}

	@RequestMapping(value = "openBaseInstalled")
	public String openBaseInstalled(Integer orderId, String changeType, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		Order2 order2 = orderService2.get(orderId);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", orderId);
		param.put("changeType", changeType);
		BizOrderChange bizOrderChange = bizOrderChangeService.queryOrderChangeByParam(param);
		if (bizOrderChange == null) {
			bizOrderChange = new BizOrderChange();
			bizOrderChange.setOrderId(orderId);
			bizOrderChange.setChangeType(changeType);
		} else if (bizOrderChange.getId() == null) {
			bizOrderChange.setOrderId(orderId);
			bizOrderChange.setChangeType(changeType);
		}
		model.addAttribute("bizOrderChange", bizOrderChange);
		model.addAttribute("order2", order2);
		String result = null;
		if (changeType.equals("10")) {
			result = "modules/bizPmPreIndustrySettleBill/baseInstalled";
		} else if (changeType.equals("20")) {
			result = "modules/bizPmPreIndustrySettleBill/midwayChangeAdd";
		} else if (changeType.equals("30")) {
			result = "modules/bizPmPreIndustrySettleBill/midwayChangeReduce";
		} else if (changeType.equals("40")) {
			result = "modules/bizPmPreIndustrySettleBill/completeChangeAdd";
		} else if (changeType.equals("50")) {
			result = "modules/bizPmPreIndustrySettleBill/completeChangeReduce";
		}
		return result;
	}

	@RequestMapping(value = "openPmRewardPunish")
	public String openPmRewardPunish(Integer orderId, String rewardPunishType, String relatedBusinessType, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", orderId);
		param.put("employeeType", 2);
		param.put("relatedBusinessType", relatedBusinessType);
		param.put("rewardPunishType", rewardPunishType);
		BizBusinessRewardPunish bizBusinessRewardPunish = bizBusinessRewardPunishService
				.queryBusinessRewardPunishByParam(param);
		if (bizBusinessRewardPunish == null || bizBusinessRewardPunish.getId() == null) {
			bizBusinessRewardPunish = new BizBusinessRewardPunish();
			bizBusinessRewardPunish.setRelatedBusinessIdInt(orderId);
			bizBusinessRewardPunish.setRelatedBusinessType(relatedBusinessType);
			bizBusinessRewardPunish.setRewardPunishType(rewardPunishType);
		}
		Order2 order2 = orderService2.get(orderId);
		model.addAttribute("bizBusinessRewardPunish", bizBusinessRewardPunish);
		model.addAttribute("order2", order2);
		String result = null;
		if (rewardPunishType.equals("1")) {
			if (relatedBusinessType.equals("1")) {
				result = "modules/bizPmPreIndustrySettleBill/midwayRewardAmount";
			} else if (relatedBusinessType.equals("2")) {
				result = "modules/bizPmPreIndustrySettleBill/completeRewardAmount";
			}
		} else if (rewardPunishType.equals("2")) {
			if (relatedBusinessType.equals("1")) {
				result = "modules/bizPmPreIndustrySettleBill/midwayPunishAmount";
			} else if (relatedBusinessType.equals("2")) {
				result = "modules/bizPmPreIndustrySettleBill/completePunishAmount";
			}
		}
		return result;
	}

	@RequestMapping(value = "openOrderMaterialCarryCost")
	public String openOrderMaterialCarryCost(Integer orderId, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		BizOrderMaterialCarryCost bizOrderMaterialCarryCost = bizOrderMaterialCarryCostService
				.queryOrderMaterialCarryCostByOrderId(orderId);
		if (bizOrderMaterialCarryCost == null) {
			bizOrderMaterialCarryCost = new BizOrderMaterialCarryCost();
			bizOrderMaterialCarryCost.setOrderId(orderId);
		} else if (bizOrderMaterialCarryCost.getId() == null) {
			bizOrderMaterialCarryCost.setOrderId(orderId);
		}
		Order2 order2 = orderService2.get(orderId);
		model.addAttribute("bizOrderMaterialCarryCost", bizOrderMaterialCarryCost);
		model.addAttribute("order2", order2);
		return "modules/bizPmPreIndustrySettleBill/orderMaterialCarryCostInfo";
	}

	@RequestMapping(value = "openCommissionLog")
	public String openCommissionLog(BizQcLongwayCommissionLog bizQcLongwayCommissionLog, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		Order2 order2 = orderService2.get(bizQcLongwayCommissionLog.getOrderId());
		BizQcLongwayCommissionLog commissionLog = bizQcLongwayCommissionLogService
				.queryCommissionLogByParam(bizQcLongwayCommissionLog);
		model.addAttribute("order2", order2);
		model.addAttribute("commissionLog", commissionLog);
		return "modules/bizPmPreIndustrySettleBill/commissionLog";
	}

	@RequestMapping(value = "exportPmSettleBill")
	public void exportPmSettleBill(BizPmPreIndustrySettleBill bizPmPreIndustrySettleBill,
			HttpServletResponse response) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");

		if (bizPmPreIndustrySettleBill.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizPmPreIndustrySettleBill.setEnginDepartIds(list);
				} else {
					bizPmPreIndustrySettleBill.setEnginDepartIds(null);
				}
			} else {
				bizPmPreIndustrySettleBill.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizPmPreIndustrySettleBill.getEnginDepartId());
			bizPmPreIndustrySettleBill.setEnginDepartIds(list);
		}
		if(bizPmPreIndustrySettleBill.getProjectMode() == null){
			bizPmPreIndustrySettleBill.setProjectMode(4);
		}
		
		List<String> statusList = new ArrayList<String>();
		if (bizPmPreIndustrySettleBill.getStatus() != null) {
			String[] statusArr = bizPmPreIndustrySettleBill.getStatus().split(",");
			if (statusArr != null && statusArr.length > 0) {
				for (String status : statusArr) {
					statusList.add(status);
				}
			}
		} else {
			statusList.add("50");
		}
		bizPmPreIndustrySettleBill.setStatusList(statusList);
		
		HSSFWorkbook excel = bizPmPreIndustrySettleBillService.exportPmSettleBill(bizPmPreIndustrySettleBill);
		ServletOutputStream out = null;
		try {
			response.setContentType("application/binary;charset=utf-8");
			String headerStr = new String(("准产业项目结算单明细" + sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");
			response.setHeader("Content-disposition", "attachment; filename=" + headerStr + ".xls");
			out = response.getOutputStream();
			excel.write(out);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if( null != out ){
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
