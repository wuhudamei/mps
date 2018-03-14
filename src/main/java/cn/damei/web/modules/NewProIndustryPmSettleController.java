package cn.damei.web.modules;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizBusinessRewardPunish;
import cn.damei.entity.modules.BizOrderContractSettle;
import cn.damei.service.modules.BizBusinessRewardPunishService;
import cn.damei.service.modules.BizOrderChangeService;
import cn.damei.service.modules.BizOrderContractSettleService;
import cn.damei.entity.modules.BizOrderMaterialCarryCost;
import cn.damei.service.modules.BizOrderMaterialCarryCostService;
import cn.damei.entity.modules.BizPmPreIndustrySettleBill;
import cn.damei.service.modules.BizPmPreIndustrySettleBillService;
import cn.damei.service.modules.BizBusinessStatusLogService;
import cn.damei.entity.modules.BizQcLongwayCommissionLog;
import cn.damei.service.modules.BizQcLongwayCommissionLogService;
import cn.damei.entity.modules.BizNormalPmSettleNode;
import cn.damei.service.modules.BizNormalPmSettleNodeService;
import cn.damei.entity.modules.BizOrderMaterialsStandard;
import cn.damei.entity.modules.Order2;
import cn.damei.service.modules.OrderService2;
import cn.damei.service.modules.BizOrderTaskpackagePaymentService;
import cn.damei.entity.modules.BizPmGuaranteeMoneyLog;
import cn.damei.service.modules.BizPmGuaranteeMoneyLogService;
import cn.damei.entity.modules.BizPmGuaranteeMoneyCnfgSnap;
import cn.damei.service.modules.BizPmGuaranteeMoneyCnfgSnapService;
import cn.damei.service.modules.BizPmSettleCategoryDetailService;
import cn.damei.service.modules.BizPmSettleCategorySummaryService;
import cn.damei.entity.modules.ProIndustryPmSettleInfo;
import cn.damei.service.modules.ProIndustryPmSettleService;
import cn.damei.entity.modules.BizGuaranteeMoneyBalance;
import cn.damei.service.modules.BizGuaranteeMoneyBalanceService;
import cn.damei.service.modules.InspectorConfirmService;
import cn.damei.common.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author zhangkangjian
 * @Description: 准产业项目经理结算（第二版）
 * @date 2018/1/22 11:27
 */
@Controller
@RequestMapping(value = "${adminPath}/newProIndustryPmSettle")
public class NewProIndustryPmSettleController extends BaseController {
    @Autowired
    private ProIndustryPmSettleService proIndustryPmSettleService;
    @Autowired
    private BizOrderTaskpackagePaymentService bizOrderTaskpackagePaymentService;
    @Autowired
    private OrderService2 orderService2;
    @Autowired
    private BizNormalPmSettleNodeService bizNormalPmSettleNodeService;
    @Autowired
    private BizOrderContractSettleService bizOrderContractSettleService;
    @Autowired
    private BizOrderChangeService bizOrderChangeService;
    @Autowired
    private InspectorConfirmService inspectorConfirmService;
    @Autowired
    private BizOrderMaterialCarryCostService bizOrderMaterialCarryCostService;
    @Autowired
    private BizBusinessRewardPunishService bizBusinessRewardPunishService;
    @Autowired
    private BizPmPreIndustrySettleBillService bizPmPreIndustrySettleBillService;
    @Autowired
    private BizPmGuaranteeMoneyCnfgSnapService bizPmGuaranteeMoneyCnfgSnapService;
    @Autowired
    private BizPmGuaranteeMoneyLogService bizPmGuaranteeMoneyLogService;
    @Autowired
    private BizGuaranteeMoneyBalanceService bizGuaranteeMoneyBalanceService;
    @Autowired
    private BizQcLongwayCommissionLogService bizQcLongwayCommissionLogService;

    @Autowired
    private BizPmSettleCategorySummaryService bizPmSettleCategorySummaryService;
    @Autowired
    private BizPmSettleCategoryDetailService bizPmSettleCategoryDetailService;
    @Autowired
    private BizBusinessStatusLogService bizBusinessStatusLogService;

    /**
     * 项目经理中期结算查询页面
     *
     * @param proIndustryPmSettleInfo
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "queryProIndustryPmMidwaySettle")
    public String queryProIndustryPmMidwaySettle(ProIndustryPmSettleInfo proIndustryPmSettleInfo, Model model,
                                                 HttpServletRequest request, HttpServletResponse response) {
        // 过滤门店
        if (proIndustryPmSettleInfo.getStoreId() == null) {
            String storeId = UserUtils.getUser().getStoreId();
            if (StringUtils.isBlank(storeId)) {
                proIndustryPmSettleInfo.setStoreId(null);
            } else {
                proIndustryPmSettleInfo.setStoreId(Integer.parseInt(storeId));
            }
        }
        if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
            model.addAttribute("storeDropEnable", true);
        } else {
            proIndustryPmSettleInfo.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
        }
        return "modules/proIndustryPmSettle/newProIndustryPmMidwaySettle";
    }

    /**
     * 项目经理中期结算查询列表
     *
     * @param proIndustryPmSettleInfo
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "queryProIndustryPmMidwaySettleList")
    public String queryProIndustryPmMidwaySettleList(ProIndustryPmSettleInfo proIndustryPmSettleInfo, Model model,
                                                     HttpServletRequest request, HttpServletResponse response) {
        // 过滤门店
        if (proIndustryPmSettleInfo.getStoreId() == null) {
            String storeId = UserUtils.getUser().getStoreId();
            if (StringUtils.isBlank(storeId)) {
                proIndustryPmSettleInfo.setStoreId(null);
            } else {
                proIndustryPmSettleInfo.setStoreId(Integer.parseInt(storeId));
            }
        }

        if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
            model.addAttribute("storeDropEnable", true);
        } else {
            proIndustryPmSettleInfo.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
        }
        if (proIndustryPmSettleInfo.getEnginDepartId() != null) {
            List<Integer> list = new ArrayList<>();
            list.add(proIndustryPmSettleInfo.getEnginDepartId());
            proIndustryPmSettleInfo.setEnginDepartIds(list);
        }
        proIndustryPmSettleInfo.setProjectMode(4);
        Page<ProIndustryPmSettleInfo> page = proIndustryPmSettleService.queryProIndustryPmMidwaySettle(
                new Page<ProIndustryPmSettleInfo>(request, response), proIndustryPmSettleInfo);
        model.addAttribute("page", page);
        return "modules/proIndustryPmSettle/newProIndustryPmMidwaySettle";
    }

    /**
     * 创建中期结算单
     *
     * @param orderId
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "openMidwaySettleInfoPage")
    public String openMidwaySettleInfoPage(Integer orderId, Model model, HttpServletRequest request,
                                           HttpServletResponse response) {
        Order2 order2 = orderService2.get(orderId);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("storeId", order2.getStoreId());
        param.put("projectMode", order2.getProjectMode());
        param.put("settleStage", 10);
        BizNormalPmSettleNode bizNormalPmSettleNode = bizNormalPmSettleNodeService.querySettleNodeByParam(param);
        // 1.承包总价
        Double contractAmount = 0.0;
        Map<String, Object> settParam = new HashMap<String, Object>();
        settParam.put("orderId", orderId);
        settParam.put("settleStage", 10);
        BizOrderContractSettle contractSettle = bizOrderContractSettleService.findOrderContractSettleByParam(settParam);
        if (contractSettle == null) {
            contractSettle = new BizOrderContractSettle();
            Double subsidySquare = Double.valueOf(order2.getContractArea());// 补助面积
            Double subsidyPrice = 0.0;// 补助单价
            if (subsidySquare > 0 && subsidySquare < 71) {
                Map<String, Object> subsidyPriceParam = new HashMap<String, Object>();
                subsidyPriceParam.put("storeId", order2.getStoreId());
                subsidyPriceParam.put("projectMode", order2.getProjectMode());
                subsidyPriceParam.put("contractArea", subsidySquare);
                subsidyPrice = proIndustryPmSettleService.querySubsidyPriceByParam(subsidyPriceParam);
                if (subsidyPrice == null) {
                    subsidyPrice = 0.0;
                }
            }
            Double subsidyAmount = subsidySquare * subsidyPrice;// 补助金额
            // 承包总价=计价面积 * 结算单价+补助金额
            if(bizNormalPmSettleNode ==null){
                bizNormalPmSettleNode = new BizNormalPmSettleNode();
            }
            if(bizNormalPmSettleNode.getSettlePrice() == null){
                bizNormalPmSettleNode.setSettlePrice("0");
            }
            contractAmount = Double.valueOf(bizNormalPmSettleNode.getSettlePrice())
                    * Double.valueOf(order2.getContractArea()) + subsidyAmount;
            // 保存承包结算的业务数据
            contractSettle.setOrderId(orderId);
            contractSettle.setSettleStage("10");
            contractSettle.setPackagedSquare( Double.valueOf(order2.getContractArea()));
            contractSettle.setPackagedPrice(Double.valueOf(bizNormalPmSettleNode.getSettlePrice()));
            contractSettle.setPackagedAmount(Double.valueOf(order2.getContractArea()) * Double.valueOf(bizNormalPmSettleNode.getSettlePrice()));
            contractSettle.setContractSubsidySquare(subsidySquare);
            contractSettle.setContractSubsidyPrice(subsidyPrice);
            contractSettle.setContractSubsidyAmount(subsidyAmount);
            contractSettle.setContractAmount(contractAmount);
            bizOrderContractSettleService.save(contractSettle);
        } else {
            contractAmount = contractSettle.getContractAmount();
        }
        // 2.辅料用量扣款
        Map<String, Object> auMaterParam = new HashMap<String, Object>();
        auMaterParam.put("orderId", orderId);
        auMaterParam.put("isSandCement", 0);
        Double auMaterAmount = proIndustryPmSettleService.queryAuxiliaryMaterialsAmountByOrderId(auMaterParam);
        if (auMaterAmount == null) {
            auMaterAmount = 0.00;
        }
        if(auMaterAmount>0){
            auMaterAmount = 0-auMaterAmount;
        }
        // 3.沙子水泥扣款
        Map<String, Object> sandParam = new HashMap<String, Object>();
        sandParam.put("orderId", orderId);
        sandParam.put("isSandCement", 1);
        Double sandAmount = proIndustryPmSettleService.queryAuxiliaryMaterialsAmountByOrderId(sandParam);
        if (sandAmount == null) {
            sandAmount = 0.00;
        }
        if(sandAmount>0){
            sandAmount = 0-sandAmount;
        }
        // 4.标化材料扣款
        Double standardMaAmount = 0.0;
        List<BizOrderMaterialsStandard> materialsStandardList = inspectorConfirmService
                .queryMaterialsStandardByOrderId(orderId);

        if (materialsStandardList != null && materialsStandardList.size() > 0) {
            for (BizOrderMaterialsStandard materialsStandard : materialsStandardList) {
                standardMaAmount = standardMaAmount + materialsStandard.getMaterialsAmount();
            }
        }
        if(standardMaAmount>0){
            standardMaAmount = 0-standardMaAmount;
        }
        // 5.开关面板扣款
        Double mainPanelAmount = proIndustryPmSettleService.queryMainPanelAmountByOrderId(orderId);
        if (mainPanelAmount == null) {
            mainPanelAmount = 0.00;
        }
        if(mainPanelAmount>0){
            mainPanelAmount = 0-mainPanelAmount;
        }
        // 6.工人人工费扣款
        Double workerAmount = proIndustryPmSettleService.queryWorkerAmountByOrderId(orderId);
        if(workerAmount == null){
            workerAmount = 0.00;
        }
        if(workerAmount>0){
            workerAmount = 0-workerAmount;
        }
        // 7.中期质检罚款
        Map<String, Object> pmQcParam = new HashMap<String, Object>();
        pmQcParam.put("orderId", orderId);
        pmQcParam.put("pmEmployeeId", order2.getItemManagerId());
        Double pmQcFine = proIndustryPmSettleService.queryPmQcFineByParam(pmQcParam);
        if (pmQcFine == null) {
            pmQcFine = 0.00;
        }
        // 8.基装增项
        Map<String, Object> baseInstalledParam = new HashMap<String, Object>();
        baseInstalledParam.put("orderId", orderId);
        baseInstalledParam.put("changeType", 10);
        Double baseInstalledAmount = proIndustryPmSettleService.queryBaseInstalledAmount(baseInstalledParam);
        if (baseInstalledAmount == null) {
            baseInstalledAmount = 0.00;
        }

        // 9.中期变更增项
        baseInstalledParam.put("changeType", 20);
        Double midwayChangeAddAmount = proIndustryPmSettleService.queryBaseInstalledAmount(baseInstalledParam);
        if (midwayChangeAddAmount == null) {
            midwayChangeAddAmount = 0.00;
        }

        // 10.中期变更减项
        baseInstalledParam.put("changeType", 30);
        Double midwayChangeReductAmount = proIndustryPmSettleService.queryBaseInstalledAmount(baseInstalledParam);
        if (midwayChangeReductAmount == null) {
            midwayChangeReductAmount = 0.00;
        }

        if(midwayChangeReductAmount>0){
            midwayChangeReductAmount = 0-midwayChangeReductAmount;
        }

        // 11.材料搬运及运输费
        BizOrderMaterialCarryCost bizOrderMaterialCarryCost = bizOrderMaterialCarryCostService
                .queryOrderMaterialCarryCostByOrderId(orderId);
        if(bizOrderMaterialCarryCost == null){
            bizOrderMaterialCarryCost = new BizOrderMaterialCarryCost();
        }
        if(bizOrderMaterialCarryCost.getAccountAmount() == null){
            bizOrderMaterialCarryCost.setAccountAmount(0.00);
        }
        // 12.中期奖励
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("orderId", orderId);
        map1.put("employeeType", 2);
        map1.put("relatedBusinessType", "1");
        map1.put("rewardPunishType", "1");
        BizBusinessRewardPunish midwayReward = bizBusinessRewardPunishService.queryBusinessRewardPunishByParam(map1);
        if (midwayReward == null) {
            midwayReward = new BizBusinessRewardPunish();
            midwayReward.setRewardPunishAmount(0.00);
        }

        // 13.中期罚款
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("orderId", orderId);
        map2.put("employeeType", 2);
        map2.put("relatedBusinessType", "1");
        map2.put("rewardPunishType", "2");
        BizBusinessRewardPunish midwayPunish = bizBusinessRewardPunishService.queryBusinessRewardPunishByParam(map2);
        if (midwayPunish == null) {
            midwayPunish = new BizBusinessRewardPunish();
            midwayPunish.setRewardPunishAmount(0.00);
        }

        if(midwayPunish.getRewardPunishAmount()>0){
            midwayPunish.setRewardPunishAmount(0-midwayPunish.getRewardPunishAmount());
        }

        //14.质保金
        Double completeGuaranteeMoneyAmount = 0.00;
        BizPmGuaranteeMoneyCnfgSnap gmcs = bizPmGuaranteeMoneyCnfgSnapService.findGmc(orderId);
        if (gmcs != null) {
            Map<String,Object> logParam = new HashMap<String,Object>();
            logParam.put("orderId", orderId);
            logParam.put("pmEmPloyeeId", gmcs.getPmEmployeeId());
            BizPmGuaranteeMoneyLog moneyLog = bizPmGuaranteeMoneyLogService.findByParam(logParam);
            if(moneyLog == null){
                Double money = 0.0;
                BizPmGuaranteeMoneyLog log = bizPmGuaranteeMoneyLogService.findByEmployeeId(gmcs.getPmEmployeeId());
                BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance = bizGuaranteeMoneyBalanceService
                        .findGuaranteeMoneyBalanceByEmployeeId(gmcs.getPmEmployeeId());
                if (bizGuaranteeMoneyBalance == null || bizGuaranteeMoneyBalance.getId() == null) {
                    bizGuaranteeMoneyBalance = new BizGuaranteeMoneyBalance();
                }
                if (log != null) {
                    money = bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance();
                }else{
                    money = bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance();
                }
                if(money == null){
                    money = 0.0;
                }
                BizPmGuaranteeMoneyLog gml = new BizPmGuaranteeMoneyLog();
                gml.setPmEmployeeId(gmcs.getPmEmployeeId());
                gml.setOrderId(gmcs.getOrderId());
                gml.setTakeoffDatetime(new Date());
                Double total = money + gmcs.getGuaranteeMoneyPerOrder();
                Double takeoffAmount = 0.00;
                if (total >= gmcs.getGuaranteeMoneyMax()) {
                    takeoffAmount = gmcs.getGuaranteeMoneyMax() - money;
                    if(takeoffAmount <0){
                        takeoffAmount = 0.00;
                    }
                } else {
                    takeoffAmount = gmcs.getGuaranteeMoneyPerOrder();
                }
                gml.setTakeoffAmount(takeoffAmount);
                gml.setTakeoffAmountTotal(
                        bizGuaranteeMoneyBalance.getGuaranteeMoneyAmountPaidSettle() + gml.getTakeoffAmount());
                gml.setGuaranteeMoneyBalance(
                        bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance() + gml.getTakeoffAmount());
                gml.preInsert();

                Integer id = bizPmGuaranteeMoneyLogService.insert1(gml);
                // 更新余额信息
                if (bizGuaranteeMoneyBalance == null || bizGuaranteeMoneyBalance.getId() == null) {
                    bizGuaranteeMoneyBalance = new BizGuaranteeMoneyBalance();
                    bizGuaranteeMoneyBalance.setEmployeeId(gml.getPmEmployeeId());
                    bizGuaranteeMoneyBalance.setGuaranteeMoneyAmountPaidSettle(gml.getTakeoffAmountTotal());
                } else {
                    bizGuaranteeMoneyBalance.setGuaranteeMoneyAmountPaidSettle(
                            bizGuaranteeMoneyBalance.getGuaranteeMoneyAmountPaidSettle() + gml.getTakeoffAmount());
                }
                bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(gml.getGuaranteeMoneyBalance());
                bizGuaranteeMoneyBalanceService.save(bizGuaranteeMoneyBalance);
                completeGuaranteeMoneyAmount = gml.getTakeoffAmount();
            }else{
                completeGuaranteeMoneyAmount = moneyLog.getTakeoffAmount();
            }

        }
        if(completeGuaranteeMoneyAmount>0){
            completeGuaranteeMoneyAmount = 0-completeGuaranteeMoneyAmount;
        }


        model.addAttribute("order2", order2);
        model.addAttribute("contractAmount", contractAmount);
        model.addAttribute("auMaterAmount", auMaterAmount);
        model.addAttribute("sandAmount", sandAmount);
        model.addAttribute("standardMaAmount", standardMaAmount);
        model.addAttribute("mainPanelAmount", mainPanelAmount);
        model.addAttribute("workerAmount", workerAmount);
        model.addAttribute("pmQcFine", pmQcFine);
        model.addAttribute("baseInstalledAmount", baseInstalledAmount);
        model.addAttribute("midwayChangeAddAmount", midwayChangeAddAmount);
        model.addAttribute("midwayChangeReductAmount", midwayChangeReductAmount);
        model.addAttribute("bizOrderMaterialCarryCost", bizOrderMaterialCarryCost);
        model.addAttribute("midwayReward", midwayReward);
        model.addAttribute("midwayPunish", midwayPunish);
        model.addAttribute("bizNormalPmSettleNode",bizNormalPmSettleNode);
        /*质保金*/
        model.addAttribute("completeGuaranteeMoneyAmount",completeGuaranteeMoneyAmount);
        return "modules/proIndustryPmSettle/newSettleInfo";
    }

    /**
     * 保存中期结算单
     *
     * @param bizPmPreIndustrySettleBill
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "createMidwaySettleBille")
    public String createMidwaySettleBille(BizPmPreIndustrySettleBill bizPmPreIndustrySettleBill,
                                          RedirectAttributes redirectAttributes, Model model, HttpServletRequest request,
                                          HttpServletResponse response) {
        bizPmPreIndustrySettleBill.setIsNewSettleBill("1");//新单子
        String result = bizPmPreIndustrySettleBillService.createMidwaySettleBille(bizPmPreIndustrySettleBill);
        if (result.equals("0")) {
            addMessage(redirectAttributes, "结算单生成成功！");
        } else if (result.equals("1")) {
            addMessage(redirectAttributes, "结算单已生成");
        }
        return "redirect:" + Global.getAdminPath()
                + "/newProIndustryPmSettle/queryProIndustryPmMidwaySettleList";
    }

    /**
     * 项目经理竣工结算页面
     *
     * @param proIndustryPmSettleInfo
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "queryProIndustryPmCompleteSettle")
    public String queryProIndustryPmCompleteSettle(ProIndustryPmSettleInfo proIndustryPmSettleInfo, Model model,
                                                   HttpServletRequest request, HttpServletResponse response) {
        // 过滤门店
        if (proIndustryPmSettleInfo.getStoreId() == null) {
            String storeId = UserUtils.getUser().getStoreId();
            if (StringUtils.isBlank(storeId)) {
                proIndustryPmSettleInfo.setStoreId(null);
            } else {
                proIndustryPmSettleInfo.setStoreId(Integer.parseInt(storeId));
            }
        }
        if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
            model.addAttribute("storeDropEnable", true);
        } else {
            proIndustryPmSettleInfo.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
        }
        return "modules/proIndustryPmSettle/newProIndustryPmCompleteSettle";
    }

    /**
     * 项目经理竣工结算查询列表
     *
     * @param proIndustryPmSettleInfo
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "queryProIndustryPmCompleteSettleList")
    public String queryProIndustryPmCompleteSettleList(ProIndustryPmSettleInfo proIndustryPmSettleInfo, Model model,
                                                       HttpServletRequest request, HttpServletResponse response) {
        // 过滤门店
        if (proIndustryPmSettleInfo.getStoreId() == null) {
            String storeId = UserUtils.getUser().getStoreId();
            if (StringUtils.isBlank(storeId)) {
                proIndustryPmSettleInfo.setStoreId(null);
            } else {
                proIndustryPmSettleInfo.setStoreId(Integer.parseInt(storeId));
            }
        }

        if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
            model.addAttribute("storeDropEnable", true);
        } else {
            proIndustryPmSettleInfo.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
        }
        if (proIndustryPmSettleInfo.getEnginDepartId() != null) {
            List<Integer> list = new ArrayList<>();
            list.add(proIndustryPmSettleInfo.getEnginDepartId());
            proIndustryPmSettleInfo.setEnginDepartIds(list);
        }
        proIndustryPmSettleInfo.setProjectMode(4);
        Page<ProIndustryPmSettleInfo> page = proIndustryPmSettleService.queryProIndustryPmCompleteSettle(
                new Page<ProIndustryPmSettleInfo>(request, response), proIndustryPmSettleInfo);
        model.addAttribute("page", page);
        return "modules/proIndustryPmSettle/newProIndustryPmCompleteSettle";
    }


    /**
     * 创建竣工结算单
     *
     * @param orderId
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "openCompleteSettleInfoPage")
    public String openCompleteSettleInfoPage(Integer orderId, Model model, HttpServletRequest request,
                                             HttpServletResponse response) {
        Order2 order2 = orderService2.get(orderId);
        //9.中期结算单
        Map<String,Object> midwaySettleBillParam = new HashMap<String,Object>();
        midwaySettleBillParam.put("orderId", orderId);
        midwaySettleBillParam.put("settleBillType", 1);
        BizPmPreIndustrySettleBill midwaySettleBill = bizPmPreIndustrySettleBillService.queryPmPreIndustrySettleBillByParam(midwaySettleBillParam);




        // 1.承包总价
        Double contractAmount = 0.0;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("storeId", order2.getStoreId());
        param.put("projectMode", order2.getProjectMode());
        param.put("settleStage", 20);
        BizNormalPmSettleNode bizNormalPmSettleNode = bizNormalPmSettleNodeService.querySettleNodeByParam(param);
        Map<String, Object> settParam = new HashMap<String, Object>();
        settParam.put("orderId", orderId);
        settParam.put("settleStage", 20);
        BizOrderContractSettle contractSettle = bizOrderContractSettleService.findOrderContractSettleByParam(settParam);
        if (contractSettle == null) {
            contractSettle = new BizOrderContractSettle();
            Double subsidySquare = Double.valueOf(order2.getContractArea());// 补助面积
            Double subsidyPrice = 0.0;// 补助单价
            if (subsidySquare > 0 && subsidySquare <= 70) {
                Map<String, Object> subsidyPriceParam = new HashMap<String, Object>();
                subsidyPriceParam.put("storeId", order2.getStoreId());
                subsidyPriceParam.put("projectMode", order2.getProjectMode());
                subsidyPriceParam.put("contractArea", subsidySquare);
                subsidyPrice = proIndustryPmSettleService.querySubsidyPriceByParam(subsidyPriceParam);
                if (subsidyPrice == null) {
                    subsidyPrice = 0.0;
                }
            }
            Double subsidyAmount = subsidySquare * subsidyPrice;// 补助金额
            // 承包总价=计价面积 * 结算单价+补助金额
            if(bizNormalPmSettleNode ==null){
                bizNormalPmSettleNode = new BizNormalPmSettleNode();
            }
            if(bizNormalPmSettleNode.getSettlePrice() == null){
                bizNormalPmSettleNode.setSettlePrice("0");
            }
            contractAmount = Double.valueOf(bizNormalPmSettleNode.getSettlePrice())
                    * Double.valueOf(order2.getContractArea()) + subsidyAmount;
            // 保存承包结算的业务数据
            contractSettle.setOrderId(orderId);
            contractSettle.setSettleStage("20");
            contractSettle.setPackagedSquare( Double.valueOf(order2.getContractArea()));
            contractSettle.setPackagedPrice(Double.valueOf(bizNormalPmSettleNode.getSettlePrice()));
            contractSettle.setPackagedAmount(Double.valueOf(order2.getContractArea()) * Double.valueOf(bizNormalPmSettleNode.getSettlePrice()));
            contractSettle.setContractSubsidySquare(subsidySquare);
            contractSettle.setContractSubsidyPrice(subsidyPrice);
            contractSettle.setContractSubsidyAmount(subsidyAmount);
            contractSettle.setContractAmount(contractAmount);
            bizOrderContractSettleService.save(contractSettle);
        } else {
            contractAmount = contractSettle.getContractAmount();
        }
//        查询中期结算单
      /*  BizPmPreIndustrySettleBill bizPmPreIndustrySettleBill = proIndustryPmSettleService.getSettlementBillByOrderIdAndType(orderId,1);
        proIndustryPmSettleService.query*//*

        Map<String, Object> paramMap = new HashMap<String, Object>();
        param.put("orderId", orderId);
        param.put("settleBillType", 1);
        BizPmPreIndustrySettleBill midwaySettleBill = bizPmPreIndustrySettleBillService
                .queryPmPreIndustrySettleBillByParam(paramMap);*/
        //2.竣工质检罚款
        Map<String, Object> pmQcParam = new HashMap<String, Object>();
        pmQcParam.put("orderId", orderId);
        pmQcParam.put("pmEmployeeId", order2.getItemManagerId());
        Double pmQcFine = proIndustryPmSettleService.queryPmQcFineByParam(pmQcParam);
        if (pmQcFine == null) {
            pmQcFine = 0.00;
        }

        //4.远程费
        Double completeLongwayCommissionAmount = 0.00;
        BizQcLongwayCommissionLog bizQcLongwayCommissionLog = new BizQcLongwayCommissionLog();
        bizQcLongwayCommissionLog.setOrderId(orderId);
        bizQcLongwayCommissionLog.setLongwayCommissionType("10");
        bizQcLongwayCommissionLog.setLongwayCommissionEmployeeId(order2.getItemManagerId());
        BizQcLongwayCommissionLog commissionLog = bizQcLongwayCommissionLogService.queryCommissionLogByParam(bizQcLongwayCommissionLog);
        if(commissionLog != null){
            completeLongwayCommissionAmount = 	commissionLog.getCommissionAmount();
        }
        //5.竣工变更增项
        Map<String, Object> baseInstalledParam = new HashMap<String, Object>();
        baseInstalledParam.put("orderId", orderId);
        baseInstalledParam.put("changeType", 40);
        Double completeChangeAddAmount = proIndustryPmSettleService.queryBaseInstalledAmount(baseInstalledParam);
        if (completeChangeAddAmount == null) {
            completeChangeAddAmount = 0.00;
        }
        //6.竣工变更减项
        baseInstalledParam.put("changeType", 50);
        Double completeChangeReductAmount = proIndustryPmSettleService.queryBaseInstalledAmount(baseInstalledParam);
        if (completeChangeReductAmount == null) {
            completeChangeReductAmount = 0.00;
        }
        if(completeChangeReductAmount>0){
            completeChangeReductAmount = 0-completeChangeReductAmount;
        }
        //7.竣工奖励
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("orderId", orderId);
        map1.put("employeeType", 2);
        map1.put("relatedBusinessType", "2");
        map1.put("rewardPunishType", "1");
        BizBusinessRewardPunish completeReward = bizBusinessRewardPunishService.queryBusinessRewardPunishByParam(map1);
        if (completeReward == null) {
            completeReward = new BizBusinessRewardPunish();
            completeReward.setRewardPunishAmount(0.00);
        }
        //8.竣工扣款
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("orderId", orderId);
        map2.put("employeeType", 2);
        map2.put("relatedBusinessType", "2");
        map2.put("rewardPunishType", "2");
        BizBusinessRewardPunish completePunish = bizBusinessRewardPunishService.queryBusinessRewardPunishByParam(map2);
        if (completePunish == null) {
            completePunish = new BizBusinessRewardPunish();
            completePunish.setRewardPunishAmount(0.00);
        }
        if(completePunish.getRewardPunishAmount()>0){
            completePunish.setRewardPunishAmount(0-completePunish.getRewardPunishAmount());
        }

        model.addAttribute("order2", order2);
        model.addAttribute("contractAmount",contractAmount);
        model.addAttribute("pmQcFine",pmQcFine);
        model.addAttribute("completeLongwayCommissionAmount",completeLongwayCommissionAmount);
        model.addAttribute("completeChangeAddAmount",completeChangeAddAmount);
        model.addAttribute("completeChangeReductAmount",completeChangeReductAmount);
        model.addAttribute("completeReward",completeReward);
        model.addAttribute("completePunish",completePunish);
        model.addAttribute("midwaySettleBill",midwaySettleBill);
        model.addAttribute("bizNormalPmSettleNode",bizNormalPmSettleNode);
        /*中期结算明细*/
        model.addAttribute("settleBill",midwaySettleBill);
        return "modules/proIndustryPmSettle/newCompleteSettleInfo";
    }

    /**
     * 保存竣工结算单
     * @param bizPmPreIndustrySettleBill
     * @param redirectAttributes
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "createCompleteSettleBille")
    public String createCompleteSettleBille(BizPmPreIndustrySettleBill bizPmPreIndustrySettleBill,
                                            RedirectAttributes redirectAttributes, Model model, HttpServletRequest request,
                                            HttpServletResponse response){
        bizPmPreIndustrySettleBill.setIsNewSettleBill("1");
        String result = bizPmPreIndustrySettleBillService.createCompleteSettleBille(bizPmPreIndustrySettleBill);

        if (result.equals("0")) {
            addMessage(redirectAttributes, "结算单生成成功！");
        } else if (result.equals("1")) {
            addMessage(redirectAttributes, "结算单已生成");
        }
        return "redirect:" + Global.getAdminPath()
                + "/newProIndustryPmSettle/queryProIndustryPmCompleteSettleList";
    }

    /**
    * @Description: 校验是否可以创建结算单
    * @Author zhangkangjian
    * @param
    * @return
    * @Date 2018/1/23 14:45
    */
    @RequestMapping(value = "isCheckedCreateSettleBille")
    @ResponseBody
    public String isCheckedCreateSettleBille(String orderId,String flag){
        //9.中期结算单
        Map<String,Object> midwaySettleBillParam = new HashMap<String,Object>();
        midwaySettleBillParam.put("orderId", orderId);
        midwaySettleBillParam.put("settleBillType", 1);
        BizPmPreIndustrySettleBill midwaySettleBill = bizPmPreIndustrySettleBillService.queryPmPreIndustrySettleBillByParam(midwaySettleBillParam);
        String isNewSettleBill = midwaySettleBill.getIsNewSettleBill();
        if(isNewSettleBill.equals(flag)){
            return "success";
        }else {
            return "fail";
        }
    }

}