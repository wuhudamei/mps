package cn.damei.web.mobile.Inspector;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.config.Global;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.entity.mobile.Inspector.CheckConfirm;
import cn.damei.service.mobile.Inspector.CheckConfirmService;
import cn.damei.service.mobile.Inspector.InspectorEvaluateWorkerService;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.service.mobile.Manager.BizEvalTaskpackRoleIndexScoreService;
import cn.damei.service.mobile.Manager.BizEvalTaskpackScoreService;
import cn.damei.entity.mobile.Manager.BusinessPic;
import cn.damei.web.mobile.Manager.PackTimeChangeController;
import cn.damei.service.mobile.Manager.ProblemService;
import cn.damei.service.mobile.Manager.QualityControlService;
import cn.damei.entity.mobile.Manager.SettlementEvalStore;
import cn.damei.entity.modules.BizEvalActivityIndex;


@Controller
@RequestMapping(value = "${adminPath}/app/pqc/confirm")
public class CheckConfirmController {

    @Autowired
    private CheckConfirmService checkConfirmService;

    @Autowired
    private ProblemService problemService;
    @Autowired
    private InspectorEvaluateWorkerService inspectorEvaluateWorkerService;

    @Autowired
    private BizEvalTaskpackScoreService bizEvalTaskpackScoreService;

    @Autowired
    private BizEvalTaskpackRoleIndexScoreService bizEvalTaskpackRoleIndexScoreService;
    @Autowired
    private QualityControlService qualityControlService;


    private static Logger logger = LoggerFactory.getLogger(PackTimeChangeController.class);


    @RequestMapping(value = "checkConfirmList")
    private String reportList(HttpServletRequest request, Model model) throws IOException {

        int id = Integer.valueOf(request.getParameter("id"));

        CheckConfirm checkConfirm = checkConfirmService.findQcBillById(id);


        if (null != checkConfirm) {
            if (!checkConfirm.getStatus().equals("5") && !checkConfirm.getStatus().equals("20")) {
                return "redirect:" + Global.getAdminPath() + "/app/pqc/checkList/list?timeForbidden=1";
            }
        }


        Map<String, Object> scoreMap = new HashMap<String, Object>();
        scoreMap.put("relatedBusinessId", id);
        scoreMap.put("evalType", "2");
        int count = bizEvalTaskpackScoreService.queryCountByMap(scoreMap);
        model.addAttribute("count", count);
        if (count > 0) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("relatedBusinessId", id);
            map.put("evalType", "2");
            map.put("evalRoleType", ConstantUtils.EVAL_ROLE_TYPE_201);
            List<SettlementEvalStore> evalStoreList = bizEvalTaskpackRoleIndexScoreService.querySettlementEval(map);
            if (CollectionUtils.isNotEmpty(evalStoreList)) {
                for (SettlementEvalStore store : evalStoreList) {
                    Double selectCount = store.getGotScore() / (store.getEvalTotalScore() / 5);
                    store.setSelectCount(selectCount.intValue());
                }
            }
            model.addAttribute("evalStoreList", evalStoreList);
        } else {

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("checkNodeId", checkConfirm.getQcCheckNodeId());
            map.put("expectCheckDatetime", checkConfirm.getExpectCheckDatetime());
            map.put("evalRoleType", ConstantUtils.EVAL_ROLE_TYPE_2);
            List<BizEvalActivityIndex> bizEvalIndexList = inspectorEvaluateWorkerService
                    .queryManagerEvalActivityList(map);
            model.addAttribute("bizEvalIndexList", bizEvalIndexList);
        }

        String text = "3";
        List<ReportCheckDetailsPic> picList = problemService.findPic(id, text);
        int countPic = qualityControlService.fingdPicNum(checkConfirm.getId());
        String baseUrl = PicRootName.picPrefixName();
        model.addAttribute("picList", picList);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("checkConfirm", checkConfirm);
        model.addAttribute("count", count);
        model.addAttribute("countPic", countPic);
        return "mobile/modules/pqc/check/confirm/check_confirm";
    }



    @RequestMapping(value = "checkConfirm", method = RequestMethod.POST)
    public @ResponseBody
    String checkConfirm(String delayReasonQc, String id, String input_date, String[] photo,
                        String bizEvalActivityId, String[] bizEvalActivityIndexId, String[] bizEvalActivityIndexEvalTotalScore,
                        String[] bizEvalActivityIndexSelectCount, HttpServletRequest request) throws UnsupportedEncodingException {



       String result = checkConfirmService.updateInform(delayReasonQc, id, photo,
                bizEvalActivityId, bizEvalActivityIndexId, bizEvalActivityIndexEvalTotalScore,
                bizEvalActivityIndexSelectCount, request);


        return result;
    }


    @RequestMapping(value = "viewPic")
    private String viewPic(int qcBillId, HttpServletRequest request, Model model) throws Exception {


        List<BusinessPic> picList = qualityControlService.findPic(qcBillId);

        String baseUrl = PicRootName.picPrefixName();

        model.addAttribute("picList", picList);
        model.addAttribute("baseUrl", baseUrl);
        return "mobile/modules/pqc/check/confirm/photo";
    }
}
