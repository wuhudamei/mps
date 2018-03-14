package cn.damei.web.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.service.modules.BizBusinessStatusLogService;
import cn.damei.entity.modules.BizOrderFinishProjectBill;
import cn.damei.service.modules.BizOrderFinishProjectBillService;
import cn.damei.entity.modules.BizQcBill;
import cn.damei.service.modules.BizQcBillService;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizOrderMaterialsStandard;
import cn.damei.entity.modules.BizMaterialsStandardReceiveBill;
import cn.damei.entity.modules.Order2;
import cn.damei.service.modules.OrderService2;
import cn.damei.entity.modules.PmMaterialsSettleInfo;
import cn.damei.service.modules.PmMaterialsSettleInfoService;
import cn.damei.entity.modules.BizPmGuaranteeMoneyCnfgSnap;
import cn.damei.service.modules.BizPmGuaranteeMoneyCnfgSnapService;
import cn.damei.entity.modules.BizPmOwnpayCnfgSnap;
import cn.damei.entity.modules.BizMaterialSelfbuyVo;
import cn.damei.entity.modules.BizPmSettleBill;
import cn.damei.service.modules.BizPmSettleBillService;
import cn.damei.entity.modules.BizPmStarCommissionCnfgSnap;
import cn.damei.entity.modules.BizGuaranteeMoneyBalance;
import cn.damei.service.modules.BizGuaranteeMoneyBalanceService;
import cn.damei.entity.modules.QcBillCheckItemInfo;
import cn.damei.service.modules.InspectorConfirmService;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/pmsettlebill/bizPmCommissionSettleController")
public class BizPmCommissionSettleController extends BaseController {
    
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizPmSettleBillService bizPmSettleBillService;
	@Autowired
	private PmMaterialsSettleInfoService pmMaterialsSettleInfoService;
	@Autowired
	private InspectorConfirmService inspectorConfirmService;
	@Autowired
	private BizPmGuaranteeMoneyCnfgSnapService bizPmGuaranteeMoneyCnfgSnapService;
	@Autowired
	private BizGuaranteeMoneyBalanceService bizGuaranteeMoneyBalanceService;
	@Autowired
	private BizQcBillService bizQcBillService;
	@Autowired
	private BizOrderFinishProjectBillService bizOrderFinishProjectBillService;
	@Autowired
	private BizBusinessStatusLogService bizBusinessStatusLogService;
	@Autowired
	private OrderService2 orderService2;
	@RequestMapping(value = "queryPmComkissionSettle")
	public String queryPmComkissionSettle(BizPmSettleBill bizPmSettleBill, HttpServletRequest request, HttpServletResponse response,
			Model model) {

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

		bizPmSettleBill.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
		Page<BizPmSettleBill> page = bizPmSettleBillService
				.findPmCommissionSettle(new Page<BizPmSettleBill>(request, response), bizPmSettleBill);
		model.addAttribute("page", page);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("bizPmSettleBill", bizPmSettleBill);
		return "modules/pmsettlebill/bizPmCommissionSettle";
	}
	
	@RequestMapping(value = "queryMidwayCommission")
	public String queryMidwayCommission(Integer orderId,HttpServletRequest request, HttpServletResponse response,
			Model model){
		BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap = inspectorConfirmService.queryManagerCommissionByOrderId(orderId);

		Double commissionAmount = bizPmStarCommissionCnfgSnap.getCommissionAmount()
				* bizPmStarCommissionCnfgSnap.getCommissionRateMidway();

		List<BizMaterialsStandardReceiveBill> list = inspectorConfirmService.findThree(orderId);
		BizMaterialsStandardReceiveBill details3 = new BizMaterialsStandardReceiveBill();
		details3.setReceiveBillAmount(0.0);
		if(list != null && list.size()>0){
			for(BizMaterialsStandardReceiveBill bill : list){
				details3.setReceiveBillAmount(details3.getReceiveBillAmount()+bill.getReceiveBillAmount());
			}
		}
		
        if(details3.getReceiveBillAmount()<0){
        	details3.setReceiveBillAmount((0-details3.getReceiveBillAmount()));
        }
        List<BizOrderMaterialsStandard>  materialsStandardList=inspectorConfirmService.queryMaterialsStandardByOrderId(orderId);

		Double managerOwnpay = inspectorConfirmService.queryManagerOwnpay(orderId);
		List<BizPmOwnpayCnfgSnap> pmOwnpayCnfgSnapList=inspectorConfirmService.queryPmOwnpayCnfgSnapByOrderId(orderId);

		Map<String,Object> map =new HashMap<String,Object>();
		map.put("orderId", orderId);
		map.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_4);
		map.put("settleStatus", 10);
		map.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
		Double managerPenalty = inspectorConfirmService.queryManangerPenalty(map);
		
		List<QcBillCheckItemInfo> pmPunishList = inspectorConfirmService.queryPmPunishByParam(map);
		if(managerPenalty<0){
			managerPenalty=0-managerPenalty;
		}
		
		double pmMaterialsSettleAmount = 0.00;
		List<PmMaterialsSettleInfo> pmMaterials = pmMaterialsSettleInfoService.queryPmMaterialsByOrderId(orderId);
		if(pmMaterials == null){
			pmMaterials = new ArrayList<PmMaterialsSettleInfo>();
		}
		if(pmMaterials != null && pmMaterials.size()>0){
			for(PmMaterialsSettleInfo info : pmMaterials){
				pmMaterialsSettleAmount = pmMaterialsSettleAmount+info.getPmMaterialsSettleAmount();
			}
		}
		double settleAmount = commissionAmount+managerOwnpay-managerPenalty-details3.getReceiveBillAmount()+pmMaterialsSettleAmount;
		model.addAttribute("pmMaterialsSettleAmount",pmMaterialsSettleAmount);
		model.addAttribute("pmMaterials",pmMaterials);
		model.addAttribute("commissionAmount",commissionAmount);
		model.addAttribute("settleAmount",settleAmount);
		model.addAttribute("details3",details3);
		model.addAttribute("managerOwnpay",managerOwnpay);
		model.addAttribute("managerPenalty",managerPenalty);
		model.addAttribute("materialsStandardList",materialsStandardList);
		model.addAttribute("pmOwnpayCnfgSnapList",pmOwnpayCnfgSnapList);
		model.addAttribute("pmPunishList",pmPunishList);
		model.addAttribute("bizPmStarCommissionCnfgSnap",bizPmStarCommissionCnfgSnap);
		return "modules/pmsettlebill/managerAmortizationDetail";
	}
	
	@RequestMapping(value = "queryCompleteCommission")
	public String queryCompleteCommission(Integer orderId,HttpServletRequest request, HttpServletResponse response,
			Model model){
		BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap = inspectorConfirmService.queryManagerCommissionByOrderId(orderId);

		Double commissionAmount = bizPmStarCommissionCnfgSnap.getCommissionAmount()
				* bizPmStarCommissionCnfgSnap.getCommissionRateComplete();

		Double pmGuaranteeMoney=0.0;
		BizPmGuaranteeMoneyCnfgSnap gmcs = bizPmGuaranteeMoneyCnfgSnapService.findGmc(orderId);
		if(gmcs != null){
			BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance = bizGuaranteeMoneyBalanceService.findGuaranteeMoneyBalanceByEmployeeId(gmcs.getPmEmployeeId());
			if(bizGuaranteeMoneyBalance == null){
				bizGuaranteeMoneyBalance = new BizGuaranteeMoneyBalance();
			}
			Double total = bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance()+gmcs.getGuaranteeMoneyPerOrder();
			if(total >= gmcs.getGuaranteeMoneyMax()){
				pmGuaranteeMoney=gmcs.getGuaranteeMoneyMax()-bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance();
            }else{
            	pmGuaranteeMoney=gmcs.getGuaranteeMoneyPerOrder();
            }			
		}
		if(pmGuaranteeMoney<0){
			pmGuaranteeMoney = 0-pmGuaranteeMoney;
		}

		Map<String,Object> map =new HashMap<String,Object>();
		map.put("orderId", orderId);
		map.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_4);
		map.put("settleStatus", 10);
		map.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
		Double managerPenalty = inspectorConfirmService.queryManangerPenalty(map);
		if(managerPenalty<0){
			managerPenalty = 0-managerPenalty;
		}
		List<QcBillCheckItemInfo> pmPunishList = inspectorConfirmService.queryPmPunishByParam(map);

		Map<String,Object> map1 =new HashMap<String,Object>();
		map1.put("orderId", orderId);
		map1.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_11);
		Double sinceMaterials = inspectorConfirmService.queryManangerPenalty(map1);
		if(sinceMaterials<0){
			sinceMaterials = 0-sinceMaterials;
		}
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orderId", orderId);
		param.put("pmEmployeeId",bizPmStarCommissionCnfgSnap.getPmEmployeeId());
		List<BizMaterialSelfbuyVo> materialSelfbuyList = bizPmSettleBillService.querySelfbuyMaterial(param);
		

		double pmMaterialsSettleAmount = 0.00;
		List<PmMaterialsSettleInfo> pmMaterials = pmMaterialsSettleInfoService.queryPmMaterialsByOrderId(orderId);
		if(pmMaterials != null && pmMaterials.size()>0){
			for(PmMaterialsSettleInfo info : pmMaterials){
				pmMaterialsSettleAmount = pmMaterialsSettleAmount+info.getPmMaterialsSettleAmount();
			}
		}
		

		Double settleAmount = commissionAmount+sinceMaterials-pmGuaranteeMoney-managerPenalty+pmMaterialsSettleAmount;
		model.addAttribute("pmMaterialsSettleAmount",pmMaterialsSettleAmount);
		model.addAttribute("pmMaterials",pmMaterials);
		model.addAttribute("commissionAmount",commissionAmount);
		model.addAttribute("pmGuaranteeMoney",pmGuaranteeMoney);
		model.addAttribute("managerPenalty",managerPenalty);
		model.addAttribute("sinceMaterials",sinceMaterials);
		model.addAttribute("settleAmount",settleAmount);
		model.addAttribute("bizPmStarCommissionCnfgSnap",bizPmStarCommissionCnfgSnap);
		model.addAttribute("gmcs",gmcs);
		model.addAttribute("pmPunishList",pmPunishList);
		model.addAttribute("materialSelfbuyList",materialSelfbuyList);
		return "modules/pmsettlebill/managerCompletedDetail";
	}
	
	@RequestMapping(value = "queryMidwayCommissionLog")
	public String queryMidwayCommissionLog(Integer orderId,HttpServletRequest request, HttpServletResponse response,
			Model model){
		BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap = inspectorConfirmService.queryManagerCommissionByOrderId(orderId);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("qcBillType", "1");
		param.put("orderId", orderId);
		param.put("nodeInex", "6");
		BizQcBill bizQcBill = bizQcBillService.queryQcBillByParam(param);
		
		Order2 order2 = orderService2.get(orderId);
		

		BizBusinessStatusLog log1 = bizBusinessStatusLogService.queryOrderPmSettleLog(orderId, BusinessLogConstantUtil.BUSINESS_TYPE_302, "30");
		model.addAttribute("log1",log1);

		BizBusinessStatusLog log2= bizBusinessStatusLogService.queryOrderPmSettleLog(orderId, BusinessLogConstantUtil.BUSINESS_TYPE_304, "30");
		model.addAttribute("log2",log2);
		

		Map<String,Object> pmSettleBillParam = new HashMap<String,Object>();
		pmSettleBillParam.put("orderId",orderId);
		pmSettleBillParam.put("settleBillType","1");
		pmSettleBillParam.put("pmEmployeeId",order2.getItemManagerId());
		BizPmSettleBill bizPmSettleBill = bizPmSettleBillService.queryPmSettleBillInfoByParam(pmSettleBillParam);
		if(bizPmSettleBill != null && bizPmSettleBill.getId() != null && bizPmSettleBill.getPmSettleSummaryBillId() != null){
			BizBusinessStatusLog log3 = 
					bizBusinessStatusLogService.queryOrderPmSettleLog(bizPmSettleBill.getPmSettleSummaryBillId(), BusinessLogConstantUtil.BUSINESS_TYPE_306, "50");
			model.addAttribute("log3",log3);
		}
		model.addAttribute("bizPmStarCommissionCnfgSnap",bizPmStarCommissionCnfgSnap);
		model.addAttribute("bizQcBill",bizQcBill);
		return "modules/pmsettlebill/midwayCommissionLog";
	}
	
	@RequestMapping(value = "queryCompleteCommissionLog")
	public String queryCompleteCommissionLog(Integer orderId,HttpServletRequest request, HttpServletResponse response,
			Model model){
		BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap = inspectorConfirmService.queryManagerCommissionByOrderId(orderId);
		BizOrderFinishProjectBill bizOrderFinishProjectBill = bizOrderFinishProjectBillService.getByOrderID(orderId);
        Order2 order2 = orderService2.get(orderId);
		

        BizBusinessStatusLog log1 = bizBusinessStatusLogService.queryOrderPmSettleLog(orderId, BusinessLogConstantUtil.BUSINESS_TYPE_303, ConstantUtils.ORDERSTATUS_340_VALUE);
        model.addAttribute("log1",log1);

        BizBusinessStatusLog log2 = bizBusinessStatusLogService.queryOrderPmSettleLog(orderId, BusinessLogConstantUtil.BUSINESS_TYPE_305, "30");
        model.addAttribute("log2",log2);
		

		Map<String,Object> pmSettleBillParam = new HashMap<String,Object>();
		pmSettleBillParam.put("orderId",orderId);
		pmSettleBillParam.put("settleBillType","1");
		pmSettleBillParam.put("pmEmployeeId",order2.getItemManagerId());
		BizPmSettleBill bizPmSettleBill = bizPmSettleBillService.queryPmSettleBillInfoByParam(pmSettleBillParam);
		if(bizPmSettleBill != null && bizPmSettleBill.getId() != null && bizPmSettleBill.getPmSettleSummaryBillId() != null){
			BizBusinessStatusLog  log3 = 
					bizBusinessStatusLogService.queryOrderPmSettleLog(bizPmSettleBill.getPmSettleSummaryBillId(), BusinessLogConstantUtil.BUSINESS_TYPE_306, "50");
			model.addAttribute("log3",log3);
		}
		model.addAttribute("bizPmStarCommissionCnfgSnap",bizPmStarCommissionCnfgSnap);
		model.addAttribute("bizOrderFinishProjectBill",bizOrderFinishProjectBill);
		return "modules/pmsettlebill/completeCommissionLog";
	}
}
