package cn.damei.web.modules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizAssessRewardPunish;
import cn.damei.service.modules.BizAssessRewardPunishService;
import cn.damei.entity.modules.BizCompletedAudit;
import cn.damei.service.modules.BizCompletedAuditService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.PmMaterialsSettleInfo;
import cn.damei.service.modules.PmMaterialsSettleInfoService;
import cn.damei.entity.modules.BizPmGuaranteeMoneyCnfgSnap;
import cn.damei.service.modules.BizPmGuaranteeMoneyCnfgSnapService;
import cn.damei.entity.modules.BizMaterialSelfbuyVo;
import cn.damei.service.modules.BizPmSettleBillService;
import cn.damei.entity.modules.BizPmStarCommissionCnfgSnap;
import cn.damei.entity.modules.BizGuaranteeMoneyBalance;
import cn.damei.service.modules.BizGuaranteeMoneyBalanceService;
import cn.damei.entity.modules.QcBillCheckItemInfo;
import cn.damei.service.modules.InspectorConfirmService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
/**
 *	订单
 *	确认竣工
 *	biz_order
 */
@Controller
@RequestMapping(value = "${adminPath}/bizcompletedaudit/bizCompletedAudit")
public class BizCompletedAuditController extends BaseController{
	@Autowired
	private BizCompletedAuditService bizCompletedAuditService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	@Autowired
	private InspectorConfirmService inspectorConfirmService;
	
	@Autowired
	private BizPmGuaranteeMoneyCnfgSnapService bizPmGuaranteeMoneyCnfgSnapService;
	
	@Autowired
	private BizGuaranteeMoneyBalanceService bizGuaranteeMoneyBalanceService;
	
	@Autowired
	private PmMaterialsSettleInfoService pmMaterialsSettleInfoService;
	
	@Autowired
	private BizPmSettleBillService bizPmSettleBillService;
	
	@Autowired
	private BizAssessRewardPunishService bizAssessRewardPunishService;
	
	@ModelAttribute
	public BizCompletedAudit get(@RequestParam(required = false) Integer id) {
		BizCompletedAudit entity = null;
		if (StringUtils.isNotBlank(id + "")) {

			entity = bizCompletedAuditService.get(id);
		}
		if (entity == null) {
			entity = new BizCompletedAudit();
		}
		return entity;
	}
	
	@RequiresPermissions("bizcompletedaudit:bizCompletedAudit:view")
	@RequestMapping(value = {"preList", ""})
	public String preList(BizCompletedAudit bizCompletedAudit, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if(null == bizCompletedAudit.getEnginDepartId()){
			if(null!= UserUtils.getUser().getEmpId()){
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if(list != null && list.size()>0){
					bizCompletedAudit.setEnginDepartIds(list);
				}else{
					bizCompletedAudit.setEnginDepartIds(null);
				}
			} else {
				bizCompletedAudit.setEnginDepartIds(null);
			}
		}else{
			List<Integer> list = new ArrayList<>();
			list.add(bizCompletedAudit.getEnginDepartId());
			bizCompletedAudit.setEnginDepartIds(list);
		}
		//过滤门店
		if(null == bizCompletedAudit.getStoreId()){
			if(null != user.getStoreId()){
				bizCompletedAudit.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(bizCompletedAudit.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizCompletedAudit.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}else{
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizCompletedAudit.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
		model.addAttribute("bizCompletedAudit",bizCompletedAudit);
		return "modules/bizcompleted/completedAuditList";
	}
	
	@RequiresPermissions("bizcompletedaudit:bizCompletedAudit:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizCompletedAudit bizCompletedAudit, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if(null == bizCompletedAudit.getEnginDepartId()){
			if(null!= UserUtils.getUser().getEmpId()){
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if(list != null && list.size()>0){
					bizCompletedAudit.setEnginDepartIds(list);
				}else{
					bizCompletedAudit.setEnginDepartIds(null);
				}
			} else {
				bizCompletedAudit.setEnginDepartIds(null);
			}
		}else{
			List<Integer> list = new ArrayList<>();
			list.add(bizCompletedAudit.getEnginDepartId());
			bizCompletedAudit.setEnginDepartIds(list);
		}
		//过滤门店
		if(null == bizCompletedAudit.getStoreId()){
			if(null != user.getStoreId()){
				bizCompletedAudit.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(bizCompletedAudit.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizCompletedAudit.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}else{
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizCompletedAudit.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		if(bizCompletedAudit.getOrderStatusNumber() != null && bizCompletedAudit.getOrderStatusNumber().length()>0){
			String[] status = bizCompletedAudit.getOrderStatusNumber().split(",");
			if(null!=status && status.length>0){
				bizCompletedAudit.setStatus(Arrays.asList(status));
			}
		}else if(bizCompletedAudit.getOrderStatusNumber() == null || bizCompletedAudit.getOrderStatusNumber().length()==0){
			bizCompletedAudit.setOrderStatusNumber("300,340");
			String[] status = bizCompletedAudit.getOrderStatusNumber().split(",");
			bizCompletedAudit.setStatus(Arrays.asList(status));
		}
		
		Page<BizCompletedAudit> page = bizCompletedAuditService.findPage(new Page<BizCompletedAudit>(request, response), bizCompletedAudit); 
		model.addAttribute("page", page);
		model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
		model.addAttribute("bizCompletedAudit",bizCompletedAudit);
		return "modules/bizcompleted/completedAuditList";
	}
	/**
	 * 查询项目经理竣工提成明细
	 * @return
	 */
	@RequestMapping(value = "queryManagerCompletedDetail")
	public String queryManagerCompletedDetail(Integer orderId,Model model){
		BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap = inspectorConfirmService.queryManagerCommissionByOrderId(orderId);
		//竣工提成
		Double commissionAmount = bizPmStarCommissionCnfgSnap.getCommissionAmount()
				* bizPmStarCommissionCnfgSnap.getCommissionRateComplete();
		//质保金
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
				if(pmGuaranteeMoney<0){
					pmGuaranteeMoney = 0.00;
				}
			}else{
            	pmGuaranteeMoney=gmcs.getGuaranteeMoneyPerOrder();
            }			
		}
		
		//竣工罚款
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
		//自采材料报销金额
		Map<String,Object> map1 =new HashMap<String,Object>();
		map1.put("orderId", orderId);
		map1.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_11);
		Double sinceMaterials = inspectorConfirmService.queryManangerPenalty(map1);
		if(sinceMaterials<0){
			sinceMaterials = 0-sinceMaterials;
		}
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orderId", orderId);
//		param.put("pmEmployeeId",bizPmStarCommissionCnfgSnap.getPmEmployeeId());
		List<BizMaterialSelfbuyVo> materialSelfbuyList = bizPmSettleBillService.querySelfbuyMaterial(param);
		
		//竣工任务包材料结算总金额
		double pmMaterialsSettleAmount = 0.00;
		List<PmMaterialsSettleInfo> pmMaterials = pmMaterialsSettleInfoService.queryPmMaterialsByOrderId(orderId);
		if(pmMaterials != null && pmMaterials.size()>0){
			for(PmMaterialsSettleInfo info : pmMaterials){
				pmMaterialsSettleAmount = pmMaterialsSettleAmount+info.getPmMaterialsSettleAmount();
			}
		}
		
		//竣工奖励金额
		double pmRewardAmount = 0.0;
		BizAssessRewardPunish rewardPunish = new BizAssessRewardPunish();
		rewardPunish.setRelatedBusinessIdInt(orderId);
		rewardPunish.setRewardPunishTargetEmployeeId(bizPmStarCommissionCnfgSnap.getPmEmployeeId());
		rewardPunish.setRewardPunishTargetEmployeeType("1");
		rewardPunish.setRewardPunishTargetType("10");
		rewardPunish.setIsRewardOrPunish("1");
		rewardPunish.setIsMonthInspection("0");
		rewardPunish.setRewardPunishStatus("1");
		pmRewardAmount = bizAssessRewardPunishService.queryTotalAmountByParam(rewardPunish);
		//竣工扣款金额
		double pmPunishAmount = 0.0;
		rewardPunish.setIsRewardOrPunish("2");
		pmPunishAmount = bizAssessRewardPunishService.queryTotalAmountByParam(rewardPunish);

		//巡检
		rewardPunish.setIsMonthInspection("1");
		//竣工巡检奖励金额
		double pmInspectionRewardAmount = 0.0;
		rewardPunish.setIsRewardOrPunish("1");
		pmInspectionRewardAmount = bizAssessRewardPunishService.queryTotalAmountByParam(rewardPunish);
		//竣工巡检罚款金额
		double pmInspectionPunishAmount = 0.0;
		rewardPunish.setIsRewardOrPunish("2");
		pmInspectionPunishAmount = bizAssessRewardPunishService.queryTotalAmountByParam(rewardPunish);

		//竣工提成合计金额
		Double settleAmount = commissionAmount+sinceMaterials-pmGuaranteeMoney-managerPenalty+pmMaterialsSettleAmount+pmRewardAmount-pmPunishAmount+pmInspectionRewardAmount-pmInspectionPunishAmount;
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
		model.addAttribute("pmRewardAmount",pmRewardAmount);
		model.addAttribute("pmPunishAmount",pmPunishAmount);

		model.addAttribute("pmInspectionRewardAmount",pmInspectionRewardAmount);
		model.addAttribute("pmInspectionPunishAmount",pmInspectionPunishAmount);

		return "modules/bizcompleted/managerCompletedDetail";
	}
	
	/**
	 * list主页面
	 * 驳回
	 * 修改订单状态为330-结算员竣工审核不通过
	 */
	@RequestMapping(value = {"auditFail", ""})
	public String auditFail(BizCompletedAudit bizCompletedAudit, HttpServletRequest request, HttpServletResponse response, Model model,
			String id) {
		logger.info("订单编号："+id);
		model.addAttribute("orderID", id);
		return "modules/bizcompleted/auditFail";
	}
	
	@RequestMapping(value = "checkOrderTaskpackByOrderId")
	public @ResponseBody int checkOrderTaskpackByOrderId(int orderId){
		return bizCompletedAuditService.checkOrderTaskpackByOrderId(orderId);
	}
	
}